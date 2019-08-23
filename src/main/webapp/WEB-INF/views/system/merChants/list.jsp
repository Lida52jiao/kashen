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
			<form role="form" class="form-inline" id="userSearchFormmerChants">
				<div class="input-group">
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
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入代理商编号" name="agentId" id="agentId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div><br>
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
				<select name="merStat" id="merStat" class="input form-control">
					<option value ="">请选择实名状态</option>
					<option value ="Y">实名</option>
					<option value ="N">未实名</option>
				</select>
				<span class="input-group-btn">
					</span>
				</div>

				<div class="input-group">
					<select name="agentStatus" id="agentStatus" class="input form-control">
						<option value ="">请选择代理商等级</option>
						<option value ="1">代理商</option>
						<option value ="2">运营商</option>
					</select>
					<span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="merType" id="merType" class="input form-control">
						<option value ="">请选择商户等级</option>
						<option value ="1">小咖</option>
						<option value ="2">大咖</option>
					</select>
					<span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="status" id="status" class="input form-control">
	  					<option value ="">请选择绑定状态</option>
	  					<option value ="Y">已绑定</option>
	  					<option value ="N">未绑定</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div> 
				<div class="input-group">
					<select name="frozen" id="frozen" class="input form-control">
	  					<option value ="">请选择是否可用</option>
	  					<option value ="Y">可用</option>
	  					<option value ="N">不可用</option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div> 
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchmerChants();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
				<div class="input-group">
					<button type="button" class="btn btn btn-primary"
							onclick="javascript:merchantsExcel();">
						<i class="fa fa-search"></i> 导出
					</button>
					</span>
				</div>

			</form>
			<div class="table-responsive">
				<table id="merChants" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getUserIdSelectionsmerChants() {
		return $.map($("#merChants").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function getMerChantId() {
		return $.map($("#merChants").bootstrapTable('getSelections'), function(
				row) {
			return row.merChantId
		});
	}
	function getUserMerStat() {
		return $.map($("#merChants").bootstrapTable('getSelections'), function(
				row) {
			return row.merStat
		});
	}
	function editMerChants() {
		var cbox = getUserIdSelectionsmerChants();
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
			href : rootPath + '/MerChants/editmerChants.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	} 
	function checkAccount() {
		var merchantId = getMerChantId();
            battcn.ajaxOpen({
                title : '提现设置',
                href : rootPath + '/MerChants/getAccount.shtml?merchantId='+merchantId,
                width : '40%',
                height : '80%',
                okhandler : function() {
                    save();
                }
            });
        }

	function getUsedAreaList() {
		battcn.ajaxOpen({
			title : '商户常住地列表',
			href : rootPath + "/MerChantsUsedArea/usedAreaHtml.shtml",
			width : '80%',
			height : '80%',
			okhandler : function() {
			}
		});
	} 
	function editUsedArea() {
		var merchantId = getMerChantId();
		var merStat = getUserMerStat();
		if (merchantId == "") {
			layer.msg("请选择查看常住地的商户！！");
			return;
		}
		if (merchantId.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		if (merStat == "N") {
			layer.msg("未实名用户，无常住地！！");
			return;
		}
		battcn.ajaxOpen({
			title : '商户常住地',
			href : rootPath + "/MerChantsUsedArea/usedArea.shtml?merChantId="+merchantId,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	} 
	function editmer() {
		var cbox = getUserIdSelectionsmerChants();
		if (cbox == "") {
			layer.msg("请选择删除项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		layer.confirm('是否删除？', function(index) {
			var url = rootPath + '/MerChants/deleteMer.shtml?id=' + cbox;
			var result = CommnUtil.ajax(url);
			if (result == "success") {
				$('#merChants').bootstrapTable('refresh');
				layer.msg('删除成功');
			} else {
				layer.msg('删除失败');
			}
		});
	}
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchmerChants() {
		$('#merChants').bootstrapTable('refresh');
	}
	function merchantsExcel() {
        var merChantId = $("#merChantId").val();
        var merName = $("#merName").val();
        var merMp = $("#merMp").val();
        var merType = $("#merType").val();
        var certNo = $("#certNo").val();
        var merStat = $("#merStat").val();
        var agentStatus = $("#agentStatus").val();
        var status = $("#status").val();
        var agentId = $("#agentId").val();
        var frozen = $("#frozen").val();
        var appId = $("#appId").val();
		window.location.href='excel/merchantOut.shtml?merChantId='+merChantId+'&merName='+merName+'&merMp='+merMp+'&agentStatus='+agentStatus+'&merType='+merType+'&certNo='+certNo+'&merStat='+merStat+'&status='+status+'&agentId='+agentId+'&frozen='+frozen+'&appId='+appId;
	}

	//重写参数传递
	 function queryParamsmerChants(params) {
		var merChantId = $("#merChantId").val();
		var merName = $("#merName").val();
		var merMp = $("#merMp").val();
		 var merType = $("#merType").val();
		var certNo = $("#certNo").val();
		var merStat = $("#merStat").val();
		var agentStatus = $("#agentStatus").val();
		 var status = $("#status").val();
		var agentId = $("#agentId").val();
		var frozen = $("#frozen").val();
		var appId = $("#appId").val();
		 var onename = $("#onename").val();
		 var onemp = $("#onemp").val();
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
			merChantId : merChantId,
			merName : merName,
			merMp : merMp,
			onemp : onemp,
			onename : onename,
			agentStatus : agentStatus,
			merType : merType,
			certNo : certNo,
			merStat : merStat,
			status : status,
			agentId : agentId,
			frozen : frozen,
			appId : appId
		}
	} 
	$('#merChants').bootstrapTable({
		url : rootPath + '/MerChants/getMerChants.shtml',
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
		queryParams : queryParamsmerChants,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'merChantId',
			title : '商户号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merName',
			title : '名称',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merMp',
			title : '手机号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'certNo',
			title : '身份证号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merStat',
			title : '实名状态',
			align : 'center',
			valign : 'middle',
			formatter:function merStatFormatter(value) {
					if(value == "Y"){
						return '实名';
					}
					if(value == "N"){
						return '未实名';
					}
				}
		}, {
			field : 'status',
			title : '绑定关系',
			align : 'center',
			valign : 'middle',
			formatter:function statusFormatter(value) {
					if(value == "Y"){
						return '已绑定';
					}
					if(value == "N"){
						return '未绑定';
					}
				}
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
			field : 'agentId',
			title : '代理商编号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merType',
			title : '商户等级',
			align : 'center',
			valign : 'middle'
		},{
			field : 'onename',
			title : '直推姓名',
			align : 'center',
			valign : 'middle'
		},{
			field : 'onemp',
			title : '直推手机号',
			align : 'center',
			valign : 'middle'
		},{
			field : 'oneMerId',
			title : '直推商户号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'frozen',
			title : '是否可使用',
			align : 'center',
			valign : 'middle',
			formatter:function frozenFormatter(value) {
					if(value == "Y"){
						return '可用';
					}
					if(value == "N"){
						return '不可用';
					}
				}
		}, {
			field : 'regDate',
			title : '注册时间',
			align : 'center',
			valign : 'middle',
			//formatter : function(value,row,index){return value.pattern("yyyy-MM-dd HH:mm:ss");  }
			sortable: true,
            //——修改——获取日期列的值进行转换
             formatter: function (value, row, index) {
                    return changeDateFormat(value);
                } 
		}, {
			field : 'merStatTime',
			title : '实名时间',
			align : 'center',
			valign : 'middle',
			//formatter : enabledFormatter
			sortable: true,
            //——修改——获取日期列的值进行转换
             formatter: function (value, row, index) {
                    return changeDateFormat(value);
                } 
		}, {
			field : 'statusDate',
			title : '绑定时间',
			align : 'center',
			valign : 'middle',
			//formatter : enabledFormatter
			sortable: true,
            //——修改——获取日期列的值进行转换
             formatter: function (value, row, index) {
                    return changeDateFormat(value);
                } 
		}, {
			field : 'startDate',
			title : '缴费时间',
			align : 'center',
			valign : 'middle',
			//formatter : enabledFormatter
			sortable: true,
            //——修改——获取日期列的值进行转换
             formatter: function (value, row, index) {
                    return changeDateFormat(value);
                } 
		}, {
			field : 'finishDate',
			title : '到期时间',
			align : 'center',
			valign : 'middle',
			//formatter : enabledFormatter
			sortable: true,
            //——修改——获取日期列的值进行转换
             formatter: function (value, row, index) {
                    return changeDateFormat(value);
                } 
		}, {
			field : 'balance',
			title : '余额',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'balanceProfit',
			title : '分润',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'appName',
			title : 'app名称',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'remarks',
			title : '上次登陆时间',
			align : 'center',
			valign : 'middle'
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
    		//新excel导出
		/*$('#merchantsExcel').click(function(){
		window.location.href="/report/out.shtml;
		});*/

        		  
</script>