package com.node.eduoa.dao;

import com.node.eduoa.entity.OaLeavePermit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * User: linfeng at Administrator
 * Date: 13-7-14
 * Time: 下午9:23
 * To change this template use File | Settings | File Templates.
 */
public interface LeavePermitDao extends JpaRepository<OaLeavePermit, Long>, JpaSpecificationExecutor<OaLeavePermit> {

    Page<OaLeavePermit> findByReasonContaining(String reason, Pageable pageable);

}
