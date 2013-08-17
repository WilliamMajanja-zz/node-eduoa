package com.node.eduoa.service.impl;

import com.node.eduoa.dao.GoodsApplyDao;
import com.node.eduoa.dao.GoodsReceiveDao;
import com.node.eduoa.entity.OaGoodsReceive;
import com.node.eduoa.service.GoodsApplyService;
import com.node.eduoa.service.GoodsReceiveService;
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
public class GoodsReceiveServiceImpl implements GoodsReceiveService {


    @Qualifier("goodsReceiveDao")
    @Autowired
    private GoodsReceiveDao goodsReceiveDao;

    @Override
    public void save(OaGoodsReceive goodsReceive) {
        goodsReceiveDao.save(goodsReceive);
    }

    @Override
    public void delete(Long id) {
        goodsReceiveDao.delete(id);
    }

    @Override
    public OaGoodsReceive get(Long id) {
        return goodsReceiveDao.findOne(id);
    }

    @Override
    public void update(OaGoodsReceive goodsReceive) {
        goodsReceiveDao.save(goodsReceive);
    }

    @Override
    public List<OaGoodsReceive> findByGoodsReceiveCondition(Page page, OaGoodsReceive goodsReceive, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaGoodsReceive> spec = buildSpecification(goodsReceive, searchParams);
        org.springframework.data.domain.Page<OaGoodsReceive> oaClassPage = goodsReceiveDao.findAll(spec, pageRequest);
        page.setTotalPage(oaClassPage.getTotalPages());
        page.setTotalCount(oaClassPage.getTotalElements());
        return oaClassPage.getContent();
    }

    private Specification<OaGoodsReceive> buildSpecification(OaGoodsReceive goodsReceive, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);

        Specification<OaGoodsReceive> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaGoodsReceive.class);
        return spec;
    }

    @Override
    public List<OaGoodsReceive> findAll(Page page) {
        org.springframework.data.domain.Page<OaGoodsReceive> springDataPage = goodsReceiveDao.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaGoodsReceive> findByCondition(Long goodsApplyId) {
        return goodsReceiveDao.findByGoodsApplyId(goodsApplyId);
    }
}
