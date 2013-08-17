<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<div class="pageContent">
    <form id="leaveForm" method="post" action="${contextPath }/management/eduoa/teachingplan/ratings"
          enctype="multipart/form-data" class="required-validate pageForm"
          onsubmit="return iframeCallback(this, dialogReloadNavTab);">
        <input type="hidden" name="id" value="${teachingPlan.id}" />
        <div class="pageFormContent" layoutH="58">
            <p>
                <label>课件标题：</label>
                <input type="text" name="planTitle" readonly="true" value="${teachingPlan.planTitle}"
                       size="32" maxlength="32"/>
            </p>
            <p>
                <label>级别：</label>
                <select name="planLevel" class="combox required">
                    <c:forEach items="${planLevelEnum}" var="item" >
                        <option value="${item.index}">${item.text}</option>
                    </c:forEach>
                </select>
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