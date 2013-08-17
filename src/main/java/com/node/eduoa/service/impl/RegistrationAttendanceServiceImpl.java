package com.node.eduoa.service.impl;

import com.node.eduoa.dao.RegistrationAttendanceDao;
import com.node.eduoa.entity.OaClass;
import com.node.eduoa.entity.OaRegistrationAttendance;
import com.node.eduoa.service.RegistrationAttendanceService;
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
 * User: linfeng at Administrator
 * Date: 13-7-21
 * Time: 下午7:14
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class RegistrationAttendanceServiceImpl implements RegistrationAttendanceService {


    @Qualifier("registrationAttendanceDao")
    @Autowired
    private RegistrationAttendanceDao registrationAttendanceDao;

    @Override
    public void save(OaRegistrationAttendance registrationAttendance) {
        registrationAttendanceDao.save(registrationAttendance);
    }

    @Override
    public void delete(Long id) {
        registrationAttendanceDao.delete(id);
    }

    @Override
    public OaRegistrationAttendance get(Long id) {
        return registrationAttendanceDao.findOne(id);
    }

    @Override
    public void update(OaRegistrationAttendance registrationAttendance) {
        registrationAttendanceDao.save(registrationAttendance);
    }

    @Override
    public List<OaRegistrationAttendance> find(Page page, String teacherName) {
        org.springframework.data.domain.Page<OaRegistrationAttendance> springDataPage =
                (org.springframework.data.domain.Page<OaRegistrationAttendance>) registrationAttendanceDao.findByTeacherNameContaining(teacherName, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaRegistrationAttendance> findByCondition(Page page, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaRegistrationAttendance> spec = buildSpecification(searchParams);
        org.springframework.data.domain.Page<OaRegistrationAttendance> oaClassPage = registrationAttendanceDao.findAll(spec, pageRequest);
        return oaClassPage.getContent();
    }

    @Override
    public List<OaRegistrationAttendance> findByCondition(Page page, Map<String, Object> andSearchParams,
                                                          Map<String, Object> orSearchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaRegistrationAttendance> spec = buildSpecification(andSearchParams, orSearchParams);
        org.springframework.data.domain.Page<OaRegistrationAttendance> oaClassPage = registrationAttendanceDao.findAll(spec, pageRequest);
        return oaClassPage.getContent();
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<OaRegistrationAttendance> buildSpecification(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<OaRegistrationAttendance> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaRegistrationAttendance.class);
        return spec;
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<OaRegistrationAttendance> buildSpecification(Map<String, Object> andSearchParams,
                                                                       Map<String, Object> orSearchParams) {
        Map<String, SearchFilter> andFilters = SearchFilter.parse(andSearchParams);
        Map<String, SearchFilter> orFilters = SearchFilter.parse(orSearchParams);
        Specification<OaRegistrationAttendance> spec = DynamicSpecifications.bySearchFilter(andFilters.values(), orFilters.values(), OaRegistrationAttendance.class);
        return spec;
    }

    @Override
    public List<OaRegistrationAttendance> findAll(Page page) {
        org.springframework.data.domain.Page<OaRegistrationAttendance> springDataPage = registrationAttendanceDao.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
}
