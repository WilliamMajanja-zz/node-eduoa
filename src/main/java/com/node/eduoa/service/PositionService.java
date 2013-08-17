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

import com.node.eduoa.entity.OaPosition;
import com.node.system.util.dwz.Page;
import com.sample.entity.Task;

import java.util.List;

/** 
 * 	
 * @author linfeng
 * Version  2.0.0
 * @since   2013-4-21 下午7:55:07 
 */

public interface PositionService {
	
	void save(OaPosition position);
	
	void delete(Long id);

    OaPosition get(Long id);

	void update(OaPosition position);
	
	List<OaPosition> find(Page page, String positionName);

	List<OaPosition> findAll(Page page);

	List<OaPosition> findAll();
}
