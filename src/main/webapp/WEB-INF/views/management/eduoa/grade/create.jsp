<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<div class="pageContent">
    <form method="post" action="${contextPath }/management/eduoa/grade/create" class="required-validate pageForm"
          onsubmit="return validateCallback(this, dialogReloadNavTab);">
        <div class="pageFormContent" layoutH="58">
            <p>
                <label>年级：</label>
                <input type="text" name="gradeName" class="required" size="32" maxlength="32"/>
            </p>

            <p>
                <label>年份：</label>
                <select name="currentYear" class="combox">
                    <c:forEach items="${years}" var="year" >
                        <option value="${year}">${year}年</option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label>学期：</label>
                <select name="currentHalf" class="combox">
                    <c:forEach items="${semesterEnums}" var="semester" >
                        <option value="${semester.index}">${semester.text}</option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label>开始日期：</label>
                <input type="text" name="startTime" class="required date" size="30"/><a class="inputDateButton"
                                                                                       href="javascript:;">选择</a>
            </p>
            <p>
                <label>结束日期：</label>
                <input type="text" name="endTime" class="required date" size="30"/><a class="inputDateButton"
                                                                                        href="javascript:;">选择</a>
            </p>

            <p>
                <label>描述：</label>
                <textarea name="description" cols="28" rows="3" maxlength="255"></textarea>
            </p>
        </div>

        <div class="formBar">
            <ul>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="submit">确定</button>
                        </div>
                    </div>
                </li>
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