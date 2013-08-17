package com.node.eduoa.enums;

/**
 * 班级分班
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 下午10:36
 * To change this template use File | Settings | File Templates.
 */
public enum ClassTypeEnum {

    NoDivision("全科"), LiberalArts("文科"), Science("理科");

    private String text;

    ClassTypeEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal() + 1;
    }

    // 获得 enum 对象
    public static ClassTypeEnum valueByIndex(int index) {
        for (ClassTypeEnum semesterEnum : ClassTypeEnum.values()) {
            if (semesterEnum.getIndex() == index) {
                return semesterEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static ClassTypeEnum valueByText(String text) {
        for (ClassTypeEnum month : ClassTypeEnum.values()) {
            if (month.getText().equals(text)) {
                return month;
            }
        }
        return null;
    }

}
