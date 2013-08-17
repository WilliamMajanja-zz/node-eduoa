/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, node.com
 * Filename:		com.node.system.service.ModuleService.java
 * Class:			ModuleService
 * Date:			2012-8-6
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.service;

import java.util.List;

import com.node.system.entity.main.Module;
import com.node.system.exception.ExistedException;
import com.node.system.exception.ServiceException;
import com.node.system.util.dwz.Page;


/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  1.1.0
 * @since   2012-8-6 上午9:32:17 
 */

public interface ModuleService {
	void save(Module module) throws ExistedException;
	
	Module get(Long id);
	
	void update(Module module);
	
	void delete(Long id) throws ServiceException;
	
	Module getTree();
	
	List<Module> findAll();
	
	List<Module> find(Long parentId, Page page);
	
	List<Module> find(Long parentId, String name, Page page);
}
