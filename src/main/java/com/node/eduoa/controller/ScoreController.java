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

import com.node.eduoa.converters.CustomTimestampEditor;
import com.node.eduoa.entity.*;
import com.node.eduoa.entity.OaScore;
import com.node.eduoa.enums.ExamsEnum;
import com.node.eduoa.enums.SemesterEnum;
import com.node.eduoa.service.GradeService;
import com.node.eduoa.service.ScoreService;
import com.node.eduoa.service.StudentService;
import com.node.eduoa.service.SubjectService;
import com.node.eduoa.service.impl.ScoreServiceImpl;
import com.node.eduoa.service.impl.StudentServiceImpl;
import com.node.eduoa.utils.YearUtils;
import com.node.eduoa.utils.model.ScoreModel;
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
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.persistence.SearchFilter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Validator;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * 分数管理
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午11:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/score")
public class ScoreController extends BaseFormController {

    @Qualifier("scoreServiceImpl")
    @Autowired
    private ScoreService scoreService;
    @Qualifier("gradeServiceImpl")
    @Autowired
    private GradeService gradeService;
    @Qualifier("subjectServiceImpl")
    @Autowired
    private SubjectService subjectService;
    @Qualifier("studentServiceImpl")
    @Autowired
    private StudentService studentService;
	
	@Autowired
	private Validator validator;
	
