/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, node.com
 * Filename:		com.node.system.dao.PermissionDao.java
 * Class:			PermissionDao
 * Date:			2013-4-16
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.node.system.entity.main.Permission;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  2.0.0
 * @since   2013-4-16 下午2:10:16 
 */

public interface PermissionDAO extends JpaRepository<Permission, Long> {

}
