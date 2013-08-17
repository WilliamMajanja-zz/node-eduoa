package com.node.eduoa.service.impl;

import com.node.eduoa.dao.MultiMediaDAO;
import com.node.eduoa.entity.OaMultimedia;
import com.node.eduoa.service.MultiMediaService;
import com.node.system.util.dwz.Page;
import com.node.system.util.dwz.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.List;
import java.util.Map;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-30
 * Time: 下午9:05
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class MultiMediaServiceImpl implements MultiMediaService {


    @Qualifier("multiMediaDAO")
    @Autowired
    private MultiMediaDAO multiMediaDAO;

    @Override
    public void save(OaMultimedia multimedia) {
        multiMediaDAO.save(multimedia);
    }

    @Override
    public void delete(Long id) {
        multiMediaDAO.delete(id);
    }

    @Override
    public OaMultimedia get(Long id) {
        return multiMediaDAO.findOne(id);
    }

    @Override
    public void update(OaMultimedia multimedia) {
        multiMediaDAO.save(multimedia);
    }

    @Override
    public List<OaMultimedia> findByTitleContaining(String title, Page page) {
        org.springframework.data.domain.Page<OaMultimedia> springDataPage =
                (org.springframework.data.domain.Page<OaMultimedia>) multiMediaDAO.findByTitleContaining(title, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaMultimedia> findByCondition(Page page, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaMultimedia> spec = buildSpecification(searchParams);
        org.springframework.data.domain.Page<OaMultimedia> oaClassPage = multiMediaDAO.findAll(spec, pageRequest);
        page.setTotalPage(oaClassPage.getTotalPages());
        page.setTotalCount(oaClassPage.getTotalElements());
        return oaClassPage.getContent();
    }

    private Specification<OaMultimedia> buildSpecification(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<OaMultimedia> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaMultimedia.class);
        return spec;
    }

    @Override
    public List<OaMultimedia> findAll(Page page) {
        org.springframework.data.domain.Page<OaMultimedia> springDataPage = multiMediaDAO.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
}
