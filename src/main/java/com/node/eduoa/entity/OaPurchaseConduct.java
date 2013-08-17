package com.node.eduoa.entity;

import com.node.eduoa.enums.PurchaseEnum;
import com.node.system.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

/**
 * User: linfeng at Administrator
 * Date: 13-7-28
 * Time: 下午1:36
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_purchase_conduct")
@Entity
public class OaPurchaseConduct extends IdEntity {

    private static final long serialVersionUID = -5489827159841554441L;

    @javax.persistence.Column(name = "purchase_id")
    private Long purchaseId;

    @javax.persistence.Column(name = "conduct_teacher_id")
    private Long conductTeacherId;

    @javax.persistence.Column(name = "conduct_teacher_name")
    private String conductTeacherName;
    @javax.persistence.Column(name = "conduct_organization_id")
    private Long conductOrganizationId;

    @javax.persistence.Column(name = "conduct_organization_name")
    private String conductOrganizationName;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "conduct_start_time")
    private Date conductStartTime;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "conduct_end_time")
    private Date conductEndTime;

    @javax.persistence.Column(name = "conduct_start_time_long")
    private Long conductStartTimeLong;

    @javax.persistence.Column(name = "conduct_end_time_long")
    private Long conductEndTimeLong;

    @javax.persistence.Column(name = "goods_id")
    private Long goodsId;

    @javax.persistence.Column(name = "goods_name")
    private String goodsName;

    @javax.persistence.Column(name = "goods_unit")
    private String goodsUnit;

    @javax.persistence.Column(name = "goods_count")
    private Integer goodsCount;

    @javax.persistence.Column(name = "leader_id")
    private Long leaderId;

    @javax.persistence.Column(name = "leader_name")
    private String leaderName;

    @javax.persistence.Column(name = "leader_position")
    private String leaderPosition;

    @javax.persistence.Column(name = "goods_unit_price")
    private Float goodsUnitPrice;

    @javax.persistence.Column(name = "price_sum")
    private Float priceSum;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "create_time")
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "commit_time")
    private Date commitTime;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "apply_time")
    private Date applyTime;

    @javax.persistence.Column(name = "apply_statue")
    private Integer applyStatue;

    @Transient
    private String applyStatueCn;

    @javax.persistence.Column(name = "statue")
    private Integer statue;

    @javax.persistence.Column(name = "remark")
    private String remark;

    public String getApplyStatueCn() {
        if (applyStatue != null) {
            PurchaseEnum classTypeEnum = PurchaseEnum.valueByIndex(applyStatue);
            if (classTypeEnum != null) {
                return classTypeEnum.getText();
            }
        }
        return applyStatueCn;
    }

    public void setApplyStatueCn(String applyStatueCn) {
        this.applyStatueCn = applyStatueCn;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getConductTeacherId() {
        return conductTeacherId;
    }

    public void setConductTeacherId(Long conductTeacherId) {
        this.conductTeacherId = conductTeacherId;
    }

    public String getConductTeacherName() {
        return conductTeacherName;
    }

    public void setConductTeacherName(String conductTeacherName) {
        this.conductTeacherName = conductTeacherName;
    }

    public Long getConductOrganizationId() {
        return conductOrganizationId;
    }

    public void setConductOrganizationId(Long conductOrganizationId) {
        this.conductOrganizationId = conductOrganizationId;
    }

    public String getConductOrganizationName() {
        return conductOrganizationName;
    }

    public void setConductOrganizationName(String conductOrganizationName) {
        this.conductOrganizationName = conductOrganizationName;
    }

    public Date getConductStartTime() {
        return conductStartTime;
    }

    public void setConductStartTime(Date conductStartTime) {
        this.conductStartTime = conductStartTime;
    }

    public Date getConductEndTime() {
        return conductEndTime;
    }

    public void setConductEndTime(Date conductEndTime) {
        this.conductEndTime = conductEndTime;
    }

    public Long getConductStartTimeLong() {
        return conductStartTimeLong;
    }

    public void setConductStartTimeLong(Long conductStartTimeLong) {
        this.conductStartTimeLong = conductStartTimeLong;
    }

    public Long getConductEndTimeLong() {
        return conductEndTimeLong;
    }

    public void setConductEndTimeLong(Long conductEndTimeLong) {
        this.conductEndTimeLong = conductEndTimeLong;
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

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
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

    public Float getGoodsUnitPrice() {
        return goodsUnitPrice;
    }

    public void setGoodsUnitPrice(Float goodsUnitPrice) {
        this.goodsUnitPrice = goodsUnitPrice;
    }

    public Float getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(Float priceSum) {
        this.priceSum = priceSum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
