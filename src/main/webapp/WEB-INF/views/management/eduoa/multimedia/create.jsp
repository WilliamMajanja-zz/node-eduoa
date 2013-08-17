<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">
    <form id="leaveForm" method="post" action="${contextPath }/management/eduoa/multimedia/create"
          enctype="multipart/form-data" class="required-validate pageForm"
          onsubmit="return iframeCallback(this, dialogReloadNavTab);">
        <div class="pageFormContent" layoutH="58">
            <p>
                <label>多媒体标题：</label>
                <input type="text" name="title" class="required" size="32" maxlength="32"/>
            </p>
            <p>
                <label>年级：</label>
                <select name="gradeId" class="combox">
                    <option value="">无归属年级</option>
                    <c:forEach items="${grades}" var="item" >
                        <option value="${item.id}">${item.gradeName}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label>学科：</label>
                <select name="subjectId" class="combox">
                    <option value="">不教授</option>
                    <c:forEach var="item" items="${subjects}">
                        <option value="${item.id}">${item.subjectName}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label>制作人：</label>
                <input type="hidden" name="userId" value="${user.id}" readonly="true" size="32" maxlength="32"/>
                <input type="text" name="userName" value="${user.teacherName}" readonly="true" size="32" maxlength="32"/>
            </p>
            <p>
                <label>多媒体：</label>
                <input name="uploadFile" class="required" type="file" />
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