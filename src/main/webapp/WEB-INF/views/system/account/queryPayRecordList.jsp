<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="wrapper wrapper-content animated fadeInRight"
     style="height: 100%">
    <div class="ibox float-e-margins">
        <div class="ibox-content">
            <form role="form" class="form-inline" id="from">
                <input id="merId" name="merId" class="form-control" type="hidden" value="${u.merId}" >
            </form>
            <div class="table-responsive">
                <table id="queryPayRecordList" data-toolbar="#toolbar"
                       data-show-refresh="true" data-show-toggle="true"
                       data-show-columns="true" data-show-export="true"
                       data-show-footer="false" data-mobile-responsive="true">
                </table>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>
<script type="text/javascript">
    //重写参数传递
    function queryBaseParamsmer(params) {
        var pageSize = params.limit;
        var sort = params.sort;
        var offset = params.offset;
        var order = params.order;
        var pageNum = offset / pageSize + 1;
        return {
            pageSize : pageSize,
            pageNum : pageNum,
            sort : sort,
            order : order
        }
    }
    var merId=$("#merId").val();
    $('#queryPayRecordList').bootstrapTable({
        url : rootPath + '/Account/queryPayRecordList.shtml?institutionId='+merId,
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
        search : true,
        sidePagination : 'server',//服务端分页  client //客户端分页
        idField : 'id',
        uniqueId : 'id',
        responseHandler : responseHandler, //处理分页函数
        queryParams : queryBaseParamsmer,//参数处理函数
        minimumCountColumns : 2,
        columns : [ {
            checkbox : false
        }, {
            field : 'name',
            title : '名称',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'phone',
            title : '手机号',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'variableAmount',
            title : '充值金额',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return value/100;
            }
        }, {
            field : 'createTime',
            title : '创建时间',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return changeDateFormat(value);
            }
        }, {
            field : 'amount',
            title : '金额',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return value/100;
            }
        }, {
            field : 'orderNo',
            title : '订单号',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'state',
            title : '状态',
            align : 'center',
            valign : 'middle',
            formatter:function merStatFormatter(value) {
                if(value == "1"){
                    return "成功";
                }
                if(value == "2"){
                    return "失败";
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
</script>
