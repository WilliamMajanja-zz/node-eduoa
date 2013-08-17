/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, node.com
 * Filename:		com.node.sample.service.impl.TaskServiceImpl.java
 * Class:			TaskServiceImpl
 * Date:			2013-4-21
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.node.system.util.dwz.Page;
import com.node.system.util.dwz.PageUtils;
import com.sample.dao.TaskDAO;
import com.sample.entity.Task;
import com.sample.service.TaskService;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  2.0.0
 * @since   2013-4-21 下午7:56:11 
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskDAO taskDAO;

	/**   
	 * @param page
	 * @param title
	 * @return  
	 * @see com.sample.service.TaskService#find(com.node.system.util.dwz.Page, java.lang.String)
	 */
	@Override
	public List<Task> find(Page page, String title) {
		org.springframework.data.domain.Page<Task> springDataPage = 
				(org.springframework.data.domain.Page<Task>)taskDAO.findByTitleContaining(title, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**   
	 * @param task  
	 * @see com.sample.service.TaskService#save(com.sample.entity.Task)  
	 */
	@Override
	public void save(Task task) {
		taskDAO.save(task);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.sample.service.TaskService#get(java.lang.Long)  
	 */
	@Override
	public Task get(Long id) {
		return taskDAO.findOne(id);
	}

	/**   
	 * @param task  
	 * @see com.sample.service.TaskService#update(com.sample.entity.Task)  
	 */
	@Override
	public void update(Task task) {
		taskDAO.save(task);
	}

	/**   
	 * @param id  
	 * @see com.sample.service.TaskService#delete(java.lang.Long)  
	 */
	@Override
	public void delete(Long id) {
		taskDAO.delete(id);
	}

	/**   
	 * @param page
	 * @return  
	 * @see com.sample.service.TaskService#findAll(com.node.system.util.dwz.Page)
	 */
	@Override
	public List<Task> findAll(Page page) {
		org.springframework.data.domain.Page<Task> springDataPage = taskDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

}
