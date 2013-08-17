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

import com.node.eduoa.entity.*;
import com.node.eduoa.enums.*;
import com.node.eduoa.service.*;
import com.node.system.entity.main.Organization;
import com.node.system.entity.main.User;
import com.node.system.log.Log;
import com.node.system.log.LogLevel;
import com.node.system.log.LogMessageObject;
import com.node.system.log.impl.LogUitl;
import com.node.system.service.OrganizationService;
import com.node.system.service.UserService;
import com.node.system.service.impl.UserServiceImpl;
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
 * 教师信息
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/teacher")
public class TeacherInfoController extends BaseFormController {

    @Qualifier("teacherInfoServiceImpl")
    @Autowired
    private TeacherInfoService teacherInfoService;
    @Qualifier("positionServiceImpl")
    @Autowired
    private PositionService positionService;
    @Qualifier("subjectServiceImpl")
    @Autowired
    private SubjectService subjectService;
    @Qualifier("organizationServiceImpl")
    @Autowired
    private OrganizationService organizationService;
    @Qualifier("gradeServiceImpl")
    @Autowired
    private GradeService gradeService;
    @Qualifier("certificateTypeServiceImpl")
    @Autowired
    private CertificateTypeService certificateTypeService;
    @Qualifier("classTeacherServiceImpl")
    @Autowired
    private ClassTeacherService classTeacherService;
    @Qualifier("classServiceImpl")
    @Autowired
    private ClassService classService;
    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private Validator validator;

    private static final String CREATE = "management/eduoa/teacher/create";
    private static final String LINK_CREATE = "management/eduoa/teacher/link_create";
    private static final String UPDATE = "management/eduoa/teacher/update";
    private static final String LIST = "management/eduoa/teacher/list";
    private static final String VIEW = "management/eduoa/teacher/view";
    private static final String TREE = "management/eduoa/teacher/treeLookup";
    private static final String LEADER_TREE = "management/eduoa/teacher/tree_leader";
    private static final String TEACHERS_TREE = "management/eduoa/teacher/tree_teacher";
    private static final String ORGANIZATION_TREE = "management/eduoa/teacher/tree_organization";
    private static final String TREE_GRADE = "management/eduoa/teacher/tree_grade";
    private static final String TREE_HEAD_TEACHER = "management/eduoa/teacher/tree_head_teacher";
    private static final String TEACH_CLASS = "management/eduoa/teacher/teach_class";
    private static final String GUIDE_CLASS = "management/eduoa/teacher/guide_class";
    private static final String CLASS_LIST = "management/eduoa/teacher/class_list";

    @RequiresPermissions("TeacherInfo:view")
    @RequestMapping(value = "/tree", method = {RequestMethod.GET, RequestMethod.POST})
    public String tree(Map<String, Object> map) {
        Organization organization = organizationService.getTree();

        map.put("organization", organization);
        return TREE;
    }

    @RequestMapping(value = "/leader", method = {RequestMethod.GET, RequestMethod.POST})
    public String getLeader(Map<String, Object> map) {
        Organization organization = organizationService.getTree();
        map.put("organization", organization);
        return LEADER_TREE;
    }

    @RequestMapping(value = "/teachers", method = {RequestMethod.GET, RequestMethod.POST})
    public String getTeachers(Map<String, Object> map) {
        Organization organization = organizationService.getTree();
        map.put("organization", organization);
        return TEACHERS_TREE;
    }

    @RequestMapping(value = "/organizationTree", method = {RequestMethod.GET, RequestMethod.POST})
    public String getOrganizationList(Map<String, Object> map) {
        Organization organization = organizationService.getTree();

        map.put("organization", organization);
        return ORGANIZATION_TREE;
    }

    @RequiresPermissions("TeacherInfo:view")
    @RequestMapping(value = "/tree_head_teacher", method = {RequestMethod.GET, RequestMethod.POST})
    public String treeHeadTeacher(Map<String, Object> map) {
        Calendar calendar = Calendar.getInstance();
        List<OaGrade> grades = gradeService.findAllByYear(calendar.get(Calendar.YEAR));
        OaGrade grade = new OaGrade();
        grade.setGradeName("根年级");
        grade.setId(-1L);
        grade.setChildren(grades);
        map.put("grade", grade);
        return TREE_HEAD_TEACHER;
    }

