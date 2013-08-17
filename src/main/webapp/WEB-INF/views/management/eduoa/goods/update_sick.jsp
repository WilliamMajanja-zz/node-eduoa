<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<script type="text/javascript">
    $(document).ready(function(){
        if ($.validator) {
            $.validator.addMethod("customvalid", function(value, element, params) {
                try{
                    return eval('(' + params + ')');
                }catch(e){
                    return false;
                }
            }, "领取数量大于剩余数量！");
        }
    });
    function recipientsCountFn(element) {
        var recipientsCount = $("#recipientsCount").val();
        var inputVal = $(element).val();
        if (Number(recipientsCount) < Number(inputVal)) {
            return false;
        }
        return true;
    }
</script>
<div class="pageContent">
    <form id="leaveForm" method="post" action="${contextPath }/management/eduoa/goods/sick"
          class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
        <input type="hidden" name="goodsApply.id" value="${goodsApply.id}"/>

        <div class="pageFormContent" style="height: 228px; overflow: auto;" layouth="56">
            <p>
                <label>申请人：</label>
                <input type="text" name="goodsApply.applyTeacherName"
                       value="${goodsApply.applyTeacherName}" readonly="true" class="required" size="32" maxlength="20"/>
            </p>

            <p>
                <label>领取时间：</label>
                <input type="text" name="goodsApply.sickTime" dateFmt="yyyy-MM-dd HH:mm:ss" class="required date" size="30"/>
                <a class="inputDateButton" href="javascript:;">选择</a>
            </p>

            <p>
                <label>领取数量(${goodsApply.lave})：</label>
                <input type="hidden" name="recipientsCount" id="recipientsCount" value="${goodsApply.lave}">
                <input type="text" customvalid="recipientsCountFn(element)" name="goodsReceive.recipientsCount" class="required digits" size="30"/>
            </p>

        </div>

        <div class="formBar">
            <ul>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="submit">提交</button>
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