package com.node.eduoa.service;

import com.node.eduoa.entity.OaClass;
import com.node.eduoa.entity.OaClassTeacher;
import com.node.system.util.dwz.Page;

import java.util.List;
import java.util.Map;

/**
 * 班级教师
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 下午8:01
 * To change this template use File | Settings | File Templates.
 */
public interface ClassTeacherService {

    void save(OaClassTeacher classTeacher);

    void delete(Long id);

    OaClassTeacher get(Long id);

    void update(OaClassTeacher classTeacher);

    List<OaClassTeacher> findByClassId(Page page, Long classId, Map<String, Object> searchParams);

    List<OaClassTeacher> findByTeacherId(Page page, Long teacherId, Map<String, Object> searchParams);

    List<OaClassTeacher> findAll(Page page);

    List<OaClassTeacher> findByClassIdNoPage(Long classId);

    List<OaClassTeacher> findByTeacherId(Long teacherId);

    List<OaClassTeacher> findByTeacherIdAndHeadTeacher(Long teacherId, Integer headTeacher);
}
