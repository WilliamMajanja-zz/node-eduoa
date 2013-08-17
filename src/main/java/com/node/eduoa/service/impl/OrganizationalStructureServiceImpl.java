package com.node.eduoa.service.impl;

import com.node.eduoa.dao.OrganizationalStructureDao;
import com.node.eduoa.entity.OaOrganizationalStructure;
import com.node.eduoa.service.OrganizationalStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午9:50
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class OrganizationalStructureServiceImpl implements OrganizationalStructureService {


    @Qualifier("organizationalStructureDao")
    @Autowired
    private OrganizationalStructureDao organizationalStructureDao;

    @Override
    public void save(OaOrganizationalStructure structure) {
        organizationalStructureDao.save(structure);
    }

    @Override
    public void delete(Long id) {
        organizationalStructureDao.delete(id);
    }

    @Override
    public OaOrganizationalStructure get(Long id) {
        return organizationalStructureDao.findOne(id);
    }

    @Override
    public void update(OaOrganizationalStructure structure) {
        organizationalStructureDao.save(structure);
    }

    @Override
    public OaOrganizationalStructure findNewStructure() {
        List<OaOrganizationalStructure> structureList = organizationalStructureDao.findAllOrderByTime();
        if (structureList != null && !structureList.isEmpty()) {
            for (OaOrganizationalStructure structure: structureList) {
                return structure;
            }
        }
        return null;
    }
}