    @RequiresPermissions("TeacherInfo:view")
    @RequestMapping(value="/tree_grade", method={RequestMethod.GET, RequestMethod.POST})
    public String treeGrade(Map<String, Object> map) {
        Calendar calendar = Calendar.getInstance();
        List<OaGrade> grades = gradeService.findAllByYear(calendar.get(Calendar.YEAR));
        OaGrade grade = new OaGrade();
        grade.setGradeName("根年级");
        grade.setId(-1L);
        grade.setChildren(grades);
        map.put("grade", grade);
        return TREE_GRADE;
    }

    @RequiresPermissions("TeacherInfo:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate(Map<String, Object> map) {
        map.put("genderEnum", GenderEnum.values());
        map.put("politicalLandscapeEnum", PoliticalLandscapeEnum.values());
        Calendar calendar = Calendar.getInstance();
        map.put("grades", gradeService.findAllByYear(calendar.get(Calendar.YEAR)));
        map.put("positions", positionService.findAll());
        map.put("subjects", subjectService.findAll());
        map.put("educations", EducationEnum.values());
        map.put("certificateTypes", certificateTypeService.findAll());
        map.put("establishments", EstablishmentEnum.values());
        map.put("isTeachers", TeacherEnum.values());
        map.put("headTeacher", HeadTeacherEnum.values());

        return CREATE;
    }

    @RequiresPermissions("TeacherInfo:save")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
    public String preLinkCreate(@PathVariable Long id, Map<String, Object> map) {

        User user = userService.get(id);

        map.put("genderEnum", GenderEnum.values());
        map.put("politicalLandscapeEnum", PoliticalLandscapeEnum.values());
        Calendar calendar = Calendar.getInstance();
        map.put("grades", gradeService.findAllByYear(calendar.get(Calendar.YEAR)));
        map.put("positions", positionService.findAll());
        map.put("subjects", subjectService.findAll());
        map.put("educations", EducationEnum.values());
        map.put("certificateTypes", certificateTypeService.findAll());
        map.put("establishments", EstablishmentEnum.values());
        map.put("isTeachers", TeacherEnum.values());
        map.put("headTeacher", HeadTeacherEnum.values());
        map.put("user", user);

        return LINK_CREATE;
    }

