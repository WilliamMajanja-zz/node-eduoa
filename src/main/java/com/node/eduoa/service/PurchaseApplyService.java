package com.node.eduoa.service;

import com.node.eduoa.entity.OaPurchaseApply;
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
public interface PurchaseApplyService {

    void save(OaPurchaseApply purchaseApply);

    void delete(Long id);

    OaPurchaseApply get(Long id);

    void update(OaPurchaseApply purchaseApply);

    List<OaPurchaseApply> findByPurchaseApplyCondition(Page page, Map<String, Object> searchParams);

    List<OaPurchaseApply> findAll(Page page);

}
