<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<keta:paginationForm action="${contextPath }/management/eduoa/subject/list" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/subject/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>科目名称：</label>
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
			<shiro:hasPermission name="Subject:look">
				<li><a class="magnifier" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/subject/view/{slt_uid}"><span>查看科目</span></a></li>
			</shiro:hasPermission>		
			<shiro:hasPermission name="Subject:save">
				<li><a class="add" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/subject/create"><span>添加科目</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Subject:edit">
				<li><a class="edit" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/subject/update/{slt_uid}"><span>编辑科目</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Subject:delete">
				<li><a class="delete" target="selectedTodo" rel="ids" href="${contextPath }/management/eduoa/subject/delete" title="确认要删除选定科目?"><span>删除科目</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="200">科目名称</th>
				<th>描述</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${subjects}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.subjectName}</td>
                <td>${item.description}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page }"/>
</div>