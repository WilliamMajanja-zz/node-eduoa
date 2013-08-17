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

import com.node.eduoa.entity.OaRegistrationAttendance;
import com.node.eduoa.entity.OaTeacherInfo;
import com.node.eduoa.enums.AttendanceEnum;
import com.node.eduoa.service.RegistrationAttendanceService;
import com.node.eduoa.utils.WeekUtils;
import com.node.eduoa.utils.model.AttendanceModel;
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

import javax.validation.Validator;
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
public class RegistrationAttendanceController extends BaseFormController {

    @Qualifier("registrationAttendanceServiceImpl")
    @Autowired
    private RegistrationAttendanceService registrationAttendanceService;

	private static final String LIST = "management/eduoa/attendance/list";
	private static final String REGISTRATION_LIST = "management/eduoa/attendance/registration_list";

    private OaRegistrationAttendance initGoToWork(Long id, WeekUtils weekUtils, Date standardDate, String type) {
        OaRegistrationAttendance registrationAttendance = registrationAttendanceService.get(id);
        Date currentDate = new Date();
        if ("morningStart".equals(type)) {
            if (weekUtils.compileDate(currentDate, standardDate) == 1) {
                registrationAttendance.setMorningStart(AttendanceEnum.ArriveLate.getIndex());
            } else {
                registrationAttendance.setMorningStart(AttendanceEnum.Normal.getIndex());
            }
            registrationAttendance.setMorningStartTime(currentDate);
        }
        if ("afternoonStart".equals(type)) {
            if (weekUtils.compileDate(currentDate, standardDate) == 1) {
                registrationAttendance.setAfternoonStart(AttendanceEnum.ArriveLate.getIndex());
            } else {
                registrationAttendance.setAfternoonStart(AttendanceEnum.Normal.getIndex());
            }
            registrationAttendance.setAfternoonStartTime(currentDate);
        }
        if ("nightStart".equals(type)) {
            if (weekUtils.compileDate(currentDate, standardDate) == 1) {
                registrationAttendance.setNightStart(AttendanceEnum.ArriveLate.getIndex());
            } else {
                registrationAttendance.setNightStart(AttendanceEnum.Normal.getIndex());
            }
            registrationAttendance.setNightStartTime(currentDate);
        }

        registrationAttendanceService.save(registrationAttendance);
        return registrationAttendance;
    }

    private OaRegistrationAttendance initGetOffWork(Long id, WeekUtils weekUtils, Date standardDate, String type) {
        OaRegistrationAttendance registrationAttendance = registrationAttendanceService.get(id);
        Date currentDate = new Date();
        if ("morningEnd".equals(type)) {
            if (weekUtils.compileDate(currentDate, standardDate) == -1) {
                registrationAttendance.setMorningEnd(AttendanceEnum.LeaveEarly.getIndex());
            } else {
                registrationAttendance.setMorningEnd(AttendanceEnum.Normal.getIndex());
            }
            registrationAttendance.setMorningEndTime(currentDate);
        }
        if ("afternoonEnd".equals(type)) {
            if (weekUtils.compileDate(currentDate, standardDate) == -1) {
                registrationAttendance.setAfternoonEnd(AttendanceEnum.LeaveEarly.getIndex());
            } else {
                registrationAttendance.setAfternoonEnd(AttendanceEnum.Normal.getIndex());
            }
            registrationAttendance.setAfternoonEndTime(currentDate);
        }
        if ("nightEnd".equals(type)) {
            if (weekUtils.compileDate(currentDate, standardDate) == -1) {
                registrationAttendance.setNightEnd(AttendanceEnum.LeaveEarly.getIndex());
            } else {
                registrationAttendance.setNightEnd(AttendanceEnum.Normal.getIndex());
            }
            registrationAttendance.setNightEndTime(currentDate);
        }
        registrationAttendanceService.save(registrationAttendance);
        return registrationAttendance;
    }

