<%@ page language="java" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<script type="text/javascript">
    //dialogs
    $("a[target=ajaxToDialog]", $(document)).each(function(){

        $(this).click(function(event){
            event.preventDefault();

            var $this = $(this);


            var title = $this.attr("title") || $this.text();
            var rel = $this.attr("rel") || "_blank";
            var options = {};
            var w = $this.attr("width");
            var h = $this.attr("height");
            if (w) options.width = w;
            if (h) options.height = h;
            options.max = eval($this.attr("max") || "false");
            options.mask = eval($this.attr("mask") || "false");
            options.maxable = eval($this.attr("maxable") || "true");
            options.minable = eval($this.attr("minable") || "true");
            options.fresh = eval($this.attr("fresh") || "true");
            options.resizable = eval($this.attr("resizable") || "true");
            options.drawable = eval($this.attr("drawable") || "true");
            options.close = eval($this.attr("close") || "");
            options.param = $this.attr("param") || "";

            var url = unescape($this.attr("href")).replaceTmById($(event.target).parents(".unitBox:first"));
            var checkUrl = unescape($this.attr("checkUrl")).replaceTmById($(event.target).parents(".unitBox:first"));
            DWZ.debug(url);
            if (!url.isFinishedTm()) {
                alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
                return false;
            } else {
                if (checkUrl) {
                    $.ajax({
                        async:true,
                        dataType:'json',
                        type:'post',
                        url:checkUrl,
                        error:DWZ.ajaxError,
                        success:function(json){
                            if (json.statusCode == DWZ.statusCode.ok){
                                $.pdialog.open(url, rel, title, options);
                            } else {
                                DWZ.ajaxDone(json);
                            }
                        }
                    });
                }
            }

            return false;
        });
    });
</script>
<keta:paginationForm action="${contextPath }/management/eduoa/leavepermit/listApproval" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/leavepermit/listApproval" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>申请事由：</label>
					<input type="text" name="keywords" value="${keywords}"/>
				</li>
			</ul>
			<div class="subBar">
				<ul>						
					<li><div class="button"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
				</ul>
			</div>
		</div>
	</div>
</form>

<div class="pageContent">

	<div class="panelBar">
		<ul class="toolBar">
            <shiro:hasPermission name="listApproval:sick">
                <li><a class="arrow_refresh" target="ajaxToDialog" mask="true"
                       checkUrl="${contextPath }/management/eduoa/leavepermit/checkSick/{slt_uid}"
                       href="${contextPath }/management/eduoa/leavepermit/sick/{slt_uid}"><span>销假</span></a></li>
            </shiro:hasPermission>
		</ul>
	</div>
	
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th>事由</th>
				<th width="130">请假开始时间</th>
				<th width="130">请假结束时间</th>
				<th width="130">请假天数</th>
				<th width="100">审批领导</th>
				<th width="130">申请时间</th>
				<th width="130">审批状态</th>
				<th width="130">实休假期</th>
				<th width="130">销假时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${leavePermits}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.reason}</td>
				<td><fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${item.applyDay}</td>
                <td>${item.leaderName}</td>
				<td><fmt:formatDate value="${item.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${item.applyStatueCn}</td>
                <td>
                    <c:if test="${item.realDay != null}">
                        共${item.realDay}天
                    </c:if>
                </td>
                <td><fmt:formatDate value="${item.sickTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page }"/>
</div>