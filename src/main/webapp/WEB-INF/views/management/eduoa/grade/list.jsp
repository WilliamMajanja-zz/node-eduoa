<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<keta:paginationForm action="${contextPath }/management/eduoa/grade/list" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/grade/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>年级名称：</label>
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
				<li><a class="magnifier" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/grade/view/{slt_uid}"><span>查看年级</span></a></li>
			</shiro:hasPermission>		
			<shiro:hasPermission name="Grade:save">
				<li><a class="add" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/grade/create"><span>添加年级</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Grade:edit">
				<li><a class="edit" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/grade/update/{slt_uid}"><span>编辑年级</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="Grade:delete">
				<li><a class="delete" target="selectedTodo" rel="ids" href="${contextPath }/management/eduoa/grade/delete" title="确认要删除选定年级?"><span>删除年级</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="200">年级名称</th>
				<th width="100">年份</th>
				<th width="100">学年</th>
				<th>描述</th>
				<th width="130">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${grades}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.gradeName}</td>
				<td>${item.currentYear}</td>
				<td>${item.semester}</td>
                <td>${item.description}</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page }"/>
</div>