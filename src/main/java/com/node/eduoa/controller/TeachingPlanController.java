package com.node.eduoa.controller;

import com.node.eduoa.entity.*;
import com.node.eduoa.enums.*;
import com.node.eduoa.service.*;
import com.node.eduoa.utils.FilenameUtils;
import com.node.eduoa.utils.YearUtils;
import com.node.system.log.Log;
import com.node.system.log.LogLevel;
import com.node.system.log.LogMessageObject;
import com.node.system.log.impl.LogUitl;
import com.node.system.util.dwz.AjaxObject;
import com.node.system.util.dwz.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.persistence.SearchFilter;

import javax.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 组织结构
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午10:17
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/teachingplan")
public class TeachingPlanController extends BaseFormController {


    @Qualifier("organizationalStructureServiceImpl")
    @Autowired
    private OrganizationalStructureService organizationalStructureService;
    @Qualifier("teachingPlanServiceImpl")
    @Autowired
    private TeachingPlanService teachingPlanService;
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

    private static final String CREATE = "management/eduoa/teachingplan/create";
    private static final String VIEW = "management/eduoa/teachingplan/view";
    private static final String LIST = "management/eduoa/teachingplan/list";
    private static final String LIST_RATINGS = "management/eduoa/teachingplan/ratings_list";
    private static final String UPDATE = "management/eduoa/teachingplan/update";
    private static final String RATINGS = "management/eduoa/teachingplan/ratings";


    @RequiresPermissions("TeachingPlanView:view")
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

    @RequiresPermissions("TeachingPlan:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate(Map<String, Object> map) {
        Calendar calendar = Calendar.getInstance();
        map.put("grades", gradeService.findAllByYear(calendar.get(Calendar.YEAR)));
        map.put("subjects", subjectService.findAll());
        map.put("user", currentUser.getUser().getTeacherInfo());
        return CREATE;
    }

    @RequiresPermissions("TeachingPlan:edit")
    @RequestMapping(value="/update/{id}", method=RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        OaTeachingPlan teachingPlan = teachingPlanService.get(id);
        Calendar calendar = Calendar.getInstance();
        map.put("teachingPlan", teachingPlan);
        map.put("grades", gradeService.findAllByYear(calendar.get(Calendar.YEAR)));
        map.put("subjects", subjectService.findAll());
        map.put("user", currentUser.getUser().getTeacherInfo());

        return UPDATE;
    }

