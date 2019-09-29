<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.sp {
		color: red;
	}
</style>
<div class="wrapper wrapper-content animated fadeInRight"
	style="height: 100%">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<div class="doc-buttons">
				<c:forEach items="${res}" var="key">
					 ${key.description}  
				</c:forEach>
			</div>
			<form role="form" class="form-inline" id="merChantsRateSearchmerChants">
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
					<select name="aislecode" id="aislecode" class="input form-control" >
						<option value ="">请选择网关  </option>
			                 <c:forEach items="${aislecode}" var="key">
			                 	<c:if test="${key.aislecode=='rf'}">
									<option value ="${key.aislecode}">银联快捷R  </option>
								</c:if>
								<%--<c:if test="${key.aislecode=='yb'}">
									<option value ="${key.aislecode}">快捷多商户  </option>
								</c:if>--%>
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
								<%--<c:if test="${key.aislecode=='ld01'}">
									<option value ="${key.aislecode}">落地通道L  </option>
								</c:if>
								<c:if test="${key.aislecode=='ld02'}">
									<option value ="${key.aislecode}">落地通道Y  </option>
								</c:if>--%>
								<c:if test="${key.aislecode=='hz'}">
									<option value ="${key.aislecode}">银联快捷H  </option>
								</c:if>
								<c:if test="${key.aislecode=='xjwk'}">
									<option value ="${key.aislecode}">大额落地X  </option>
								</c:if>
								<c:if test="${key.aislecode=='ld03'}">
									<option value ="${key.aislecode}">落地通道X  </option>
								</c:if>
								<%--<c:if test="${key.aislecode=='hz01'}">
									<option value ="${key.aislecode}">银联快捷H1  </option>
								</c:if>--%>
								<c:if test="${key.aislecode=='ld04'}">
									<option value ="${key.aislecode}">落地还款X  </option>
								</c:if>
								<c:if test="${key.aislecode=='cj'}">
									<option value ="${key.aislecode}">大额快捷C  </option>
								</c:if>
								<c:if test="${key.aislecode=='ld05'}">
									<option value ="${key.aislecode}">落地还款T  </option>
								</c:if>
								<c:if test="${key.aislecode=='ld06'}">
									<option value ="${key.aislecode}">大额还款K </option>
									</c:if>
                                 <c:if test="${key.aislecode=='ld07'}">
                                     <option value ="${key.aislecode}">落地大额H </option>
                                 </c:if>
                                 <c:if test="${key.aislecode=='ld09'}">
                                     <option value ="${key.aislecode}">落地还款C </option>
                                 </c:if>
								 <c:if test="${key.aislecode=='ld11'}">
									 <option value ="${key.aislecode}">落地还款NT </option>
								 </c:if>
									<c:if test="${key.aislecode=='kft'}">
									<option value ="${key.aislecode}">大额快捷K  </option>
								</c:if>
								 <c:if test="${key.aislecode=='ld12'}">
									 <option value ="${key.aislecode}">落地大额C </option>
								 </c:if>
								 <c:if test="${key.aislecode=='ld14'}">
									 <option value ="${key.aislecode}">小额落地HB </option>
								 </c:if>
								 <c:if test="${key.aislecode=='ld15'}">
									 <option value ="${key.aislecode}">落地大额C2 </option>
								 </c:if>
								 <%--<c:if test="${key.aislecode=='ld16'}">
									 <option value ="${key.aislecode}">小额落地C2 </option>
								 </c:if>
								 <c:if test="${key.aislecode=='ld13'}">
									 <option value ="${key.aislecode}">落地小额D </option>
								 </c:if>--%>
								 <c:if test="${key.aislecode=='ld17'}">
									 <option value ="${key.aislecode}">组合计划T </option>
								 </c:if>
							</c:forEach>
	  				</select>
					 <span class="input-group-btn"></span>
				</div>

					<div class="input-group">
						<select name="isrepayment" id="isrepayment" class="input form-control" >
							<option value ="">请选择通道类型</option>
							<option value ="Y">还款</option>
							<option value ="N">无卡</option>
						</select>
					</div>

				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:merChantsRateSearchmerChants();">
							<i class="fa fa-search"></i> 搜索
						</button>
				</div>
			</form>
			<div class="table-responsive">
				<table id="merChantsRate" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
			<div>
				<span class="sp">****修改后仅对新增用户有效*****</span>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getmerChantsRateIdSelectionsmerChants() {
		return $.map($("#merChantsRate").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function editMerChantsRate() {
		var cbox = getmerChantsRateIdSelectionsmerChants();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		battcn.ajaxOpen({
			title : '无卡通道设置',
			href : rootPath + '/merchantsRate/alert.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	} 
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function merChantsRateSearchmerChants() {
		$('#merChantsRate').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsmerChants(params) {
		var appId=$("#appId").val();
		var aislecode=$("#aislecode").val();
         var isrepayment=$("#isrepayment").val();
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
			appId : appId,
			aislecode : aislecode,
            isrepayment : isrepayment

		}
	} 
	$('#merChantsRate').bootstrapTable({
		url : rootPath + '/merchantsRate/getList.shtml',
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
			field : 'mertype',
			title : '用户等级',
			align : 'center',
			valign : 'middle',
			formatter:function merStatFormatter(value) {
                if(value == "1"){
                    return "小咖";
                }
                if(value == "2"){
                    return "大咖";
                }
                if(value == "3"){
                    return "代理商";
                }
                if(value == "4"){
                    return "运营商";
                }
			}
		}, {
			field : 'aislecode',
			title : '通道名称',
			align : 'center',
			valign : 'middle',
			formatter:function merStatFormatter(value) {
				if(value == "rf"){
					return "银联快捷R";
				}
				/*if(value == "yb"){
					return "银联多商户";
				}*/
				if(value == "easy"){
					return "易生";
				}
				if(value == "zf01"){
					return "银联快捷Z";
				}
				if(value == "yb01"){
					return "银联快捷Y";
				}
				if(value == "xj"){
					return "银联快捷X";
				}
				/*if(value == "ld01"){
					return "落地通道L";
				}
				if(value == "ld02"){
					return "落地通道Y";
				}*/
				if(value == "hz"){
					return "银联快捷H";
				}
				if(value == "xjwk"){
					return "大额落地X";
				}
				if(value == "ld03"){
					return "落地通道X";
				}
				/*if(value == "hz01"){
					return "银联快捷H1";
				}*/
				if(value == "ld04"){
					return "落地还款X";
				}
				if(value == "cj"){
					return "大额快捷C";
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
				if(value == "kft"){
					return "大额快捷K";
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
                /*if(value == "ld16"){
                    return "小额落地C2";
                }
                if(value == "ld13"){
                    return "落地小额D";
                }*/
                if(value == "ld17"){
                    return "组合计划T";
                }
			}
		}, {
			field : 'aislecode',
			title : '网关',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'rate',
			title : '费率%',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value*100)).toFixed(3);
			}
		}, {
			field : 'd0fee',
			title : '笔数费（元/笔）',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
			field : 'appid',
			title : 'app编号',
			align : 'center',
			valign : 'middle',
		}, {
			field : 'appid',
			title : 'app名称',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "0000"){
					return "帐期机器人";
				}
			}
		}]
	});
        		  
</script>