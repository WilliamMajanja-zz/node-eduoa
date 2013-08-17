package com.node.eduoa.utils;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
/**
 * jackson 创建ObjectMapper对象
 * <pre>
 * 创建为饿汉式单例模式 ，Jackson用于转换的核心类ObjectMapper无需每次都new一个object，
 * 官网上的一句话：can reuse, share globally(可以重复使用，全局共享)
 * </pre>

 * @author zxh
 * @date 2012-7-25 下午11:36:33
 */
public class JacksonMapper {
	 /** 创建 ObjectMapper*/   
    private static final ObjectMapper objectMapper = new ObjectMapper();    
    /**
     *  默认空的构造方法
     */
    private JacksonMapper() {}   
    /**   
     *  获取实例
     * @return   
     */   
    public static ObjectMapper getInstance() {
        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        objectMapper.configure(Feature.FAIL_ON_EMPTY_BEANS, false);    
        return objectMapper;    
    }    
   

}
