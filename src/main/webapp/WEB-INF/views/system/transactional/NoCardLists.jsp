<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script> 
<div class="wrapper wrapper-content animated fadeInRight"
	style="height: 100%">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="doc-buttons">
				 <c:forEach items="${res}" var="key">
					 ${key.description}  
				</c:forEach> 
			</div>
			<form role="form" class="form-inline" id="userSearchNoCardCount">
				<c:if test="${type=='1'}">
					<div class="input-group">
						<input type="text" placeholder="输入代理商编号" name="agentId" id="agentId"
							class="input form-control"> <span class="input-group-btn">
						</span>
					</div>
				</c:if>
				<div class="form-group">
				<div class="col-sm-8">
					<select name="aislecode" id="aislecode" class="input form-control" >
							<option value ="">请选择通道  </option>
			                 <c:forEach items="${key}" var="key">
			                 	<c:if test="${key.aislecode=='rf'}">
									<option value ="${key.aislecode}">银联快捷R  </option>
								</c:if>
								<c:if test="${key.aislecode=='yb'}">
									<option value ="${key.aislecode}">快捷多商户  </option>
								</c:if>
								<c:if test="${key.aislecode=='zf01'}">
									<option value ="${key.aislecode}">银联快捷Z  </option>
								</c:if>
								<c:if test="${key.aislecode=='easy'}">
									<option value ="${key.aislecode}">易生  </option>
								</c:if>
								<c:if test="${key.aislecode=='yb01'}">
									<option value ="${key.aislecode}">银联快捷Y  </option>
								</c:if>
								<c:if test="${key.aislecode=='xj'}">
									<option value ="${key.aislecode}">银联快捷X  </option>
								</c:if>
								<c:if test="${key.aislecode=='hz'}">
									<option value ="${key.aislecode}">银联快捷H  </option>
								</c:if>
								<c:if test="${key.aislecode=='xjwk'}">
									<option value ="${key.aislecode}">大额落地X  </option>
								</c:if>
								<c:if test="${key.aislecode=='hz01'}">
									<option value ="${key.aislecode}">银联快捷H1  </option>
								</c:if>
								<c:if test="${key.aislecode=='cj'}">
									<option value ="${key.aislecode}">大额快捷C  </option>
								</c:if>
								<c:if test="${key.aislecode=='kft'}">
									<option value ="${key.aislecode}">大额快捷K  </option>
								</c:if>
							</c:forEach>
		  			</select>
	  			</div>
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
				</div><br>
			<!-- 	<div class="input-group">
					<select name="type" id="types" class="input form-control">
						<option value ="">请选择分润类型</option>
						<option value ="1">落地分润</option>
						<option value ="epos">无卡分润</option>
	  					<option value ="2">还款 </option>
	  					<option value ="vipPay">年月费</option>
	  					<option value ="levelPay">升级费</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div> -->
				<div class="form-group">
				<div class="col-sm-8">
					<input type="text" placeholder="交易开始时间" name="starttime" id="starttime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-8">
					<input type="text" placeholder="交易结束时间" name="finishtime" id="finishtime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchNoCardCount();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="NoCardCount" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchNoCardCount() {
		$('#NoCardCount').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsNoCardCount(params) {
	 	var agentId = $("#agentId").val();
	 	var merchantId = $("#merchantId").val();
	 	var type = $("#types").val();
	 	var starttime = $("#starttime").val();
	 	var finishtime = $("#finishtime").val();
		var aislecode = $("#aislecode").val();
	 	var appId = $("#appId").val();
		var isLd = $("#isLd").val();
		var payType = $("#payType").val();
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
			agentId : agentId,
			merchantId : merchantId,
			type : type,
			starttime : starttime,
			finishtime : finishtime,
			aislecode : aislecode,
			appId : appId,
			isLd : isLd,
			payType : payType
		}
	} 
	$('#NoCardCount').bootstrapTable({
		url : rootPath + '/Transactional/noCardCount.shtml',
		height : '100%',
		sortName : 'tlId',
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
		queryParams : queryParamsNoCardCount,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'totalAmount',
			title : '总交易金额（元）',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value/100)).toFixed(2);
			}
		}, {
			field : 'totalPayFee',
			title : '总手续费（元）',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value/100)).toFixed(2);
			}
		}, {
			field : 'totalInstitutionFee',
			title : '我的分润（元）',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value/100)).toFixed(2);
			}
		}, {
			field : 'totalNoInstitutionFee',
			title : '下属分润（元）',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value/100)).toFixed(2);
			}
		}]
	});
	
</script>