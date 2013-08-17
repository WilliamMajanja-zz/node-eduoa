package com.node.eduoa.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 下午5:32
 * To change this template use File | Settings | File Templates.
 */
public class YearUtils {

    public static List<Integer> getYears(Integer limit) {
        List<Integer> years = new ArrayList<Integer>();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int count = year+limit;
        int i=(year);
        for (; i<count; i++) {
            years.add(i);
        }
        return years;
    }

    public static Date getFirstSemesterStartDate(Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH +1, 9);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getFirstSemesterEndDate(Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH +1, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getSecondSemesterStartDate(Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH +1, 3);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    public static Date getSecondSemesterEndDate(Integer year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH +1, 7);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

}
