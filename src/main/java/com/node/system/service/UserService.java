/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, node.com
 * Filename:		com.node.system.service.UserService.java
 * Class:			UserService
 * Date:			2012-8-7
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.service;

import java.util.List;

import com.node.system.entity.main.User;
import com.node.system.exception.ExistedException;
import com.node.system.exception.ServiceException;
import com.node.system.util.dwz.Page;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  1.1.0
 * @since   2012-8-7 下午3:03:59 
 */

public interface UserService {
	
	User get(String username);
	
	List<User> find(Page page, String name);

	void update(User user);

    void updateUser(User user);

	void save(User user) throws ExistedException;

	User get(Long id);

	void delete(Long id) throws ServiceException;

	List<User> findAll(Page page);
}
