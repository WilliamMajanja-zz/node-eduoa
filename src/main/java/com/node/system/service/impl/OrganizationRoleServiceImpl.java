/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, node.com
 * Filename:		com.node.system.service.impl.OrganizationRoleServiceImpl.java
 * Class:			OrganizationRoleServiceImpl
 * Date:			2013-4-15
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

import com.node.system.dao.OrganizationRoleDAO;
import com.node.system.entity.main.OrganizationRole;
import com.node.system.service.OrganizationRoleService;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  2.0.0
 * @since   2013-4-15 下午4:16:04 
 */
@Service
@Transactional
public class OrganizationRoleServiceImpl implements OrganizationRoleService {
	
	private OrganizationRoleDAO organizationRoleDAO;
	
	/**
	 * 
	 * 构造函数
	 * @param organizationRoleDAO
	 */
	@Autowired
	public OrganizationRoleServiceImpl(OrganizationRoleDAO organizationRoleDAO) {
		this.organizationRoleDAO = organizationRoleDAO;
	}
	
	/**   
	 * @param id
	 * @return  
	 * @see com.node.system.service.OrganizationRoleService#get(java.lang.Long)
	 */
	@Override
	public OrganizationRole get(Long id) {
		return organizationRoleDAO.findOne(id);
	}

	/**   
	 * @param organizationId
	 * @return  
	 * @see com.node.system.service.OrganizationRoleService#find(java.lang.Long)
	 */
	@Override
	public List<OrganizationRole> find(Long organizationId) {
		return organizationRoleDAO.findByOrganizationId(organizationId);
	}

	/**   
	 * @param organizationRole  
	 * @see com.node.system.service.OrganizationRoleService#save(com.node.system.entity.main.OrganizationRole)
	 */
	@Override
	public void save(OrganizationRole organizationRole) {
		organizationRoleDAO.save(organizationRole);
	}

	/**   
	 * @param organizationRoleId  
	 * @see com.node.system.service.OrganizationRoleService#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long organizationRoleId) {
		organizationRoleDAO.delete(organizationRoleId);
	}

}
