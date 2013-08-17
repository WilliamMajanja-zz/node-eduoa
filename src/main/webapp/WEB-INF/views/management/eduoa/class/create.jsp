<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<a id="refreshJbsxBox2organizationTree" rel="jbsxBox2organizationTree" target="ajax" href="${request.contextPath}/management/eduoa/class/tree" style="display:none;"></a>
<div class="pageContent">
<form method="post" action="${contextPath }/management/eduoa/class/create" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadRel2Org);">
	<input type="hidden" name="oaGradeByGradeId.id" value="${gradeId }"/>
	<div class="pageFormContent" layoutH="58">
		<dl>
			<dt>名称：</dt>
			<dd>
				<input type="text" name="className" class="required" size="32" maxlength="64" alt="请输入班级名称"/>
			</dd>
		</dl>
        <dl>
            <dt>文理科：</dt>
            <dd>
                <select name="category" class="combox required">
                    <c:forEach items="${classTypeEnums}" var="item">
                        <option value="${item.index}">${item.text}</option>
                    </c:forEach>
                </select>
            </dd>
        </dl>
        <dl>
            <dt>人数限制：</dt>
            <dd>
                <input type="text" name="numberLimit" size="32" maxlength="3" alt="请输入人数"/>
            </dd>
        </dl>
        <dl>
			<dt>描述：</dt>
			<dd>
				<textarea name="description" cols="28" rows="3" maxlength="255"></textarea>
			</dd>
		</dl>	
	</div>
			
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>