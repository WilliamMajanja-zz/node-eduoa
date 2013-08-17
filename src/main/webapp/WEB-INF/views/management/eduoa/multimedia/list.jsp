<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<keta:paginationForm action="${contextPath }/management/eduoa/multimedia/list" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/multimedia/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>多媒体标题：</label>
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
            <shiro:hasPermission name="MultiMedia:save">
                <li><a class="add" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/multimedia/create"><span>添加多媒体</span></a></li>
            </shiro:hasPermission>
			<shiro:hasPermission name="MultiMedia:edit">
				<li><a class="edit" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/multimedia/update/{slt_uid}"><span>编辑多媒体</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="MultiMedia:delete">
				<li><a class="delete" target="selectedTodo" rel="ids" href="${contextPath }/management/eduoa/multimedia/delete" title="确认要删除选定多媒体?"><span>删除多媒体</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
                <th width="130">上传时间</th>
				<th width="100">年级</th>
				<th width="100">学科</th>
                <th>多媒体标题</th>
				<th width="100">制作人</th>
                <th width="100">使用次数</th>
                <th width="100">下载</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${multimedias}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td><fmt:formatDate value="${item.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.gradeName}</td>
                <td>${item.subjectName}</td>
                <td>${item.title}</td>
                <td>${item.teacherName}</td>
                <td>${item.useCount}</td>
                <td><a target="_blank" href="${contextPath }/management/eduoa/multimedia/download/${item.id}">${item.attachmentTitle}</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page }"/>
</div>