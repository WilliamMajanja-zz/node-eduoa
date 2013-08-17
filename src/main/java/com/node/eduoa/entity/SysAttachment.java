package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "sys_attachment")
@Entity
public class SysAttachment extends IdEntity {

    private static final long serialVersionUID = 7966223008613097857L;
    @javax.persistence.Column(name = "file_name")
    private String fileName;
    @javax.persistence.Column(name = "file_ext")
    private String fileExt;
    @javax.persistence.Column(name = "file_size")
    private Long fileSize;
    @javax.persistence.Column(name = "service_file")
    private String serviceFile;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "create_time")
    private Date createTime;
    @javax.persistence.Column(name = "user_id")
    private Long userId;

    @javax.persistence.Column(name = "attachment_type")
    private Integer attachmentType;

    @javax.persistence.Column(name = "url_path")
    private String urlPath;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExt() {
        return fileExt;
    }

    public void setFileExt(String fileExt) {
        this.fileExt = fileExt;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getServiceFile() {
        return serviceFile;
    }

    public void setServiceFile(String serviceFile) {
        this.serviceFile = serviceFile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(Integer attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
