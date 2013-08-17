package com.node.eduoa.dao;

import com.node.eduoa.entity.OaClass;
import com.node.eduoa.entity.OaGrade;
import com.node.eduoa.entity.OaSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 学科x
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 下午3:34
 * To change this template use File | Settings | File Templates.
 */
public interface SubjectDao extends JpaRepository<OaSubject, Long>, JpaSpecificationExecutor<OaSubject> {

    Page<OaSubject> findBySubjectNameContaining(String subjectName, Pageable pageable);

}
