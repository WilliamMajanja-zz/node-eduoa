package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "cms_article")
public class CmsArticle extends IdEntity {

    private static final long serialVersionUID = 1523502380173939582L;

    @Column(name = "article_class_id")
    @Basic
    private Long articleClassId;

    @Column(name = "title")
    @Basic
    private String title;

    @Column(name = "summary")
    @Basic
    private String summary;

    @Column(name = "content")
    @Basic
    private String content;

    @Column(name = "hits")
    @Basic
    private Integer hits;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    @Basic
    private Date createTime;

    @Column(name = "create_user_id")
    @Basic
    private Long createUserId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_user_id")
    @Basic
    private Long updateUserId;

    @Column(name = "article_state")
    @Basic
    private Integer articleState;
    @ManyToOne
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    private CmsChannel cmsChannelByChannelId;

    @OneToMany(mappedBy = "cmsArticleByArticleId")
    private List<CmsArticleTeacher> cmsArticleTeachersById;

    public Long getArticleClassId() {
        return articleClassId;
    }

    public void setArticleClassId(Long articleClassId) {
        this.articleClassId = articleClassId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getArticleState() {
        return articleState;
    }

    public void setArticleState(Integer articleState) {
        this.articleState = articleState;
    }

    public CmsChannel getCmsChannelByChannelId() {
        return cmsChannelByChannelId;
    }

    public void setCmsChannelByChannelId(CmsChannel cmsChannelByChannelId) {
        this.cmsChannelByChannelId = cmsChannelByChannelId;
    }

    public List<CmsArticleTeacher> getCmsArticleTeachersById() {
        return cmsArticleTeachersById;
    }

    public void setCmsArticleTeachersById(List<CmsArticleTeacher> cmsArticleTeachersById) {
        this.cmsArticleTeachersById = cmsArticleTeachersById;
    }

}
