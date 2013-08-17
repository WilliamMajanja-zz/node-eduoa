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

import com.node.eduoa.entity.OaPurchaseApply;
import com.node.eduoa.enums.ApplyStatusEnum;
import com.node.eduoa.enums.SemesterEnum;
import com.node.eduoa.enums.StatusEnum;
import com.node.eduoa.service.PurchaseApplyService;
import com.node.eduoa.utils.YearUtils;
import com.node.eduoa.utils.model.PurchaseModel;
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
 * 采购申请管理
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午11:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/purchase")
public class PurchaseApplyController extends BaseFormController {

    @Qualifier("purchaseApplyServiceImpl")
    @Autowired
    private PurchaseApplyService purchaseApplyService;

	@Autowired
	private Validator validator;

	private static final String CREATE = "management/eduoa/purchase/create";
	private static final String UPDATE = "management/eduoa/purchase/update";
	private static final String UPDATE_SICK = "management/eduoa/purchase/update_sick";
	private static final String LIST = "management/eduoa/purchase/list";
	private static final String LIST_FINISH = "management/eduoa/purchase/list_finish";
	private static final String LIST_DRAFT = "management/eduoa/purchase/list_draft";
	private static final String LIST_APPROVAL = "management/eduoa/purchase/list_approval";
	private static final String VIEW = "management/eduoa/purchase/view";
	private static final String SICK_VIEW = "management/eduoa/purchase/view_sick";


    @RequiresPermissions("PurchaseDraft:view")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String preCreate(Map<String, Object> map) {
        map.put("user", currentUser.getUser().getTeacherInfo());
        map.put("organization", currentOrganization);
        map.put("createTime", new Date());
		return CREATE;
	}

	/**
	 * LogMessageObject的write用法实例。
	 */
    @Log(message="添加了{0}采购申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("PurchaseDraft:save")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody String create(PurchaseModel purchaseModel) {

		BeanValidators.validateWithException(validator, purchaseModel.getPurchaseApply());

        try {
            Date currentDate = new Date();
            purchaseModel.getPurchaseApply().setLeaderId(purchaseModel.getLeader().getLeaderId());
            purchaseModel.getPurchaseApply().setLeaderName(purchaseModel.getLeader().getLeaderName());
            purchaseModel.getPurchaseApply().setLeaderPosition(purchaseModel.getLeader().getLeaderPosition());
            purchaseModel.getPurchaseApply().setStatue(StatusEnum.Uncommitted.getIndex());
            purchaseModel.getPurchaseApply().setApplyStatue(ApplyStatusEnum.Normal.getIndex());
            purchaseModel.getPurchaseApply().setCreateTime(currentDate);

            purchaseModel.getPurchaseApply().setApplyTeacherId(getCurrentUser().getUser().getTeacherInfo().getId());
            purchaseModel.getPurchaseApply().setApplyTeacherName(getCurrentUser().getUser().getTeacherInfo().getTeacherName());

            purchaseModel.getPurchaseApply().setApplyOrganizationId(currentOrganization.getId());
            purchaseModel.getPurchaseApply().setApplyOrganizationName(currentOrganization.getName());

            purchaseModel.getPurchaseApply().setGoodsId(null);


            purchaseApplyService.save(purchaseModel.getPurchaseApply());
        } catch (Exception e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

		// 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{purchaseModel.getPurchaseApply().getApplyTeacherName()}));
		return AjaxObject.newOk("保存采购申请成功！").toString();
	}

    /**
     * LogMessageObject的write用法实例。
     */
    @Log(message="添加了{0}采购申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("PurchaseDraft:save")
    @RequestMapping(value="/submit", method=RequestMethod.POST)
    public @ResponseBody String submit(PurchaseModel purchaseModel) {
        BeanValidators.validateWithException(validator, purchaseModel.getPurchaseApply());
        try {

            Date currentDate = new Date();
            purchaseModel.getPurchaseApply().setLeaderId(purchaseModel.getLeader().getLeaderId());
            purchaseModel.getPurchaseApply().setLeaderName(purchaseModel.getLeader().getLeaderName());
            purchaseModel.getPurchaseApply().setLeaderPosition(purchaseModel.getLeader().getLeaderPosition());
            purchaseModel.getPurchaseApply().setStatue(StatusEnum.Submitted.getIndex());
            purchaseModel.getPurchaseApply().setApplyStatue(ApplyStatusEnum.Normal.getIndex());
            purchaseModel.getPurchaseApply().setCreateTime(currentDate);

            purchaseModel.getPurchaseApply().setApplyTeacherId(getCurrentUser().getUser().getTeacherInfo().getId());
            purchaseModel.getPurchaseApply().setApplyTeacherName(getCurrentUser().getUser().getTeacherInfo().getTeacherName());
            purchaseModel.getPurchaseApply().setCommitTime(currentDate);
            purchaseModel.getPurchaseApply().setApplyOrganizationId(currentOrganization.getId());
            purchaseModel.getPurchaseApply().setApplyOrganizationName(currentOrganization.getName());

            purchaseModel.getPurchaseApply().setGoodsId(null);

            purchaseApplyService.save(purchaseModel.getPurchaseApply());

        } catch (Exception e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        // 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{purchaseModel.getPurchaseApply().getApplyTeacherName()}));
        return AjaxObject.newOk("提交采购申请成功！").toString();
    }

    @RequiresPermissions("PurchaseDraft:view")
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		OaPurchaseApply purchaseApply = purchaseApplyService.get(id);
		map.put("purchaseApply", purchaseApply);
		return UPDATE;
	}

    @RequiresPermissions("PurchaseApproval:sick")
    @RequestMapping(value="/sick/{id}", method=RequestMethod.GET)
    public String preSick(@PathVariable Long id, Map<String, Object> map) {
        OaPurchaseApply purchaseApply = purchaseApplyService.get(id);
        if (purchaseApply.getApplyStatue() == ApplyStatusEnum.Pass.getIndex()) {
            map.put("purchaseApply", purchaseApply);
            return UPDATE_SICK;
        } else {
            return AjaxObject.newError("请选择已经审批通过的采购条！").setCallbackType("").toString();
        }
    }


    @Log(message="修改了{0}的采购申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("PurchaseDraft:edit")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody String update(OaPurchaseApply purchaseApply) {
		BeanValidators.validateWithException(validator, purchaseApply);
		purchaseApplyService.update(purchaseApply);

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{purchaseApply.getApplyTeacherName()}));
		return AjaxObject.newOk("采购申请修改成功！").toString();
	}


