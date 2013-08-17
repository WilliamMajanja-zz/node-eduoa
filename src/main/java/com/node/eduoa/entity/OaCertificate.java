package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_certificate")
@Entity
public class OaCertificate extends IdEntity {


    private static final long serialVersionUID = 3678144871183414046L;

    @javax.persistence.Column(name = "advanced")
    @Basic
    private Integer advanced;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "certificates_time")
    private Date certificatesTime;
    @javax.persistence.Column(name = "description")
    private String description;
    @ManyToOne
    @javax.persistence.JoinColumn(name = "type_id", referencedColumnName = "id")
    private OaCertificateType oaCertificateTypeByTypeId;
    @ManyToOne
    @javax.persistence.JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private OaTeacherInfo oaTeacherInfoByTeacherId;

    public Integer getAdvanced() {
        return advanced;
    }

    public void setAdvanced(Integer advanced) {
        this.advanced = advanced;
    }

    public Date getCertificatesTime() {
        return certificatesTime;
    }

    public void setCertificatesTime(Date certificatesTime) {
        this.certificatesTime = certificatesTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OaCertificateType getOaCertificateTypeByTypeId() {
        return oaCertificateTypeByTypeId;
    }

    public void setOaCertificateTypeByTypeId(OaCertificateType oaCertificateTypeByTypeId) {
        this.oaCertificateTypeByTypeId = oaCertificateTypeByTypeId;
    }

    public OaTeacherInfo getOaTeacherInfoByTeacherId() {
        return oaTeacherInfoByTeacherId;
    }

    public void setOaTeacherInfoByTeacherId(OaTeacherInfo oaTeacherInfoByTeacherId) {
        this.oaTeacherInfoByTeacherId = oaTeacherInfoByTeacherId;
    }


}
