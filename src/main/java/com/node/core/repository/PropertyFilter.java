package com.node.core.repository;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 与具体ORM实现无关的属性过滤条件封装类, 主要记录页面中简单的搜索过滤条件.
 *
 * @author calvin
 */
public class PropertyFilter {

    /**
     * 属性比较类型.
     */
    public enum MatchType {
        EQ, LIKE, LT, GT, LE, GE, IN
    }

    /**
     * 属性数据类型.
     */
    public enum PropertyType {
        S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(Boolean.class);

        private Class<?> clazz;

        private PropertyType(Class<?> clazz) {
            this.clazz = clazz;
        }

        public Class<?> getValue() {
            return clazz;
        }
    }

    private MatchType matchType = null;
    private Object matchValue = null;

    private Class<?> propertyClass = null;
    private String propertyName = null;

    public PropertyFilter() {
    }

    public PropertyFilter(String propertyName, MatchType matchType, Object matchValue) {
        this.propertyName = propertyName;
        this.matchType = matchType;
        this.matchValue = matchValue;
    }

    /**
     * @param filterName 比较属性字符串,含待比较的比较类型、属性值类型及属性列表.
     *                   eg. LIKES_NAME
     * @param value      待比较的值.
     */
    public PropertyFilter(final String filterName, final String value) {

        String firstPart = StringUtils.substringBefore(filterName, "_");
        String matchTypeCode = StringUtils.substring(firstPart, 0, firstPart.length() - 1);
        String propertyTypeCode = StringUtils.substring(firstPart, firstPart.length() - 1, firstPart.length());

        try {
            matchType = Enum.valueOf(MatchType.class, matchTypeCode);
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性比较类型.", e);
        }

        try {
            propertyClass = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
        } catch (RuntimeException e) {
            throw new IllegalArgumentException("filter名称" + filterName + "没有按规则编写,无法得到属性值类型.", e);
        }

        String propertyName = StringUtils.substringAfter(filterName, "_");
        Assert.isTrue(StringUtils.isNotBlank(propertyName), "filter名称" + filterName + "没有按规则编写,无法得到属性名称.");

        this.matchValue = ConvertUtils.convert(value, propertyClass);
    }

    /**
     * 从HttpRequest中创建PropertyFilter列表, 默认Filter属性名前缀为filter.
     *
     * @see #buildFromHttpRequest(HttpServletRequest, String)
     */
    public static List<PropertyFilter> buildFromHttpRequest(final HttpServletRequest request) {
        return buildFromHttpRequest(request, "filter");
    }

    /**
     * 从HttpRequest中创建PropertyFilter列表
     * PropertyFilter命名规则为Filter属性前缀_比较类型属性类型_属性名.
     * <p/>
     * eg.
     * filter_EQS_name
     * filter_LIKES_name
     */
    public static List<PropertyFilter> buildFromHttpRequest(final HttpServletRequest request, final String filterPrefix) {
        List<PropertyFilter> filterList = new ArrayList<PropertyFilter>();

        //从request中获取含属性前缀名的参数,构造去除前缀名后的参数Map.
        Map<String, Object> filterParamMap = ServletUtils.getParametersStartingWith(request, filterPrefix + "_");

        //分析参数Map,构造PropertyFilter列表
        for (Map.Entry<String, Object> entry : filterParamMap.entrySet()) {
            String filterName = entry.getKey();
            String value = (String) entry.getValue();
            //如果value值为空,则忽略此filter.
            if (StringUtils.isNotBlank(value)) {
                PropertyFilter filter = new PropertyFilter(filterName, value);
                filterList.add(filter);
            }
        }

        return filterList;
    }

    /**
     * 获取比较值的类型.
     */
    public Class<?> getPropertyClass() {
        return propertyClass;
    }

    /**
     * 获取比较方式.
     */
    public MatchType getMatchType() {
        return matchType;
    }

    /**
     * 获取比较值.
     */
    public Object getMatchValue() {
        return matchValue;
    }

    /**
     * 获取比较属性名称.
     */
    public String getPropertyName() {
        return propertyName;
    }
}
