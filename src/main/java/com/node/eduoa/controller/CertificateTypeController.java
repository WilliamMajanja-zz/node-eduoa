/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, node.com
 * Filename:		com.node.sample.controller.TaskController.java
 * Class:			TaskController
 * Date:			2013-4-21
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.eduoa.controller;

import com.node.eduoa.entity.OaCertificateType;
import com.node.eduoa.enums.SemesterEnum;
import com.node.eduoa.service.CertificateTypeService;
import com.node.eduoa.service.GradeService;
import com.node.eduoa.service.impl.CertificateTypeServiceImpl;
import com.node.eduoa.utils.YearUtils;
import com.node.system.log.Log;
import com.node.system.log.LogLevel;
import com.node.system.log.LogMessageObject;
import com.node.system.log.impl.LogUitl;
import com.node.system.util.dwz.AjaxObject;
import com.node.system.util.dwz.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.beanvalidator.BeanValidators;

import javax.validation.Validator;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** 
 * 职称证书管理
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午11:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/certificatetype")
public class CertificateTypeController extends BaseFormController {

    @Qualifier("certificateTypeServiceImpl")
    @Autowired
    private CertificateTypeService certificateTypeService;


	@Autowired
	private Validator validator;

	private static final String CREATE = "management/eduoa/certificatetype/create";
	private static final String UPDATE = "management/eduoa/certificatetype/update";
	private static final String LIST = "management/eduoa/certificatetype/list";
	private static final String VIEW = "management/eduoa/certificatetype/view";



    @RequiresPermissions("CertificateType:save")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String preCreate(Map<String, Object> map) {
        map.put("semesterEnums", SemesterEnum.values());
        map.put("years", YearUtils.getYears(3));
		return CREATE;
	}
	
	/**
	 * LogMessageObject的write用法实例。
	 */
    @Log(message="添加了{0}职称证书。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("CertificateType:save")
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody String create(OaCertificateType certificateType) {
		BeanValidators.validateWithException(validator, certificateType);
        try {
            certificateTypeService.save(certificateType);
        } catch (Exception e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }
		
		// 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{certificateType.getTypeName()}));
		return AjaxObject.newOk("任务添加成功！").toString();
	}
	
	/**
	 * LogMessageObject的ignore用法实例，ignore不会记录日志。
	 */
	@Log(message="你永远不会看见该日志，LogMessageObject的isWritten为false。", level=LogLevel.TRACE)
	@RequiresPermissions("CertificateType:edit")
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		OaCertificateType certificateType = certificateTypeService.get(id);
		
		map.put("certificateType", certificateType);
        map.put("years", YearUtils.getYears(3));
        map.put("semesterEnums", SemesterEnum.values());
		
		// 加入一个LogMessageObject，该对象的isWritten为false，不会记录日志。
		LogUitl.putArgs(LogMessageObject.newIgnore());
		return UPDATE;
	}

    @Log(message="修改了{0}职称证书。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("CertificateType:edit")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody String update(OaCertificateType certificateType) {
		BeanValidators.validateWithException(validator, certificateType);
		certificateTypeService.update(certificateType);

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{certificateType.getTypeName()}));
		return AjaxObject.newOk("职称证书修改成功！").toString();
	}


    @Log(message="删除了{0}职称证书。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("CertificateType:delete")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Long id) {
        OaCertificateType certificateType = certificateTypeService.get(id);
		certificateTypeService.delete(id);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{certificateType.getTypeName()}));
		return AjaxObject.newOk("职称证书删除成功！").setCallbackType("").toString();
	}
	
	
	/**
	 * Log的override用法实例
	 * 假如override为true，会忽略掉level
	 *                        i
	 * 批量删除展示
	 */
	@Log(message="删除了{0}职称证书。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("CertificateType:delete")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody String deleteMany(Long[] ids) {
		String[] titles = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			OaCertificateType certificateType = certificateTypeService.get(ids[i]);
			certificateTypeService.delete(certificateType.getId());
			titles[i] = certificateType.getTypeName();
		}
		
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
		return AjaxObject.newOk("职称证书删除成功！").setCallbackType("").toString();
	}

	@RequiresPermissions("CertificateType:view")
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, String keywords, Map<String, Object> map) {
		List<OaCertificateType> certificateTypes = null;
		if (StringUtils.isNotBlank(keywords)) {
			certificateTypes = certificateTypeService.find(page, keywords);
		} else {
			certificateTypes = certificateTypeService.findAll(page);
		}
        SemesterEnum[] semesterEnums = SemesterEnum.values();
        map.put("semesterEnums", semesterEnums);

		map.put("page", page);
		map.put("certificateTypes", certificateTypes);
		map.put("keywords", keywords);

		return LIST;
	}
	
	/**
	 * 自定look权限，实例。
	 * 描述
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("CertificateType:look")
	@RequestMapping(value="/view/{id}", method={RequestMethod.GET})
	public String view(@PathVariable Long id, Map<String, Object> map) {
		OaCertificateType certificateType = certificateTypeService.get(id);
		map.put("certificateType", certificateType);
        map.put("semesterEnums", SemesterEnum.values());
		return VIEW;
	}
}
