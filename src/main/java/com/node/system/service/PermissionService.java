/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, node.com
 * Filename:		com.node.system.service.PermissionService.java
 * Class:			PermissionService
 * Date:			2013-4-16
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.service;

import com.node.system.entity.main.Permission;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  2.0.0
 * @since   2013-4-16 下午2:11:41 
 */

public interface PermissionService {
	
	void save(Permission permission);
	
	Permission get(Long id);
	
	void update(Permission permission);
	
	void delete(Long id);
}
