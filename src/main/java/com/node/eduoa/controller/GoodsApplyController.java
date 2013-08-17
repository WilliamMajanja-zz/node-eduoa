/**
 * <pre>
 * Copyright:		Copyright(C) 2012-2013, node.com
 * Filename:		com.node.sample.controller.TaskController.java
 * Class:			TaskController
 * Date:			2013-4-21
 * Author:			<a href="mailto:node@gmail.com">node</a>
 * Version          2.0.0
 * Description:		
 *
 * </pre>
 **/
 
package com.node.eduoa.controller;

import com.node.eduoa.entity.OaGoodsApply;
import com.node.eduoa.entity.OaGoodsReceive;
import com.node.eduoa.enums.ApplyStatusEnum;
import com.node.eduoa.enums.SemesterEnum;
import com.node.eduoa.enums.StatusEnum;
import com.node.eduoa.service.GoodsApplyService;
import com.node.eduoa.service.GoodsReceiveService;
import com.node.eduoa.service.impl.GoodsReceiveServiceImpl;
import com.node.eduoa.utils.YearUtils;
import com.node.eduoa.utils.model.GoodsModel;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.beanvalidator.BeanValidators;
import org.springside.modules.persistence.SearchFilter;

import javax.validation.Validator;
import java.util.*;

