package com.node.eduoa.enums;

/**
 * 学历
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 下午4:11
 * To change this template use File | Settings | File Templates.
 */
public enum EducationEnum {

    Secondary("中专"), College("大专"), Undergraduate("本科"), Master("硕士"), Doctor("博士");

    private String text;

    EducationEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal() + 1;
    }

    // 获得 enum 对象
    public static EducationEnum valueByIndex(int index) {
        for (EducationEnum semesterEnum : EducationEnum.values()) {
            if (semesterEnum.getIndex() == index) {
                return semesterEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static EducationEnum valueByText(String text) {
        for (EducationEnum month : EducationEnum.values()) {
            if (month.getText().equals(text)) {
                return month;
            }
        }
        return null;
    }

}
