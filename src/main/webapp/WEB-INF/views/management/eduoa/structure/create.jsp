<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">
    <form method="post" action="${contextPath }/management/eduoa/structure/create"
          enctype="multipart/form-data" class="required-validate pageForm"
          onsubmit="return iframeCallback(this, popupAlertMsg);">
        <div class="pageFormContent" layoutH="58">
            <p>
                <label>组织结构名称：</label>
                <input type="text" name="structureName" class="required" size="32" maxlength="32"/>
            </p>
            <p>
                <label>图片：</label>
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