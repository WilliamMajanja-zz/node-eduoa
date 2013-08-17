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
<keta:paginationForm action="${contextPath }/management/eduoa/attendance/listRegistration" page="${page }">
    <input type="hidden" name="startTime" value="${startTime}"/>
    <input type="hidden" name="endTime" value="${endTime}"/>
    <input type="hidden" name="organizationId" value="${organizationId}"/>
    <input type="hidden" name="organizationName" value="${organizationName}"/>
    <input type="hidden" name="teacherId" value="${teacherId}"/>
    <input type="hidden" name="teacherName" value="${teacherName}"/>
</keta:paginationForm>

<form id="attendanceModel" method="post" action="${contextPath }/management/eduoa/attendance/listRegistration" onsubmit="return navTabSearch(this)">
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
                <li>
                    <label>选择状态：</label>
                    <span style="width: 200px;">
                    <select name="statue" class="combox">
                        <option value="">请选择</option>
                        <c:forEach items="${attendanceEnum}" var="item">
                            <option value="${item.index}">${item.text}</option>
                        </c:forEach>
                    </select>
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
            <th width="120">上午上班</th>
            <th width="120">上午下班</th>
            <th width="120">下午上班</th>
            <th width="120">下午下班</th>
            <th width="120">晚上上班</th>
            <th width="120">晚上下班</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${registrationAttendances}">
            <tr target="slt_uid" rel="${item.id}">
                <td>${item.attendanceDateCn}</td>
                <td>${item.teacherName}</td>
                <td>
                    <c:if test="${item.morningStart != null}">
                        <c:if test="${item.morningStart == 1}" var="morningStart">
                            <div style="color: green">${item.morningStartString}
                                (<fmt:formatDate value="${item.morningStartTime}" pattern="HH:mm:ss"/>)
                            </div>
                        </c:if>
                        <c:if test="${!morningStart}">
                            <div style="color: red">${item.morningStartString}
                                (<fmt:formatDate value="${item.morningStartTime}" pattern="HH:mm:ss"/>)
                            </div>
                        </c:if>
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.morningEnd != null}">
                        <c:if test="${item.morningEnd == 1}" var="morningEnd">
                            <div style="color: green">${item.morningEndString}
                                (<fmt:formatDate value="${item.morningEndTime}" pattern="HH:mm:ss"/>)
                            </div>
                        </c:if>
                        <c:if test="${!morningEnd}">
                            <div style="color: red">${item.morningEndString}
                                (<fmt:formatDate value="${item.morningEndTime}" pattern="HH:mm:ss"/>)
                            </div>
                        </c:if>
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.afternoonStart != null}">
                        <c:if test="${item.afternoonStart == 1}" var="afternoonStart">
                            <div style="color: green">${item.afternoonStartString}
                                (<fmt:formatDate value="${item.afternoonStartTime}" pattern="HH:mm:ss"/>)
                            </div>
                        </c:if>
                        <c:if test="${!afternoonStart}">
                            <div style="color: red">${item.afternoonStartString}
                                (<fmt:formatDate value="${item.afternoonStartTime}" pattern="HH:mm:ss"/>)
                            </div>
                        </c:if>
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.afternoonEnd != null}">
                        <c:if test="${item.afternoonEnd == 1}" var="afternoonEnd">
                            <div style="color: green">${item.afternoonEndString}
                                (<fmt:formatDate value="${item.afternoonEndTime}" pattern="HH:mm:ss"/>)
                            </div>
                        </c:if>
                        <c:if test="${!afternoonStart}">
                            <div style="color: red">${item.afternoonEndString}
                                (<fmt:formatDate value="${item.afternoonEndTime}" pattern="HH:mm:ss"/>)
                            </div>
                        </c:if>
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.nightStart != null}">
                        <c:if test="${item.nightStart == 1}" var="nightStart">
                            <div style="color: green">${item.nightStartString}
                                (<fmt:formatDate value="${item.nightStartTime}" pattern="HH:mm:ss"/>)
                            </div>
                        </c:if>
                        <c:if test="${!afternoonStart}">
                            <div style="color: red">${item.nightStartString}
                                (<fmt:formatDate value="${item.nightStartTime}" pattern="HH:mm:ss"/>)
                            </div>
                        </c:if>
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.nightEnd != null}">
                        <c:if test="${item.nightEnd == 1}" var="nightEnd">
                            <div style="color: green">${item.nightEndString}
                                (<fmt:formatDate value="${item.nightEndTime}" pattern="HH:mm:ss"/>)
                            </div>
                        </c:if>
                        <c:if test="${!nightEnd}">
                            <div style="color: red">${item.nightEndString}
                                (<fmt:formatDate value="${item.nightEndTime}" pattern="HH:mm:ss"/>)
                            </div>
                        </c:if>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 分页 -->
    <keta:pagination page="${page }"/>
</div>