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
@Table(name = "oa_teaching_plan")
public class OaTeachingPlan extends IdEntity {

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

    @Column(name = "plan_title")
    @Basic
    private String planTitle;

    @Column(name = "attachment_id")
    @Basic
    private Long attachmentId;

    @Column(name = "attachment_title")
    @Basic
    private String attachmentTitle;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "statue")
    private Integer statue;
    @Transient
    private String statueCn;

    @Column(name = "ratings")
    private Integer ratings;
    @Transient
    private String ratingsCn;

    @Column(name = "plan_level")
    private Integer planLevel;
    @Transient
    private String planLevelCn;

    @Column(name = "create_time_long")
    private Long createTimeLong;

    public String getStatueCn() {
        if (statue != null) {
            UploadEnum educationEnum = UploadEnum.valueByIndex(statue);
            if (educationEnum != null) {
                return educationEnum.getText();
            }
        }
        return "";
    }

    public void setStatueCn(String statueCn) {
        this.statueCn = statueCn;
    }

    public String getRatingsCn() {
        if (ratings != null) {
            RatingsEnum educationEnum = RatingsEnum.valueByIndex(ratings);
            if (educationEnum != null) {
                return educationEnum.getText();
            }
        }
        return "";
    }

    public void setRatingsCn(String ratingsCn) {
        this.ratingsCn = ratingsCn;
    }

    public String getPlanLevelCn() {
        if (planLevel != null) {
            PlanLevelEnum educationEnum = PlanLevelEnum.valueByIndex(planLevel);
            if (educationEnum != null) {
                return educationEnum.getText();
            }
        }
        return "";
    }

    public void setPlanLevelCn(String planLevelCn) {
        this.planLevelCn = planLevelCn;
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

    public String getPlanTitle() {
        return planTitle;
    }

    public void setPlanTitle(String planTitle) {
        this.planTitle = planTitle;
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

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public Integer getPlanLevel() {
        return planLevel;
    }

    public void setPlanLevel(Integer planLevel) {
        this.planLevel = planLevel;
    }

    public Long getCreateTimeLong() {
        return createTimeLong;
    }

    public void setCreateTimeLong(Long createTimeLong) {
        this.createTimeLong = createTimeLong;
    }
}
