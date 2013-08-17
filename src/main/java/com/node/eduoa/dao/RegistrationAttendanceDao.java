package com.node.eduoa.dao;

import com.node.eduoa.entity.OaRegistrationAttendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-21
 * Time: 下午7:09
 * To change this template use File | Settings | File Templates.
 */
public interface RegistrationAttendanceDao extends JpaRepository<OaRegistrationAttendance, Long>, JpaSpecificationExecutor<OaRegistrationAttendance> {

    Page<OaRegistrationAttendance> findByTeacherNameContaining(String teacherName, Pageable pageable);



}
