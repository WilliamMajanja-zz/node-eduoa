package com.node.eduoa.utils.model;

import com.node.eduoa.entity.OaPurchaseApply;
import com.node.eduoa.entity.OaPurchaseConduct;

import java.io.Serializable;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-19
 * Time: 下午11:27
 * To change this template use File | Settings | File Templates.
 */
public class ConductModel implements Serializable {

    private static final long serialVersionUID = 2321915936088861275L;

    private Leader leader;
    private TeacherModel teacherModel;
    private OaPurchaseConduct purchaseConduct;
    private OrganizationModel organizationModel;

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

    public TeacherModel getTeacherModel() {
        return teacherModel;
    }

    public void setTeacherModel(TeacherModel teacherModel) {
        this.teacherModel = teacherModel;
    }

    public OaPurchaseConduct getPurchaseConduct() {
        return purchaseConduct;
    }

    public void setPurchaseConduct(OaPurchaseConduct purchaseConduct) {
        this.purchaseConduct = purchaseConduct;
    }

    public OrganizationModel getOrganizationModel() {
        return organizationModel;
    }

    public void setOrganizationModel(OrganizationModel organizationModel) {
        this.organizationModel = organizationModel;
    }
}
