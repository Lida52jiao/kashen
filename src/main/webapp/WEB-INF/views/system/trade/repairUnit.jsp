<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: N
  Date: 2019/8/20
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="ibox float-e-margins">
    <div class="ibox-content">
        <form class="form-horizontal m-t required-validate" id="from1">
            <div class="form-group">
                <input id="merchantId" name="merchantId" class="form-control" type="hidden" value="${merchantId}"/>
                <input id="cardNo" name="cardNo" class="form-control" type="hidden" value="${cardNo}"/>
                <input id="statementDate" name="statementDate" class="form-control" type="hidden" value="${cardInfo.statementdate}"/>
                <input id="repaymentDate" name="repaymentDate" class="form-control" type="hidden" value="${cardInfo.repaymentdate}"/>
                <input id="order" name="order" class="form-control" type="hidden" value="${orderNo}"/>
                <div class="form-group">
                    <label class="col-sm-3 control-label">单元Id：</label>
                    <div class="col-sm-8">
                        <input id="cycleId" name="cycleId" class="form-control" type="text" value="${cycleId}" disabled="true"/>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">还款订单号：</label>
                    <div class="col-sm-8">
                        <input id="orderNo" name="orderNo" class="form-control" type="text" value="${orderNo}" disabled="true"/>
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">请选择补单元执行时间：</label>
                    <div class="col-sm-8">
                        <select id="timestamp" name="timestamp" class="input form-control" onblur="findTime()">
                            <option value ="">请选择</option>
                            <c:forEach items = "${unitTimeList}" var = "key">
                                <option value = "${key}">${key}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">请选择时间段：</label>
                    <label class="col-sm-3 control-label">
                        <input type="radio" id="indexId1" name="indexTime" value="1"/>09:00~12:00
                        <input type="radio" id="indexId2" name="indexTime" value="2"/>12:00~15:00
                        <br/>
                        <input type="radio" id="indexId3" name="indexTime" value="3"/>15:00~18:00
                        <input type="radio" id="indexId4" name="indexTime" value="4"/>18:00~21:00
                    </label>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    /*$(function(){
        alert("页面加载完成！");
    });*/
    $(document).ready(function(){
        var statementDate = $("#statementDate").val();
        var repaymentDate = $("#repaymentDate").val();
        var now = new Date();
        var date = now.getDate();//得到日期
        console.info("date:"+date);
        if (parseInt(statementDate) > parseInt(repaymentDate)) {
            if (date < parseInt(statementDate) && date > parseInt(repaymentDate)) {
                layer.confirm("不允许补单元!!", function () {
                    battcn.closeWindow();
                });
            }
        } else {
            if (date < parseInt(statementDate) || date > parseInt(repaymentDate)) {
                layer.confirm("不允许补单元！", function () {
                    battcn.closeWindow();
                });
            }
        }
    });
    function findTime(){
        $("#indexId1").prop("checked", false);
        $("#indexId2").prop("checked", false);
        $("#indexId3").prop("checked", false);
        $("#indexId4").prop("checked", false);
        var merchantId = $("#merchantId").val();
        var timestamp = $("#timestamp").val();
        if (timestamp == '') {
            return;
        }
        console.info("timestamp:"+timestamp);
        var time = new Date(timestamp + " 00:00:00");
        var timestamp = time.getTime();
        var timestamps = time.getTime();
        var cardNo = $("#cardNo").val();
        /* 查可补单元时间
         * http://47.104.161.254:1003/ld17/order/findTime
         *  timestamp   补单元时间
         *  merchantId  商户号
         *  cardNo      卡号
         *  pwd         yjkj123
         */
        var timestamp = $("#timestamp").val();
        var time = new Date(timestamp + " 00:00:00");
        var timestamp = time.getTime();
        var today = new Date();
        var year = today.getFullYear();
        var month = today.getMonth() + 1;
        var day = today.getDate();
        var newDay = year + "-" + month + "-" +day;
        var ymd = newDay + " 00:00:00";
        console.info("ymd:"+ymd);
        var timestamp1 = new Date(ymd).getTime();
        console.info(timestamp +"===="+ timestamp1);
        if (parseInt(timestamp) == timestamp1) {
            var timestamp = today.getTime();
            var nine = newDay + " 09:00:00";
            var twelve = newDay + " 12:00:00";
            var fifteen = newDay + " 15:00:00";
            var eighteen = newDay + " 18:00:00";
            var nineTime = new Date(nine).getTime();
            var twelveTime = new Date(twelve).getTime();
            var fifteenTime = new Date(fifteen).getTime();
            var eighteenTime = new Date(eighteen).getTime();
            if (parseInt(timestamp) > eighteenTime) {
                console.info("eighteenTime"+eighteenTime);
                $("#indexId1").attr('disabled',true);
                $("#indexId2").attr('disabled',true);
                $("#indexId3").attr('disabled',true);
                $("#indexId4").attr('disabled',true);
            } else if (timestamp > fifteenTime) {
                console.info("fifteenTime"+fifteenTime);
                $("#indexId1").attr('disabled',true);
                $("#indexId2").attr('disabled',true);
                $("#indexId3").attr('disabled',true);
            } else if (timestamp > twelveTime) {
                console.info("twelveTime"+twelveTime);
                $("#indexId1").attr('disabled',true);
                $("#indexId2").attr('disabled',true);
            } else if (timestamp > nineTime) {
                console.info("nineTime"+nineTime);
                $("#indexId1").attr('disabled',true);
            }
        }
        $.ajax({
            type: "POST",
            url: "http://47.104.161.254:1003/ld17/order/findTime?timestamp="+timestamp+"&merchantId="+merchantId+"&cardNo="+cardNo+"&pwd=yjkj123",
            //解决编码问题
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function(data){
                if(data.respCode == "0000") {
                    console.info(timestamps +"++++++"+ timestamp1);
                    if (parseInt(timestamps) == timestamp1) {
                        var timestamp = today.getTime();
                        var nine = newDay + " 09:00:00";
                        var twelve = newDay + " 12:00:00";
                        var fifteen = newDay + " 15:00:00";
                        var eighteen = newDay + " 18:00:00";
                        var nineTime = new Date(nine).getTime();
                        var twelveTime = new Date(twelve).getTime();
                        var fifteenTime = new Date(fifteen).getTime();
                        var eighteenTime = new Date(eighteen).getTime();
                        if (parseInt(timestamp) > eighteenTime) {

                        } else if (timestamp > fifteenTime) {
                            if (data.data.four == "0") {
                                $("#indexId4").attr('disabled', false);
                            }
                        } else if (timestamp > twelveTime) {
                            if (data.data.three == "0") {
                                $("#indexId3").attr('disabled', false);
                            }
                            if (data.data.four == "0") {
                                $("#indexId4").attr('disabled', false);
                            }
                        } else if (timestamp > nineTime) {
                            if (data.data.two == "0") {
                                $("#indexId2").attr('disabled',false);
                            }
                            if (data.data.three == "0") {
                                $("#indexId3").attr('disabled', false);
                            }
                            if (data.data.four == "0") {
                                $("#indexId4").attr('disabled', false);
                            }
                        }
                    } else {
                        if (data.data.one == "0") {
                            console.info("one");
                            $("#indexId1").attr('disabled',false);
                        }
                        if (data.data.two == "0") {
                            console.info("two");
                            $("#indexId2").attr('disabled',false);
                        }
                        if (data.data.three == "0") {
                            console.info("three");
                            $("#indexId3").attr('disabled', false);
                        }
                        if (data.data.four == "0") {
                            console.info("four");
                            $("#indexId4").attr('disabled', false);
                        }
                    }
                } else {
                    layer.confirm("系统异常！", function () {
                        battcn.closeWindow();
                    })
                }
                battcn.toastrsAlert({
                    code: data.success ? 'success' :'error',
                    msg: data.success ? '成功' :'失败'
                });
            }
        });
    }
    /* 补单元
     * http://47.104.161.254:1003/ld17/order/anewCycle
     * orderNo      订单号
     * indexTime    补单元时间段 value(1 2 3 4)
     * timestamp    补单元时间  默认当天
     * pwd          yjkj123
     */
    $(function() {
        save = function () {
            var orderNo = $("#order").val();
            var timestamp = $("#timestamp").val();
            var obj = document.getElementsByName("indexTime");
            for (var i = 0; i < obj.length; i++) {
                if (obj[i].checked) {
                    var indexTime = obj[i].value;
                }
            }
            if (timestamp == "") {
                layer.msg("请选择补单执行时间！");
                return;
            }
            var time = new Date(timestamp+" 00:00:00");
            var timestamp = time.getTime();
            console.info(timestamp+"timestamp");
            console.info("orderNo:"+orderNo);
            console.info("timestamp:"+timestamp);
            console.info("indexTime:"+indexTime);
            if (indexTime == undefined) {
                layer.msg("请选择时间段！");
                return;
            }
            $.ajax({
                type: "POST",
                url: "http://47.104.161.254:1003/ld17/order/anewCycle?orderNo="+orderNo+"&timestamp="+timestamp+"&indexTime="+indexTime+"&pwd=yjkj123",
                success: function(data){
                    if (data.respCode == "0000") {
                        layer.confirm("操作成功！", function () {
                            battcn.closeWindow();
                        })
                    } else {
                        layer.confirm(data.respDesc, function () {
                            battcn.closeWindow();
                        })
                    }
                }
            })
        }
    });
</script>