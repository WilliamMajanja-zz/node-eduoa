<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<keta:paginationForm action="${contextPath }/management/eduoa/student/list" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/student/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>学生名称：</label>
					<input type="text" name="keywords" value="${keywords}"/>
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

	<div class="panelBar">
		<ul class="toolBar">
			<shiro:hasPermission name="StudentInfo:look">
				<li><a class="magnifier" target="dialog" mask="true" width="600" height="500" href="${contextPath }/management/eduoa/student/view/{slt_uid}"><span>查看学生</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="StudentInfo:save">
				<li><a class="add" target="dialog" rel="dialog_mask" width="600" height="500" href="${contextPath }/management/eduoa/student/create"><span>添加学生</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="StudentInfo:edit">
				<li><a class="edit" target="dialog" rel="dialog_mask" width="600" height="500" href="${contextPath }/management/eduoa/student/update/{slt_uid}"><span>编辑学生</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="StudentInfo:delete">
				<li><a class="delete" target="selectedTodo" rel="ids" href="${contextPath }/management/eduoa/student/delete" title="确认要删除选定学生?"><span>删除学生</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
                <th width="100">学号</th>
                <th>姓名</th>
				<th width="50">性别</th>
				<th width="100">出生年月</th>
                <th width="100">就学年级</th>
				<th width="100">就学班级</th>
				<th width="100">文理科</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${studentList}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
                <td>${item.studentNumber}</td>
				<td>${item.studentName}</td>
				<td>${item.genderName}</td>
                <td><fmt:formatDate value="${item.birthday}" pattern="yyyy-MM-dd"/></td>
                <td>${item.gradeName}</td>
                <td>${item.className}</td>
                <td>${item.categoryName}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page }"/>
</div>