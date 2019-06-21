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
			<form role="form" class="form-inline" id="integrateGoods">
				 <div class="input-group">
					<input type="text" placeholder="输入商品名称" name="goodsName" id="goodsName"
						class="input form-control"> <span class="input-group-btn"></span>
				</div>
				<div class="input-group">
					<select name="isUsed" id="isUsed" class="input form-control" >
							<option value ="">请选择商品状态</option>
					 		<option value ="0"> 不可兑换商品 </option>
					 		<option value ="1"> 可兑换商品</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入商品编号" name="goodsId" id="goodsId"
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
						onclick="javascript:integrateGoodsSearchForm();">
						<i class="fa fa-search"></i> 搜索
					</button>
				</div> 
			</form>
			<div class="table-responsive">
				<table id="integrateGoodsTable" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
	function getGoodsIdIngreate() {
		return $.map($("#integrateGoodsTable").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function editGoods() {
		var cbox = getGoodsIdIngreate();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		battcn.ajaxOpen({
			title : '编辑/查看',
			href : rootPath + '/Integrage/IntegrateGoodById.shtml?goodsId=' + cbox,
			width : '40%',
			height : '100%',
			okhandler : function() {
				save();
			}
		});
	} 
	function addGoods() {
		battcn.ajaxOpen({
			title : '添加',
			href : rootPath + '/Integrage/IntegrateGoodAdd.shtml',
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	} 
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function integrateGoodsSearchForm() {
		$('#integrateGoodsTable').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryBaseParamsmer(params) {
		var goodsName = $("#goodsName").val();
		var isUsed = $("#isUsed").val();
		var goodsId = $("#goodsId").val();
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
			goodsName : goodsName,
			isUsed : isUsed,
			goodsId : goodsId,
			appId : appId
		}
	} 
	$('#integrateGoodsTable').bootstrapTable({
		url : rootPath + '/Integrage/getIntegrateGoodsList.shtml',
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
		columns : [ {
			checkbox : true
		}, {
			field : 'id',
			title : '商品编号',
			align : 'center',
			valign : 'middle'
		}, {	
			field : 'name',
			title : '商品名称',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'isUsed',
			title : '商品状态',
			align : 'center',
			valign : 'middle',
			formatter:function merStatFormatter(value) {
				if(value == "1"){
					return '可兑换';
				}
				if(value == "0"){
					return '不可兑换';
				}
			}
		},{
			field : 'appId',
			title : '归属App',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "0000"){
					return "帐期机器人";
				}
			}
		},  {
			field : 'createTime',
			title : '商品上架时间',
			align : 'center',
			valign : 'middle'
		}]
	});
</script>