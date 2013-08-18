package com.node.eduoa.service;

import com.node.eduoa.entity.CmsArticle;
import com.node.eduoa.entity.CmsArticle;
import com.node.system.util.dwz.Page;

import java.util.List;
import java.util.Map;

/**
 *
 * User: linfeng
 * Date: 13-8-18
 * Time: 下午1:12
 * To change this template use File | Settings | File Templates.
 */
public interface ArticleService {

    void save(CmsArticle article);

    void delete(Long id);

    CmsArticle get(Long id);

    void update(CmsArticle article);

    List<CmsArticle> find(Page page, String title);

    List<CmsArticle> find(Page page, Long channelId, Map<String, Object> searchParams);

    List<CmsArticle> findAll(Page page);

}
