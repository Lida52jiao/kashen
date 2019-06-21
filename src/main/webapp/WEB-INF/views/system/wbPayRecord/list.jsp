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
			<form role="form" class="form-inline" id="wbRecordSearchForm">
				<div class="input-group">
					<input type="text" placeholder="输入订单号" name="orderid" id="orderid"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="status" id="status" class="input form-control">
	  					<option value ="">请选择交易状态</option>
	  					<option value ="Y">交易完成</option>
	  					<option value ="N">未进行交易</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div> 
				<div class="input-group">
					<input type="text" placeholder="输入代理商号" name="agentid" id="agentid"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div><br>
				<div class="input-group">
					<input type="text" placeholder="输入交易人商户号号" name="merchantid" id="merchantid"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入交易人手机号" name="mermp" id="mermp"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入交易人姓名" name="mername" id="mername"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:wbRecordSearchForm();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="wbRecordTable" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getBaseIdSelectionsmerCode() {
		return $.map($("#basePriceTable").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function wbRecordSearchForm() {
		$('#wbRecordTable').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryWBParamsmer(params) {
		var orderid = $("#orderid").val();
		var status = $("#status").val();
		var agentid = $("#agentid").val();
		var mername = $("#mername").val();
		var mermp = $("#mermp").val();
		var merchantid = $("#merchantid").val();
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
			orderid : orderid,
			status : status,
			agentid : agentid,
			merchantid : merchantid,
			mermp : mermp,
			mername : mername
		}
	} 
	$('#wbRecordTable').bootstrapTable({
		url : rootPath + '/WB/getList.shtml',
		height : '100%',
		sortName : 'createtime',
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
		queryParams : queryWBParamsmer,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'orderid',
			title : '订单号',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'mername',
			title : '交易人姓名',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'mermp',
			title : '交易人手机号',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'abbrname',
			title : '外币单位',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'orderprice',
			title : '订单金额',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'merchantid',
			title : '交易人商户号',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'agentid',
			title : '归属代理商编号',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'status',
			title : '交易状态',
			align : 'center',
			valign : 'middle',
			formatter:function statusFormatter(value) {
				if(value =="Y"){
					return '交易完成';
				}
				if(value == "N"){
					return '未进行交易';
				}
			}
		}, {
			field : 'createtime',
			title : '订单创建时间',
			align : 'center',
			valign : 'middle',
			//formatter : enabledFormatter
			sortable: true,
            //——修改——获取日期列的值进行转换
             formatter: function (value, row, index) {
                    return changeDateFormat(value);
                } 
		}, {
			field : 'finishtime',
			title : '订单完成时间',
			align : 'center',
			valign : 'middle',
			//formatter : enabledFormatter
			sortable: true,
            //——修改——获取日期列的值进行转换
             formatter: function (value, row, index) {
                    return changeDateFormat(value);
                } 
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