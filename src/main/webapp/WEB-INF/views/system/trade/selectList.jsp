<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script> 
<div class="wrapper wrapper-content animated fadeInRight"
	style="height: 100%">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="doc-buttons">
				 <c:forEach items="${res}" var="key">
					 ${key.description}  
				</c:forEach> 
			</div>
			<form role="form" class="form-inline" id="userSearchXJ">
				<div class="input-group">
					<input type="text" placeholder="输入商户号" name="merChantId" id="merChantId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入商户姓名" name="merName" id="merName"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入代理商编号" name="agentId" id="agentId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="form-group">
				<div class="col-sm-8">
					<input type="text" placeholder="开始时间" name="starttime" id="starttime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-8">
					<input type="text" placeholder="结束时间" name="finishtime" id="finishtime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchxj();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="xj" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
/* 	function getUserIdSelectionsAgentOrders() {
		return $.map($("#AgentOrders").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function editMerChants() {
		var cbox = getUserIdSelectionstrade();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		battcn.ajaxOpen({
			title : '信息变更',
			href : rootPath + '/MerChants/editmerChants.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	} 
	function editmer() {
		var cbox = getUserIdSelectionstrade();
		if (cbox == "") {
			layer.msg("请选择删除项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		layer.confirm('是否删除？', function(index) {
			var url = rootPath + '/MerChants/deleteMer.shtml?id=' + cbox;
			var result = CommnUtil.ajax(url);
			if (result == "success") {
				$('#merChants').bootstrapTable('refresh');
				layer.msg('删除成功');
			} else {
				layer.msg('删除失败');
			}
		});
	} */
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchxj() {
		$('#xj').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsxj(params) {
	 	var merChantId = $("#merChantId").val();
		var merName = $("#merName").val();
		var agentId = $("#agentId").val();
		var starttime = $("#starttime").val();
		var finishtime = $("#finishtime").val();
		var pageSize = params.limit;
		var sort = params.sort;
		var offset = params.offset;
		var order = params.order;
		var pageNum = offset / pageSize + 1;
		return {
			pageSize : pageSize,
			pageNum : pageNum,
			sort : sort,
			order : order,
			merChantId : merChantId,
			merName : merName,
			agentId : agentId,
			starttime : starttime,
			finishtime : finishtime
		}
	} 
	$('#xj').bootstrapTable({
		url : rootPath + '/Trade/selectStatistics.shtml',
		height : '100%',
		sortName : 'id',
		sortOrder : 'desc',
		ajaxOptions: {async:true,timeout:50000},
		showColumns : true,
		showExport : true,
		striped : true,
		pagination : true,
		pageNumber : 1,
		pageSize : 10,
		pageList : "[10,20,30,All]",
		search : false,
		sidePagination : 'server',//服务端分页  client //客户端分页
		idField : 'id',
		uniqueId : 'id',
		responseHandler : responseHandler, //处理分页函数
		queryParams : queryParamsxj,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'total',
			title : '交易量',
			align : 'center',
			valign : 'middle',
			formatter:function totalFeeFormatter(value){
				return value/100;
			}
		}, {
			field : 'fee',
			title : '手续费',
			align : 'center',
			valign : 'middle',
			formatter:function totalFeeFormatter(value){
				return value/100;
			}
		}, {
			field : 'balanceProfit',
			title : '总分润',
			align : 'center',
			valign : 'middle',
			formatter:function totalFeeFormatter(value){
				return value/100;
			}
		}, {
			field : 'profit',
			title : '平台分润',
			align : 'center',
			valign : 'middle',
			formatter:function totalFeeFormatter(value){
				return value/100;
			}
		}, {
			field : 'cost',
			title : '成本',
			align : 'center',
			valign : 'middle',
			formatter:function totalFeeFormatter(value){
				return parseFloat((value/100)).toFixed(2);
			}
		}]
	});
	 
</script>