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
<keta:paginationForm action="${contextPath }/management/eduoa/attendance/update" page="${page }">
    <input type="hidden" name="keywords" value="${keywords}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/attendance/update" onsubmit="return navTabSearch(this)">
    <div class="pageHeader">
        <div class="searchBar">
            <ul class="searchContent">

            </ul>
            <div class="subBar">

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
                    <c:if test="${item.morningStartDisplay == 1}" var="morningStartDisplay">
                        <a onclick="doCommit('${contextPath }/management/eduoa/attendance/morningStart/${item.id}')"
                           href="javascript:;" >签到</a>
                    </c:if>
                    <c:if test="${!morningStartDisplay}">
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
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.morningEndDisplay == 1}" var="morningEndDisplay">
                        <a onclick="doCommit('${contextPath }/management/eduoa/attendance/morningEnd/${item.id}')"
                           href="javascript:;">签退</a>
                    </c:if>
                    <c:if test="${!morningEndDisplay}">
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
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.afternoonStartDisplay == 1}" var="afternoonStartDisplay">
                        <a onclick="doCommit('${contextPath }/management/eduoa/attendance/afternoonStart/${item.id}')"
                           href="javascripy:;">签到</a>
                    </c:if>
                    <c:if test="${!afternoonStartDisplay}">
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
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.afternoonEndDisplay == 1}" var="afternoonEndDisplay">
                        <a onclick="doCommit('${contextPath }/management/eduoa/attendance/afternoonEnd/${item.id}')"
                           href="javascript:;">签退</a>
                    </c:if>
                    <c:if test="${!afternoonEndDisplay}">
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
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.nightStartDisplay == 1}" var="nightStartDisplay">
                        <a onclick="doCommit('${contextPath }/management/eduoa/attendance/nightStart/${item.id}')"
                           href="javascript:;">签到</a>
                    </c:if>
                    <c:if test="${!nightStartDisplay}">
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
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.nightEndDisplay == 1}" var="nightEndDisplay">
                        <a onclick="doCommit('${contextPath }/management/eduoa/attendance/nightEnd/${item.id}')"
                           href="javascript:;">签退</a>
                    </c:if>
                    <c:if test="${!nightEndDisplay}">
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
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <!-- 分页 -->
    <keta:pagination page="${page }"/>
</div>