<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<script type="text/javascript">
    function doCommit(href) {
        $.ajax({
            type: 'post',
            dataType: 'json',
            url: href,
            error: DWZ.ajaxError,
            success: function (json) {
                dialogReloadNavTab(json);
            }
        });
    }


</script>
<keta:paginationForm action="${contextPath }/management/eduoa/attendance/listLeave" page="${page }">
    <input type="hidden" name="startTime" value="${startTime}"/>
    <input type="hidden" name="endTime" value="${endTime}"/>
    <input type="hidden" name="organizationId" value="${organizationId}"/>
    <input type="hidden" name="organizationName" value="${organizationName}"/>
    <input type="hidden" name="teacherId" value="${teacherId}"/>
    <input type="hidden" name="teacherName" value="${teacherName}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/attendance/listLeave" onsubmit="return navTabSearch(this)">
    <div class="pageHeader">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>选择时间：</label>
                    <span style="width: 400px;">
                    <input type="text" name="startTime" class="date" size="10" value="${startTime}"/>
                    <input type="text" name="endTime" class="date" size="10" value="${endTime}"/>
                    </span>
                </li>
                <li>
                    <label>选择部门：</label>
                    <span style="width: 400px;">
                    <input type="hidden" name="organizationId" value="${organizationId}"/>
                    <input type="text" name="organizationName" value="${organizationName}" />
                    <a id="organization_tree_select" class="btnLook" style="float: right;" target="dialog" width="530" mask="true" height="450"
                       href="${contextPath }/management/eduoa/teacher/organizationTree"
                       lookupGroup="attendanceModel" title="选择">选择</a>
                    </span>
                </li>
                <li>
                    <label>选择人员：</label>
                    <span style="width: 400px;">
                    <input type="hidden" name="teacherId" value="${teacherId}"/>
                    <input type="text" name="teacherName" value="${teacherName}"/>
                    <a id="teachers_tree_select" class="btnLook" style="float: right;" target="dialog" width="530" mask="true" height="450"
                       href="${contextPath }/management/eduoa/teacher/teachers"
                       lookupGroup="attendanceModel" title="选择">选择</a>
                    </span>
                </li>
            </ul>
            <div class="subBar">
                <ul>
                    <li><div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
                </ul>
            </div>
        </div>
    </div>
</form>

<div class="pageContent">

    <div class="panelBar"></div>

    <table class="table" layoutH="138" width="100%">
        <thead>
        <tr>
            <th width="150">日期</th>
            <th>姓名</th>
            <th width="120">请假离校</th>
            <th width="120">销假返校</th>
            <th width="120">离校时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${leaveAttendances}">
            <tr target="slt_uid" rel="${item.id}">
                <td>${item.attendanceDateCn}</td>
                <td>${item.teacherName}</td>
                <td>
                    <c:if test="${item.leaveStartTime != null}">
                        <div style="color: red">离校(<fmt:formatDate value="${item.leaveStartTime}" pattern="HH:mm:ss"/>)</div>
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.leaveEndTime != null}">
                        <div style="color: green">返校(<fmt:formatDate value="${item.leaveEndTime}" pattern="HH:mm:ss"/>)</div>
                    </c:if>
                </td>
                <td>
                    ${item.leaveTimeCn}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 分页 -->
    <keta:pagination page="${page }"/>
</div>