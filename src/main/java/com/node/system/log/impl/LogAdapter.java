/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, node.com
 * Filename:		com.node.system.log.LogAdapter.java
 * Class:			LogTemplateAdapter
 * Date:			2013-5-3
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.log.impl;

import java.util.Map;

import com.google.common.collect.Maps;
import com.node.system.log.LogAPI;
import com.node.system.log.LogLevel;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  2.1.0
 * @since   2013-5-3 下午5:21:07 
 */

public class LogAdapter implements LogAPI {

	/**   
	 * @param message
	 * @param logLevel  
	 * @see com.node.system.log.LogAPI#log(java.lang.String, com.node.system.log.LogLevel)
	 */
	@Override
	public void log(String message, LogLevel logLevel) {
		log(message, null, logLevel);
	}

	/**   
	 * @param message
	 * @param objects
	 * @param logLevel  
	 * @see com.node.system.log.LogAPI#log(java.lang.String, java.lang.Object[], com.node.system.log.LogLevel)
	 */
	@Override
	public void log(String message, Object[] objects, LogLevel logLevel) {
		
	}
	
	/**   
	 * @return  
	 * @see com.node.system.log.LogAPI#getRootLogLevel()
	 */
	@Override
	public LogLevel getRootLogLevel() {
		return LogLevel.ERROR;
	}

	/**   
	 * @return  
	 * @see com.node.system.log.LogAPI#getCustomLogLevel()
	 */
	@Override
	public Map<String, LogLevel> getCustomLogLevel() {
		return Maps.newHashMap();
	}
}
