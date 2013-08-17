package com.node.eduoa.entity;

import com.node.eduoa.enums.ClassTypeEnum;
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
@Table(name = "oa_class")
@Entity
public class OaClass extends IdEntity {

    private static final long serialVersionUID = 481228190821770458L;
    @Column(name = "class_name")
    private String className;
    @Column(name = "category")
    private Integer category;
    @Column(name = "description")
    private String description;
    @Column(name = "number_limit")
    private Integer numberLimit;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "create_user_id")
    private Long createUserId;
    @Column(name = "update_user_id")
    private Long updateUserId;
    @ManyToOne
    @JoinColumn(name = "grade_id", referencedColumnName = "id")
    private OaGrade oaGradeByGradeId;
    @OneToMany(mappedBy = "oaClassByClassId")
    private List<OaClassTeacher> oaClassTeachersById;
    @OneToMany(mappedBy = "oaClassByClassId")
    private List<OaStudentClass> oaStudentClassesById;

    @Transient
    private String categoryName;


    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberLimit() {
        return numberLimit;
    }

    public void setNumberLimit(Integer numberLimit) {
        this.numberLimit = numberLimit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public OaGrade getOaGradeByGradeId() {
        return oaGradeByGradeId;
    }

    public void setOaGradeByGradeId(OaGrade oaGradeByGradeId) {
        this.oaGradeByGradeId = oaGradeByGradeId;
    }

    public List<OaClassTeacher> getOaClassTeachersById() {
        return oaClassTeachersById;
    }

    public void setOaClassTeachersById(List<OaClassTeacher> oaClassTeachersById) {
        this.oaClassTeachersById = oaClassTeachersById;
    }

    public List<OaStudentClass> getOaStudentClassesById() {
        return oaStudentClassesById;
    }

    public void setOaStudentClassesById(List<OaStudentClass> oaStudentClassesById) {
        this.oaStudentClassesById = oaStudentClassesById;
    }

    public String getCategoryName() {
        if (getCategory() != null) {
            ClassTypeEnum classTypeEnum = ClassTypeEnum.valueByIndex(getCategory());
            if (classTypeEnum != null) {
                return classTypeEnum.getText();
            }
        }
        return "";
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
