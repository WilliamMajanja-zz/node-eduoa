<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<form method="post" action="${contextPath }/management/eduoa/gradequery/list2" class="required-validate" 
onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>年级：</label>
					<select name="grade" class="combox" >
						<c:forEach items="${grades }" var="item">
							<option value="${item.id }" ${item.id==grade?"selected":"" }>${item.gradeName }</option>
						</c:forEach>
					</select>
				</li>
				<li>
					<label>考试批次：</label>
					<select name="examType" class="combox" >
						<c:forEach items="${examTypes }" var="item" varStatus="i">
							<option value="${item.index }" ${item.index==examType?"selected":"" }>${item.text}</option>
						</c:forEach>
					</select>
				</li>
			</ul>
			<ul class="searchContent">				
				<li>
					<label>学科：</label>
					<select name="subject" class="combox" >
						<option value="-1">--全部--</option>
						<c:forEach items="${subjects }" var="item">
							<option value="${item.id }" ${item.id==subject?"selected":"" }>${item.subjectName }</option>
						</c:forEach>
					</select>
				</li>
				<li style="width:400px;">
					<label>分数段：</label>
					<input name="score1" class="number" style="width:50px;height:15px;" value="${score1 }"/>-
					<input name="score2" class="number" style="width:50px;height:15px;" value="${score2 }"/>
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
            <div id="chartdiv2"></div>
<script type="text/javascript">
	var chart2 = new FusionCharts("${contextPath }/styles/fusionchart/FusionCharts/Pie2D.swf", "ChartId", "500", "300", "0", "0");
	chart2.setXMLData("${result}");
	chart2.render("chartdiv2");
</script>
   </div>
</div>