package com.node.eduoa.enums;

/**
 * 编制情况b
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 下午4:15
 * To change this template use File | Settings | File Templates.
 */
public enum EstablishmentEnum {

    InPlait("在编"), NoPlait("未在编"), Contract("合同制工人"), Temporary("临时工");

    private String text;

    EstablishmentEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal() + 1;
    }

    // 获得 enum 对象
    public static EstablishmentEnum valueByIndex(int index) {
        for (EstablishmentEnum semesterEnum : EstablishmentEnum.values()) {
            if (semesterEnum.getIndex() == index) {
                return semesterEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static EstablishmentEnum valueByText(String text) {
        for (EstablishmentEnum month : EstablishmentEnum.values()) {
            if (month.getText().equals(text)) {
                return month;
            }
        }
        return null;
    }
}
