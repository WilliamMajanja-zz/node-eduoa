package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
@javax.persistence.Table(name = "oa_contact")
@Entity
public class OaContact extends IdEntity {


    private static final long serialVersionUID = -6046655656817061149L;
    @javax.persistence.Column(name = "nexus")
    private String nexus;
    @javax.persistence.Column(name = "contact_name")
    private String contactName;
    @javax.persistence.Column(name = "job")
    private String job;
    @javax.persistence.Column(name = "phone")
    private String phone;
    @javax.persistence.Column(name = "qq")
    private String qq;
    @javax.persistence.Column(name = "email")
    private String email;
    @javax.persistence.Column(name = "address")
    private String address;
    @javax.persistence.Column(name = "home_phone")
    private String homePhone;
    @javax.persistence.Column(name = "office_phone")
    private String officePhone;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "update_time")
    private Date updateTime;

    @ManyToOne
    @javax.persistence.JoinColumn(name = "student_id", referencedColumnName = "id")
    private OaStudent oaStudentByStudentId;

    public String getNexus() {
        return nexus;
    }

    public void setNexus(String nexus) {
        this.nexus = nexus;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public OaStudent getOaStudentByStudentId() {
        return oaStudentByStudentId;
    }

    public void setOaStudentByStudentId(OaStudent oaStudentByStudentId) {
        this.oaStudentByStudentId = oaStudentByStudentId;
    }
}
