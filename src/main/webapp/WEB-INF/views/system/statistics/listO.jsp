<%--
  Created by IntelliJ IDEA.
  User: Dada
  Date: 2019/1/10
  Time: 15:25
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

        <form role="form" class="form-inline" id="CountO">
            <div class="form-group">
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
                <div class="col-sm-8">
                    <input type="text" placeholder="交易时间" name="startTime" id="startTime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
                    <span class="help-block m-b-none"></span>
                    <input type="text" placeholder="交易时间" name="finishTime" id="finishTime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
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
                </div></div>
                <div class="col-sm-8">
                <div class="input-group">
                    <select name="aisleCode" id="aisleCode" class="input form-control" >
                        <option value ="">请选择通道  </option>
                        <option value ="yb">银联多商户</option>
                        <option value ="ld04">落地还款X</option>
                        <option value ="cj">大额快捷C</option>
                        <option value ="ld05">落地还款T</option>
                        <option value ="ld06">大额还款K</option>
                        <option value ="ld07">落地大额H</option>
                        <option value ="ld09">落地还款C</option>
                        <option value ="ld11">落地还款NT</option>
                        <option value ="kft">大额快捷K</option>
                        <option value ="alipay">升级缴费</option>
                        <option value ="ld12">落地大额C</option>
                        <option value ="ld14">小额落地HB</option>
                        <option value ="ld15">落地大额C2</option>
                        <option value ="ld16">小额落地C2</option>
                        <option value ="ld13">落地小额D</option>
                        <option value ="ld17">组合计划T</option>
                    </select>
                </div></div>

            </div>
            <%--<div class="form-group">--%>
                <%--<div class="col-sm-8">--%>
                    <%--<input type="text" placeholder="交易结束时间" name="finishTime" id="finishTime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">--%>
                    <%--<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->--%>
                    <%--<span class="help-block m-b-none"></span>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="input-group">
                <button type="button" class="btn btn btn-primary"
                        onclick="javascript:agentSearchCountByArea();">
                    <i class="fa fa-search"></i> 搜索
                </button>
                </span>
            </div>
            <div class="input-group">
                <input class="btn btn btn-primary"  id="T1Excel" type="button" value="导出">
                </span>
            </div>
        </form>
        <div class="table-responsive">
            <table id="cashCountO" data-toolbar="#toolbar"
                   data-show-refresh="true" data-show-toggle="true"
                   data-show-columns="true" data-show-export="true"
                   data-show-footer="false" data-mobile-responsive="true">
            </table>
            <%--<p style="font-size:25px">所选时间范围内无卡提现费利润:<span id="amount" style="color:red;font-size:25px"></span>元</p>--%>
        </div>

    </div>
</div>


<script type="text/javascript">

    //查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
    function agentSearchCountByArea() {
        $('#cashCountO').bootstrapTable('refresh');
    }
    //重写参数传递
    function queryParamsNoCardCount(params) {
        var startTime = $("#startTime").val();
        var finishTime = $("#finishTime").val();
        var module = $("#module").val();
        var appId = $("#appId").val();
        var aisleCode = $("#aisleCode").val();
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
            module : module,
            appId : appId,
            aisleCode : aisleCode,
            startTime : startTime,
            finishTime : finishTime
        }
    }
    $('#cashCountO').bootstrapTable({
        url : rootPath + '/Statistics/totalO.shtml',
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
        pageList : "[10,20,30,50,100,200,All]",
        search : false,
        sidePagination : 'server',//服务端分页  client //客户端分页
        responseHandler : responseHandler, //处理分页函数
        queryParams : queryParamsNoCardCount,//参数处理函数
        minimumCountColumns : 2,
        columns : [{
            checkbox : true
        },{
            field : 'module',
            title : '交易类型',
            align : 'center',
            valign : 'middle',
            formatter:function moduleFormatter(value) {
                if(value === "repayment"){
                    return '还款';
                }
                if(value === "epos"){
                    return '无卡';
                }
                if(value === "alipay"){
                    return "支付宝";
                }
            }
        }, {
            field : 'aisleCode',
            title : '通道名称',
            align : 'center',
            valign : 'middle',
            formatter:function aisleCodeFormatter(value) {
                if(value === "rf"){
                    return "银联快捷R";
                }
                if(value === "yb"){
                    return "银联多商户";
                }
                if(value === "easy"){
                    return "易生";
                }
                if(value === "zf01"){
                    return "银联快捷Z";
                }
                if(value === "yb01"){
                    return "银联快捷Y";
                }
                if(value === "xj"){
                    return "银联快捷X";
                }
                if(value === "hz"){
                    return "银联快捷H";
                }
                if(value === "xjwk"){
                    return "大额落地X";
                }
                if(value === "ld04"){
                    return "落地还款X";
                }
                if(value === "cj"){
                    return "大额快捷C";
                }
                if(value === "ld05"){
                    return "落地还款T";
                }
                if(value === "ld06"){
                    return "大额还款K";
                }
                if(value === "kft"){
                    return "大额快捷K";
                }
                if(value === "ld07"){
                    return "落地大额H";
                }
                if(value === "ld09"){
                    return "落地还款C";
                }
                if(value == "ld11"){
                    return "落地还款NT";
                }
                if(value === "alipay"){
                    return "升级缴费";
                }
                if(value == "ld12"){
                    return "落地大额C";
                }
                if(value == "ld14"){
                    return "小额落地HB";
                }
                if(value == "ld15"){
                    return "落地大额C2";
                }
                if(value == "ld16"){
                    return "小额落地C2";
                }
                if(value == "ld13"){
                    return "落地小额D";
                }
                if(value == "ld17"){
                    return "组合计划T";
                }
            }
        },{
            field : 'totalAmount',
            title : '交易金额(元)',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return parseFloat((value/100)).toFixed(2);
            }
        },{
            field : 'appFee',
            title : 'O单商分润',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return parseFloat((value/100)).toFixed(2);
            }
        },{
            field : 'notAppFee',
            title : 'O单商下发分润',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return parseFloat((value/100)).toFixed(2);
            }
        },{
            field : 'appId',
            title : '归属App',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                if(value == "0000"){
                    return '帐期机器人';
                }
                if(value == "0001"){
                    return '卡无优';
                }
                if(value == "0002"){
                    return '信诚管家';
                }
                if(value == "0003"){
                    return '智宝';
                }
                if(value == "0004"){
                    return '金猪管家';
                }
                if(value == "0005"){
                    return '易信管家';
                }
                if(value == "0006"){
                    return '盛达管家';
                }
            }
        }, {
            field : 'countDate',
            title : '交易时间',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return (value.substring(0,19));
            }
        },{
            field : 'createTime',
            title : '生成时间',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return (value.substring(0,19));
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


    // function T1Excel(){
    //     var startTime = $("#startTime").val();
    //     var finishTime = $("#finishTime").val();
    //     var module = $("#module").val();
    //     var aisleCode = $("#aisleCode").val();
    //     window.location.href=rootPath + '/excel/T1Out.shtml?startTime='+startTime+'&finishTime='+finishTime+'&module='+module+'&aisleCode='+aisleCode;
    //
    // }

    $("#T1Excel").click(function(){
        $("#cashCountO").tableExport({
            headings: true,
            footers: true,
            formats: "csv",
            fileName: "T1报表",
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
