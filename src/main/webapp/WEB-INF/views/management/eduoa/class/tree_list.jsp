<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>    
<div class="pageContent">
	<div class="tabs">
		<div class="tabsContent">
			<div>
				<div layoutH="0" id="jbsxBox2organizationTree" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
					<c:import url="/management/eduoa/class/tree"/>
				</div>
				
				<div layoutH="0" id="jbsxBox2organizationList" class="unitBox" style="margin-left:246px;">
					<c:import url="/management/eduoa/class/list/1"/>
				</div>
			</div>
		</div>
	</div>
</div>