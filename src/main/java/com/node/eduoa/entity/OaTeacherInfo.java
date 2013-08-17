package com.node.eduoa.entity;

import com.node.eduoa.enums.EducationEnum;
import com.node.system.entity.IdEntity;
import com.node.system.entity.main.Organization;
import com.node.system.entity.main.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@Table(name = "oa_teacher_info")
@Entity
public class OaTeacherInfo extends IdEntity {

    private static final long serialVersionUID = 4333599216356731675L;
    @Column(name = "teacher_name")
    private String teacherName;
    @Column(name = "teacher_number")
    private String teacherNumber;
    @Column(name = "id_number")
    private String idNumber;
    @Column(name = "is_teacher")
    private Integer teacher;
    @Column(name = "phone")
    private String phone;
    @Column(name = "head_teacher")
    private Integer headTeacher;
    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    private Integer gender;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "political_landscape")
    private Integer politicalLandscape;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "join_time")
    private Date joinTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "working_time")
    private Date workingTime;
    @Column(name = "original_education")
    private Integer originalEducation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "original_education_time")
    private Date originalEducationTime;
    @Column(name = "special")
    private String special;
    @Column(name = "new_education")
    private Integer newEducation;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "new_education_time")
    private Date newEducationTime;

    @Column(name = "certificates_type")
    private Integer certificatesType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "certificates_time")
    private Date certificatesTime;

    @Column(name = "establishment")
    private Integer establishment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time")
    private Date updateTime;

    @OneToMany(mappedBy = "oaTeacherInfoByTeacherId")
    private List<OaCertificate> oaCertificatesById;
    @OneToMany(mappedBy = "oaTeacherInfoByTeacherId")
    private List<OaClassTeacher> oaClassTeachersById;
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private OaSubject oaSubjectBySubjectId;
    @ManyToOne
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Organization securityOrganizationByOrgId;
    @ManyToOne
    @JoinColumn(name = "grade_id", referencedColumnName = "id")
    private OaGrade oaGradeByGradeId;
    @ManyToOne
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private OaPosition oaPositionByPositionId;

    @OneToOne(mappedBy = "teacherInfo", cascade = {CascadeType.PERSIST})
    private User user;

    @Transient
    private Integer workedYear;
    @Transient
    private String originalEducationName;
    @Transient
    private String newEducationName;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherNumber() {
        return teacherNumber;
    }

    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Integer getTeacher() {
        return teacher;
    }

    public void setTeacher(Integer teacher) {
        this.teacher = teacher;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getHeadTeacher() {
        return headTeacher;
    }

    public void setHeadTeacher(Integer headTeacher) {
        this.headTeacher = headTeacher;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getPoliticalLandscape() {
        return politicalLandscape;
    }

    public void setPoliticalLandscape(Integer politicalLandscape) {
        this.politicalLandscape = politicalLandscape;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Date getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(Date workingTime) {
        this.workingTime = workingTime;
    }

    public Integer getOriginalEducation() {
        return originalEducation;
    }

    public void setOriginalEducation(Integer originalEducation) {
        this.originalEducation = originalEducation;
    }

    public Date getOriginalEducationTime() {
        return originalEducationTime;
    }

    public void setOriginalEducationTime(Date originalEducationTime) {
        this.originalEducationTime = originalEducationTime;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public Integer getNewEducation() {
        return newEducation;
    }

    public void setNewEducation(Integer newEducation) {
        this.newEducation = newEducation;
    }

    public Date getNewEducationTime() {
        return newEducationTime;
    }

    public void setNewEducationTime(Date newEducationTime) {
        this.newEducationTime = newEducationTime;
    }

    public Date getCertificatesTime() {
        return certificatesTime;
    }

    public void setCertificatesTime(Date certificatesTime) {
        this.certificatesTime = certificatesTime;
    }

    public Integer getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Integer establishment) {
        this.establishment = establishment;
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

    public List<OaCertificate> getOaCertificatesById() {
        return oaCertificatesById;
    }

    public void setOaCertificatesById(List<OaCertificate> oaCertificatesById) {
        this.oaCertificatesById = oaCertificatesById;
    }

    public List<OaClassTeacher> getOaClassTeachersById() {
        return oaClassTeachersById;
    }

    public void setOaClassTeachersById(List<OaClassTeacher> oaClassTeachersById) {
        this.oaClassTeachersById = oaClassTeachersById;
    }

    public OaSubject getOaSubjectBySubjectId() {
        return oaSubjectBySubjectId;
    }

    public void setOaSubjectBySubjectId(OaSubject oaSubjectBySubjectId) {
        this.oaSubjectBySubjectId = oaSubjectBySubjectId;
    }

    public Organization getSecurityOrganizationByOrgId() {
        return securityOrganizationByOrgId;
    }

    public void setSecurityOrganizationByOrgId(Organization securityOrganizationByOrgId) {
        this.securityOrganizationByOrgId = securityOrganizationByOrgId;
    }

    public OaGrade getOaGradeByGradeId() {
        return oaGradeByGradeId;
    }

    public void setOaGradeByGradeId(OaGrade oaGradeByGradeId) {
        this.oaGradeByGradeId = oaGradeByGradeId;
    }

    public OaPosition getOaPositionByPositionId() {
        return oaPositionByPositionId;
    }

    public void setOaPositionByPositionId(OaPosition oaPositionByPositionId) {
        this.oaPositionByPositionId = oaPositionByPositionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCertificatesType() {
        return certificatesType;
    }

    public void setCertificatesType(Integer certificatesType) {
        this.certificatesType = certificatesType;
    }

    public Integer getWorkedYear() {
        if (joinTime != null) {
            Calendar currentCalendar = Calendar.getInstance();
            Integer currentYear = currentCalendar.get(Calendar.YEAR);
            Calendar birthdayCalendar = Calendar.getInstance();
            birthdayCalendar.setTime(joinTime);
            Integer birthdayYear = birthdayCalendar.get(Calendar.YEAR);
            workedYear = currentYear - birthdayYear;
        }
        return workedYear;
    }

    public void setWorkedYear(Integer workedYear) {
        this.workedYear = workedYear;
    }

    public String getNewEducationName() {
        if (newEducation != null) {
            EducationEnum educationEnum = EducationEnum.valueByIndex(newEducation);
            if (educationEnum != null) {
                return educationEnum.getText();
            }
        }
        return "";
    }

    public void setNewEducationName(String newEducationName) {
        this.newEducationName = newEducationName;
    }

    public String getOriginalEducationName() {
        if (originalEducation != null) {
            EducationEnum educationEnum = EducationEnum.valueByIndex(originalEducation);
            if (educationEnum != null) {
                return educationEnum.getText();
            }
        }
        return "";
    }

    public void setOriginalEducationName(String originalEducationName) {
        this.originalEducationName = originalEducationName;
    }
}
