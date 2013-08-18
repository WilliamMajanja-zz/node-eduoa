package com.node.eduoa.service;

import com.node.eduoa.entity.CmsChannel;
import com.node.eduoa.entity.CmsChannel;
import com.node.system.util.dwz.Page;

import java.util.List;
import java.util.Map;

/**
 *
 * User: linfeng
 * Date: 13-8-18
 * Time: 下午12:45
 * To change this template use File | Settings | File Templates.
 */
public interface ChannelService {

    void save(CmsChannel channel);

    void delete(Long id);

    CmsChannel get(Long id);

    void update(CmsChannel channel);

    List<CmsChannel> findAllChannel();

}
