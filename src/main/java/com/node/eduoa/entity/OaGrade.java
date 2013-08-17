package com.node.eduoa.entity;

import com.node.eduoa.enums.SemesterEnum;
import com.node.system.entity.IdEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_grade")
@Entity
public class OaGrade extends IdEntity {

    private static final long serialVersionUID = 5145887823277072783L;
    @javax.persistence.Column(name = "grade_name")
    private String gradeName;
    @javax.persistence.Column(name = "description")
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "create_time")
    private Date createTime;
    @javax.persistence.Column(name = "current_year")
    private Integer currentYear;
    @javax.persistence.Column(name = "current_half")
    private Integer currentHalf;
    @OneToMany(mappedBy = "oaGradeByGradeId")
    private List<OaClass> oaClassesById;
    @OneToMany(mappedBy = "oaGradeByGradeId")
    private List<OaStudentGrade> oaStudentGradesById;
    @OneToMany(mappedBy = "oaGradeByGradeId")
    private List<OaTeacherInfo> oaTeacherInfosById;
    @Transient
    private String semester;
    @Transient
    private List<OaGrade> children = new ArrayList<OaGrade>(0);

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

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

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    public Integer getCurrentHalf() {
        return currentHalf;
    }

    public void setCurrentHalf(Integer currentHalf) {
        this.currentHalf = currentHalf;
    }

    public List<OaClass> getOaClassesById() {
        return oaClassesById;
    }

    public void setOaClassesById(List<OaClass> oaClassesById) {
        this.oaClassesById = oaClassesById;
    }

    public List<OaStudentGrade> getOaStudentGradesById() {
        return oaStudentGradesById;
    }

    public void setOaStudentGradesById(List<OaStudentGrade> oaStudentGradesById) {
        this.oaStudentGradesById = oaStudentGradesById;
    }

    public List<OaTeacherInfo> getOaTeacherInfosById() {
        return oaTeacherInfosById;
    }

    public void setOaTeacherInfosById(List<OaTeacherInfo> oaTeacherInfosById) {
        this.oaTeacherInfosById = oaTeacherInfosById;
    }

    public String getSemester() {
        if (getCurrentHalf() != null) {
            SemesterEnum semesterEnum = SemesterEnum.valueByIndex(getCurrentHalf());
            if (semesterEnum != null) {
                return semesterEnum.getText();
            }
        }
        return "";
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<OaGrade> getChildren() {
        if (oaClassesById != null && !oaClassesById.isEmpty()) {

        }
        return children;
    }

    public void setChildren(List<OaGrade> children) {
        this.children = children;
    }
}
