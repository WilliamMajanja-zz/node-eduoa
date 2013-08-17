/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, node.com
 * Filename:		com.node.sample.dao.TaskDAO.java
 * Class:			TaskDAO
 * Date:			2013-4-21
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/

package com.node.eduoa.dao;

import com.node.eduoa.entity.OaClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 班级
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午9:53
 * To change this template use File | Settings | File Templates.
 */
public interface ClassDAO extends JpaRepository<OaClass, Long>, JpaSpecificationExecutor<OaClass> {

    Page<OaClass> findByClassNameContaining(String className, Pageable pageable);

    @SuppressWarnings("JpaQlInspection")
    @Query("select c from OaClass c where c.id in :classIds")
    List<OaClass> findByClassIds(@Param("classIds") List<Long> classIds);

}
