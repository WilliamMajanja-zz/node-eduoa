/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, node.com
 * Filename:		com.node.sample.service.TaskService.java
 * Class:			TaskService
 * Date:			2013-4-21
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.eduoa.service;

import com.node.eduoa.entity.OaClass;
import com.node.eduoa.entity.OaTeacherInfo;
import com.node.system.entity.main.LogEntity;
import com.node.system.util.dwz.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/** 
 * 	教师信息service
 * @author linfeng
 * Version  2.0.0
 * @since   2013-4-21 下午7:55:07 
 */

public interface TeacherInfoService {
	
	void save(OaTeacherInfo teacherInfo);
	
	void delete(Long id);

    OaTeacherInfo get(Long id);

	void update(OaTeacherInfo teacherInfo);

    List<OaTeacherInfo> findByTeacherNameContaining(String teacherName, Page page);

	List<OaTeacherInfo> findByTeacherInfoCondition(Page page, OaTeacherInfo teacherInfo, Map<String, Object> searchParams);

	List<OaTeacherInfo> findAll(Page page);

    List<OaTeacherInfo> findByExample(Specification<OaTeacherInfo> specification, Page page);
}
