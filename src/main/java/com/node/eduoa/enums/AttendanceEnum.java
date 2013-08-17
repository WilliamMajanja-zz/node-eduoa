package com.node.eduoa.enums;

/**
 * 考勤状态枚举
 * User: linfeng at Administrator
 * Date: 13-7-21
 * Time: 下午7:22
 * To change this template use File | Settings | File Templates.
 */
public enum AttendanceEnum {

    LeaveEarly("早退"), Normal("已签到"), ArriveLate("迟到");

    private String text;

    AttendanceEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal();
    }

    // 获得 enum 对象
    public static AttendanceEnum valueByIndex(int index) {
        for (AttendanceEnum typeEnum : AttendanceEnum.values()) {
            if (typeEnum.getIndex() == index) {
                return typeEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static AttendanceEnum valueByText(String text) {
        for (AttendanceEnum typeEnum : AttendanceEnum.values()) {
            if (typeEnum.getText().equals(text)) {
                return typeEnum;
            }
        }
        return null;
    }

}
