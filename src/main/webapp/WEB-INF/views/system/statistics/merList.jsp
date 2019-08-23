<%--
  Created by IntelliJ IDEA.
  User: Dada
  Date: 2019/1/10
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>
<div class="wrapper wrapper-content animated fadeInRight"
     style="height: 100%">
    <div class="ibox float-e-margins">

        <form role="form" class="form-inline" id="merCounts">
            <div class="form-group">
                <div class="col-sm-8">
                    <input type="text" placeholder="交易时间" name="startTime" id="startTime" class="Wdate"
                           onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
                    <span class="help-block m-b-none"></span>
                    <input type="text" placeholder="交易时间" name="finishTime" id="finishTime" class="Wdate"
                           onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
                    <span class="help-block m-b-none"></span>
                </div>
                <div class="col-sm-8">
                    <div class="input-group">
                        <select name="module" id="module" class="input form-control" >
                            <option value ="">请选择交易类型</option>
                            <option value ="repayment">还款</option>
                            <option value ="epos">无卡</option>
                            <option value ="alipay">支付宝</option>
                        </select>
                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="input-group">
                        <select name="aisleCode" id="aisleCode" class="input form-control">
                            <option value="">请选择通道</option>
                            <option value="yb">银联多商户</option>
                            <option value="ld04">落地还款X</option>
                            <option value="cj">大额快捷C</option>
                            <option value="ld05">落地还款T</option>
                            <option value="ld06">大额还款K</option>
                            <option value="ld07">落地大额H</option>
                            <option value="ld09">落地还款C</option>
                            <option value="ld11">落地还款NT</option>
                            <option value="kft">大额快捷K</option>
                            <option value="alipay">升级缴费</option>
                            <option value ="ld12">落地大额C</option>
                            <option value ="ld14">小额落地HB</option>
                            <option value ="ld15">落地大额C2</option>
                            <option value ="ld16">小额落地C2</option>
                            <option value ="ld13">落地小额D</option>
                            <option value ="ld17">组合计划T</option>
                        </select>
                    </div>
                </div>
                <div class="input-group">
                    <input type="text" placeholder="输入代理商号" name="agentId" id="agentId"
                           class="input form-control"> <span class="input-group-btn">
					</span>
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
            <table id="merCount" data-toolbar="#toolbar"
                   data-show-refresh="true" data-show-toggle="true"
                   data-show-columns="true" data-show-export="true"
                   data-show-footer="false" data-mobile-responsive="true">
            </table>
            <p style="font-size:25px">温馨提示:如不选择时间则默认显示前一天数据.</p>
        </div>

    </div>
</div>


<script type="text/javascript">

    //查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
    function agentSearchCountByArea() {
        $('#merCount').bootstrapTable('refresh');
    }
    function getUserIdSelectnews() {
        return $.map($("#merCount").bootstrapTable('getSelections'), function(
            row) {
            return row.id
        });
    }
    function editmerList() {
        var cbox = getUserIdSelectnews();
        if (cbox == "") {
            layer.msg("请选择删除项！！");
            return;
        }
        layer.confirm('是否删除？', function(index) {
            var url = "http://47.104.25.59:1183/sendMessage/deleteLists&id="+cbox;
            var result = CommnUtil.ajax(url);
            if (result == "success") {
                $('#news').bootstrapTable('refresh');
                layer.msg('删除成功');
            } else {
                layer.msg('删除失败');
            }
        });
    }
    function editmerList() {
        var cbox = getUserIdSelectnews();
        if (cbox == "") {
            layer.msg("请选择删除项！！");
            return;
        }
        layer.confirm('是否删除？', function(index) {
            var url = "http://47.104.25.59:1183/sendMessage/deleteLists&id="+cbox;
            var result = CommnUtil.ajax(url);
            if (result == "success") {
                $('#news').bootstrapTable('refresh');
                layer.msg('删除成功');
            } else {
                layer.msg('删除失败');
            }
        });
    }
    //重写参数传递
    function queryParamsNoCardCount(params) {
        var startTime = $("#startTime").val();
        var finishTime = $("#finishTime").val();
        var agentId = $("#agentId").val();
        var aisleCode = $("#aisleCode").val();
        var module = $("#module").val();
        var pageSize = params.limit;
        var sort = params.sort;
        var offset = params.offset;
        var order = params.order;
        var pageNum = offset / pageSize + 1;
        return {
            pageSize: pageSize,
            pageNum: pageNum,
            sort: sort,
            order: order,
            agentId: agentId,
            aisleCode: aisleCode,
            module: module,
            startTime: startTime,
            finishTime: finishTime
        }
    }

    $('#merCount').bootstrapTable({
        url: rootPath + '/Statistics/merCount.shtml',
        height: '100%',
        sortOrder: 'desc',
        ajaxOptions: {async: true, timeout: 50000},
        showColumns: true,
        showExport: true,
        striped: true,
        pagination: true,
        pageNumber: 1,
        pageSize: 10,
        pageList: "[10,20,30,50,100,200,All]",
        search: false,
        sidePagination: 'server',//服务端分页  client //客户端分页
        responseHandler: responseHandler, //处理分页函数
        queryParams: queryParamsNoCardCount,//参数处理函数
        minimumCountColumns: 2,
        columns: [
           {
            field: 'totalAmount',
            title: '总交易金额(元)',
            align: 'center',
            valign: 'middle',
            formatter: function (value) {
                return parseFloat((value / 100)).toFixed(2);
            }
        }, {
            field: 'totalFee',
            title: '总分润',
            align: 'center',
            valign: 'middle',
            formatter: function (value) {
                return parseFloat((value / 100)).toFixed(2);
            }
        }]
    });

</script>
