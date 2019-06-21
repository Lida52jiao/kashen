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
			<form role="form" class="form-inline" id="userSearchFormbanner">
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
				<table id="bannerTable" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getUserIdSelectionsbanner() {
		return $.map($("#bannerTable").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function editBanner() {
		var cbox = getUserIdSelectionsbanner();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		battcn.ajaxOpen({
			title : '编辑轮播图',
			href : rootPath + '/Banner/editbanner.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	} 
	function delBanner() {
		var cbox = getUserIdSelectionsbanner();
		if (cbox == "") {
			layer.msg("请选择删除项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		layer.confirm('是否删除？', function(index) {
			var url = rootPath + '/Banner/delbanners.shtml?id=' + cbox;
			var result = CommnUtil.ajax(url);
			if (result == "success") {
				$('#bannerTable').bootstrapTable('refresh');
				layer.msg('删除成功');
			} else {
				layer.msg('删除失败');
			}
		});
	}
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	/* function agentSearch() {
		$('#agentTable').bootstrapTable('refresh');
	} */
	//重写参数传递
	 function queryParamsBanner(params) {
		//var merId = $("#merId").val();
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
			//merId : merId
		}
	} 
	$('#bannerTable').bootstrapTable({
		url : rootPath + '/Banner/getBannerList.shtml',
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
		queryParams : queryParamsBanner,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'name',
			title : '名称',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'status',
			title : '类型',
			align : 'center',
			valign : 'middle',
			formatter: function bannerFormatter(value) {
					if(value == "0"){
						return '首页';
					}
					if(value == "1"){
						return '信用卡申请';
					}
					if(value == "2"){
						return '用卡百科';
					}
					if(value == "3"){
						return '广告位';
					}
					if(value == "4"){
						return '分享页';
					}
				}
		},{
			field : 'imgURL',
			title : '图片的链接',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
					 return "<a href="+value+" target='_blank'>查看</a>"
				}
		},{
			field : 'forwardURL',
			title : '跳转链接',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
					 return "<a href="+value+" target='_blank'>查看</a>"
				}
		}, {
			field : 'appId',
			title : '所属app',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "0001"){
					return '宜富';
				}
				if(value == "0002"){
					return '融易用';
				}
			}
				
		}]
	});
</script>