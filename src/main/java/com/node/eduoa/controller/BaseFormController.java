package com.node.eduoa.controller;

import com.node.eduoa.converters.CustomTimestampEditor;
import com.node.eduoa.entity.OaTeacherInfo;
import com.node.system.entity.main.Organization;
import com.node.system.shiro.ShiroDbRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午11:47
 * To change this template use File | Settings | File Templates.
 */
public class BaseFormController implements ServletContextAware {


    private ServletContext servletContext;
    protected ShiroDbRealm.ShiroUser currentUser;
    protected Organization currentOrganization;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public ShiroDbRealm.ShiroUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(ShiroDbRealm.ShiroUser currentUser) {
        this.currentUser = currentUser;
    }

    public Organization getCurrentOrganization() {
        return currentOrganization;
    }

    public void setCurrentOrganization(Organization currentOrganization) {
        this.currentOrganization = currentOrganization;
    }

    @InitBinder
    protected void initBinder(HttpServletRequest request,
                              ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Integer.class, null,
                new CustomNumberEditor(Integer.class, null, true));
        binder.registerCustomEditor(Long.class, null,
                new CustomNumberEditor(Long.class, null, true));
//        binder.registerCustomEditor(byte[].class,
//                new ByteArrayMultipartFileEditor());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", request.getLocale());
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,
                new CustomDateEditor(dateFormat, true));
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", request.getLocale());
        dateTimeFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,
                new CustomTimestampEditor(dateTimeFormat, true));
    }

    @ModelAttribute
    protected void initCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        currentUser = ((ShiroDbRealm.ShiroUser)subject.getPrincipal());
        OaTeacherInfo teacherInfo = currentUser.getUser().getTeacherInfo();
        if (teacherInfo != null) {
            currentOrganization = currentUser.getUser().getTeacherInfo().getSecurityOrganizationByOrgId();
        }
    }

}
