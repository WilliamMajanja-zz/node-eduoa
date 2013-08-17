package com.node.eduoa.entity;

import com.node.eduoa.enums.ApplyStatusEnum;
import com.node.system.entity.IdEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * User: linfeng at Administrator
 * Date: 13-7-14
 * Time: 下午8:33
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_goods_apply")
@Entity
public class OaGoodsApply extends IdEntity {

    private static final long serialVersionUID = -1685386458670939641L;

    @javax.persistence.Column(name = "leader_id")
    private Long leaderId;
    @javax.persistence.Column(name = "leader_name")
    private String leaderName;
    @javax.persistence.Column(name = "leader_position")
    private String leaderPosition;
    @javax.persistence.Column(name = "goods_id")
    private Long goodsId;
    @javax.persistence.Column(name = "goods_name")
    private String goodsName;
    @javax.persistence.Column(name = "goods_unit")
    private String goodsUnit;
    @javax.persistence.Column(name = "goods_count")
    private Integer goodsCount;
    @javax.persistence.Column(name = "apply_teacher_id")
    private Long applyTeacherId;
    @javax.persistence.Column(name = "apply_teacher_name")
    private String applyTeacherName;
    @javax.persistence.Column(name = "apply_organization_id")
    private Long applyOrganizationId;
    @javax.persistence.Column(name = "apply_organization_name")
    private String applyOrganizationName;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "apply_time")
    private Date applyTime;
    @javax.persistence.Column(name = "apply_statue")
    private Integer applyStatue;
    @javax.persistence.Column(name = "statue")
    private Integer statue;

    @javax.persistence.Column(name = "goods_statue")
    private Integer goodsStatue;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sick_time")
    private Date sickTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "commit_time")
    private Date commitTime;

    @Column(name = "lave")
    private Integer lave;

    @Transient
    private String applyStatueCn;

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public Integer getGoodsStatue() {
        return goodsStatue;
    }

    public void setGoodsStatue(Integer goodsStatue) {
        this.goodsStatue = goodsStatue;
    }

    public Date getSickTime() {
        return sickTime;
    }

    public void setSickTime(Date sickTime) {
        this.sickTime = sickTime;
    }

    public String getApplyStatueCn() {
        if (applyStatue != null) {
            ApplyStatusEnum classTypeEnum = ApplyStatusEnum.valueByIndex(applyStatue);
            if (classTypeEnum != null) {
                return classTypeEnum.getText();
            }
        }
        return applyStatueCn;
    }

    public void setApplyStatueCn(String applyStatueCn) {
        this.applyStatueCn = applyStatueCn;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderPosition() {
        return leaderPosition;
    }

    public void setLeaderPosition(String leaderPosition) {
        this.leaderPosition = leaderPosition;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

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

    public Long getApplyOrganizationId() {
        return applyOrganizationId;
    }

    public void setApplyOrganizationId(Long applyOrganizationId) {
        this.applyOrganizationId = applyOrganizationId;
    }

    public String getApplyOrganizationName() {
        return applyOrganizationName;
    }

    public void setApplyOrganizationName(String applyOrganizationName) {
        this.applyOrganizationName = applyOrganizationName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getApplyStatue() {
        return applyStatue;
    }

    public void setApplyStatue(Integer applyStatue) {
        this.applyStatue = applyStatue;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Integer getLave() {
        return lave;
    }

    public void setLave(Integer lave) {
        this.lave = lave;
    }
}
