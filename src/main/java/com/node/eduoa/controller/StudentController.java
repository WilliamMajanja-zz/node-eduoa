package com.node.eduoa.controller;

import com.node.eduoa.converters.CustomTimestampEditor;
import com.node.eduoa.entity.*;
import com.node.eduoa.enums.*;
import com.node.eduoa.service.ClassService;
import com.node.eduoa.service.GradeService;
import com.node.eduoa.service.StudentService;
import com.node.eduoa.service.impl.ClassServiceImpl;
import com.node.eduoa.service.impl.StudentServiceImpl;
import com.node.eduoa.utils.model.GradeClassModel;
import com.node.eduoa.utils.model.StudentModel;
import com.node.system.entity.main.Organization;
import com.node.system.log.Log;
import com.node.system.log.LogLevel;
import com.node.system.log.LogMessageObject;
import com.node.system.log.impl.LogUitl;
import com.node.system.service.OrganizationService;
import com.node.system.util.dwz.AjaxObject;
import com.node.system.util.dwz.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.persistence.SearchFilter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-13
 * Time: 下午2:22
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/student")
public class StudentController extends BaseFormController {


    @Qualifier("studentServiceImpl")
    @Autowired
    private StudentService studentService;
    @Qualifier("organizationServiceImpl")
    @Autowired
    private OrganizationService organizationService;
    @Qualifier("gradeServiceImpl")
    @Autowired
    private GradeService gradeService;
    @Qualifier("classServiceImpl")
    @Autowired
    private ClassService classService;

