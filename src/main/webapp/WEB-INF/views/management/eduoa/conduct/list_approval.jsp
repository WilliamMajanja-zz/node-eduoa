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
                } else {
                    $.pdialog.open(url, rel, title, options);
                }
            }

            return false;
        });
    });
</script>
<keta:paginationForm action="${contextPath }/management/eduoa/conduct/listApproval" page="${page }">
	<input type="hidden" name="keywords" value="${keywords}"/>
</keta:paginationForm>

<form method="post" action="${contextPath }/management/eduoa/conduct/listApproval" onsubmit="return navTabSearch(this)">
	<div class="pageHeader">
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>物品名称：</label>
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

		</ul>
	</div>
	
	<table class="table" layoutH="138" width="100%">
		<thead>
			<tr>
				<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
                <th width="130">申请时间</th>
                <th width="80">采购人</th>
                <th width="80">采购部门</th>
                <th width="80">审批领导</th>
                <th>物品名称</th>
                <th width="80">单位</th>
                <th width="80">数量</th>
                <th width="130">单价</th>
                <th width="130">总价</th>
                <th width="100">审批状态</th>
                <th width="130">审批时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${purchaseApplies}">
			<tr target="slt_uid" rel="${item.id}">
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${item.conductTeacherName}</td>
                <td>${item.conductOrganizationName}</td>
                <td>${item.leaderName}</td>
                <td>${item.goodsName}</td>
                <td>${item.goodsUnit}</td>
                <td>${item.goodsCount}</td>
                <td>${item.goodsUnitPrice}</td>
                <td>${item.priceSum}</td>
                <td>${item.applyStatueCn}</td>
                <td><fmt:formatDate value="${item.applyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 分页 -->
	<keta:pagination page="${page }"/>
</div>