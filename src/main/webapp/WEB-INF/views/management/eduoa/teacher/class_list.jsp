<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<script type="text/javascript">
    function dialogAjax(json){
        var url = $("#reload_url").val();
        var rel = "toNewTeachClass";
        dialogReloadRel2ClassTeacher(json, rel, url);
    }
</script>
<div class="pageContent">
    <input type="hidden" id="reload_url" value="${contextPath}/management/eduoa/teacher/classList/${teacherInfo.id}"/>
    <table class="table" width="100%">
        <thead>
        <tr>
            <th>班级</th>
            <th width="150">日期</th>
            <th width="50">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${classTeacherList}">
            <tr target="slt_uid" rel="${item.id}">
                <td>${item.oaClassByClassId.className}</td>
                <td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd"/> -
                    <fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd"/></td>
                <td>
                    <a class="delete"  href="${contextPath }/management/eduoa/teacher/deleteClassTeacher/${item.id}"
                       callback="dialogAjax" target="ajaxTodo" title="确定要删除吗?" fresh="true">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>