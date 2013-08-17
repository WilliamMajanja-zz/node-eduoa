package com.node.eduoa.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: linfeng at Administrator
 * Date: 13-7-21
 * Time: 下午6:53
 * To change this template use File | Settings | File Templates.
 */
public class WeekUtils {

    // 用来全局控制 上一周，本周，下一周的周数变化
    private static int weeks = 0;
    private Map<String, Date> minDateMap;

    public Map<String, Date> getMinDateMap() {
        return minDateMap;
    }

    public void setMinDateMap(Map<String, Date> minDateMap) {
        this.minDateMap = minDateMap;
    }

    // 获得当前日期与本周一相差的天数
    private int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }

    // 获得上周星期一的日期
    public String getPreviousMonday() {
        weeks--;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得本周星期一的日期
    public String getCurrentMonday() {
        weeks = 0;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得本周星期一的日期
    public Date getCurrentMondayDate() {
        weeks = 0;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        return currentDate.getTime();
    }

    // 获得下周星期一的日期
    public String getNextMonday() {
        weeks++;
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得相应周的周日的日期
    public String getSunday() {
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得相应周的周日的日期
    public Date getSundayDate() {
        int mondayPlus = this.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
        return currentDate.getTime();
    }

    public Date getCurrentDate() {
        GregorianCalendar currentDate = new GregorianCalendar();
        return currentDate.getTime();
    }

    public Date getConvertStringToDate(String date) {
        if (date != null && StringUtils.isNotBlank(date)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String newDateStr = date + " 00:00:00";
            Date convertDate = null;
            try {
                convertDate = simpleDateFormat.parse(newDateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return convertDate;
        }
        return null;

    }


    public Date getConvertTimeToDate(String time){
        Date date = getCurrentDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String day = simpleDateFormat.format(date);
        String newDateStr = day + " " + time;
        Date convertDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            convertDate = dateFormat.parse(newDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertDate;
    }

    public Long getCompileDateForSomeTime(String time, Date current) {
        Date tmpDate = getConvertTimeToDate(time);
        return current.getTime() - tmpDate.getTime();
    }

    public Boolean compileDateForSomeDay(Date oneDate, Date twoDate) {
        Long oneTime = oneDate.getTime();
        Long twoTime = twoDate.getTime();
        int step = 24*60*60*1000;
        if ((oneTime - twoTime) >= -step && (oneTime - twoTime) <= step) {
            return true;
        } else {
            return false;
        }
    }

    public int compileDate(Date oneDate, Date twoDate) {
        long oneTime = oneDate.getTime();
        long twoTime = twoDate.getTime();
        if (oneTime > twoTime) {
            return 1;
        } else if (oneTime == twoTime) {
            return 0;
        } else {
            return -1;
        }
    }

    public String compileDateOfDate(Date oneDate, Date twoDate) {
        long oneTime = oneDate.getTime();
        long twoTime = twoDate.getTime();
        long result = twoTime - oneTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String day = getCurrentDay();
        Date currentDate = null;
        try {
            currentDate = dateFormat.parse(day);
            currentDate.setTime(currentDate.getTime() + result);
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            return timeFormat.format(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获得当天日期
    public String getCurrentDay() {
        GregorianCalendar currentDate = new GregorianCalendar();
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }

    public List<String> getCurrentDateOfWeek() {
        List<String> resultDate = new ArrayList<String>(7);
        for (int i=0; i<= 6; i++) {
            int mondayPlus = this.getMondayPlus();
            GregorianCalendar currentDate = new GregorianCalendar();
            currentDate.add(GregorianCalendar.DATE, mondayPlus + i);
            Date monday = currentDate.getTime();
            DateFormat df = DateFormat.getDateInstance();
            String preMonday = df.format(monday);
            resultDate.add(preMonday);
        }
        return resultDate;
    }

    public MinDate getMinDate(Map<String, Long> dateMap) {
        MinDate minDate = null;
        for(Map.Entry<String, Long> entry: dateMap.entrySet()) {
            if (minDate == null) {
                minDate = new MinDate(entry.getKey(), entry.getValue());
            } else {
                if (minDate.getValue() >= entry.getValue()) {
                    minDate = new MinDate(entry.getKey(), entry.getValue());
                }
            }
        }
        return minDate;
    }

    public class MinDate {
        String key;
        Long value;

        MinDate(String key, Long value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }
    }

}
