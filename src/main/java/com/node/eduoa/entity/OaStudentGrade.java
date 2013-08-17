package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_student_grade")
@Entity
public class OaStudentGrade extends IdEntity {

    private static final long serialVersionUID = 536487947039848805L;
    @ManyToOne
    @javax.persistence.JoinColumn(name = "grade_id", referencedColumnName = "id", nullable = false)
    private OaGrade oaGradeByGradeId;
    @ManyToOne
    @javax.persistence.JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private OaStudent oaStudentByStudentId;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "current_year")
    private Integer currentYear;

    public OaStudentGrade() {
    }

    public OaStudentGrade(OaGrade oaGradeByGradeId, OaStudent oaStudentByStudentId, Date startTime, Date endTime, Integer currentYear) {
        this.oaGradeByGradeId = oaGradeByGradeId;
        this.oaStudentByStudentId = oaStudentByStudentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.currentYear = currentYear;
    }

    public Integer getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    public OaGrade getOaGradeByGradeId() {
        return oaGradeByGradeId;
    }

    public void setOaGradeByGradeId(OaGrade oaGradeByGradeId) {
        this.oaGradeByGradeId = oaGradeByGradeId;
    }

    public OaStudent getOaStudentByStudentId() {
        return oaStudentByStudentId;
    }

    public void setOaStudentByStudentId(OaStudent oaStudentByStudentId) {
        this.oaStudentByStudentId = oaStudentByStudentId;
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
}
