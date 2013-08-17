package com.node.eduoa.entity;

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
    @OneToMany(mappedBy = "cmsChannelByChannelId")
    private List<CmsArticle> cmsArticlesById;

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

    public List<CmsArticle> getCmsArticlesById() {
        return cmsArticlesById;
    }

    public void setCmsArticlesById(List<CmsArticle> cmsArticlesById) {
        this.cmsArticlesById = cmsArticlesById;
    }

}
