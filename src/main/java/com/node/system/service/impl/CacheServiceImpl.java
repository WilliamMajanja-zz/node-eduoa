/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, node.com
 * Filename:		com.node.system.service.impl.ServiceUtil.java
 * Class:			ServiceUtil
 * Date:			2012-9-14
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.node.system.service.CacheService;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  1.1.0
 * @since   2012-9-14 上午9:59:55 
 */
@Service
public class CacheServiceImpl implements CacheService {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * @see com.node.system.service.CacheService#clearAllCache()
	 */
	public void clearAllCache() {
		em.getEntityManagerFactory().getCache().evictAll();
	}

}
