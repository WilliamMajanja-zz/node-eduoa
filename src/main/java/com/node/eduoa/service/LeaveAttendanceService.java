package com.node.eduoa.service;

import com.node.eduoa.entity.OaLeaveAttendance;
import com.node.system.util.dwz.Page;

import java.util.List;
import java.util.Map;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-21
 * Time: 下午7:11
 * To change this template use File | Settings | File Templates.
 */
public interface LeaveAttendanceService {

    void save(OaLeaveAttendance leaveAttendance);

    void delete(Long id);

    OaLeaveAttendance get(Long id);

    void update(OaLeaveAttendance leaveAttendance);

    List<OaLeaveAttendance> find(Page page, String teacherName);

    List<OaLeaveAttendance> findByCondition(Page page, Map<String, Object> searchParams);

    List<OaLeaveAttendance> findAll(Page page);

}
