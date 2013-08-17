/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, node.com
 * Filename:		com.node.system.controller.IndexController.java
 * Class:			IndexController
 * Date:			2012-8-2
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          1.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.node.system.SecurityConstants;
import com.node.system.entity.main.Module;
import com.node.system.entity.main.Permission;
import com.node.system.entity.main.User;
import com.node.system.log.Log;
import com.node.system.log.LogMessageObject;
import com.node.system.log.impl.LogUitl;
import com.node.system.service.ModuleService;
import com.node.system.service.UserService;
import com.node.system.shiro.ShiroDbRealm;
import com.node.system.util.dwz.AjaxObject;

/** 
 * 	
 * @author 	<a href="mailto:node@gmail.com">node</a>
 * Version  1.1.0
 * @since   2012-8-2 下午5:45:57 
 */
@Controller
@RequestMapping("/management/index")
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModuleService moduleService;
	
	private static final String INDEX = "management/index/index";
	private static final String UPDATE_PASSWORD = "management/index/updatePwd";
	private static final String UPDATE_BASE = "management/index/updateBase";
	
	@Log(message="{0}登录了系统。")
	@RequiresUser 
	@RequestMapping(value="", method=RequestMethod.GET)
	public String index(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser)subject.getPrincipal();
		// 加入ipAddress
		shiroUser.setIpAddress(request.getRemoteAddr());
		
		Module menuModule = getMenuModule(subject);

		// 这个是放入user还是shiroUser呢？
		request.getSession().setAttribute(SecurityConstants.LOGIN_USER, shiroUser.getUser());
		request.setAttribute("menuModule", menuModule);

		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{shiroUser.getLoginName()}));
		return INDEX;
	}
	
	private Module getMenuModule(Subject subject) {
		Module rootModule = moduleService.getTree();

		check(rootModule, subject);
		return rootModule;
	}
	
	private void check(Module module, Subject subject) {
		List<Module> list1 = Lists.newArrayList();
		for (Module m1 : module.getChildren()) {
			// 只加入拥有view权限的Module
			if (subject.isPermitted(m1.getSn() + ":"
					+ Permission.PERMISSION_READ)) {
				check(m1, subject);
				list1.add(m1);
			}
		}
		module.setChildren(list1);
	}
	
	@RequestMapping(value="/updatePwd", method=RequestMethod.GET)
	public String preUpdatePassword() {
		return UPDATE_PASSWORD;
	}
	
	@Log(message="{0}修改了密码。")
	@RequestMapping(value="/updatePwd", method=RequestMethod.POST)
	public @ResponseBody String updatePassword(HttpServletRequest request, String oldPassword, 
			String plainPassword, String rPassword) {
		User user = (User)request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		if (plainPassword.equals(rPassword)) {
			user.setPlainPassword(plainPassword);
			userService.update(user);
		
			LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{user.getUsername()}));
			return AjaxObject.newOk("修改密码成功！").toString();
		}
		
		return AjaxObject.newError("修改密码失败！").setCallbackType("").toString();
	}
	
	@RequestMapping(value="/updateBase", method=RequestMethod.GET)
	public String preUpdateBase() {
		return UPDATE_BASE;
	}
	
	@Log(message="{0}修改了详细信息。")
	@RequestMapping(value="/updateBase", method=RequestMethod.POST)
	public @ResponseBody String update(User user, HttpServletRequest request) {
		User loginUser = (User)request.getSession().getAttribute(SecurityConstants.LOGIN_USER);
		
		loginUser.setPhone(user.getPhone());
		loginUser.setEmail(user.getEmail());

		userService.update(loginUser);
		
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{user.getUsername()}));
		return AjaxObject.newOk("修改详细信息成功！").toString();
	}
}
