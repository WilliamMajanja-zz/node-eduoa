package com.node.eduoa.dao;

import com.node.eduoa.entity.CmsChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng
 * Date: 13-8-18
 * Time: 下午12:44
 * To change this template use File | Settings | File Templates.
 */
public interface ChannelDao extends JpaRepository<CmsChannel, Long>, JpaSpecificationExecutor<CmsChannel> {

    @Query("select c from CmsChannel c")
    List<CmsChannel> findAllChannel();

}