    @Log(message="{0}执行了签到。", level=LogLevel.INFO)
    @RequiresPermissions("Registration:save")
    @RequestMapping(value="/morningStart/{id}", method=RequestMethod.POST)
    public @ResponseBody String signUpMorningStart(@PathVariable Long id) {

        WeekUtils weekUtils = new WeekUtils();
        Date standardDate = weekUtils.getConvertTimeToDate("07:30:00");

        OaRegistrationAttendance registrationAttendance = initGoToWork(id, weekUtils, standardDate, "morningStart");

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{registrationAttendance.getTeacherName()}));
        return AjaxObject.newOk("签到成功！").setCallbackType("").toString();
    }

    @Log(message="{0}执行了签到。", level=LogLevel.INFO)
    @RequiresPermissions("Registration:save")
    @RequestMapping(value="/morningEnd/{id}", method=RequestMethod.POST)
    public @ResponseBody String signUpMorningEnd(@PathVariable Long id) {

        WeekUtils weekUtils = new WeekUtils();
        Date standardDate = weekUtils.getConvertTimeToDate("11:30:00");

        OaRegistrationAttendance registrationAttendance = initGetOffWork(id, weekUtils, standardDate, "morningEnd");

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{registrationAttendance.getTeacherName()}));
        return AjaxObject.newOk("签到成功！").setCallbackType("").toString();
    }

    @Log(message="{0}执行了签到。", level=LogLevel.INFO)
    @RequiresPermissions("Registration:save")
    @RequestMapping(value="/afternoonStart/{id}", method=RequestMethod.POST)
    public @ResponseBody String signUpAfternoonStart(@PathVariable Long id) {

        WeekUtils weekUtils = new WeekUtils();
        Date standardDate = weekUtils.getConvertTimeToDate("14:00:00");

        OaRegistrationAttendance registrationAttendance = initGoToWork(id, weekUtils, standardDate, "afternoonStart");

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{registrationAttendance.getTeacherName()}));
        return AjaxObject.newOk("签到成功！").setCallbackType("").toString();
    }

    @Log(message="{0}执行了签到。", level=LogLevel.INFO)
    @RequiresPermissions("Registration:save")
    @RequestMapping(value="/afternoonEnd/{id}", method=RequestMethod.POST)
    public @ResponseBody String signUpAfternoonEnd(@PathVariable Long id) {

        WeekUtils weekUtils = new WeekUtils();
        Date standardDate = weekUtils.getConvertTimeToDate("17:30:00");

        OaRegistrationAttendance registrationAttendance = initGetOffWork(id, weekUtils, standardDate, "afternoonEnd");

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{registrationAttendance.getTeacherName()}));
        return AjaxObject.newOk("签到成功！").setCallbackType("").toString();
    }

    @Log(message="{0}执行了签到。", level=LogLevel.INFO)
    @RequiresPermissions("Registration:save")
    @RequestMapping(value="/nightStart/{id}", method=RequestMethod.POST)
    public @ResponseBody String signUpNightStart(@PathVariable Long id) {

        WeekUtils weekUtils = new WeekUtils();
        Date standardDate = weekUtils.getConvertTimeToDate("18:30:00");

        OaRegistrationAttendance registrationAttendance = initGoToWork(id, weekUtils, standardDate, "nightStart");

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{registrationAttendance.getTeacherName()}));
        return AjaxObject.newOk("签到成功！").setCallbackType("").toString();
    }

    @Log(message="{0}执行了签到。", level=LogLevel.INFO)
    @RequiresPermissions("Registration:save")
    @RequestMapping(value="/nightEnd/{id}", method=RequestMethod.POST)
    public @ResponseBody String signUpNightEnd(@PathVariable Long id) {

        WeekUtils weekUtils = new WeekUtils();
        Date standardDate = weekUtils.getConvertTimeToDate("21:30:00");

        OaRegistrationAttendance registrationAttendance = initGetOffWork(id, weekUtils, standardDate, "nightEnd");

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{registrationAttendance.getTeacherName()}));
        return AjaxObject.newOk("签到成功！").setCallbackType("").toString();
    }


	@RequiresPermissions("Registration:view")
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, String keywords, Map<String, Object> map) {

        WeekUtils weekUtils = new WeekUtils();
        Date currentDate = new Date();
        Map<String, Long> dateMap = new HashMap<String, Long>(6);

        // 上午上班 07:30:00
        Long morningStartTimeLong = weekUtils.getCompileDateForSomeTime("07:30:00", currentDate);
        dateMap.put("morningStart", Math.abs(morningStartTimeLong));

        // 上午下班11:30:00
        Long morningEndTimeLong = weekUtils.getCompileDateForSomeTime("11:30:00", currentDate);
        dateMap.put("morningEnd", Math.abs(morningEndTimeLong));

        // 下午上班14:00:00
        Long afternoonStartTimeLong = weekUtils.getCompileDateForSomeTime("14:00:00", currentDate);
        dateMap.put("afternoonStart", Math.abs(afternoonStartTimeLong));

        // 下午上班17:30:00
        Long afternoonEndTimeLong = weekUtils.getCompileDateForSomeTime("17:30:00", currentDate);
        dateMap.put("afternoonEnd", Math.abs(afternoonEndTimeLong));

        // 晚上上班18:30:00
        Long nightStartTimeLong = weekUtils.getCompileDateForSomeTime("18:30:00", currentDate);
        dateMap.put("nightStart", Math.abs(nightStartTimeLong));

        // 晚上下班21:30:00
        Long nightEndTimeLong = weekUtils.getCompileDateForSomeTime("21:30:00", currentDate);
        dateMap.put("nightEnd", Math.abs(nightEndTimeLong));
        WeekUtils.MinDate minDate = weekUtils.getMinDate(dateMap);


        Map<String, Object> searchParams = new HashMap<String, Object>();
        searchParams.put(SearchFilter.Operator.GTE + "_attendanceDate", weekUtils.getConvertStringToDate(weekUtils.getCurrentMonday()).getTime()+"");
        searchParams.put(SearchFilter.Operator.LTE + "_attendanceDate", weekUtils.getConvertStringToDate(weekUtils.getSunday()).getTime()+"");
        List<OaRegistrationAttendance> registrationAttendanceList = registrationAttendanceService.findByCondition(page, searchParams);
        if (registrationAttendanceList == null || registrationAttendanceList.isEmpty()) {
            OaTeacherInfo teacherInfo = currentUser.getUser().getTeacherInfo();
            List<String> weekList = weekUtils.getCurrentDateOfWeek();
            OaRegistrationAttendance registrationAttendance = null;
            for (String week: weekList) {
                registrationAttendance = new OaRegistrationAttendance(week, weekUtils.getConvertStringToDate(week).getTime(), teacherInfo.getId(), teacherInfo.getTeacherName(),
                        currentOrganization.getId(), currentOrganization.getName(), new Date());
                registrationAttendanceService.save(registrationAttendance);
            }
            registrationAttendanceList = registrationAttendanceService.findByCondition(page, searchParams);
        }

        String currentDay = weekUtils.getCurrentDay();
        for (OaRegistrationAttendance registrationAttendance: registrationAttendanceList) {
            String day = registrationAttendance.getAttendanceDateCn();
            if (currentDay.equals(day)) {
                registrationAttendance.setAttendanceDateCn(registrationAttendance.getAttendanceDateCn() + "(今日)");
                if (registrationAttendance.getMorningStartTime() == null) {
                    if ("morningStart".equals(minDate.getKey())) {
                        registrationAttendance.setMorningStartDisplay(1);
                    }
                }
                if (registrationAttendance.getMorningEndTime() == null) {
                    if ("morningEnd".equals(minDate.getKey())) {
                        registrationAttendance.setMorningEndDisplay(1);
                    }
                }

                if (registrationAttendance.getAfternoonStartTime() == null) {
                    if ("afternoonStart".equals(minDate.getKey())) {
                        registrationAttendance.setAfternoonStart(1);
                    }
                }
                if (registrationAttendance.getAfternoonEndTime() == null) {
                    if ("afternoonEnd".equals(minDate.getKey())) {
                        registrationAttendance.setAfternoonEndDisplay(1);
                    }
                }

                if (registrationAttendance.getNightStartTime() == null) {
                    if ("nightStart".equals(minDate.getKey())) {
                        registrationAttendance.setNightStartDisplay(1);
                    }
                }
                if (registrationAttendance.getNightEndTime() == null) {
                    if ("nightEnd".equals(minDate.getKey())) {
                        registrationAttendance.setNightEndDisplay(1);
                    }
                }

            }
        }

		map.put("page", page);
		map.put("registrationAttendances", registrationAttendanceList);
		map.put("keywords", keywords);

		return LIST;
	}

    @RequiresPermissions("listRegistration:view")
    @RequestMapping(value="/listRegistration", method={RequestMethod.GET, RequestMethod.POST})
    public String listRegistration(Page page, String startTime, String endTime, String teacherId,
                                                 String organizationId, String teacherName,
                                                 String organizationName, String statue, Map<String, Object> map) {

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

        boolean orFlag = false;
        Map<String, Object> orSearchParams = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(statue)) {
            orSearchParams.put(SearchFilter.Operator.EQ + "_morningStart", statue);
            orSearchParams.put(SearchFilter.Operator.EQ + "_morningEnd", statue);
            orSearchParams.put(SearchFilter.Operator.EQ + "_afternoonStart", statue);
            orSearchParams.put(SearchFilter.Operator.EQ + "_afternoonEnd", statue);
            orSearchParams.put(SearchFilter.Operator.EQ + "_nightStart", statue);
            orSearchParams.put(SearchFilter.Operator.EQ + "_nightEnd", statue);
            orFlag = true;
        }

        List<OaRegistrationAttendance> registrationAttendanceList = null;
        if (orFlag) {
            registrationAttendanceList = registrationAttendanceService.findByCondition(page, searchParams, orSearchParams);
        } else {
            registrationAttendanceList = registrationAttendanceService.findByCondition(page, searchParams);
        }


        map.put("page", page);
        map.put("registrationAttendances", registrationAttendanceList);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("teacherId", teacherId);
        map.put("organizationId", organizationId);
        map.put("teacherName", teacherName);
        map.put("organizationName", organizationName);
        map.put("statue", statue);
        map.put("attendanceEnum", AttendanceEnum.values());

        return REGISTRATION_LIST;
    }

}
