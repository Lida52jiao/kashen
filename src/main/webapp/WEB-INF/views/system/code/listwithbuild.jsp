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
			<form role="form" class="form-inline" id="userSearchFormbuild">
				<div class="input-group">
					<!-- <input type="text" placeholder="输入代理商号" name="merId" id="merId"
						class="input form-control">  -->
						<select name="status" id="status" class="input form-control">
	  					<option value ="">请选择状态</option>
	  					<option value ="Y">使用</option>
	  					<option value ="N">未使用</option>
	  					</select>
						<span class="input-group-btn">
						<button type="button" class="btn btn btn-primary"
							onclick="javascript:agentSearchbuild();">
							<i class="fa fa-search"></i> 搜索
						</button>
					</span>
				</div>
			</form>
			<div class="table-responsive">
				<table id="build" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	/* function getUserIdSelectionsbuild() {
		return $.map($("#build").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function editAgentbuild() {
		var cbox = getUserIdSelectionsbuild();
		if (cbox == "") {
			layer.msg("请选择编辑项！！");
			return;
		}
		if (cbox.length > 1) {
			layer.msg("只能选中一个");
			return;
		}
		battcn.ajaxOpen({
			title : '激活码划拨',
			href : rootPath + '/Code/editUIcode.shtml?id=' + cbox,
			width : '40%',
			height : '80%',
			okhandler : function() {
				save();
			}
		});
	} */
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchbuild() {
		$('#build').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsbuild(params) {
		var status = $("#status").val();
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
			status : status
		}
	} 
	$('#build').bootstrapTable({
		url : rootPath + '/Code/getCodeList.shtml',
		height : '100%',
		sortName : 'codeId',
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
		queryParams : queryParamsbuild,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'activationCode',
			title : '激活码',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'status',
			title : '是否使用',
			align : 'center',
			valign : 'middle',
			formatter:function activationCodeFormatter(value) {
					return value == "Y" ? '使用' : '未使用';
				}
		}, {
			field : 'creatDate',
			title : '生成的时间',
			align : 'center',
			valign : 'middle',
			sortable: true,
            //——修改——获取日期列的值进行转换
             formatter: function (value, row, index) {
                    return changeDateFormat(value);
                } 
		}, {
			field : 'actDate',
			title : '激活时间',
			align : 'center',
			valign : 'middle',
			sortable: true,
            //——修改——获取日期列的值进行转换
             formatter: function (value, row, index) {
                    return changeDateFormat(value);
                } 
		}, {
			field : 'merName',
			title : '使用者姓名',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'merMp',
			title : '使用者手机',
			align : 'center',
			valign : 'middle'
		}]
	});
	//修改——转换日期格式(时间戳转换为datetime格式)
        function changeDateFormat(cellval) {
            /* if (cellval != null) {
                var date = new Date(parseInt(cellval.replace("/Date(", "").replace(")/", ""), 10));
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                return date.getFullYear() + "-" + month + "-" + currentDate; 
            } */
            if(cellval != "" && cellval != null){
             //1.获取时间戳前十位数字
                unittime = cellval.replace("/Date(", "").replace(")/", "");
                var shijian = unittime.substr(0, unittime.length - 3);


                //2.把获取的数据*1000
                shijian = shijian * 1000;
                //3.转换成js时间格式
                var jsDate = new Date(shijian);
                var UnixTimeToDate = jsDate.getFullYear() + '-' + (jsDate.getMonth() + 1) + '-' + jsDate.getDate()
                + ' ' + jsDate.getHours() + ':' + jsDate.getMinutes();
               // + ' ' + jsDate.getHours() + ':' + jsDate.getMinutes() + ':' + jsDate.getSeconds();
                return UnixTimeToDate;
            } 
        }
</script>