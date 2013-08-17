package com.node.eduoa.entity;

import com.node.eduoa.enums.GenderEnum;
import com.node.system.entity.IdEntity;
import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_student")
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class OaStudent extends IdEntity {

    private static final long serialVersionUID = 8252084003837390544L;
    @javax.persistence.Column(name = "student_name")
    private String studentName;
    @javax.persistence.Column(name = "id_number")
    private String idNumber;
    @javax.persistence.Column(name = "gender")
    private Integer gender;
    @javax.persistence.Column(name = "student_number")
    private Integer studentNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "birthday")
    private Date birthday;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "update_time")
    private Date updateTime;
    @javax.persistence.Column(name = "operator_id")
    private Long operatorId;
    @OneToMany(mappedBy = "oaStudentByStudentId", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    private List<OaContact> oaContactsById;
    @OneToMany(mappedBy = "oaStudentByStudentId", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    private List<OaStudentClass> oaStudentClassesById;
    @OneToMany(mappedBy = "oaStudentByStudentId", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    private List<OaStudentGrade> oaStudentGradesById;

    @Column(name = "grade_name")
    private String gradeName;

    @Column(name = "class_name")
    private String className;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "current_year")
    private Integer currentYear;

    @Transient
    private String genderName;
    @Transient
    private Long gradeId;
    @Transient
    private Long classId;

    @JsonSerialize
    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    @JsonSerialize
    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    @JsonSerialize
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @JsonSerialize
    public Integer getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    @JsonSerialize
    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    @JsonSerialize
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @JsonSerialize
    public String getGenderName() {
        if (getGender() != null) {
            GenderEnum genderEnum = GenderEnum.valueByIndex(getGender());
            if (genderEnum != null) {
                return genderEnum.getText();
            }
        }
        return "";
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    @JsonSerialize
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @JsonSerialize
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @JsonSerialize
    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @JsonSerialize
    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }

    @JsonIgnore
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonIgnore
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonIgnore
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @JsonIgnore
    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    @JsonIgnore
    public List<OaContact> getOaContactsById() {
        return oaContactsById;
    }

    public void setOaContactsById(List<OaContact> oaContactsById) {
        this.oaContactsById = oaContactsById;
    }

    @JsonIgnore
    public List<OaStudentClass> getOaStudentClassesById() {
        return oaStudentClassesById;
    }

    public void setOaStudentClassesById(List<OaStudentClass> oaStudentClassesById) {
        this.oaStudentClassesById = oaStudentClassesById;
    }

    @JsonIgnore
    public List<OaStudentGrade> getOaStudentGradesById() {
        return oaStudentGradesById;
    }

    public void setOaStudentGradesById(List<OaStudentGrade> oaStudentGradesById) {
        this.oaStudentGradesById = oaStudentGradesById;
    }

}
