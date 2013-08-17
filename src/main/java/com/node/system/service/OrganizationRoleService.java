/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, node.com
 * Filename:		com.node.system.service.OrganizationRoleService.java
 * Class:			OrganizationRoleService
 * Date:			2013-4-15
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.service;

import java.util.List;

import com.node.system.entity.main.OrganizationRole;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  2.0.0
 * @since   2013-4-15 下午4:14:43 
 */

public interface OrganizationRoleService {
	OrganizationRole get(Long id);
	
	/**
	 * 根据organizationId，找到已分配的角色。
	 * 描述
	 * @param organizationId
	 * @return
	 */
	List<OrganizationRole> find(Long organizationId);

	void save(OrganizationRole organizationRole);

	void delete(Long organizationRoleId);
}
