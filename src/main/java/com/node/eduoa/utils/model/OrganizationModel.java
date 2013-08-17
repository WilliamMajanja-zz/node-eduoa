package com.node.eduoa.utils.model;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-19
 * Time: 下午11:36
 * To change this template use File | Settings | File Templates.
 */
public class OrganizationModel implements Serializable {
    private static final long serialVersionUID = -4911908969800164087L;

    private Long applyOrganizationId;
    private String applyOrganizationName;

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
}
