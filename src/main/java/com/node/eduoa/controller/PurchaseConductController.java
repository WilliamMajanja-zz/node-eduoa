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
import com.node.eduoa.entity.OaPurchaseConduct;
import com.node.eduoa.enums.ApplyStatusEnum;
import com.node.eduoa.enums.PurchaseEnum;
import com.node.eduoa.enums.SemesterEnum;
import com.node.eduoa.enums.StatusEnum;
import com.node.eduoa.service.PurchaseApplyService;
import com.node.eduoa.service.PurchaseConductService;
import com.node.eduoa.service.impl.PurchaseConductServiceImpl;
import com.node.eduoa.utils.model.ConductModel;
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
@RequestMapping("/management/eduoa/conduct")
public class PurchaseConductController extends BaseFormController {

    @Qualifier("purchaseApplyServiceImpl")
    @Autowired
    private PurchaseApplyService purchaseApplyService;

    @Qualifier("purchaseConductServiceImpl")
    @Autowired
    private PurchaseConductService purchaseConductService;


	@Autowired
	private Validator validator;

	private static final String CREATE = "management/eduoa/conduct/create";
	private static final String UPDATE = "management/eduoa/conduct/update";
	private static final String UPDATE_SICK = "management/eduoa/conduct/update_sick";
	private static final String LIST = "management/eduoa/conduct/list";
	private static final String LIST_FINISH = "management/eduoa/conduct/list_finish";
	private static final String APPLY_FINISH = "management/eduoa/conduct/apply_finish";
	private static final String LIST_DRAFT = "management/eduoa/conduct/list_draft";
	private static final String LIST_APPROVAL = "management/eduoa/conduct/list_approval";
	private static final String VIEW = "management/eduoa/conduct/view";
	private static final String SICK_VIEW = "management/eduoa/conduct/view_sick";

    @RequiresPermissions("PurchaseApplyFinish:add")
    @RequestMapping(value="/create/{id}", method=RequestMethod.GET)
    public String preCreate(@PathVariable Long id, Map<String, Object> map) {
        OaPurchaseApply purchaseApply = purchaseApplyService.get(id);
        OaPurchaseConduct purchaseConduct = new OaPurchaseConduct();
        purchaseConduct.setPurchaseId(purchaseApply.getId());
        purchaseConduct.setGoodsId(purchaseApply.getGoodsId());
        purchaseConduct.setGoodsName(purchaseApply.getGoodsName());
        purchaseConduct.setGoodsCount(purchaseApply.getGoodsCount());
        purchaseConduct.setGoodsUnit(purchaseApply.getGoodsUnit());

        map.put("purchaseConduct", purchaseConduct);
        map.put("purchaseApply", purchaseApply);
        map.put("user", currentUser.getUser().getTeacherInfo());
        map.put("organization", currentOrganization);
        map.put("createTime", new Date());
        return CREATE;
    }

