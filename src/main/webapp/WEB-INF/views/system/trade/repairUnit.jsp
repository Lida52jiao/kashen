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
                    <label class="control-label" id="selBox">
                        <div id="id1" class="div" selData="1">09:00~12:00</div>
                        <div id="id2" class="div" selData="2">12:00~15:00</div>
                        <div id="id3" class="div" selData="3">15:00~18:00</div>
                        <div id="id4" class="div" selData="4">18:00~21:00</div>
                    </label>
                </div>
            </div>
        </form>
    </div>
</div>
<style type="text/css">
    .div{
        color: #636363;
        border: 1px solid #636363;
        width: 100px;
        height: 30px;
        text-align: center;
        float: left;
        line-height: 30px;
        margin-left: 30px;
    }
    .nosel{
        color: #dcdcdc;
        border: 1px solid #dcdcdc;
    }
    .sel{
        border: 1px solid #217aff;
        color: #3a74ba;
    }
</style>
<script type="text/javascript">
    var selVal = "";
    var selBoxList = $("#selBox div");
    selBoxList.click(function () {
        if(!$(this).hasClass('nosel')){
            $(this).addClass('sel').siblings().removeClass("sel");
            selVal = $(this).attr("selData");

        }
    });
    $(document).ready(function(){
        var statementDate = $("#statementDate").val();
        var repaymentDate = $("#repaymentDate").val();
        var now = new Date();
        var date = now.getDate();//得到日期
        console.info("date:"+date);
        if (parseInt(statementDate) > parseInt(repaymentDate)) {
            if (date < parseInt(statementDate) && date > parseInt(repaymentDate)) {
                layer.confirm("不允许补单元!!",{btn: ["确认"]}, function () {
                    battcn.closeWindow();
                });
            }
        } else {
            if (date < parseInt(statementDate) || date > parseInt(repaymentDate)) {
                layer.confirm("不允许补单元！",{btn: ["确认"]}, function () {
                    battcn.closeWindow();
                });
            }
        }
    });
    function findTime(){
        selVal = "";
        $.each(selBoxList,function (){
            $(this).removeClass("sel");
            $(this).removeClass("nosel");
        });
        var merchantId = $("#merchantId").val();
        var timestamp = $("#timestamp").val();
        if (timestamp == '') {
            return;
        }
        console.info("timestamp:"+timestamp)
        var time = new Date(timestamp + " 00:00:00");
        var timestamp = time.getTime();
        var timestamps = time.getTime();
        var cardNo = $("#cardNo").val();
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
                $.each(selBoxList,function () {
                    $(this).addClass("nosel");
                })
            } else if (timestamp > fifteenTime) {
                $("#id1").addClass("nosel")
                $("#id2").addClass("nosel")
                $("#id3").addClass("nosel")
            } else if (timestamp > twelveTime) {
                $("#id1").addClass("nosel");
                $("#id2").addClass("nosel");
            } else if (timestamp > nineTime) {
                $("#id1").addClass("nosel");
            }
        }
        $.ajax({
            /* 查可补单元时间
             * http://47.104.161.254:1003/ld17/order/findTime
             *  timestamp   补单元时间
             *  merchantId  商户号
             *  cardNo      卡号
             *  pwd         yjkj123
             */
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
                                $("#id4").removeClass("nosel");
                            }
                        } else if (timestamp > twelveTime) {
                            if (data.data.three == "0") {
                                $("#id3").removeClass("nosel");
                            }
                            if (data.data.four == "0") {
                                $("#id4").removeClass("nosel");
                            }
                        } else if (timestamp > nineTime) {
                            if (data.data.two == "0") {
                                $("#id2").removeClass("nosel");
                            }
                            if (data.data.three == "0") {
                                $("#id3").removeClass("nosel");
                            }
                            if (data.data.four == "0") {
                                $("#id4").removeClass("nosel");
                            }
                        }
                    } else {
                        if (data.data.one == "0") {
                            $("#id1").removeClass("nosel");
                        }
                        if (data.data.two == "0") {
                            $("#id2").removeClass("nosel");
                        }
                        if (data.data.three == "0") {
                            $("#id3").removeClass("nosel");
                        }
                        if (data.data.four == "0") {
                            $("#id4").removeClass("nosel");
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
            if (timestamp == "") {
                layer.msg("请选择补单执行时间！");
                return;
            }
            var time = new Date(timestamp+" 00:00:00");
            var timestamp = time.getTime();
            if (selVal == "") {
                layer.msg("请选择时间段！");
                return;
            }
            $.ajax({
                type: "POST",
                url: "http://47.104.161.254:1003/ld17/order/anewCycle?orderNo="+orderNo+"&timestamp="+timestamp+"&indexTime="+selVal+"&pwd=yjkj123",
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