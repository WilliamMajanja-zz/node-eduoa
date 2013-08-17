<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<div class="pageContent">
    <form id="studentModel" method="post" action="${contextPath }/management/eduoa/student/update" class="required-validate pageForm"
          onsubmit="return validateCallback(this, dialogReloadNavTab);">
        <input type="hidden" name="student.id" value="${student.id}"/>

        <div class="pageFormContent" layoutH="58">
            <fieldset id="studentInfo">
                <legend>学生基本信息</legend>
                <p>
                    <label>学号：</label>
                    <input type="text" name="student.studentNumber" value="${student.studentNumber}" class="required" size="30" maxlength="30"/>
                </p>
                <p>
                    <label>姓名：</label>
                    <input type="text" name="student.studentName" value="${student.studentName}" class="required" size="30" maxlength="30"/>
                </p>

                <p>
                    <label>性别：</label>
                    <select name="student.gender">
                        <c:forEach items="${genderEnum}" var="item" varStatus="state">
                            <option ${state.index == student.gender ? 'selected="selected"':''} value="${item.index}">${item.text}</option>
                        </c:forEach>
                    </select>
                </p>
                <p>
                    <label>身份证：</label>
                    <input type="text" name="student.idNumber" value="${student.idNumber}" class="required" size="30" maxlength="18"/>
                </p>
                <p>
                    <label>出生年月：</label>
                    <input type="hidden" name="student.createTime" value="${student.createTime}"/>
                    <input type="text" name="student.birthday"
                           value="<fmt:formatDate value="${student.birthday}" pattern="yyyy-MM-dd"/>"
                           class="required date" size="30"/><a class="inputDateButton" href="javascript:;">选择</a>
                </p>
            </fieldset>

            <fieldset id="classGradeInfo">
                <legend>所属年级班级</legend>
                <p>
                    <label>年级：</label>
                    <input type="hidden" name="classModel.gradeId" value="${classModel.gradeId}" />
                    <input type="text" class="required" name="classModel.gradeName" value="${classModel.gradeName}" readonly="true" />
                </p>
                <p>
                    <label>班级：</label>
                    <input type="hidden" name="classModel.classId" value="${classModel.classId}"/>
                    <input type="text" class="required" name="classModel.className" value="${classModel.className}" readonly="true" />
                    <a class="btnLook" target="dialog" width="500" height="400"
                       lookupGroup="classModel" mask="true"
                       href="${contextPath }/management/eduoa/student/tree_grade"
                       title="选择">选择</a>
                </p>

                <p>
                    <label>开始日期：</label>
                    <input type="text" name="classModel.startTime"
                           value="<fmt:formatDate value="${classModel.startTime}" pattern="yyyy-MM-dd"/>"
                           class="required date" size="30"/><a class="inputDateButton" href="javascript:;">选择</a>
                </p>
                <p>
                    <label>结束日期：</label>
                    <input type="text" name="classModel.endTime"
                           value="<fmt:formatDate value="${classModel.endTime}" pattern="yyyy-MM-dd"/>"
                           class="required date" size="30"/><a class="inputDateButton" href="javascript:;">选择</a>
                </p>
            </fieldset>

            <fieldset id="contactInfo">
                <legend>联系方式</legend>
                <p>
                    <label>关系：</label>
                    <input type="hidden" name="contact.id" value="${contact.id}"/>
                    <input type="hidden" name="contact.createTime" value="${contact.createTime}"/>
                    <input type="text" name="contact.nexus" value="${contact.nexus}" class="required" size="30" maxlength="30"/>
                </p>
                <p>
                    <label>联系人姓名：</label>
                    <input type="text" name="contact.contactName" value="${contact.contactName}" class="required" size="30" maxlength="30"/>
                </p>

                <p>
                    <label>职业：</label>
                    <input type="text" name="contact.job" value="${contact.job}" size="30" maxlength="18"/>
                </p>
                <p>
                    <label>联系人手机：</label>
                    <input type="text" name="contact.phone" value="${contact.phone}" class="required" size="30"/>
                </p>
            </fieldset>
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