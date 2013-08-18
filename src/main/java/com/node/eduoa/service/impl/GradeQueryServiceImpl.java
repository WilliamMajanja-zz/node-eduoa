package com.node.eduoa.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import com.node.eduoa.dao.GradeQueryDAO;
import com.node.eduoa.entity.OaLeavePermit;
import com.node.eduoa.entity.OaScore;
import com.node.eduoa.service.GradeQueryService;
import com.node.system.util.dwz.Page;
import com.node.system.util.dwz.PageUtils;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 下午2:22
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class GradeQueryServiceImpl implements GradeQueryService {

    @Autowired
    private GradeQueryDAO gradeAQueryDAO;

	@Override
	public List<OaScore> findSearchParameter(Map<String, Object> searchParams) {
        Specification<OaScore> spec = buildSpecification(searchParams);
        List<OaScore> oaClass = gradeAQueryDAO.findAll(spec);
        return oaClass;
	}


    private Specification<OaScore> buildSpecification(Map<String, Object> searchParams) {
        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<OaScore> spec = DynamicSpecifications.bySearchFilter(filters.values(), OaScore.class);
        return spec;
    }
}
