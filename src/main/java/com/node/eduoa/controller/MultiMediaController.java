package com.node.eduoa.controller;

import com.node.eduoa.entity.*;
import com.node.eduoa.enums.*;
import com.node.eduoa.service.*;
import com.node.eduoa.utils.FilenameUtils;
import com.node.system.log.Log;
import com.node.system.log.LogLevel;
import com.node.system.log.LogMessageObject;
import com.node.system.log.impl.LogUitl;
import com.node.system.util.dwz.AjaxObject;
import com.node.system.util.dwz.Page;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 多媒体
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/multimedia")
public class MultiMediaController extends BaseFormController {


    @Qualifier("multiMediaServiceImpl")
    @Autowired
    private MultiMediaService multiMediaService;
    @Qualifier("gradeServiceImpl")
    @Autowired
    private GradeService gradeService;
    @Qualifier("subjectServiceImpl")
    @Autowired
    private SubjectService subjectService;

    @Qualifier("attachmentServiceImpl")
    @Autowired
    private AttachmentService attachmentService;


    @Autowired
    private Validator validator;

    private static final String CREATE = "management/eduoa/multimedia/create";
    private static final String LIST = "management/eduoa/multimedia/list";
    private static final String UPDATE = "management/eduoa/multimedia/update";


    @RequiresPermissions("MultiMedia:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate(Map<String, Object> map) {
        Calendar calendar = Calendar.getInstance();
        map.put("grades", gradeService.findAllByYear(calendar.get(Calendar.YEAR)));
        map.put("subjects", subjectService.findAll());
        map.put("user", currentUser.getUser().getTeacherInfo());
        return CREATE;
    }

    @RequiresPermissions("MultiMedia:edit")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        OaMultimedia multimedia = multiMediaService.get(id);
        Calendar calendar = Calendar.getInstance();
        map.put("multimedia", multimedia);
        map.put("grades", gradeService.findAllByYear(calendar.get(Calendar.YEAR)));
        map.put("subjects", subjectService.findAll());
        map.put("user", currentUser.getUser().getTeacherInfo());

