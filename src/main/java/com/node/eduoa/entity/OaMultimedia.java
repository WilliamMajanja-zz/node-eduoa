package com.node.eduoa.entity;

import com.node.eduoa.enums.PlanLevelEnum;
import com.node.eduoa.enums.RatingsEnum;
import com.node.eduoa.enums.UploadEnum;
import com.node.system.entity.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "oa_multimedia")
public class OaMultimedia extends IdEntity {

    private static final long serialVersionUID = 1523502380173939582L;

    @Column(name = "grade_id")
    @Basic
    private Long gradeId;

    @Column(name = "grade_name")
    @Basic
    private String gradeName;

    @Column(name = "subject_id")
    @Basic
    private Long subjectId;

    @Column(name = "subject_name")
    @Basic
    private String subjectName;

    @Column(name = "teacher_id")
    @Basic
    private Long teacherId;

    @Column(name = "teacher_name")
    @Basic
    private String teacherName;

    @Column(name = "title")
    @Basic
    private String title;

    @Column(name = "attachment_id")
    @Basic
    private Long attachmentId;

    @Column(name = "attachment_title")
    @Basic
    private String attachmentTitle;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "upload_time")
    private Date uploadTime;

    @Column(name = "upload_time_long")
    private Long uploadTimeLong;

    @Column(name = "use_count")
    private Integer useCount;

    @Column(name = "need_score")
    private Integer needScore;

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }

    public Integer getNeedScore() {
        return needScore;
    }

    public void setNeedScore(Integer needScore) {
        this.needScore = needScore;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentTitle() {
        return attachmentTitle;
    }

    public void setAttachmentTitle(String attachmentTitle) {
        this.attachmentTitle = attachmentTitle;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getUploadTimeLong() {
        return uploadTimeLong;
    }

    public void setUploadTimeLong(Long uploadTimeLong) {
        this.uploadTimeLong = uploadTimeLong;
    }
}
