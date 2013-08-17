package com.node.eduoa.service;

import com.node.eduoa.entity.OaRegistrationAttendance;
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
public interface RegistrationAttendanceService {

    void save(OaRegistrationAttendance registrationAttendance);

    void delete(Long id);

    OaRegistrationAttendance get(Long id);

    void update(OaRegistrationAttendance registrationAttendance);

    List<OaRegistrationAttendance> find(Page page, String teacherName);

    List<OaRegistrationAttendance> findByCondition(Page page, Map<String, Object> searchParams);

    List<OaRegistrationAttendance> findByCondition(Page page, Map<String, Object> andSearchParams, Map<String, Object> orSearchParams);

    List<OaRegistrationAttendance> findAll(Page page);

}
