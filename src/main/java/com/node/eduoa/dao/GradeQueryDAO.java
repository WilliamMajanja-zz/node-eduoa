/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, node.com
 * Filename:		com.node.sample.dao.TaskDAO.java
 * Class:			TaskDAO
 * Date:			2013-4-21
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.eduoa.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.node.eduoa.entity.OaGrade;
import com.node.eduoa.entity.OaLeavePermit;
import com.node.eduoa.entity.OaScore;

/**
 * 年级比例图查询
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午9:53
 * To change this template use File | Settings | File Templates.
 */
public interface GradeQueryDAO extends JpaRepository<OaScore, Long>, JpaSpecificationExecutor<OaScore> {
	
//	@Query("select s from OaScore s where s.gradeId = :grade and s.classId = :classId " +
//			"and s.subjectId = :subject and s.score >= :score1 and s.score <= :score2 and s.examType = :examType")
//	List<OaScore> findByGradeClassSubjectScoreExamType(@Param("grade") Long grade,@Param("class") Long classId,@Param("subject") Long subject,@Param("score1") Float score1,@Param("score2") Float score2,@Param("examType") int examType);
//	
//	@Query("select s from OaScore s where s.score >= :score1 and s.score <= :score2 and s.examType = :examType")
//	List<OaScore> findByScoreAndExamType(@Param("score1") Float score1,@Param("score2") Float score2,@Param("examType") int examType);

}
