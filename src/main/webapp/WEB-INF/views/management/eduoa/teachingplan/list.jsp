<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<keta:paginationForm action="${contextPath }/management/eduoa/teachingplan/list" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/teachingplan/list" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>课件标题：</label>
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
            <shiro:hasPermission name="TeachingPlan:save">
                <li><a class="add" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/teachingplan/create"><span>添加课件</span></a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="TeachingPlan:save">
                <li><a class="arrow_refresh" target="selectedTodo" rel="ids" href="${contextPath }/management/eduoa/teachingplan/submit" title="确认要提交选定课件?"><span>提交课件</span></a></li>
            </shiro:hasPermission>
			<shiro:hasPermission name="TeachingPlan:edit">
				<li><a class="edit" target="dialog" mask="true" width="530" height="350" href="${contextPath }/management/eduoa/teachingplan/update/{slt_uid}"><span>编辑课件</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="TeachingPlan:delete">
				<li><a class="delete" target="selectedTodo" rel="ids" href="${contextPath }/management/eduoa/teachingplan/delete" title="确认要删除选定课件?"><span>删除课件</span></a></li>
			</shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th>课件标题</th>
                <th width="130">撰写时间</th>
				<th width="100">年级</th>
				<th width="100">学科</th>
				<th width="100">撰写人</th>
				<th width="100">上传状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${teachingPlans}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.planTitle}</td>
				<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${item.gradeName}</td>
                <td>${item.subjectName}</td>
                <td>${item.teacherName}</td>
                <td style="color: red;">${item.statueCn}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page }"/>
</div>