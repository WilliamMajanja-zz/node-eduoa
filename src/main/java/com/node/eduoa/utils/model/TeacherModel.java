package com.node.eduoa.utils.model;

import java.io.Serializable;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-19
 * Time: 下午11:34
 * To change this template use File | Settings | File Templates.
 */
public class TeacherModel implements Serializable {

    private static final long serialVersionUID = -8267871532044582424L;

    private Long applyTeacherId;
    private String applyTeacherName;

    public Long getApplyTeacherId() {
        return applyTeacherId;
    }

    public void setApplyTeacherId(Long applyTeacherId) {
        this.applyTeacherId = applyTeacherId;
    }

    public String getApplyTeacherName() {
        return applyTeacherName;
    }

    public void setApplyTeacherName(String applyTeacherName) {
        this.applyTeacherName = applyTeacherName;
    }
}
