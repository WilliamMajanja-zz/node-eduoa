/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, node.com
 * Filename:		com.node.system.service.impl.RolePermissionServiceImpl.java
 * Class:			RolePermissionServiceImpl
 * Date:			2013-4-16
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.node.system.dao.RolePermissionDAO;
import com.node.system.entity.main.RolePermission;
import com.node.system.service.RolePermissionService;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  2.0.0
 * @since   2013-4-16 下午2:14:10 
 */
@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {
	
	@Autowired
	private RolePermissionDAO rolePermissionDAO;

	/**   
	 * @param rolePermission  
	 * @see com.node.system.service.RolePermissionService#save(com.node.system.entity.main.RolePermission)
	 */
	@Override
	public void save(RolePermission rolePermission) {
		rolePermissionDAO.save(rolePermission);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.node.system.service.RolePermissionService#get(java.lang.Long)
	 */
	@Override
	public RolePermission get(Long id) {
		return rolePermissionDAO.findOne(id);
	}

	/**   
	 * @param rolePermission  
	 * @see com.node.system.service.RolePermissionService#update(com.node.system.entity.main.RolePermission)
	 */
	@Override
	public void update(RolePermission rolePermission) {
		rolePermissionDAO.save(rolePermission);
	}

	/**   
	 * @param id  
	 * @see com.node.system.service.RolePermissionService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		rolePermissionDAO.delete(id);
	}

	/**   
	 * @param roleId
	 * @return  
	 * @see com.node.system.service.RolePermissionService#findByRoleId(java.lang.Long)
	 */
	@Override
	public List<RolePermission> findByRoleId(Long roleId) {
		return rolePermissionDAO.findByRoleId(roleId);
	}

}
