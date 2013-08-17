package com.node.eduoa.service.impl;

import com.node.eduoa.dao.TeachingPlanDAO;
import com.node.eduoa.entity.OaLeavePermit;
import com.node.eduoa.entity.OaTeachingPlan;
import com.node.eduoa.service.TeachingPlanService;
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
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-7-29
 * Time: 下午5:25
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class TeachingPlanServiceImpl implements TeachingPlanService {

    @Qualifier("teachingPlanDAO")
    @Autowired
    private TeachingPlanDAO teachingPlanDAO;

    @Override
    public void save(OaTeachingPlan teachingPlan) {
        teachingPlanDAO.save(teachingPlan);
    }

    @Override
    public void delete(Long id) {
        teachingPlanDAO.delete(id);
    }

    @Override
    public OaTeachingPlan get(Long id) {
        return teachingPlanDAO.findOne(id);
    }

    @Override
    public void update(OaTeachingPlan teachingPlan) {
        teachingPlanDAO.save(teachingPlan);
    }

    @Override
    public List<OaTeachingPlan> findByPlanTitleContaining(String planTitle, Page page) {
        org.springframework.data.domain.Page<OaTeachingPlan> springDataPage =
                (org.springframework.data.domain.Page<OaTeachingPlan>) teachingPlanDAO.findByPlanTitleContaining(planTitle, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaTeachingPlan> findByPlanTitleCondition(Page page, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaTeachingPlan> spec = buildSpecification(searchParams);
        org.springframework.data.domain.Page<OaTeachingPlan> oaClassPage = teachingPlanDAO.findAll(spec, pageRequest);
        page.setTotalPage(oaClassPage.getTotalPages());
        page.setTotalCount(oaClassPage.getTotalElements());
        return oaClassPage.getContent();
    }

    private Specification<OaTeachingPlan> buildSpecification(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<OaTeachingPlan> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaTeachingPlan.class);
        return spec;
    }

    @Override
    public List<OaTeachingPlan> findAll(Page page) {
        org.springframework.data.domain.Page<OaTeachingPlan> springDataPage = teachingPlanDAO.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
}
