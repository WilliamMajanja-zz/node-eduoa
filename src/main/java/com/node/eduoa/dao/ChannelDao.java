package com.node.eduoa.dao;

import com.node.eduoa.entity.CmsChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * User: linfeng
 * Date: 13-8-18
 * Time: 下午12:44
 * To change this template use File | Settings | File Templates.
 */
public interface ChannelDao extends JpaRepository<CmsChannel, Long>, JpaSpecificationExecutor<CmsChannel> {

    @SuppressWarnings("JpaQlInspection")
    @Query("select c from CmsChannel c")
    List<CmsChannel> findAllChannel();

    @SuppressWarnings("JpaQlInspection")
    @Query("select c from CmsChannel c where c.channelCode=:channelCode")
    CmsChannel findByChannelCode(@Param("channelCode") String channelCode);

}
