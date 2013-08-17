<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>    
<div class="pageContent">
<form action="#">
	<div class="pageFormContent" layoutH="58">
	<p>
		<label>科目名称：</label>
        <label>${subject.subjectName}</label>
	</p>
	<p>
		<label>描述：</label>
		<textarea name="description" cols="28" rows="3" maxlength="255" readOnly=readOnly>${subject.description}</textarea>
	</p>
	</div>
	
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>