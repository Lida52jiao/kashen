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
            <div class="doc-buttons">
                <c:forEach items="${res}" var="key">
                    ${key.description}
                </c:forEach>
            </div>
            <form role="form" class="form-inline" id="userSearchFormmerChants">
                <div class="input-group">
                    <input type="text" placeholder="姓名" name="merName" id="merName"
                           class="input form-control"> <span class="input-group-btn">
					</span>
                </div>
                <div class="input-group">
                    <input type="text" placeholder="商户号" name="merChantId" id="merChantId"
                           class="input form-control"> <span class="input-group-btn">
					</span>
                </div>
                <div class="input-group">
                    <input type="text" placeholder="手机号" name="mobile" id="mobile"
                           class="input form-control"> <span class="input-group-btn">
					</span>
                </div>
                <div class="input-group">
                    <select name="state" id="state" class="input form-control">
                        <option value="">请选择状态</option>
                        <option value="1">成功</option>
                        <option value="2">待审核</option>
                    </select><span class="input-group-btn">
					</span>
                </div>
                <div class="input-group">
                    <select name="artificial" id="artificial" class="input form-control">
                        <option value="">请选择是否人工审核</option>
                        <option value="1">否</option>
                        <option value="2">是</option>
                    </select><span class="input-group-btn">
					</span>
                </div>
                <div class="input-group">
                    <input type="text" placeholder="开始时间" name="startTime" id="startTime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                    <span class="help-block m-b-none"></span>
                </div>
                <div class="input-group">
                    <input type="text" placeholder="结束时间" name="finshTime" id="finshTime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                    <span class="help-block m-b-none"></span>
                </div>
                <div class="input-group">
                    <button type="button" class="btn btn btn-primary"
                            onclick="javascript:agentSearchmerChants();">
                        <i class="fa fa-search"></i> 搜索
                    </button>
                    </span>
                </div>
            </form>
            <div class="table-responsive">
                <table id="merChants" data-toolbar="#toolbar"
                       data-show-refresh="true" data-show-toggle="true"
                       data-show-columns="true" data-show-export="true"
                       data-show-footer="false" data-mobile-responsive="true">
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function getUserIdSelectionsmerChants() {
        return $.map($("#merChants").bootstrapTable('getSelections'), function(
            row) {
            return row.id
        });
    }
    function getMerChantId() {
        return $.map($("#merChants").bootstrapTable('getSelections'), function(
            row) {
            return row.merChantId
        });
    }
    function getMerName() {
        return $.map($("#merChants").bootstrapTable('getSelections'), function(
            row) {
            return row.merName
        });
    }
    function check() {
        var merChantId = getMerChantId();
        if (merChantId == "") {
            layer.msg("请选择编辑项！！");
            return;
        }
        if (merChantId.length > 1) {
            layer.msg("只能选中一个");
            return;
        }
        battcn.ajaxOpen({
            title : '审核',
            href : rootPath + '/MerChants/check.shtml?merChantId='+merChantId,
            width : '40%',
            height : '80%',
            okhandler : function() {
                save();
            }
        });
    }
    function supplement() {
        var merChantId = getMerChantId();
        var merName = getMerName();
        if (merChantId == "") {
            layer.msg("请选择编辑项！！");
            return;
        }
        if (merChantId.length > 1) {
            layer.msg("只能选中一个");
            return;
        }
        battcn.ajaxOpen({
            title : '资料补充',
            href : rootPath + '/MerChants/supplement.shtml?merChantId='+merChantId+"&name="+merName,
            width : '40%',
            height : '80%',
            okhandler : function() {
                save();
            }
        });
    }
    //查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
    function agentSearchmerChants() {
        $('#merChants').bootstrapTable('refresh');
    }
    //重写参数传递
    function queryParamsmerChants(params) {
        var merChantId = $("#merChantId").val();
        var merName = $("#merName").val();
        var mobile = $("#mobile").val();
        var state = $("#state").val();
        var artificial = $("#artificial").val();
        console.info("state:"+state+";artificial:"+artificial)
        var startTime = $("#startTime").val();
        var finshTime = $("#finshTime").val();
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
            merChantId : merChantId,
            merName : merName,
            mobile : mobile,
            state : state,
            artificial : artificial,
            startTime : startTime,
            finshTime : finshTime
        }
    }
    $('#merChants').bootstrapTable({
        url : rootPath + '/MerChants/queryRealNameList.shtml',
        height : '100%',
        sortName : 'id',
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
        queryParams : queryParamsmerChants,//参数处理函数
        minimumCountColumns : 2,
        columns : [{
            checkbox : true
        }, {
            field : 'merName',
            title : '姓名',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'merChantId',
            title : '商户号',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'mobile',
            title : '手机号',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'state',
            title : '状态',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                if (value == 1) {
                    return "成功";
                }
                if (value == 2) {
                    return "待审核";
                }
            }
        }, {
            field : 'artificial',
            title : '是否人工审核',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                if (value == 1) {
                    return "否"
                }
                if (value == 2) {
                    return "是"
                }
            }
        }, {
            field : 'validity',
            title : '身份证号有效期',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'userIDCardA',
            title : '身份证正面',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return "<a href="+value+" target='_blank'>查看</a>"
            }
        }, {
            field : 'userIDCardB',
            title : '身份证反面',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return "<a href="+value+" target='_blank'>查看</a>"
            }
        }, {
            field : 'cardImgA',
            title : '银行卡正面',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return "<a href="+value+" target='_blank'>查看</a>"
            }
        }, {
            field : 'faceImg',
            title : '手持身份证照片',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return "<a href="+value+" target='_blank'>查看</a>"
            }
        }, {
            field : 'time',
            title : '提交时间',
            align : 'center',
            valign : 'middle'
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