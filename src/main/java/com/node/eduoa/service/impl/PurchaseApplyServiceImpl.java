package com.node.eduoa.service.impl;

import com.node.eduoa.dao.PurchaseApplyDao;
import com.node.eduoa.entity.OaPurchaseApply;
import com.node.eduoa.service.PurchaseApplyService;
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
 * Date: 13-7-27
 * Time: 下午5:27
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class PurchaseApplyServiceImpl implements PurchaseApplyService {

    @Qualifier("purchaseApplyDao")
    @Autowired
    private PurchaseApplyDao purchaseApplyDao;

    @Override
    public void save(OaPurchaseApply purchaseApply) {
        purchaseApplyDao.save(purchaseApply);
    }

    @Override
    public void delete(Long id) {
        purchaseApplyDao.delete(id);
    }

    @Override
    public OaPurchaseApply get(Long id) {
        return purchaseApplyDao.findOne(id);
    }

    @Override
    public void update(OaPurchaseApply purchaseApply) {
        purchaseApplyDao.save(purchaseApply);
    }

    @Override
    public List<OaPurchaseApply> findByPurchaseApplyCondition(Page page, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaPurchaseApply> spec = buildSpecification(searchParams);
        org.springframework.data.domain.Page<OaPurchaseApply> oaClassPage = purchaseApplyDao.findAll(spec, pageRequest);
        page.setTotalPage(oaClassPage.getTotalPages());
        page.setTotalCount(oaClassPage.getTotalElements());
        return oaClassPage.getContent();
    }

    private Specification<OaPurchaseApply> buildSpecification(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<OaPurchaseApply> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaPurchaseApply.class);
        return spec;
    }

    @Override
    public List<OaPurchaseApply> findAll(Page page) {
        org.springframework.data.domain.Page<OaPurchaseApply> springDataPage = purchaseApplyDao.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
}
