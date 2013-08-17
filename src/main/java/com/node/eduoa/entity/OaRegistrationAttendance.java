package com.node.eduoa.entity;

import com.node.eduoa.enums.AttendanceEnum;
import com.node.system.entity.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: linfeng
 * Date: 13-7-20
 * Time: 下午4:42
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_registration_attendance")
@Entity
public class OaRegistrationAttendance extends IdEntity {

    private static final long serialVersionUID = 7116375295309848920L;

    @Column(name = "attendance_date_cn")
    private String attendanceDateCn;//考勤日期
    @Column(name = "attendance_date")
    private Long attendanceDate;//考勤日期
    @Column(name = "teacher_id")
    private Long teacherId;
    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "morning_start")
    private Integer morningStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "morning_start_time")
    private Date morningStartTime;
    @Transient
    private Integer morningStartDisplay;
    @Transient
    private String morningStartString;

    @Column(name = "morning_end")
    private Integer morningEnd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "morning_end_time")
    private Date morningEndTime;
    @Transient
    private Integer morningEndDisplay;
    @Transient
    private String morningEndString;

    @Column(name = "afternoon_start")
    private Integer afternoonStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "afternoon_start_time")
    private Date afternoonStartTime;
    @Transient
    private Integer afternoonStartDisplay;
    @Transient
    private String afternoonStartString;

    @Column(name = "afternoon_end")
    private Integer afternoonEnd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "afternoon_end_time")
    private Date afternoonEndTime;
    @Transient
    private Integer afternoonEndDisplay;
    @Transient
    private String afternoonEndString;

    @Column(name = "night_start")
    private Integer nightStart;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "night_start_time")
    private Date nightStartTime;
    @Transient
    private Integer nightStartDisplay;
    @Transient
    private String nightStartString;

    @Column(name = "night_end")
    private Integer nightEnd;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "night_end_time")
    private Date nightEndTime;
    @Transient
    private Integer nightEndDisplay;
    @Transient
    private String nightEndString;

    @Column(name = "create_time")
    private Date createTime;

    public OaRegistrationAttendance() {
    }

    public OaRegistrationAttendance(String attendanceDateCn, Long attendanceDate, Long teacherId, String teacherName,
                                    Long organizationId, String organizationName, Date createTime) {
        this.attendanceDateCn = attendanceDateCn;
        this.attendanceDate = attendanceDate;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.organizationId = organizationId;
        this.organizationName = organizationName;
        this.createTime = createTime;
    }

    public Long getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Long attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getAttendanceDateCn() {
        return attendanceDateCn;
    }

    public void setAttendanceDateCn(String attendanceDateCn) {
        this.attendanceDateCn = attendanceDateCn;
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

    public Integer getMorningStart() {
        return morningStart;
    }

    public void setMorningStart(Integer morningStart) {
        this.morningStart = morningStart;
    }

    public Integer getMorningEnd() {
        return morningEnd;
    }

    public void setMorningEnd(Integer morningEnd) {
        this.morningEnd = morningEnd;
    }

    public Integer getAfternoonStart() {
        return afternoonStart;
    }

    public void setAfternoonStart(Integer afternoonStart) {
        this.afternoonStart = afternoonStart;
    }

    public Integer getAfternoonEnd() {
        return afternoonEnd;
    }

    public void setAfternoonEnd(Integer afternoonEnd) {
        this.afternoonEnd = afternoonEnd;
    }

    public Integer getNightStart() {
        return nightStart;
    }

    public void setNightStart(Integer nightStart) {
        this.nightStart = nightStart;
    }

    public Integer getNightEnd() {
        return nightEnd;
    }

    public void setNightEnd(Integer nightEnd) {
        this.nightEnd = nightEnd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getMorningStartTime() {
        return morningStartTime;
    }

    public void setMorningStartTime(Date morningStartTime) {
        this.morningStartTime = morningStartTime;
    }

    public Date getMorningEndTime() {
        return morningEndTime;
    }

    public void setMorningEndTime(Date morningEndTime) {
        this.morningEndTime = morningEndTime;
    }

    public Date getAfternoonStartTime() {
        return afternoonStartTime;
    }

    public void setAfternoonStartTime(Date afternoonStartTime) {
        this.afternoonStartTime = afternoonStartTime;
    }

    public Date getAfternoonEndTime() {
        return afternoonEndTime;
    }

    public void setAfternoonEndTime(Date afternoonEndTime) {
        this.afternoonEndTime = afternoonEndTime;
    }

    public Date getNightStartTime() {
        return nightStartTime;
    }

    public void setNightStartTime(Date nightStartTime) {
        this.nightStartTime = nightStartTime;
    }

    public Date getNightEndTime() {
        return nightEndTime;
    }

    public void setNightEndTime(Date nightEndTime) {
        this.nightEndTime = nightEndTime;
    }

    public Integer getMorningStartDisplay() {
        return morningStartDisplay;
    }

    public void setMorningStartDisplay(Integer morningStartDisplay) {
        this.morningStartDisplay = morningStartDisplay;
    }

    public Integer getMorningEndDisplay() {
        return morningEndDisplay;
    }

    public void setMorningEndDisplay(Integer morningEndDisplay) {
        this.morningEndDisplay = morningEndDisplay;
    }

    public Integer getAfternoonStartDisplay() {
        return afternoonStartDisplay;
    }

    public void setAfternoonStartDisplay(Integer afternoonStartDisplay) {
        this.afternoonStartDisplay = afternoonStartDisplay;
    }

    public Integer getAfternoonEndDisplay() {
        return afternoonEndDisplay;
    }

    public void setAfternoonEndDisplay(Integer afternoonEndDisplay) {
        this.afternoonEndDisplay = afternoonEndDisplay;
    }

    public Integer getNightStartDisplay() {
        return nightStartDisplay;
    }

    public void setNightStartDisplay(Integer nightStartDisplay) {
        this.nightStartDisplay = nightStartDisplay;
    }

    public Integer getNightEndDisplay() {
        return nightEndDisplay;
    }

    public void setNightEndDisplay(Integer nightEndDisplay) {
        this.nightEndDisplay = nightEndDisplay;
    }


    public String getMorningStartString() {
        if (morningStart != null) {
            AttendanceEnum classTypeEnum = AttendanceEnum.valueByIndex(morningStart);
            if (classTypeEnum != null) {
                return classTypeEnum.getText();
            }
        }
        return "";
    }

    public void setMorningStartString(String morningStartString) {
        this.morningStartString = morningStartString;
    }

    public String getMorningEndString() {
        if (morningEnd != null) {
            AttendanceEnum classTypeEnum = AttendanceEnum.valueByIndex(morningEnd);
            if (classTypeEnum != null) {
                return classTypeEnum.getText();
            }
        }
        return "";
    }

    public void setMorningEndString(String morningEndString) {
        this.morningEndString = morningEndString;
    }

    public String getAfternoonStartString() {
        if (afternoonStart != null) {
            AttendanceEnum classTypeEnum = AttendanceEnum.valueByIndex(afternoonStart);
            if (classTypeEnum != null) {
                return classTypeEnum.getText();
            }
        }
        return "";
    }

    public void setAfternoonStartString(String afternoonStartString) {
        this.afternoonStartString = afternoonStartString;
    }

    public String getAfternoonEndString() {
        if (afternoonEnd != null) {
            AttendanceEnum classTypeEnum = AttendanceEnum.valueByIndex(afternoonEnd);
            if (classTypeEnum != null) {
                return classTypeEnum.getText();
            }
        }
        return "";
    }

    public void setAfternoonEndString(String afternoonEndString) {
        this.afternoonEndString = afternoonEndString;
    }

    public String getNightStartString() {
        if (nightStart != null) {
            AttendanceEnum classTypeEnum = AttendanceEnum.valueByIndex(nightStart);
            if (classTypeEnum != null) {
                return classTypeEnum.getText();
            }
        }
        return "";
    }

    public void setNightStartString(String nightStartString) {
        this.nightStartString = nightStartString;
    }

    public String getNightEndString() {
        if (nightEnd != null) {
            AttendanceEnum classTypeEnum = AttendanceEnum.valueByIndex(nightEnd);
            if (classTypeEnum != null) {
                return classTypeEnum.getText();
            }
        }
        return "";
    }

    public void setNightEndString(String nightEndString) {
        this.nightEndString = nightEndString;
    }
}
