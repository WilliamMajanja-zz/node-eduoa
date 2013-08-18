<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<keta:paginationForm action="${contextPath }${nav}" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }${nav}" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>标题：</label>
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
            <li><a class="add" target="dialog" mask="true" width="530" height="450" href="${contextPath }/management/eduoa/article/create/${channelCode}"><span>添加</span></a></li>
            <li><a class="edit" target="dialog" mask="true" width="530" height="450" href="${contextPath }/management/eduoa/article/update/{slt_uid}"><span>编辑</span></a></li>
            <li><a class="delete" target="selectedTodo" rel="ids" href="${contextPath }/management/eduoa/article/delete" title="确认要删除选定?"><span>删除</span></a></li>
		</ul>
	</div>
	
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th>标题</th>
                <th width="200">更新人</th>
				<th width="100">创建时间</th>
				<th width="100">更新时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${articles}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.title}</td>
				<td>${item.createUserName}</td>
                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page }"/>
</div>