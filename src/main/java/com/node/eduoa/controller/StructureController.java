package com.node.eduoa.controller;

import com.node.eduoa.converters.CustomTimestampEditor;
import com.node.eduoa.entity.OaGrade;
import com.node.eduoa.entity.OaOrganizationalStructure;
import com.node.eduoa.entity.OaPosition;
import com.node.eduoa.entity.SysAttachment;
import com.node.eduoa.enums.AttachmentTypeEnum;
import com.node.eduoa.enums.SemesterEnum;
import com.node.eduoa.service.AttachmentService;
import com.node.eduoa.service.OrganizationalStructureService;
import com.node.eduoa.service.impl.AttachmentServiceImpl;
import com.node.eduoa.service.impl.OrganizationalStructureServiceImpl;
import com.node.eduoa.utils.FilenameUtils;
import com.node.eduoa.utils.YearUtils;
import com.node.system.log.Log;
import com.node.system.log.LogLevel;
import com.node.system.log.LogMessageObject;
import com.node.system.log.impl.LogUitl;
import com.node.system.util.dwz.AjaxObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springside.modules.beanvalidator.BeanValidators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 组织结构
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/structure")
public class StructureController extends BaseFormController {


    @Qualifier("organizationalStructureServiceImpl")
    @Autowired
    private OrganizationalStructureService organizationalStructureService;

    @Qualifier("attachmentServiceImpl")
    @Autowired
    private AttachmentService attachmentService;


    @Autowired
    private Validator validator;

    private static final String CREATE = "management/eduoa/structure/create";
    private static final String VIEW = "management/eduoa/structure/view";


    @RequiresPermissions("StructureView:view")
    @RequestMapping(value="/view", method={RequestMethod.GET})
    public String view(Map<String, Object> map) {
        OaOrganizationalStructure structure = organizationalStructureService.findNewStructure();
        if (structure != null) {
            map.put("structure", structure);
            SysAttachment attachment = attachmentService.get(structure.getAttachmentId());
            map.put("attachment", attachment);
        }

        return VIEW;
    }

    @RequiresPermissions("Structure:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate(Map<String, Object> map) {
        SysAttachment attachment = null;
        OaOrganizationalStructure structure = organizationalStructureService.findNewStructure();
        if (structure != null) {
            map.put("structure",structure);
            attachment = attachmentService.get(structure.getId());
            if (attachment != null) {
                map.put("attachment",attachment);
            }
        }
        return CREATE;
    }

    /**
     * LogMessageObject的write用法实例。
     */
    @Log(message = "添加了{0}文件。", level = LogLevel.INFO)
    @RequiresPermissions("Structure:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody String create(@RequestParam("structureName") String structureName,
                         @RequestParam("uploadFile") MultipartFile multipartFile) {

        String path = getServletContext().getRealPath("/upload");
        String webPath = "/upload";

        if(multipartFile.isEmpty()){
            return AjaxObject.newError("文件没有选择！").toString();
        }else{
            Calendar calendar = Calendar.getInstance();
            String distDir = "/structure/" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1)
                    + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/";

            File distDirFile = new File(path + distDir);
            if (!distDirFile.exists()) {
                distDirFile.mkdirs();
            }

            String fileExt = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String fileName = distDir + calendar.getTimeInMillis() + "." + fileExt;
            File file = new File(path+fileName);

            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
                return AjaxObject.newError("上传文件失败，请联系管理员！").setCallbackType("").toString();
            }

            SysAttachment sysAttachment = initAttachment(file, multipartFile.getOriginalFilename(), fileExt, webPath + fileName);
            attachmentService.save(sysAttachment);
            OaOrganizationalStructure structure = initOaOrganizationalStructure(structureName, sysAttachment);
            organizationalStructureService.save(structure);

            LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{structureName}));
            return AjaxObject.newOk("文件添加成功！").setCallbackType("").setForwardUrl(CREATE).toString();
        }

    }


    private SysAttachment initAttachment(File file, String fileName, String fileExt, String urlPath) {
        SysAttachment attachment = new SysAttachment();
        attachment.setFileName(fileName);
        attachment.setFileExt(fileExt);
        attachment.setFileSize(file.length());
        attachment.setServiceFile(file.getPath());
        attachment.setCreateTime(new Date());
        attachment.setAttachmentType(AttachmentTypeEnum.OrgStructure.getIndex());
        attachment.setUserId(currentUser.getId());
        attachment.setUrlPath(urlPath);
        return attachment;
    }

    private OaOrganizationalStructure initOaOrganizationalStructure(String structureName, SysAttachment attachment) {
        OaOrganizationalStructure structure = new OaOrganizationalStructure();
        structure.setAttachmentId(attachment.getId());
        structure.setCreateTime(new Date());
        structure.setUserId(currentUser.getId());
        structure.setStructureName(structureName);
        return structure;
    }


}
