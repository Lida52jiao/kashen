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
			<form role="form" class="form-inline" id="userSearchPlanDetails">
				<div class="input-group">
					<input type="text" placeholder="商户号" name="merchantId" id="merchants"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<!-- <div class="input-group">
					<select name="state" id="state" class="input form-control">
	  					<option value ="">请计划状态</option>
	  					<option value ="1">草稿</option>
	  					<option value ="2">执行中</option>
	  					<option value ="3">已完成</option>
	  					<option value ="4">中断</option>
	  					<option value ="5">交易锁定</option>
	  					</select>
					<span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入本金占比" name="ratio" id="ratio"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入总天数" name="totalDay" id="totalDay"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入还款笔数" name="number" id="number"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="bankCode" id="bankCode" class="input form-control">
	  					<option value ="">请选择银行</option>
	  					<option value ="ICBC">工商银行</option>
	  					<option value ="ABC">农业银行</option>
	  					<option value ="BOC">中国银行</option>
	  					<option value ="CCB">建设银行</option>
	  					<option value ="CMBCHINA">招商银行</option>
	  					<option value ="POST">邮政储蓄</option>
	  					<option value ="ECITIC">中信银行</option>
	  					<option value ="CEB">光大银行</option>
	  					<option value ="BOCO">交通银行</option>
	  					<option value ="CIB">兴业银行</option>
	  					<option value ="CMBC">民生银行</option>
	  					<option value ="PINGAN">平安银行</option>
	  					<option value ="CGB">广发银行</option>
	  					<option value ="BCCB">北京银行</option>
	  					<option value ="HXB">华夏银行</option>
	  					<option value ="SPDB">浦发银行</option>
	  					<option value ="SHB">上海银行</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div> 
				<div class="input-group">
					<input type="text" placeholder="输入商户名" name="name" id="name"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入卡号" name="cardNo" id="cardNo"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入手机号" name="phone" id="phone"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div> -->
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchplanDetails();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="planDetails" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getUserIdSelectionsplanDetails() {
		return $.map($("#planDetails").bootstrapTable('getSelections'), function(
				row) {
			return row.orderNo
		});
	}
	function editRepayment() {
		var cbox = getUserIdSelectionsplanDetails();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		/* battcn.ajaxOpen({
			title : '信息变更',
			href : rootPath + '/Plan/lists.shtml?id=' + cbox,
			width : '100%',
			height : '100%',
			okhandler : function() {
				save();
			}
		}); */
		$.ajax({
				type: "POST", 
				url: rootPath + "/Plan/add.shtml?id=" + cbox,
				success: function(data){
					$('#planDetails').bootstrapTable('refresh');
					alert(data);
				}
			});
	} 
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchplanDetails() {
		$('#planDetails').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsplanDetails(params) {
		var merchantId = $("#merchants").val();
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
			merchantId : merchantId
		}
	} 
	$('#planDetails').bootstrapTable({
		url : rootPath + '/Plan/findPlanDetails.shtml',
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
		idField : 'orderNo',
		uniqueId : 'orderNo',
		responseHandler : responseHandler, //处理分页函数
		queryParams : queryParamsplanDetails,//参数处理函数
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
			title : '商户姓名',
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
			field : 'state',
			title : '发起状态',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
					if(value == "1"){
						return '待执行';
					}
					if(value == "2"){
						return '执行中';
					}
					if(value == "3"){
						return '成功';
					}
					if(value == "4"){
						return '失败';
					}
					if(value == "5"){
						return '交易锁定';
					}
					return '已完成';
				}
		}, {
			field : 'repaymentState',
			title : '结算状态',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
					if(value == "1"){
						return '待执行';
					}
					if(value == "2"){
						return '执行中';
					}
					if(value == "3"){
						return '成功';
					}
					if(value == "4"){
						return '失败';
					}
					if(value == "5"){
						return '交易锁定';
					}
					return '已完成';
				}
		}, {
			field : 'appId',
			title : '所属app',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "0001"){
					return '宜富';
				}
				if(value == "0002"){
					return '融易用';
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