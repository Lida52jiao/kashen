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
			<form role="form" class="form-inline" id="">
				 <input id="agentid" name="agentid"  class="form-control" type="hidden" value="${agentid}" >
			</form>
			<div class="table-responsive">
				<table id="queryRateTable" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	
	var agentid=$("#agentid").val();
	
	//重写参数传递
	 function queryBaseParamsmer(params) {
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
	$('#queryRateTable').bootstrapTable({
		url : rootPath + '/Rates/queryAlert.shtml?agentid='+agentid,
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
		queryParams : queryBaseParamsmer,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'rate',
			title : '通道费率%',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value*100)).toFixed(3);
			}
		}, {
			field : 'd0fee',
			title : '笔数费',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		},  {
			field : 'aislecode',
			title : '网关',
			align : 'center',
			valign : 'middle',
			formatter:function merStatFormatter(value) {
				if(value == "ld01"){
					return "落地通道L";
				}
				if(value == "ld02"){
					return "落地通道Y";
				}
				if(value == "ld03"){
					return "落地通道X";
				}
				if(value == "ld04"){
					return "落地还款X";
				}
				if(value == "ld05"){
					return "落地还款T";
				}
				if(value == "ld06"){
					return "大额还款K";
				}
				if(value == "ld07"){
					return "落地大额H";
				}
                if(value == "ld09"){
                    return "落地还款C";
                }
                if(value == "ld11"){
                    return "落地还款NT";
                }
                if(value == "ld12"){
                    return "落地大额C";
                }
                if(value == "ld14"){
                    return "小额落地HB";
                }
                if(value == "ld15"){
                    return "落地大额C2";
                }
                if(value == "ld16"){
                    return "小额落地C2";
                }
			}
		}]
	});
</script>