package com.node.eduoa.enums;

/**
 * 采购状态z
 * User: linfeng at Administrator
 * Date: 13-7-21状态
 * Time: 下午7:22
 * To change this template use File | Settings | File Templates.
 */
public enum PurchaseEnum {

    NotProcurement("未采购"), Purchased("已采购");

    private String text;

    PurchaseEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal();
    }

    // 获得 enum 对象
    public static PurchaseEnum valueByIndex(int index) {
        for (PurchaseEnum typeEnum : PurchaseEnum.values()) {
            if (typeEnum.getIndex() == index) {
                return typeEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static PurchaseEnum valueByText(String text) {
        for (PurchaseEnum typeEnum : PurchaseEnum.values()) {
            if (typeEnum.getText().equals(text)) {
                return typeEnum;
            }
        }
        return null;
    }

}
