<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<div class="pageContent">
    <form method="post" action="${contextPath }/management/eduoa/teacher/createLink"
          class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
        <input type="hidden" name="user.id" value="${user.id}"/>
        <div class="pageFormContent" layoutH="56">

            <p>
                <label>员工序号：</label>
                <input name="teacherNumber" type="text" size="30" value="" />
            </p>

            <p>
                <label>姓名：</label>
                <input name="teacherName" class="required" value="${user.realname}" type="text" size="30" alt="请输入姓名"/>
            </p>

            <p>
                <label>身份证号码：</label>
                <input name="idNumber" class="required" type="text" size="30" maxlength="18" alt="请输入身份证号码"/>
            </p>

            <p>
                <label>性别：</label>
                <select name="gender" class="combox">
                    <c:forEach items="${genderEnum}" var="item" >
                        <option value="${item.index}">${item.text}</option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label>出生日期：</label>
                <input type="text" name="birthday" class="date" size="30"/><a class="inputDateButton"
                                                                               href="javascript:;">选择</a>
            </p>

            <p>
                <label>手机号：</label>
                <input type="text" name="phone" class="required" size="30"/>
            </p>

            <p>
                <label>电子邮箱：</label>
                <input type="text" name="email" class="required" size="30"/>
            </p>

            <div class="divider"></div>

            <p>
                <label>是否是教师：</label>
                <select name="teacher" class="combox">
                    <c:forEach items="${isTeachers}" var="item" >
                        <option value="${item.index}">${item.text}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label>年级：</label>
                <select name="oaGradeByGradeId.id" class="combox">
                    <option value="">无归属年级</option>
                    <c:forEach items="${grades}" var="item" >
                        <option value="${item.id}">${item.gradeName}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label>工作岗位：</label>
                <input type="hidden" name="securityOrganizationByOrgId.id"/>
                <input type="text" class="required" name="securityOrganizationByOrgId.name" />
                <a class="btnLook" target="dialog" width="530" mask="true" height="450" href="${contextPath }/management/eduoa/teacher/tree"
                   lookupGroup="securityOrganizationByOrgId" title="选择">选择</a>
            </p>
            <p>
                <label>职务：</label>
                <select name="oaPositionByPositionId.id" class="combox">
                    <option value="">无职务</option>
                    <c:forEach items="${positions}" var="item" >
                        <option value="${item.id}">${item.positionName}</option>
                    </c:forEach>
                </select>
            </p>
            <%--<p>--%>
                <%--<label>是否是班主任：</label>--%>
                <%--<select name="headTeacher" class="combox">--%>
                    <%--<c:forEach items="${headTeacher}" var="item" >--%>
                        <%--<option value="${item.index}">${item.text}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</p>--%>
            <%--<p>--%>
                <%--<label>带领班级：</label>--%>
                <%--<input type="hidden" name="headClass.classIds" value=""/>--%>
                <%--<input type="text" class="required" name="headClass.classNames" value=""/>--%>
                <%--<a class="btnLook" target="dialog" width="530" height="450"--%>
                   <%--lookupGroup="headClass" mask="true"--%>
                   <%--href="${contextPath }/management/eduoa/teacher/tree_head_teacher"--%>
                   <%--title="选择">选择</a>--%>
            <%--</p>--%>
            <p>
                <label>学科：</label>
                <select name="oaSubjectBySubjectId.id" class="combox">
                    <option value="">不教授</option>
                    <c:forEach var="item" items="${subjects}">
                        <option value="${item.id}">${item.subjectName}</option>
                    </c:forEach>
                </select>
            </p>
            <%--<p>--%>
                <%--<label>任课班级：</label>--%>
                <%--<input type="hidden" name="teacherClass.classIds" value=""/>--%>
                <%--<input type="text" class="required" name="teacherClass.classNames" value=""/>--%>
                <%--<a class="btnLook" target="dialog" width="530" height="450"--%>
                   <%--lookupGroup="teacherClass" mask="true"--%>
                   <%--href="${contextPath }/management/eduoa/teacher/tree_grade"--%>
                   <%--title="选择">选择</a>--%>
            <%--</p>--%>

            <div class="divider"></div>

            <p>
                <label>政治面貌：</label>
                <select name="politicalLandscape" class="combox">
                    <c:forEach items="${politicalLandscapeEnum}" var="item" >
                        <option value="${item.index}">${item.text}</option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label>参加工作时间：</label>
                <input type="text" name="workingTime" class="date" size="30"/><a class="inputDateButton"
                                                                              href="javascript:;">选择</a>
            </p>
            <p>
                <label>进入学校时间：</label>
                <input type="text" name="joinTime" class="date" size="30"/><a class="inputDateButton"
                                                                                 href="javascript:;">选择</a>
            </p>
            <p>
                <label>原始学历：</label>
                <select name="originalEducation" class="combox" >
                    <c:forEach items="${educations}" var="item" >
                        <option value="${item.index}">${item.text}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label>原始学历取得时间：</label>
                <input type="text" name="originalEducationTime" class="date" size="30"/><a class="inputDateButton"
                                                                              href="javascript:;">选择</a>
            </p>
            <p>
                <label>最新学历：</label>
                <select name="newEducation" class="combox" >
                    <c:forEach items="${educations}" var="item" >
                        <option value="${item.index}">${item.text}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label>最新学历取得时间：</label>
                <input type="text" name="newEducationTime" class="date" size="30"/><a class="inputDateButton"
                                                                                           href="javascript:;">选择</a>
            </p>
            <p>
                <label>职称：</label>
                <select name="certificatesType" class="combox" >
                    <c:forEach items="${certificateTypes}" var="item" >
                        <option value="${item.id}">${item.typeName}</option>
                    </c:forEach>
                </select>
            </p>
            <p>
                <label>职称评定时间：</label>
                <input type="text" name="certificatesTime" dateFmt="yyyy-MM-dd" class="date" size="30"/><a class="inputDateButton"
                                                                                      href="javascript:;">选择</a>
            </p>
            <p>
                <label>编制情况：</label>
                <select name="establishment" class="combox" >
                    <c:forEach items="${establishments}" var="item" >
                        <option value="${item.index}">${item.text}</option>
                    </c:forEach>
                </select>
            </p>
        </div>

        <div class="formBar">
            <ul>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="submit">确定</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" class="close">关闭</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>