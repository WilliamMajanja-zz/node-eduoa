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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Validator;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springside.modules.persistence.SearchFilter;

import com.google.common.collect.Lists;
import com.node.eduoa.entity.OaClass;
import com.node.eduoa.entity.OaGrade;
import com.node.eduoa.entity.OaScore;
import com.node.eduoa.entity.OaSubject;
import com.node.eduoa.enums.ExamsEnum;
import com.node.eduoa.service.ClassService;
import com.node.eduoa.service.GradeQueryService;
import com.node.eduoa.service.GradeService;
import com.node.eduoa.service.SubjectService;

/** 
 * 年级比例图查询
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午11:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/gradequery")
public class GradeQueryController extends BaseFormController {

    @Autowired
    private GradeQueryService gradeQueryService;
    
    @Autowired
    private GradeService gradeService;
    
    @Autowired
    private ClassService classService;
    
    @Autowired
    private SubjectService subjectService;
	
	@Autowired
	private Validator validator;
	
	private static final String LIST = "management/eduoa/gradeQuery/findSubject";
	private static final String LIST2 = "management/eduoa/gradeQuery/findSubject2";
	private static final String LIST3 = "management/eduoa/gradeQuery/findSubject3";
	
	@RequiresPermissions("GradeQuery:view")
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Long grade,Long classId, Long subject,Float score1, Float score2, Integer examType, Map<String, Object> map) {
		List<OaGrade> grades = gradeService.findList();
		List<OaClass> classList = classService.findList();
		List<OaSubject> subjects = subjectService.findAll();
		ExamsEnum[] examTypes = ExamsEnum.values();
		Map<String, Object> searchParam = new HashMap<String, Object>();
		if(grade == null){
			if(grades != null && grades.size() > 0){
				grade = grades.get(0).getId();
			}
		}
		searchParam.put(SearchFilter.Operator.EQ + "_gradeId", grade);
		if(classId != null && classId != -1){
			searchParam.put(SearchFilter.Operator.EQ + "_classId", classId);
		}
		if(subject != null && subject != -1){
			searchParam.put(SearchFilter.Operator.EQ + "_subjectId", subject);
		}
		if(score1 != null && score1 != 0){
			searchParam.put(SearchFilter.Operator.GTE + "_score", score1);
		}else{
			score1 = 0F;
		}
		if(score2 != null && score2 != 0){
			searchParam.put(SearchFilter.Operator.LTE + "_score", score2);
		}else{
			score2 = 100F;
		}
		if(examType == null){
			examType = 0;
		}
		searchParam.put(SearchFilter.Operator.EQ + "_examType", examType);
        List<OaScore> result = gradeQueryService.findSearchParameter(searchParam);
        
        Map<String, Object> searchParam2 = new HashMap<String, Object>();
    	searchParam2.put(SearchFilter.Operator.EQ + "_gradeId", grade);
		if(classId != null && classId != -1){
			searchParam2.put(SearchFilter.Operator.EQ + "_classId", classId);
		}
		if(subject != null && subject != -1){
			searchParam2.put(SearchFilter.Operator.EQ + "_subjectId", subject);
		}
		searchParam2.put(SearchFilter.Operator.EQ + "_examType", examType);
        List<OaScore> result2 = gradeQueryService.findSearchParameter(searchParam2);
        
        String str = 
        		"<chart caption='年级比例图' showPercentageInLabel='1' showValues='1' showLabels='1' showLegend='1'>"
        		+"<set value='"+result.size()+"' label='符合' color='429EAD'/>"
    			+"<set value='"+(result2.size()-result.size())+"' label='不符合' color='4249AD'/>"
    			+"</chart>";

        
        map.put("result", str);
        map.put("grades", grades);
        map.put("classList", classList);
        map.put("subjects", subjects);
        map.put("examTypes", examTypes);
        map.put("grade", grade);
        map.put("classId", classId);
        map.put("subject", subject);
        map.put("score1", score1);
        map.put("score2", score2);
        map.put("examType", examType);
        return LIST;
	}
	
	@RequiresPermissions("GradeQuery2:view")
	@RequestMapping(value="/list2", method={RequestMethod.GET, RequestMethod.POST})
	public String list2(Long grade, Long subject,Float score1, Float score2, Integer examType, Map<String, Object> map) {
		Map<String, Object> searchParam = new HashMap<String, Object>();
		List<OaGrade> grades = gradeService.findList();
        List<OaClass> classList = classService.findList();
        List<OaSubject> subjects = subjectService.findAll();
        ExamsEnum[] examTypes = ExamsEnum.values();
		if(grade == null){
			if(grades != null && grades.size() > 0){
				grade = grades.get(0).getId();
			}
		}
		searchParam.put(SearchFilter.Operator.EQ + "_gradeId", grade);
		if(subject != null && subject != -1){
			searchParam.put(SearchFilter.Operator.EQ + "_subjectId", subject);
		}
		if(score1 != null && score1 != 0){
			searchParam.put(SearchFilter.Operator.GTE + "_score", score1);
		}else{
			score1 = 0F;
		}
		if(score2 != null && score2 != 0){
			searchParam.put(SearchFilter.Operator.LTE + "_score", score2);
		}else{
			score2 = 100F;
		}
		if(examType == null){
			examType = 0;
		}
		searchParam.put(SearchFilter.Operator.EQ + "_examType", examType);
        
		List<OaScore> result = gradeQueryService.findSearchParameter(searchParam);
        Map<Long, List<OaScore>> scoreMapList = new HashMap<Long, List<OaScore>>();
        List<OaClass> allClass = classService.findByGradeId(grade);
        for(OaClass o:allClass){
        	scoreMapList.put(o.getId(), new ArrayList());
        }
        for(OaScore score:result){
        	if(scoreMapList.containsKey(score.getClassId())){
        		scoreMapList.get(score.getClassId()).add(score);
        	}
        }
        
        String str1 = "<chart caption='年级比例图' showPercentageInLabel='1' showValues='1' showLabels='1' showLegend='1'>";
		Set set = scoreMapList.keySet();
		String str2 = "";
		for(Object id:set){
			String className = "";
			if(scoreMapList.get(id).size() > 0){
				className = scoreMapList.get(id).get(0).getClassName();
			}else{
				className = classService.get(Long.valueOf(id.toString())).getClassName();
			}
			str2 += "<set value='"+scoreMapList.get(id).size()+"' label='"+className+"' color='42"+id+"EAD'/>";
			
		}
    	String str3 = "</chart>";
    	
        map.put("result", str1+str2+str3);
        map.put("grades", grades);
        map.put("classList", classList);
        map.put("subjects", subjects);
        map.put("examTypes", examTypes);
        map.put("grade", grade);
        map.put("subject", subject);
        map.put("score1", score1);
        map.put("score2", score2);
        map.put("examType", examType);
        return LIST2;
	}
	
	@RequiresPermissions("GradeQuery3:view")
	@RequestMapping(value="/list3", method={RequestMethod.GET, RequestMethod.POST})
	public String list3(Long grade, Long subject,Float score1, Float score2, Integer examType, Map<String, Object> map) {
		Map<String, Object> searchParam = new HashMap<String, Object>();
		List<OaGrade> grades = gradeService.findList();
        List<OaClass> classList = classService.findList();
        List<OaSubject> subjects = subjectService.findAll();
        ExamsEnum[] examTypes = ExamsEnum.values();
		if(grade == null){
			if(grades != null && grades.size() > 0){
				grade = grades.get(0).getId();
			}
		}
		searchParam.put(SearchFilter.Operator.EQ + "_gradeId", grade);
		if(subject != null && subject != -1){
			searchParam.put(SearchFilter.Operator.EQ + "_subjectId", subject);
		}
		if(score1 != null && score1 != 0){
			searchParam.put(SearchFilter.Operator.GTE + "_score", score1);
		}else{
			score1 = 0F;
		}
		if(score2 != null && score2 != 0){
			searchParam.put(SearchFilter.Operator.LTE + "_score", score2);
		}else{
			score2 = 100F;
		}
		if(examType == null){
			examType = 0;
		}
		searchParam.put(SearchFilter.Operator.EQ + "_examType", examType);
        
		List<OaScore> result = gradeQueryService.findSearchParameter(searchParam);
        Map<Long, List<OaScore>> scoreMapList = new HashMap<Long, List<OaScore>>();
        List<OaClass> allClass = classService.findByGradeId(grade);
        for(OaClass o:allClass){
        	scoreMapList.put(o.getId(), new ArrayList());
        }
        for(OaScore score:result){
        	if(scoreMapList.containsKey(score.getClassId())){
        		scoreMapList.get(score.getClassId()).add(score);
        	}
        }
        
        String str1 = "<chart yAxisName='人数' caption='优秀人数' showBorder='1' imageSave='1' exportHandler='http://export.api3.fusioncharts.com'>";
		Set set = scoreMapList.keySet();
		String str2 = "";
		for(Object id:set){
			String className = "";
			if(scoreMapList.get(id).size() > 0){
				className = scoreMapList.get(id).get(0).getClassName();
			}else{
				className = classService.get(Long.valueOf(id.toString())).getClassName();
			}
			str2 += "<set value='"+scoreMapList.get(id).size()+"' label='"+className+"'/>";
			
		}
    	String str3 = "</chart>";
    	
        map.put("result", str1+str2+str3);
        map.put("grades", grades);
        map.put("classList", classList);
        map.put("subjects", subjects);
        map.put("examTypes", examTypes);
        map.put("grade", grade);
        map.put("subject", subject);
        map.put("score1", score1);
        map.put("score2", score2);
        map.put("examType", examType);
        return LIST3;
	}

}
