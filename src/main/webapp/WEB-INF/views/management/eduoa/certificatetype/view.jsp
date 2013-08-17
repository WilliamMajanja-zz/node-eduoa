<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<div class="pageContent">
    <form action="#">
        <div class="pageFormContent" layoutH="58">
            <p>
                <label>职称证书：</label>
                <label>${certificateType.typeName}</label>
            </p>

            <p>
                <label>级别：</label>
                <label>${certificateType.typeLevel}</label>
            </p>

            <p>
                <label>描述：</label>
                <textarea name="description" readonly="true" cols="28" rows="3" maxlength="255">${certificateType.description}</textarea>
            </p>
        </div>

        <div class="formBar">
            <ul>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">关闭</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>