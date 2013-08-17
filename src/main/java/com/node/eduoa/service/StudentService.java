package com.node.eduoa.service;

import com.node.eduoa.entity.OaStudent;
import com.node.system.util.dwz.Page;

import java.util.List;
import java.util.Map;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-13
 * Time: 下午1:48
 * To change this template use File | Settings | File Templates.
 */
public interface StudentService {
    void save(OaStudent student);

    void delete(Long id);

    OaStudent get(Long id);

    void update(OaStudent student);

    List<OaStudent> find(Page page, String studentName);

    List<OaStudent> findByStudent(Page page, OaStudent student, Map<String, Object> searchParams);

    List<OaStudent> findAll(Page page);

    Integer getMaxStudentNumber();

    OaStudent findByStudentNumber(Integer studentNumber);

}
