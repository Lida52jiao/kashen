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
			<form role="form" class="form-inline" id="repayRecordSearchForm">
			 	<%--<div class="input-group">--%>
			 		<%--<input type="text" placeholder="输入卡种" name="wsName" id="wsName" class="input form-control">--%>
			 	<%--</div>--%>
					<button type="button" class="btn btn btn-primary"
						onclick="javascript:repaySearch();">
						<i class="fa fa-search"></i> 刷新
					</button>
				<div class="input-group">
						<input class="btn btn btn-primary" id="payRecordExcel" type="button" value="导出">
				</div>
			</form>
			<div class="table-responsive">
				<table id="redPocketTable" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
    function getId() {
        return $.map($("#redPocketTable").bootstrapTable('getSelections'), function(
            row) {
            return row.id
        });
    }
    function getself() {
        return $.map($("#redPocketTable").bootstrapTable('getSelections'), function(
            row) {
            return row.self*100
        });
    }
    function searchoneMer() {
        return $.map($("#redPocketTable").bootstrapTable('getSelections'), function(
            row) {
            return row.oneMer*100
        });
    }
    function searchtwoMer() {
        return $.map($("#redPocketTable").bootstrapTable('getSelections'), function(
            row) {
            return row.twoMer*100
        });
    }
    function searchoneAgent() {
        return $.map($("#redPocketTable").bootstrapTable('getSelections'), function(
            row) {
            return row.oneAgent*100
        });
    }
    function searchtwoAgent() {
        return $.map($("#redPocketTable").bootstrapTable('getSelections'), function(
            row) {
            return row.towAgent*100
        });
    }
    function searchwsName() {
        return $.map($("#redPocketTable").bootstrapTable('getSelections'), function(
            row) {
            return row.wsName
        });
    }

    function editWsCongfig() {
        var id= getId();
        var self= getself();
        var oneMer= searchoneMer();
        var twoMer= searchtwoMer();
        var oneAgent = searchoneAgent();
        var towAgent = searchtwoAgent();
        var  wsName= searchwsName();
        if (id == "") {
            layer.msg("请选择编辑项！！");
            return;
        }
        if (id.length > 1) {
            layer.msg("只能选中一个");
            return;
        }
        battcn.ajaxOpen({
            title : 'app通道设置',
            href : rootPath + '/WsConfig/getEdit.shtml?id='+id+"&self="+self+"&oneMer="+oneMer+"&twoMer="+twoMer+"&oneAgent="+oneAgent+"&towAgent="+towAgent+"&wsName="+wsName,
            width : '40%',
            height : '80%',
            okhandler : function() {
                save();
            }
        });
    }

	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	 function repaySearch() {
		$('#redPocketTable').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsRepayRecord(params) {
		var wsName = $("#wsName").val();
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
            wsName : wsName
			// startTime : startTime,
			// finishTime : finishTime
		}
	} 
	$('#redPocketTable').bootstrapTable({
		url : rootPath + '/WsConfig/list.shtml',
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
		queryParams : queryParamsRepayRecord,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		},{
            field : 'id',
            title : 'ID',
            visible: false
        }, /*{
			field : 'wsName',
			title : '卡种',
			align : 'center',
			valign : 'middle'
		},*/ {
			field : 'self',
			title : '申卡人返佣(%)',
			align : 'center',
			valign : 'middle',
            formatter:function (value) {
                return parseFloat((value*100));
            }
		}, {
			field : 'oneMer',
			title : '直推人返佣(%)',
			align : 'center',
			valign : 'middle',
            formatter:function (value) {
                return parseFloat((value*100));
            }
		}, {
			field : 'twoMer',
			title : '间推人返佣(%)',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value*100));
			}
		}, {
			field : 'oneAgent',
			title : '直接代理返佣(%)',
			align : 'center',
			valign : 'middle',
			formatter:function (value) {
				return parseFloat((value*100));
			}
		}, {
			field : 'towAgent',
			title : '间接代理返佣(%)',
			align : 'center',
			valign : 'middle',
            formatter:function (value) {
                return parseFloat((value*100));
            }
		}, {
			field : 'createTime',
			title : '修改时间',
			align : 'center',
			valign : 'middle'
		}]
	});
	$("#payRecordExcel").click(function(){
		$("#redPocketTable").tableExport({
		    headings: true, 
		    footers: true, 
		    formats: "csv",
		    fileName: "Excel红包提现表",
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