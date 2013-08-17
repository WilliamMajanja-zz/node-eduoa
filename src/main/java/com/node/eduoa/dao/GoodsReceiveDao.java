package com.node.eduoa.dao;

import com.node.eduoa.entity.OaClass;
import com.node.eduoa.entity.OaGoodsReceive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-14
 * Time: 下午9:35
 * To change this template use File | Settings | File Templates.
 */
public interface GoodsReceiveDao extends JpaRepository<OaGoodsReceive, Long>, JpaSpecificationExecutor<OaGoodsReceive> {

    @SuppressWarnings("JpaQlInspection")
    @Query("select c from OaGoodsReceive c where c.goodsApplyId = :goodsApplyId")
    List<OaGoodsReceive> findByGoodsApplyId(@Param("goodsApplyId") Long goodsApplyId);

}
