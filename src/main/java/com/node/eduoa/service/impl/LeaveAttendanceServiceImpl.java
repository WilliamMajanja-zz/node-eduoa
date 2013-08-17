package com.node.eduoa.service.impl;

import com.node.eduoa.dao.LeaveAttendanceDao;
import com.node.eduoa.dao.RegistrationAttendanceDao;
import com.node.eduoa.entity.OaLeaveAttendance;
import com.node.eduoa.entity.OaLeaveAttendance;
import com.node.eduoa.service.LeaveAttendanceService;
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
public class LeaveAttendanceServiceImpl implements LeaveAttendanceService {


    @Qualifier("leaveAttendanceDao")
    @Autowired
    private LeaveAttendanceDao leaveAttendanceDao;

    @Override
    public void save(OaLeaveAttendance leaveAttendance) {
        leaveAttendanceDao.save(leaveAttendance);
    }


    @Override
    public void delete(Long id) {
        leaveAttendanceDao.delete(id);
    }

    @Override
    public OaLeaveAttendance get(Long id) {
        return leaveAttendanceDao.findOne(id);
    }

    @Override
    public void update(OaLeaveAttendance leaveAttendance) {
        leaveAttendanceDao.save(leaveAttendance);
    }

    @Override
    public List<OaLeaveAttendance> find(Page page, String teacherName) {
        org.springframework.data.domain.Page<OaLeaveAttendance> springDataPage =
                (org.springframework.data.domain.Page<OaLeaveAttendance>) leaveAttendanceDao.findByTeacherNameContaining(teacherName, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaLeaveAttendance> findByCondition(Page page, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaLeaveAttendance> spec = buildSpecification(searchParams);
        org.springframework.data.domain.Page<OaLeaveAttendance> oaClassPage = leaveAttendanceDao.findAll(spec, pageRequest);
        return oaClassPage.getContent();
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<OaLeaveAttendance> buildSpecification(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<OaLeaveAttendance> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaLeaveAttendance.class);
        return spec;
    }

    @Override
    public List<OaLeaveAttendance> findAll(Page page) {
        org.springframework.data.domain.Page<OaLeaveAttendance> springDataPage = leaveAttendanceDao.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
}
