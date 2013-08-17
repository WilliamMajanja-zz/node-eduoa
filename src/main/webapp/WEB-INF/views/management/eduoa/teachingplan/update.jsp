<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">
    <form id="leaveForm" method="post" action="${contextPath }/management/eduoa/teachingplan/update"
          enctype="multipart/form-data" class="required-validate pageForm"
          onsubmit="return iframeCallback(this, dialogReloadNavTab);">
        <input type="hidden" name="id" value="${teachingPlan.id}" />
        <div class="pageFormContent" layoutH="58">
            <p>
                <label>课件标题：</label>
                <input type="text" name="planTitle" value="${teachingPlan.planTitle}" class="required" size="32" maxlength="32"/>
            </p>
            <p>
                <label>年级：</label>
                <select name="gradeId" value="${teachingPlan.gradeId}" class="combox">
                    <option value="">无归属年级</option>
                    <c:forEach items="${grades}" var="item" >
                        <option ${teachingPlan.gradeId == item.id ? 'selected="selected"':''}
                                value="${item.id}">${item.gradeName}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label>学科：</label>
                <select name="subjectId" value="${teachingPlan.subjectId}" class="combox">
                    <option value="">不教授</option>
                    <c:forEach var="item" items="${subjects}">
                        <option ${teachingPlan.subjectId == item.id ? 'selected="selected"':''}
                                value="${item.id}">${item.subjectName}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label>上传人：</label>
                <input type="hidden" name="userId" value="${user.id}" readonly="true" size="32" maxlength="32"/>
                <input type="text" name="userName" value="${user.teacherName}" readonly="true" size="32" maxlength="32"/>
            </p>
            <p>
                <label>教案：<a href="#" target="_blank">${teachingPlan.attachmentTitle}</a></label>
                <input name="uploadFile" type="file" />
            </p>
        </div>

        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
    </form>
</div>