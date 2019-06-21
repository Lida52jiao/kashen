<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="News">
			 <div class="doc-buttons">
				<c:forEach items="${res}" var="key">
					 ${key.description}  
				</c:forEach>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">app：</label>
				<div class="col-sm-8">
				<select name="appId"> 
    			
             	<option value="${jiGuang.appId}">${jiGuang.appName}</option>
      			 
				</select> 
			</div>
			</div>
			<%--<div class="form-group">--%>
				<%--<label class="col-sm-3 control-label">商户号：</label>--%>
				<%--<div class="col-sm-8">--%>
					<%--<input id="merChantId" name="merChantId"  class="news" type="text" value="">--%>
                    <%--<span class="help-block m-b-none">给商户通知必填</span>--%>
				<%--</div>--%>
			<%--</div>--%>
			<div class="form-group">
				<label class="col-sm-3 control-label">内容：</label>
				<div class="col-sm-8">
					<textarea name="msg" rows="2" cols="25" class="news" ></textarea> 
                    <span class="help-block m-b-none"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><input type="button" value="提交" onclick="saveNew()"></label>
				<label class="col-sm-3 control-label"><input type="button" value="重置" onclick="reset()"></label>	
			</div> 
		</form>
			<div class="table-responsive">
				<table id="news" data-toolbar="#toolbar"
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

 $(function(){
  	saveNew = function(obj) {
  		if($("#News").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Push/add.shtml",
				data: $('#News').serializeArray(),
				success: function(data){
					if(data == "f"){
						layer.confirm('填完整信息', function(index) {
							battcn.closeWindow();
							$('.news').val("");
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('发送成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#news').bootstrapTable('refresh');
							$('.news').val("");
				        	return false;
 						});
					}
					battcn.toastrsAlert({
		       		     code: data.success ? 'success' :'error',
		       		     msg: data.success ? '成功' :'失败'
		       		});
				}
			});
  		}
	} 
	
 }); 
</script>
<script type="text/javascript">
	function getUserIdSelectnews() {
		return $.map($("#news").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function editNews() {
		var cbox = getUserIdSelectnews();
		if (cbox == "") {
			layer.msg("请选择删除项！！");
			return;
		}
		layer.confirm('是否删除？', function(index) {
			var url = "http://47.104.25.59:1183/sendMessage/deleteLists?id=" + cbox;
			var result = CommnUtil.ajax(url);
			if (result == "success") {
				$('#news').bootstrapTable('refresh');
				layer.msg('删除成功');
			} else {
				layer.msg('删除失败');
			}
		});
	}
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchnews() {
		$('#news').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsnews(params) {
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
		}
	} 
	$('#news').bootstrapTable({
		url : rootPath + '/News/get.shtml',
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
		queryParams : queryParamsnews,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'appId',
			title : '推送给谁',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "0000"){
					return '有为生活';
				} else {
					return value;
				}
			}
		}, {
			field : 'msg',
			title : '推送内容',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'createDate',
			title : '推送日期',
			align : 'center',
			valign : 'middle',
			sortable: true,
            // formatter: function (value, row, index) {
            //     return changeDateFormat(value);
            // }
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