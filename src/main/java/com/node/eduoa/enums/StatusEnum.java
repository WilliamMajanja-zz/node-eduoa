package com.node.eduoa.enums;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-14
 * Time: 下午11:34
 * To change this template use File | Settings | File Templates.
 */
public enum StatusEnum {

    Uncommitted("未提交"), Submitted("已提交");

    private String text;

    StatusEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal();
    }

    // 获得 enum 对象
    public static StatusEnum valueByIndex(int index) {
        for (StatusEnum typeEnum : StatusEnum.values()) {
            if (typeEnum.getIndex() == index) {
                return typeEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static StatusEnum valueByText(String text) {
        for (StatusEnum typeEnum : StatusEnum.values()) {
            if (typeEnum.getText().equals(text)) {
                return typeEnum;
            }
        }
        return null;
    }

}
