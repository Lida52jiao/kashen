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
			<form role="form" class="form-inline" id="numSearchFormNum">
				<input id="enjoy" name="enjoy"  class="form-control" type="hidden" value="${enjoy }" >
			</form>
			<div class="table-responsive">
				<table id="numTables" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
			<form class="form-horizontal m-t required-validate" id="enjoyNumber">
					<label class="col-sm-3 control-label"><span style="color: red;">最低等级是否享受此权益：</span></label>
					<div class="col-sm-5">
						 <input name="enjoy" id="isenjoy" type="radio" value="Y" >是
		    			 <input name="enjoy" id="notenjoy" type="radio" value="N">否
	    			 </div>
					<span  style="margin-left:300px;" ><input class="btn btn btn-primary" type="button" value="提交" onclick="saveEnjoy()"></span>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">

var isEnjoy = $("#enjoy").val();
 if(isEnjoy == "Y"){
	$("#isenjoy").attr("checked",true);
}else if(isEnjoy == "N"){
	$("#notenjoy").attr("checked",true);
}
</script>
<script type="text/javascript">
$(function(){
	saveEnjoy = function(obj) {
		var enjoy = $('input:radio[name="enjoy"]:checked').val();
		if(enjoy == null){
			alert('请选择是否享有此权益！！')
			return false;
		}else if($("#enjoyNumber").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Enjoy/confirm.shtml",
				data: $('#enjoyNumber').serializeArray(),
				success: function(data){
					layer.confirm(data, function(index) {
						battcn.closeWindow();
						$('#numTables').bootstrapTable('refresh');
			        	return false;
 						});

				}
			});
  		}
	} 
	
 }); 
</script>
<script type="text/javascript">
	function getUserIdSelectionsNum() {
		return $.map($("#numTables").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	 function editNum() {
		var cbox = getUserIdSelectionsNum();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		battcn.ajaxOpen({
			title : '编辑升级费',
			href : rootPath + '/Num/editNum.shtml?id=' + cbox,
			width : '40%',
			height : '95%',
			okhandler : function() {
				save();
			}
		});
	} 
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	/* function agentSearch() {
		$('#usedTable').bootstrapTable('refresh');
	} */
	//重写参数传递
	 function queryParamsNum(params) {
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
	$('#numTables').bootstrapTable({
		url : rootPath + '/Num/getNumList.shtml',
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
		queryParams : queryParamsNum,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'mertypeName',
			title : '商户升级动作',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				 return "由上一级别-->"+value;
				
			}
		}, {
			field : 'num',
			title : '金额（元）',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'validtime',
			title : '有效期（天）',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'onemerchant',
			title : '直推',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'twomerchant',
			title : '间推',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'threemerchant',
			title : '间间推',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'oneagent',
			title : '直接代理',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'twoagent',
			title : '间接代理',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'countyagent',
			title : '县级代理',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'cityagent',
			title : '市级代理',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'provinceagent',
			title : '省级代理',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'topagent',
			title : '顶级代理',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'institution',
			title : '平台',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'amount',
			title : '推荐人数',
			align : 'center',
			valign : 'middle'
		}]
	});
</script>