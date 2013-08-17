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

import com.node.eduoa.entity.OaLeaveAttendance;
import com.node.eduoa.entity.OaTeacherInfo;
import com.node.eduoa.enums.AttendanceEnum;
import com.node.eduoa.service.LeaveAttendanceService;
import com.node.eduoa.service.RegistrationAttendanceService;
import com.node.eduoa.service.impl.LeaveAttendanceServiceImpl;
import com.node.eduoa.utils.WeekUtils;
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
import org.springside.modules.persistence.SearchFilter;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 
 * 考勤管理
 * User: linfeng at Administrator
 * Date: 13-7-7
 * Time: 上午11:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/management/eduoa/attendance")
public class LeaveAttendanceController extends BaseFormController {

    @Qualifier("leaveAttendanceServiceImpl")
    @Autowired
    private LeaveAttendanceService leaveAttendanceService;

	private static final String LIST = "management/eduoa/attendance/leave";
	private static final String LEAVE_LIST = "management/eduoa/attendance/leave_list";

    @Log(message="{0}执行了离校。", level=LogLevel.INFO)
    @RequiresPermissions("LeaveAttendance:leaveStart")
    @RequestMapping(value="/leaveStart/{id}", method=RequestMethod.POST)
    public @ResponseBody String leaveStart(@PathVariable Long id) {

        OaLeaveAttendance leaveAttendance = leaveAttendanceService.get(id);
        leaveAttendance.setLeaveStartTime(new Date());
        leaveAttendance.setLeaveStart(1);
        leaveAttendanceService.save(leaveAttendance);

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{leaveAttendance.getTeacherName()}));
        return AjaxObject.newOk("离校操作成功！").setCallbackType("").toString();
    }

    @RequiresPermissions("LeaveAttendance:leaveEnd")
    @RequestMapping(value="/checkStartTime/{id}", method=RequestMethod.POST)
    public @ResponseBody String checkStartTime(@PathVariable Long id) {
        OaLeaveAttendance leaveAttendance = leaveAttendanceService.get(id);
        if (leaveAttendance.getLeaveStartTime() != null) {
            return AjaxObject.newOk("可以执行返校操作！").setCallbackType("").toString();
        } else {
            return AjaxObject.newError("请先离校再返校！").setCallbackType("").toString();
        }
    }

    @Log(message="{0}执行了返校。", level=LogLevel.INFO)
    @RequiresPermissions("LeaveAttendance:leaveEnd")
    @RequestMapping(value="/leaveEnd/{id}", method=RequestMethod.POST)
    public @ResponseBody String leaveEnd(@PathVariable Long id) {

        WeekUtils weekUtils = new WeekUtils();
        Date currentDate = new Date();
        OaLeaveAttendance leaveAttendance = leaveAttendanceService.get(id);
        leaveAttendance.setLeaveEndTime(currentDate);
        leaveAttendance.setLeaveEnd(1);
        String leaveTime = weekUtils.compileDateOfDate(leaveAttendance.getLeaveStartTime(), leaveAttendance.getLeaveEndTime());
        leaveAttendance.setLeaveTimeCn(leaveTime);

        leaveAttendanceService.save(leaveAttendance);

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{leaveAttendance.getTeacherName()}));
        return AjaxObject.newOk("返校操作成功！").setCallbackType("").toString();
    }


	@RequiresPermissions("LeaveAttendance:view")
	@RequestMapping(value="/leave", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, String keywords, Map<String, Object> map) {

        WeekUtils weekUtils = new WeekUtils();

        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put(SearchFilter.Operator.GTE + "_attendanceDate", weekUtils.getConvertStringToDate(weekUtils.getCurrentMonday()).getTime() + "");
        searchParams.put(SearchFilter.Operator.LTE + "_attendanceDate", weekUtils.getConvertStringToDate(weekUtils.getSunday()).getTime() + "");
        List<OaLeaveAttendance> leaveAttendanceList = leaveAttendanceService.findByCondition(page, searchParams);
        if (leaveAttendanceList == null || leaveAttendanceList.isEmpty()) {
            OaTeacherInfo teacherInfo = currentUser.getUser().getTeacherInfo();
            List<String> weekList = weekUtils.getCurrentDateOfWeek();
            OaLeaveAttendance registrationAttendance = null;
            for (String week: weekList) {
                registrationAttendance = new OaLeaveAttendance(weekUtils.getConvertStringToDate(week).getTime(), week,
                        teacherInfo.getId(), teacherInfo.getTeacherName(), currentOrganization.getId(),
                        currentOrganization.getName(), new Date());
                leaveAttendanceService.save(registrationAttendance);
            }
            leaveAttendanceList = leaveAttendanceService.findByCondition(page, searchParams);
        }

        String currentDay = weekUtils.getCurrentDay();
        for (OaLeaveAttendance leaveAttendance: leaveAttendanceList) {
            String day = leaveAttendance.getAttendanceDateCn();
            if (currentDay.equals(day)) {
                leaveAttendance.setAttendanceDateCn(leaveAttendance.getAttendanceDateCn() + "(今日)");
                if (leaveAttendance.getLeaveStartTime() == null) {
                    leaveAttendance.setLeaveStartDisplay(1);
                    leaveAttendance.setLeaveEndDisplay(0);
                } else if (leaveAttendance.getLeaveEndTime() == null) {
                    leaveAttendance.setLeaveEndDisplay(1);
                }
            }
        }

		map.put("page", page);
		map.put("leaveAttendances", leaveAttendanceList);
		map.put("keywords", keywords);

		return LIST;
	}

    @RequiresPermissions("listLeave:view")
    @RequestMapping(value="/listLeave", method={RequestMethod.GET, RequestMethod.POST})
    public String listLeave(Page page, String startTime, String endTime, String teacherId,
                            String organizationId, String teacherName,
                            String organizationName, Map<String, Object> map) {

        WeekUtils weekUtils = new WeekUtils();

        Boolean existCondition = false;
        Map<String, Object> searchParams = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(teacherId)) {
            searchParams.put(SearchFilter.Operator.EQ + "_teacherId", teacherId);
            existCondition = true;
        }
        if (StringUtils.isNotBlank(organizationId)) {
            searchParams.put(SearchFilter.Operator.EQ + "_organizationId", organizationId);
            existCondition = true;
        }
        if (StringUtils.isNotBlank(startTime)) {
            searchParams.put(SearchFilter.Operator.GTE + "_attendanceDate", weekUtils.getConvertStringToDate(startTime).getTime()+"");
            existCondition = true;
        }
        if (StringUtils.isNotBlank(endTime)) {
            searchParams.put(SearchFilter.Operator.LTE + "_attendanceDate", weekUtils.getConvertStringToDate(endTime).getTime()+"");
            existCondition = true;
        }

        if (!existCondition) {
            searchParams.put(SearchFilter.Operator.EQ + "_teacherId", currentUser.getUser().getTeacherInfo().getId() + "");
            searchParams.put(SearchFilter.Operator.GTE + "_attendanceDate", weekUtils.getConvertStringToDate(weekUtils.getCurrentMonday()).getTime() + "");
            searchParams.put(SearchFilter.Operator.LTE + "_attendanceDate", weekUtils.getConvertStringToDate(weekUtils.getSunday()).getTime() + "");
        }

        List<OaLeaveAttendance> leaveAttendanceList = leaveAttendanceService.findByCondition(page, searchParams);

        String currentDay = weekUtils.getCurrentDay();
        for (OaLeaveAttendance leaveAttendance: leaveAttendanceList) {
            String day = leaveAttendance.getAttendanceDateCn();
            if (currentDay.equals(day)) {
                leaveAttendance.setAttendanceDateCn(leaveAttendance.getAttendanceDateCn() + "(今日)");
            }
        }

        map.put("page", page);
        map.put("leaveAttendances", leaveAttendanceList);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("teacherId", teacherId);
        map.put("organizationId", organizationId);
        map.put("teacherName", teacherName);
        map.put("organizationName", organizationName);

        return LEAVE_LIST;
    }

}
