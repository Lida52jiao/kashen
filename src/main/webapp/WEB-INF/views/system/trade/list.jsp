<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>js/tableexport.js"></script>
<div class="wrapper wrapper-content animated fadeInRight"
	style="height: 100%">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="doc-buttons">
				 <c:forEach items="${res}" var="key">
					 ${key.description}  
				</c:forEach> 
			</div>
			<form role="form" class="form-inline" id="userSearchTrade">
				<div class="input-group">
					<input type="text" placeholder="输入订单号" name="orderNo" id="orderNo"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入商户号" name="merchantId" id="merchantId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
					<input type="hidden" placeholder="输入商户姓名" name="name" id="name"
						class="input form-control">
				<div class="input-group">
					<input type="text" placeholder="输入手机号" name="phone" id="phone"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入代理商号" name="agentId" id="agentId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="计划单元Id" name="cycleId" id="cycleId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<!-- <div class="input-group">
					<select name="payType" id="payType" class="input form-control">
	  					<option value ="">请选择订单类型</option>
	  					<option value ="1">还款</option>
	  					<option value ="2">消费</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div> -->
				<div class="input-group">
					<select name="aisleCode" id="aisleCode" class="input form-control" >
						<option value ="">请选择通道  </option>
		                        <c:forEach items="${aisleCode}" var="key">
							 		<%--<c:if test="${key.aislecode=='ld01'}">
									<option value ="${key.aislecode}">落地通道L  </option>
									</c:if>
									<c:if test="${key.aislecode=='ld02'}">
										<option value ="${key.aislecode}">落地通道Y  </option>
									</c:if>--%>
									<c:if test="${key.aislecode=='ld03'}">
									<option value ="${key.aislecode}">落地通道X  </option>
									</c:if>
									<c:if test="${key.aislecode=='ld04'}">
									<option value ="${key.aislecode}">落地还款X  </option>
									</c:if>
									<c:if test="${key.aislecode=='ld05'}">
									<option value ="${key.aislecode}">落地还款T  </option>
                                    </c:if>
                                    <c:if test="${key.aislecode=='ld06'}">
									<option value ="${key.aislecode}">大额还款K </option>
									</c:if>
									<c:if test="${key.aislecode=='ld07'}">
										<option value ="${key.aislecode}">落地大额H </option>
									</c:if>
                                    <c:if test="${key.aislecode=='ld09'}">
                                        <option value ="${key.aislecode}">落地还款C  </option>
                                    </c:if>
									<c:if test="${key.aislecode=='ld11'}">
										<option value ="${key.aislecode}">落地还款NT  </option>
									</c:if>
									<c:if test="${key.aislecode=='ld12'}">
										<option value ="${key.aislecode}">落地大额C  </option>
									</c:if>
									<c:if test="${key.aislecode=='ld14'}">
										<option value ="${key.aislecode}">小额落地HB </option>
									</c:if>
									<c:if test="${key.aislecode=='ld15'}">
										<option value ="${key.aislecode}">落地大额C2 </option>
									</c:if>
									<%--<c:if test="${key.aislecode=='ld16'}">
										<option value ="${key.aislecode}">小额落地C2 </option>
									</c:if>
									<c:if test="${key.aislecode=='ld13'}">
										<option value ="${key.aislecode}">落地小额D </option>
									</c:if>--%>
									<c:if test="${key.aislecode=='ld17'}">
										<option value ="${key.aislecode}">组合计划T </option>
									</c:if>
								</c:forEach>
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
					<select name="payType" id="payType" class="input form-control" >
						<option value ="">请选择消费类型  </option>
							 <option value ="1">还款订单  </option>
							 <option value ="2">消费订单  </option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入计划编号" name="planId" id="planId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
			<div class="form-group">
				<div class="col-sm-8">
					<input type="text" placeholder="计划执行时间" name="executestartTime" id="executestart" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
					<span class="help-block m-b-none"></span>
				</div>
			</div>
			<br>
			<div class="input-group">
					<select name="state" id="states" class="input form-control">
	  					<option value ="">请选择发起状态</option>
	  					<option value ="1">发起待执行</option>
	  					<option value ="2">发起执行中</option>
	  					<option value ="4">发起中断</option>
	  					<option value ="5">发起交易中</option>
	  					<option value ="6">发起完成</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div> 
				<div class="input-group">
					<select name="payState" id="paystate" class="input form-control">
	  					<option value ="">请选择支付状态</option>
	  					<option value ="1">支付待执行</option>
	  					<option value ="2">支付执行中</option>
	  					<option value ="3">支付成功</option>
	  					<option value ="4">支付失败</option>
	  					<option value ="5">支付交易中</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div> 
				<div class="input-group">
					<select name="repaymentState" id="repaymentState" class="input form-control">
	  					<option value ="">请选还款状态</option>
	  					<option value ="1">还款待执行</option>
	  					<option value ="2">还款执行中</option>
	  					<option value ="3">还款成功</option>
	  					<option value ="4">还款失败</option>
	  					<option value ="5">还款交易中</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入组合计划编号" name="groupId" id="groupId"
						   class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="isAnew" id="isAnew" class="input form-control">
						<option value ="">请选择补单状态</option>
						<option value ="0">否</option>
						<option value ="1">是</option>
					</select>
					<span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchtrade();">
							<i class="fa fa-search"></i> 搜索
						</button>
				</div>
				<%--<div class="input-group">
					<button type="button" class="btn btn btn-primary"
							onclick="javascript:tradeExcel();">
						<i class="fa fa-search"></i> 导出
					</button>
				</div>--%>
				<div class="input-group">
					<input class="btn btn btn-primary" id="tradeExcel" type="button" value="导出">
				</div>
			</form>
			<div class="table-responsive">
				<table id="trade" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
    $("#tradeExcel").click(function(){
        $("#trade").tableExport({
            headings: true,
            footers: true,
            formats: "csv",
            fileName: "消费还款",
            bootstrap: false,
            position: "bottom",
            ignoreRows: null,
            ignoreCols: null,
            ignoreColumn: [0]
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
function getPlanOrderNo() {
	return $.map($("#trade").bootstrapTable('getSelections'), function(
			row) {
		return row.orderNo
	});
}
function getPlanId() {
	return $.map($("#trade").bootstrapTable('getSelections'), function(
			row) {
		return row.planId
	});
}
function getMerchantId() {
	return $.map($("#trade").bootstrapTable('getSelections'), function(
			row) {
		return row.merchantId
	});
}
function getAisleCode() {
	return $.map($("#trade").bootstrapTable('getSelections'), function(
			row) {
		return row.aisleCode
	});
}
function getRepayState() {
	return $.map($("#trade").bootstrapTable('getSelections'), function(
			row) {
		return row.repaymentState
	});
}
function getState() {
	return $.map($("#trade").bootstrapTable('getSelections'), function(
			row) {
		return row.state
	});
}
    function getPayType() {
        return $.map($("#trade").bootstrapTable('getSelections'), function(
            row) {
            return row.payType
        });
    }
    function getPlanType() {
        return $.map($("#trade").bootstrapTable('getSelections'), function(
            row) {
            return row.planType
        });
    }
    function getAnewOrderNo() {
        return $.map($("#trade").bootstrapTable('getSelections'), function(
            row) {
            return row.anewOrderNo
        });
    }
    function getRepaymentState() {
        return $.map($("#trade").bootstrapTable('getSelections'), function(
            row) {
            return row.repaymentState
        });
    }
    function getGroupId() {
        return $.map($("#trade").bootstrapTable('getSelections'), function(
            row) {
            return row.groupId
        });
    }
    function getCardNo() {
        return $.map($("#trade").bootstrapTable('getSelections'), function(
            row) {
            return row.cardNo
        });
    }
    function getAppId() {
        return $.map($("#trade").bootstrapTable('getSelections'), function(
            row) {
            return row.appId
        });
    }
    function getCycleId() {
        return $.map($("#trade").bootstrapTable('getSelections'), function(
            row) {
            return row.cycleId
        });
    }
    function updateUnit() {
        var cbox = getPlanOrderNo();
        var payType = getPayType();
        var planType = getPlanType();
        var anewOrderNo = getAnewOrderNo();
        var state = getState();
        var repaymentState = getRepaymentState();
        var merchantId = getMerchantId();
        var cycleId = getCycleId();
        var cardNo = getCardNo();
        var appId = getAppId();
        console.info("cbox:"+cbox);
        if (cbox == "") {
            layer.msg("请选择数据！");
            return;
        }
        if (cbox.length > 1) {
            layer.msg("请选择一条数据！");
            return;
        }
        if (planType != "multi_middle") {
            layer.msg("请选择组合计划！");
            return;
        }
        if (payType != "1") {
            layer.msg("请选择还款订单！");
            return;
        }
        if (anewOrderNo == null) {
            layer.msg("请选择未进行补单订单！");
            return;
        }
        if (state != "6" && repaymentState != "4") {
            layer.msg("请选择还款失败订单！");
            return;
        }
        battcn.ajaxOpen({
            title : '组合计划补单元',
            href : rootPath + '/Trade/repairUnit.shtml?cycleId='+cycleId+'&orderNo='+cbox+'&merchantId='+merchantId+'&cardNo='+cardNo+'&appId='+appId,
            width : '40%',
            height : '80%',
            okhandler : function() {
                save();
            }
        });
    }
 function updatePlanState() {
 	var cbox = getPlanOrderNo();
 	var aisleCode = getAisleCode();
	if (cbox == "") {
		layer.msg("请选择补状态订单！！");
		return;
	}
	if (cbox.length > 1) {
		layer.msg("只能选中一个");
		return;
	}
	$.ajax({
		type: "POST", 
		url: rootPath + "/Trade/checkOrUpdatePlanState.shtml?orderNo="+cbox+"&type=1&aisleCode="+aisleCode,
		//解决编码问题
		contentType: "application/x-www-form-urlencoded; charset=utf-8",  
		success: function(data){
			if(data){
				layer.confirm(data, function(index) {
					battcn.closeWindow();
					$('#trade').bootstrapTable('refresh');
		        	return false;
					});
			}
			battcn.toastrsAlert({
       		     code: data.success ? 'success' :'error',
       		     msg: data.success ? '成功' :'失败'
       		});
		}
	});
} 
 //补还款 只有发起状态为6的、还款状态为4的才可不还款
 function repayUpdate() {
	 	var cbox = getPlanOrderNo();
	 	var aisleCode = getAisleCode();
	 	var repayState = getRepayState();//还款状态4
	 	var state = getState();//发起状态6
		if (cbox == "") {
			layer.msg("请选择补状态订单！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		if(repayState!="4"&&state!="6"){
			layer.msg("此计划不能补还款,点击补状态后进行补还款操作");
			return;
		}
		$.ajax({
			type: "POST",
			url:"http://47.104.161.254:1003/"+aisleCode+"/order/anewRepayment2?pwd=yjkj123&orderNo="+cbox,
			//url: rootPath + "/Trade/checkOrUpdatePlanState.shtml?orderNo="+cbox+"&type=1&aisleCode="+aisleCode,
			//解决编码问题
			contentType: "application/x-www-form-urlencoded; charset=utf-8",  
			success: function(data){
				if(data){
					layer.confirm(data.respDesc, function(index) {
						battcn.closeWindow();
						$('#trade').bootstrapTable('refresh');
			        	return false;
						});
				}
				battcn.toastrsAlert({
	       		     code: data.success ? 'success' :'error',
	       		     msg: data.success ? '成功' :'失败'
	       		});
			}
		});
	} 
 //补消费，选中一个调第一个接口，选中两单，调第二个几口
 function consumeUpdate() {
	 	var cbox = getPlanOrderNo();
	 	var aisleCode = getAisleCode();
	 	var planId = getPlanId();
	 	var merchantId = getMerchantId();
	 	var aisle = aisleCode[0];
	 	var cboxo = cbox[0];
	 	var cboxt = cbox[1];
		var data = new Date();
	 	var hours = data.getHours();
		if (hours > 20 || hours < 9) {
			layer.msg("您好，晚上九点后，早上九点前不允许补消费！");
			return;
		}
		if (cbox == "") {
			layer.msg("请选择补消费的订单！！");
			return;
		}
		if (cbox.length >2) {
			layer.msg("补消费订单最多可同时补两笔！！");
			return;
		}
		if (cbox.length == 1) {
			$.ajax({
				type: "POST", 
				url:"http://47.104.161.254:1003/"+aisleCode+"/order/anewPay?pwd=yjkj123&orderNo="+cbox,
				//url: rootPath + "/Trade/consumeUpdatePlanState.shtml?orderNoStr="+cbox+"&planId="+planId+"&merchantId="+merchantId+"&aisleCode="+aisleCode,
				//解决编码问题
				contentType: "application/x-www-form-urlencoded; charset=utf-8",  
				success: function(data){
					if(data) {
						layer.confirm(data.respDesc, function(index) {
							battcn.closeWindow();
							$('#trade').bootstrapTable('refresh');
				        	return false;
							});
					} 
					battcn.toastrsAlert({
		       		     code: data.success ? 'success' :'error',
		       		     msg: data.success ? '成功' :'失败'
		       		});
				}
			});
		}
		if (cbox.length ==2) {
			$.ajax({
				type: "POST", 
				url:"http://47.104.161.254:1003/"+aisle+"/order/anewPay2?pwd=yjkj123&orderNo="+cboxo+"&orderNo2="+cboxt,
				//url: rootPath + "/Trade/consumeUpdatePlanState.shtml?orderNoStr="+cbox+"&planId="+planId+"&merchantId="+merchantId+"&aisleCode="+aisleCode,
				//解决编码问题
				contentType: "application/x-www-form-urlencoded; charset=utf-8",  
				success: function(data){
					if(data) {
						layer.confirm(data.respDesc, function(index) {
							battcn.closeWindow();
							$('#trade').bootstrapTable('refresh');
				        	return false;
							});
					} 
					battcn.toastrsAlert({
		       		     code: data.success ? 'success' :'error',
		       		     msg: data.success ? '成功' :'失败'
		       		});
				}
			});
			
		}
	} 

	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchtrade() {
		$('#trade').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamstrade(params) {
		var planId = $("#planId").val();
		var isLd = $("#isLd").val();
	 	var orderNo = $("#orderNo").val();
		var merchantId = $("#merchantId").val();
		var name = $("#name").val();
		var phone = $("#phone").val();
		var agentId = $("#agentId").val();
		var cycleId = $("#cycleId").val();
		var state = $("#states").val();
		var payState = $("#paystate").val();
		var repaymentState = $("#repaymentState").val();
		var executestartTime = $("#executestart").val();
		var appId = $("#appId").val();
		var aisleCode = $("#aisleCode").val();
		var payType = $("#payType").val();
		var groupId = $("#groupId").val();
		var isAnew = $("#isAnew").val();
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
			merchantId : merchantId,
			name : name,
			phone : phone,
			agentId : agentId,
            cycleId : cycleId,
			state : state,
			payState : payState,
			repaymentState : repaymentState,
			executestartTime : executestartTime,
			appId : appId,
			isLd : isLd,
			planId : planId,
			aisleCode : aisleCode,
			payType : payType,
            groupId : groupId,
            isAnew : isAnew
		}
	} 
	$('#trade').bootstrapTable({
		url : rootPath + '/Trade/getList.shtml',
		height : '100%',
		sortName : 'executeTime',
		sortOrder : 'desc',
		ajaxOptions: {async:true,timeout:500000},
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
		queryParams : queryParamstrade,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'merchantId',
			title : '商户号',
			align : 'center',
			valign : 'middle'
		},{
			field : 'isLd',
			title : '消费类型',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "2"){
					return '线上消费';
				}
				if(value == "1"){
					return '落地消费';
				}
			}
		},{
			field : 'aisleCode',
			title : '通道标识',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				/*if(value == "ld01"){
					return '落地通道L';
				}
				if(value == "ld02"){
					return '落地通道Y';
				}*/
				if(value == "ld03"){
					return "落地通道X";
				}
				if(value == "ld04"){
					return "落地还款X";
				}
				if(value == "ld05"){
					return "落地还款T";
				}
				if(value == "ld06"){
					return "大额还款K";
				}
				if(value == "ld07"){
					return "落地大额H";
				}
                if(value == "ld09"){
                    return "落地还款C";
                }
                if(value == "ld11"){
                    return "落地还款NT";
                }
                if(value == "ld12"){
                    return "落地大额C";
                }
                if(value == "ld14"){
                    return "小额落地HB";
                }
                if(value == "ld15"){
                    return "落地大额C2";
                }
                /*if(value == "ld16"){
                    return "小额落地C2";
                }
                if(value == "ld13"){
                    return "落地小额D";
                }*/
                if(value == "ld17"){
                    return "组合计划T";
                }
			}
		}, {
			field : 'name',
			title : '商户姓名',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'province',
			title : '省份名称',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'city',
			title : '城市名称',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'phone',
			title : '商户手机号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'bankCode',
			title : '发卡行',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
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
			field : 'cardNo',
			title : '卡号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'agentId',
			title : '代理商编号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'payType',
			title : '类型',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
					if(value == "1"){
						return '还款';
					}
					if(value == "2"){
						return '消费';
					}
				}
		}, {
			field : 'state',
			title : '发起状态',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "1"){
					return '发起待执行';
				}
				if(value == "2"){
					return '发起执行中';
				}
				if(value == "4"){
					return '发起中断';
				}
				if(value == "5"){
					return '发起交易中';
				}
				if(value == "6"){
					return '发起完成';
				}
			}
		}
		, {
			field : 'payState',
			title : '支付状态',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "1"){
					return '支付待执行';
				}
				if(value == "2"){
					return '支付执行中';
				}
				if(value == "3"){
					return '支付成功';
				}
				if(value == "4"){
					return '支付失败';
				}
				if(value == "5"){
					return '支付交易中';
				}
			}
		}, {
			field : 'repaymentState',
			title : '还款状态',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "1"){
					return '还款待执行';
				}
				if(value == "2"){
					return '还款执行中';
				}
				if(value == "3"){
					return '还款成功';
				}
				if(value == "4"){
					return '还款失败';
				}
				if(value == "5"){
					return '还款交易中';
				}
			}
			
		}, {
			field : 'cause',
			title : '情况描述',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'amount',
			title : '交易金额',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'arrivalAmount',
			title : '到达金额',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'fee',
			title : '手续费',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'orderNo',
			title : '订单号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'anewOrderNo',
			title : '订单号（补）',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == null){
					return "未进行过补单";
				} else {
					return value;
				}
			}
		}, {
			field : 'planId',
			title : '计划编号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'groupId',
			title : '组合计划编号',
			align : 'center',
			valign : 'middle'
        }, {
			field : 'planType',
			title : '订单类型',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "multi_firstAndLast"){
					return "预留金计划";
				}
				if(value == "multi_middle"){
					return "组合子计划";
				}
				if(value == "multi_clear"){
					return "清算订单";
				}
			}
		},{
		    field : 'isAnew',
			title : '是否补单',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if (value == "1") {
				    return "是";
				}
				if (value == "0") {
				    return "否";
				}
            }
        },{
			field : 'cycleId',
			title : '计划单元Id',
			align : 'center',
			valign : 'middle'
		},{
			field : 'appId',
			title : '所属app',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "0000"){
					return "帐期机器人";
				}
			}
		}, {
			field : 'createTime',
			title : '创建时间',
			align : 'center',
			valign : 'middle',
			formatter: function (value) {
        		return value.substring(0,19);
    		}
		}, {
			field : 'executeTime',
			title : '执行开始时间',
			align : 'center',
			valign : 'middle',
			sortable: true,
			formatter: function (value, row, index) {
        		return changeDateFormat(value);
    		}
		}, {
			field : 'finishTime',
			title : '执行结束时间',
			align : 'center',
			valign : 'middle',
			sortable: true,
			formatter: function (value, row, index) {
        		return changeDateFormat(value);
    		}
		}, {
			field : 'remarks',
			title : '备注',
			align : 'center',
			valign : 'middle'
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
    //消费还款导出
/*function tradeExcel(){
    var planId = $("#planId").val();
    var orderNo = $("#orderNo").val();
    var merchantId = $("#merchantId").val();
    var name = $("#name").val();
    var phone = $("#phone").val();
    var agentId = $("#agentId").val();
    var cycleId = $("#cycleId").val();
    var state = $("#states").val();
    var payState = $("#paystate").val();
    var repaymentState = $("#repaymentState").val();
    var executestartTime = $("#executestart").val();
    var executefinishTime = $("#executefinish").val();
    var appId = $("#appId").val();
    var aisleCode = $("#aisleCode").val();
    var payType = $("#payType").val();
	window.location.href=rootPath + '/excel/tradeOut.shtml?orderNo='+orderNo+'&planId='+planId+'&merchantId='+merchantId+'&name='+name+'&phone='+phone+'&agentId='+agentId+'&state='+state+'&payState='+payState+'&repaymentState='+repaymentState+'&appId='+appId+'&aisleCode='+aisleCode+'&payType='+payType+'&executestartTime='+executestartTime+'&executefinishTime='+executefinishTime+'&cycleId='+cycleId;
    // orderNo, merchantId, name, phone, agentId, state, payState, repaymentState, executestartTime, executefinishTime, appId, planId, isLd, aisleCode, payType

}*/


</script>
