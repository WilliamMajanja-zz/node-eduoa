package com.node.eduoa.enums;

/**
 * 学期
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 下午2:46
 * To change this template use File | Settings | File Templates.
 */
public enum SemesterEnum {
    First("第一学期"), Second("第二学期");

    private String text;

    SemesterEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal() + 1;
    }

    // 获得 enum 对象
    public static SemesterEnum valueByIndex(int index) {
        for (SemesterEnum semesterEnum : SemesterEnum.values()) {
            if (semesterEnum.getIndex() == index) {
                return semesterEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static SemesterEnum valueByText(String text) {
        for (SemesterEnum month : SemesterEnum.values()) {
            if (month.getText().equals(text)) {
                return month;
            }
        }
        return null;
    }

}
