package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
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
    private String title;

    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "create_user_id")
    private Long createUserId;

    @Column(name = "create_user_name")
    private String createUserName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_user_id")
    private Long updateUserId;

    @Column(name = "update_user_name")
    private String updateUserName;

    @Column(name = "article_state")
    private Integer articleState;

    @Column(name = "attachment_id")
    private Long attachmentId;

    @Column(name = "file_name")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "channel_id", referencedColumnName = "id")
    private CmsChannel channel;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public CmsChannel getChannel() {
        return channel;
    }

    public void setChannel(CmsChannel channel) {
        this.channel = channel;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }
}