	/**
	 * LogMessageObject的write用法实例。
	 */
    @Log(message="添加了{0}采购申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("PurchaseApplyFinish:save")
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public @ResponseBody String create(ConductModel conductModel) {

		BeanValidators.validateWithException(validator, conductModel.getPurchaseConduct());

        try {
            Date currentDate = new Date();
            conductModel.getPurchaseConduct().setLeaderId(conductModel.getLeader().getLeaderId());
            conductModel.getPurchaseConduct().setLeaderName(conductModel.getLeader().getLeaderName());
            conductModel.getPurchaseConduct().setLeaderPosition(conductModel.getLeader().getLeaderPosition());
            conductModel.getPurchaseConduct().setStatue(StatusEnum.Uncommitted.getIndex());
            conductModel.getPurchaseConduct().setApplyStatue(ApplyStatusEnum.Normal.getIndex());
            conductModel.getPurchaseConduct().setCreateTime(currentDate);

            conductModel.getPurchaseConduct().setConductTeacherId(getCurrentUser().getUser().getTeacherInfo().getId());
            conductModel.getPurchaseConduct().setConductTeacherName(getCurrentUser().getUser().getTeacherInfo().getTeacherName());

            conductModel.getPurchaseConduct().setConductOrganizationId(currentOrganization.getId());
            conductModel.getPurchaseConduct().setConductOrganizationName(currentOrganization.getName());

            conductModel.getPurchaseConduct().setGoodsId(null);


            purchaseConductService.save(conductModel.getPurchaseConduct());
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

		// 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{conductModel.getPurchaseConduct().getConductTeacherName()}));
		return AjaxObject.newOk("保存采购申请成功！").toString();
	}

    /**
     * LogMessageObject的write用法实例。
     */
    @Log(message="添加了{0}采购申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("PurchaseApplyFinish:save")
    @RequestMapping(value="/submit", method=RequestMethod.POST)
    public @ResponseBody String submit(ConductModel conductModel) {

        BeanValidators.validateWithException(validator, conductModel.getPurchaseConduct());

        try {
            Date currentDate = new Date();
            conductModel.getPurchaseConduct().setLeaderId(conductModel.getLeader().getLeaderId());
            conductModel.getPurchaseConduct().setLeaderName(conductModel.getLeader().getLeaderName());
            conductModel.getPurchaseConduct().setLeaderPosition(conductModel.getLeader().getLeaderPosition());
            conductModel.getPurchaseConduct().setStatue(StatusEnum.Submitted.getIndex());
            conductModel.getPurchaseConduct().setApplyStatue(ApplyStatusEnum.Normal.getIndex());
            conductModel.getPurchaseConduct().setCreateTime(currentDate);

            conductModel.getPurchaseConduct().setConductTeacherId(getCurrentUser().getUser().getTeacherInfo().getId());
            conductModel.getPurchaseConduct().setConductTeacherName(getCurrentUser().getUser().getTeacherInfo().getTeacherName());

            conductModel.getPurchaseConduct().setConductOrganizationId(currentOrganization.getId());
            conductModel.getPurchaseConduct().setConductOrganizationName(currentOrganization.getName());

            conductModel.getPurchaseConduct().setGoodsId(null);

            Long purchaseId = conductModel.getPurchaseConduct().getPurchaseId();
            OaPurchaseApply purchaseApply = purchaseApplyService.get(purchaseId);
            purchaseApply.setPurchase(PurchaseEnum.Purchased.getIndex());
            purchaseApplyService.save(purchaseApply);

            purchaseConductService.save(conductModel.getPurchaseConduct());
        } catch (Exception e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        // 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{conductModel.getPurchaseConduct().getConductTeacherName()}));
        return AjaxObject.newOk("提交采购申请成功！").toString();
    }

    @RequiresPermissions("PurchaseConductDraft:view")
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		OaPurchaseConduct purchaseConduct = purchaseConductService.get(id);
		map.put("purchaseConduct", purchaseConduct);
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


    @Log(message="删除了{0}采购申请。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("PurchaseConductDraft:delete")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Long id) {
        OaPurchaseConduct purchaseConduct = purchaseConductService.get(id);
        purchaseConductService.delete(id);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{purchaseConduct.getConductTeacherName()}));
		return AjaxObject.newOk("实物采购申请删除成功！").setCallbackType("").toString();
	}

    @Log(message="提交了{0}采购申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("PurchaseConductDraft:save")
	@RequestMapping(value="/commit", method=RequestMethod.POST)
	public @ResponseBody String doSubmit(Long[] ids) {

        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaPurchaseConduct purchaseConduct = purchaseConductService.get(ids[i]);
            purchaseConduct.setStatue(StatusEnum.Submitted.getIndex());
            purchaseConduct.setCommitTime(new Date());

            Long purchaseId = purchaseConduct.getPurchaseId();
            OaPurchaseApply purchaseApply = purchaseApplyService.get(purchaseId);
            purchaseApply.setPurchase(PurchaseEnum.Purchased.getIndex());
            purchaseApplyService.update(purchaseApply);

            purchaseConductService.update(purchaseConduct);
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
    @RequiresPermissions("PurchaseConductDraft:delete")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody String deleteMany(Long[] ids) {
		String[] titles = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			OaPurchaseConduct purchaseConduct = purchaseConductService.get(ids[i]);
            purchaseConductService.delete(purchaseConduct.getId());
			titles[i] = purchaseConduct.getConductTeacherName();
		}

		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
		return AjaxObject.newOk("采购申请删除成功！").setCallbackType("").toString();
	}

	@RequiresPermissions("PurchaseConductPermit:view")
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, String keywords, Map<String, Object> map) {

        page.setOrderField("applyTime");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put(SearchFilter.Operator.EQ + "_leaderId", getCurrentUser().getUser().getTeacherInfo().getId()+"");
        searchParam.put(SearchFilter.Operator.EQ + "_statue", StatusEnum.Submitted.getIndex() + "");
        searchParam.put(SearchFilter.Operator.EQ + "_applyStatue", ApplyStatusEnum.Normal.getIndex() + "");
        List<OaPurchaseConduct> purchaseApplies = null;
        if (StringUtils.isNotBlank(keywords)) {
            searchParam.put(SearchFilter.Operator.LIKE + "_goodsName", keywords);
            purchaseApplies = purchaseConductService.findByPurchaseConductCondition(page, searchParam);
        } else {
            purchaseApplies = purchaseConductService.findByPurchaseConductCondition(page, searchParam);
        }

		map.put("page", page);
		map.put("purchaseApplies", purchaseApplies);
		map.put("keywords", keywords);

		return LIST;
	}

    @RequiresPermissions("PurchaseApplyFinish:view")
    @RequestMapping(value="/applyFinish", method={RequestMethod.GET, RequestMethod.POST})
    public String applyFinish(Page page, String keywords, Map<String, Object> map) {

        page.setOrderField("applyTime");
        Map<String, Object> searchParam = new HashMap<String, Object>();
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

        return APPLY_FINISH;
    }

    @RequiresPermissions("PurchaseConductList:view")
    @RequestMapping(value="/listFinish", method={RequestMethod.GET, RequestMethod.POST})
    public String listFinish(Page page, String keywords, Map<String, Object> map) {

        page.setOrderField("applyTime");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put(SearchFilter.Operator.EQ + "_statue", StatusEnum.Submitted.getIndex() + "");
        searchParam.put(SearchFilter.Operator.GTE + "_applyStatue", ApplyStatusEnum.Pass.getIndex() + "");
        List<OaPurchaseConduct> purchaseApplies = null;
        if (StringUtils.isNotBlank(keywords)) {
            searchParam.put(SearchFilter.Operator.LIKE + "_goodsName", keywords);
            purchaseApplies = purchaseConductService.findByPurchaseConductCondition(page, searchParam);
        } else {
            purchaseApplies = purchaseConductService.findByPurchaseConductCondition(page, searchParam);
        }

        map.put("page", page);
        map.put("purchaseApplies", purchaseApplies);
        map.put("keywords", keywords);

        return LIST_FINISH;
    }

    @Log(message="同意了{0}采购申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("PurchaseConductPermit:pass")
    @RequestMapping(value="/passed", method=RequestMethod.POST)
    public @ResponseBody String doPass(Long[] ids) {

        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaPurchaseConduct purchaseApply = purchaseConductService.get(ids[i]);
            purchaseApply.setApplyStatue(ApplyStatusEnum.Pass.getIndex());
            purchaseApply.setApplyTime(new Date());
            purchaseConductService.update(purchaseApply);
            titles[i] = purchaseApply.getConductTeacherName();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
        return AjaxObject.newOk("申请通过了！").setCallbackType("").toString();
    }

    @Log(message="驳回了{0}采购申请。", level=LogLevel.TRACE, override=true)
    @RequiresPermissions("PurchaseConductPermit:reject")
    @RequestMapping(value="/rejected", method=RequestMethod.POST)
    public @ResponseBody String doReject(Long[] ids) {

        String[] titles = new String[ids.length];
        for (int i = 0; i < ids.length; i++) {
            OaPurchaseConduct purchaseApply = purchaseConductService.get(ids[i]);
            purchaseApply.setApplyStatue(ApplyStatusEnum.Reject.getIndex());
            purchaseApply.setApplyTime(new Date());
            purchaseConductService.update(purchaseApply);
            titles[i] = purchaseApply.getConductTeacherName();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
        return AjaxObject.newOk("申请采购被驳回了！").setCallbackType("").toString();
    }

    @RequiresPermissions("PurchaseConductDraft:view")
    @RequestMapping(value="/listDraft", method={RequestMethod.GET, RequestMethod.POST})
    public String listDraft(Page page, String keywords, Map<String, Object> map) {

        page.setOrderField("applyTime");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put(SearchFilter.Operator.EQ + "_conductTeacherId", getCurrentUser().getUser().getTeacherInfo().getId()+"");
        searchParam.put(SearchFilter.Operator.EQ + "_statue", StatusEnum.Uncommitted.getIndex()+"");
        List<OaPurchaseConduct> purchaseApplies = null;
        if (StringUtils.isNotBlank(keywords)) {
            searchParam.put(SearchFilter.Operator.LIKE + "_goodsName", keywords);
            purchaseApplies = purchaseConductService.findByPurchaseConductCondition(page, searchParam);
        } else {
            purchaseApplies = purchaseConductService.findByPurchaseConductCondition(page, searchParam);
        }

        map.put("page", page);
        map.put("purchaseApplies", purchaseApplies);
        map.put("keywords", keywords);

        return LIST_DRAFT;
    }

    @RequiresPermissions("PurchaseConductApproval:view")
    @RequestMapping(value="/listApproval", method={RequestMethod.GET, RequestMethod.POST})
    public String listApproval(Page page, String keywords, Map<String, Object> map) {

        page.setOrderField("applyStatue");
        Map<String, Object> searchParam = new HashMap<String, Object>();
        searchParam.put(SearchFilter.Operator.EQ + "_conductTeacherId", getCurrentUser().getUser().getTeacherInfo().getId() + "");
        searchParam.put(SearchFilter.Operator.EQ + "_statue", StatusEnum.Submitted.getIndex() + "");
        List<OaPurchaseConduct> purchaseApplies = null;
        if (StringUtils.isNotBlank(keywords)) {
            searchParam.put(SearchFilter.Operator.LIKE + "_goodsName", keywords);
            purchaseApplies = purchaseConductService.findByPurchaseConductCondition(page, searchParam);
        } else {
            purchaseApplies = purchaseConductService.findByPurchaseConductCondition(page, searchParam);
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
	@RequiresPermissions("PurchaseConductDraft:look")
	@RequestMapping(value="/view/{id}", method={RequestMethod.GET})
	public String view(@PathVariable Long id, Map<String, Object> map) {
		OaPurchaseApply purchaseApply = purchaseApplyService.get(id);
		map.put("purchaseApply", purchaseApply);
        map.put("semesterEnums", SemesterEnum.values());
		return VIEW;
	}
}
