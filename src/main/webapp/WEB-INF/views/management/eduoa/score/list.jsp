<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<keta:paginationForm action="${contextPath }/management/eduoa/score/list" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/score/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>分数名称：</label>
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
			<shiro:hasPermission name="Score:save">
				<li><a class="add" target="navTab" href="${contextPath }/management/eduoa/score/create"><span>添加分数</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Score:edit">
				<li><a class="edit" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/score/update/{slt_uid}"><span>编辑分数</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Score:delete">
				<li><a class="delete" target="selectedTodo" rel="ids" href="${contextPath }/management/eduoa/score/delete" title="确认要删除选定分数?"><span>删除分数</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="130">时间</th>
				<th>考试批次</th>
				<th width="100">年级</th>
				<th width="100">班级</th>
				<th width="100">学生姓名</th>
				<th width="100">学科</th>
				<th width="80">成绩</th>
				<th width="100">录入用户</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${scores}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td><fmt:formatDate value="${item.examDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.examTypeName}</td>
				<td>${item.gradeName}</td>
				<td>${item.className}</td>
				<td>${item.studentName}</td>
                <td>${item.subjectName}</td>
                <td>${item.score}</td>
                <td>${item.teacherName}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page }"/>
</div>