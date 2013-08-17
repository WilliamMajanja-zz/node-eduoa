<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<a id="refreshJbsxBox2organizationTree" rel="jbsxBox2organizationTree" target="ajax" href="${contextPath}/management/eduoa/class/tree" style="display:none;"></a>
<keta:paginationForm action="${contextPath}/management/eduoa/class/list/${gradeId}" page="${page }" onsubmit="return divSearch(this, 'jbsxBox2organizationList');">
	<input type="hidden" name="keywords" value="${keywords}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/class/list/${gradeId}" onsubmit="return divSearch(this, 'jbsxBox2organizationList');">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>班级名称：</label>
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
		<shiro:hasPermission name="Class:save">
			<li><a class="group_add" target="dialog" mask="true" width="530" height="250" href="${contextPath}/management/eduoa/class/create/${gradeId}"><span>添加班级</span></a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="Class:edit">
			<li><a class="group_edit" target="dialog" mask="true" rel="lookupParent2org_edit" width="530" height="250" href="${contextPath}/management/eduoa/class/update/{slt_uid}"><span>编辑班级</span></a></li>
		</shiro:hasPermission>
		<shiro:hasPermission name="Class:delete">
			<li><a class="group_delete" class="group_delete" target="ajaxTodo" callback="dialogReloadRel2Org" href="${contextPath}/management/eduoa/class/delete/{slt_uid}" title="确认要删除该班级?"><span>删除班级</span></a></li>
		</shiro:hasPermission>
		</ul>
	</div>
	<table class="table" layoutH="142" width="100%" rel="jbsxBox2organizationList" >
		<thead>
			<tr>
				<th width="100">年级</th>
				<th width="150">名称</th>
				<th width="50">文理科</th>
                <th width="70">人数限制</th>
				<th>描述</th>
				<th width="150">创建时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${classList}">
			<tr target="slt_uid" rel="${item.id}">
				<td>${item.oaGradeByGradeId.gradeName}</td>
				<td>${item.className}</td>
                <td>${item.categoryName}</td>
                <td>${item.numberLimit}</td>
                <td>${item.description}</td>
                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
			</c:forEach>
		</tbody>
	</table>

	<!-- 分页 -->
	<keta:pagination page="${page }" rel="jbsxBox2organizationList" onchange="navTabPageBreak({numPerPage:this.value}, 'jbsxBox2organizationList')"/>
</div>