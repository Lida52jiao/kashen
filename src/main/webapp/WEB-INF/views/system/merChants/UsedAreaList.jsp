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
			<form role="form" class="form-inline" id="usedAreaForm">
				<div class="input-group">
					<input type="text" placeholder="输入商户号" name="usedMerChantId" id="usedMerChantId"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
					<input type="text" placeholder="输入商户手机号" name="usedMerMp" id="usedMerMp"
						class="input form-control"> <span class="input-group-btn">
					</span>
				</div>
				<div class="input-group">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:usedAreaSearchForm();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
				<div class="input-group">
							<input class="btn btn btn-primary" id="usedAreaExcel" type="button" value="导出">
					</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="usedArea" data-toolbar="#toolbar"
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
	function usedAreaSearchForm() {
		$('#usedArea').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsUsedArea(params) {
		var usedMerChantId = $("#usedMerChantId").val();
		var usedMerMp = $("#usedMerMp").val();
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
			usedMerChantId : usedMerChantId,
			usedMerMp : usedMerMp
		}
	} 
	$('#usedArea').bootstrapTable({
		url : rootPath + '/MerChantsUsedArea/UsedAreaList.shtml',
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
		queryParams : queryParamsUsedArea,//参数处理函数
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
			field : 'proviceAreaName',
			title : '常住地(省)',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'cityAreaName',
			title : '常住地(市)',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'countryAreaName',
			title : '常住地(县)',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'createdTime',
			title : '实名时间',
			align : 'center',
			valign : 'middle',
			//formatter : enabledFormatter
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
        	$("#usedAreaExcel").click(function(){
        			$("#usedArea").tableExport({
        			    headings: true, 
        			    footers: true, 
        			    formats: "csv",
        			    fileName: "Excel商户常住地",
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