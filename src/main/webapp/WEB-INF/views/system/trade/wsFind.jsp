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
            <form role="form" class="form-inline" id="wsFindListFrom">
                <div class="input-group">
                    <input type="text" placeholder="订单号" name="orderNo" id="orderNo"
                           class="input form-control">
                    <span class="input-group-btn"></span>
                </div>
                <div class="input-group">
                    <input type="text" placeholder="申请人姓名" name="name" id="name"
                           class="input form-control">
                    <span class="input-group-btn"></span>
                </div>
                <div class="input-group">
                    <input type="text" placeholder="申请人商户号" name="merchantId" id="merchantId"
                           class="input form-control">
                    <span class="input-group-btn"></span>
                </div>
                <div class="input-group">
                    <input type="text" placeholder="代理商号" name="agentId" id="agentId"
                           class="input form-control">
                    <span class="input-group-btn"></span>
                </div>
                <div class="input-group">
                    <input type="text" placeholder="手机号" name="phone" id="phone"
                           class="input form-control">
                    <span class="input-group-btn"></span>
                </div>
                <%--<div class="input-group">
                    <select name="state" id="state" class="input form-control">
                        <option value ="">是否返回结果</option>
                        <option value ="1">等待</option>
                        <option value ="3">成功</option>
                    </select>
                </div>--%>
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" placeholder="申请时间开始时间" name="startTime" id="startTime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" placeholder="申请时间结束时间" name="finishTime" id="finishTime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="input-group">
                    <button type="button" class="btn btn btn-primary"
                            onclick="javascript:agentSearchplan();">
                        <i class="fa fa-search"></i> 搜索
                    </button>
                    </span>
                </div>
            </form>
            <div class="table-responsive">
                <table id="wsFindList" data-toolbar="#toolbar"
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
        return $.map($("#wsFindList").bootstrapTable('getSelections'), function(
            row) {
            return row.groupId
        });
    }
    function agentSearchplan() {
        $('#wsFindList').bootstrapTable('refresh');
    }
    function queryWsFindList(params) {
        var startTime = $("#startTime").val();
        var finishTime = $("#finishTime").val();
        var orderNo = $("#orderNo").val();
        var merchantId = $("#merchantId").val();
        var agentId = $("#agentId").val();
        var name = $("#name").val();
        var phone = $("#phone").val();
        var state = $("#state").val();
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
            startTime:startTime,
            finishTime:finishTime,
            orderNo:orderNo,
            merchantId:merchantId,
            agentId:agentId,
            name:name,
            phone:phone,
            state:state
        }
    }
    $('#wsFindList').bootstrapTable({
        url : rootPath + '/Trade/queryWsFindList.shtml',
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
        queryParams : queryWsFindList,//参数处理函数
        minimumCountColumns : 2,
        columns : [{
            checkbox : true
        }, {
            field : 'orderNo',
            title : '订单号',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'name',
            title : '申请人名称',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'merchantId',
            title : '申请人商户号',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'agentId',
            title : '代理商号',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'phone',
            title : '手机号',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'state',
            title : '是否返回申请结果',
            align : 'center',
            valign : 'middle',
            formatter:function (val) {
                if (val == "1") {
                    return "等待"
                }
                if (val == "3") {
                    return "成功"
                }
            }
        }, {
            field : 'createTime',
            title : '申请时间',
            align : 'center',
            valign : 'middle'
        }]
    })
</script>