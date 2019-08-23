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
			<form role="form" class="form-inline" id="userSearchTrades">
				<!-- <div class="input-group">
					<input type="text" placeholder="输入计划编号" name="planId" id="planId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div> -->
				<div class="input-group">
					<input type="text" placeholder="输入订单号" name="orderNo" id="orderNo"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入交易商户号" name=orderMerchantId id="orderMerchantId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入分润商户号" name="merchantId" id="merchantId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="module" id="module" class="input form-control">
	  					<option value ="">请选择分润类型</option>
	  					<option value ="epos">无卡 </option>
	  					<option value ="repayment">还款 </option>
	  					<option value ="alipay">升级费</option>
	  					<option value ="ws">网申</option>
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
					<input type="text" placeholder="开始时间" name="starttime" id="hkstarttime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
					<span class="help-block m-b-none"></span>
				</div>
			</div>
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchtrades();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
				<div class="input-group">
							<input class="btn btn btn-primary" id="tradesExcel"  type="button" value="导出">
					</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="trades" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getUserIdSelectionstrades() {
		return $.map($("#trades").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchtrades() {
		$('#trades').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamstrades(params) {
	 	var orderNo = $("#orderNo").val();
	 	var orderMerchantId = $("#orderMerchantId").val();
		var merchantId = $("#merchantId").val();
		var type = $("#type").val();
		var starttime = $("#hkstarttime").val();
		var appId = $("#appId").val();
		var module = $("#module").val();
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
			orderNo : orderNo,
			orderMerchantId : orderMerchantId,
			merchantId : merchantId,
			type : type,
			starttime : starttime,
			appId : appId,
			module : module
		}
	} 
	$('#trades').bootstrapTable({
		url : rootPath + '/Trade/getTransactionalEntityList.shtml',
		ajaxOptions: {async:true,timeout:5000000},
		height : '100%',
		sortName : 'tlId',
		sortOrder : 'desc',
		ajaxOptions: {async:true,timeout:5000000},
		showColumns : true,
		showExport : true,
		striped : true,
		pagination : true,
		pageNumber : 1,
		pageSize : 10,
		pageList : "[10,20,30,All]",
		search : false,
		sidePagination : 'server',//服务端分页  client //客户端分页
		idField : 'tlId',
		uniqueId : 'tlId',
		responseHandler : responseHandler, //处理分页函数
		queryParams : queryParamstrades,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'merchantId',
			title : '收益人商户号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merName',
			title : '收益人姓名',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'orderMerchantId',
			title : '交易人商户号',
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
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'level',
			title : '分润来源',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "oneMerchant"){
					return '直推补贴';
				}
				if(value == "twoMerchant"){
					return '间推补贴';
				}
				if(value == "threeMerchant"){
					return '间间推补贴';
				}
				if(value == "oneAgent"){
					return '直接代理';
				}
				if(value == "twoAgent"){
					return '间接代理';
				}
				if(value == "countyAgent"){
					return '县级代理';
				}
				if(value == "cityAgent"){
					return '市级代理';
				}
				if(value == "provinceAgent"){
					return '省级代理';
				}
				if(value == "topAgent"){
					return '顶级代理';
				}
				if(value == "institution"){
					return '平台';
				}
				if(value == "user"){
					return '用户分润';
				} else {
					return '其他';
				}
			}
		}, {
			field : 'appId',
			title : 'app名称',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "0000"){
						return '帐期机器人';
					}
				
			}
		}, {
			field : 'module',
			title : '交易类型',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "alipay"){
					return '升级费';
				}
				if(value == "epos"){
					return '无卡';
				}
				if(value == "repayment"){
					return '还款';
				}
				if(value == "ws"){
					return '网申';
				} else {
					return "原始数据无从考证";
				}
			}
		}, {
			field : 'amount',
			title : '交易金额',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'createTime',
			title : '交易时间',
			align : 'center',
			valign : 'middle',
			formatter: function (value) {
        		return value.substring(0,19);
    		}
		}]
	});
    	$("#tradesExcel").click(function(){
    			$("#trades").tableExport({
    			    headings: true, 
    			    footers: true, 
    			    formats: "csv",
    			    fileName: "Excel还款分润查询表",
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