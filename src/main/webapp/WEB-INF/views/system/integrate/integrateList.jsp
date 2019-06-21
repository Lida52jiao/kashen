<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.sp {
		color: red;
	}
</style>
<div class="wrapper wrapper-content animated fadeInRight"
	style="height: 100%">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="doc-buttons">
				<c:forEach items="${res}" var="key">
					 ${key.description}  
				</c:forEach>
			</div>
			<form role="form" class="form-inline" id="integrate">
				 <div class="input-group">
					<input type="text" placeholder="输入商户号" name="merchantId" id="merchantId"
						class="input form-control"> <span class="input-group-btn"></span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入姓名" name="mername" id="mername"
						class="input form-control"> <span class="input-group-btn"></span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入手机号" name="phone" id="phone"
						class="input form-control"> <span class="input-group-btn"></span>
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
					<button type="button" class="btn btn btn-primary"
						onclick="javascript:integrateSearchForm();">
						<i class="fa fa-search"></i> 搜索
					</button>
				</div> 
			</form>
			<div class="table-responsive">
				<table id="integrateTable" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function integrateSearchForm() {
		$('#integrateTable').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryBaseParamsmer(params) {
		var mername = $("#mername").val();
		var merchantId = $("#merchantId").val();
		var phone = $("#phone").val();
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
			mername : mername,
			merchantId : merchantId,
			phone : phone,
			appId : appId
		}
	} 
	$('#integrateTable').bootstrapTable({
		url : rootPath + '/Integrage/getIntegrateList.shtml',
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
		queryParams : queryBaseParamsmer,//参数处理函数
		minimumCountColumns : 2,
		columns : [{
			field : 'point',
			title : '获得积分',
			align : 'center',
			valign : 'middle',
		}, {	
			field : 'number',
			title : '签到次数',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merchantId',
			title : '商户号',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'name',
			title : '商户姓名',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'phone',
			title : '商户电话',
			align : 'center',
			valign : 'middle',
		},  {
			field : 'createTime',
			title : '积分获得时间',
			align : 'center',
			valign : 'middle'
		}]
	});
</script>