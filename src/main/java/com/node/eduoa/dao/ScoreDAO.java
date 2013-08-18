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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.node.eduoa.entity.OaScore;

/**
 * 分数
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午9:53
 * To change this template use File | Settings | File Templates.
 */
public interface ScoreDAO extends JpaRepository<OaScore, Long> {

	Page<OaScore> findByStudentNameContaining(String studentName, Pageable pageable);
	
	@Query("select s from OaScore s where s.gradeId = :grade " +
			"and s.subjectId = :subject and s.examType = :examType")
	List<OaScore> findBySubjectName(@Param("grade") Long grade,
			@Param("subject") Long subject,@Param("examType") int examType);
	@Query("select s from OaScore s where s.gradeId = :grade " +"and s.examType = :examType")
	List<OaScore> findByGread(@Param("grade") Long grade,@Param("examType") int examType);
}
