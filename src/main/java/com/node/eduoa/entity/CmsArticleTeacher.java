package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "cms_article_teacher")
@Entity
public class CmsArticleTeacher extends IdEntity {

    private static final long serialVersionUID = 3583676522491595053L;

    @javax.persistence.Column(name = "teacher_name")
    @Basic
    private String teacherName;
    @javax.persistence.Column(name = "photo_url")
    @Basic
    private String photoUrl;
    @javax.persistence.Column(name = "attachment_id")
    @Basic
    private Long attachmentId;
    @ManyToOne
    @javax.persistence.JoinColumn(name = "article_id", referencedColumnName = "id")
    private CmsArticle cmsArticleByArticleId;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public CmsArticle getCmsArticleByArticleId() {
        return cmsArticleByArticleId;
    }

    public void setCmsArticleByArticleId(CmsArticle cmsArticleByArticleId) {
        this.cmsArticleByArticleId = cmsArticleByArticleId;
    }


}
