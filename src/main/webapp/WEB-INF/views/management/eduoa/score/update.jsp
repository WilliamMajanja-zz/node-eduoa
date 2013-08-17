<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<script type="text/javascript">
    var navClose = function(json) {
        $("#button_close", $("#scoreModel")).trigger("click");
        dialogReloadNavTabAfterCloseTab(json);
    };

    $(document).ready(function(){
        if ($.validator) {
            $.validator.addMethod("getStudent", function(value, element, params) {
                try{
                    return eval('(' + params + ')');
                }catch(e){
                    return false;
                }
            }, "不存在该学号的学生！");

            $.validator.addMethod("scoreValidator", function(value, element, params) {
                try{
                    return eval('(' + params + ')');
                }catch(e){
                    return false;
                }
            }, "分数值不能大于100！");
        }
    });

    function doCheckScore() {
        var score = $("#score_score").val();
        if (Number(score) <= 100) {
            return true;
        }
        return false;
    }

    function doInitStudent() {
        var student_no = $("#score_studentNumber").val();
        $.ajax({
            type: 'GET',
            async : false,
            url:'${contextPath }/management/eduoa/score/student/'+student_no,
            dataType:"json",
            cache: false,
            success: function(json){
                if (json.statusCode == DWZ.statusCode.ok){
                    var value = json.value;
                    $("#score_studentName").val(value.studentName);
                    $("#score_gradeId").val(value.gradeId);

                    $("#score_gradeId").find("option[value='"+value.gradeId+"']").attr("selected","selected");
                    $("#classModel_classId").val(value.classId);
                    $("#classModel_className").val(value.className);
                } else {
                    DWZ.ajaxDone(json);
                    $("#score_studentNumber").val("");
                }
            },
            error: DWZ.ajaxError
        });
        return true;
    }

</script>
<div class="pageContent">
    <form id="scoreModel" method="post" action="${contextPath }/management/eduoa/score/update"
          class="required-validate pageForm"
          onsubmit="return validateCallback(this, navClose);">
        <div class="pageFormContent" layoutH="58">
            <p>
                <label>时间：</label>
                <input type="hidden" name="score.id" value="${score.id}" />
                <input type="text" name="score.examDate" value="<fmt:formatDate value="${score.examDate}" pattern="yyyy-MM-dd"/>"
                       class="required date" size="30"/><a class="inputDateButton" href="javascript:;">选择</a>
            </p>

            <p>
                <label>学号：</label>
                <input type="text" id="score_studentNumber" value="${score.studentNumber}" name="score.studentNumber" getStudent="doInitStudent()" class="required"  size="30"/>
            </p>

            <p>
                <label>考号：</label>
                <input type="text" name="score.examNo" value="${score.examNo}" size="30"/>
            </p>

            <p>
                <label>学生姓名：</label>
                <input type="text" id="score_studentName" value="${score.studentName}" class="required" name="score.studentName" />
            </p>

            <p>
                <label>考试批次：</label>
                <select id="score_examType" name="score.examType">
                    <c:forEach items="${examsEnums}" var="item" >
                        <option ${score.examType == item.index ? 'selected="selected"' : ''} value="${item.index}">${item.text}</option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label>年级：</label>
                <select id="score_gradeId" name="score.gradeId">
                    <option value="">无归属年级</option>
                    <c:forEach items="${grades}" var="item" >
                        <option ${score.gradeId == item.id ? 'selected="selected"' : ''} value="${item.id}">${item.gradeName}</option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label>班级：</label>
                <input type="hidden" id="classModel_classId" value="${score.classId}" name="classModel.classId" />
                <input type="text" class="required" value="${score.className}" id="classModel_className" name="classModel.className" readonly="true" />
                <a class="btnLook" target="dialog" width="500" height="400"
                   lookupGroup="classModel" mask="true"
                   href="${contextPath }/management/eduoa/student/tree_grade"
                   title="选择">选择</a>
            </p>

            <p>
                <label>学科：</label>
                <select name="score.subjectId">
                    <c:forEach var="item" items="${subjects}">
                        <option ${score.subjectId == item.id ? 'selected="selected"' : ''} value="${item.id}">${item.subjectName}</option>
                    </c:forEach>
                </select>
            </p>

            <p>
                <label>成绩：</label>
                <input type="text" value="${score.score}" scoreValidator="doCheckScore()" id="score_score" name="score.score" class="number" size="30"/>
            </p>

            <div class="divider"></div>
            <dl class="nowrap">
                <dt>备注：</dt>
                <dd><textarea cols="90" rows="5" name="score.remark">${score.remark}</textarea></dd>
            </dl>

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
                            <button id="button_close" type="button" class="close">关闭</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>