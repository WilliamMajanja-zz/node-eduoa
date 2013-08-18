package com.node.eduoa.dao;

import com.node.eduoa.entity.CmsArticle;
import com.node.eduoa.entity.OaClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * User: linfeng
 * Date: 13-8-18
 * Time: 下午12:56
 * To change this template use File | Settings | File Templates.
 */
public interface ArticleDao extends JpaRepository<CmsArticle, Long>, JpaSpecificationExecutor<CmsArticle> {


    Page<CmsArticle> findByTitleContaining(String title, Pageable pageable);


}
