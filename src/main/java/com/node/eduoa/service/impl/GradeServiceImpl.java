package com.node.eduoa.service.impl;

import com.node.eduoa.dao.GradeDAO;
import com.node.eduoa.entity.OaGrade;
import com.node.eduoa.entity.OaPosition;
import com.node.eduoa.service.GradeService;
import com.node.system.util.dwz.Page;
import com.node.system.util.dwz.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 下午2:22
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class GradeServiceImpl implements GradeService {


    @Qualifier("gradeDAO")
    @Autowired
    private GradeDAO gradeDAO;

    @Override
    public void save(OaGrade grade) {
        gradeDAO.save(grade);
    }

    @Override
    public void delete(Long id) {
        gradeDAO.delete(id);
    }

    @Override
    public OaGrade get(Long id) {
        return gradeDAO.findOne(id);
    }

    @Override
    public void update(OaGrade grade) {
        gradeDAO.save(grade);
    }

    @Override
    public List<OaGrade> find(Page page, String gradeName) {
        org.springframework.data.domain.Page<OaGrade> springDataPage =
                (org.springframework.data.domain.Page<OaGrade>) gradeDAO.findByGradeNameContaining(gradeName, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaGrade> findAll(Page page) {
        org.springframework.data.domain.Page<OaGrade> springDataPage = gradeDAO.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaGrade> findAllByYear(Integer currentYear) {
        return gradeDAO.findAllOrderByCreateTime(currentYear);
    }
}
