package com.node.eduoa.service;

import com.node.eduoa.entity.OaPurchaseConduct;
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
public interface PurchaseConductService {

    void save(OaPurchaseConduct purchaseConduct);

    void delete(Long id);

    OaPurchaseConduct get(Long id);

    void update(OaPurchaseConduct purchaseConduct);

    List<OaPurchaseConduct> findByPurchaseConductCondition(Page page, Map<String, Object> searchParams);

    List<OaPurchaseConduct> findAll(Page page);

}
