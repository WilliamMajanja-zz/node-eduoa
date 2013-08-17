package com.node.eduoa.enums;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 下午7:19
 * To change this template use File | Settings | File Templates.
 */
public enum GenderEnum {

    Female("女性"), Male("男性");

    private String text;

    GenderEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal();
    }

    // 获得 enum 对象
    public static GenderEnum valueByIndex(int index) {
        for (GenderEnum typeEnum : GenderEnum.values()) {
            if (typeEnum.getIndex() == index) {
                return typeEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static GenderEnum valueByText(String text) {
        for (GenderEnum typeEnum : GenderEnum.values()) {
            if (typeEnum.getText().equals(text)) {
                return typeEnum;
            }
        }
        return null;
    }

}
