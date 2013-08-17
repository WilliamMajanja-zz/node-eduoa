package com.node.eduoa.dao;

import com.node.eduoa.entity.OaGrade;
import com.node.eduoa.entity.OaOrganizationalStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 组织结构z
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午9:46
 * To change this template use File | Settings | File Templates.
 */
public interface OrganizationalStructureDao extends JpaRepository<OaOrganizationalStructure, Long> {

    @Query("select u from OaOrganizationalStructure u order by u.createTime desc ")
    List<OaOrganizationalStructure> findAllOrderByTime();

}
