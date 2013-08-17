<%@page import="com.node.system.entity.main.Organization"%>
<%@ page import="com.node.eduoa.entity.OaGrade" %>
<%@ page import="com.node.eduoa.entity.OaClass" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%!
	public String tree(OaGrade grade, String basePath) {
		if (grade.getChildren().isEmpty()) {
            StringBuffer subBudder = new StringBuffer();
            subBudder.append("<ul>" + "\n");
            for(OaClass oaClass : grade.getOaClassesById()) {
                subBudder.append("<li><a href=\"javascript:\" onclick=\"returnClose({id:'"
                        + oaClass.getId() + "', className:'" + oaClass.getClassName() + "'})\" >"
                        + oaClass.getClassName() + "</a>" + "\n");
                subBudder.append("</li>" + "\n");
            }
            subBudder.append("</ul>" + "\n");
			return subBudder.toString();
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("<ul>" + "\n");
		for(OaGrade o : grade.getChildren()) {
            buffer.append("<li><a href=\"javascript:\" >"  + o.getGradeName() + "（"+o.getSemester()+"）</a>" + "\n");
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
            <li><div class="button"><div class="buttonContent"><button id="treeGrade_close" class="close" type="button">关闭</button></div></div></li>
        </ul>
        <ul>
            <li><div class="button"><div class="buttonContent"><button id="treeGrade_clean" type="button">清空</button></div></div></li>
        </ul>
    </div>
    <script type="text/javascript">
        $("#treeGrade_clean").on("click", function(){
            $.bringBackSuggest({id:'', className:''});
            $("#treeGrade_close").trigger("click");
        });
        function returnClose(obj) {
            $.bringBackSuggest(obj);
            $("#treeGrade_close").click();
        }
    </script>
</div>