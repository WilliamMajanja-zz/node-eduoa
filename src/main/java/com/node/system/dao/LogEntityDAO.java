/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, node.com
 * Filename:		com.node.system.dao.LogEntityDao.java
 * Class:			LogEntityDao
 * Date:			2013-5-3
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.node.system.entity.main.LogEntity;
import com.node.system.log.LogLevel;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  2.1.0
 * @since   2013-5-3 下午5:06:37 
 */

public interface LogEntityDAO extends JpaRepository<LogEntity, Long>, JpaSpecificationExecutor<LogEntity>{
	Page<LogEntity> findByLogLevel(LogLevel level, Pageable pageable);
}
