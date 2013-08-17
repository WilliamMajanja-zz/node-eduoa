<%@page import="com.node.system.entity.main.Organization" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="/WEB-INF/views/include.inc.jsp" %>
<%!
    public String tree(Organization organization, String basePath) {
        if (organization.getChildren().isEmpty()) {
            return "";
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append("<ul>" + "\n");
        for (Organization o : organization.getChildren()) {
            buffer.append("<li><a href=\"javascript:\" onclick=\"returnClose({organizationId:'" + o.getId()
                    + "', organizationName:'" + o.getName() + "'})\" >" + o.getName() + "</a>" + "\n");
            buffer.append(tree(o, basePath));
            buffer.append("</li>" + "\n");
        }
        buffer.append("</ul>" + "\n");
        return buffer.toString();
    }
%>
<%
    Organization organization2 = (Organization) request.getAttribute("organization");
%>
<script type="text/javascript">
    function bringBackSuggest(args){
        var $box = $("#organization_tree_select").parents(".unitBox:first");
        $box.find(":input").each(function(){
            var $input = $(this), inputName = $input.attr("name");
            for (var key in args) {
                if (key == inputName) {
                    $input.val(args[key]);
                    break;
                }
            }
        });
    }

    function returnClose(obj) {
        bringBackSuggest(obj);
        $("#treeLookup_close", $(".formBar")).click();
    }
</script>
<div class="pageContent">
    <div class="pageFormContent" layoutH="58">
        <ul class="tree expand">
            <li>
                <a href="javascript:">${organization.name }</a> <%=tree(organization2, request.getContextPath())%>
            </li>
        </ul>
    </div>

    <div class="formBar">
        <ul>
            <li><div class="button"><div class="buttonContent"><button id="treeLookup_clean" type="button">清空</button></div></div></li>
        </ul>
        <ul>
            <li><div class="button"><div class="buttonContent"><button id="treeLookup_close" class="close" type="button">关闭</button></div></div></li>
        </ul>
    </div>
    <script type="text/javascript">
        $("#treeLookup_clean").on("click", function(){
            bringBackSuggest({organizationId:'', organizationName:''});
            $("#treeLookup_close").trigger("click");
        });
    </script>
</div>