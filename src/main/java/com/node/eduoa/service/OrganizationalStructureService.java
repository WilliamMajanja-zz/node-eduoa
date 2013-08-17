package com.node.eduoa.service;

import com.node.eduoa.entity.OaOrganizationalStructure;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午9:48
 * To change this template use File | Settings | File Templates.
 */
public interface OrganizationalStructureService {

    void save(OaOrganizationalStructure structure);

    void delete(Long id);

    OaOrganizationalStructure get(Long id);

    void update(OaOrganizationalStructure structure);

    OaOrganizationalStructure findNewStructure();

}
