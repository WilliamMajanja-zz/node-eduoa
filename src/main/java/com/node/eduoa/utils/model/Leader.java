package com.node.eduoa.utils.model;

import java.io.Serializable;

public class Leader implements Serializable {

        private Long leaderId;
        private String leaderName;
        private String leaderPosition;

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
    }