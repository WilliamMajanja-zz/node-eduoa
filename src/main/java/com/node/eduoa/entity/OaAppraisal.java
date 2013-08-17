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
@javax.persistence.Table(name = "oa_appraisal")
@Entity
public class OaAppraisal extends IdEntity {

    private static final long serialVersionUID = 5855825230199448063L;

    @javax.persistence.Column(name = "atype")
    @Basic
    private Integer atype;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "start_month")
    private Date startMonth;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "end_month")
    private Date endMonth;
    @javax.persistence.Column(name = "teacher_id")
    @Basic
    private Long teacherId;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "create_time")
    private Date createTime;

    @javax.persistence.Column(name = "user_id")
    private Long userId;
    @OneToMany(mappedBy = "oaAppraisalByApplayId")
    private List<SysTypeRules> sysTypeRulesesById;

    public Integer getAtype() {
        return atype;
    }

    public void setAtype(Integer atype) {
        this.atype = atype;
    }

    public Date getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(Date startMonth) {
        this.startMonth = startMonth;
    }

    public Date getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(Date endMonth) {
        this.endMonth = endMonth;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
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

    public List<SysTypeRules> getSysTypeRulesesById() {
        return sysTypeRulesesById;
    }

    public void setSysTypeRulesesById(List<SysTypeRules> sysTypeRulesesById) {
        this.sysTypeRulesesById = sysTypeRulesesById;
    }


}
