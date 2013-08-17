package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;

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
@javax.persistence.Table(name = "oa_student_class")
@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class OaStudentClass extends IdEntity {

    private static final long serialVersionUID = 4859515590347236233L;

    @ManyToOne
    @javax.persistence.JoinColumn(name = "class_id", referencedColumnName = "id", nullable = false)
    private OaClass oaClassByClassId;
    @ManyToOne
    @javax.persistence.JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private OaStudent oaStudentByStudentId;

    public OaStudentClass() {
    }

    public OaStudentClass(OaClass oaClassByClassId, OaStudent oaStudentByStudentId, Date startTime, Date endTime, Integer currentYear) {
        this.oaClassByClassId = oaClassByClassId;
        this.oaStudentByStudentId = oaStudentByStudentId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.currentYear = currentYear;
    }

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "current_year")
    private Integer currentYear;

    public Integer getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    public OaClass getOaClassByClassId() {
        return oaClassByClassId;
    }

    public void setOaClassByClassId(OaClass oaClassByClassId) {
        this.oaClassByClassId = oaClassByClassId;
    }

    @JsonManagedReference
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
