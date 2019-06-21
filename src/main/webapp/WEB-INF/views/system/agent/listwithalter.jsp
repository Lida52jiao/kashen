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
			<form role="form" class="form-inline" id="userSearchFormalter">
				<div class="input-group">
					<input type="text" placeholder="输入代理商号" name="merId" id="merId"
						class="input form-control"> <span class="input-group-btn">
					<input type="text" placeholder="输入代理姓名" name="merName" id="merName"
						class="input form-control"> <span class="input-group-btn">
					<input type="text" placeholder="输入代理手机号" name="merMp" id="merMp"
						class="input form-control"> <span class="input-group-btn">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchalter();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="agentwithalter" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getUserIdSelectionsalter() {
		return $.map($("#agentwithalter").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function getMerId() {
		return $.map($("#agentwithalter").bootstrapTable('getSelections'), function(
				row) {
			return row.merId
		});
	}
	function getMerChantId() {
		return $.map($("#agentwithalter").bootstrapTable('getSelections'), function(
				row) {
			return row.merChantId
		});
	}
	function editAgentalter() {
		var cbox = getUserIdSelectionsalter();
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
			href : rootPath + '/Agent/editUIalter.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	}

	//代理商删除
	function deleteBindAgent() {
		var cbox = getUserIdSelectionsalter();
		var merId = getMerId();
		var merChantId = getMerChantId();
		if (cbox == "") {
			layer.msg("请选择删除项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		layer.confirm('是否删除？', function(index) {
			var url = rootPath + '/Agentdel/del.shtml?id=' + cbox + '&merId='+ merId +'&merChantId='+merChantId;
			var result = CommnUtil.ajax(url);
			if (result == "success") {
				$('#agentwithalter').bootstrapTable('refresh');
				layer.msg('删除成功');
			} else {
				layer.msg('删除失败');
			}
		});
	}

	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchalter() {
		$('#agentwithalter').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsalter(params) {
		var merId = $("#merId").val();
		var merName = $("#merName").val();
		var merMp = $("#merMp").val();
		 var merChantId = $("#merChantId").val();
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
			merChantId : merChantId,
			merMp : merMp
		}
	} 
	$('#agentwithalter').bootstrapTable({
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
		queryParams : queryParamsalter,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'merId',
			title : '代理商号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merChantId',
			title : '商户号',
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