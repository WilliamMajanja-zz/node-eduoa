<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<div class="pageContent">
    <form action="#">
        <div class="pageFormContent" layoutH="58">
            <p>
                <label>年级名称：</label>
                <label>${grade.gradeName}</label>
            </p>

            <p>
                <label>年份：</label>
                <label>${grade.currentYear}</label>
            </p>

            <p>
                <label>学期：</label>
                <label>${grade.semester}</label>
            </p>

            <p>
                <label>开始日期：</label>
                <label><fmt:formatDate value="${grade.startTime}" pattern="yyyy-MM-dd"/></label>
            </p>
            <p>
                <label>结束日期：</label>
                <label><fmt:formatDate value="${grade.startTime}" pattern="yyyy-MM-dd"/></label>
            </p>

            <p>
                <label>描述：</label>
                <textarea name="description" cols="28" rows="3" maxlength="255"
                          readOnly=readOnly>${grade.description}</textarea>
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