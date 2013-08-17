package com.node.eduoa.enums;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 下午7:25
 * To change this template use File | Settings | File Templates.
 */
public enum PoliticalLandscapeEnum {
    Masses("群众"), Member("团员"), PartyMember("党员");

    private String text;

    PoliticalLandscapeEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal();
    }

    // 获得 enum 对象
    public static PoliticalLandscapeEnum valueByIndex(int index) {
        for (PoliticalLandscapeEnum typeEnum : PoliticalLandscapeEnum.values()) {
            if (typeEnum.getIndex() == index) {
                return typeEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static PoliticalLandscapeEnum valueByText(String text) {
        for (PoliticalLandscapeEnum typeEnum : PoliticalLandscapeEnum.values()) {
            if (typeEnum.getText().equals(text)) {
                return typeEnum;
            }
        }
        return null;
    }
}
