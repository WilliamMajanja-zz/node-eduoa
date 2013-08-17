package com.node.eduoa.converters;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: linfeng at Administrator
 * Date: 13-7-6
 * Time: 下午4:52
 * To change this template use File | Settings | File Templates.
 */
public class StringToDateConverter implements Converter<String, Date> {

   private static String DEFAULT_PATTERN = "yyyy-MM-dd HH-mm-ss";

    public StringToDateConverter(String pattern) {
        DEFAULT_PATTERN = pattern;
    }

    public StringToDateConverter() {
        this(DEFAULT_PATTERN);
    }

    @Override
    public Date convert(String src) {
        final DateFormat df = new SimpleDateFormat(DEFAULT_PATTERN);
        try {
            df.parse(src);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


}
