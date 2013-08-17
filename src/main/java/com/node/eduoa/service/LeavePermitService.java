package com.node.eduoa.service;

import com.node.eduoa.entity.OaLeavePermit;
import com.node.eduoa.entity.OaLeavePermit;
import com.node.system.util.dwz.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-14
 * Time: 下午9:25
 * To change this template use File | Settings | File Templates.
 */
public interface LeavePermitService {

    void save(OaLeavePermit leavePermit);

    void delete(Long id);

    OaLeavePermit get(Long id);

    void update(OaLeavePermit leavePermit);

    List<OaLeavePermit> findByLeavePermitCondition(Page page, OaLeavePermit leavePermit, Map<String, Object> searchParams);

    List<OaLeavePermit> findAll(Page page);

}
