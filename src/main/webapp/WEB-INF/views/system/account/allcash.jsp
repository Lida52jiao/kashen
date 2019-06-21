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
        <div style="margin-left:26%">
            <p style="font-size:15px">当前余额:<span id="account" style="color:red;font-size:15px"></span>元</p>
        </div>
            <form role="form" class="form-inline" id="userSearchNoCardCount">
                <div class="input-group">
                    <input type="text" placeholder="输入姓名" name="merName" id="merName"
                           class="input form-control"> <span class="input-group-btn">
					</span>
                </div>
                <%--<div class="input-group">--%>
                    <%--<input type="text" placeholder="输入手机号" name="merMp" id="merMp"--%>
                           <%--class="input form-control"> <span class="input-group-btn">--%>
					<%--</span>--%>
                <%--</div>--%>
                <div class="input-group">
                    <input type="text" placeholder="输入订单号" name="orderNo" id="orderNo"
                           class="input form-control"> <span class="input-group-btn">
					</span>
                </div>
                <div class="input-group">
                    <input type="text" placeholder="输入代理商编号" name="agentId" id="agentId"
                           class="input form-control"> <span class="input-group-btn">
					</span>
                </div>
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
                    <select name="type" id="type" class="input form-control" >
                        <option value ="">请选择类型  </option>
                        <option value ="dx">短信</option>
                        <option value ="sys">银行卡鉴权</option>
                        <option value ="kcp">卡测评</option>
                    </select>
                    <span class="input-group-btn">
					</span>
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
                <p style="font-size:15px">消费汇总:<span id="amount" style="color:red;font-size:15px"></span>元</p>
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
                url: rootPath + '/Account/total.shtml?startTime='+startTime+'&finishTime='+finishTime,
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
        var merName = $("#merName").val();
        var agentId = $("#agentId").val();
        var orderNo = $("#orderNo").val();
        var type = $("#type").val();
        var amount = $("#amount").val();
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
            orderNo : orderNo,
            sort : sort,
            order : order,
            merName : merName,
            type : type,
            agentId : agentId,
            startTime : startTime,
            finishTime : finishTime,
            appId : appId
        }
    }
    $('#cashCount').bootstrapTable({
        url : rootPath + '/Account/findaccount.shtml',
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
            field : 'merName',
            title : '姓名',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'agentId',
            title : '代理商号',
            align : 'center',
            valign : 'middle'

        },{
            field : 'orderNo',
            title : '订单号',
            align : 'center',
            valign : 'middle'

        },{
            field : 'typeName',
            title : '类型',
            align : 'center',
            valign : 'middle'

        },{
            field : 'amount',
            title : '金额（元）',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return parseFloat((value/100)).toFixed(2);
            }
        },{
            field : 'appId',
            title : 'APP',
            align : 'center',
            valign : 'middle'

        },{
            field : 'createTime',
            title : '时间',
            align : 'center',
            valign : 'middle',
            formatter: function (value) {
                return changeDateFormat(value);
            }

        },{
            field : 'remark',
            title : '备注',
            align : 'center',
            valign : 'middle',
            formatter:function merStatFormatter(value) {
                if(value == "authentication"){
                    return '银行卡鉴权';
                }
                if(value == "login"){
                    return '登录短信';
                }
                if(value == "alterMobile"){
                    return '更改手机号短信';
                }
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

        $.ajax({
            url: rootPath + '/Account/atotal.shtml',
            type: 'POST',
            async: true,
            cache: false,
            contentType: "text/plain",
            /* dataType: "json",*/
            processData: false,
            success: function (data) {
                $("#account").html(data/100);
            },
            error: function (data) {
                alert("加载失败!请刷新");
            }
        });

    });
    $(function () {
        var startTime = $("#startTime").val();
        var finishTime = $("#finishTime").val();
        $.ajax({
            url: rootPath + '/Account/total.shtml?startTime='+startTime+'&finishTime='+finishTime,
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
