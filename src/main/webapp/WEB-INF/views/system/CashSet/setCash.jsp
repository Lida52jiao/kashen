<%--
  Created by IntelliJ IDEA.
  User: Dada
  Date: 2018/12/25
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="wrapper wrapper-content animated fadeInRight"
     style="height: 100%">
    <div class="ibox float-e-margins">
        <div class="ibox-content">
            <div class="doc-buttons">
                <c:forEach items="${res}" var="key">
                    ${key.description}
                </c:forEach>
            </div>
            <form role="form" class="form-inline" id="userSearchFormpwd">
                <div class="input-group">
						<button type="button" class="btn btn btn-primary"
                                onclick="javascript:agentSearchpwd();">
							<i class="fa fa-search"></i> 刷新
						</button>
					</span>
                </div>
            </form>
            <div class="table-responsive">
                <table id="setCash" data-toolbar="#toolbar"
                       data-show-refresh="true" data-show-toggle="true"
                       data-show-columns="true" data-show-export="true"
                       data-show-footer="false" data-mobile-responsive="true">
                </table>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    function ptSetCash() {
        battcn.ajaxOpen({
            title : '提现设置',
            href : rootPath + '/Transactional/editCash.shtml',
            width : '40%',
            height : '80%',
            okhandler : function() {
                save();
            }
        });
    }

    //查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
    function agentSearchpwd() {
        $('#setCash').bootstrapTable('refresh');
    }
    //重写参数传递
    function queryParamspwd(params) {
        var aliWithdrawFee = $("#aliWithdrawFee").val();
        var aliWithdrawMin = $("#aliWithdrawMin").val();
        var aliWithdrawMax = $("#aliWithdrawMax").val();
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
            aliWithdrawFee : aliWithdrawFee,
            aliWithdrawMin : aliWithdrawMin,
            aliWithdrawMax : aliWithdrawMax
        }
    }
    $('#setCash').bootstrapTable({
        url : rootPath + '/Transactional/findCash.shtml',
        height : '100%',
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
        queryParams : queryParamspwd,//参数处理函数
        minimumCountColumns : 2,
        columns : [ {
            field : 'aliWithdrawFee',
            title : '提现手续费(元)',
            align : 'center',
            valign : 'middle',
            formatter: function (value) {
                return value/100;
            }
        }, {
            field : 'aliWithdrawMin',
            title : '最小提现金额(元)',
            align : 'center',
            valign : 'middle',
            formatter: function (value) {
                return value/100;
            }
        }, {
            field : 'aliWithdrawMax',
            title : '最大提现金额(元)',
            align : 'center',
            valign : 'middle',
            formatter: function (value) {
                return value/100;
            }
        }]
    });
</script>