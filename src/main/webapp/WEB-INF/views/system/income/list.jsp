<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#profit-label{
	color:red;
}
</style>
<div class="wrapper wrapper-content animated fadeInRight"
	style="height: 100%">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div id="showbutton" class="doc-buttons">
				<c:forEach items="${res}" var="key">
					 ${key.description}  
				</c:forEach>
			</div>
			<form role="form" class="form-inline" id="userSearchFormIncome">
					<label class="col-sm-2 control-label" id="profit-label">分润分配模式为：</label>
					<input id="profit" name="profit"  class="form-control" disabled="disabled" type="text" value="${profit}">
			</form>
			<div class="table-responsive">
				<table id="incomeTable" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
			<form class="form-horizontal m-t required-validate" id="subsidyNumber">
				<div class="form-group">
					<label class="col-sm-4 control-label">当平台只有一个代理等级的时候，补贴上级代理万分之：</label>
					<div class="col-sm-3">
						<input id="twoagent" name="twoagent"  class="form-control"  type="text" value="${subsidy.twoagent*1000}">
						<span class="help-block m-b-none">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">补贴上级商户万分之：</label>
					<div class="col-sm-3">
						<input id="onemerchant" name="onemerchant"  class="form-control" type="text" value="${subsidy.onemerchant*1000}">
							<span class="help-block m-b-none">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">补贴上上级商户万分之：</label>
					<div class="col-sm-3">
						<input id="twomerchant" name="twomerchant"  class="form-control" type="text" value="${subsidy.twomerchant*1000}">
							<span class="help-block m-b-none">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">补贴县级代理万分之：</label>
					<div class="col-sm-3">
						<input id="countryagent" name="countryagent"  class="form-control" type="text" value="${subsidy.countryagent*1000}">
							<span class="help-block m-b-none">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">补贴市级代理万分之：</label>
					<div class="col-sm-3">
						<input id="cityagent" name="cityagent"  class="form-control" type="text" value="${subsidy.cityagent*1000}">
							<span class="help-block m-b-none">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">补贴省级代理万分之：</label>
					<div class="col-sm-3">
						<input id="provinceagent" name="provinceagent"  class="form-control" type="text" value="${subsidy.provinceagent*1000}">
							<span class="help-block m-b-none">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">补贴顶级代理万分之：</label>
					<div class="col-sm-3">
						<input id="topagent" name="topagent"  class="form-control" type="text" value="${subsidy.topagent*1000}">
							<span class="help-block m-b-none">
					</div>
				</div><input class="btn btn btn-primary" type="button" value="提交" onclick="saveSubsidy()"></span>
			</form>
		</div>
	</div>
</div>

<script type="text/javascript">

var isProfit = $("#profit").val();
if(isProfit == "settled"){
	$("#profit").val("固定返佣");
}else if(isProfit == "rate"){
	$("#profit").val("升级降费率");
}
if(isProfit == "rate"){
	$("#showbutton").attr("style","display:none;");//隐藏div
	$("#incomeTable").attr("style","display:none;");//隐藏table
}
</script>
<script>
$("#twoagent").val(${subsidy.twoagent*10000});
$("#onemerchant").val(${subsidy.onemerchant*10000});
$("#twomerchant").val(${subsidy.twomerchant*10000});
$("#countryagent").val(${subsidy.countryagent*10000});
$("#cityagent").val(${subsidy.cityagent*10000});
$("#provinceagent").val(${subsidy.provinceagent*10000});
$("#topagent").val(${subsidy.topagent*10000});
</script>
<script type="text/javascript">
$(function(){
	saveSubsidy = function(obj) {
		var twoagent = $("#twoagent").val();
		var onemerchant = $("#onemerchant").val();
		var twomerchant = $("#twomerchant").val();
		var countryagent = $("#countryagent").val();
		var cityagent = $("#cityagent").val();
		var provinceagent = $("#provinceagent").val();
		var topagent = $("#topagent").val();
		if(twoagent == ''){
			alert('请填写间接代理补贴！！')
			return false;
		}
		if(onemerchant == ''){
			alert('请填上级商户补贴！！')
			return false;
		}
		if(twomerchant == ''){
			alert('请填上上级商户补贴！！')
			return false;
		}
		if(countryagent == ''){
			alert('请填写县级补贴！！')
			return false;
		}
		if(cityagent == ''){
			alert('请填写市级补贴！！')
			return false;
		}
		if(provinceagent == ''){
			alert('请填写省级补贴！！')
			return false;
		}
		if(topagent == ''){
			alert('请填写顶级补贴！！')
			return false;
		}else if($("#subsidyNumber").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Subsidy/confirm.shtml",
				data: $('#subsidyNumber').serializeArray(),
				success: function(data){
					layer.confirm(data, function(index) {
						battcn.closeWindow();
						$('#incomeTable').bootstrapTable('refresh');
			        	return false;
 						});

				}
			});
  		}
	} 
	
 }); 
</script>
<script type="text/javascript">
	function getUserIdSelectionsIncome() {
		return $.map($("#incomeTable").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	 function editIncome() {
		var cbox = getUserIdSelectionsIncome();
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
			href : rootPath + '/Income/editUIalter.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
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
	 function queryParamsIncome(params) {
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
	$('#incomeTable').bootstrapTable({
		url : rootPath + '/Income/getList.shtml',
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
		queryParams : queryParamsIncome,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'level',
			title : '级别名',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'first',
			title : '还款直接分润(万分之)',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value*10000)).toFixed(2);
			}
		}, {
			field : 'second',
			title : '还款间接分润(万分之)',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value*10000)).toFixed(2);
			}
		}, {
			field : 'third',
			title : '还款间间接分润(万分之)',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value*10000)).toFixed(2);
			}
		}, {
			field : 'brushFirst',
			title : '无卡直接分润(万分之)',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value*10000)).toFixed(2);
			}
		}, {
			field : 'brushSecond',
			title : '无卡间接分润(万分之)',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value*10000)).toFixed(2);
			}
		}, {
			field : 'brushThird',
			title : '无卡间间接分润(万分之)',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value*10000)).toFixed(2);
			}
		}]
	});
</script>