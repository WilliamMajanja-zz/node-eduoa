/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, node.com
 * Filename:		com.node.system.service.impl.LogEntityServiceImpl.java
 * Class:			LogEntityServiceImpl
 * Date:			2013-5-3
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.node.system.dao.LogEntityDAO;
import com.node.system.entity.main.LogEntity;
import com.node.system.log.LogLevel;
import com.node.system.service.LogEntityService;
import com.node.system.util.dwz.Page;
import com.node.system.util.dwz.PageUtils;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  2.1.0
 * @since   2013-5-3 下午5:08:05 
 */
@Service
@Transactional
public class LogEntityServiceImpl implements LogEntityService {
	
	@Autowired
	private LogEntityDAO logEntityDAO;

	/**   
	 * @param logEntity  
	 * @see com.node.system.service.LogEntityService#save(com.node.system.entity.main.LogEntity)
	 */
	@Override
	public void save(LogEntity logEntity) {
		logEntityDAO.save(logEntity);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.node.system.service.LogEntityService#get(java.lang.Long)
	 */
	@Override
	public LogEntity get(Long id) {
		return logEntityDAO.findOne(id);
	}

	/**   
	 * @param logEntity  
	 * @see com.node.system.service.LogEntityService#update(com.node.system.entity.main.LogEntity)
	 */
	@Override
	public void update(LogEntity logEntity) {
		logEntityDAO.save(logEntity);
	}

	/**   
	 * @param id  
	 * @see com.node.system.service.LogEntityService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		logEntityDAO.delete(id);
	}

	/**
	 * 
	 * @param logLevel
	 * @param page
	 * @return  
	 * @see com.node.system.service.LogEntityService#findByLogLevel(com.node.system.log.LogLevel, com.node.system.util.dwz.Page)
	 */
	@Override
	public List<LogEntity> findByLogLevel(LogLevel logLevel, Page page) {
		org.springframework.data.domain.Page<LogEntity> springDataPage = 
				logEntityDAO.findByLogLevel(logLevel, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**   
	 * @return  
	 * @see com.node.system.service.LogEntityService#findAll()
	 */
	@Override
	public List<LogEntity> findAll() {
		return logEntityDAO.findAll();
	}

	/**
	 * 
	 * @param specification
	 * @param page
	 * @return  
	 * @see com.node.system.service.LogEntityService#findByExample(org.springframework.data.jpa.domain.Specification, com.node.system.util.dwz.Page)
	 */
	@Override
	public List<LogEntity> findByExample(
			Specification<LogEntity> specification, Page page) {
		org.springframework.data.domain.Page<LogEntity> springDataPage = 
				logEntityDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
}
