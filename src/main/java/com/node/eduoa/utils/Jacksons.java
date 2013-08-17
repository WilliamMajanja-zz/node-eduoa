package com.node.eduoa.utils;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.FilterProvider;
import org.codehaus.jackson.map.ser.impl.SimpleBeanPropertyFilter;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;
/**
 * jackson一些转换方法
 * <pre>
 *  Jacksons.json().setDateFormate();
 * </pre>
 * @author zxh
 * @date 2012-7-25 下午11:36:33
 */
public class Jacksons {  
	
    private ObjectMapper objectMapper;   
	/** 格式化时间的string */
	private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
    public static Jacksons json() {   
        return new Jacksons();   
    }   
  
    /**
     * 构造方法
     */
    private Jacksons() {   
        objectMapper =  JacksonMapper.getInstance();   
    }   
  
    /**
     * 过滤属性
     * <pre>
     * 使用方法:先给ObjectMapper添加一个filter，然后还要在需要过滤的类上加@JsonFilter("filterName")注解。
	 *	比如说要过滤User 上的name属性，先
	 *	Jacksons.json().filter("myFilter", "name").readAsString(user)，具体看Jacksons代码。并在User类上加@JsonFilter("myFilter")。
	 *	有点不爽的是如果用另外一个没有添加该filter的ObjectMapper解析的话会报错。	 	
	 *	如果这个User类已经添加了@JsonFilter("myFilter")注解，但在另外一个地方又要解析它并不想过滤name 属性，那只能是
	 *		Jacksons.json().filter("myFilter", "")，然后在读出来。
	 *</pre>
     * @param filterName 过滤器的名称
     * @param properties  要过滤的字段名称
     * @return 
     */
    public Jacksons filter(String filterName, String... properties) {   
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter(filterName,   
                SimpleBeanPropertyFilter.serializeAllExcept(properties));   
//        objectMapper.setFilters(filterProvider);

        return this;
    }   
  
    /**
     * 混入注解
     * <pre>
     * 使用方法：
     *  先定义一个接口或类, 在该类上添加@JsonIgnoreProperties("name"), 然后在ObjectMapper的配置项上添加混入注解
	 *		@JsonIgnoreProperties("name")
	 *		public interface MixInUser {
	 *		
	 *		}
	 * 使用：
     * String mixInUser = Jacksons.json()
     * 			.addMixInAnnotations(User.class, MixInUser.class)
     * 			.readAsString(user);

     * </pre>
     * @param target 目标对象的class
     * @param mixinSource  接口或类 
     * @return
     */
    public Jacksons addMixInAnnotations(Class<?> target, Class<?> mixinSource) {   
        objectMapper.getSerializationConfig().addMixInAnnotations(target, mixinSource);   
        objectMapper.getDeserializationConfig().addMixInAnnotations(target, mixinSource);   
        return this;   
    }   
  
    /**
     * 设置日期格式
     * @param dateFormat
     * 		自定义的日期/时间格式。该属性的值遵循java标准的date/time格式规范。如：yyyy-MM-dd
     * @return 
     */
    public Jacksons setDateFormate(String dateFormat) {
        objectMapper.getDeserializationConfig().setDateFormat(new SimpleDateFormat(dateFormat));
        objectMapper.getSerializationConfig().setDateFormat(new SimpleDateFormat(dateFormat));
        return this;   
    }   
  
    /**
     * 设置默认的日期 
     * @return
     */
    public Jacksons setDateFormate() {
        objectMapper.getSerializationConfig().setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));
        objectMapper.getDeserializationConfig().setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));

        return this;   
    } 
    
    /**
     * json转换为java对象
     * @param json  字符串
     * @param clazz  对象的class
     * @return  返回对象
     */
    public <T> T fromJsonToObject(String json, Class<T> clazz) {   
        try {   
            return objectMapper.readValue(json, clazz);   
        } catch (Exception e) {   
            e.printStackTrace();   
            throw new RuntimeException("解析json错误");   
        }   
    }   
  
    /**
     * java对象转换为json字符串
     * @param object java对象
     * @return json字符串
     */
    public String fromObjectToJson(Object object) {   
        try {   
            return objectMapper.writeValueAsString(object);   
        } catch (Exception e) {   
            e.printStackTrace();   
            throw new RuntimeException("解析对象错误");   
        }   
    }   
  
    /**
     * Json转List
     * @param json json字符串
     * @return  list
     */
    @SuppressWarnings("unchecked")   
    public List<Map<String, Object>> fromJsonToList(String json) {   
        try {   
            return objectMapper.readValue(json, List.class);   
        } catch (Exception e) {   
            e.printStackTrace();   
            throw new RuntimeException("解析json错误");   
        }   
    }   
    
    public static void main(String[] args) {
		Jacksons.json().setDateFormate();
		//Jacksons.json().
	}
}  
