/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, node.com
 * Filename:		com.node.system.controller.OrganizationController.java
 * Class:			OrganizationController
 * Date:			2012-8-27
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.eduoa.controller;

import com.node.eduoa.entity.OaClass;
import com.node.eduoa.entity.OaGrade;
import com.node.eduoa.enums.ClassTypeEnum;
import com.node.eduoa.service.ClassService;
import com.node.eduoa.service.GradeService;
import com.node.system.log.Log;
import com.node.system.log.LogLevel;
import com.node.system.log.LogMessageObject;
import com.node.system.log.impl.LogUitl;
import com.node.system.service.OrganizationRoleService;
import com.node.system.service.OrganizationService;
import com.node.system.service.RoleService;
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
import org.springside.modules.persistence.SearchFilter;

import javax.validation.Validator;
import java.util.*;

/**
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午11:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/class")
public class ClassController extends BaseFormController {

    @Qualifier("gradeServiceImpl")
    @Autowired
    private GradeService gradeService;
    @Qualifier("classServiceImpl")
    @Autowired
    private ClassService classService;
	
	@Autowired
	private Validator validator;
	
	private static final String CREATE = "management/eduoa/class/create";
	private static final String UPDATE = "management/eduoa/class/update";
	private static final String LIST = "management/eduoa/class/list";
	private static final String TREE = "management/eduoa/class/tree";
	private static final String TREE_LIST = "management/eduoa/class/tree_list";


    @RequiresPermissions("Class:save")
	@RequestMapping(value="/create/{gradeId}", method=RequestMethod.GET)
	public String preCreate(@PathVariable Long gradeId, Map<String, Object> map) {
		map.put("gradeId", gradeId);
		map.put("classTypeEnums", ClassTypeEnum.values());
		return CREATE;
	}
	
	@Log(message="添加了{0}班级。", level = LogLevel.TRACE, override = true)
	@RequiresPermissions("Class:save")
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody String create(OaClass oaClass) {
		BeanValidators.validateWithException(validator, oaClass);

		OaGrade grade = gradeService.get(oaClass.getOaGradeByGradeId().getId());
		if (grade == null) {
			return AjaxObject.newError("添加班级失败：id=" + grade.getId() + "的年级不存在！").toString();
		}
        oaClass.setCreateTime(new Date());
        classService.save(oaClass);
		
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{oaClass.getClassName()}));
		return AjaxObject.newOk("添加班级成功！").toString();
	}
	
	@RequiresPermissions("Class:edit")
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		OaClass oaClass = classService.get(id);
		
		map.put("oaClass", oaClass);
        map.put("classTypeEnums", ClassTypeEnum.values());
		return UPDATE;
	}
	
	@Log(message="修改了{0}班级的信息。")
	@RequiresPermissions("Class:edit")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody String update(OaClass oaClass) {
		BeanValidators.validateWithException(validator, oaClass);

        OaGrade grade = gradeService.get(oaClass.getOaGradeByGradeId().getId());
        if (grade == null) {
            return AjaxObject.newError("修改班级失败：id=" + grade.getId() + "的年级不存在！").toString();
        }
        oaClass.setUpdateTime(new Date());
        oaClass.setOaGradeByGradeId(grade);
        classService.update(oaClass);
		
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{oaClass.getClassName()}));
		return AjaxObject.newOk("修改班级成功！").toString();
	}
	
	@Log(message="删除了{0}班级。")
	@RequiresPermissions("Class:delete")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Long id) {
		OaClass oaClass = classService.get(id);
        if (oaClass.getOaClassTeachersById() != null && !oaClass.getOaClassTeachersById().isEmpty()) {
            return AjaxObject.newError("班级【"+oaClass.getClassName()+"】存在教师带领，不能删除。<br>若需要删除，请先移除任课或班主任教师。").setCallbackType("").toString();
        }
        if (oaClass.getOaStudentClassesById() != null && !oaClass.getOaStudentClassesById().isEmpty()) {
            return AjaxObject.newError("班级【"+oaClass.getClassName()+"】已经存在学生，不能删除。<br>若需要删除，请先移除班级的学生。").setCallbackType("").toString();
        }
        classService.delete(id);
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{oaClass.getClassName()}));
		return AjaxObject.newOk("删除班级成功！").setCallbackType("").toString();
	}
	
	@RequiresPermissions("Class:view")
	@RequestMapping(value="/tree_list", method={RequestMethod.GET, RequestMethod.POST})
	public String tree_list(Map<String, Object> map) {
		return TREE_LIST;
	}
	
	@RequiresPermissions("Class:view")
	@RequestMapping(value="/tree", method={RequestMethod.GET, RequestMethod.POST})
	public String tree(Map<String, Object> map) {
        Calendar calendar = Calendar.getInstance();
        List<OaGrade> grades = gradeService.findAllByYear(calendar.get(Calendar.YEAR));
        OaGrade grade = new OaGrade();
        grade.setGradeName("根年级");
        grade.setId(-1L);
        grade.setChildren(grades);
		map.put("grade", grade);
		return TREE;
	}
	
	@RequiresPermissions("Class:view")
	@RequestMapping(value="/list/{gradeId}", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, @PathVariable Long gradeId, String keywords,
			Map<String, Object> map) {
		List<OaClass> classList = null;
		if (StringUtils.isNotBlank(keywords)) {
            Map<String, Object> searchParam = new HashMap<String, Object>();
            searchParam.put(SearchFilter.Operator.LIKE + "_className", keywords);
            classList = classService.findByGradeId(page, gradeId, searchParam);
		} else {
            classList = classService.findByGradeId(page, gradeId, new HashMap<String, Object>());
		}

		map.put("page", page);
		map.put("classList", classList);
		map.put("keywords", keywords);
		map.put("gradeId", gradeId);

		return LIST;
	}

}
