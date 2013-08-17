<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<keta:paginationForm action="${contextPath }/management/eduoa/certificatetype/list" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/certificatetype/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>职称证书：</label>
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
			<shiro:hasPermission name="Grade:look">
				<li><a class="magnifier" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/certificatetype/view/{slt_uid}"><span>查看证书</span></a></li>
			</shiro:hasPermission>		
			<shiro:hasPermission name="Grade:save">
				<li><a class="add" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/certificatetype/create"><span>添加证书</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Grade:edit">
				<li><a class="edit" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/certificatetype/update/{slt_uid}"><span>编辑证书</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Grade:delete">
				<li><a class="delete" target="selectedTodo" rel="ids" href="${contextPath }/management/eduoa/certificatetype/delete" title="确认要删除选定证书?"><span>删除证书</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="200">职称证书</th>
				<th width="100">级别</th>
				<th>描述</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${certificateTypes}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.typeName}</td>
				<td>${item.typeLevel}</td>
                <td>${item.description}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page }"/>
</div>