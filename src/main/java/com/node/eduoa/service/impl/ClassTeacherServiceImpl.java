package com.node.eduoa.service.impl;

import com.node.eduoa.dao.ClassTeacherDAO;
import com.node.eduoa.entity.OaClassTeacher;
import com.node.eduoa.service.ClassTeacherService;
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

import java.util.List;
import java.util.Map;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-9
 * Time: 下午8:49
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class ClassTeacherServiceImpl implements ClassTeacherService {


    @Qualifier("classTeacherDAO")
    @Autowired
    private ClassTeacherDAO classTeacherDAO;

    @Override
    public void save(OaClassTeacher classTeacher) {
        classTeacherDAO.save(classTeacher);
    }

    @Override
    public void delete(Long id) {
        classTeacherDAO.delete(id);
    }

    @Override
    public OaClassTeacher get(Long id) {
        return classTeacherDAO.findOne(id);
    }

    @Override
    public void update(OaClassTeacher classTeacher) {
        classTeacherDAO.save(classTeacher);
    }

    @Override
    public List<OaClassTeacher> findByClassId(Page page, Long classId, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaClassTeacher> spec = buildSpecification(classId, null, searchParams);
        org.springframework.data.domain.Page<OaClassTeacher> oaClassPage = classTeacherDAO.findAll(spec, pageRequest);
        return oaClassPage.getContent();
    }

    @Override
    public List<OaClassTeacher> findByTeacherId(Page page, Long teacherId, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaClassTeacher> spec = buildSpecification(null, teacherId, searchParams);
        org.springframework.data.domain.Page<OaClassTeacher> oaClassPage = classTeacherDAO.findAll(spec, pageRequest);
        return oaClassPage.getContent();
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<OaClassTeacher> buildSpecification(Long classId, Long teacherId, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        if (classId != null) {
            filters.put("oaClassByClassId.id", new SearchFilter("oaClassByClassId.id", SearchFilter.Operator.EQ, classId));
        }
        if (teacherId != null) {
            filters.put("oaTeacherInfoByTeacherId.id", new SearchFilter("oaTeacherInfoByTeacherId.id", SearchFilter.Operator.EQ, classId));
        }
        Specification<OaClassTeacher> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaClassTeacher.class);
        return spec;
    }

    @Override
    public List<OaClassTeacher> findAll(Page page) {
        org.springframework.data.domain.Page<OaClassTeacher> springDataPage = classTeacherDAO.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaClassTeacher> findByClassIdNoPage(Long classId) {
        return classTeacherDAO.findByClassId(classId);
    }

    @Override
    public List<OaClassTeacher> findByTeacherId(Long teacherId) {
        return classTeacherDAO.findByTeacherId(teacherId);
    }

    @Override
    public List<OaClassTeacher> findByTeacherIdAndHeadTeacher(Long teacherId, Integer headTeacher) {
        return classTeacherDAO.findByTeacherIdAndHead(teacherId, headTeacher);
    }
}