        return UPDATE;
    }

    @Log(message = "修改了{0}多媒体。", level = LogLevel.TRACE, override = true)
    @RequiresPermissions("MultiMedia:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    String update(@RequestParam("id") Long id, @RequestParam("title") String title,
                  @RequestParam("gradeId") Long gradeId, @RequestParam("subjectId") Long subjectId,
                  @RequestParam("userId") Long userId, @RequestParam("userName") String userName,
                  @RequestParam("uploadFile") MultipartFile multipartFile) {

        String path = getServletContext().getRealPath("/upload");
        String webPath = "/upload";

        if (multipartFile.isEmpty()) {
            OaMultimedia media = multiMediaService.get(id);
            media.setTitle(title);
            media.setSubjectId(subjectId);
            OaGrade grade = gradeService.get(gradeId);
            if (grade != null) {
                media.setGradeId(grade.getId());
                media.setGradeName(grade.getGradeName());
            }
            OaSubject subject = subjectService.get(subjectId);
            if (subject != null) {
                media.setSubjectId(subjectId);
                media.setSubjectName(subject.getSubjectName());
            }
            media.setTeacherId(userId);
            media.setTeacherName(userName);
            multiMediaService.save(media);

            return AjaxObject.newOk("多媒体修改成功！").toString();
        } else {

            OaMultimedia media = multiMediaService.get(id);
            if (media.getAttachmentId() != null) {
                attachmentService.delete(media.getAttachmentId());
            }

            Calendar calendar = Calendar.getInstance();
            String distDir = "/multimedia/" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1)
                    + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/";

            File distDirFile = new File(path + distDir);
            if (!distDirFile.exists()) {
                distDirFile.mkdirs();
            }

            String fileExt = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String fileName = distDir + calendar.getTimeInMillis() + "." + fileExt;
            File file = new File(path + fileName);

            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
                return AjaxObject.newError("上传文件失败，请联系管理员！").setCallbackType("").toString();
            }

            SysAttachment sysAttachment = initAttachment(file, multipartFile.getOriginalFilename(), fileExt, webPath + fileName);
            attachmentService.save(sysAttachment);

            OaMultimedia multimedia = initMultiMedia(media, title, gradeId,
                    subjectId, userId, userName, sysAttachment);
            if (media.getAttachmentId() != null) {
                multimedia.setUseCount(0);
            }
            multiMediaService.save(multimedia);

            LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{title}));
            return AjaxObject.newOk("多媒体修改成功！").toString();
        }

    }

    /**
     * LogMessageObject的write用法实例。
     */
    @Log(message = "添加了{0}文件。", level = LogLevel.INFO)
    @RequiresPermissions("MultiMedia:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public
    @ResponseBody
    String create(@RequestParam("title") String title, @RequestParam("gradeId") Long gradeId,
                  @RequestParam("subjectId") Long subjectId, @RequestParam("userId") Long userId,
                  @RequestParam("userName") String userName, @RequestParam("uploadFile") MultipartFile multipartFile) {

        String path = getServletContext().getRealPath("/upload");
        String webPath = "/upload";

        if (multipartFile.isEmpty()) {
            return AjaxObject.newError("文件没有选择！").toString();
        } else {
            Calendar calendar = Calendar.getInstance();
            String distDir = "/teaching_plan/" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1)
                    + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/";

            File distDirFile = new File(path + distDir);
            if (!distDirFile.exists()) {
                distDirFile.mkdirs();
            }

            String fileExt = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
            String fileName = distDir + calendar.getTimeInMillis() + "." + fileExt;
            File file = new File(path + fileName);

            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
                return AjaxObject.newError("上传文件失败，请联系管理员！").setCallbackType("").toString();
            }

            SysAttachment sysAttachment = initAttachment(file, multipartFile.getOriginalFilename(), fileExt, webPath + fileName);
            attachmentService.save(sysAttachment);


            OaMultimedia multimedia = initMultiMedia(null, title, gradeId,
                    subjectId, userId, userName, sysAttachment);
            multimedia.setUseCount(0);
            multiMediaService.save(multimedia);

            LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{title}));
            return AjaxObject.newOk("多媒体添加成功！").toString();
        }

    }


    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@PathVariable Long id) throws IOException {

        SysAttachment attachment = null;
        String filePath = null;
        OaMultimedia multimedia = multiMediaService.get(id);
        if (multimedia != null) {
            if (multimedia.getAttachmentId() != null) {
                attachment = attachmentService.get(multimedia.getAttachmentId());
                filePath = attachment.getServiceFile();

                File file = new File(filePath);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                headers.setContentDispositionFormData("attachment", attachment.getFileName());
                if (multimedia.getUseCount() == null) {
                    multimedia.setUseCount(1);
                } else {
                    multimedia.setUseCount(multimedia.getUseCount()+1);
                }

                multiMediaService.save(multimedia);

                return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                        headers, HttpStatus.CREATED);
            }
        }
        return null;
    }


    @RequiresPermissions("MultiMedia:view")
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Page page, String keywords, Map<String, Object> map) {
        List<OaMultimedia> multimedias = null;
        if (StringUtils.isNotBlank(keywords)) {
            multimedias = multiMediaService.findByTitleContaining(keywords, page);
        } else {
            multimedias = multiMediaService.findAll(page);
        }

        map.put("page", page);
        map.put("multimedias", multimedias);
        map.put("keywords", keywords);

        return LIST;
    }


    @Log(message = "删除了{0}多媒体。", level = LogLevel.TRACE, override = true)
    @RequiresPermissions("MultiMedia:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    String deleteMany(Long[] ids) {
        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaMultimedia multimedia = multiMediaService.get(ids[i]);
            Long attamId = multimedia.getAttachmentId();
            if (attamId != null) {
                SysAttachment attachment = attachmentService.get(attamId);
                if (attachment != null) {
                    attachmentService.delete(attamId);
                }
            }
            multiMediaService.delete(multimedia.getId());
            titles[i] = multimedia.getTitle();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
        return AjaxObject.newOk("多媒体删除成功！").setCallbackType("").toString();
    }

    private SysAttachment initAttachment(File file, String fileName, String fileExt, String urlPath) {
        SysAttachment attachment = new SysAttachment();
        attachment.setFileName(fileName);
        attachment.setFileExt(fileExt);
        attachment.setFileSize(file.length());
        attachment.setServiceFile(file.getPath());
        attachment.setCreateTime(new Date());
        attachment.setAttachmentType(AttachmentTypeEnum.TeachingPlan.getIndex());
        attachment.setUserId(currentUser.getId());
        attachment.setUrlPath(urlPath);
        return attachment;
    }

    private OaMultimedia initMultiMedia(OaMultimedia media, String title, Long gradeId,
                                        Long subjectId, Long userId, String userName, SysAttachment attachment) {
        Date currentDate = new Date();
        OaMultimedia multimedia = null;
        if (media == null) {
            multimedia = new OaMultimedia();
        } else {
            multimedia = media;
        }

        multimedia.setAttachmentId(attachment.getId());
        multimedia.setAttachmentTitle(attachment.getFileName());
        multimedia.setTitle(title);
        multimedia.setUploadTime(currentDate);
        multimedia.setUploadTimeLong(currentDate.getTime());
        if (gradeId != null) {
            OaGrade grade = gradeService.get(gradeId);
            if (grade != null) {
                multimedia.setGradeId(grade.getId());
                multimedia.setGradeName(grade.getGradeName());
            }
        }

        if (subjectId != null) {
            OaSubject subject = subjectService.get(subjectId);
            if (subject != null) {
                multimedia.setSubjectId(subjectId);
                multimedia.setSubjectName(subject.getSubjectName());
            }
        }

        multimedia.setTeacherId(userId);
        multimedia.setTeacherName(userName);

        return multimedia;
    }


}
