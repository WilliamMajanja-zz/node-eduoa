<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<script type="text/javascript">
    var chart = new FusionCharts("${contextPath }/styles/fusionchart/FusionCharts/Column2D.swf", "ChartId", "500", "300", "0", "0");
    chart.setDataURL("${contextPath }/data/Column2D.xml");
    chart.render("chartdiv");
</script>
<div class="pageContent">
    <form method="post" action="${contextPath }/management/eduoa/position/create"
          class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
        <div class="pageFormContent" layoutV="58">
            <div id="chartdiv"></div>
        </div>

        <div class="formBar">
            <ul>
                <li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
            </ul>
        </div>
    </form>
</div>