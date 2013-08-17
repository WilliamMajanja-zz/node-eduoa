package com.node.eduoa.dao;

import com.node.eduoa.entity.OaClass;
import com.node.eduoa.entity.OaStudent;
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
 * Date: 13-7-13
 * Time: 下午1:46
 * To change this template use File | Settings | File Templates.
 */
public interface StudentDao extends JpaRepository<OaStudent, Long>, JpaSpecificationExecutor<OaStudent> {

    Page<OaStudent> findByStudentNameContaining(String studentName, Pageable pageable);

    @Query("select s from OaStudent s where s.currentYear=:currentYear order by s.studentNumber desc")
    List<OaStudent> getMaxStudentNumber(@Param("currentYear") Integer currentYear);

    @Query("select s from OaStudent s where s.studentNumber=:studentNumber")
    OaStudent findByStudentNumber(@Param("studentNumber") Integer studentNumber);

}
