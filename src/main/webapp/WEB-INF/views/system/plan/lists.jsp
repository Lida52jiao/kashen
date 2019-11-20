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
			<form role="form" class="form-inline" id="planSearchTrade">
				<input type="hidden"  name="planId" id="planId" value="${planId}"/>
			</form>
			<div class="table-responsive">
				<table id="planDetail" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	var planId = $("#planId").val();
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function planSearchTrade() {
		$('#planDetail').bootstrapTable('refresh');
	}
	//重写参数传递
	 function planSearchTrade(params) {
	 	var orderNo = $("#orderNo").val();
		var merchantId = $("#merchantId").val();
		var name = $("#name").val();
		var phone = $("#phone").val();
		var agentId = $("#agentId").val();
		var state = $("#states").val();
		var payState = $("#paystate").val();
		var repaymentState = $("#repaymentstate").val();
		var executestartTime = $("#executestart").val();
		var executefinishTime = $("#executefinish").val();
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
			orderNo : orderNo,
			merchantId : merchantId,
			name : name,
			phone : phone,
			agentId : agentId,
			state : state,
			payState : payState,
			repaymentState : repaymentState,
			executestartTime : executestartTime,
			executefinishTime :executefinishTime,
			appId : appId
		}
	} 
	$('#planDetail').bootstrapTable({
		url : rootPath + '/Plan/findPlanDetail.shtml?pId='+planId,
		height : '100%',
		sortName : 'executeTime',
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
		queryParams : planSearchTrade,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
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
				if(value == "0"){
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
				if(value == "ld01"){
					return '落地通道L';
				}
				if(value == "ld02"){
					return '落地通道Y';
				}
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
                if(value == "ld16"){
                    return "小额落地C2";
                }
                if(value == "ld13"){
                    return "落地小额D";
                }
                if(value == "ld17"){
                    return "组合计划T";
                }
                if(value == "ybq"){
                    return "大额快捷M";
                }
			}
		}, {
			field : 'orderNo',
			title : '订单号',
			align : 'center',
			valign : 'middle'
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
			field : 'planId',
			title : '计划编号',
			align : 'center',
			valign : 'middle'
		}, {
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
    		  
</script>
