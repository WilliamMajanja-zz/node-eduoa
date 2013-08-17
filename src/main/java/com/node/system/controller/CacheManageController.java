/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, node.com
 * Filename:		com.node.system.controller.CacheManageController.java
 * Class:			CacheManageController
 * Date:			2012-9-14
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.node.system.log.Log;
import com.node.system.service.CacheService;
import com.node.system.util.dwz.AjaxObject;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  1.1.0
 * @since   2012-9-14 上午11:08:15 
 */
@Controller
@RequestMapping("/management/security/cacheManage")
public class CacheManageController {
	@Autowired
	private CacheService cacheService;
	
	private static final String INDEX = "management/security/cacheManage/index";
	
	@RequiresPermissions("CacheManage:view")
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public String index() {
		return INDEX;
	}
	
	@Log(message="进行了缓存清理。")
	@RequiresPermissions("CacheManage:edit")
	@RequestMapping(value="/clear", method=RequestMethod.POST)
	public @ResponseBody String clear() {
		cacheService.clearAllCache();
		
		return AjaxObject.newOk("清除缓存成功！").setCallbackType("").toString();
	}
}
