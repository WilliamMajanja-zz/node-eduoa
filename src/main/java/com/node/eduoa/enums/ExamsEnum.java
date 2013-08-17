package com.node.eduoa.enums;

/**
 *
 * User: linfeng at Administrator
 * Date: 13-7-14
 * Time: 下午11:34
 * To change this template use File | Settings | File Templates.
 */
public enum ExamsEnum {

    FirstSemesterMidTermExam("第一学期期中考试"),
    SecondSemesterMidTermExam("第二学期期中考试"),
    TheFirstSemesterFinalExams("第一学期期未考试"),
    TheSecondSemesterFinalExams("第二学期期未考试"),
    StageTest("阶段测验"),
    AnalogTest("模拟测试"),
    TemporaryTest("临时测试"),
    Yuekao("月考");

    private String text;

    ExamsEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public int getIndex() {
        return ordinal();
    }

    // 获得 enum 对象
    public static ExamsEnum valueByIndex(int index) {
        for (ExamsEnum typeEnum : ExamsEnum.values()) {
            if (typeEnum.getIndex() == index) {
                return typeEnum;
            }
        }
        return null;
    }

    // 根据全名获得 enum 对象
    public static ExamsEnum valueByText(String text) {
        for (ExamsEnum typeEnum : ExamsEnum.values()) {
            if (typeEnum.getText().equals(text)) {
                return typeEnum;
            }
        }
        return null;
    }

}
