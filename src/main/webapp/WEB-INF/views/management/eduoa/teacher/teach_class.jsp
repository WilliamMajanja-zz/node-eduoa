<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<script type="text/javascript">
    function divAjax(json) {
        dialogReloadRel2ClassTeacher(json, "toNewTeachClass", '/management/eduoa/teacher/classList/${teacherInfo.id}');
        var $input = $("#newTeachClassInput");
        $('input', $input).val("");
    }
</script>
<div class="pageContent">
    <form id="permissionForm" method="post" action="${contextPath }/management/eduoa/teacher/createTeachClass"
          class="required-validate pageForm" onsubmit="return validateCallback(this, divAjax);">
        <div id="teacherClassFormContent" class="pageFormContent" layoutH="50">
            <fieldset>
                <legend>任课班级列表</legend>
                <div id="toNewTeachClass">
                    <c:import url="/management/eduoa/teacher/classList/${teacherInfo.id}"/>
                </div>
            </fieldset>
            <fieldset id="newTeachClassInput">
                <legend>任课班级新增</legend>
                <p>
                    <label>任课班级：</label>
                    <input type="hidden" name="oaTeacherInfoByTeacherId.id" value="${teacherInfo.id}">
                    <input type="hidden" name="oaTeacherInfoByTeacherId.teacherName" value="${teacherInfo.teacherName}">
                    <input type="hidden" name="oaClassByClassId.id" />
                    <input type="text" class="required" name="oaClassByClassId.className" readonly="true" />
                    <a class="btnLook" target="dialog" width="500" height="400"
                       lookupGroup="oaClassByClassId" mask="true"
                       href="${contextPath }/management/eduoa/teacher/tree_grade"
                       title="选择">选择</a>
                </p>

                <p>
                    <label>开始时间：</label>
                    <input type="text" name="startTime" class="date required" size="16" maxlength="16"/>
                    <span class="info">&nbsp;&nbsp;任课开始时间</span>
                </p>

                <p>
                    <label>结束时间：</label>
                    <input type="text" name="endTime" class="date required" size="16" maxlength="16" />
                    <span class="info">&nbsp;&nbsp;任课结束时间</span>
                </p>

                <div class="button" id="newTeachClass">
                    <div class="buttonContent">
                        <button type="submit">新增</button>
                    </div>
                </div>
            </fieldset>
        </div>

        <div class="formBar">
            <ul>
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