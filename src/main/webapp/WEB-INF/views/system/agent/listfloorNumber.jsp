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
				<form role="form" class="form-inline" id="floorNumber">
				 <div class="input-group">
					<input type="text" placeholder="输入代理商号" name="merId" id="merId"
						class="input form-control"> <span class="input-group-btn">
					<input type="text" placeholder="输入代理姓名" name="merName" id="merName"
						class="input form-control"> <span class="input-group-btn">
					<input type="text" placeholder="输入代理手机号" name="merMp" id="merMp"
						class="input form-control"> <span class="input-group-btn">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:floorNumberSearch();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div> 
			</form>
			<div class="table-responsive">
				<table id="floorNumberTable" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getUserIdSelectionsfloorNumber() {
		return $.map($("#floorNumberTable").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	 function editFloorNumber() {
		var cbox = getUserIdSelectionsfloorNumber();
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
			href : rootPath + '/Transaction/edits.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	}  
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function floorNumberSearch() {
		$('#floorNumberTable').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsFloorNumber(params) {
		var merId = $("#merId").val();
		var merName = $("#merName").val();
		var merMp = $("#merMp").val();
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
			merId : merId,
			merName : merName,
			merMp : merMp
		}
	} 
	$('#floorNumberTable').bootstrapTable({
		url : rootPath + '/Transaction/getList.shtml',
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
		queryParams : queryParamsFloorNumber,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'merId',
			title : '代理商号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'agentName',
			title : '代理商名称',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merChantId',
			title : '商户号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'floorNumber',
			title : '还款底价',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'generationFee',
			title : '提现代发费',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'generationFeeRepayment',
			title : '还款代发费',
			align : 'center',
			valign : 'middle'
		}/* , {
			field : 'fee0',
			title : '有积分无卡费率费',
			align : 'center',
			valign : 'middle',
			formatter: function (value) {
                    return parseFloat((value/10)).toFixed(2);
                } 
		}, {
			field : 'd0fee',
			title : '有积分无卡提现费',
			align : 'center',
			valign : 'middle',
			formatter: function (value) {
                    return value/100;
                } 
		}, {
			field : 'fee1',
			title : '无积分无卡费率费',
			align : 'center',
			valign : 'middle',
			formatter: function (value) {
                    return parseFloat((value/10)).toFixed(2);
                } 
		}, {
			field : 'd1fee',
			title : '无积分无卡提现费',
			align : 'center',
			valign : 'middle',
			formatter: function (value) {
                    return value/100;
                } 
		}, {
			field : 'creatDate',
			title : '设置的时间',
			align : 'center',
			valign : 'middle',
			sortable: true,
            //——修改——获取日期列的值进行转换
             formatter: function (value, row, index) {
                    return changeDateFormat(value);
                } 
		} */
		]
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