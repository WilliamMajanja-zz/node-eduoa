<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<script type="text/javascript">
    function closeTab(json) {
        $("#article_close_button").trigger("click");
        dialogReloadRel(json);
    }
</script>
<div class="pageContent">
    <form method="post" action="${contextPath }/management/eduoa/article/create" enctype="multipart/form-data"
          class="required-validate pageForm"
          onsubmit="return iframeCallback(this, dialogReloadNavTab);">
        <div class="pageFormContent" layoutH="58">
            <p>
                <label>标题：</label>
                <input type="text" name="title" class="required" size="32" maxlength="32"/>
                <input type="hidden" name="channelCode" value="${channelCode}" size="32" maxlength="32"/>
            </p>

            <p>
                <label>图片&附件：</label>
                <input name="uploadFile" type="file" />
            </p>

            <p>
                <label>内容：</label>
                <textarea class="editor" name="content" rows="12" cols="68" tools="simple"></textarea>
            </p>
        </div>

        <div class="formBar">
            <ul>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button type="submit">发布</button>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="button">
                        <div class="buttonContent">
                            <button id="article_close_button" type="button" class="close">关闭</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </form>
</div>