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
			<form role="form" class="form-inline" id="userSearchAgentOrders">
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
					<input type="text" placeholder="输入订单号" name="agentOrderNo" id="agentOrderNo"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="state" id="states" class="input form-control">
					<option value ="">请选择状态</option>
	  					<option value ="1">已下单</option>
	  					<option value ="2">执行中</option>
	  					<option value ="3">支付成功</option>
	  					<option value ="4">支付失败</option>
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
				</div>
				<div class="input-group">
					<select name="aisleCode" id="aisleCode" class="input form-control" >
							<option value ="">请选择通道  </option>
			                 <c:forEach items="${key}" var="key">
			                 	<c:if test="${key.aislecode=='rf'}">
									<option value ="${key.aislecode}">银联快捷R  </option>
								</c:if>
								<c:if test="${key.aislecode=='zf01'}">
									<option value ="${key.aislecode}">银联快捷Z  </option>
								</c:if>
								<c:if test="${key.aislecode=='easy'}">
									<option value ="${key.aislecode}">易生  </option>
								</c:if>
								<c:if test="${key.aislecode=='yb01'}">
									<option value ="${key.aislecode}">银联快捷Y  </option>
								</c:if>
								<c:if test="${key.aislecode=='xj'}">
									<option value ="${key.aislecode}">银联快捷X  </option>
								</c:if>
								<c:if test="${key.aislecode=='hz'}">
									<option value ="${key.aislecode}">银联快捷H  </option>
								</c:if>
								<c:if test="${key.aislecode=='xjwk'}">
									<option value ="${key.aislecode}">大额落地X  </option>
								</c:if>
								<c:if test="${key.aislecode=='cj'}">
									<option value ="${key.aislecode}">大额快捷C  </option>
								</c:if>
								<c:if test="${key.aislecode=='kft'}">
									<option value ="${key.aislecode}">大额快捷K  </option>
								</c:if>
								<c:if test="${key.aislecode=='ybq'}">
									<option value ="${key.aislecode}">大额快捷M  </option>
								</c:if>
								<c:if test="${key.aislecode=='ybc'}">
									<option value ="${key.aislecode}">云闪付M(大额)  </option>
								</c:if>
								<c:if test="${key.aislecode=='ybcs'}">
									<option value ="${key.aislecode}">云闪付M(小额)  </option>
								</c:if>
							</c:forEach>
		  			</select>
				</div>
				<br>
				<div class="form-group">
				<div class="col-sm-8">
					<input type="text" placeholder="开始时间" name="starttime" id="starts" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-8">
					<input type="text" placeholder="结束时间" name="finishtime" id="finishs" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchagentOrders();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
				<div class="input-group">
							<input class="btn btn btn-primary"  id="agentOrdersExcel" type="button" value="导出">
					</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="agentOrders" data-toolbar="#toolbar"
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
	function agentSearchagentOrders() {
		$('#agentOrders').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsagentOrders(params) {
	 	var merChantId = $("#merChantId").val();
		var merName = $("#merName").val();
		var agentOrderNo = $("#agentOrderNo").val();
		var states = $("#states").val();
		var starttime = $("#starts").val();
		var finishtime = $("#finishs").val();
		var aisleCode = $("#aisleCode").val();
		var appId = $("#appId").val();
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
			agentOrderNo : agentOrderNo,
			state : states,
			starttime : starttime,
			finishtime : finishtime,
			appId : appId,
			aisleCode : aisleCode
		}
	} 
	$('#agentOrders').bootstrapTable({
		url : rootPath + '/Trade/selectAgentOrders.shtml',
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
		queryParams : queryParamsagentOrders,//参数处理函数
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
			title : '姓名',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'phone',
			title : '手机号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'agentId',
			title : '代理商号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'orderNo',
			title : '订单号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'amount',
			title : '交易金额',
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
			field : 'd0Fee',
			title : '笔数费',
			align : 'center',
			valign : 'middle',
			formatter:function totalFeeFormatter(value){
				return value/100;
			}
		}, {
			field : 'arrivalAmount',
			title : '到达金额',
			align : 'center',
			valign : 'middle',
			formatter:function totalFeeFormatter(value){
				return value/100;
			}
		},{
			field : 'aisleCode',
			title : '交易通道',
			align : 'center',
			valign : 'middle',
			formatter:function merStatFormatter(value) {
				if(value == "rf"){
					return "银联快捷R";
				}
				if(value == "yb"){
					return "快捷多商户";
				}
				if(value == "easy"){
					return "易生";
				}
				if(value == "zf01"){
					return "银联快捷Z";
				}
				if(value == "yb01"){
					return "银联快捷Y";
				}
				if(value == "xj"){
					return "银联快捷X";
				}
				if(value == "hz"){
					return "银联快捷H";
				}
				if(value == "xjwk"){
					return "大额落地X";
				}
				if(value == "cj"){
					return "大额快捷C";
				}
				if(value == "kft"){
					return "大额快捷K";
				}
				if(value == "ybq"){
					return "大额快捷M";
				}
				if(value == "ybc"){
					return "云闪付M(大额)";
				}
				if(value == "ybcs"){
					return "云闪付M(小额)";
				}
			}
		},{
			field : 'appId',
			title : '归属App',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "0000"){
					return '帐期机器人';
				}
			}
		},{
			field : 'state',
			title : '交易状态',
			align : 'center',
			valign : 'middle',
			formatter:function stateFormatter(value) {
				if(value == "1"){
					return '已下单';
				}
				if(value == "2"){
					return '执行中';
				}
				if(value == "3"){
					return '支付成功';
				}
				if(value == "4"){
					return '支付失败';
				}
			}
		}, {
			field : 'creditCardCode',
			title : '信用卡',
			align : 'center',
			valign : 'middle',
			formatter:function bankCodeFormatter(value) {
					if(value == "ICBC"){
						return '工商银行';
					}
					if(value == "ABC"){
						return '农业银行';
					}
					if(value == "BOC"){
						return '中国银行';
					}
					if(value == "CCB"){
						return '建设银行';
					}
					if(value == "CMBCHINA"){
						return '招商银行';
					}
					if(value == "POST"){
						return '邮政储蓄';
					}
					if(value == "ECITIC"){
						return '中信银行';
					}
					if(value == "CEB"){
						return '光大银行';
					}
					if(value == "BOCO"){
						return '交通银行';
					}
					if(value == "CIB"){
						return '兴业银行';
					}
					if(value == "CMBC"){
						return '民生银行';
					}
					if(value == "PINGAN"){
						return '平安银行';
					}
					if(value == "CGB"){
						return '广发银行';
					}
					if(value == "BCCB"){
						return '北京银行';
					}
					if(value == "HXB"){
						return '华夏银行';
					}
					if(value == "SPDB"){
						return '浦发银行';
					}
					if(value == "SHB"){
						return '上海银行';
					}
				}
		}, {
			field : 'creditCardNumber',
			title : '信用卡卡号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'issuingBankCode',
			title : '结算卡',
			align : 'center',
			valign : 'middle',
			formatter:function bankCodeFormatter(value) {
					if(value == "ICBC"){
						return '工商银行';
					}
					if(value == "ABC"){
						return '农业银行';
					}
					if(value == "BOC"){
						return '中国银行';
					}
					if(value == "CCB"){
						return '建设银行';
					}
					if(value == "CMBCHINA"){
						return '招商银行';
					}
					if(value == "POST"){
						return '邮政储蓄';
					}
					if(value == "ECITIC"){
						return '中信银行';
					}
					if(value == "CEB"){
						return '光大银行';
					}
					if(value == "BOCO"){
						return '交通银行';
					}
					if(value == "CIB"){
						return '兴业银行';
					}
					if(value == "CMBC"){
						return '民生银行';
					}
					if(value == "PINGAN"){
						return '平安银行';
					}
					if(value == "CGB"){
						return '广发银行';
					}
					if(value == "BCCB"){
						return '北京银行';
					}
					if(value == "HXB"){
						return '华夏银行';
					}
					if(value == "SPDB"){
						return '浦发银行';
					}
					if(value == "SHB"){
						return '上海银行';
					}
				}
		}, {
			field : 'cardNumber',
			title : '结算卡号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'createTime',
			title : '交易时间',
			align : 'center',
			valign : 'middle',
		}]
	});
	 //转换日期格式(时间戳转换为datetime格式)
    	function changeDateFormat(cellval) {
        var dateVal = cellval + "";
        if (cellval != null) {
            var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            
            var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
            
            return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
        }
    }
        	$("#agentOrdersExcel").click(function(){
        			$("#agentOrders").tableExport({
        			    headings: true, 
        			    footers: true, 
        			    formats: "csv",
        			    fileName: "Excel无卡交易查询表",
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