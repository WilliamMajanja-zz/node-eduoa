package com.node.eduoa.service;

import com.node.eduoa.entity.OaGoodsApply;
import com.node.system.util.dwz.Page;

import java.util.List;
import java.util.Map;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-14
 * Time: 下午9:25
 * To change this template use File | Settings | File Templates.
 */
public interface GoodsApplyService {

    void save(OaGoodsApply goodsApply);

    void delete(Long id);

    OaGoodsApply get(Long id);

    void update(OaGoodsApply goodsApply);

    List<OaGoodsApply> findByGoodsApplyCondition(Page page, OaGoodsApply goodsApply, Map<String, Object> searchParams);

    List<OaGoodsApply> findAll(Page page);

}
