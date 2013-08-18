package com.node.eduoa.service.impl;

import com.node.eduoa.dao.ArticleDao;
import com.node.eduoa.entity.CmsArticle;
import com.node.eduoa.entity.OaStudent;
import com.node.eduoa.entity.OaTeacherInfo;
import com.node.eduoa.entity.SysAttachment;
import com.node.eduoa.service.ArticleService;
import com.node.system.util.dwz.Page;
import com.node.system.util.dwz.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.List;
import java.util.Map;

/**
 *
 * User: linfeng
 * Date: 13-8-18
 * Time: 下午1:13
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Qualifier("articleDao")
    @Autowired
    private ArticleDao articleDao;

    @Override
    public void save(CmsArticle article) {
        articleDao.save(article);
    }

    @Override
    public void delete(Long id) {
        articleDao.delete(id);
    }

    @Override
    public CmsArticle get(Long id) {
        return articleDao.findOne(id);
    }

    @Override
    public void update(CmsArticle article) {
        articleDao.save(article);
    }

    @Override
    public List<CmsArticle> find(Page page, String title) {
        page.setOrderField("updateTime");
        org.springframework.data.domain.Page<CmsArticle> springDataPage =
                (org.springframework.data.domain.Page<CmsArticle>) articleDao.findByTitleContaining(title, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<CmsArticle> find(Page page, Long channelId, Map<String, Object> searchParams) {
        page.setOrderField("updateTime");
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<CmsArticle> spec = buildSpecification(channelId, searchParams);
        org.springframework.data.domain.Page<CmsArticle> oaClassPage = articleDao.findAll(spec, pageRequest);
        page.setTotalPage(oaClassPage.getTotalPages());
        page.setTotalCount(oaClassPage.getTotalElements());
        return oaClassPage.getContent();
    }

    @Override
    public List<CmsArticle> findAll(Page page) {
        return articleDao.findAll(new Sort(Sort.Direction.DESC, "updateTime"));
    }

    private Specification<CmsArticle> buildSpecification(Long channelId, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("channel.id", new SearchFilter("channel.id", SearchFilter.Operator.EQ, channelId));
        Specification<CmsArticle> spec = DynamicSpecifications.bySearchFilter(filters.values(), CmsArticle.class);
        return spec;
    }
}
