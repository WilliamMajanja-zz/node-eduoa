package com.node.eduoa.service.impl;

import com.node.eduoa.dao.TeacherInfoDao;
import com.node.eduoa.entity.OaTeacherInfo;
import com.node.eduoa.service.TeacherInfoService;
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
 * Date: 13-7-7
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class TeacherInfoServiceImpl implements TeacherInfoService {


    @Qualifier("teacherInfoDao")
    @Autowired
    private TeacherInfoDao teacherInfoDao;

    @Override
    public void save(OaTeacherInfo teacherInfo) {
        teacherInfoDao.save(teacherInfo);
    }

    @Override
    public void delete(Long id) {
        teacherInfoDao.delete(id);
    }

    @Override
    public OaTeacherInfo get(Long id) {
        return teacherInfoDao.findOne(id);
    }

    @Override
    public void update(OaTeacherInfo teacherInfo) {
        teacherInfoDao.save(teacherInfo);
    }

    @Override
    public List<OaTeacherInfo> findByTeacherNameContaining(String teacherName, Page page) {
        org.springframework.data.domain.Page<OaTeacherInfo> springDataPage =
                (org.springframework.data.domain.Page<OaTeacherInfo>) teacherInfoDao.findByTeacherNameContaining(teacherName, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaTeacherInfo> findByTeacherInfoCondition(Page page, OaTeacherInfo teacherInfo, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaTeacherInfo> spec = buildSpecification(teacherInfo, searchParams);
        org.springframework.data.domain.Page<OaTeacherInfo> oaClassPage = teacherInfoDao.findAll(spec, pageRequest);
        page.setTotalPage(oaClassPage.getTotalPages());
        page.setTotalCount(oaClassPage.getTotalElements());
        return oaClassPage.getContent();
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<OaTeacherInfo> buildSpecification(OaTeacherInfo teacherInfo, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        if (teacherInfo.getSecurityOrganizationByOrgId() != null
                && teacherInfo.getSecurityOrganizationByOrgId().getName() != null) {
            filters.put("securityOrganizationByOrgId.name", new SearchFilter("securityOrganizationByOrgId.name",
                    SearchFilter.Operator.LIKE, teacherInfo.getSecurityOrganizationByOrgId().getName()));
        }
        if (teacherInfo.getOaSubjectBySubjectId() != null
                && teacherInfo.getOaSubjectBySubjectId().getId() != null) {
            filters.put("oaSubjectBySubjectId.id", new SearchFilter("oaSubjectBySubjectId.id",
                    SearchFilter.Operator.EQ, teacherInfo.getOaSubjectBySubjectId().getId()));
        }
        if (teacherInfo.getOaPositionByPositionId() != null
                && teacherInfo.getOaPositionByPositionId().getId() != null) {
            filters.put("oaPositionByPositionId.id", new SearchFilter("oaPositionByPositionId.id",
                    SearchFilter.Operator.EQ, teacherInfo.getOaPositionByPositionId().getId()));
        }
        if (teacherInfo.getOriginalEducation() != null) {
            filters.put("originalEducation", new SearchFilter("originalEducation",
                    SearchFilter.Operator.EQ, teacherInfo.getOriginalEducation()));
        }
        if (teacherInfo.getNewEducation() != null) {
            filters.put("newEducation", new SearchFilter("newEducation",
                    SearchFilter.Operator.EQ, teacherInfo.getNewEducation()));
        }

        Specification<OaTeacherInfo> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaTeacherInfo.class);
        return spec;
    }

    @Override
    public List<OaTeacherInfo> findAll(Page page) {
        org.springframework.data.domain.Page<OaTeacherInfo> springDataPage = teacherInfoDao.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaTeacherInfo> findByExample(Specification<OaTeacherInfo> specification, Page page) {
        org.springframework.data.domain.Page<OaTeacherInfo> springDataPage =
                teacherInfoDao.findAll(specification, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

}
