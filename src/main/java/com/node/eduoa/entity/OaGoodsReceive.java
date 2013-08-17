package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * User: linfeng at Administrator
 * Date: 13-7-24
 * Time: 下午10:51
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "oa_goods_receive")
@Entity
public class OaGoodsReceive extends IdEntity {

    private static final long serialVersionUID = 3336563335657711972L;
    @Column(name = "goods_apply_id")
    private Long goodsApplyId;
    @Column(name = "recipients_id")
    private Long recipientsId;
    @Column(name = "recipients_name")
    private String recipientsName;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "recipients_time")
    private Date recipientsTime;
    @Column(name = "recipients_count")
    private Integer recipientsCount;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time")
    private Date createTime;

    public Long getGoodsApplyId() {
        return goodsApplyId;
    }

    public void setGoodsApplyId(Long goodsApplyId) {
        this.goodsApplyId = goodsApplyId;
    }

    public Long getRecipientsId() {
        return recipientsId;
    }

    public void setRecipientsId(Long recipientsId) {
        this.recipientsId = recipientsId;
    }

    public String getRecipientsName() {
        return recipientsName;
    }

    public void setRecipientsName(String recipientsName) {
        this.recipientsName = recipientsName;
    }

    public Date getRecipientsTime() {
        return recipientsTime;
    }

    public void setRecipientsTime(Date recipientsTime) {
        this.recipientsTime = recipientsTime;
    }

    public Integer getRecipientsCount() {
        return recipientsCount;
    }

    public void setRecipientsCount(Integer recipientsCount) {
        this.recipientsCount = recipientsCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
