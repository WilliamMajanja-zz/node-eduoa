package com.node.eduoa.utils.model;

import com.node.eduoa.entity.OaLeavePermit;

import java.io.Serializable;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-16
 * Time: 下午9:53
 * To change this template use File | Settings | File Templates.
 */
public class LeaveModel implements Serializable {
    private static final long serialVersionUID = 4908019034547393591L;

    private Leader leader;
    private OaLeavePermit leavePermit;

    public OaLeavePermit getLeavePermit() {
        return leavePermit;
    }

    public void setLeavePermit(OaLeavePermit leavePermit) {
        this.leavePermit = leavePermit;
    }

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }

}
