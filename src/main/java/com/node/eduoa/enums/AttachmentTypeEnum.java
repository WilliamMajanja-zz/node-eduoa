package com.node.eduoa.enums;

/**
 * 附件类型
 * Date: 13-7-7
 * Time: 下午12:13
 * To change this template use File | Settings | File Templates.
 */
public enum AttachmentTypeEnum {
    OrgStructure("组织结构"), TeachingPlan("电子教案");

    private String text;

    AttachmentTypeEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal() + 1;
    }

    // 获得 enum 对象
    public static AttachmentTypeEnum valueByIndex(int index) {
        for (AttachmentTypeEnum typeEnum : AttachmentTypeEnum.values()) {
            if (typeEnum.getIndex() == index) {
                return typeEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static ClassTypeEnum valueByText(String text) {
        for (ClassTypeEnum typeEnum : ClassTypeEnum.values()) {
            if (typeEnum.getText().equals(text)) {
                return typeEnum;
            }
        }
        return null;
    }
}
