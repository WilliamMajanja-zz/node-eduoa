package com.node.eduoa.utils.model;

import java.io.Serializable;

/**
 *
 * User: Administrator
 * Date: 13-7-24
 * Time: 下午3:32
 * To change this template use File | Settings | File Templates.
 */
public class AttendanceModel implements Serializable {
    private static final long serialVersionUID = 4261615373349960461L;

    private String startTime;//考勤日期
    private String endTime;//考勤日期
    private Long teacherId;
    private String teacherName;
    private Long organizationId;
    private String organizationName;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
}
