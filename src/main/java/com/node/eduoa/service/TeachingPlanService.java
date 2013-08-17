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

import com.node.eduoa.entity.OaTeachingPlan;
import com.node.system.util.dwz.Page;

import java.util.List;
import java.util.Map;

/**
 * @author linfeng
 *         Version  2.0.0
 * @since 2013-4-21 下午7:55:07
 */

public interface TeachingPlanService {

    void save(OaTeachingPlan teachingPlan);

    void delete(Long id);

    OaTeachingPlan get(Long id);

    void update(OaTeachingPlan teachingPlan);

    List<OaTeachingPlan> findByPlanTitleContaining(String planTitle, Page page);

    List<OaTeachingPlan> findByPlanTitleCondition(Page page, Map<String, Object> searchParams);

    List<OaTeachingPlan> findAll(Page page);
}