    @Log(message="修改了{0}年级。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("TeachingPlan:edit")
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public @ResponseBody String update(@RequestParam("id") Long id, @RequestParam("planTitle") String planTitle,
                                       @RequestParam("gradeId") Long gradeId, @RequestParam("subjectId") Long subjectId,
                                       @RequestParam("userId") Long userId, @RequestParam("userName") String userName,
                                       @RequestParam("uploadFile") MultipartFile multipartFile) {

        String path = getServletContext().getRealPath("/upload");
        String webPath = "/upload";

        if(multipartFile.isEmpty()){
            OaTeachingPlan plan = teachingPlanService.get(id);
            plan.setPlanTitle(planTitle);
            plan.setSubjectId(subjectId);
            OaGrade grade = gradeService.get(gradeId);
            if (grade != null) {
                plan.setGradeId(grade.getId());
                plan.setGradeName(grade.getGradeName());
            }
            OaSubject subject = subjectService.get(subjectId);
            if (subject != null) {
                plan.setSubjectId(subjectId);
                plan.setSubjectName(subject.getSubjectName());
            }
            plan.setTeacherId(userId);
            plan.setTeacherName(userName);
            teachingPlanService.save(plan);

            return AjaxObject.newOk("课件修改成功！").toString();
        }else{

            OaTeachingPlan plan = teachingPlanService.get(id);
            if (plan.getAttachmentId() != null) {
                attachmentService.delete(plan.getAttachmentId());
            }

            Calendar calendar = Calendar.getInstance();
            String distDir = "/teaching_plan/" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1)
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

            OaTeachingPlan teachingPlan = initTeachingPlan(planTitle, gradeId,
                    subjectId, userId, userName, sysAttachment);
            teachingPlan.setStatue(UploadEnum.UnUpLoad.getIndex());
            teachingPlanService.save(teachingPlan);

            LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{planTitle}));
            return AjaxObject.newOk("课件修改成功！").toString();
        }

    }

    /**
     * LogMessageObject的write用法实例。
     */
    @Log(message = "添加了{0}文件。", level = LogLevel.INFO)
    @RequiresPermissions("TeachingPlan:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody String create(@RequestParam("planTitle") String planTitle, @RequestParam("gradeId") Long gradeId,
                                       @RequestParam("subjectId") Long subjectId, @RequestParam("userId") Long userId,
                                       @RequestParam("userName") String userName, @RequestParam("uploadFile") MultipartFile multipartFile) {

        String path = getServletContext().getRealPath("/upload");
        String webPath = "/upload";

        if(multipartFile.isEmpty()){
            return AjaxObject.newError("文件没有选择！").toString();
        }else{
            Calendar calendar = Calendar.getInstance();
            String distDir = "/teaching_plan/" + calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH)+1)
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


            OaTeachingPlan teachingPlan = initTeachingPlan(planTitle, gradeId,
                    subjectId, userId, userName, sysAttachment);
            teachingPlan.setStatue(UploadEnum.UnUpLoad.getIndex());
            teachingPlanService.save(teachingPlan);

            LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{planTitle}));
            return AjaxObject.newOk("文件添加成功！").toString();
        }

    }

    @Log(message="提交了{0}课件。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("TeachingPlan:save")
    @RequestMapping(value="/submit", method=RequestMethod.POST)
    public @ResponseBody String submit(Long[] ids) {
        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaTeachingPlan teachingPlan = teachingPlanService.get(ids[i]);
            teachingPlan.setStatue(UploadEnum.Upload.getIndex());
            if (teachingPlan.getAttachmentId() != null) {
                attachmentService.delete(teachingPlan.getAttachmentId());
            }
            teachingPlanService.save(teachingPlan);
            titles[i] = teachingPlan.getPlanTitle();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
        return AjaxObject.newOk("课件提交成功！").setCallbackType("").toString();
    }


    @RequiresPermissions("TeachingPlan:view")
    @RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
    public String list(Page page, String keywords, Map<String, Object> map) {
        List<OaTeachingPlan> teachingPlans = null;
        if (StringUtils.isNotBlank(keywords)) {
            teachingPlans = teachingPlanService.findByPlanTitleContaining(keywords, page);
        } else {
            teachingPlans = teachingPlanService.findAll(page);
        }
        SemesterEnum[] semesterEnums = SemesterEnum.values();

        map.put("page", page);
        map.put("teachingPlans", teachingPlans);
        map.put("keywords", keywords);

        return LIST;
    }

    @RequiresPermissions("TeachingPlanRatings:view")
    @RequestMapping(value="/listRatings", method={RequestMethod.GET, RequestMethod.POST})
    public String listRatings(Page page, String keywords, Map<String, Object> map) {
        List<OaTeachingPlan> teachingPlans = null;
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put(SearchFilter.Operator.EQ + "_statue", UploadEnum.Upload.getIndex()+"");
        if (StringUtils.isNotBlank(keywords)) {
            searchParam.put(SearchFilter.Operator.EQ + "_planTitle", keywords);
            teachingPlans = teachingPlanService.findByPlanTitleCondition(page, searchParam);
        } else {
            teachingPlans = teachingPlanService.findByPlanTitleCondition(page, searchParam);
        }

        map.put("page", page);
        map.put("teachingPlans", teachingPlans);
        map.put("keywords", keywords);

        return LIST_RATINGS;
    }

    @RequiresPermissions("TeachingPlanRatings:ratings")
    @RequestMapping(value="/ratings/{id}", method=RequestMethod.GET)
    public String preRatings(@PathVariable Long id, Map<String, Object> map) {
        OaTeachingPlan teachingPlan = teachingPlanService.get(id);
        map.put("teachingPlan", teachingPlan);
        map.put("planLevelEnum", PlanLevelEnum.values());

        return RATINGS;
    }

    @Log(message="评定了{0}课件。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("TeachingPlanRatings:ratings")
    @RequestMapping(value="/ratings", method=RequestMethod.POST)
    public @ResponseBody String ratings(@RequestParam("id") Long id, @RequestParam("planLevel") Integer planLevel) {
        OaTeachingPlan teachingPlan = teachingPlanService.get(id);
        teachingPlan.setRatings(RatingsEnum.Assessed.getIndex());
        teachingPlan.setPlanLevel(planLevel);
        teachingPlanService.save(teachingPlan);

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{teachingPlan.getPlanTitle()}));
        return AjaxObject.newOk("课件评定成功！").toString();
    }

    @Log(message="删除了{0}课件。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("TeachingPlan:delete")
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public @ResponseBody String deleteMany(Long[] ids) {
        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaTeachingPlan teachingPlan = teachingPlanService.get(ids[i]);
            teachingPlanService.delete(teachingPlan.getId());
            titles[i] = teachingPlan.getPlanTitle();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
        return AjaxObject.newOk("课件删除成功！").setCallbackType("").toString();
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

    private OaTeachingPlan initTeachingPlan(String planTitle, Long gradeId,
                                            Long subjectId, Long userId, String userName, SysAttachment attachment) {
        Date currentDate = new Date();
        OaTeachingPlan teachingPlan = new OaTeachingPlan();
        teachingPlan.setAttachmentId(attachment.getId());
        teachingPlan.setAttachmentTitle(attachment.getFileName());
        teachingPlan.setPlanTitle(planTitle);
        teachingPlan.setCreateTime(currentDate);
        teachingPlan.setCreateTimeLong(currentDate.getTime());

        OaGrade grade = gradeService.get(gradeId);
        if (grade != null) {
            teachingPlan.setGradeId(grade.getId());
            teachingPlan.setGradeName(grade.getGradeName());
        }
        OaSubject subject = subjectService.get(subjectId);
        if (subject != null) {
            teachingPlan.setSubjectId(subjectId);
            teachingPlan.setSubjectName(subject.getSubjectName());
        }
        teachingPlan.setTeacherId(userId);
        teachingPlan.setTeacherName(userName);

        return teachingPlan;
    }


}
