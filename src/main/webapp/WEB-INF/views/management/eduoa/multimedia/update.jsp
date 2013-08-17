<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">
    <form id="leaveForm" method="post" action="${contextPath }/management/eduoa/multimedia/update"
          enctype="multipart/form-data" class="required-validate pageForm"
          onsubmit="return iframeCallback(this, dialogReloadNavTab);">
        <input type="hidden" name="id" value="${multimedia.id}" />
        <div class="pageFormContent" layoutH="58">
            <p>
                <label>多媒体标题：</label>
                <input type="text" name="title" value="${multimedia.title}" class="required" size="32" maxlength="32"/>
            </p>
            <p>
                <label>年级：</label>
                <select name="gradeId" value="${multimedia.gradeId}" class="combox">
                    <option value="">无归属年级</option>
                    <c:forEach items="${grades}" var="item" >
                        <option ${multimedia.gradeId == item.id ? 'selected="selected"':''}
                                value="${item.id}">${item.gradeName}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label>学科：</label>
                <select name="subjectId" value="${multimedia.subjectId}" class="combox">
                    <option value="">不教授</option>
                    <c:forEach var="item" items="${subjects}">
                        <option ${multimedia.subjectId == item.id ? 'selected="selected"':''}
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
                <label>教案：<a target="_blank" href="${contextPath }/management/eduoa/multimedia/download/${multimedia.id}">${multimedia.attachmentTitle}</a></label>
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