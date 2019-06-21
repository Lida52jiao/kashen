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
			<form role="form" class="form-inline" id="userSearchFormWithdrawEntityWuka">
				<div class="input-group">
					<input type="text" placeholder="输入商户号" name="merchantId" id="WukamerchantId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入代理商号" name="agentId" id="WukaagentId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入结算单号" name="orderNo" id="WukaorderNo"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="state" id="Wukastate" class="input form-control">
	  					<option value ="">请计划状态</option>
	  					<option value ="1">未审核</option>
	  					<option value ="2">初审</option>
	  					<option value ="3">复核</option>
	  					<option value ="4">已完成</option>
	  					<option value ="5">未通过</option>
	  					</select>
					<span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入商户名称" name="merchantName" id="WukamerchantName"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入结算卡号" name="cardNo" id="WukacardNo"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="bankCode" id="WukabankCode" class="input form-control">
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
				<div class="form-group">
				<div class="col-sm-8">
					<input type="text" placeholder="开始时间" name="starttime" id="Wukastarttime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-8">
					<input type="text" placeholder="结束时间" name="finishtime" id="Wukafinishtime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:withdrawEntitySearchWuka();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="WukawithdrawEntity" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getUserIdSelectionsWithdrawEntityWuka() {
		return $.map($("#WukawithdrawEntity").bootstrapTable('getSelections'), function(
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
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function withdrawEntitySearchWuka() {
		$('#WukawithdrawEntity').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamswithdrawEntityWuka(params) {
		var merchantId = $("#WukamerchantId").val();
		var agentId = $("#WukaagentId").val();
		var orderNo = $("#WukaorderNo").val();
		var state = $("#Wukastate").val();
		var merchantName = $("#WukamerchantName").val();
		var cardNo = $("#WukacardNo").val();
		var bankCode = $("#WukabankCode").val();
		var starttime = $("#Wukastarttime").val();
		var finishtime = $("#Wukafinishtime").val();
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
			orderNo : orderNo,
			state : state,
			merchantName : merchantName,
			cardNo : cardNo,
			bankCode : bankCode,
			starttime : starttime,
			finishtime : finishtime
		}
	} 
	$('#WukawithdrawEntity').bootstrapTable({
		url : rootPath + '/Wuka/getWithdrawList.shtml',
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
		queryParams : queryParamswithdrawEntityWuka,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'merchantId',
			title : '商户号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merchantName',
			title : '商户姓名',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merchantPhone',
			title : '商户手机号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'orderNo',
			title : '结算单号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'state',
			title : '状态',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
					if(value == "1"){
						return '未审核';
					}
					if(value == "2"){
						return '初审';
					}
					if(value == "3"){
						return '复审';
					}
					if(value == "4"){
						return '已完成';
					}
					if(value == "5"){
						return '未通过';
					}
				}
		}, {
			field : 'amount',
			title : '金额',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'arrivalAmount',
			title : '到账金额',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'profitFee',
			title : '手续费',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'agentId',
			title : '代理商编号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'appName',
			title : '所属app',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'bankCode',
			title : '银行',
			align : 'center',
			valign : 'middle',
			formatter:function bankFormatter(value) {
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
			field : 'firstTime',
			title : '初审时间',
			align : 'center',
			valign : 'middle',
			formatter: function (value, row, index) {
        		return changeDateFormat(value);
    		}
		}, {
			field : 'firstName',
			title : '初审人',
			align : 'center',
			valign : 'middle'
		}
		, {
			field : 'secondTime',
			title : '复核时间',
			align : 'center',
			valign : 'middle',
			formatter: function (value, row, index) {
        		return changeDateFormat(value);
    		}
		}, {
			field : 'secondName',
			title : '复核人',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'createTime',
			title : '提交时间',
			align : 'center',
			valign : 'middle',
			formatter: function (value) {
        		return value.substring(0,19);
    		}
		}, {
			field : 'remarks',
			title : '拒绝原因',
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