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
			<form role="form" class="form-inline" id="getbanner">
			</form>
			<div class="table-responsive">
				<table id="banner" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getBanner() {
		return $.map($("#banner").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function insertBanner() {
		battcn.ajaxOpen({
			title : 'Banner增加',
			href : rootPath + '/CardStrategy/BannerInsert.shtml',
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	} 
	function editBanner() {
		var cbox = getBanner();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		battcn.ajaxOpen({
			title : 'Banner编辑',
			href : rootPath + '/CardStrategy/BannerUpdate.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	} 
	function deleteBanner() {
		var cbox = getBanner();
		if (cbox == "") {
			layer.msg("请选择删除项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		layer.confirm('是否删除？', function(index) {
			var url = rootPath + '/CardStrategy/deleteBanner.shtml?id=' + cbox;
			var result = CommnUtil.ajax(url);
				layer.msg(result);
				$('#banner').bootstrapTable('refresh');
		});
	}
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function getbannerSearch() {
		$('#banner').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsbanner(params) {
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
	$('#banner').bootstrapTable({
		url : rootPath + '/CardStrategy/getBannerList.shtml',
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
		queryParams : queryParamsbanner,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'imgURL',
			title : '图片地址',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				 return "<a href="+value+" target='_blank'>预览</a>"
				
			}
		}, {
			field : 'forwardURL',
			title : 'banner跳转链接',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				 return "<a href="+value+" target='_blank'>预览</a>"
				
			}
		}, {	
			field : 'appId',
			title : '归属app',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "0000"){
					return "帐期机器人";
				}
			}
		}, {
			field : 'creatDate',
			title : '新增时间',
			align : 'center',
			valign : 'middle',
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