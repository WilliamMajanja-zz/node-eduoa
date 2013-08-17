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

import com.node.eduoa.entity.OaClass;
import com.node.eduoa.entity.OaClassTeacher;
import com.node.eduoa.entity.OaGrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 班级教师
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午9:53
 * To change this template use File | Settings | File Templates.
 */
public interface ClassTeacherDAO extends JpaRepository<OaClassTeacher, Long>, JpaSpecificationExecutor<OaClassTeacher> {

    @Query("select t from OaClassTeacher t where t.oaTeacherInfoByTeacherId.id = :teacherId order by t.id asc")
    public List<OaClassTeacher> findByTeacherId(@Param("teacherId") Long teacherId);

    @Query("select t from OaClassTeacher t where t.oaClassByClassId.id = :classId order by t.id asc")
    public List<OaClassTeacher> findByClassId(@Param("classId") Long classId);

    @Query("select t from OaClassTeacher t where t.oaTeacherInfoByTeacherId.id = :teacherId and t.headTeacher=:headTeacher order by t.id asc")
    public List<OaClassTeacher> findByTeacherIdAndHead(@Param("teacherId") Long teacherId, @Param("headTeacher") Integer headTeacher);

    @Query("select t from OaClassTeacher t where t.oaClassByClassId.id = :classId and t.headTeacher=:headTeacher order by t.id asc")
    public List<OaClassTeacher> findByClassIdAndHead(@Param("classId") Long classId, @Param("headTeacher") Integer headTeacher);

}
