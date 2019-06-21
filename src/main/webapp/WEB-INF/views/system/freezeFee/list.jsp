<%--
  Created by IntelliJ IDEA.
  User: Dada
  Date: 2019/1/24
  Time: 16:56
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
        <div class="ibox-content">
            <div class="doc-buttons">
                <c:forEach items="${res}" var="key">
                    ${key.description}
                </c:forEach>
            </div>
            <form role="form" class="form-inline" id="userSearchrepayCount">
                <c:if test="${type=='1'}">
                    <div class="input-group">
                        <input type="text" placeholder="输入代理商编号" name="agentId" id="agentId"
                               class="input form-control"> <span class="input-group-btn">
						</span>
                    </div>
                </c:if>
                <div class="input-group">
                    <select name="aislecode" id="aislecode" class="input form-control" >
                        <option value ="">请选择通道  </option>
                        <option value ="ld01">落地通道L</option>
                        <option value ="ld02">落地通道Y</option>
                        <option value ="ld04">落地还款X</option>
                        <option value ="ld05">落地还款T</option>
                        <option value ="ld06">大额还款K</option>
                        <option value ="ld07">落地大额H</option>
                        <option value ="ld09">落地还款C</option>
                        <option value ="ld11">落地还款NT</option>
                        <option value ="ld12">落地大额C</option>
                        <option value ="ld14">小额落地HB</option>
                        <option value ="ld15">落地大额C2</option>
                        <option value ="ld16">小额落地C2</option>
                    </select>
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
                </div><br>

                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" placeholder="交易开始时间" name="starttime" id="starttime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
                        <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-8">
                        <input type="text" placeholder="交易结束时间" name="finishtime" id="finishtime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
                        <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="input-group">
                    <button type="button" class="btn btn btn-primary"
                            onclick="javascript:agentSearchrepayCount();">
                        <i class="fa fa-search"></i> 搜索
                    </button>
                    </span>
                </div>
            </form>
            <div class="table-responsive">
                <table id="repayCount" data-toolbar="#toolbar"
                       data-show-refresh="true" data-show-toggle="true"
                       data-show-columns="true" data-show-export="true"
                       data-show-footer="false" data-mobile-responsive="true">
                </table>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

    //查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
    function agentSearchrepayCount() {
        $('#repayCount').bootstrapTable('refresh');
        $(function(){
            alert("数据量过大功能维护中，恢复时间另行通知。");
        });
    }
    //重写参数传递
    function queryParamsrepayCount(params) {
        var agentId = $("#agentId").val();
        var merchantId = $("#merchantId").val();
        var type = $("#types").val();
        var starttime = $("#starttime").val();
        var finishtime = $("#finishtime").val();
        var aislecode = $("#aislecode").val();
        var appId = $("#appId").val();
        var isLd = $("#isLd").val();
        var payType = $("#payType").val();
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
            agentId : agentId,
            merchantId : merchantId,
            type : type,
            starttime : starttime,
            finishtime : finishtime,
            aislecode : aislecode,
            appId : appId,
            isLd : isLd,
            payType : payType
        }
    }
    $('#repayCount').bootstrapTable({
        url : rootPath + '/Transactional/rePayCount.shtml',
        height : '100%',
        sortName : 'tlId',
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
        queryParams : queryParamsrepayCount,//参数处理函数
        minimumCountColumns : 2,
        columns : [ {
            checkbox : true
        }, {
            field : 'totalAmount',
            title : '总交易金额（元）',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return parseFloat((value/100)).toFixed(2);
            }
        }, {
            field : 'totalPayFee',
            title : '总手续费（元）',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return parseFloat((value/100)).toFixed(2);
            }
        }, {
            field : 'totalInstitutionFee',
            title : '我的分润（元）',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return parseFloat((value/100)).toFixed(2);
            }
        }, {
            field : 'totalNoInstitutionFee',
            title : '下属分润（元）',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return parseFloat((value/100)).toFixed(2);
            }
        }]
    });

</script>