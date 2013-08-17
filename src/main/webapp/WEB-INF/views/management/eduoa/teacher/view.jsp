<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>    
<div class="pageContent">
<form action="#">
    <div class="pageFormContent" layoutH="56">

        <p>
            <label>员工序号：</label>
            <input name="teacherNumber" type="text" size="30" value="${teacherInfo.teacherNumber}" />
        </p>

        <p>
            <label>姓名：</label>
            <input name="teacherName" class="required" value="${teacherInfo.teacherName}" type="text" size="30" alt="请输入姓名"/>
        </p>

        <p>
            <label>身份证号码：</label>
            <input name="idNumber" class="required" value="${teacherInfo.idNumber}" type="text" size="30" maxlength="18" alt="请输入身份证号码"/>
        </p>

        <p>
            <label>性别：</label>
            <select name="gender" class="combox">
                <c:forEach items="${genderEnum}" var="item" >
                    <option ${teacherInfo.gender == item.index ? 'selected="selected"' : ''}
                            value="${item.index}">${item.text}</option>
                </c:forEach>
            </select>
        </p>

        <p>
            <label>出生日期：</label>
            <input type="text" name="birthday" value='<fmt:formatDate value="${teacherInfo.birthday}" pattern="yyyy-MM-dd"/>'
                   class="date" size="30"/><a class="inputDateButton"
                                              href="javascript:;">选择</a>
        </p>

        <p>
            <label>手机号：</label>
            <input type="text" name="phone" class="required" size="30" value="${teacherInfo.phone}"/>
        </p>

        <p>
            <label>电子邮箱：</label>
            <input type="text" name="email" class="required" size="30" value="${teacherInfo.email}"/>
        </p>

        <div class="divider"></div>

        <p>
            <label>是否是教师：</label>
            <select name="teacher" class="combox">
                <c:forEach items="${isTeachers}" var="item" >
                    <option ${teacherInfo.teacher == item.index ? 'selected="selected"' : ''}
                            value="${item.index}">${item.text}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label>年级：</label>
            <select name="oaGradeByGradeId.id" class="combox">
                <c:forEach items="${grades}" var="item" >
                    <option ${teacherInfo.oaGradeByGradeId.id == item.id ? 'selected="selected"' : ''}
                            value="${item.id}">${item.gradeName}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label>工作岗位：</label>
            <input type="hidden" name="securityOrganizationByOrgId.id" value="${teacherInfo.securityOrganizationByOrgId.id}"/>
            <input type="text" class="required" name="securityOrganizationByOrgId.name" value="${teacherInfo.securityOrganizationByOrgId.name}" />
            <a class="btnLook" target="dialog" width="530" mask="true" height="450" href="${contextPath }/management/eduoa/teacher/tree"
               lookupGroup="securityOrganizationByOrgId" title="选择">选择</a>
        </p>
        <p>
            <label>职务：</label>
            <select name="oaPositionByPositionId.id" class="combox">
                <option value="">无职务</option>
                <c:forEach items="${positions}" var="item" >
                    <option ${teacherInfo.oaPositionByPositionId.id == item.id ? 'selected="selected"' : ''}
                            value="${item.id}">${item.positionName}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label>学科：</label>
            <select name="oaSubjectBySubjectId.id" class="combox">
                <option value="">不教授</option>
                <c:forEach var="item" items="${subjects}">
                    <option ${teacherInfo.oaSubjectBySubjectId.id == item.id ? 'selected="selected"' : ''}
                            value="${item.id}">${item.subjectName}</option>
                </c:forEach>
            </select>
        </p>

        <div class="divider"></div>

        <p>
            <label>政治面貌：</label>
            <select name="politicalLandscape" class="combox">
                <c:forEach items="${politicalLandscapeEnum}" var="item" >
                    <option ${teacherInfo.politicalLandscape == item.index ? 'selected="selected"' : ''}
                            value="${item.index}">${item.text}</option>
                </c:forEach>
            </select>
        </p>

        <p>
            <label>参加工作时间：</label>
            <input type="text" name="workingTime" value='<fmt:formatDate value="${teacherInfo.workingTime}" pattern="yyyy-MM-dd"/>'
                   class="date" size="30"/><a class="inputDateButton"
                                              href="javascript:;">选择</a>
        </p>
        <p>
            <label>进入学校时间：</label>
            <input type="text" name="joinTime" value='<fmt:formatDate value="${teacherInfo.joinTime}" pattern="yyyy-MM-dd"/>'
                   class="date" size="30"/><a class="inputDateButton" href="javascript:;">选择</a>
        </p>
        <p>
            <label>原始学历：</label>
            <select name="originalEducation" class="combox" >
                <c:forEach items="${educations}" var="item" >
                    <option ${teacherInfo.originalEducation == item.index ? 'selected="selected"':''}
                            value="${item.index}">${item.text}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label>原始学历取得时间：</label>
            <input type="text" name="originalEducationTime"
                   value='<fmt:formatDate value="${teacherInfo.originalEducationTime}" pattern="yyyy-MM-dd"/>'
                   class="date" size="30"/><a class="inputDateButton"  href="javascript:;">选择</a>
        </p>
        <p>
            <label>最新学历：</label>
            <select name="newEducation" class="combox" >
                <c:forEach items="${educations}" var="item" >
                    <option ${teacherInfo.newEducation == item.index ? 'selected="selected"':''}
                            value="${item.index}">${item.text}</option>
                </c:forEach>
            </select>
        </p>
        <p>
            <label>最新学历取得时间：</label>
            <input type="text" name="newEducationTime"
                   value='<fmt:formatDate value="${teacherInfo.newEducationTime}" pattern="yyyy-MM-dd"/>'
                   class="date" size="30"/><a class="inputDateButton"
                                              href="javascript:;">选择</a>
        </p>
        <p>
            <label>编制情况：</label>
            <select name="establishment" class="combox" >
                <c:forEach items="${establishments}" var="item" >
                    <option ${teacherInfo.establishment == item.index?'selected="selected"':''}
                            value="${item.index}">${item.text}</option>
                </c:forEach>
            </select>
        </p>
    </div>
	
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>