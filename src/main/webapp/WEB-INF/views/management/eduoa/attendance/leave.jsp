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
                    <c:if test="${item.leaveStartDisplay == 1}" var="leaveStartDisplay">
                        <a onclick="doCommit('${contextPath }/management/eduoa/attendance/leaveStart/${item.id}')"
                           href="javascript:;" >离校</a>
                    </c:if>
                    <c:if test="${!leaveStartDisplay}">
                        <c:if test="${item.leaveStartTime != null}">
                            <div style="color: red">离校(<fmt:formatDate value="${item.leaveStartTime}" pattern="HH:mm:ss"/>)</div>
                        </c:if>
                    </c:if>
                </td>
                <td>
                    <c:if test="${item.leaveEndDisplay == 1}" var="leaveEndDisplay">
                        <a onclick="doCommit('${contextPath }/management/eduoa/attendance/leaveEnd/${item.id}')"
                           href="javascript:;">返校</a>
                    </c:if>
                    <c:if test="${!leaveEndDisplay}">
                        <c:if test="${item.leaveEndTime != null}">
                            <div style="color: green">返校(<fmt:formatDate value="${item.leaveEndTime}" pattern="HH:mm:ss"/>)</div>
                        </c:if>
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