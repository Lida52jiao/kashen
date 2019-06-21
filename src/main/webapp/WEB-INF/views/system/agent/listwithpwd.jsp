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
			<form role="form" class="form-inline" id="userSearchFormpwd">
				<div class="input-group">
					<input type="text" placeholder="输入代理商号" name="merId" id="merId"
						class="input form-control"> <span class="input-group-btn">
					<input type="text" placeholder="输入代理姓名" name="merName" id="merName"
						class="input form-control"> <span class="input-group-btn">
					<input type="text" placeholder="输入代理手机号" name="merMp" id="merMp"
						class="input form-control"> <span class="input-group-btn">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchpwd();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="agentwithpwd" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getUserIdSelectionspwd() {
		return $.map($("#agentwithpwd").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function editAgentpwd() {
		var cbox = getUserIdSelectionspwd();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		battcn.ajaxOpen({
			title : '密码重置',
			href : rootPath + '/Agent/editUIpwd.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	}
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchpwd() {
		$('#agentwithpwd').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamspwd(params) {
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
	$('#agentwithpwd').bootstrapTable({
		url : rootPath + '/Agent/getAgentList.shtml',
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
		queryParams : queryParamspwd,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'merId',
			title : '代理商号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merName',
			title : '名称',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merMp',
			title : '手机号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'accountNumber',
			title : '账号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'mailbox',
			title : '邮箱',
			align : 'center',
			valign : 'middle'
		}]
	});
</script>