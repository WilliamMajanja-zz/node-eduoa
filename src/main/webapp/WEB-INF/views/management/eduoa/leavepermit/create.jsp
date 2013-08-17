<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<script type="text/javascript">
    $(document).ready(function(){
        var navClose = function(json) {
            DWZ.ajaxDone(json);
            $("#button_close", $("#leaveForm")).trigger("click");
        };
        //提交按钮
        $("#button_submit").on('click', function(event){
            $("#leaveForm").prop("action", '${contextPath }/management/eduoa/leavepermit/submit');
            return validateCallback($("#leaveForm"), navClose);
        });
        //保存按钮
        $("#button_save").on('click', function(event){
            $("#leaveForm").prop("action", '${contextPath }/management/eduoa/leavepermit/save');
            return validateCallback($("#leaveForm"), navClose);
        });
    });
</script>
<div class="pageContent">
    <form id="leaveForm" method="post" action="${contextPath }/management/eduoa/leavepermit/create" class="required-validate pageForm">
        <div class="pageFormContent" style="height: 228px; overflow: auto;" layouth="56">
            <p>
                <label>申请人：</label>
                <input type="hidden" name="leavePermit.applyTeacherId" value="${user.id}" size="32" maxlength="32"/>
                <input type="text" name="leavePermit.applyTeacherName" value="${user.realname}" readonly="true" class="required" size="32" maxlength="20"/>
            </p>

            <p>
                <label>请假天数：</label>
                <input type="text" name="leavePermit.applyDay" class="required" size="30"/>
            </p>

            <p>
                <label>请假开始时间：</label>
                <input type="text" name="leavePermit.startTime" dateFmt="yyyy-MM-dd HH:mm:ss" class="required date" size="30"/><a class="inputDateButton"
                                                                                        href="javascript:;">选择</a>
            </p>
            <p>
                <label>请假结束时间：</label>
                <input type="text" name="leavePermit.endTime" dateFmt="yyyy-MM-dd HH:mm:ss" class="required date" size="30"/><a class="inputDateButton"
                                                                                      href="javascript:;">选择</a>
            </p>
            <p>
                <label>事由：</label>
                <input type="text" name="leavePermit.reason" class="required" size="32" maxlength="200"/>
            </p>
            <p>
                <label>审批人：</label>
                <input type="hidden" name="leader.leaderId" size="32" maxlength="32"/>
                <input type="hidden" name="leader.leaderPosition" size="32" maxlength="32"/>
                <input type="text" name="leader.leaderName" class="required" size="32" maxlength="20"/>
                <a class="btnLook" target="dialog" width="530" mask="true" height="450"
                   href="${contextPath }/management/eduoa/teacher/leader"
                   lookupGroup="leader" title="选择">选择</a>
            </p>

            <div class="divider"></div>
            <dl class="nowrap">
                <dt>备注：</dt>
                <dd><textarea name="leavePermit.remark" rows="5" cols="45" maxlength="255" class="textInput valid" style="width: 574px; height: 61px;"></textarea></dd>
            </dl>
        </div>

        <div class="formBar">
            <ul>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" id="button_submit">提交</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" id="button_save">保存</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="button" id="button_close" class="close">关闭</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>