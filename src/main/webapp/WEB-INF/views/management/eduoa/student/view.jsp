<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<div class="pageContent">
    <form action="#">
        <div class="pageFormContent" layoutH="58">
            <p>
                <label>学号：</label>
                <label>${student.studentNumber}</label>
            </p>
            <p>
                <label>姓名：</label>
                <label>${student.studentName}</label>
            </p>

            <p>
                <label>性别：</label>
                <label>${student.genderName}</label>
            </p>
            <p>
                <label>身份证：</label>
                <label>${student.idNumber}</label>
            </p>
            <p>
                <label>出生年月：</label>
                <label><fmt:formatDate value="${student.birthday}" pattern="yyyy-MM-dd"/></label>
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