	private static final String CREATE = "management/eduoa/score/create";
	private static final String UPDATE = "management/eduoa/score/update";
	private static final String LIST = "management/eduoa/score/list";
	private static final String VIEW = "management/eduoa/score/view";

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", request.getLocale());
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,
                new CustomDateEditor(dateFormat, true));
    }

    @RequiresPermissions("Score:save")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String preCreate(Map<String, Object> map) {
        map.put("semesterEnums", SemesterEnum.values());
        map.put("examsEnums", ExamsEnum.values());
        map.put("years", YearUtils.getYears(3));
        Calendar calendar = Calendar.getInstance();
        map.put("grades", gradeService.findAllByYear(calendar.get(Calendar.YEAR)));
        map.put("subjects", subjectService.findAll());
		return CREATE;
	}
	
	/**
	 * LogMessageObject的write用法实例。
	 */
    @Log(message="添加了{0}分数。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("Score:save")
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody String create(ScoreModel scoreModel) {

        try {
            scoreModel.getScore().setCreateTime(new Date());

            scoreModel.getScore().setClassId(scoreModel.getClassModel().getClassId());
            scoreModel.getScore().setClassName(scoreModel.getClassModel().getClassName());
            Long gradeId = scoreModel.getScore().getGradeId();
            OaGrade grade = null;
            if (gradeId != null) {
                grade = gradeService.get(gradeId);
                scoreModel.getScore().setGradeName(grade.getGradeName());
            }
            Long subjectId = scoreModel.getScore().getSubjectId();
            OaSubject subject = null;
            if (subjectId != null) {
                subject = subjectService.get(subjectId);
                scoreModel.getScore().setSubjectName(subject.getSubjectName());
            }
            Date examDate = scoreModel.getScore().getExamDate();
            if (examDate != null) {
                scoreModel.getScore().setExamDateTime(examDate.getTime());
            }
            scoreModel.getScore().setTeacherId(currentUser.getUser().getTeacherInfo().getId());
            scoreModel.getScore().setTeacherName(currentUser.getUser().getTeacherInfo().getTeacherName());

            scoreService.save(scoreModel.getScore());
        } catch (Exception e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }
		
		// 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{scoreModel.getScore().getStudentName()}));
		return AjaxObject.newOk("分数添加成功！").setCallbackType("").toString();
	}
	
	/**
	 * LogMessageObject的ignore用法实例，ignore不会记录日志。
	 */
	@Log(message="你永远不会看见该日志，LogMessageObject的isWritten为false。", level=LogLevel.TRACE)
	@RequiresPermissions("Score:edit")
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		OaScore score = scoreService.get(id);
		
		map.put("score", score);
        map.put("years", YearUtils.getYears(3));
        map.put("semesterEnums", SemesterEnum.values());
		
		// 加入一个LogMessageObject，该对象的isWritten为false，不会记录日志。
		LogUitl.putArgs(LogMessageObject.newIgnore());
		return UPDATE;
	}

    @Log(message="修改了{0}分数。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("Score:edit")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody String update(ScoreModel scoreModel) {
        try {
            scoreModel.getScore().setCreateTime(new Date());

            scoreModel.getScore().setClassId(scoreModel.getClassModel().getClassId());
            scoreModel.getScore().setClassName(scoreModel.getClassModel().getClassName());
            Long gradeId = scoreModel.getScore().getGradeId();
            OaGrade grade = null;
            if (gradeId != null) {
                grade = gradeService.get(gradeId);
                scoreModel.getScore().setGradeName(grade.getGradeName());
            }
            Long subjectId = scoreModel.getScore().getSubjectId();
            OaSubject subject = null;
            if (subjectId != null) {
                subject = subjectService.get(subjectId);
                scoreModel.getScore().setSubjectName(subject.getSubjectName());
            }
            Date examDate = scoreModel.getScore().getExamDate();
            if (examDate != null) {
                scoreModel.getScore().setExamDateTime(examDate.getTime());
            }
            scoreModel.getScore().setTeacherId(currentUser.getUser().getTeacherInfo().getId());
            scoreModel.getScore().setTeacherName(currentUser.getUser().getTeacherInfo().getTeacherName());

            scoreService.save(scoreModel.getScore());
        } catch (Exception e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        // 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{scoreModel.getScore().getStudentName()}));
		return AjaxObject.newOk("分数修改成功！").setCallbackType("").toString();
	}

    @RequiresPermissions("Score:edit")
    @RequestMapping(value="/student/{studentNumber}", method=RequestMethod.GET)
    public @ResponseBody String getStudent(@PathVariable Integer studentNumber) {

        Calendar calendar = Calendar.getInstance();
        OaStudent student = studentService.findByStudentNumber(studentNumber);
        if (student != null) {
            List<OaStudentGrade> studentGrades = student.getOaStudentGradesById();
            if (studentGrades != null && !studentGrades.isEmpty()) {
                for (OaStudentGrade studentGrade : studentGrades) {
                    if (calendar.get(Calendar.YEAR) == studentGrade.getCurrentYear()) {
                        student.setGradeName(studentGrade.getOaGradeByGradeId().getGradeName());
                        student.setGradeId(studentGrade.getOaGradeByGradeId().getId());
                        break;
                    }
                }
            }
            List<OaStudentClass> studentClasses = student.getOaStudentClassesById();
            if (studentClasses != null && !studentClasses.isEmpty()) {
                for (OaStudentClass studentClass:studentClasses) {
                    if (calendar.get(Calendar.YEAR) == studentClass.getCurrentYear()) {
                        student.setClassId(studentClass.getOaClassByClassId().getId());
                        student.setClassName(studentClass.getOaClassByClassId().getClassName());
                        break;
                    }
                }
            }
        } else {
            return AjaxObject.newError("不存在该学生").toString();
        }

        return AjaxObject.newOk("获得学生成功！").setValue(student).toString();
    }


    @Log(message="删除了{0}分数。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("Score:delete")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Long id) {
        OaScore score = scoreService.get(id);
		scoreService.delete(id);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{score.getGradeName()}));
		return AjaxObject.newOk("分数删除成功！").setCallbackType("").toString();
	}
	
	
	/**
	 * Log的override用法实例
	 * 假如override为true，会忽略掉level
	 * 
	 * 批量删除展示
	 */
	@Log(message="删除了{0}分数。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("Score:delete")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody String deleteMany(Long[] ids) {
		String[] titles = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			OaScore score = scoreService.get(ids[i]);
			scoreService.delete(score.getId());
			titles[i] = score.getGradeName();
		}
		
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
		return AjaxObject.newOk("分数删除成功！").setCallbackType("").toString();
	}

	@RequiresPermissions("Score:view")
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, String keywords, Map<String, Object> map) {
		List<OaScore> scores = null;
		if (StringUtils.isNotBlank(keywords)) {
			scores = scoreService.find(page, keywords);
		} else {
			scores = scoreService.findAll(page);
		}

        map.put("examsEnums", ExamsEnum.values());

		map.put("page", page);
		map.put("scores", scores);
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
	@RequiresPermissions("Score:look")
	@RequestMapping(value="/view/{id}", method={RequestMethod.GET})
	public String view(@PathVariable Long id, Map<String, Object> map) {
		OaScore score = scoreService.get(id);
		map.put("score", score);
        map.put("semesterEnums", SemesterEnum.values());
		return VIEW;
	}
}
