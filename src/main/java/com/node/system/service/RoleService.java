/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, node.com
 * Filename:		com.node.system.service.RoleService.java
 * Class:			RoleService
 * Date:			2012-8-7
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.service;

import java.util.List;

import com.node.system.entity.main.Role;
import com.node.system.util.dwz.Page;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  1.1.0
 * @since   2012-8-7 下午5:04:05 
 */

public interface RoleService {
	
	List<Role> find(Page page, String name);

	void save(Role role);

	Role get(Long id);

	void update(Role role);

	void delete(Long id);

	List<Role> findAll(Page page);
}
