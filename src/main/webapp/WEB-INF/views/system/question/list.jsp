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
			<form role="form" class="form-inline" id="userInterlocution">
				<!-- <div class="input-group">
					<input type="text" placeholder="输入商户号" name="merChantId" id="merChantId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入商户名称" name="merName" id="merName"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入商户手机号" name="merMp" id="merMp"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入身份证号" name="certNo" id="certNo"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div> -->
				<!-- <div class="input-group">
					<select name="merStat" id="merStat" class="input form-control">
	  					<option value ="">请选择实名状态</option>
	  					<option value ="Y">实名</option>
	  					<option value ="N">未实名</option>
	  					</select>
					<span class="input-group-btn">
					</span>
				</div> -->
				<!-- <div class="input-group">
					<select name="status" id="status" class="input form-control">
	  					<option value ="">请选择激活状态</option>
	  					<option value ="Y">激活</option>
	  					<option value ="N">未激活</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div> 
				<div class="input-group">
					<input type="text" placeholder="输入代理商编号" name="agentId" id="agentId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div> -->
				<div class="form-group">
					<div class="col-sm-8">
						<select id="appId" name="appId" class="input form-control" >
		            		<option value ="">请选择app </option>
		                   	<c:forEach items="${s}" var="s">
		                  		<option value ="${s.appId}">${s.appName}</option>
		                	</c:forEach>
	             		</select>
						<span class="help-block m-b-none">
					</div>
				</div>
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchInterlocution();">
							<i class="fa fa-search"></i> 搜索
						</button>
				</div>
			</form>
			<div class="table-responsive">
				<table id="interlocution" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getUserIdSelectionsinterlocution() {
		return $.map($("#interlocution").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function editInterlocutions() {
		var cbox = getUserIdSelectionsinterlocution();
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
			href : rootPath + '/Question/alter.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	} 
	function editInterlocution() {
		var cbox = getUserIdSelectionsinterlocution();
		if (cbox == "") {
			layer.msg("请选择删除项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		layer.confirm('是否删除？', function(index) {
			var url = rootPath + '/Question/delete.shtml?id=' + cbox;
			var result = CommnUtil.ajax(url);
			if (result == "success") {
				$('#interlocution').bootstrapTable('refresh');
				layer.msg('删除成功');
			} else {
				layer.msg('删除失败');
			}
		});
	}
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchInterlocution() {
		$('#interlocution').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsinterlocution(params) {
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
			appId : appId
		}
	} 
	$('#interlocution').bootstrapTable({
		url : rootPath + '/Question/select.shtml',
		height : '100%',
		sortName : 'id',
		sortOrder : 'desc',
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
		queryParams : queryParamsinterlocution,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'question',
			title : '问题',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'answer',
			title : '答案',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'type',
			title : '类型',
			align : 'center',
			valign : 'middle',
			formatter:function typesFormatter(value) {
					if(value == "1"){
						return '常见问题';
					}
					if(value == "2"){
						return '无卡问题';
					}
					if(value == "3"){
						return '还款问题';
					}
					if(value == "4"){
						return '其它问题';
					}
				}
		}, {
			field : 'forwardURL',
			title : '跳转链接',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'institutionId',
			title : '机构号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'appId',
			title : 'app名称',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "0000"){
					return '帐期机器人';
				}
			}
		}]
	});
	/* //修改——转换日期格式(时间戳转换为datetime格式)
        function changeDateFormat(cellval) {
            /* if (cellval != null) {
                var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                return date.getFullYear() + "-" + month + "-" + currentDate; 
            } */
           /*  if(cellval != "" && cellval != null){
             //1.获取时间戳前十位数字
                unittime = cellval.replace("/Date(", "").replace(")/", "");
                var shijian = unittime.substr(0, unittime.length - 3); */


                //2.把获取的数据*1000
               /*  shijian = shijian * 1000;
                //3.转换成js时间格式
                var jsDate = new Date(shijian);
                var UnixTimeToDate = jsDate.getFullYear() + '-' + (jsDate.getMonth() + 1) + '-' + jsDate.getDate()
                + ' ' + jsDate.getHours() + ':' + jsDate.getMinutes();
               // + ' ' + jsDate.getHours() + ':' + jsDate.getMinutes() + ':' + jsDate.getSeconds();
                return UnixTimeToDate;
            } 
        } */// */
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