package com.node.eduoa.service.impl;

import com.node.eduoa.dao.ScoreDAO;
import com.node.eduoa.entity.OaGrade;
import com.node.eduoa.entity.OaScore;
import com.node.eduoa.service.ScoreService;
import com.node.system.util.dwz.Page;
import com.node.system.util.dwz.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-31
 * Time: 下午10:34
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class ScoreServiceImpl implements ScoreService {

    @Qualifier("scoreDAO")
    @Autowired
    private ScoreDAO scoreDAO;

    @Override
    public void save(OaScore score) {
        scoreDAO.save(score);
    }

    @Override
    public void delete(Long id) {
        scoreDAO.delete(id);
    }

    @Override
    public OaScore get(Long id) {
        return scoreDAO.findOne(id);
    }

    @Override
    public void update(OaScore score) {
        scoreDAO.save(score);
    }

    @Override
    public List<OaScore> find(Page page, String studentName) {
        org.springframework.data.domain.Page<OaScore> springDataPage = scoreDAO.findByStudentNameContaining(studentName, PageUtils.createPageable(page));
        return springDataPage.getContent();
    }

    @Override
    public List<OaScore> findAll(Page page) {
        org.springframework.data.domain.Page<OaScore> springDataPage = scoreDAO.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }
}
