<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="wrapper wrapper-content animated fadeInRight"
	style="height: 100%">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="doc-buttons">
				 <c:forEach items="${res}" var="key">
					 ${key.description}  
				</c:forEach> 
			</div>
			<form role="form" class="form-inline" id="userSearchTransactional">
				<div class="input-group">
					<input type="text" placeholder="输入订单号" name="orderNo" id="orderNo"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<!-- <div class="input-group">
					<input type="text" placeholder="输入商户号" name="merchantId" id="merchantId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="type" id="type" class="input form-control">
	  					<option value ="">请选择交易类型</option>
	  					<option value ="planCardPay">计划中支付</option>
	  					<option value ="planRepayment">计划中还款</option>
	  					<option value ="bankPay">充值</option>
	  					<option value ="repayment">还款</option>
	  					</select>
					<span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="state" id="state" class="input form-control">
	  					<option value ="">请选择状态</option>
	  					<option value ="2">成功</option>
	  					<option value ="1">执行中</option>
	  					<option value ="3">失败</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div>  -->
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchtransactional();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="transactional" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	/* function getUserIdSelectionstransactional() {
		return $.map($("#transactional").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function editMerChants() {
		var cbox = getUserIdSelectionstransactional();
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
	function agentSearchtransactional() {
		$('#transactional').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamstransactional(params) {
	 	var orderNo = $("#orderNo").val();
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
			orderNo : orderNo
		}
	} 
	$('#transactional').bootstrapTable({
		url : rootPath + '/Transactional/findTransactional.shtml',
		height : '100%',
		sortName : 'tlId',
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
		queryParams : queryParamstransactional,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'merchantId',
			title : '收益人编号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'orderMerchantId',
			title : '交易人编号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'orderMerchantName',
			title : '交易人姓名',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'orderMerchantPhone',
			title : '交易人手机号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'agentId',
			title : '代理商编号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'orderNo',
			title : '订单号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'rate',
			title : '费率',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'fee',
			title : '收益',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'amount',
			title : '交易金额',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'createTime',
			title : '交易时间',
			align : 'center',
			valign : 'middle',
		}]
	});
	
</script>