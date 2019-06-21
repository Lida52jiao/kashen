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
                        <button type="button" class="btn btn btn-primary"
                                onclick="javascript:agentSearchpwd();">
                            <i class="fa fa-search"></i> 搜索
                        </button>
                    </div>
            </form>
            <div class="table-responsive">
                <table id="aislleTable" data-toolbar="#toolbar"
                       data-show-refresh="true" data-show-toggle="true"
                       data-show-columns="true" data-show-export="true"
                       data-show-footer="false" data-mobile-responsive="true">
                </table>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    function searchAisle() {
        return $.map($("#aislleTable").bootstrapTable('getSelections'), function(
            row) {
            return row.id
        });
    }

    function ptaislleTable() {
        var cbox = searchAisle();
        if (cbox == "") {
            layer.msg("请选择编辑项！！");
            return;
        }
        if (cbox.length > 1) {
            layer.msg("只能选中一个");
            return;
        }
        battcn.ajaxOpen({
            title : 'app通道设置',
            href : rootPath + '/AisleSet/edit.shtml?id='+cbox,
            width : '40%',
            height : '80%',
            okhandler : function() {
                save();
            }
        });
    }

    //查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
    function agentSearchpwd() {
        $('#aislleTable').bootstrapTable('refresh');
    }
    //重写参数传递
    function queryParamspwd(params) {
        var appId = $("#appId").val();
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
            appId : appId
        }
    }
    $('#aislleTable').bootstrapTable({
        url : rootPath + '/AisleSet/getList.shtml',
        height : '100%',
        sortName : 'orderBy',
        sortOrder : 'desc',
        ajaxOptions: {async:true,timeout:50000},
        showColumns : true,
        showExport : true,
        striped : true,
        pagination : true,
        pageNumber : 1,
        pageSize : 20,
        pageList : "[10,20,30,All]",
        search : false,
        sidePagination : 'server',//服务端分页  client //客户端分页
        responseHandler : responseHandler, //处理分页函数
        queryParams : queryParamspwd,//参数处理函数
        minimumCountColumns : 2,
        columns : [ {
            checkbox : true
        },{
            field : 'id',
            title : 'ID',
            visible: false
        },{
            field : 'orderBy',
            title : '通道序号',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'appId',
            title : 'APP名称',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                if(value == "0000"){
                    return "帐期机器人";
                }
            }
        }, {
            field : 'aisleName',
            title : '通道名称',
            align : 'center',
            valign : 'middle'
        },{
            field : 'description',
            title : '通道描述',
            align : 'center',
            valign : 'middle'
        },{
            field : 'remarks',
            title : '支持银行',
            align : 'center',
            valign : 'middle'
        }]
    });
</script>