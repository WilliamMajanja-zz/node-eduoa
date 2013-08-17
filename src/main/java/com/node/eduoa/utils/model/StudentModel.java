package com.node.eduoa.utils.model;

import com.node.eduoa.entity.OaContact;
import com.node.eduoa.entity.OaStudent;

import java.io.Serializable;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-14
 * Time: 上午8:31
 * To change this template use File | Settings | File Templates.
 */
public class StudentModel implements Serializable {
    private static final long serialVersionUID = -2358539544538371627L;

    private OaStudent student;
    private OaContact contact;
    private GradeClassModel classModel;

    public GradeClassModel getClassModel() {
        return classModel;
    }

    public void setClassModel(GradeClassModel classModel) {
        this.classModel = classModel;
    }

    public OaContact getContact() {
        return contact;
    }

    public void setContact(OaContact contact) {
        this.contact = contact;
    }

    public OaStudent getStudent() {
        return student;
    }

    public void setStudent(OaStudent student) {
        this.student = student;
    }
}
