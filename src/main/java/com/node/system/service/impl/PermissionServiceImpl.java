/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, node.com
 * Filename:		com.node.system.service.impl.RolePermissionImpl.java
 * Class:			RolePermissionImpl
 * Date:			2013-4-16
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.node.system.dao.PermissionDAO;
import com.node.system.entity.main.Permission;
import com.node.system.service.PermissionService;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  2.0.0
 * @since   2013-4-16 下午2:12:41 
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionDAO permissionDAO;

	/**   
	 * @param permission  
	 * @see com.node.system.service.PermissionService#save(com.node.system.entity.main.Permission)
	 */
	@Override
	public void save(Permission permission) {
		permissionDAO.save(permission);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.node.system.service.PermissionService#get(java.lang.Long)
	 */
	@Override
	public Permission get(Long id) {
		return permissionDAO.findOne(id);
	}

	/**   
	 * @param permission  
	 * @see com.node.system.service.PermissionService#update(com.node.system.entity.main.Permission)
	 */
	@Override
	public void update(Permission permission) {
		permissionDAO.save(permission);
	}

	/**   
	 * @param id  
	 * @see com.node.system.service.PermissionService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		permissionDAO.delete(id);
	}
}
