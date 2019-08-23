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
            <form role="form" class="form-inline" id="interruptPlanListFrom">
                <div class="input-group">
                    <input type="text" placeholder="组合计划编号" name="groupId" id="groupId"
                           class="input form-control"> <span class="input-group-btn">
                            </span>
                </div>
                <%--<div class="input-group">
                    <input type="text" placeholder="中断时间" name="pId" id="pId"
                           class="input form-control"> <span class="input-group-btn">
                            </span>
                </div>--%>
                <div class="input-group">
                    <select name="state" id="state" class="input form-control">
                        <option value ="">请选择</option>
                        <option value ="1">中断未清算</option>
                        <option value ="2">清算中</option>
                        <option value ="3">清算成功</option>
                    </select>
                    <span class="input-group-btn"></span>
                </div>
                <div class="input-group">
                    <input type="text" placeholder="商户号" name="merchantId" id="merchantId"
                           class="input form-control"> <span class="input-group-btn">
                            </span>
                </div>
                <div class="input-group">
                    <button type="button" class="btn btn btn-primary"
                            onclick="javascript:agentSearchplan();">
                        <i class="fa fa-search"></i> 搜索
                    </button>
                    </span>
                </div>
                <br/>
                <div class="input-group">
                    <button type="button" class="btn btn btn-primary"
                            onclick="javascript:clearPlan();">
                        <i class="fa fa-search"></i> 清算
                    </button>
                    </span>
                </div>
            </form>
            <div class="table-responsive">
                <table id="interruptPlanListTable" data-toolbar="#toolbar"
                       data-show-refresh="true" data-show-toggle="true"
                       data-show-columns="true" data-show-export="true"
                       data-show-footer="false" data-mobile-responsive="true">
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function getState() {
        return $.map($("#interruptPlanListTable").bootstrapTable('getSelections'), function(
            row) {
            return row.state
        });
    }
    function getGroupId() {
        return $.map($("#interruptPlanListTable").bootstrapTable('getSelections'), function(
            row) {
            return row.groupId
        });
    }
    //清算
    function clearPlan() {
        var state = getState();
        var groupId = getGroupId();
        console.info("groupId"+groupId);
        if (groupId == "") {
            layer.msg("请选择数据！");
            return;
        }
        if (groupId.length > 1) {
            layer.msg("请选择一条数据！");
            return;
        }
        if (state != 1) {
            layer.msg("请选择状态为中断未清算！");
            return;
        }
        $.ajax({
            type: "POST",
            url: rootPath + "/Plan/clearPlan.shtml?groupId="+groupId,
            //解决编码问题
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            // contentType : "application/json",
            dataType: "json",
            success: function(data){
                console.info("data"+data);
                if(data.respCode == "0000"){
                    layer.confirm('清算成功!是否关闭窗口?', function(index) {
                        battcn.closeWindow();
                        $('#interruptPlanListTable').bootstrapTable('refresh');
                        return false;
                    });
                } else if (data.respCode == "3984") {
                    layer.confirm(data.respDesc+",请操作补状态功能处理!", function(index) {
                        battcn.closeWindow();
                        $('#interruptPlanListTable').bootstrapTable('refresh');
                        return false;
                    });
                } else {
                    layer.confirm(data.respDesc, function(index) {
                        battcn.closeWindow();
                        $('#interruptPlanListTable').bootstrapTable('refresh');
                        return false;
                    });
                }
                battcn.toastrsAlert({
                    code: data.success ? 'success' :'error',
                    msg: data.success ? '成功' :'失败'
                });
            }
        });
    }
    function agentSearchplan() {
        $('#interruptPlanListTable').bootstrapTable('refresh');
    }
    function queryInterruptPlan(params) {
        var groupId = $("#groupId").val();
        var state = $("#state").val();
        var merchantId = $("#merchantId").val();
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
            merchantId : merchantId,
            state:state,
            groupId:groupId
        }
    }
    $('#interruptPlanListTable').bootstrapTable({
        url : rootPath + '/Plan/queryInterruptPlanList.shtml',
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
        queryParams : queryInterruptPlan,//参数处理函数
        minimumCountColumns : 2,
        columns : [{
            checkbox : true
        }, {
            field : 'groupId',
            title : '组合计划编号',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'createTime',
            title : '中断时间',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'state',
            title : '状态',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                if(value == "1"){
                    return '中断未清算';
                }
                if(value == "2"){
                    return '清算中';
                }
                if(value == "3"){
                    return '清算成功';
                }
            }
        }, {
            field : 'merchantId',
            title : '商户号',
            align : 'center',
            valign : 'middle'
        }]
    })
</script>