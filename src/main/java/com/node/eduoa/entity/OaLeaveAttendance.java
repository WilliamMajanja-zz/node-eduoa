package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: linfeng
 * Date: 13-7-20
 * Time: 下午4:42
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_leave_attendance")
@Entity
public class OaLeaveAttendance extends IdEntity {


    private static final long serialVersionUID = 1731468052657407591L;

    @Column(name = "attendance_date")
    private Long attendanceDate;//考勤日期

    @Column(name = "attendance_date_cn")
    private String attendanceDateCn;//考勤日期

    @Column(name = "teacher_id")
    private Long teacherId;
    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "organization_name")
    private String organizationName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "leave_start_time")
    private Date leaveStartTime;
    @Column(name = "leave_start")
    private Integer leaveStart;
    @Transient
    private Integer leaveStartDisplay;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "leave_end_time")
    private Date leaveEndTime;
    @Column(name = "leave_end")
    private Integer leaveEnd;
    @Transient
    private Integer leaveEndDisplay;

    @Column(name = "leave_time")
    private Long leaveTime;

    @Column(name = "leave_time_cn")
    private String leaveTimeCn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    public OaLeaveAttendance() {
    }

    public OaLeaveAttendance(Long attendanceDate, String attendanceDateCn, Long teacherId, String teacherName,
                             Long organizationId, String organizationName, Date createTime) {
        this.attendanceDate = attendanceDate;
        this.attendanceDateCn = attendanceDateCn;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.createTime = createTime;
    }

    public String getAttendanceDateCn() {
        return attendanceDateCn;
    }

    public void setAttendanceDateCn(String attendanceDateCn) {
        this.attendanceDateCn = attendanceDateCn;
    }

    public Long getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Long attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Date getLeaveStartTime() {
        return leaveStartTime;
    }

    public void setLeaveStartTime(Date leaveStartTime) {
        this.leaveStartTime = leaveStartTime;
    }

    public Date getLeaveEndTime() {
        return leaveEndTime;
    }

    public void setLeaveEndTime(Date leaveEndTime) {
        this.leaveEndTime = leaveEndTime;
    }

    public Long getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Long leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLeaveStartDisplay() {
        return leaveStartDisplay;
    }

    public void setLeaveStartDisplay(Integer leaveStartDisplay) {
        this.leaveStartDisplay = leaveStartDisplay;
    }

    public Integer getLeaveEndDisplay() {
        return leaveEndDisplay;
    }

    public void setLeaveEndDisplay(Integer leaveEndDisplay) {
        this.leaveEndDisplay = leaveEndDisplay;
    }

    public Integer getLeaveStart() {
        return leaveStart;
    }

    public void setLeaveStart(Integer leaveStart) {
        this.leaveStart = leaveStart;
    }

    public Integer getLeaveEnd() {
        return leaveEnd;
    }

    public void setLeaveEnd(Integer leaveEnd) {
        this.leaveEnd = leaveEnd;
    }

    public String getLeaveTimeCn() {
        return leaveTimeCn;
    }

    public void setLeaveTimeCn(String leaveTimeCn) {
        this.leaveTimeCn = leaveTimeCn;
    }
}
