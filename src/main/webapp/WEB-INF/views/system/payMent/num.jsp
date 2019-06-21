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
			<form role="form" class="form-inline" id="paymentNumSearchForm">
				<input id="amount" name="amount"  class="form-control" type="hidden" value="" >
			</form>
			<div class="table-responsive">
				<table id="paymentNumTables" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
			<form class="form-horizontal m-t required-validate" id="paymentNum">
					<label class="col-sm-3 control-label"><span style="color: red;">小咖升级大咖缴费金额：</span></label>
					<div class="col-sm-5">
						<input name="id" id="id" type="hidden" value="${payment.id }" />
						 <input name="paymentNum" id="paymentNum" type="text" value="${payment.paymentNum/100 }" />
	    			 </div>
					<span  style="margin-left:300px;" ><input class="btn btn btn-primary" type="button" value="提交" onclick="saveChangeAmount()"></span>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function(){
	saveChangeAmount = function(obj) {
		var changeAmount = $("#changeAmount").val();
		if(changeAmount == ''){
			alert('升级金额不能为空')
			return false;
		}else if($("#paymentNum").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/KSPaymentFix/confirm.shtml",
				data: $('#paymentNum').serializeArray(),
				success: function(data){
					layer.confirm(data, function(index) {
						battcn.closeWindow();
						$('#paymentNumTables').bootstrapTable('refresh');
			        	return false;
 						});

				}
			});
  		}
	} 
	
 }); 
</script>
<script type="text/javascript">
	function getpaymentNumId() {
		return $.map($("#paymentNumTables").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	 function editpaymentNum() {
		var cbox = getpaymentNumId();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		battcn.ajaxOpen({
			title : '编辑升级模式',
			href : rootPath + '/KSPaymentFix/alertNum.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	} 
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	/* function agentSearch() {
		$('#usedTable').bootstrapTable('refresh');
	} */
	//重写参数传递
	 function queryParamspaymentNum(params) {
		var pageSize = params.limit;
		var sort = params.sort;
		var offset = params.offset;
		var order = params.order;
		var pageNum = offset / pageSize + 1;
		return {
			pageSize : pageSize,
			pageNum : pageNum,
			sort : sort,
			order : order
		}
	} 
	$('#paymentNumTables').bootstrapTable({
		url : rootPath + '/KSPaymentFix/getMerChants.shtml',
		height : '100%',
		sortName : 'id',
		sortOrder : 'asc',
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
		queryParams : queryParamspaymentNum,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		},{
			field : 'identityName',
			title : '级别名称',
			align : 'center',
			valign : 'middle'
		},{
			field : 'oneMer',
			title : '直推份额（元）',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
					return value/100;
			}
		}, {
			field : 'twoMer',
			title : '间推份额（元）',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'threeMer',
			title : '间间推份额（元）',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'fixReward',
			title : '直接代理份额（元）',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'subsidy',
			title : '补贴（元）',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}]
	});
</script>