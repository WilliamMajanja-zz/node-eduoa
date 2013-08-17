<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<div class="pageContent">
    <form id="permissionForm" method="post" action="${contextPath }/management/eduoa/teacher/createGuideClass"
          class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
        <div id="teacherClassFormContent" class="pageFormContent" layoutH="50">
            <fieldset id="newTeachClassInput">
                <legend>任课班级新增</legend>
                <p>
                    <label>任课班级：</label>
                    <input type="hidden" name="id" value="${classTeacher.id}">
                    <input type="hidden" name="oaTeacherInfoByTeacherId.id" value="${teacherInfo.id}">
                    <input type="hidden" name="oaTeacherInfoByTeacherId.teacherName" value="${teacherInfo.teacherName}">
                    <input type="hidden" name="oaClassByClassId.id" value="${classTeacher.oaClassByClassId.id}" />
                    <input type="text" class="required" name="oaClassByClassId.className"
                           value="${classTeacher.oaClassByClassId.className}" readonly="true" />
                    <a class="btnLook" target="dialog" width="500" height="400"
                       lookupGroup="oaClassByClassId" mask="true"
                       href="${contextPath }/management/eduoa/teacher/tree_grade"
                       title="选择">选择</a>
                </p>

                <p>
                    <label>开始时间：</label>
                    <input type="text" name="startTime" class="date required" size="16"
                           value="<fmt:formatDate value="${classTeacher.startTime}" pattern="yyyy-MM-dd"/>" maxlength="16"/>
                    <span class="info">&nbsp;&nbsp;任课开始时间</span>
                </p>

                <p>
                    <label>结束时间：</label>
                    <input type="text" name="endTime" class="date required" size="16"
                           value="<fmt:formatDate value="${classTeacher.endTime}" pattern="yyyy-MM-dd"/>" maxlength="16" />
                    <span class="info">&nbsp;&nbsp;任课结束时间</span>
                </p>
            </fieldset>
        </div>

        <div class="formBar">
            <ul>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="submit">保存</button>
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