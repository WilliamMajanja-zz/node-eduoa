package com.node.eduoa.entity;

import com.node.system.entity.IdEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 上午9:57
 * To change this template use File | Settings | File Templates.
 */
@javax.persistence.Table(name = "sys_type_rules")
@Entity
public class SysTypeRules extends IdEntity {

    private static final long serialVersionUID = -7102957171005074947L;
    @javax.persistence.Column(name = "role_type")
    private Integer roleType;
    @javax.persistence.Column(name = "role_name")
    private String roleName;
    @javax.persistence.Column(name = "content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @javax.persistence.Column(name = "create_time")
    private Date createTime;
    @ManyToOne
    @javax.persistence.JoinColumn(name = "applay_id", referencedColumnName = "id")
    private OaAppraisal oaAppraisalByApplayId;

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public OaAppraisal getOaAppraisalByApplayId() {
        return oaAppraisalByApplayId;
    }

    public void setOaAppraisalByApplayId(OaAppraisal oaAppraisalByApplayId) {
        this.oaAppraisalByApplayId = oaAppraisalByApplayId;
    }

}
