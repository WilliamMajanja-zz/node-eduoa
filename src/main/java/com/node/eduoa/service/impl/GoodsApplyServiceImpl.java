package com.node.eduoa.service.impl;

import com.node.eduoa.dao.GoodsApplyDao;
import com.node.eduoa.entity.OaGoodsApply;
import com.node.eduoa.entity.OaLeavePermit;
import com.node.eduoa.service.GoodsApplyService;
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
 * Date: 13-7-14
 * Time: 下午9:37
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class GoodsApplyServiceImpl implements GoodsApplyService {

    @Qualifier("goodsApplyDao")
    @Autowired
    private GoodsApplyDao goodsApplyDao;

    @Override
    public void save(OaGoodsApply goodsApply) {
        goodsApplyDao.save(goodsApply);
    }

    @Override
    public void delete(Long id) {
        goodsApplyDao.delete(id);
    }

    @Override
    public OaGoodsApply get(Long id) {
        return goodsApplyDao.findOne(id);
    }

    @Override
    public void update(OaGoodsApply goodsApply) {
        goodsApplyDao.save(goodsApply);
    }

    @Override
    public List<OaGoodsApply> findByGoodsApplyCondition(Page page, OaGoodsApply goodsApply, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaGoodsApply> spec = buildSpecification(goodsApply, searchParams);
        org.springframework.data.domain.Page<OaGoodsApply> oaClassPage = goodsApplyDao.findAll(spec, pageRequest);
        page.setTotalPage(oaClassPage.getTotalPages());
        page.setTotalCount(oaClassPage.getTotalElements());
        return oaClassPage.getContent();
    }

    private Specification<OaGoodsApply> buildSpecification(OaGoodsApply goodsApply, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

        Specification<OaGoodsApply> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaGoodsApply.class);
        return spec;
    }

    @Override
    public List<OaGoodsApply> findAll(Page page) {
        org.springframework.data.domain.Page<OaGoodsApply> springDataPage = goodsApplyDao.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
}
