package com.node.eduoa.service.impl;

import com.node.eduoa.dao.ChannelDao;
import com.node.eduoa.entity.CmsChannel;
import com.node.eduoa.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * User: linfeng
 * Date: 13-8-18
 * Time: 下午12:48
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {

    @Qualifier("channelDao")
    @Autowired
    private ChannelDao channelDao;

    @Override
    public void save(CmsChannel channel) {
        channelDao.save(channel);
    }

    @Override
    public void delete(Long id) {
        channelDao.delete(id);
    }

    @Override
    public CmsChannel get(Long id) {
        return channelDao.findOne(id);
    }

    @Override
    public void update(CmsChannel channel) {
        channelDao.save(channel);
    }

    @Override
    public List<CmsChannel> findAllChannel() {
        return channelDao.findAllChannel();
    }

    @Override
    public CmsChannel findByChannelCode(String channelCode) {
        return channelDao.findByChannelCode(channelCode);
    }
}
