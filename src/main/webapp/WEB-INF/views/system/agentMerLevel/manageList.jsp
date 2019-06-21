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
			<form role="form" class="form-inline" id="levelSearchForm">
			</form>
			<div class="table-responsive">
				<table id="agentMerLevelTable" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getUserIdSelections() {
		return $.map($("#agentMerLevelTable").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function editManageLevel() {
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
			title : '编辑用户等级管理',
			href : rootPath + '/agentMerLevel/editManageLevel.shtml?id=' + cbox,
			width : '40%',
			height : '90%',
			okhandler : function() {
				save();
			}
		});
	}
	function addManageLevel() {
		battcn.ajaxOpen({
			title : '编辑用户等级管理',
			href : rootPath + '/agentMerLevel/addManageLevel.shtml',
			width : '40%',
			height : '90%',
			okhandler : function() {
				save();
			}
		});
	}
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function levelSearch() {
		$('#agentMerLevelTable').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParams(params) {
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
	$('#agentMerLevelTable').bootstrapTable({
		url : rootPath + '/agentMerLevel/manageList.shtml',
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
		queryParams : queryParams,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'levelname',
			title : '级别名称',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'levellogo',
			title : '等级图标',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				 return "<a href="+value+" target='_blank'>查看</a>"
				
			}
		}, {
			field : 'levelbcard',
			title : '等级卡背',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				 return "<a href="+value+" target='_blank'>查看</a>"
			}
		}, {
			field : 'levelweight',
			title : '等级权重',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'agentornot',
			title : '是否为代理商',
			align : 'center',
			valign : 'middle',
			formatter: function(value){
				if(value == "Y"){
					return "是";
				}
				if(value == "N"){
					return "不是";
				}
                
            }
		}, {
			field : 'showornot',
			title : '是否显示',
			align : 'center',
			valign : 'middle',
			formatter: function(value){
				if(value == "Y"){
					return "显示";
				}
				if(value == "N"){
					return "不显示";
				}
                
            }
		}, {
			field : 'funcexplain',
			title : '权益说明',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'usablemodule',
			title : '可用功能',
			align : 'center',
			valign : 'middle'
		}]
	});
</script>