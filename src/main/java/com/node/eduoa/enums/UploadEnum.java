package com.node.eduoa.enums;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-14
 * Time: 下午11:34
 * To change this template use File | Settings | File Templates.
 */
public enum UploadEnum {

    UnUpLoad("未上传"), Upload("已上传");

    private String text;

    UploadEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal();
    }

    // 获得 enum 对象
    public static UploadEnum valueByIndex(int index) {
        for (UploadEnum typeEnum : UploadEnum.values()) {
            if (typeEnum.getIndex() == index) {
                return typeEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static UploadEnum valueByText(String text) {
        for (UploadEnum typeEnum : UploadEnum.values()) {
            if (typeEnum.getText().equals(text)) {
                return typeEnum;
            }
        }
        return null;
    }

}
