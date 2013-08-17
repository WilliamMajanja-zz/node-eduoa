package com.node.eduoa.utils.model;

import com.node.eduoa.entity.OaGoodsApply;
import com.node.eduoa.entity.OaGoodsReceive;
import com.node.system.entity.main.Organization;

import java.io.Serializable;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-19
 * Time: 下午11:27
 * To change this template use File | Settings | File Templates.
 */
public class GoodsModel implements Serializable {
    private static final long serialVersionUID = -8857228341808949069L;

    private Leader leader;
    private TeacherModel teacherModel;
    private OaGoodsApply goodsApply;
    private OrganizationModel organizationModel;
    private OaGoodsReceive goodsReceive;

    public OaGoodsReceive getGoodsReceive() {
        return goodsReceive;
    }

    public void setGoodsReceive(OaGoodsReceive goodsReceive) {
        this.goodsReceive = goodsReceive;
    }

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

    public OaGoodsApply getGoodsApply() {
        return goodsApply;
    }

    public void setGoodsApply(OaGoodsApply goodsApply) {
        this.goodsApply = goodsApply;
    }

    public OrganizationModel getOrganizationModel() {
        return organizationModel;
    }

    public void setOrganizationModel(OrganizationModel organizationModel) {
        this.organizationModel = organizationModel;
    }
}
