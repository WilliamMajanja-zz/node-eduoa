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
 
package com.sample.service;

import java.util.List;

import com.node.system.util.dwz.Page;
import com.sample.entity.Task;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  2.0.0
 * @since   2013-4-21 下午7:55:07 
 */

public interface TaskService {
	
	void save(Task task);
	
	void delete(Long id);

	Task get(Long id);

	void update(Task task);
	
	List<Task> find(Page page, String title);

	List<Task> findAll(Page page);
}
