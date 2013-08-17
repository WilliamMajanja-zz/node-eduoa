package com.node.eduoa.enums;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 下午7:19
 * To change this template use File | Settings | File Templates.
 */
public enum RatingsEnum {

    Unrated("未评定"), Assessed("已评定");

    private String text;

    RatingsEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal();
    }

    // 获得 enum 对象
    public static RatingsEnum valueByIndex(int index) {
        for (RatingsEnum typeEnum : RatingsEnum.values()) {
            if (typeEnum.getIndex() == index) {
                return typeEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static RatingsEnum valueByText(String text) {
        for (RatingsEnum typeEnum : RatingsEnum.values()) {
            if (typeEnum.getText().equals(text)) {
                return typeEnum;
            }
        }
        return null;
    }

}
