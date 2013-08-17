package com.node.eduoa.service.impl;

import com.node.eduoa.dao.PurchaseConductDao;
import com.node.eduoa.entity.OaPurchaseConduct;
import com.node.eduoa.service.PurchaseConductService;
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
 * User: linfeng at Administrator
 * Date: 13-7-28
 * Time: 下午1:45
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class PurchaseConductServiceImpl implements PurchaseConductService {

    @Qualifier("purchaseConductDao")
    @Autowired
    private PurchaseConductDao purchaseConductDao;

    @Override
    public void save(OaPurchaseConduct purchaseConduct) {
        purchaseConductDao.save(purchaseConduct);
    }

    @Override
    public void delete(Long id) {
        purchaseConductDao.delete(id);
    }

    @Override
    public OaPurchaseConduct get(Long id) {
        return purchaseConductDao.findOne(id);
    }

    @Override
    public void update(OaPurchaseConduct purchaseConduct) {
        purchaseConductDao.save(purchaseConduct);
    }

    @Override
    public List<OaPurchaseConduct> findByPurchaseConductCondition(Page page, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaPurchaseConduct> spec = buildSpecification(searchParams);
        org.springframework.data.domain.Page<OaPurchaseConduct> oaClassPage = purchaseConductDao.findAll(spec, pageRequest);
        page.setTotalPage(oaClassPage.getTotalPages());
        page.setTotalCount(oaClassPage.getTotalElements());
        return oaClassPage.getContent();
    }

    private Specification<OaPurchaseConduct> buildSpecification(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<OaPurchaseConduct> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaPurchaseConduct.class);
        return spec;
    }

    @Override
    public List<OaPurchaseConduct> findAll(Page page) {
        org.springframework.data.domain.Page<OaPurchaseConduct> springDataPage = purchaseConductDao.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
}
