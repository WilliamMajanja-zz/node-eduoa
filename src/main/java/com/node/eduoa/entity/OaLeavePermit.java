package com.node.eduoa.entity;

import com.node.eduoa.enums.ApplyStatusEnum;
import com.node.system.entity.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: linfeng at Administrator
 * Date: 13-7-14
 * Time: 下午8:33
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_leave_permit")
@Entity
public class OaLeavePermit extends IdEntity {

    private static final long serialVersionUID = 3751396132231100633L;

    @Column(name = "leader_id")
    private Long leaderId;

    @javax.persistence.Column(name = "leader_name")
    private String leaderName;

    @javax.persistence.Column(name = "leader_position")
    private String leaderPosition;

    @javax.persistence.Column(name = "reason")
    private String reason;

    @javax.persistence.Column(name = "remark")
    private String remark;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "start_time")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "end_time")
    private Date endTime;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "apply_time")
    private Date applyTime;
    @javax.persistence.Column(name = "apply_teacher_id")
    private Long applyTeacherId;
    @javax.persistence.Column(name = "apply_teacher_name")
    private String applyTeacherName;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "create_time")
    private Date createTime;
    @javax.persistence.Column(name = "statue")
    private Integer statue;
    @javax.persistence.Column(name = "apply_statue")
    private Integer applyStatue;

    @Column(name = "apply_day")
    private Integer applyDay;

    @Column(name = "real_day")
    private Integer realDay;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sick_time")
    private Date sickTime;

    @Transient
    private String applyStatueCn;

    public Date getSickTime() {
        return sickTime;
    }

    public void setSickTime(Date sickTime) {
        this.sickTime = sickTime;
    }

    public Integer getRealDay() {
        return realDay;
    }

    public void setRealDay(Integer realDay) {
        this.realDay = realDay;
    }

    public Integer getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(Integer applyDay) {
        this.applyDay = applyDay;
    }

    public String getApplyStatueCn() {
        if (applyStatue != null) {
            ApplyStatusEnum classTypeEnum = ApplyStatusEnum.valueByIndex(applyStatue);
            if (classTypeEnum != null) {
                return classTypeEnum.getText();
            }
        }
        return "";
    }

    public void setApplyStatueCn(String applyStatueCn) {
        this.applyStatueCn = applyStatueCn;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderPosition() {
        return leaderPosition;
    }

    public void setLeaderPosition(String leaderPosition) {
        this.leaderPosition = leaderPosition;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Long getApplyTeacherId() {
        return applyTeacherId;
    }

    public void setApplyTeacherId(Long applyTeacherId) {
        this.applyTeacherId = applyTeacherId;
    }

    public String getApplyTeacherName() {
        return applyTeacherName;
    }

    public void setApplyTeacherName(String applyTeacherName) {
        this.applyTeacherName = applyTeacherName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public Integer getApplyStatue() {
        return applyStatue;
    }

    public void setApplyStatue(Integer applyStatue) {
        this.applyStatue = applyStatue;
    }
}
