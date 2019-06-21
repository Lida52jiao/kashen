<%--
  Created by IntelliJ IDEA.
  User: Dada
  Date: 2018/11/28
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
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

            <form role="form" class="form-inline" id="userSearchNoCardCount">
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" placeholder="交易开始时间" name="startTime" id="startTime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
                        <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" placeholder="交易结束时间" name="finishTime" id="finishTime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
                        <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="input-group">
                    <button type="button" class="btn btn btn-primary"
                            onclick="javascript:agentSearchCountByArea();">
                        <i class="fa fa-search"></i> 搜索
                    </button>
                    </span>
                </div>
            </form>
            <div class="table-responsive">
                <table id="cashCount" data-toolbar="#toolbar"
                       data-show-refresh="true" data-show-toggle="true"
                       data-show-columns="true" data-show-export="true"
                       data-show-footer="false" data-mobile-responsive="true">
                </table>
                <p style="font-size:25px">总金额为:<span id="amount" style="color:red;font-size:25px"></span></p>
            </div>

    </div>
</div>


<script type="text/javascript">

    //查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
    function agentSearchCountByArea() {
        $('#cashCount').bootstrapTable('refresh');
        $(function () {
            var startTime = $("#startTime").val();
            var finishTime = $("#finishTime").val();
            $.ajax({
                url: rootPath + '/Transactional/total.shtml?startTime='+startTime+'&finishTime='+finishTime,
                type: 'POST',
                async: true,
                cache: false,
                contentType: "text/plain",
                /* dataType: "json",*/
                processData: false,
                success: function (data) {
                    $("#amount").html(data/100);
                },
                error: function (data) {
                    alert("总金额加载失败!请刷新");
                }
            });
        });
    }
    //重写参数传递
    function queryParamsNoCardCount(params) {
        var startTime = $("#startTime").val();
        var finishTime = $("#finishTime").val();
        var merchantId = $("#merchantId").val();
        var createTime = $("#createTime").val();
        var totalFee = $("#totalFee").val();
        var pageSize = params.limit;
        var sort = params.sort;
        var offset = params.offset;
        var order = params.order;
        var pageNum = offset / pageSize + 1;
        return {
            pageSize : pageSize,
            pageNum : pageNum,
            merchantId : merchantId,
            sort : sort,
            order : order,
            startTime : startTime,
            finishTime : finishTime,
            createTime : createTime,
            totalFee : totalFee
        }
    }
    $('#cashCount').bootstrapTable({
        url : rootPath + '/Transactional/countbyArea.shtml',
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
        responseHandler : responseHandler, //处理分页函数
        queryParams : queryParamsNoCardCount,//参数处理函数
        minimumCountColumns : 2,
        columns : [{
            field : 'createTime',
            title : '创建时间',
            align : 'center',
            valign : 'middle',
             formatter:function (value) {
             return (value.substring(0,19));
             }
        }, {
            field : 'fee',
            title : '分润金额（元）',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return parseFloat((value/100)).toFixed(2);
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
    $(function () {
        var startTime = $("#startTime").val();
        var finishTime = $("#finishTime").val();
        $.ajax({
            url: rootPath + '/Transactional/total.shtml?startTime='+startTime+'&finishTime='+finishTime,
            type: 'POST',
            async: true,
            cache: false,
            contentType: "text/plain",
            /* dataType: "json",*/
            processData: false,
            success: function (data) {
                $("#amount").html(data/100);
            },
            error: function (data) {
                alert("总金额加载失败!请刷新");
            }
        });
    });

</script>
