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
import com.node.system.util.dwz.Page;

import java.util.List;
import java.util.Map;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  2.0.0
 * @since   2013-4-21 下午7:55:07 
 */

public interface ClassService {
	
	void save(OaClass oaClass);
	
	void delete(Long id);

    OaClass get(Long id);

	void update(OaClass oaClass);
	
	List<OaClass> find(Page page, String className);

	List<OaClass> findByGradeId(Page page, Long gradeId, Map<String, Object> searchParams);

	List<OaClass> findAll(Page page);

    List<OaClass> findByClassIds(List<Long> classIds);
}
