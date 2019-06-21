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
			<form role="form" class="form-inline" id="userSearchAlipayOrder">
				<div class="input-group">
					<input type="text" placeholder="输入商户号" name="merchantId" id="alipayOrdermerchantId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入代理商号" name="agentId" id="alipayOrderagentId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入手机号" name="phone" id="alipayOrderphone"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入订单号" name="orderNo" id="alipayOrderorderNo"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div><br>
				<div class="input-group">
					<select name="state" id="alipayOrderstate" class="input form-control">
	  					<option value ="">请选择订单状态</option>
	  					<option value ="1">待支付 </option>
	  					<option value ="3">成功</option>
	  					<option value ="4">失败</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="appId" id="appId" class="input form-control" >
						<option value ="">请选择app  </option>
		                        <c:forEach items="${app}" var="key">
							 		<option value ="${key.appId}">${key.appName}  </option>
								</c:forEach>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div><br>
				<div class="form-group">
				<div class="col-sm-8">
					<input type="text" placeholder="开始时间" name="starttime" id="starttime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
					<span class="help-block m-b-none"></span>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-8">
					<input type="text" placeholder="结束时间" name="finishtime" id="finishtime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
						<span class="help-block m-b-none">
				</div>
			</div>
				<div class="input-group">
					<select name="type" id="alipayOrdertype" class="input form-control">
	  					<option value ="">请选择订单类型</option>
	  					<option value ="dropRate">降费率</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchAlipayOrder();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
				<div class="input-group">
					<button type="button" class="btn btn btn-primary"
							onclick="javascript:alipayOrderExcel();">
						<i class="fa fa-search"></i> 导出
					</button>
				</div>
			</form>
			<div class="table-responsive">
				<table id="alipayOrder" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getUserIdSelectionsAlipayOrder() {
		return $.map($("#alipayOrder").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	/* function editAgent() {
		var cbox = getUserIdSelections();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		battcn.ajaxOpen({
			title : '编辑结算底价和代发费用',
			href : rootPath + '/Agent/editUI.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	} */


	//Excel导出
	function alipayOrderExcel(){
		var merchantId = $("#alipayOrdermerchantId").val();
		var agentId = $("#alipayOrderagentId").val();
		var phone = $("#alipayOrderphone").val();
		var orderNo = $("#alipayOrderorderNo").val();
		var state = $("#alipayOrderstate").val();
		var type = $("#alipayOrdertype").val();
		var appId = $("#appId").val();
		/*var starttime = $("#starttime").val();
		var finishtime = $("#finishtime").val();*/
		window.location.href=rootPath + 'excel/alipayOut.shtml?merchantId='+merchantId+'&agentId='+agentId+'&phone='+phone+'&orderNo='+orderNo+'&state='+state+'&type='+type+'&appId='+appId;


	}
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchAlipayOrder() {
		$('#alipayOrder').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsAlipayOrder(params) {
		var merchantId = $("#alipayOrdermerchantId").val();
		var agentId = $("#alipayOrderagentId").val();
		var phone = $("#alipayOrderphone").val();
		var orderNo = $("#alipayOrderorderNo").val();
		var state = $("#alipayOrderstate").val();
		var type = $("#alipayOrdertype").val();
		var appId = $("#appId").val();
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
			merchantId : merchantId,
			agentId : agentId,
			phone : phone,
			orderNo : orderNo,
			state : state,
			type : type,
			appId : appId,
			starttime : starttime,
			finishtime : finishtime
		}
	} 
	$('#alipayOrder').bootstrapTable({
		url : rootPath + '/AlipayOrderEntity/getAlipayOrderList.shtml',
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
		queryParams : queryParamsAlipayOrder,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'merchantId',
			title : '商户号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'name',
			title : '名称',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'phone',
			title : '手机号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'orderNo',
			title : '订单号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'state',
			title : '状态',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "1"){
					return '待支付';
				}
				if(value == "3"){
					return '成功';
				}
				if(value == "4"){
					return '失败';
				}
			}
		}, {
			field : 'totalAmount',
			title : '交易金额',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'reducedAmount',
			title : '随机立减金额',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'amount',
			title : '到账金额',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'type',
			title : '交易类型',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "dropRate"){
					return '降费率';
				}
				if(value == "levelPay"){
					return '升级费';
				}
			}
		}, {
			field : 'agentId',
			title : '归属代理商',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'institutionId',
			title : '机构号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'appId',
			title : '归属App',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "0000"){
					return '帐期机器人';
				}
			}
		}, {
			field : 'createTime',
			title : '缴费时间',
			align : 'center',
			valign : 'middle',
			formatter: function (value) {
        		return value.substring(0,19);
    		}
		}]
	});
//    	$("#alipayOrderExcel").click(function(){
//    			$("#alipayOrder").tableExport({
//    			    headings: true,
//    			    footers: true,
//    			    formats: "csv",
//    			    fileName: "Excel年月费和升级费查询表",
//    			    bootstrap: false,
//    			    position: "bottom",
//    			    ignoreRows: null,
//    			    ignoreCols: null
//    			});
//    			/* Comma Separated Values (.csv) */
//    			$.fn.tableExport.csv = {
//    			    defaultClass: "csv",
//    			    buttonContent: "Export to csv",
//    			    separator: ",",
//    			    mimeType: "application/csv",
//    			    fileExtension: ".csv"
//    			};
//    			/* default charset encoding (UTF-8) */
//    			$.fn.tableExport.charset = "charset=utf-8";
//
//    			/* default filename if "id" attribute is set and undefined */
//    			$.fn.tableExport.defaultFileName = "myDownload";
//
//    			/* default class to style buttons when not using bootstrap  */
//    			$.fn.tableExport.defaultButton = "button-default";
//
//    			/* bootstrap classes used to style and position the export buttons */
//    			$.fn.tableExport.bootstrap = ["btn", "btn-default", "btn-toolbar"];
//
//    			/* row delimeter used in all filetypes */
//    			$.fn.tableExport.rowDel = "\r\n";
//    		});
    		  
</script>