/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, node.com
 * Filename:		com.node.sample.service.TaskService.java
 * Class:			TaskService
 * Date:			2013-4-21
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.eduoa.service;

import com.node.eduoa.entity.OaCertificateType;
import com.node.eduoa.entity.OaGrade;
import com.node.system.util.dwz.Page;

import java.util.List;

/**
 * 年级
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午9:56
 * To change this template use File | Settings | File Templates.
 */
public interface CertificateTypeService {
	
	void save(OaCertificateType certificateType);
	
	void delete(Long id);

    OaCertificateType get(Long id);

	void update(OaCertificateType certificateType);
	
	List<OaCertificateType> find(Page page, String typeName);

	List<OaCertificateType> findAll(Page page);

	List<OaCertificateType> findAll();
}
