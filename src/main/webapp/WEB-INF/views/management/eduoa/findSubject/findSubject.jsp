<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<script type="text/javascript">
    var chart = new FusionCharts("${contextPath }/styles/fusionchart/FusionCharts/Column2D.swf", "ChartId", "500", "300", "0", "0");
    chart.setXMLData("${findSubject }");
    chart.render("chartdiv");
</script>
<keta:paginationForm action="${contextPath }/management/eduoa/score/findSubject" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/score/findSubject" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>年级：</label>
					<select name="gradeId" class="combox" >
						<c:forEach items="${grades }" var="item">
							<option value="${item.id }">${item.gradeName }</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<label>班级：</label>
					<select name="classId" class="combox" id="w_combox_class">
						<option value="0">所有班级</option>
					</select>
				</li>
				<li>
					<label>考试批次：</label>
					<select name="examType" class="combox" >
						<c:forEach items="${examsEnums }" var="item" varStatus="i">
							<option value="${i.index }">${item.text}</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<label>学科：</label>
					<select name="subjectId" class="combox" >
						<c:forEach items="${subjects }" var="item">
							<option value="${item.id }">${item.subjectName }</option>
						</c:forEach>
					</select>
				</li>
			</ul>
			<div class="subBar">
				<ul>						
					<li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
				</ul>
			</div>
		</div>
	</div>
</form>

<div class="pageContent">
	<div class="pageFormContent" layoutV="58">
            <div id="chartdiv"></div>
   </div>
</div>