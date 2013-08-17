package com.node.eduoa.dao;

import com.node.eduoa.entity.OaGoodsApply;
import com.node.eduoa.entity.OaPurchaseApply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-14
 * Time: 下午9:35
 * To change this template use File | Settings | File Templates.
 */
public interface PurchaseApplyDao extends JpaRepository<OaPurchaseApply, Long>, JpaSpecificationExecutor<OaPurchaseApply> {

}
