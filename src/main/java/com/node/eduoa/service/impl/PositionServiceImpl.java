package com.node.eduoa.service.impl;

import com.node.eduoa.dao.PositionDAO;
import com.node.eduoa.entity.OaPosition;
import com.node.eduoa.service.PositionService;
import com.node.system.util.dwz.Page;
import com.node.system.util.dwz.PageUtils;
import com.sample.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 下午12:02
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class PositionServiceImpl implements PositionService {

    @Qualifier("positionDAO")
    @Autowired
    private PositionDAO positionDAO;

    @Override
    public void save(OaPosition position) {
        positionDAO.save(position);
    }

    @Override
    public void delete(Long id) {
        positionDAO.delete(id);
    }

    @Override
    public OaPosition get(Long id) {
        return positionDAO.findOne(id);
    }

    @Override
    public void update(OaPosition position) {
        positionDAO.save(position);
    }

    @Override
    public List<OaPosition> find(Page page, String positionName) {
        org.springframework.data.domain.Page<OaPosition> springDataPage =
                (org.springframework.data.domain.Page<OaPosition>) positionDAO.findByPositionNameContaining(positionName, PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaPosition> findAll(Page page) {
        org.springframework.data.domain.Page<OaPosition> springDataPage = positionDAO.findAll(PageUtils.createPageable(page));
        page.setTotalCount(springDataPage.getTotalElements());
        return springDataPage.getContent();
    }

    @Override
    public List<OaPosition> findAll() {
        return positionDAO.findAll(new Sort(Sort.Direction.ASC, "id"));
    }
}
