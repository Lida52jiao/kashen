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
			<form role="form" class="form-inline" id="repayRecordSearchForm">
			 	<div class="input-group">
			 		<input type="text" placeholder="输入姓名" name="merchantName" id="merchantName" class="input form-control"> 
			 	</div>
			 	<div class="input-group">
			 		<input type="text" placeholder="输入手机号" name="merchantPhone" id="merchantPhone" class="input form-control"> 
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入商户号" name="merchantId" id="merchantId" class="input form-control"> 
				</div>
				<div class="input-group">
					<select name="state" id="state" class="input form-control">
		  				<option value ="">请选择状态  </option>
		  				<option value="1">已下单</option>
						<option value="3">提现成功</option>
						<option value="4">提现失败</option>
	  				</select>
  				</div>
  				<div class="input-group">
  					<input type="text" placeholder="输入提现单号" name="orderNo" id="orderNo" class="input form-control"> 
				<span class="input-group-btn">
				</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="提现开始时间" name="startTime" id="executestart" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
					<span class="help-block m-b-none">
				</div>
				<div class="input-group">
					<input type="text" placeholder="提现结束时间" name="finishTime" id="executefinish" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
					<span class="help-block m-b-none">
				</div>
					<button type="button" class="btn btn btn-primary"
						onclick="javascript:repaySearch();">
						<i class="fa fa-search"></i> 搜索
					</button>
				<div class="input-group">
						<input class="btn btn btn-primary" id="payRecordExcel" type="button" value="导出">
				</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="repayRecordTable" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
/* 	function getUserIdSelectionsRepayRecord() {
		return $.map($("#repayRecordTable").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	 function editWithdrawals() {
		var cbox = getUserIdSelectionsRepayRecord();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		battcn.ajaxOpen({
			title : '编辑升级费',
			href : rootPath + '/Withdrawals/editUIalter.shtml?id=' + cbox,
			width : '40%',
			height : '60%',
			okhandler : function() {
				save();
			}
		});
	}  */
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	 function repaySearch() {
		$('#repayRecordTable').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsRepayRecord(params) {
		var merchantName = $("#merchantName").val();
		var merchantPhone = $("#merchantPhone").val();
		var merchantId = $("#merchantId").val();
		var state = $("#state").val();
		var orderNo = $("#orderNo").val();
		var startTime = $("#executestart").val();
		var finishTime = $("#executefinish").val();
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
			merchantName : merchantName,
			merchantPhone : merchantPhone,
			merchantId : merchantId,
			state : state,
			orderNo : orderNo,
			startTime : startTime,
			finishTime : finishTime
		}
	} 
	$('#repayRecordTable').bootstrapTable({
		url : rootPath + '/Repay/RecordList.shtml',
		height : '100%',
		sortName : 'createTime',
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
		queryParams : queryParamsRepayRecord,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'orderNo',
			title : '提现单号',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'merchantName',
			title : '姓名',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'merchantPhone',
			title : '手机号',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'amount',
			title : '提现金额',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value/100)).toFixed(2);
			}
		}, {
			field : 'arrivalAmount',
			title : '到账金额',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value/100)).toFixed(2);
			}
		}, {
			field : 'profitFee',
			title : '手续费',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value/100)).toFixed(2);
			}
		}, {
			field : 'aliLoginId',
			title : '到账账号',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'createTime',
			title : '提现时间',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'state',
			title : '提现状态',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "1"){
					return '已下单';
				}
				if(value == "3"){
					return '提现成功';
				}
				if(value == "4"){
					return '提现失败';
				}
			}
		}]
	});
	$("#payRecordExcel").click(function(){
		$("#repayRecordTable").tableExport({
		    headings: true, 
		    footers: true, 
		    formats: "csv",
		    fileName: "Excel返佣提现表",
		    bootstrap: false,
		    position: "bottom",
		    ignoreRows: null,
		    ignoreCols: null
		}); 
		/* Comma Separated Values (.csv) */
		$.fn.tableExport.csv = {
		    defaultClass: "csv",
		    buttonContent: "Export to csv",
		    separator: ",",
		    mimeType: "application/csv",
		    fileExtension: ".csv"
		};
		/* default charset encoding (UTF-8) */
		$.fn.tableExport.charset = "charset=utf-8";
		  
		/* default filename if "id" attribute is set and undefined */
		$.fn.tableExport.defaultFileName = "myDownload";
		  
		/* default class to style buttons when not using bootstrap  */
		$.fn.tableExport.defaultButton = "button-default";
		  
		/* bootstrap classes used to style and position the export buttons */
		$.fn.tableExport.bootstrap = ["btn", "btn-default", "btn-toolbar"];
		  
		/* row delimeter used in all filetypes */
		$.fn.tableExport.rowDel = "\r\n";   
	});
</script>