/** 
 * 领物申请管理
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午11:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/goods")
public class GoodsApplyController extends BaseFormController {

    @Qualifier("goodsApplyServiceImpl")
    @Autowired
    private GoodsApplyService goodsApplyService;

    @Qualifier("goodsReceiveServiceImpl")
    @Autowired
    private GoodsReceiveService goodsReceiveService;

	@Autowired
	private Validator validator;

	private static final String CREATE = "management/eduoa/goods/create";
	private static final String UPDATE = "management/eduoa/goods/update";
	private static final String UPDATE_SICK = "management/eduoa/goods/update_sick";
	private static final String LIST = "management/eduoa/goods/list";
	private static final String LIST_FINISH = "management/eduoa/goods/list_finish";
	private static final String LIST_DRAFT = "management/eduoa/goods/list_draft";
	private static final String LIST_APPROVAL = "management/eduoa/goods/list_approval";
	private static final String VIEW = "management/eduoa/goods/view";
	private static final String SICK_VIEW = "management/eduoa/goods/view_sick";



    @RequiresPermissions("GoodsApply:view")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String preCreate(Map<String, Object> map) {
        map.put("semesterEnums", SemesterEnum.values());
        map.put("years", YearUtils.getYears(3));
        map.put("user", currentUser.getUser().getTeacherInfo());
        map.put("organization", currentOrganization);
        map.put("createTime", new Date());
		return CREATE;
	}

	/**
	 * LogMessageObject的write用法实例。
	 */
    @Log(message="添加了{0}领物申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("GoodsApply:save")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody String create(GoodsModel goodsModel) {

		BeanValidators.validateWithException(validator, goodsModel.getGoodsApply());

        try {
            Date currentDate = new Date();
            goodsModel.getGoodsApply().setLeaderId(goodsModel.getLeader().getLeaderId());
            goodsModel.getGoodsApply().setLeaderName(goodsModel.getLeader().getLeaderName());
            goodsModel.getGoodsApply().setLeaderPosition(goodsModel.getLeader().getLeaderPosition());
            goodsModel.getGoodsApply().setStatue(StatusEnum.Uncommitted.getIndex());
            goodsModel.getGoodsApply().setApplyStatue(ApplyStatusEnum.Normal.getIndex());
            goodsModel.getGoodsApply().setCreateTime(currentDate);

            goodsModel.getGoodsApply().setApplyTeacherId(getCurrentUser().getUser().getTeacherInfo().getId());
            goodsModel.getGoodsApply().setApplyTeacherName(getCurrentUser().getUser().getTeacherInfo().getTeacherName());

            goodsModel.getGoodsApply().setApplyOrganizationId(currentOrganization.getId());
            goodsModel.getGoodsApply().setApplyOrganizationName(currentOrganization.getName());

            goodsModel.getGoodsApply().setGoodsId(null);


            goodsApplyService.save(goodsModel.getGoodsApply());
        } catch (Exception e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

		// 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{goodsModel.getGoodsApply().getApplyTeacherName()}));
		return AjaxObject.newOk("领物申请成功！").toString();
	}

    /**
     * LogMessageObject的write用法实例。
     */
    @Log(message="添加了{0}领物申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("GoodsApply:save")
    @RequestMapping(value="/submit", method=RequestMethod.POST)
    public @ResponseBody String submit(GoodsModel goodsModel) {
        BeanValidators.validateWithException(validator, goodsModel.getGoodsApply());
        try {

            Date currentDate = new Date();
            goodsModel.getGoodsApply().setLeaderId(goodsModel.getLeader().getLeaderId());
            goodsModel.getGoodsApply().setLeaderName(goodsModel.getLeader().getLeaderName());
            goodsModel.getGoodsApply().setLeaderPosition(goodsModel.getLeader().getLeaderPosition());
            goodsModel.getGoodsApply().setStatue(StatusEnum.Submitted.getIndex());
            goodsModel.getGoodsApply().setApplyStatue(ApplyStatusEnum.Normal.getIndex());
            goodsModel.getGoodsApply().setCreateTime(currentDate);

            goodsModel.getGoodsApply().setApplyTeacherId(getCurrentUser().getUser().getTeacherInfo().getId());
            goodsModel.getGoodsApply().setApplyTeacherName(getCurrentUser().getUser().getTeacherInfo().getTeacherName());
            goodsModel.getGoodsApply().setCommitTime(currentDate);
            goodsModel.getGoodsApply().setApplyOrganizationId(currentOrganization.getId());
            goodsModel.getGoodsApply().setApplyOrganizationName(currentOrganization.getName());

            goodsModel.getGoodsApply().setGoodsId(null);

            goodsApplyService.save(goodsModel.getGoodsApply());

        } catch (Exception e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        // 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{goodsModel.getGoodsApply().getApplyTeacherName()}));
        return AjaxObject.newOk("领物申请成功！").toString();
    }

    @RequiresPermissions("GoodsApply:view")
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		OaGoodsApply goodsApply = goodsApplyService.get(id);

		map.put("goodsApply", goodsApply);
        map.put("years", YearUtils.getYears(3));
        map.put("semesterEnums", SemesterEnum.values());

		return UPDATE;
	}

    @RequiresPermissions("GoodsApproval:sick")
    @RequestMapping(value="/sick/{id}", method=RequestMethod.GET)
    public String preSick(@PathVariable Long id, Map<String, Object> map) {
        OaGoodsApply goodsApply = goodsApplyService.get(id);
        if (goodsApply.getApplyStatue() == ApplyStatusEnum.Pass.getIndex()) {
            map.put("goodsApply", goodsApply);
            return UPDATE_SICK;
        } else {
            return AjaxObject.newError("请选择已经审批通过的领物条！").setCallbackType("").toString();
        }
    }

    @RequiresPermissions("GoodsApproval:sick")
    @RequestMapping(value="/checkSick/{id}", method=RequestMethod.POST)
    public @ResponseBody String checkSick(@PathVariable Long id) {
        OaGoodsApply goodsApply = goodsApplyService.get(id);
        if (goodsApply.getApplyStatue() != ApplyStatusEnum.Pass.getIndex()) {
            return AjaxObject.newError("请等待申请通过之后操作！").setCallbackType("").toString();
        }
        if (goodsApply.getLave() != null && goodsApply.getLave() <= 0) {
            return AjaxObject.newError("该物品已经全部领取！").setCallbackType("").toString();
        }
        return AjaxObject.newOk("打开窗口验证成功！").setCallbackType("").toString();
    }

    @Log(message="{0}申领的物品进行了领取。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("GoodsApproval:sick")
    @RequestMapping(value="/sick", method=RequestMethod.POST)
    public @ResponseBody String updateSick(GoodsModel goodsModel) {

        OaGoodsApply goodsApply = goodsApplyService.get(goodsModel.getGoodsApply().getId());
        goodsApply.setSickTime(goodsModel.getGoodsApply().getSickTime());

        if (goodsApply.getLave() > 0) {


            OaGoodsReceive goodsReceive = goodsModel.getGoodsReceive();
            goodsReceive.setGoodsApplyId(goodsApply.getId());
            goodsReceive.setRecipientsId(currentUser.getUser().getTeacherInfo().getId());
            goodsReceive.setRecipientsName(currentUser.getUser().getTeacherInfo().getTeacherName());
            goodsReceive.setRecipientsTime(new Date());
            goodsReceive.setCreateTime(new Date());
            Integer lave = goodsApply.getGoodsCount() - goodsReceive.getRecipientsCount();
            goodsApply.setLave(lave);

            goodsApplyService.update(goodsApply);

            goodsReceiveService.save(goodsReceive);
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{goodsApply.getApplyTeacherName()}));
        return AjaxObject.newOk("领取成功！").toString();
    }

    @RequiresPermissions("GoodsApproval:sick")
    @RequestMapping(value="/viewSick/{id}", method={RequestMethod.GET})
    public String viewSick(@PathVariable Long id, Map<String, Object> map) {
        OaGoodsApply goodsApply = goodsApplyService.get(id);
        map.put("goodsApply", goodsApply);
        if (goodsApply != null) {
            List<OaGoodsReceive> goodsReceives = goodsReceiveService.findByCondition(goodsApply.getId());
            map.put("goodsReceives", goodsReceives);
        }
        return SICK_VIEW;
    }

    @Log(message="修改了{0}的领物申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("GoodsApply:edit")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody String update(OaGoodsApply goodsApply) {
		BeanValidators.validateWithException(validator, goodsApply);
		goodsApplyService.update(goodsApply);

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{goodsApply.getApplyTeacherName()}));
		return AjaxObject.newOk("领物申请修改成功！").toString();
	}


    @Log(message="删除了{0}领物申请。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("GoodsApply:delete")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Long id) {
        OaGoodsApply goodsApply = goodsApplyService.get(id);
		goodsApplyService.delete(id);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{goodsApply.getApplyTeacherName()}));
		return AjaxObject.newOk("领物申请删除成功！").setCallbackType("").toString();
	}

    @Log(message="提交了{0}领物申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("GoodsApply:save")
	@RequestMapping(value="/commit", method=RequestMethod.POST)
	public @ResponseBody String doSubmit(Long[] ids) {

        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaGoodsApply goodsApply = goodsApplyService.get(ids[i]);
            goodsApply.setStatue(StatusEnum.Submitted.getIndex());
            goodsApply.setCommitTime(new Date());
            goodsApplyService.update(goodsApply);
            titles[i] = goodsApply.getApplyTeacherName();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
		return AjaxObject.newOk("领物申请提交成功！").setCallbackType("").toString();
	}


	/**
	 * Log的override用法实例
	 * 假如override为true，会忽略掉level
	 *
	 * 批量删除展示
	 */
	@Log(message="删除了{0}领物申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("GoodsApply:delete")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody String deleteMany(Long[] ids) {
		String[] titles = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			OaGoodsApply goodsApply = goodsApplyService.get(ids[i]);
			goodsApplyService.delete(goodsApply.getId());
			titles[i] = goodsApply.getApplyTeacherName();
		}

		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
		return AjaxObject.newOk("领物申请删除成功！").setCallbackType("").toString();
	}

	@RequiresPermissions("GoodsPermit:view")
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, String keywords, Map<String, Object> map) {

        OaGoodsApply goodsApply = new OaGoodsApply();
        page.setOrderField("applyTime");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put(SearchFilter.Operator.EQ + "_leaderId", getCurrentUser().getUser().getTeacherInfo().getId()+"");
        searchParam.put(SearchFilter.Operator.EQ + "_statue", StatusEnum.Submitted.getIndex() + "");
        searchParam.put(SearchFilter.Operator.EQ + "_applyStatue", ApplyStatusEnum.Normal.getIndex() + "");
        List<OaGoodsApply> leavePermits = null;
        if (StringUtils.isNotBlank(keywords)) {
            searchParam.put(SearchFilter.Operator.LIKE + "_goodsName", keywords);
            leavePermits = goodsApplyService.findByGoodsApplyCondition(page, goodsApply, searchParam);
        } else {
            leavePermits = goodsApplyService.findByGoodsApplyCondition(page, goodsApply, searchParam);
        }

		map.put("page", page);
		map.put("leavePermits", leavePermits);
		map.put("keywords", keywords);

		return LIST;
	}

    @RequiresPermissions("GoodsFinish:view")
    @RequestMapping(value="/listFinish", method={RequestMethod.GET, RequestMethod.POST})
    public String listFinish(Page page, String keywords, Map<String, Object> map) {

        OaGoodsApply goodsApply = new OaGoodsApply();
        page.setOrderField("applyTime");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put(SearchFilter.Operator.EQ + "_leaderId", getCurrentUser().getUser().getTeacherInfo().getId()+"");
        searchParam.put(SearchFilter.Operator.EQ + "_statue", StatusEnum.Submitted.getIndex() + "");
        searchParam.put(SearchFilter.Operator.GTE + "_applyStatue", ApplyStatusEnum.Pass.getIndex() + "");
        List<OaGoodsApply> leavePermits = null;
        if (StringUtils.isNotBlank(keywords)) {
            searchParam.put(SearchFilter.Operator.LIKE + "_goodsName", keywords);
            leavePermits = goodsApplyService.findByGoodsApplyCondition(page, goodsApply, searchParam);
        } else {
            leavePermits = goodsApplyService.findByGoodsApplyCondition(page, goodsApply, searchParam);
        }

        map.put("page", page);
        map.put("leavePermits", leavePermits);
        map.put("keywords", keywords);

        return LIST_FINISH;
    }

    @Log(message="同意了{0}领物申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("GoodsPermit:pass")
    @RequestMapping(value="/passed", method=RequestMethod.POST)
    public @ResponseBody String doPass(Long[] ids) {

        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaGoodsApply goodsApply = goodsApplyService.get(ids[i]);
            goodsApply.setApplyStatue(ApplyStatusEnum.Pass.getIndex());
            goodsApply.setApplyTime(new Date());
            goodsApply.setLave(goodsApply.getGoodsCount());
            goodsApplyService.update(goodsApply);
            titles[i] = goodsApply.getApplyTeacherName();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
        return AjaxObject.newOk("申请通过了！").setCallbackType("").toString();
    }

    @Log(message="驳回了{0}领物申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("GoodsPermit:reject")
    @RequestMapping(value="/rejected", method=RequestMethod.POST)
    public @ResponseBody String doReject(Long[] ids) {

        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaGoodsApply goodsApply = goodsApplyService.get(ids[i]);
            goodsApply.setApplyStatue(ApplyStatusEnum.Reject.getIndex());
            goodsApply.setApplyTime(new Date());
            goodsApplyService.update(goodsApply);
            titles[i] = goodsApply.getApplyTeacherName();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
        return AjaxObject.newOk("申请被驳回了！").setCallbackType("").toString();
    }

    @RequiresPermissions("GoodsApply:view")
    @RequestMapping(value="/listDraft", method={RequestMethod.GET, RequestMethod.POST})
    public String listDraft(Page page, String keywords, Map<String, Object> map) {

        OaGoodsApply goodsApply = new OaGoodsApply();
        page.setOrderField("applyTime");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put(SearchFilter.Operator.EQ + "_applyTeacherId", getCurrentUser().getUser().getTeacherInfo().getId()+"");
        searchParam.put(SearchFilter.Operator.EQ + "_statue", StatusEnum.Uncommitted.getIndex()+"");
        List<OaGoodsApply> leavePermits = null;
        if (StringUtils.isNotBlank(keywords)) {
            searchParam.put(SearchFilter.Operator.LIKE + "_goodsName", keywords);
            leavePermits = goodsApplyService.findByGoodsApplyCondition(page, goodsApply, searchParam);
        } else {
            leavePermits = goodsApplyService.findByGoodsApplyCondition(page, goodsApply, searchParam);
        }

        map.put("page", page);
        map.put("leavePermits", leavePermits);
        map.put("keywords", keywords);

        return LIST_DRAFT;
    }

    @RequiresPermissions("GoodsApproval:view")
    @RequestMapping(value="/listApproval", method={RequestMethod.GET, RequestMethod.POST})
    public String listApproval(Page page, String keywords, Map<String, Object> map) {

        OaGoodsApply goodsApply = new OaGoodsApply();
        page.setOrderField("applyStatue");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put(SearchFilter.Operator.EQ + "_applyTeacherId", getCurrentUser().getUser().getTeacherInfo().getId() + "");
        searchParam.put(SearchFilter.Operator.EQ + "_statue", StatusEnum.Submitted.getIndex() + "");
        List<OaGoodsApply> leavePermits = null;
        if (StringUtils.isNotBlank(keywords)) {
            searchParam.put(SearchFilter.Operator.LIKE + "_goodsName", keywords);
            leavePermits = goodsApplyService.findByGoodsApplyCondition(page, goodsApply, searchParam);
        } else {
            leavePermits = goodsApplyService.findByGoodsApplyCondition(page, goodsApply, searchParam);
        }

        map.put("page", page);
        map.put("leavePermits", leavePermits);
        map.put("keywords", keywords);

        return LIST_APPROVAL;
    }

	/**
	 * 自定look权限，实例。
	 * 描述
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("GoodsApply:look")
	@RequestMapping(value="/view/{id}", method={RequestMethod.GET})
	public String view(@PathVariable Long id, Map<String, Object> map) {
		OaGoodsApply goodsApply = goodsApplyService.get(id);
		map.put("goodsApply", goodsApply);
        map.put("semesterEnums", SemesterEnum.values());
		return VIEW;
	}
}
