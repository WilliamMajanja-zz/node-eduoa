<%@page import="com.node.system.entity.main.Organization" %>
<%@ page import="com.node.eduoa.entity.OaGrade" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<%!
    public String tree(OaGrade grade, String basePath) {
        if (grade.getChildren().isEmpty()) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("<ul>" + "\n");
        for (OaGrade o : grade.getChildren()) {
            buffer.append("<li><a href=\"javascript:\" onclick=\"returnClose({classIds:'" + o.getId()
                    + "', classNames:'" + o.getGradeName() + "'})\" >" + o.getGradeName() + "</a>" + "\n");
            buffer.append(tree(o, basePath));
            buffer.append("</li>" + "\n");
        }
        buffer.append("</ul>" + "\n");
        return buffer.toString();
    }
%>
<%
    OaGrade gradeParent = (OaGrade)request.getAttribute("grade");
%>
<script type="text/javascript">
    function returnClose(obj) {
        $.bringBackSuggest(obj);
        $("#treeHeadTeacher_close", $(".formBar")).click();
    }
</script>
<div class="pageContent">
    <div class="pageFormContent" layoutH="58">
        <ul class="tree expand">
            <li>
                <a href="javascript:">${grade.gradeName }</a> <%=tree(gradeParent, request.getContextPath())%>
            </li>
        </ul>
    </div>

    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button id="treeHeadTeacher_clean" type="button">清空</button></div></div></li>
        </ul>
        <ul>
            <li><div class="button"><div class="buttonContent"><button id="treeHeadTeacher_close" class="close" type="button">关闭</button></div></div></li>
        </ul>
    </div>
    <script type="text/javascript">
        $("#treeHeadTeacher_clean").on("click", function(){
            $.bringBackSuggest({classIds:'', classNames:''});
            $("#treeHeadTeacher_close").trigger("click");
        });
    </script>
</div>