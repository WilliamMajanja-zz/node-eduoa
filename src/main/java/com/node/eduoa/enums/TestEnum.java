package com.node.eduoa.enums;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 下午3:53
 * To change this template use File | Settings | File Templates.
 */
public enum TestEnum {
    First("第一学期"), Second("第二学期");

    private String value;

    TestEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
