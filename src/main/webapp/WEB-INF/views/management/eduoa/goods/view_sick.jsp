<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<div class="pageContent">
    <div class="pageFormContent" style="height: 228px; overflow: auto;" layouth="56">
        <div class="panelBar">
            <div class="toolBar" style="margin: 7px;">
                领取物品：${goodsApply.goodsName}(${goodsApply.goodsUnit})
            </div>
        </div>

        <table class="table" width="100%">
            <thead>
            <tr>
                <th>领取人</th>
                <th width="50">数量</th>
                <th width="130">领取时间</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${goodsReceives}">
                <tr target="slt_uid" rel="${item.id}">
                    <td>${item.recipientsName}</td>
                    <td>${item.recipientsCount}</td>
                    <td><fmt:formatDate value="${item.recipientsTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>

    <div class="formBar">
        <ul>
            <li>
                <div class="button">
                    <div class="buttonContent">
                        <button type="button" id="button_close" class="close">关闭</button>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>