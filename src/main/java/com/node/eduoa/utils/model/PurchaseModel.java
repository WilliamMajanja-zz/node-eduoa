package com.node.eduoa.utils.model;

import com.node.eduoa.entity.OaGoodsApply;
import com.node.eduoa.entity.OaGoodsReceive;
import com.node.eduoa.entity.OaPurchaseApply;

import java.io.Serializable;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-19
 * Time: 下午11:27
 * To change this template use File | Settings | File Templates.
 */
public class PurchaseModel implements Serializable {

    private static final long serialVersionUID = 2321915936088861275L;

    private Leader leader;
    private TeacherModel teacherModel;
    private OaPurchaseApply purchaseApply;
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

    public OaPurchaseApply getPurchaseApply() {
        return purchaseApply;
    }

    public void setPurchaseApply(OaPurchaseApply purchaseApply) {
        this.purchaseApply = purchaseApply;
    }

    public OrganizationModel getOrganizationModel() {
        return organizationModel;
    }

    public void setOrganizationModel(OrganizationModel organizationModel) {
        this.organizationModel = organizationModel;
    }
}
