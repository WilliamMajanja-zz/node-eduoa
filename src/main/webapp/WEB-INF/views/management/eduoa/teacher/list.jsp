<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<keta:paginationForm action="${contextPath }/management/eduoa/teacher/list" page="${page }">
    <input type="hidden" name="keywords" value="${keywords}"/>
    <input type="hidden" name="organizationName" value="${organizationName}"/>
    <input type="hidden" name="subjectId" value="${subjectId}"/>
    <input type="hidden" name="positionId" value="${positionId}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/teacher/list" onsubmit="return navTabSearch(this)">
    <div class="pageHeader">
        <div class="searchBar">
            <ul class="searchContent">
                <li>
                    <label>教师名称：</label>
                    <input type="text" name="keywords" value="${keywords}"/>
                </li>
                <li>
                    <label>工作岗位：</label>
                    <input type="text" name="organizationName" value="${organizationName}" />
                </li>

                <li>
                    <label>学科：</label>
                    <select name="subjectId" class="combox">
                        <option value="">所有</option>
                        <c:forEach var="item" items="${subjects}">
                            <option value="${item.id}" ${item.id == subjectId ? 'selected="selected"':'' }>${item.subjectName}</option>
                        </c:forEach>
                    </select>
                </li>
                <li>
                    <label>职务：</label>
                    <select name="positionId" class="combox">
                        <option value="">所有</option>
                        <c:forEach var="item" items="${positions}">
                            <option value="${item.id}" ${item.id == positionId ? 'selected="selected"':'' }>${item.positionName}</option>
                        </c:forEach>
                    </select>
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
			<shiro:hasPermission name="TeacherInfo:view">
				<li><a class="magnifier" target="dialog" rel="dlg_mask" mask="true" width="600" height="500" href="${contextPath }/management/eduoa/teacher/view/{slt_uid}"><span>查看人员</span></a></li>
			</shiro:hasPermission>		
			<shiro:hasPermission name="TeacherInfo:save">
				<li><a class="add" target="dialog" rel="dlg_mask" mask="true" width="600" height="500" href="${contextPath }/management/eduoa/teacher/create"><span>添加人员</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="TeacherInfo:edit">
				<li><a class="edit" target="dialog" rel="dlg_mask" mask="true" width="600" height="500" href="${contextPath }/management/eduoa/teacher/update/{slt_uid}"><span>编辑人员</span></a></li>
			</shiro:hasPermission>
			<shiro:hasPermission name="TeacherInfo:delete">
				<li><a class="delete" target="selectedTodo" rel="ids" href="${contextPath }/management/eduoa/teacher/delete" title="确认要删除选定人员?"><span>删除人员</span></a></li>
			</shiro:hasPermission>
            <shiro:hasPermission name="TeacherInfo:teachClass">
                <li><a class="add" target="dialog" rel="dlg_mask" mask="true" width="600" height="500" href="${contextPath }/management/eduoa/teacher/teachClass/{slt_uid}"><span>任课班级</span></a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="TeacherInfo:guideClass">
                <li><a class="add" target="dialog" rel="dlg_mask" mask="true" width="600" height="500" href="${contextPath }/management/eduoa/teacher/guideClass/{slt_uid}"><span>带领班级</span></a></li>
            </shiro:hasPermission>
        </ul>
	</div>
	
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="100">人员姓名</th>
				<th>人员工号</th>
				<th width="50">岗位</th>
				<th width="50">学科</th>
				<th width="50">职务</th>
				<th width="100">原始学历</th>
				<th width="100">最新学历</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${teacherInfos}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.teacherName}</td>
				<td>${item.teacherNumber}</td>
				<td>${item.securityOrganizationByOrgId.name}</td>
				<td>${item.oaSubjectBySubjectId.subjectName}</td>
				<td>${item.oaPositionByPositionId.positionName}</td>
				<td>${item.originalEducationName}</td>
				<td>${item.newEducationName}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page }"/>
</div>