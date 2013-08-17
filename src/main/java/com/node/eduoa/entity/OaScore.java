package com.node.eduoa.entity;

import com.node.eduoa.enums.ExamsEnum;
import com.node.system.entity.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-31
 * Time: 下午10:07
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_score")
@Entity
public class OaScore extends IdEntity {

    private static final long serialVersionUID = -8590895188359907191L;

    @Temporal(TemporalType.DATE)
    @Column(name = "exam_date")
    private Date examDate;

    @Column(name = "exam_type")
    private Integer examType;

    @Column(name = "exam_date_time")
    private Long examDateTime;

    @Column(name = "student_number")
    private Integer studentNumber;

    @Column(name = "exam_no")
    private String examNo;

    @Column(name = "grade_name")
    private String gradeName;

    @Column(name = "grade_id")
    private Long gradeId;

    @Column(name = "class_id")
    private Long classId;

    @Column(name = "class_name")
    private String className;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "score")
    private Float score;

    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "teacher_name")
    private String teacherName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "remark")
    private String remark;

    @Transient
    private String examTypeName;

    public String getExamTypeName() {
        if (examType != null) {
            ExamsEnum genderEnum = ExamsEnum.valueByIndex(examType);
            if (genderEnum != null) {
                return genderEnum.getText();
            }
        }
        return "";
    }

    public void setExamTypeName(String examTypeName) {
        this.examTypeName = examTypeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getExamType() {
        return examType;
    }

    public void setExamType(Integer examType) {
        this.examType = examType;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Long getExamDateTime() {
        return examDateTime;
    }

    public void setExamDateTime(Long examDateTime) {
        this.examDateTime = examDateTime;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getExamNo() {
        return examNo;
    }

    public void setExamNo(String examNo) {
        this.examNo = examNo;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