    @Log(message="删除了{0}采购申请。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("PurchaseDraft:delete")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Long id) {
        OaPurchaseApply purchaseApply = purchaseApplyService.get(id);
		purchaseApplyService.delete(id);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{purchaseApply.getApplyTeacherName()}));
		return AjaxObject.newOk("采购申请删除成功！").setCallbackType("").toString();
	}

    @Log(message="提交了{0}采购申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("PurchaseDraft:save")
	@RequestMapping(value="/commit", method=RequestMethod.POST)
	public @ResponseBody String doSubmit(Long[] ids) {

        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaPurchaseApply purchaseApply = purchaseApplyService.get(ids[i]);
            purchaseApply.setStatue(StatusEnum.Submitted.getIndex());
            purchaseApply.setCommitTime(new Date());
            purchaseApplyService.update(purchaseApply);
            titles[i] = purchaseApply.getApplyTeacherName();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
		return AjaxObject.newOk("采购申请提交成功！").setCallbackType("").toString();
	}


	/**
	 * Log的override用法实例
	 * 假如override为true，会忽略掉level
	 *
	 * 批量删除展示
	 */
	@Log(message="删除了{0}采购申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("PurchaseDraft:delete")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody String deleteMany(Long[] ids) {
		String[] titles = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			OaPurchaseApply purchaseApply = purchaseApplyService.get(ids[i]);
			purchaseApplyService.delete(purchaseApply.getId());
			titles[i] = purchaseApply.getApplyTeacherName();
		}

		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
		return AjaxObject.newOk("采购申请删除成功！").setCallbackType("").toString();
	}

	@RequiresPermissions("PurchasePermit:view")
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, String keywords, Map<String, Object> map) {

        page.setOrderField("applyTime");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put(SearchFilter.Operator.EQ + "_leaderId", getCurrentUser().getUser().getTeacherInfo().getId()+"");
        searchParam.put(SearchFilter.Operator.EQ + "_statue", StatusEnum.Submitted.getIndex() + "");
        searchParam.put(SearchFilter.Operator.EQ + "_applyStatue", ApplyStatusEnum.Normal.getIndex() + "");
        List<OaPurchaseApply> purchaseApplies = null;
        if (StringUtils.isNotBlank(keywords)) {
            searchParam.put(SearchFilter.Operator.LIKE + "_goodsName", keywords);
            purchaseApplies = purchaseApplyService.findByPurchaseApplyCondition(page, searchParam);
        } else {
            purchaseApplies = purchaseApplyService.findByPurchaseApplyCondition(page, searchParam);
        }

		map.put("page", page);
		map.put("purchaseApplies", purchaseApplies);
		map.put("keywords", keywords);

		return LIST;
	}

    @RequiresPermissions("PurchaseFinish:view")
    @RequestMapping(value="/listFinish", method={RequestMethod.GET, RequestMethod.POST})
    public String listFinish(Page page, String keywords, Map<String, Object> map) {

        OaPurchaseApply purchaseApply = new OaPurchaseApply();
        page.setOrderField("applyTime");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put(SearchFilter.Operator.EQ + "_leaderId", getCurrentUser().getUser().getTeacherInfo().getId()+"");
        searchParam.put(SearchFilter.Operator.EQ + "_statue", StatusEnum.Submitted.getIndex() + "");
        searchParam.put(SearchFilter.Operator.GTE + "_applyStatue", ApplyStatusEnum.Pass.getIndex() + "");
        List<OaPurchaseApply> purchaseApplies = null;
        if (StringUtils.isNotBlank(keywords)) {
            searchParam.put(SearchFilter.Operator.LIKE + "_goodsName", keywords);
            purchaseApplies = purchaseApplyService.findByPurchaseApplyCondition(page, searchParam);
        } else {
            purchaseApplies = purchaseApplyService.findByPurchaseApplyCondition(page, searchParam);
        }

        map.put("page", page);
        map.put("purchaseApplies", purchaseApplies);
        map.put("keywords", keywords);

        return LIST_FINISH;
    }

    @Log(message="同意了{0}采购申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("PurchasePermit:pass")
    @RequestMapping(value="/passed", method=RequestMethod.POST)
    public @ResponseBody String doPass(Long[] ids) {

        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaPurchaseApply purchaseApply = purchaseApplyService.get(ids[i]);
            purchaseApply.setApplyStatue(ApplyStatusEnum.Pass.getIndex());
            purchaseApply.setApplyTime(new Date());
            purchaseApplyService.update(purchaseApply);
            titles[i] = purchaseApply.getApplyTeacherName();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
        return AjaxObject.newOk("申请通过了！").setCallbackType("").toString();
    }

    @Log(message="驳回了{0}采购申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("PurchasePermit:reject")
    @RequestMapping(value="/rejected", method=RequestMethod.POST)
    public @ResponseBody String doReject(Long[] ids) {

        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaPurchaseApply purchaseApply = purchaseApplyService.get(ids[i]);
            purchaseApply.setApplyStatue(ApplyStatusEnum.Reject.getIndex());
            purchaseApply.setApplyTime(new Date());
            purchaseApplyService.update(purchaseApply);
            titles[i] = purchaseApply.getApplyTeacherName();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
        return AjaxObject.newOk("申请被驳回了！").setCallbackType("").toString();
    }

    @RequiresPermissions("PurchaseDraft:view")
    @RequestMapping(value="/listDraft", method={RequestMethod.GET, RequestMethod.POST})
    public String listDraft(Page page, String keywords, Map<String, Object> map) {

        page.setOrderField("applyTime");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put(SearchFilter.Operator.EQ + "_applyTeacherId", getCurrentUser().getUser().getTeacherInfo().getId()+"");
        searchParam.put(SearchFilter.Operator.EQ + "_statue", StatusEnum.Uncommitted.getIndex()+"");
        List<OaPurchaseApply> purchaseApplies = null;
        if (StringUtils.isNotBlank(keywords)) {
            searchParam.put(SearchFilter.Operator.LIKE + "_goodsName", keywords);
            purchaseApplies = purchaseApplyService.findByPurchaseApplyCondition(page, searchParam);
        } else {
            purchaseApplies = purchaseApplyService.findByPurchaseApplyCondition(page, searchParam);
        }

        map.put("page", page);
        map.put("purchaseApplies", purchaseApplies);
        map.put("keywords", keywords);

        return LIST_DRAFT;
    }

    @RequiresPermissions("PurchaseApproval:view")
    @RequestMapping(value="/listApproval", method={RequestMethod.GET, RequestMethod.POST})
    public String listApproval(Page page, String keywords, Map<String, Object> map) {

        OaPurchaseApply purchaseApply = new OaPurchaseApply();
        page.setOrderField("applyStatue");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put(SearchFilter.Operator.EQ + "_applyTeacherId", getCurrentUser().getUser().getTeacherInfo().getId() + "");
        searchParam.put(SearchFilter.Operator.EQ + "_statue", StatusEnum.Submitted.getIndex() + "");
        List<OaPurchaseApply> purchaseApplies = null;
        if (StringUtils.isNotBlank(keywords)) {
            searchParam.put(SearchFilter.Operator.LIKE + "_goodsName", keywords);
            purchaseApplies = purchaseApplyService.findByPurchaseApplyCondition(page, searchParam);
        } else {
            purchaseApplies = purchaseApplyService.findByPurchaseApplyCondition(page, searchParam);
        }

        map.put("page", page);
        map.put("purchaseApplies", purchaseApplies);
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
	@RequiresPermissions("PurchaseDraft:look")
	@RequestMapping(value="/view/{id}", method={RequestMethod.GET})
	public String view(@PathVariable Long id, Map<String, Object> map) {
		OaPurchaseApply purchaseApply = purchaseApplyService.get(id);
		map.put("purchaseApply", purchaseApply);
        map.put("semesterEnums", SemesterEnum.values());
		return VIEW;
	}
}
