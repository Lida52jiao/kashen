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
			<form role="form" class="form-inline" id="userSearchFormUsed">
				<!-- <div class="input-group">
					<input type="text" placeholder="输入商户号" name="merId" id="merId"
						class="input form-control"> <span class="input-group-btn">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearch();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div> -->
			</form>
			<div class="table-responsive">
				<table id="usedTable" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getUserIdSelectionsUsed() {
		return $.map($("#usedTable").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	 function editUseds() {
		var cbox = getUserIdSelectionsUsed();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		battcn.ajaxOpen({
			title : '编辑年月费',
			href : rootPath + '/Used/editUIalter.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	} 
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	/* function agentSearch() {
		$('#usedTable').bootstrapTable('refresh');
	} */
	//重写参数传递
	 function queryParamsUsed(params) {
		var pageSize = params.limit;
		var sort = params.sort;
		var offset = params.offset;
		var order = params.order;
		var pageNum = offset / pageSize + 1;
		return {
			pageSize : pageSize,
			pageNum : pageNum,
			sort : sort,
			order : order
		}
	} 
	$('#usedTable').bootstrapTable({
		url : rootPath + '/Used/getUsed.shtml',
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
		queryParams : queryParamsUsed,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'month',
			title : '激活码费',
			align : 'center',
			valign : 'middle'
		}]
	});
</script>