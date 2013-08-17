package com.node.eduoa.enums;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 下午7:19
 * To change this template use File | Settings | File Templates.
 */
public enum PlanLevelEnum {

    Excellent("优秀"), Good("良好"), Qualified("合格"), Unqualified("不合格");

    private String text;

    PlanLevelEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal();
    }

    // 获得 enum 对象
    public static PlanLevelEnum valueByIndex(int index) {
        for (PlanLevelEnum typeEnum : PlanLevelEnum.values()) {
            if (typeEnum.getIndex() == index) {
                return typeEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static PlanLevelEnum valueByText(String text) {
        for (PlanLevelEnum typeEnum : PlanLevelEnum.values()) {
            if (typeEnum.getText().equals(text)) {
                return typeEnum;
            }
        }
        return null;
    }

}
