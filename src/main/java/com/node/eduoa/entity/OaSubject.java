package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_subject")
@Entity
public class OaSubject extends IdEntity {


    private static final long serialVersionUID = -214326213784001389L;
    @javax.persistence.Column(name = "subject_name")
    private String subjectName;
    @javax.persistence.Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "oaSubjectBySubjectId")
    private List<OaTeacherInfo> oaTeacherInfosById;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OaTeacherInfo> getOaTeacherInfosById() {
        return oaTeacherInfosById;
    }

    public void setOaTeacherInfosById(List<OaTeacherInfo> oaTeacherInfosById) {
        this.oaTeacherInfosById = oaTeacherInfosById;
    }

}
