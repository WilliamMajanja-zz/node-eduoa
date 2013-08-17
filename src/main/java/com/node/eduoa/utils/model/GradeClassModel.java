package com.node.eduoa.utils.model;

import com.node.eduoa.entity.OaContact;
import com.node.eduoa.entity.OaStudent;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-13
 * Time: 下午11:07
 * To change this template use File | Settings | File Templates.
 */
public class GradeClassModel implements Serializable {

    private static final long serialVersionUID = -6023818817037127199L;

    private Long classId;
    private String className;
    private Long gradeId;
    private String gradeName;
    private Date startTime;
    private Date endTime;

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
