<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">
    <form action="#">
        <div class="pageFormContent" layoutH="58">
            <img src="${attachment.urlPath}" alt="${structure.structureName}"/>
        </div>

        <div class="formBar">
            <ul>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
            </ul>
        </div>
    </form>
</div>