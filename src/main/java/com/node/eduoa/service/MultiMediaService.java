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

import com.node.eduoa.entity.OaMultimedia;
import com.node.system.util.dwz.Page;

import java.util.List;
import java.util.Map;

/**
 * @author linfeng
 *         Version  2.0.0
 * @since 2013-4-21 下午7:55:07
 */

public interface MultiMediaService {

    void save(OaMultimedia multimedia);

    void delete(Long id);

    OaMultimedia get(Long id);

    void update(OaMultimedia multimedia);

    List<OaMultimedia> findByTitleContaining(String title, Page page);

    List<OaMultimedia> findByCondition(Page page, Map<String, Object> searchParams);

    List<OaMultimedia> findAll(Page page);
}
