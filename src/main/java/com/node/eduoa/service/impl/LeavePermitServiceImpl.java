package com.node.eduoa.service.impl;

import com.node.eduoa.dao.LeavePermitDao;
import com.node.eduoa.entity.OaLeavePermit;
import com.node.eduoa.entity.OaTeacherInfo;
import com.node.eduoa.service.LeavePermitService;
import com.node.system.util.dwz.Page;
import com.node.system.util.dwz.PageUtils;
import org.apache.commons.lang3.StringUtils;
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
 * Date: 13-7-14
 * Time: 下午9:27
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class LeavePermitServiceImpl implements LeavePermitService {


    @Qualifier("leavePermitDao")
    @Autowired
    private LeavePermitDao leavePermitDao;

    @Override
    public void save(OaLeavePermit leavePermit) {
        leavePermitDao.save(leavePermit);
    }

    @Override
    public void delete(Long id) {
        leavePermitDao.delete(id);
    }

    @Override
    public OaLeavePermit get(Long id) {
        return leavePermitDao.findOne(id);
    }

    @Override
    public void update(OaLeavePermit leavePermit) {
        leavePermitDao.save(leavePermit);
    }

    @Override
    public List<OaLeavePermit> findByLeavePermitCondition(Page page, OaLeavePermit leavePermit, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaLeavePermit> spec = buildSpecification(leavePermit, searchParams);
        org.springframework.data.domain.Page<OaLeavePermit> oaClassPage = leavePermitDao.findAll(spec, pageRequest);
        page.setTotalPage(oaClassPage.getTotalPages());
        page.setTotalCount(oaClassPage.getTotalElements());
        return oaClassPage.getContent();
    }

    private Specification<OaLeavePermit> buildSpecification(OaLeavePermit leavePermit, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
//        if (leavePermit.getApplyTeacherId() != null) {
//            filters.put("securityOrganizationByOrgId.name", new SearchFilter("securityOrganizationByOrgId.name",
//                    SearchFilter.Operator.LIKE, teacherInfo.getSecurityOrganizationByOrgId().getName()));
//        }
        Specification<OaLeavePermit> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaLeavePermit.class);
        return spec;
    }

    @Override
    public List<OaLeavePermit> findAll(Page page) {
        org.springframework.data.domain.Page<OaLeavePermit> springDataPage = leavePermitDao.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
}
