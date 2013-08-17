<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<script type="text/javascript">
    $(document).ready(function(){
        var navClose = function(json) {
            $("#button_close", $("#leaveForm")).trigger("click");
            dialogReloadNavTabAfterCloseTab(json);
        };
        //提交按钮
        $("#button_submit").on('click', function(event){
            $("#leaveForm").prop("action", '${contextPath }/management/eduoa/purchase/submit');
            return validateCallback($("#leaveForm"), navClose);
        });
        //保存按钮
        $("#button_save").on('click', function(event){
            $("#leaveForm").prop("action", '${contextPath }/management/eduoa/purchase/save');
            return validateCallback($("#leaveForm"), navClose);
        });

        $("#leaderName").on('click', function(event) {
            $("#leaderName_select").trigger("click");
        });

    });
</script>
<div class="pageContent">
    <form id="leaveForm" method="post" action="${contextPath }/management/eduoa/purchase/update" class="required-validate pageForm">
        <input type="hidden" name="purchaseApply.id" value="${purchaseApply.id}"/>

        <div class="pageFormContent" style="height: 228px; overflow: auto;" layouth="56">
            <p>
                <label>申请人：</label>
                <input type="hidden" name="purchaseApply.applyTeacherId" value="${purchaseApply.applyTeacherId}" size="32" maxlength="32"/>
                <input type="text" name="purchaseApply.applyTeacherName" value="${purchaseApply.applyTeacherName}" readonly="true" class="required" size="32" maxlength="20"/>
            </p>

            <p>
                <label>申请部门：</label>
                <input type="hidden" name="purchaseApply.applyOrganizationId" value="${purchaseApply.applyOrganizationId}" class="required" size="30"/>
                <input type="text" name="purchaseApply.applyOrganizationName" value="${purchaseApply.applyOrganizationName}" class="required" size="30"/>
            </p>

            <p>
                <label>申请时间：</label>
                <input type="text" name="purchaseApply.createTime"
                       value="<fmt:formatDate value="${purchaseApply.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                       dateFmt="yyyy-MM-dd HH:mm:ss" class="required date" size="30"/><a class="inputDateButton" href="javascript:;">选择</a>
            </p>

            <p>
                <label>审批人：</label>
                <input type="hidden" name="leader.leaderId" value="${purchaseApply.leaderId}" size="32" maxlength="32"/>
                <input type="hidden" name="leader.leaderPosition" value="${purchaseApply.leaderPosition}" size="32" maxlength="32"/>
                <input type="text" name="leader.leaderName" id="leaderName" value="${purchaseApply.leaderName}" class="required" size="32" maxlength="20"/>
                <a id="leaderName_select" class="btnLook" target="dialog" width="530" mask="true" height="450"
                   href="${contextPath }/management/eduoa/teacher/leader"
                   lookupGroup="leader" title="选择">选择</a>
            </p>

            <div class="divider"></div>
            <p>
                <label>审批人：</label>
                <input type="hidden" name="purchaseApply.goodsId"  value="${purchaseApply.goodsId}" size="32" maxlength="32"/>
                <input type="text" name="purchaseApply.goodsName" class="required" value="${purchaseApply.goodsName}" size="32" maxlength="32"/>
            </p>
            <p>
                <label>单位：</label>
                <input type="text" name="purchaseApply.goodsUnit" value="${purchaseApply.goodsUnit}" class="required" size="32" maxlength="20"/>
            </p>
            <p>
                <label>数量：</label>
                <input type="text" name="purchaseApply.goodsCount" value="${purchaseApply.goodsCount}" class="required digits" size="32" maxlength="20"/>
            </p>

            <div class="divider"></div>
            <dl class="nowrap">
                <dt>申请备注：</dt>
                <dd><textarea cols="90" rows="5" name="purchaseApply.remark">${purchaseApply.remark}</textarea></dd>
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