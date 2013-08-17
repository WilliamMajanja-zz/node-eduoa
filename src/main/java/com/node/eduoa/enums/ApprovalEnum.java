package com.node.eduoa.enums;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-14
 * Time: 下午11:32
 * To change this template use File | Settings | File Templates.
 */
public enum ApprovalEnum {


    Unapproved("未审批"), Passed("已通过"), Dismissed("已驳回");

    private String text;

    ApprovalEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal();
    }

    // 获得 enum 对象
    public static ApprovalEnum valueByIndex(int index) {
        for (ApprovalEnum typeEnum : ApprovalEnum.values()) {
            if (typeEnum.getIndex() == index) {
                return typeEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static ApprovalEnum valueByText(String text) {
        for (ApprovalEnum typeEnum : ApprovalEnum.values()) {
            if (typeEnum.getText().equals(text)) {
                return typeEnum;
            }
        }
        return null;
    }

}