    /**
     * LogMessageObject的write用法实例。
     */
    @Log(message = "添加了{0}教师信息。", level = LogLevel.TRACE, override = true)
    @RequiresPermissions("TeacherInfo:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public
    @ResponseBody
    String create(OaTeacherInfo teacherInfo) {
        if (teacherInfo.getOaPositionByPositionId() != null
                && teacherInfo.getOaPositionByPositionId().getId() != null) {
            OaPosition position = positionService.get(teacherInfo.getOaPositionByPositionId().getId());
            teacherInfo.setOaPositionByPositionId(position);
        } else {
            teacherInfo.setOaPositionByPositionId(null);
        }
        if (teacherInfo.getSecurityOrganizationByOrgId() != null
                && teacherInfo.getSecurityOrganizationByOrgId().getId() != null) {
            Organization organization = organizationService.get(teacherInfo.getSecurityOrganizationByOrgId().getId());
            teacherInfo.setSecurityOrganizationByOrgId(organization);
        } else {
            teacherInfo.setSecurityOrganizationByOrgId(null);
        }
        if (teacherInfo.getOaSubjectBySubjectId() != null &&
                teacherInfo.getOaSubjectBySubjectId().getId() != null) {
            OaSubject subject = subjectService.get(teacherInfo.getOaSubjectBySubjectId().getId());
            teacherInfo.setOaSubjectBySubjectId(subject);
        } else {
            teacherInfo.setOaSubjectBySubjectId(null);
        }
        if (teacherInfo.getOaGradeByGradeId() != null && teacherInfo.getOaGradeByGradeId().getId() != null) {
            OaGrade grade = gradeService.get(teacherInfo.getOaGradeByGradeId().getId());
            teacherInfo.setOaGradeByGradeId(grade);
        } else {
            teacherInfo.setOaGradeByGradeId(null);
        }

        BeanValidators.validateWithException(validator, teacherInfo);
        try {
            teacherInfoService.save(teacherInfo);
        } catch (Exception e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        // 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{teacherInfo.getTeacherName()}));
        return AjaxObject.newOk("教师信息添加成功！").toString();
    }

    /**
     * LogMessageObject的write用法实例。
     */
    @Log(message = "添加了{0}教师信息。", level = LogLevel.TRACE, override = true)
    @RequiresPermissions("TeacherInfo:save")
    @RequestMapping(value = "/createLink", method = RequestMethod.POST)
    public
    @ResponseBody
    String createLink(OaTeacherInfo teacherInfo) {
        if (teacherInfo.getOaPositionByPositionId() != null
                && teacherInfo.getOaPositionByPositionId().getId() != null) {
            OaPosition position = positionService.get(teacherInfo.getOaPositionByPositionId().getId());
            teacherInfo.setOaPositionByPositionId(position);
        } else {
            teacherInfo.setOaPositionByPositionId(null);
        }
        if (teacherInfo.getSecurityOrganizationByOrgId() != null
                && teacherInfo.getSecurityOrganizationByOrgId().getId() != null) {
            Organization organization = organizationService.get(teacherInfo.getSecurityOrganizationByOrgId().getId());
            teacherInfo.setSecurityOrganizationByOrgId(organization);
        } else {
            teacherInfo.setSecurityOrganizationByOrgId(null);
        }
        if (teacherInfo.getOaSubjectBySubjectId() != null &&
                teacherInfo.getOaSubjectBySubjectId().getId() != null) {
            OaSubject subject = subjectService.get(teacherInfo.getOaSubjectBySubjectId().getId());
            teacherInfo.setOaSubjectBySubjectId(subject);
        } else {
            teacherInfo.setOaSubjectBySubjectId(null);
        }
        if (teacherInfo.getOaGradeByGradeId() != null && teacherInfo.getOaGradeByGradeId().getId() != null) {
            OaGrade grade = gradeService.get(teacherInfo.getOaGradeByGradeId().getId());
            teacherInfo.setOaGradeByGradeId(grade);
        } else {
            teacherInfo.setOaGradeByGradeId(null);
        }
        if (teacherInfo.getUser() != null && teacherInfo.getUser().getId() != null) {
            User user = userService.get(teacherInfo.getUser().getId());
            user.setTeacherInfo(teacherInfo);
            teacherInfo.setUser(user);
        }

        BeanValidators.validateWithException(validator, teacherInfo);
        try {
            teacherInfoService.save(teacherInfo);

//            List<User> userList = teacherInfo.getSecurityUsersById();
//            if (userList != null && !userList.isEmpty()) {
//                for (User user: userList) {
//                    user.setTeacherInfo(teacherInfo);
//                    userService.updateUser(user);
//                }
//            }

        } catch (Exception e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        // 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{teacherInfo.getTeacherName()}));
        return AjaxObject.newOk("教师信息添加成功！").toString();
    }

    /**
     * LogMessageObject的ignore用法实例，ignore不会记录日志。
     */
    @Log(message = "你永远不会看见该日志，LogMessageObject的isWritten为false。", level = LogLevel.TRACE)
    @RequiresPermissions("TeacherInfo:edit")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        OaTeacherInfo teacherInfo = teacherInfoService.get(id);

        map.put("teacherInfo", teacherInfo);
        map.put("genderEnum", GenderEnum.values());
        map.put("politicalLandscapeEnum", PoliticalLandscapeEnum.values());
        Calendar calendar = Calendar.getInstance();
        map.put("grades", gradeService.findAllByYear(calendar.get(Calendar.YEAR)));
        map.put("positions", positionService.findAll());
        map.put("subjects", subjectService.findAll());
        map.put("educations", EducationEnum.values());
        map.put("certificateTypes", certificateTypeService.findAll());
        map.put("establishments", EstablishmentEnum.values());
        map.put("isTeachers", TeacherEnum.values());
        map.put("headTeacher", HeadTeacherEnum.values());
        if (teacherInfo.getUser() != null) {
            map.put("user", teacherInfo.getUser());
        }

        // 加入一个LogMessageObject，该对象的isWritten为false，不会记录日志。
        LogUitl.putArgs(LogMessageObject.newIgnore());
        return UPDATE;
    }

    @Log(message = "修改了{0}教师信息。", level = LogLevel.TRACE, override = true)
    @RequiresPermissions("TeacherInfo:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    String update(OaTeacherInfo teacherInfo) {

        if (teacherInfo.getOaPositionByPositionId() != null
                && teacherInfo.getOaPositionByPositionId().getId() != null) {
            OaPosition position = positionService.get(teacherInfo.getOaPositionByPositionId().getId());
            teacherInfo.setOaPositionByPositionId(position);
        } else {
            teacherInfo.setOaPositionByPositionId(null);
        }
        if (teacherInfo.getSecurityOrganizationByOrgId() != null
                && teacherInfo.getSecurityOrganizationByOrgId().getId() != null) {
            Organization organization = organizationService.get(teacherInfo.getSecurityOrganizationByOrgId().getId());
            teacherInfo.setSecurityOrganizationByOrgId(organization);
        } else {
            teacherInfo.setSecurityOrganizationByOrgId(null);
        }
        if (teacherInfo.getOaSubjectBySubjectId() != null &&
                teacherInfo.getOaSubjectBySubjectId().getId() != null) {
            OaSubject subject = subjectService.get(teacherInfo.getOaSubjectBySubjectId().getId());
            teacherInfo.setOaSubjectBySubjectId(subject);
        } else {
            teacherInfo.setOaSubjectBySubjectId(null);
        }
        if (teacherInfo.getOaGradeByGradeId() != null && teacherInfo.getOaGradeByGradeId().getId() != null) {
            OaGrade grade = gradeService.get(teacherInfo.getOaGradeByGradeId().getId());
            teacherInfo.setOaGradeByGradeId(grade);
        } else {
            teacherInfo.setOaGradeByGradeId(null);
        }

        BeanValidators.validateWithException(validator, teacherInfo);
        teacherInfoService.update(teacherInfo);

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{teacherInfo.getTeacherName()}));
        return AjaxObject.newOk("教师信息修改成功！").toString();
    }