    private static final String CREATE = "management/eduoa/student/create";
    private static final String UPDATE = "management/eduoa/student/update";
    private static final String LIST = "management/eduoa/student/list";
    private static final String VIEW = "management/eduoa/student/view";
    private static final String TREE = "management/eduoa/student/treeLookup";
    private static final String TREE_GRADE = "management/eduoa/student/tree_grade";
    private static final String TREE_HEAD_TEACHER = "management/eduoa/student/tree_head_teacher";
    private static final String TEACH_CLASS = "management/eduoa/student/teach_class";
    private static final String GUIDE_CLASS = "management/eduoa/student/guide_class";
    private static final String CLASS_LIST = "management/eduoa/student/class_list";


    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Integer.class, null,
                new CustomNumberEditor(Integer.class, null, true));
        binder.registerCustomEditor(Long.class, null,
                new CustomNumberEditor(Long.class, null, true));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", request.getLocale());
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,
                new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    private Validator validator;

    @RequiresPermissions("StudentInfo:view")
    @RequestMapping(value = "/tree", method = {RequestMethod.GET, RequestMethod.POST})
    public String tree(Map<String, Object> map) {
        Organization organization = organizationService.getTree();

        map.put("organization", organization);
        return TREE;
    }

    @RequiresPermissions("StudentInfo:view")
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

    @RequiresPermissions("StudentInfo:view")
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

    @RequiresPermissions("StudentInfo:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate(Map<String, Object> map) {

        map.put("genderEnum", GenderEnum.values());
        map.put("studentNumber", studentService.getMaxStudentNumber() + 1);

        return CREATE;
    }


    /**
     * LogMessageObject的write用法实例。
     */
    @Log(message = "添加了{0}学生信息。", level = LogLevel.INFO)
    @RequiresPermissions("StudentInfo:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public
    @ResponseBody
    String create(StudentModel studentModel) {

        BeanValidators.validateWithException(validator, studentModel.getStudent());
        Calendar calendar = Calendar.getInstance();

        OaContact contact = studentModel.getContact();
        contact.setCreateTime(calendar.getTime());
        contact.setOaStudentByStudentId(studentModel.getStudent());
        List<OaContact> contacts = Arrays.asList(contact);
        studentModel.getStudent().setOaContactsById(contacts);

        if (studentModel.getClassModel() != null && studentModel.getClassModel().getClassId() != null
                && !"".equals(studentModel.getClassModel().getClassId())) {
            OaClass oaClass = classService.get(studentModel.getClassModel().getClassId());
            OaStudentClass studentClass = new OaStudentClass(oaClass, studentModel.getStudent(),
                    studentModel.getClassModel().getStartTime(), studentModel.getClassModel().getEndTime(),
                    calendar.get(Calendar.YEAR));
            List<OaStudentClass> studentClasses = Arrays.asList(studentClass);
            studentModel.getStudent().setOaStudentClassesById(studentClasses);
            studentModel.getStudent().setClassName(oaClass.getClassName());
            studentModel.getStudent().setCategoryName(oaClass.getCategoryName());
        }

        if (studentModel.getClassModel() != null && studentModel.getClassModel().getGradeId() != null
                && !"".equals(studentModel.getClassModel().getGradeId())) {
            OaGrade grade = gradeService.get(studentModel.getClassModel().getGradeId());
            OaStudentGrade studentGrade = new OaStudentGrade(grade, studentModel.getStudent(),
                    studentModel.getClassModel().getStartTime(), studentModel.getClassModel().getEndTime(),
                    calendar.get(Calendar.YEAR));
            List<OaStudentGrade> studentGrades = Arrays.asList(studentGrade);
            studentModel.getStudent().setOaStudentGradesById(studentGrades);
            studentModel.getStudent().setGradeName(studentModel.getClassModel().getGradeName());
        }
        studentModel.getStudent().setCurrentYear(calendar.get(Calendar.YEAR));
        studentModel.getStudent().setCreateTime(calendar.getTime());
        try {
            studentService.save(studentModel.getStudent());
        } catch (Exception e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        // 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{studentModel.getStudent().getStudentName()}));
        return AjaxObject.newOk("教师信息添加成功！").toString();
    }

    @RequiresPermissions("StudentInfo:edit")
    @RequestMapping(value="/update/{id}", method=RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        Calendar calendar = Calendar.getInstance();
        OaStudent student = studentService.get(id);
        GradeClassModel classModel = new GradeClassModel();
        List<OaStudentGrade> studentGrades = student.getOaStudentGradesById();
        if (studentGrades != null && !studentGrades.isEmpty()) {
            for (OaStudentGrade studentGrade: studentGrades) {
                if (studentGrade.getCurrentYear() == calendar.get(Calendar.YEAR)) {
                    classModel.setGradeId(studentGrade.getOaGradeByGradeId().getId());
                    classModel.setGradeName(studentGrade.getOaGradeByGradeId().getGradeName());
                    classModel.setStartTime(studentGrade.getStartTime());
                    classModel.setEndTime(studentGrade.getEndTime());
                    break;
                }
            }
        }

        List<OaStudentClass> studentClasses = student.getOaStudentClassesById();
        if (studentClasses != null && !studentClasses.isEmpty()) {
            for (OaStudentClass studentClass: studentClasses) {
                if (studentClass.getCurrentYear() == calendar.get(Calendar.YEAR)) {
                    classModel.setClassId(studentClass.getOaClassByClassId().getId());
                    classModel.setClassName(studentClass.getOaClassByClassId().getClassName());
                    break;
                }
            }
        }
        List<OaContact> contacts = student.getOaContactsById();
        if (contacts != null && !contacts.isEmpty()) {
            OaContact contact = contacts.get(0);
            map.put("contact", contact);
        }

        map.put("classModel", classModel);
        map.put("student", student);
        map.put("genderEnum", GenderEnum.values());
        return UPDATE;
    }

    @Log(message="修改了{0}同学的信息。")
    @RequiresPermissions("StudentInfo:edit")
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public
    @ResponseBody
    String update(StudentModel studentModel) {
        BeanValidators.validateWithException(validator, studentModel.getStudent());
        Calendar calendar = Calendar.getInstance();

        OaContact contact = studentModel.getContact();
        contact.setUpdateTime(calendar.getTime());
        contact.setOaStudentByStudentId(studentModel.getStudent());
        List<OaContact> contacts = Arrays.asList(contact);
        studentModel.getStudent().setOaContactsById(contacts);

        if (studentModel.getClassModel() != null && studentModel.getClassModel().getClassId() != null
                && !"".equals(studentModel.getClassModel().getClassId())) {
            OaClass oaClass = classService.get(studentModel.getClassModel().getClassId());
            OaStudentClass studentClass = new OaStudentClass(oaClass, studentModel.getStudent(),
                    studentModel.getClassModel().getStartTime(), studentModel.getClassModel().getEndTime(),
                    calendar.get(Calendar.YEAR));
            List<OaStudentClass> studentClasses = Arrays.asList(studentClass);
            studentModel.getStudent().setOaStudentClassesById(studentClasses);
            studentModel.getStudent().setClassName(oaClass.getClassName());
            studentModel.getStudent().setCategoryName(oaClass.getCategoryName());
        }

        if (studentModel.getClassModel() != null && studentModel.getClassModel().getGradeId() != null
                && !"".equals(studentModel.getClassModel().getGradeId())) {
            OaGrade grade = gradeService.get(studentModel.getClassModel().getGradeId());
            OaStudentGrade studentGrade = new OaStudentGrade(grade, studentModel.getStudent(),
                    studentModel.getClassModel().getStartTime(), studentModel.getClassModel().getEndTime(),
                    calendar.get(Calendar.YEAR));
            List<OaStudentGrade> studentGrades = Arrays.asList(studentGrade);
            studentModel.getStudent().setOaStudentGradesById(studentGrades);
            studentModel.getStudent().setGradeName(studentModel.getClassModel().getGradeName());
        }
        studentModel.getStudent().setCurrentYear(calendar.get(Calendar.YEAR));
        studentModel.getStudent().setUpdateTime(calendar.getTime());
        try {
            studentService.save(studentModel.getStudent());
        } catch (Exception e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{studentModel.getStudent().getStudentName()}));
        return AjaxObject.newOk("修改学生成功！").toString();
    }

    @Log(message="删除了{0}同学的信息。")
    @RequiresPermissions("StudentInfo:delete")
    @RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
    public @ResponseBody String delete(@PathVariable Long id) {
        OaStudent student = studentService.get(id);
        studentService.delete(id);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{student.getStudentName()}));
        return AjaxObject.newOk("删除学生成功！").setCallbackType("").toString();
    }

    @Log(message="删除了{0}同学的信息。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("StudentInfo:delete")
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public @ResponseBody String deleteMany(Long[] ids) {
        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaStudent student = studentService.get(ids[i]);
            studentService.delete(student.getId());
            titles[i] = student.getStudentName();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
        return AjaxObject.newOk("学生信息删除成功！").setCallbackType("").toString();
    }

    @RequiresPermissions("StudentInfo:view")
    @RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
    public String list(Page page, String keywords,
                       Map<String, Object> map) {
        OaStudent student = new OaStudent();
        List<OaStudent> studentList = null;
        if (StringUtils.isNotBlank(keywords)) {
            Map<String, Object> searchParam = new HashMap<String, Object>();
            searchParam.put(SearchFilter.Operator.LIKE + "_studentName", keywords);
            studentList = studentService.findByStudent(page, student, searchParam);
        } else {
            studentList = studentService.findByStudent(page, student, new HashMap<String, Object>());
        }

        map.put("page", page);
        map.put("studentList", studentList);
        map.put("keywords", keywords);

        return LIST;
    }

    /**
     * @param id
     * @param map
     * @return
     */
    @RequiresPermissions("StudentInfo:view")
    @RequestMapping(value="/view/{id}", method={RequestMethod.GET})
    public String view(@PathVariable Long id, Map<String, Object> map) {
        OaStudent student = studentService.get(id);
        map.put("student", student);
        return VIEW;
    }

}
