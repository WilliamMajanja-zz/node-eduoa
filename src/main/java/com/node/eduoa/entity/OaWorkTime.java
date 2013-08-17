package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.Column;
import java.util.Date;

/**
 * User: linfeng
 * Date: 13-7-20
 * Time: 下午3:50
 * To change this template use File | Settings | File Templates.
 */
public class OaWorkTime extends IdEntity {
    private static final long serialVersionUID = 5919721507780353738L;

    @Column(name = "name")
    private String name;

    @Column(name = "commuting")
    private Integer commuting;

    @Column(name = "work_time")
    private String workTime;

    @Column(name = "work_time_long")
    private Long workTimeLong;

    @Column(name = "time_order")
    private Integer timeOrder;

    @Column(name = "create_time")
    private Date createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCommuting() {
        return commuting;
    }

    public void setCommuting(Integer commuting) {
        this.commuting = commuting;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public Long getWorkTimeLong() {
        return workTimeLong;
    }

    public void setWorkTimeLong(Long workTimeLong) {
        this.workTimeLong = workTimeLong;
    }

    public Integer getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(Integer timeOrder) {
        this.timeOrder = timeOrder;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