    @Log(message = "删除了{0}教师信息。", level = LogLevel.TRACE, override = true)
    @RequiresPermissions("TeacherInfo:delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    String delete(@PathVariable Long id) {
        OaTeacherInfo teacherInfo = teacherInfoService.get(id);
        teacherInfoService.delete(id);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{teacherInfo.getTeacherName()}));
        return AjaxObject.newOk("教师信息删除成功！").setCallbackType("").toString();
    }


    /**
     * Log的override用法实例
     * 假如override为true，会忽略掉level
     * <p/>
     * 批量删除展示
     */
    @Log(message = "删除了{0}教师信息。", level = LogLevel.TRACE, override = true)
    @RequiresPermissions("TeacherInfo:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteMany(Long[] ids) {
        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaTeacherInfo teacherInfo = teacherInfoService.get(ids[i]);
            teacherInfoService.delete(teacherInfo.getId());

            titles[i] = teacherInfo.getTeacherName();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
        return AjaxObject.newOk("教师信息删除成功！").setCallbackType("").toString();
    }

    @RequiresPermissions("TeacherInfo:view")
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Page page, String keywords, String organizationName, Long subjectId, Long positionId, Map<String, Object> map) {

        OaTeacherInfo teacherInfo = new OaTeacherInfo();

        if (organizationName != null) {
            Organization organization = new Organization();
            organization.setName(organizationName);
            teacherInfo.setSecurityOrganizationByOrgId(organization);
        }
        if (subjectId != null) {
            OaSubject subject = new OaSubject();
            subject.setId(subjectId);
            teacherInfo.setOaSubjectBySubjectId(subject);
        }
        if (positionId != null) {
            OaPosition position = new OaPosition();
            position.setId(positionId);
            teacherInfo.setOaPositionByPositionId(position);
        }

        List<OaTeacherInfo> teacherInfos = null;
        page.setOrderField("createTime");
        if (StringUtils.isNotBlank(keywords)) {
            Map<String, Object> searchParam = new HashMap<String, Object>();
            searchParam.put(SearchFilter.Operator.LIKE + "_teacherName", keywords);
            teacherInfos = teacherInfoService.findByTeacherInfoCondition(page, teacherInfo, searchParam);
        } else {
            teacherInfos = teacherInfoService.findByTeacherInfoCondition(page, teacherInfo, new HashMap<String, Object>());
        }


        map.put("positions", positionService.findAll());
        map.put("subjects", subjectService.findAll());
        map.put("educations", EducationEnum.values());

//        map.put("teacherInfo", teacherInfo);
        map.put("organizationName", organizationName);
        map.put("subjectId", subjectId);
        map.put("positionId", positionId);
        map.put("keywords", keywords);
        map.put("page", page);
        map.put("teacherInfos", teacherInfos);

        return LIST;
    }

    /**
     * @param id
     * @param map
     * @return
     */
    @RequiresPermissions("TeacherInfo:view")
    @RequestMapping(value = "/view/{id}", method = {RequestMethod.GET})
    public String view(@PathVariable Long id, Map<String, Object> map) {
        OaTeacherInfo teacherInfo = teacherInfoService.get(id);
        map.put("teacherInfo", teacherInfo);
        map.put("genderEnum", GenderEnum.values());
        map.put("politicalLandscapeEnum", PoliticalLandscapeEnum.values());
        Calendar calendar = Calendar.getInstance();
        map.put("grades", gradeService.findAllByYear(calendar.get(Calendar.YEAR)));
        map.put("positions", positionService.findAll());
        map.put("subjects", subjectService.findAll());
        map.put("educations", EducationEnum.values());
        map.put("certificateTypes", certificateTypeService.findAll());
        map.put("establishments", EstablishmentEnum.values());
        map.put("isTeachers", TeacherEnum.values());
        map.put("headTeacher", HeadTeacherEnum.values());
        return VIEW;
    }

    @RequiresPermissions("TeacherInfo:teachClass")
    @RequestMapping(value = "/teachClass/{id}", method = {RequestMethod.GET})
    public String teachClass(@PathVariable Long id, Map<String, Object> map) {

        OaTeacherInfo teacherInfo = teacherInfoService.get(id);
        map.put("teacherInfo", teacherInfo);

        return TEACH_CLASS;
    }

    @RequiresPermissions("TeacherInfo:guideClass")
    @RequestMapping(value = "/guideClass/{id}", method = {RequestMethod.GET})
    public String guideClass(@PathVariable Long id, Map<String, Object> map) {

        OaTeacherInfo teacherInfo = teacherInfoService.get(id);
        map.put("teacherInfo", teacherInfo);

        List<OaClassTeacher> classTeachers = teacherInfo.getOaClassTeachersById();
        if (classTeachers != null && !classTeachers.isEmpty()) {
            for (OaClassTeacher classTeacher: classTeachers) {
                if (classTeacher.getHeadTeacher() == 1) {
                    map.put("classTeacher", classTeacher);
                    break;
                }
            }
        }

        return GUIDE_CLASS;
    }

    @Log(message = "给{0}添加了带领班级【{1}】。", level = LogLevel.INFO)
    @RequiresPermissions("TeacherInfo:guideClass")
    @RequestMapping(value = "/createGuideClass", method = RequestMethod.POST)
    public
    @ResponseBody
    String saveGuideClass(OaClassTeacher classTeacher){
        classTeacher.setHeadTeacher(1);
        classTeacherService.save(classTeacher);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{classTeacher.getOaTeacherInfoByTeacherId().getTeacherName(),
                classTeacher.getOaClassByClassId().getClassName()}));
        return AjaxObject.newOk("带领班级添加成功！").toString();
    }

    @Log(message = "给{0}添加了任课班级【{1}】。", level = LogLevel.INFO)
    @RequiresPermissions("TeacherInfo:teachClass")
    @RequestMapping(value = "/createTeachClass", method = RequestMethod.POST)
    public
    @ResponseBody
    String saveTeachClass(OaClassTeacher classTeacher){
        classTeacher.setHeadTeacher(0);
        classTeacherService.save(classTeacher);

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{classTeacher.getOaTeacherInfoByTeacherId().getTeacherName(),
                classTeacher.getOaClassByClassId().getClassName()}));
        return AjaxObject.newOk("任课班级添加成功！").setCallbackType("").toString();
    }

    @RequiresPermissions("TeacherInfo:view")
    @RequestMapping(value="/classList/{id}", method={RequestMethod.GET, RequestMethod.POST})
    public String classList(@PathVariable Long id, Map<String, Object> map) {
        List<OaClassTeacher> classTeacherList = classTeacherService.findByTeacherIdAndHeadTeacher(id, 0);
        map.put("classTeacherList", classTeacherList);
        OaTeacherInfo teacherInfo = teacherInfoService.get(id);
        map.put("teacherInfo", teacherInfo);

        return CLASS_LIST;
    }


    @Log(message = "删除了{0}的任课班级【{1}】。", level = LogLevel.INFO)
    @RequiresPermissions("TeacherInfo:delete")
    @RequestMapping(value = "/deleteClassTeacher/{id}", method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteClassTeacher(@PathVariable Long id) {
        OaClassTeacher classTeacher = classTeacherService.get(id);
        classTeacherService.delete(id);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{classTeacher.getOaTeacherInfoByTeacherId().getTeacherName(),
                classTeacher.getOaClassByClassId().getClassName()}));
        return AjaxObject.newOk("任课班级删除成功！").setCallbackType("").toString();
    }

}
