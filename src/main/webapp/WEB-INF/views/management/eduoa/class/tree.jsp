<%@page import="com.node.system.entity.main.Organization"%>
<%@ page import="com.node.eduoa.entity.OaGrade" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%!
	public String tree(OaGrade grade, String basePath) {
		if (grade.getChildren().isEmpty()) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		buffer.append("<ul>" + "\n");
		for(OaGrade o : grade.getChildren()) {
			buffer.append("<li><a href=\"" + basePath + "/management/eduoa/class/list/" + o.getId()
                    + "\" target=\"ajax\" rel=\"jbsxBox2organizationList\">" + o.getGradeName()
                    + "（"+o.getSemester()+"）</a>" + "\n");
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
<ul class="tree expand">
	<li><a href="${contextPath }/management/eduoa/class/list/${grade.id}"
		target="ajax" rel="jbsxBox2module">${grade.gradeName }</a> <%=tree(gradeParent, request.getContextPath())%>
	</li>
</ul>