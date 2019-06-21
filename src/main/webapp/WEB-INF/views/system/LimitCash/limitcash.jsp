<%--
  Created by IntelliJ IDEA.
  User: Dada
  Date: 2018/11/26
  Time: 16:16
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
            <form role="form" class="form-inline" id="limit">
                <div class="input-group">
                    <input type="text" placeholder="商户号" name="merchantId" id="merchantId"
                           class="input form-control"> <span class="input-group-btn"></span>
                </div>
                <%--<div class="input-group">--%>
                    <%--<input type="text" placeholder="商户姓名" name="merName" id="merName"--%>
                           <%--class="input form-control"> <span class="input-group-btn"></span>--%>
                <%--</div>--%>
                <%--<div class="input-group">--%>
                    <%--<input type="text" placeholder="商户电话" name="merMp" id="merMp"--%>
                           <%--class="input form-control"> <span class="input-group-btn"></span>--%>
                <%--</div>--%>
                <%--<div class="input-group">--%>
                    <%--<input type="text" placeholder="代理商号" name="agentId" id="agentId"--%>
                           <%--class="input form-control"> <span class="input-group-btn"></span>--%>
                <%--</div>--%>

                <div class="input-group">
                    <button type="button" class="btn btn btn-primary"
                            onclick="javascript:LimitSearchForm();">
                        <i class="fa fa-search"></i> 搜索
                    </button>
                </div>
            </form>
            <div class="table-responsive">
                <table id="limitList" data-toolbar="#toolbar"
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
    function LimitSearchForm() {
        $('#limitList').bootstrapTable('refresh');
    }
    function limitList() {
        return $.map($("#limitList").bootstrapTable('getSelections'), function(
                row) {
            return row.merchantId
        });
    }
    //添加
    function addlimit() {
        battcn.ajaxOpen({
            title : '添加到黑名单',
            href : rootPath + '/Numfee/addlimit.shtml',
            width : '40%',
            height : '80%',
            okhandler : function() {
                save();
            }
        });
    }
    //编辑
//    function editenter() {
//        var cbox = limitList();
//        if (cbox == "") {
//            layer.msg("请选择编辑项！！");
//            return;
//        }
//        if (cbox.length > 1) {
//            layer.msg("只能选中一个");
//            return;
//        }
//        battcn.ajaxOpen({
//            title : '编辑',
//            href : rootPath + '/Numfee/editenter.shtml?mercahntId=' + cbox,
//            width : '40%',
//            height : '80%',
//            okhandler : function() {
//                save();
//            }
//        });
//    }
    //删除
    function dellimit() {
        var cbox = limitList();
        if (cbox == "") {
            layer.msg("请选择删除项！！");
            return;
        }
        if (cbox.length > 1) {
            layer.msg("只能选中一个");
            return;
        }
        layer.confirm('是否删除？', function(index) {
            var url = rootPath + '/Numfee/dellimit.shtml?merchantId=' + cbox;
            var result = CommnUtil.ajax(url);
            if (result == "0000") {
                $('#limitList').bootstrapTable('refresh');
                layer.msg('删除成功');
            } else {
                layer.msg('删除失败');
            }
        });
    }
    /* //批量删除
     function dellimit() {
     var cbox = limitList();
     if (cbox == "") {
     layer.msg("请选择删除项！！");
     return;
     }
     layer.confirm('是否删除？', function(index) {
     var url = rootPath + '/enterShow/delenter.shtml';
     var result = CommnUtil.ajax(url, {ids : cbox.join(",")});
     if (result == "success") {
     $('#limitList').bootstrapTable('refresh');
     layer.msg('删除成功');
     } else {
     layer.msg('删除失败');
     }
     });
     } */
    //重写参数传递
    function queryBaseParamsmer(params) {
        var merchantId = $("#merchantId").val();
        var phone = $("#phone").val();
        var name = $("#name").val();
        var agentId = $("#agentId").val();
        var institutionId = $("#institutionId").val();
        var createTime = $("#createTime").val();
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
            merchantId : merchantId,
            name : name,
            phone : phone,
            agentId : agentId,
            institutionId : institutionId,
            appId : appId,
            createTime : createTime
        }
    }
    $('#limitList').bootstrapTable({
        url : rootPath + '/Numfee/findblack.shtml',
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
        idField : 'id',
        uniqueId : 'id',
        responseHandler : responseHandler, //处理分页函数
        queryParams : queryBaseParamsmer,//参数处理函数
        minimumCountColumns : 2,
        columns : [ {
            checkbox : true
        }, {
            field : 'merchantId',
            title : '商户号',
            align : 'center',
            valign : 'middle'
        }, {
            field : 'name',
            title : '姓名',
            align : 'center',
            valign : 'middle'
        },  {
            field : 'phone',
            title : '电话',
            align : 'center',
            valign : 'middle'
        },  {
            field : 'agentId',
            title : '代理商号',
            align : 'center',
            valign : 'middle'
        },  {
            field : 'institutionId',
            title : '机构号',
            align : 'center',
            valign : 'middle'
        },  {
            field : 'appId',
            title : 'APP',
            align : 'center',
            valign : 'middle'
        },  {
            field : 'createTime',
            title : '创建时间',
            align : 'center',
            valign : 'middle'
        }]
    });
</script>
