<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form role="form" class="form-inline" id="menuListForm">
			<div class="input-group">
				<c:forEach items="${menu}" var="key">
			 		<div class="col-sm-2">
			 			<input onclick="firstMenu()" class="col-sm-9 btn btn btn-primary" type="button" value="${key.name }">
			 			<input id="menuId" name="menuId" type="hidden" value="${key.id }">
			 			<input id="level" name="level" class="form-control" value="${key.level }">
			 		</div>
				</c:forEach>
			</div>
			<div class="form-group">
				<span  style="margin-left:500px;" ><input class="btn btn btn-primary" type="button" value="提交" onclick="saveMenu()"></span>
				<span  style="margin-left:100px;" ><input class="btn btn btn-primary" type="button" value="重置" onclick="reset()"></span>
			</div> 
		</form>
		<div class="doc-buttons">
			<c:forEach items="${res}" var="key">
				 ${key.description}  
			</c:forEach>
		</div>
		<div class="table-responsive">
			<table id="menuTable" data-toolbar="#toolbar"
				data-show-refresh="true" data-show-toggle="true"
				data-show-columns="true" data-show-export="true"
				data-show-footer="false" data-mobile-responsive="true">
			</table>
		</div>
	</div>
</div>
<script>
   $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>
<script type="text/javascript">
function getMenuId() {
	return $.map($("#menuTable").bootstrapTable('getSelections'), function(
			row) {
		return row.id
	});
}
function firstMenu() {
	var cbox = getMenuId();
	if (cbox == "") {
		layer.msg("请选择编辑项！！");
		return;
	}
	if (cbox.length > 1) {
		layer.msg("只能选中一个");
		return;
	}
	battcn.ajaxOpen({
		title : '二级目录编辑',
		href : rootPath + '/MenuOrder/getTwoMenuList.shtml?parentId=' + cbox,
		width : '80%',
		height : '70%',
		okhandler : function() {
			save();
		}
	});
} 
//重写参数传递
 function queryMenuParamsmer(params) {
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
$('#menuTable').bootstrapTable({
	url : rootPath + '/MenuOrder/MenuOrderList.shtml',
	height : '100%',
	sortName : 'id',
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
	queryParams : queryMenuParamsmer,//参数处理函数
	minimumCountColumns : 2,
	columns : [{
		checkbox : true
	}, {
		field : 'name',
		title : '一级菜单',
		align : 'center',
		valign : 'middle',
	}, {	
		field : 'level',
		title : '位置',
		align : 'center',
		valign : 'middle'
	}]
});
</script>
<script type="text/javascript">
	$(function(){
		saveMenu = function(obj) {
	 		if($("#menuListForm").valid()){
	 			layer.confirm('修改很漫长，请耐心等待！！！！');
	 			$.ajax({
				type: "POST", 
				url: rootPath + "/MenuOrder/updateMenuOrder.shtml",
				data: $('#menuListForm').serializeArray(),
				success: function(data){
						layer.confirm(data, function(index) {
							location.reload();
							battcn.closeWindow();
							
							});
				}
			});
	 		}
		} 
	}); 
</script>