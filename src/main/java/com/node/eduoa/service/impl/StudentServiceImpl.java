package com.node.eduoa.service.impl;

import com.node.eduoa.dao.StudentDao;
import com.node.eduoa.entity.OaStudent;
import com.node.eduoa.service.StudentService;
import com.node.system.util.dwz.Page;
import com.node.system.util.dwz.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * User: linfeng at Administrator
 * Date: 13-7-13
 * Time: 下午1:49
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {


    @Qualifier("studentDao")
    @Autowired
    private StudentDao studentDao;

    @Override
    public void save(OaStudent student) {
        studentDao.save(student);
    }

    @Override
    public void delete(Long id) {
        studentDao.delete(id);
    }

    @Override
    public OaStudent get(Long id) {
        return studentDao.findOne(id);
    }

    @Override
    public void update(OaStudent student) {
        studentDao.save(student);
    }

    @Override
    public List<OaStudent> find(Page page, String studentName) {
        org.springframework.data.domain.Page<OaStudent> springDataPage =
                (org.springframework.data.domain.Page<OaStudent>) studentDao.findByStudentNameContaining(studentName, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaStudent> findByStudent(Page page, OaStudent student, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaStudent> spec = buildSpecification(student, searchParams);
        org.springframework.data.domain.Page<OaStudent> oaClassPage = studentDao.findAll(spec, pageRequest);
        return oaClassPage.getContent();
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<OaStudent> buildSpecification( OaStudent student, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
//        filters.put("oaGradeByGradeId.id", new SearchFilter("oaGradeByGradeId.id", SearchFilter.Operator.EQ, gradeId));
        Specification<OaStudent> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaStudent.class);
        return spec;
    }

    @Override
    public List<OaStudent> findAll(Page page) {
        org.springframework.data.domain.Page<OaStudent> springDataPage = studentDao.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public OaStudent findByStudentNumber(Integer studentNumber) {
        return studentDao.findByStudentNumber(studentNumber);
    }

    @Override
    public Integer getMaxStudentNumber() {
        Calendar calendar = Calendar.getInstance();
        List<OaStudent> studentList = studentDao.getMaxStudentNumber(calendar.get(Calendar.YEAR));
        if (studentList != null && !studentList.isEmpty()) {
            return studentList.get(0).getStudentNumber();
        }
        return 0;
    }
}
