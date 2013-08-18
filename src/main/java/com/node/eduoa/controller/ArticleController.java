package com.node.eduoa.controller;

import com.node.eduoa.entity.CmsArticle;
import com.node.eduoa.entity.CmsChannel;
import com.node.eduoa.entity.OaGrade;
import com.node.eduoa.entity.SysAttachment;
import com.node.eduoa.enums.AttachmentTypeEnum;
import com.node.eduoa.service.ArticleService;
import com.node.eduoa.service.ChannelService;
import com.node.system.log.LogMessageObject;
import com.node.system.log.impl.LogUitl;
import com.node.system.util.dwz.AjaxObject;
import com.node.system.util.dwz.Page;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.persistence.SearchFilter;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * User: linfeng
 * Date: 13-8-18
 * Time: 下午1:50
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/article")
public class ArticleController extends BaseFormController {

    private static final String CREATE = "management/eduoa/article/create";
    private static final String UPDATE = "management/eduoa/article/update";
    private static final String LIST = "management/eduoa/article/list";
    private static final String VIEW = "management/eduoa/article/view";

    private static final String jyxw = "jyxw"; //教育新闻
    private static final String xydt = "xydt"; //校园动态
    private static final String xxyd = "xxyd"; //学习园地
    private static final String zcfg = "zcfg"; //政策法规
    private static final String hdgg = "hdgg"; //活动公告
    private static final String jsfc = "jsfc"; //教师风采

    @Qualifier("articleServiceImpl")
    @Autowired
    private ArticleService articleService;
    @Qualifier("channelServiceImpl")
    @Autowired
    private ChannelService channelService;


    @RequestMapping(value = "/list/jyxw", method = {RequestMethod.GET, RequestMethod.POST})
    public String jyxw(Page page, String keywords, Map<String, Object> map) {

        CmsChannel channel = null;
        if (StringUtils.isNotBlank(jyxw)) {
            initQueryMethod(page, jyxw, keywords, map);
        }

        return LIST;

    }

    @RequestMapping(value = "/list/xydt", method = {RequestMethod.GET, RequestMethod.POST})
    public String xydt(Page page, String keywords, Map<String, Object> map) {
        CmsChannel channel = null;
        if (StringUtils.isNotBlank(xydt)) {
            initQueryMethod(page, xydt, keywords, map);
        }
        return LIST;
    }

    @RequestMapping(value = "/list/xxyd", method = {RequestMethod.GET, RequestMethod.POST})
    public String xxyd(Page page, String keywords, Map<String, Object> map) {
        CmsChannel channel = null;
        if (StringUtils.isNotBlank(xxyd)) {
            initQueryMethod(page, xxyd, keywords, map);
        }
        return LIST;
    }

    @RequestMapping(value = "/list/zcfg", method = {RequestMethod.GET, RequestMethod.POST})
    public String zcfg(Page page, String keywords, Map<String, Object> map) {
        CmsChannel channel = null;
        if (StringUtils.isNotBlank(zcfg)) {
            initQueryMethod(page, zcfg, keywords, map);
        }
        return LIST;
    }

    @RequestMapping(value = "/list/hdgg", method = {RequestMethod.GET, RequestMethod.POST})
    public String hdgg(Page page, String keywords, Map<String, Object> map) {
        CmsChannel channel = null;
        if (StringUtils.isNotBlank(hdgg)) {
            initQueryMethod(page, hdgg, keywords, map);
        }
        return LIST;
    }

    @RequestMapping(value = "/list/jsfc", method = {RequestMethod.GET, RequestMethod.POST})
    public String jsfc(Page page, String keywords, Map<String, Object> map) {
        CmsChannel channel = null;
        if (StringUtils.isNotBlank(jsfc)) {
            initQueryMethod(page, jsfc, keywords, map);
        }
        return LIST;
    }

