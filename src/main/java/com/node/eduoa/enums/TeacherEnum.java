package com.node.eduoa.enums;

/**
 * 是否是教师
 * Date: 13-7-7
 * Time: 下午12:13
 * To change this template use File | Settings | File Templates.
 */
public enum TeacherEnum {
    NOTEACHER("否"), ISTEACHER("是");

    private String text;

    TeacherEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal();
    }

    // 获得 enum 对象
    public static TeacherEnum valueByIndex(int index) {
        for (TeacherEnum typeEnum : TeacherEnum.values()) {
            if (typeEnum.getIndex() == index) {
                return typeEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static ClassTypeEnum valueByText(String text) {
        for (ClassTypeEnum typeEnum : ClassTypeEnum.values()) {
            if (typeEnum.getText().equals(text)) {
                return typeEnum;
            }
        }
        return null;
    }
}
