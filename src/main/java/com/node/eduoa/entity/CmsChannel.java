package com.node.eduoa.entity;

import com.google.common.collect.Lists;
import com.node.system.entity.IdEntity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "cms_channel")
@Entity
public class CmsChannel extends IdEntity {

    private static final long serialVersionUID = -4798305974563046367L;

    @javax.persistence.Column(name = "channel_name")
    @Basic
    private String channelName;
    @javax.persistence.Column(name = "channel_sort")
    @Basic
    private Integer channelSort;
    @javax.persistence.Column(name = "channel_url")
    @Basic
    private String channelUrl;
    @javax.persistence.Column(name = "channel_code")
    @Basic
    private String channelCode;

    @OneToMany(mappedBy = "channel")
    private List<CmsArticle> articles = Lists.newArrayList();

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public Integer getChannelSort() {
        return channelSort;
    }

    public void setChannelSort(Integer channelSort) {
        this.channelSort = channelSort;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public List<CmsArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<CmsArticle> articles) {
        this.articles = articles;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
}
