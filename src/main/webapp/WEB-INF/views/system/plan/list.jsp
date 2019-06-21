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
			<form role="form" class="form-inline" id="userSearchPlan">
				<div class="input-group">
					<input type="text" placeholder="输入计划编号" name="pId" id="pId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入商户号" name="merchantId" id="merchantId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="state" id="state" class="input form-control">
	  					<option value ="">请计划状态</option>
	  					<option value ="1">待执行</option>
	  					<option value ="2">执行中</option>
	  					<option value ="4">失败</option>
	  					<option value ="6">完成</option>
	  					</select>
					<span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<select name="aisleCode" id="aisleCode" class="input form-control" >
						<option value ="">请选择通道  </option>
		                        <c:forEach items="${aisleCode}" var="key">
							 		<c:if test="${key.aislecode=='ld01'}">
									<option value ="${key.aislecode}">落地通道L  </option>
									</c:if>
									<c:if test="${key.aislecode=='ld02'}">
										<option value ="${key.aislecode}">落地通道Y  </option>
									</c:if>
									<c:if test="${key.aislecode=='ld03'}">
									<option value ="${key.aislecode}">落地通道X  </option>
									</c:if>
									<c:if test="${key.aislecode=='ld04'}">
									<option value ="${key.aislecode}">落地还款X  </option>
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
										<option value ="${key.aislecode}">落地还款NT  </option>
									</c:if>
									<c:if test="${key.aislecode=='ld12'}">
										<option value ="${key.aislecode}">落地大额C  </option>
									</c:if>
									<c:if test="${key.aislecode=='ld14'}">
										<option value ="${key.aislecode}">小额落地HB </option>
									</c:if>
									<c:if test="${key.aislecode=='ld15'}">
										<option value ="${key.aislecode}">落地大额C2 </option>
									</c:if>
									<c:if test="${key.aislecode=='ld16'}">
										<option value ="${key.aislecode}">小额落地C2 </option>
									</c:if>
								</c:forEach>
	  					</select>
					 <span class="input-group-btn">
					</span>
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
				<div class="input-group">
					<select name="isLd" id="isLd" class="input form-control" >
						<option value ="">请选择消费类型  </option>
							 <option value ="0">线上消费  </option>
							 <option value ="1">落地消费  </option>
	  					</select>
					 <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入代理商号" name="agentId" id="agentId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入卡号" name="cardNo" id="cardNo"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入手机号" name="phone" id="phone"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div><br>
				<div class="form-group">
				<div class="col-sm-8">
					<input type="text" placeholder="计划执行时间" name="starttime" id="starttimes" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-8">
					<input type="text" placeholder="计划执行时间" name="finishtime" id="finishtimes" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchplan();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
				<div class="input-group">
							<input class="btn btn btn-primary" id="planExcel" type="button" value="导出">
					</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="plan" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	function getPlanIdSelectionsplan() {
		return $.map($("#plan").bootstrapTable('getSelections'), function(
				row) {
			return row.pId
		});
	}
	function checkPlans() {
		var cbox = getPlanIdSelectionsplan();
		if (cbox == "") {
			layer.msg("请选择查看项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		battcn.ajaxOpen({
			title : '查询计划详情',
			href : rootPath + '/Plan/lists.shtml?planId=' + cbox,
			width : '90%',
			height : '90%',
			okhandler : function() {
				save();
			}
		});
	} 
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchplan() {
		$('#plan').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsplan(params) {
		var pId = $("#pId").val();
		var isLd = $("#isLd").val();
		var merchantId = $("#merchantId").val();
		var state = $("#state").val();
		var appId = $("#appId").val();
		var agentId = $("#agentId").val();
		var cardNo = $("#cardNo").val();
		var phone = $("#phone").val();
		var starttime = $("#starttimes").val();
		var finishtime = $("#finishtimes").val();
		var aisleCode = $("#aisleCode").val();
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
			pId : pId,
			merchantId : merchantId,
			state:state,
			appId : appId,
			agentId:agentId,
			cardNo:cardNo,
			phone:phone,
			starttime : starttime,
			finishtime : finishtime,
			isLd : isLd,
			aisleCode : aisleCode
		}
	} 
	$('#plan').bootstrapTable({
		url : rootPath + '/Plan/findPlanByAll.shtml',
		height : '100%',
		sortName : 'createTime',
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
		queryParams : queryParamsplan,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'pId',
			title : '计划编号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merchantId',
			title : '商户号',
			align : 'center',
			valign : 'middle'
		},{
			field : 'isLd',
			title : '消费类型',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "0"){
					return '线上消费';
				}
				if(value == "1"){
					return '落地消费';
				}
			}
		},{
			field : 'aisleCode',
			title : '通道标识',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == "ld01"){
					return '落地通道L';
				}
				if(value == "ld02"){
					return '落地通道Y';
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
		},{
			field : 'province',
			title : '落地省份',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == null){
					return '此通道不支持落地';
				} else {
					return value;
				}
			}
		},{
			field : 'city',
			title : '落地城市',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				if(value == null){
					return '此通道不支持落地';
				} else {
					return value;
				}
			}
		}, {
			field : 'agentId',
			title : '归属代理商号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'name',
			title : '姓名',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'phone',
			title : '手机号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'cardNo',
			title : '卡号',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'bankCode',
			title : '发卡行',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
					if(value == "ICBC"){
						return '工商银行';
					}
					if(value == "ABC"){
						return '农业银行';
					}
					if(value == "BOC"){
						return '中国银行';
					}
					if(value == "CCB"){
						return '建设银行';
					}
					if(value == "CMBCHINA"){
						return '招商银行';
					}
					if(value == "POST"){
						return '邮政储蓄';
					}
					if(value == "ECITIC"){
						return '中信银行';
					}
					if(value == "CEB"){
						return '光大银行';
					}
					if(value == "BOCO"){
						return '交通银行';
					}
					if(value == "CIB"){
						return '兴业银行';
					}
					if(value == "CMBC"){
						return '民生银行';
					}
					if(value == "PINGAN"){
						return '平安银行';
					}
					if(value == "CGB"){
						return '广发银行';
					}
					if(value == "BCCB"){
						return '北京银行';
					}
					if(value == "HXB"){
						return '华夏银行';
					}
					if(value == "SPDB"){
						return '浦发银行';
					}
					if(value == "SHB"){
						return '上海银行';
					}
				}
		}, {
			field : 'totalFee',
			title : '总手续费',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return value/100;
			}
		}, {
            field : 'alreadyAmount',
            title : '已还金额',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return value/100;
            }
        }, {
            field : 'basicAmount',
            title : '未还金额',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return value/100;
            }
        }, {
            field : 'totalAmount',
            title : '总金额',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return value/100;
            }
        }, {
			field : 'totalDay',
			title : '总天数',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'number',
			title : '还款笔数',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'state',
			title : '状态',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
					if(value == "1"){
						return '待执行';
					}
					if(value == "2"){
						return '执行中';
					}
					if(value == "3"){
						return '成功';
					}
					if(value == "4"){
						return '失败';
					}
					if(value == "5"){
						return '交易中';
					}
					if(value == "6"){
						return '已完成';
					}
				}
		}, {
			field : 'createTime',
			title : '计划创建时间',
			align : 'center',
			valign : 'middle',
			formatter: function (value) {
        		return value.substring(0,19);
    		}
		}
		, {
			field : 'startTime',
			title : '计划开始时间',
			align : 'center',
			valign : 'middle',
			sortable: true,
            //——修改——获取日期列的值进行转换
             formatter: function (value, row, index) {
                    return changeDateFormat(value);
                } 
		}
		, {
			field : 'finishTime',
			title : '计划终止时间',
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
    	$("#planExcel").click(function(){
			$("#plan").tableExport({
			    headings: true, 
			    footers: true, 
			    formats: "csv",
			    fileName: "Excel查询计划表",
			    bootstrap: false,
			    position: "bottom",
			    ignoreRows: null,
			    ignoreCols: null
			}); 
			/* Comma Separated Values (.csv) */
			$.fn.tableExport.csv = {
			    defaultClass: "csv",
			    buttonContent: "Export to csv",
			    separator: ",",
			    mimeType: "application/csv",
			    fileExtension: ".csv"
			};
			/* default charset encoding (UTF-8) */
			$.fn.tableExport.charset = "charset=utf-8";
			  
			/* default filename if "id" attribute is set and undefined */
			$.fn.tableExport.defaultFileName = "myDownload";
			  
			/* default class to style buttons when not using bootstrap  */
			$.fn.tableExport.defaultButton = "button-default";
			  
			/* bootstrap classes used to style and position the export buttons */
			$.fn.tableExport.bootstrap = ["btn", "btn-default", "btn-toolbar"];
			  
			/* row delimeter used in all filetypes */
			$.fn.tableExport.rowDel = "\r\n";   
		});
</script>