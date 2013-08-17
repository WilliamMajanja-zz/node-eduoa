package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_class_teacher")
@Entity
public class OaClassTeacher extends IdEntity {

    private static final long serialVersionUID = -7753889417673969057L;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "start_time")
    private Date startTime;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "end_time")
    private Date endTime;

    @javax.persistence.Column(name = "head_teacher")
    private Integer headTeacher;
    @ManyToOne
    @javax.persistence.JoinColumn(name = "class_id", referencedColumnName = "id")
    private OaClass oaClassByClassId;
    @ManyToOne
    @javax.persistence.JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private OaTeacherInfo oaTeacherInfoByTeacherId;

    public OaClassTeacher(Integer headTeacher) {
        this.headTeacher = headTeacher;
    }

    public OaClassTeacher() {
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

    public Integer getHeadTeacher() {
        return headTeacher;
    }

    public void setHeadTeacher(Integer headTeacher) {
        this.headTeacher = headTeacher;
    }

    public OaClass getOaClassByClassId() {
        return oaClassByClassId;
    }

    public void setOaClassByClassId(OaClass oaClassByClassId) {
        this.oaClassByClassId = oaClassByClassId;
    }

    public OaTeacherInfo getOaTeacherInfoByTeacherId() {
        return oaTeacherInfoByTeacherId;
    }

    public void setOaTeacherInfoByTeacherId(OaTeacherInfo oaTeacherInfoByTeacherId) {
        this.oaTeacherInfoByTeacherId = oaTeacherInfoByTeacherId;
    }

}
