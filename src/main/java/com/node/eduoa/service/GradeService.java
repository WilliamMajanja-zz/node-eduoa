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

import com.node.eduoa.entity.OaGrade;
import com.node.eduoa.entity.OaPosition;
import com.node.system.util.dwz.Page;

import java.util.List;

/**
 * 年级
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午9:56
 * To change this template use File | Settings | File Templates.
 */
public interface GradeService {
	
	void save(OaGrade grade);
	
	void delete(Long id);

    OaGrade get(Long id);

	void update(OaGrade grade);
	
	List<OaGrade> find(Page page, String gradeName);

	List<OaGrade> findAll(Page page);

    List<OaGrade> findAllByYear(Integer currentYear);
}
