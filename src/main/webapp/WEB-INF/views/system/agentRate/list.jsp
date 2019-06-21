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
			<form role="form" class="form-inline" id="floorNumber">
				<input id="admin" type="hidden" value = "${USER_SESSION_KEY.userName}"/>
				 <div class="input-group">
					<input type="text" placeholder="输入代理商号" name="merId" id="merId"
						class="input form-control"> <span class="input-group-btn"></span>
				</div>
				 <div class="input-group">
					<input type="text" placeholder="输入商户号" name="merChantId" id="merChantId"
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
					<select name="agentStatus" id="agentStatus" class="agentStatus">
						<option value ="">请选择代理商等级  </option>
		  				<c:forEach items="${agentLevel}" var="key">
							 <option value ="${key.level}">${key.levelname}  </option>
						</c:forEach>
	  				</select>
					<span class="input-group-btn"></span>
				</div>
				<div class="input-group">
					<button type="button" class="btn btn btn-primary"
						onclick="javascript:agentRateSearchForm();">
						<i class="fa fa-search"></i> 搜索
					</button>
				</div> 
			</form>
			<div class="table-responsive">
				<table id="agentRateTable" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function queryCode() {
	return $.map($("#agentRateTable").bootstrapTable('getSelections'), function(
			row) {
		return row.merId
	});
}
 function queryAgentRate() {
	var box = queryCode();
	if (box == "") {
		layer.msg("请选择查看项！！");
		return;
	}
	if (box.length > 1) {
		layer.msg("只能选中一个");
		return;
	}
	battcn.ajaxOpen({
		title : '调整',
		href : rootPath + '/AgentRate/RateList.shtml?agentid=' + box,
		width : '40%',
		height : '80%',
		okhandler : function() {
			save();
		}
	});
} 
</script>

<script type="text/javascript">
    var userName =$("#admin").val();
	function getAgentRateIdSelectionsmerCode() {
		return $.map($("#agentRateTable").bootstrapTable('getSelections'), function(
				row) {
			return row.merId
		});
	}
	function getAgentRateIdSelectionsmerCodeT() {
		return $.map($("#agentRateTable").bootstrapTable('getSelections'), function(
				row) {
			return row.merChantId
		});
	}
	 function editAgentRate() {
		var cbox = getAgentRateIdSelectionsmerCode();
		var merChantId = getAgentRateIdSelectionsmerCodeT();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if(cbox == "T00000007"){
			layer.msg("总平台不享有编辑自己的权限！！");
			return;
		}
		battcn.ajaxOpen({
			title : '调整',
			href : rootPath + '/AgentRate/editRate.shtml?merId=' + cbox+'&merChantId='+merChantId,
			width : '40%',
			height : '50%',
			okhandler : function() {
				save();
			}
		});
	} 
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentRateSearchForm() {
		$('#agentRateTable').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryBaseParamsmer(params) {
		var merId = $("#merId").val();
		var agentStatus = $("#agentStatus").val();
		var appId = $("#appId").val();
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
			agentStatus : agentStatus,
			appId : appId,
			merChantId : merChantId
		}
	} 
	$('#agentRateTable').bootstrapTable({
		url : rootPath + '/Transaction/getListForNoCard.shtml',
		height : '100%',
		sortName : 't.id',
		sortOrder : 'desc',
		ajaxOptions: {async:true,timeout:50000},
		showColumns : true,
		showExport : true,
		striped : true,
		pagination : true,
		pageNumber : 1,
		pageSize : 10,
		pageList : "[10,20,30,120,All]",
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
			field : 'merId',
			title : '代理商户号',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'agentName',
			title : '代理商名称',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'agentStatus',
			title : '代理商等级',
			align : 'center',
			valign : 'middle',
			formatter:function agentStatusFormatter(value) {
					if(value == "N"){
						return '不是代理商';
					}
					if(value == "1"){
						return '代理商';
					}
					if(value == "2"){
						return '运营商';
					}
				}
		}, {
			field : 'merChantId',
			title : '绑定商户号',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'appName',
			title : '归属app',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'updateName',
			title : '修改人姓名',
			align : 'center',
			valign : 'middle',
		},  {
			field : 'updateDate',
			title : '修改时间',
			align : 'center',
			valign : 'middle',
			sortable: true,
            //——修改——获取日期列的值进行转换
             formatter: function (value, row, index) {
                    return changeDateFormat(value);
                } 
		}]
	});
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