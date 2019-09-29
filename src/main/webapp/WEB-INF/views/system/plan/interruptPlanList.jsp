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
                           class="input form-control">
                    <span class="input-group-btn"></span>
                </div>
                <div class="input-group">
                    <input type="text" placeholder="商户号" name="merchantId" id="merchantId"
                           class="input form-control">
                    <span class="input-group-btn"></span>
                </div>
                <div class="input-group">
                    <select name="executeState" id="executeState" class="input form-control">
                        <option value ="">请选择执行状态</option>
                        <option value ="1">执行中</option>
                        <option value ="2">中断</option>
                    </select>
                    <span class="input-group-btn"></span>
                </div>
                <div class="input-group">
                    <select name="clearState" id="clearState" class="input form-control">
                        <option value ="">请选择清算状态</option>
                        <option value ="1">未清算</option>
                        <option value ="2">清算中</option>
                        <option value ="3">清算成功</option>
                    </select>
                    <span class="input-group-btn"></span>
                </div>
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" placeholder="组合计划创建开始时间" name="starttime" id="startTime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" placeholder="组合计划创建结束时间" name="finishtime" id="finishTime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="input-group">
                    <select name="appId" id="appId" class="input form-control" >
                        <option value ="">请选择app  </option>
                        <c:forEach items="${app}" var="key">
                            <option value ="${key.appId}">${key.appName}  </option>
                        </c:forEach>
                    </select>
                    <span class="input-group-btn"></span>
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
    function getGroupId() {
        return $.map($("#interruptPlanListTable").bootstrapTable('getSelections'), function(
            row) {
            return row.groupId
        });
    }
    function getExecuteState() {
        return $.map($("#interruptPlanListTable").bootstrapTable('getSelections'), function(
            row) {
            return row.executeState
        });
    }
    function getClearState() {
        return $.map($("#interruptPlanListTable").bootstrapTable('getSelections'), function(
            row) {
            return row.clearState
        });
    }
    function getAisleCode() {
        return $.map($("#interruptPlanListTable").bootstrapTable('getSelections'), function(
            row) {
            return row.aisleCode
        });
    }
    //清算
    function clearPlan() {
        var executeState = getExecuteState();
        var clearState = getClearState();
        var groupId = getGroupId();
        var aisleCode = getAisleCode();
        console.info("groupId"+groupId);
        console.info("clearState"+clearState);
        console.info("executeState"+executeState);
        console.info("aisleCode"+aisleCode);
        if (groupId == "") {
            layer.msg("请选择数据！");
            return;
        }
        if (groupId.length > 1) {
            layer.msg("请选择一条数据！");
            return;
        }
        if (executeState != 2) {
            layer.msg("请选择状态为中断！");
            return;
        }
        if (clearState != 1) {
            layer.msg("请选择状态为未清算！");
            return;
        }
        $.ajax({
            type: "POST",
            url: "http://47.104.161.254:1003/"+aisleCode+"/planMultiCard/clear?groupId="+groupId,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function(data){
                if(data.respCode == "0000"){
                    layer.confirm('清算成功!是否关闭窗口?', function(index) {
                        battcn.closeWindow();
                        $('#interruptPlanListTable').bootstrapTable('refresh');
                        return false;
                    });
                } else {
                    layer.confirm('清算失败!'+data.respDesc, function(index) {
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
        })
    }
    function agentSearchplan() {
        $('#interruptPlanListTable').bootstrapTable('refresh');
    }
    function queryInterruptPlan(params) {
        var groupId = $("#groupId").val();
        var merchantId = $("#merchantId").val();
        var executeState = $("#executeState").val();
        var clearState = $("#clearState").val();
        var appId = $("#appId").val();
        var startTime = $("#startTime").val();
        var finishTime = $("#finishTime").val();
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
            groupId : groupId,
            merchantId : merchantId,
            executeState:executeState,
            clearState:clearState,
            appId:appId,
            startTime:startTime,
            finishTime:finishTime
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
            field : 'merchantId',
            title : '商户号',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'executeState',
            title : '组合计划执行状态',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                if(value == "1"){
                    return '执行中';
                }
                if(value == "2"){
                    return '中断';
                }
            }
        }, {
            field : 'clearState',
            title : '组合计划清算状态',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                if(value == "1"){
                    return '未清算';
                }
                if(value == "2"){
                    return '清算中';
                }
                if(value == "3"){
                    return '清算成功';
                }
            }
        }, {
            field : 'totalRepAmount',
            title : '总还款金额',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return value/100;
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
            field : 'appId',
            title : '归属app',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                if (value == "0000") {
                    return "卡神";
                }
            }
        }, {
            field : 'createTime',
            title : '中断时间',
            align : 'center',
            valign : 'middle'
        }]
    })
</script>