package com.node.eduoa.controller;

import com.node.eduoa.converters.CustomTimestampEditor;
import com.node.eduoa.entity.OaTeacherInfo;
import com.node.eduoa.entity.SysAttachment;
import com.node.eduoa.enums.AttachmentTypeEnum;
import com.node.eduoa.service.AttachmentService;
import com.node.system.entity.main.Organization;
import com.node.system.shiro.ShiroDbRealm;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

    @Qualifier("attachmentServiceImpl")
    @Autowired
    protected AttachmentService attachmentService;

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

    protected SysAttachment uploadFile(MultipartFile multipartFile, AttachmentTypeEnum attachmentType) throws IOException{
        String path = getServletContext().getRealPath("/upload");
        String webPath = "/upload";
        SysAttachment attachment = null;
        if(!multipartFile.isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            String distDir = "/"+attachmentType.name()+"/" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1)
                    + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/";

            File distDirFile = new File(path + distDir);
            if (!distDirFile.exists()) {
                distDirFile.mkdirs();
            }
            String fileExt = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String fileName = distDir + calendar.getTimeInMillis() + "." + fileExt;
            File file = new File(path+fileName);
            multipartFile.transferTo(file);
            attachment = initSysAttachment(file, multipartFile.getOriginalFilename(), fileExt, webPath + fileName, attachmentType);
            attachmentService.save(attachment);
            //保存业务对象
        }
        return attachment;
    }

    private SysAttachment initSysAttachment(File file, String fileName, String fileExt, String urlPath, AttachmentTypeEnum attachmentType) {
        SysAttachment attachment = new SysAttachment();
        attachment.setFileName(fileName);
        attachment.setFileExt(fileExt);
        attachment.setFileSize(file.length());
        attachment.setServiceFile(file.getPath());
        attachment.setCreateTime(new Date());
        attachment.setAttachmentType(attachmentType.getIndex());
        attachment.setUserId(currentUser.getId());
        attachment.setUrlPath(urlPath);
        return attachment;
    }


}
