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

import com.node.eduoa.utils.YearUtils;
import com.node.eduoa.converters.CustomTimestampEditor;
import com.node.eduoa.entity.OaGrade;
import com.node.eduoa.enums.SemesterEnum;
import com.node.eduoa.service.GradeService;
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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springside.modules.beanvalidator.BeanValidators;

import javax.validation.Validator;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * 年级管理
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午11:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/grade")
public class GradeController extends BaseFormController {

    @Qualifier("gradeServiceImpl")
    @Autowired
    private GradeService gradeService;

	
	@Autowired
	private Validator validator;
	
	private static final String CREATE = "management/eduoa/grade/create";
	private static final String UPDATE = "management/eduoa/grade/update";
	private static final String LIST = "management/eduoa/grade/list";
	private static final String VIEW = "management/eduoa/grade/view";


    @RequiresPermissions("Grade:save")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String preCreate(Map<String, Object> map) {
        map.put("semesterEnums", SemesterEnum.values());
        map.put("years", YearUtils.getYears(3));
		return CREATE;
	}
	
	/**
	 * LogMessageObject的write用法实例。
	 */
    @Log(message="添加了{0}年级。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("Grade:save")
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody String create(OaGrade grade) {
		BeanValidators.validateWithException(validator, grade);
        try {
            grade.setCreateTime(new Date());
            gradeService.save(grade);
        } catch (Exception e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }
		
		// 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{grade.getGradeName()}));
		return AjaxObject.newOk("年级添加成功！").toString();
	}
	
	/**
	 * LogMessageObject的ignore用法实例，ignore不会记录日志。
	 */
	@Log(message="你永远不会看见该日志，LogMessageObject的isWritten为false。", level=LogLevel.TRACE)
	@RequiresPermissions("Grade:edit")
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		OaGrade grade = gradeService.get(id);
		
		map.put("grade", grade);
        map.put("years", YearUtils.getYears(3));
        map.put("semesterEnums", SemesterEnum.values());
		
		// 加入一个LogMessageObject，该对象的isWritten为false，不会记录日志。
		LogUitl.putArgs(LogMessageObject.newIgnore());
		return UPDATE;
	}

    @Log(message="修改了{0}年级。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("Grade:edit")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody String update(OaGrade grade) {
		BeanValidators.validateWithException(validator, grade);
		gradeService.update(grade);

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{grade.getGradeName()}));
		return AjaxObject.newOk("年级修改成功！").toString();
	}


    @Log(message="删除了{0}年级。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("Grade:delete")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Long id) {
        OaGrade grade = gradeService.get(id);
        if (grade.getOaClassesById() != null && !grade.getOaClassesById().isEmpty()) {
            return AjaxObject.newError("年级删除失败：【"+grade.getGradeName()+"】下有班级！").setCallbackType("").toString();
        }
        if (grade.getOaTeacherInfosById() != null && !grade.getOaTeacherInfosById().isEmpty()) {
            return AjaxObject.newError("年级删除失败：【"+grade.getGradeName()+"】下有属于此的教师！").setCallbackType("").toString();
        }
        if (grade.getOaStudentGradesById() != null && !grade.getOaStudentGradesById().isEmpty()) {
            return AjaxObject.newError("年级删除失败：【"+grade.getGradeName()+"】下有学生！").setCallbackType("").toString();
        }
		gradeService.delete(id);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{grade.getGradeName()}));
		return AjaxObject.newOk("年级删除成功！").setCallbackType("").toString();
	}
	
	
	/**
	 * Log的override用法实例
	 * 假如override为true，会忽略掉level
	 * 
	 * 批量删除展示
	 */
	@Log(message="删除了{0}年级。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("Grade:delete")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody String deleteMany(Long[] ids) {
		String[] titles = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			OaGrade grade = gradeService.get(ids[i]);
            if (grade.getOaClassesById() != null && !grade.getOaClassesById().isEmpty()) {
                return AjaxObject.newError("年级删除失败：【"+grade.getGradeName()+"】下面有班级！").setCallbackType("").toString();
            }
			gradeService.delete(grade.getId());
			titles[i] = grade.getGradeName();
		}
		
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
		return AjaxObject.newOk("年级删除成功！").setCallbackType("").toString();
	}

	@RequiresPermissions("Grade:view")
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, String keywords, Map<String, Object> map) {
		List<OaGrade> grades = null;
		if (StringUtils.isNotBlank(keywords)) {
			grades = gradeService.find(page, keywords);
		} else {
			grades = gradeService.findAll(page);
		}
        SemesterEnum[] semesterEnums = SemesterEnum.values();
        map.put("semesterEnums", semesterEnums);

		map.put("page", page);
		map.put("grades", grades);
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
	@RequiresPermissions("Grade:look")
	@RequestMapping(value="/view/{id}", method={RequestMethod.GET})
	public String view(@PathVariable Long id, Map<String, Object> map) {
		OaGrade grade = gradeService.get(id);
		map.put("grade", grade);
        map.put("semesterEnums", SemesterEnum.values());
		return VIEW;
	}
}
