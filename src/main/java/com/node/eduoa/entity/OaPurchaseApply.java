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
@Table(name = "oa_purchase_apply")
@Entity
public class OaPurchaseApply extends IdEntity {

    private static final long serialVersionUID = 1129436417848821873L;

    @Column(name = "leader_id")
    private Long leaderId;
    @Column(name = "leader_name")
    private String leaderName;
    @Column(name = "leader_position")
    private String leaderPosition;
    @Column(name = "goods_id")
    private Long goodsId;
    @Column(name = "goods_name")
    private String goodsName;
    @Column(name = "goods_unit")
    private String goodsUnit;
    @Column(name = "goods_count")
    private Integer goodsCount;
    @Column(name = "apply_teacher_id")
    private Long applyTeacherId;
    @Column(name = "apply_teacher_name")
    private String applyTeacherName;
    @Column(name = "apply_organization_id")
    private Long applyOrganizationId;
    @Column(name = "apply_organization_name")
    private String applyOrganizationName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "apply_time")
    private Date applyTime;
    @Column(name = "apply_time_long")
    private Long applyTimeLong;
    @Column(name = "apply_statue")
    private Integer applyStatue;
    @Column(name = "statue")
    private Integer statue;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "commit_time")
    private Date commitTime;
    @Column(name = "commit_time_long")
    private Long commitTimeLong;
    @Column(name = "remark")
    private String remark;
    @Column(name = "purchase")
    private Integer purchase;

    @Transient
    private String applyStatueCn;

    public Integer getPurchase() {
        return purchase;
    }

    public void setPurchase(Integer purchase) {
        this.purchase = purchase;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
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

    public Long getApplyTimeLong() {
        return applyTimeLong;
    }

    public void setApplyTimeLong(Long applyTimeLong) {
        this.applyTimeLong = applyTimeLong;
    }

    public Long getCommitTimeLong() {
        return commitTimeLong;
    }

    public void setCommitTimeLong(Long commitTimeLong) {
        this.commitTimeLong = commitTimeLong;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