    private void initQueryMethod(Page page, String channelCode, String keywords, Map<String, Object> map) {
        CmsChannel channel;
        channel = channelService.findByChannelCode(channelCode);
        List<CmsArticle> articles = null;
        if (StringUtils.isNotBlank(keywords)) {
            Map<String, Object> searchParam = new HashMap<String, Object>();
            searchParam.put(SearchFilter.Operator.LIKE + "_title", keywords);
            articles = articleService.find(page, channel.getId(), searchParam);
        } else {
            Map<String, Object> searchParam = new HashMap<String, Object>();
            articles = articleService.find(page, channel.getId(), searchParam);
        }
        List<CmsChannel> channels = channelService.findAllChannel();
        map.put("page", page);
        map.put("articles", articles);
        map.put("keywords", keywords);
        map.put("channelCode", channelCode);
        map.put("channels", channels);
        map.put("nav", "/management/eduoa/article/list/" + channelCode);
    }

    @RequestMapping(value = "/create/{channelCode}", method = RequestMethod.GET)
    public String preCreate(@PathVariable("channelCode") String channelCode, Map<String, Object> map) {
        map.put("channelCode", channelCode);
        return CREATE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public
    @ResponseBody
    String create(String title, String content, String channelCode, @RequestParam("uploadFile") MultipartFile uploadFile) {
        Date currentDate = new Date();
        try {

            SysAttachment attachment = new SysAttachment();
            try {
                if (uploadFile.getSize() > 0) {
                    attachment = uploadFile(uploadFile, AttachmentTypeEnum.Image);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            CmsArticle article = new CmsArticle();
            article.setTitle(title);
            article.setContent(content);
            article.setChannel(channelService.findByChannelCode(channelCode));
            article.setAttachmentId(attachment.getId());
            article.setFileName(attachment.getFileName());

            article.setCreateUserId(currentUser.getId());
            article.setCreateUserName(currentUser.getUser().getRealname());
            article.setCreateTime(currentDate);

            article.setUpdateTime(currentDate);
            article.setUpdateUserId(currentUser.getId());
            article.setUpdateUserName(currentUser.getUser().getRealname());

            articleService.save(article);
        } catch (Exception e) {
            return AjaxObject.newError("发布失败:" + e.getMessage()).setCallbackType("").toString();
        }
        return AjaxObject.newOk("发布成功！").toString();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        CmsArticle article = articleService.get(id);
        map.put("article", article);
        return UPDATE;
    }

    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public @ResponseBody String deleteMany(Long[] ids) {
        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            CmsArticle article = articleService.get(ids[i]);
            articleService.delete(article.getId());
            titles[i] = article.getTitle();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
        return AjaxObject.newOk("删除成功！").setCallbackType("").toString();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public
    @ResponseBody
    String update(Long id, String title, String content, String channelCode, @RequestParam("uploadFile") MultipartFile uploadFile) {
        Date currentDate = new Date();
        try {

            CmsArticle article = articleService.get(id);
            if (article == null) {
                article = new CmsArticle();
            }

            SysAttachment attachment = new SysAttachment();
            try {
                if (uploadFile.getSize() > 0) {
                    attachment = uploadFile(uploadFile, AttachmentTypeEnum.Image);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            article.setTitle(title);
            article.setContent(content);
            article.setChannel(channelService.findByChannelCode(channelCode));
            if (attachment.getId() != null) {
                article.setAttachmentId(attachment.getId());
                article.setFileName(attachment.getFileName());
            }

            article.setCreateUserId(currentUser.getId());
            article.setCreateUserName(currentUser.getUser().getRealname());
            article.setCreateTime(currentDate);

            article.setUpdateTime(currentDate);
            article.setUpdateUserId(currentUser.getId());
            article.setUpdateUserName(currentUser.getUser().getRealname());

            articleService.save(article);
        } catch (Exception e) {
            return AjaxObject.newError("发布失败:" + e.getMessage()).setCallbackType("").toString();
        }
        return AjaxObject.newOk("发布成功！").toString();
    }

    @RequestMapping(value = "/downloadFile/{id}", method = RequestMethod.GET)
    protected ResponseEntity<byte[]> downloadFile(@PathVariable Long id) throws IOException {

        SysAttachment attachment = attachmentService.get(id);
        String filePath = attachment.getServiceFile();
        File file = new File(filePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", attachment.getFileName());

        attachmentService.save(attachment);

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);

    }

}
