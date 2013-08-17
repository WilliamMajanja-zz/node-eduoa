package com.node.eduoa.service.impl;

import com.node.eduoa.dao.ClassDAO;
import com.node.eduoa.entity.OaClass;
import com.node.eduoa.service.ClassService;
import com.node.system.util.dwz.Page;
import com.node.system.util.dwz.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
 * Date: 13-7-6
 * Time: 下午6:25
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class ClassServiceImpl implements ClassService {

    @Qualifier("classDAO")
    @Autowired
    private ClassDAO classDAO;

    @Override
    public void save(OaClass oaClass) {
        classDAO.save(oaClass);
    }

    @Override
    public void delete(Long id) {
        classDAO.delete(id);
    }

    @Override
    public OaClass get(Long id) {
        return classDAO.findOne(id);
    }

    @Override
    public void update(OaClass oaClass) {
        classDAO.save(oaClass);
    }

    @Override
    public List<OaClass> find(Page page, String className) {
        org.springframework.data.domain.Page<OaClass> springDataPage =
                (org.springframework.data.domain.Page<OaClass>) classDAO.findByClassNameContaining(className, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaClass> findByGradeId(Page page, Long gradeId, Map<String, Object> searchParams) {
        PageRequest pageRequest = PageUtils.createPageRequest(page);
        Specification<OaClass> spec = buildSpecification(gradeId, searchParams);
        org.springframework.data.domain.Page<OaClass> oaClassPage = classDAO.findAll(spec, pageRequest);
        return oaClassPage.getContent();
    }

    /**
     * 创建动态查询条件组合.
     */
    private Specification<OaClass> buildSpecification(Long gradeId, Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        filters.put("oaGradeByGradeId.id", new SearchFilter("oaGradeByGradeId.id", SearchFilter.Operator.EQ, gradeId));
        Specification<OaClass> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaClass.class);
        return spec;
    }

    @Override
    public List<OaClass> findAll(Page page) {
        org.springframework.data.domain.Page<OaClass> springDataPage = classDAO.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaClass> findByClassIds(List<Long> classIds) {
        return classDAO.findByClassIds(classIds);
    }
}
