package com.node.eduoa.service;

import com.node.eduoa.entity.OaGoodsApply;
import com.node.eduoa.entity.OaGoodsReceive;
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
public interface GoodsReceiveService {

    void save(OaGoodsReceive goodsReceive);

    void delete(Long id);

    OaGoodsReceive get(Long id);

    void update(OaGoodsReceive goodsReceive);

    List<OaGoodsReceive> findByGoodsReceiveCondition(Page page, OaGoodsReceive goodsReceive, Map<String, Object> searchParams);

    List<OaGoodsReceive> findAll(Page page);

    List<OaGoodsReceive> findByCondition(Long goodsApplyId);

}
