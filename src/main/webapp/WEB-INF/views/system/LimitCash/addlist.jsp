<%--
  Created by IntelliJ IDEA.
  User: Dada
  Date: 2018/11/27
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <form class="form-horizontal m-t required-validate" id="addlist">
            <div class="form-group">
                <label class="col-sm-3 control-label">商户号：</label>
                <div class="col-sm-8">
                    <input id="merchantId" name="merchantId"  class="form-control" type="text" value="">
                    <span class="help-block m-b-none"></span>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>

<script type="text/javascript">
    $(function(){
        save = function(obj) {
            var form = new FormData(document.getElementById("addlist"));
            if($("#addlist").valid()){
                $.ajax({
                    type: "POST",
                    url: rootPath + "/Numfee/add.shtml",
                    data: form,
                    contentType:false,
                    processData:false,
                    mimeType:"multipart/form-data",
                    success: function(data){
                        if(data == "0000") {
                            layer.confirm('保存成功!是否关闭窗口?', function(index) {
                                battcn.closeWindow();
                                $('#limitList').bootstrapTable('refresh');
                                return false;
                            })
                        }
                        else if(data == "8302") {
                            layer.confirm('本商户已在黑名单!', function(index) {
                                battcn.closeWindow();
                                $('#limitList').bootstrapTable('refresh');
                                return false;
                            });
                        }
                        else if(data == "8301"){
                            layer.confirm('找不到该商户!', function(index) {
                                battcn.closeWindow();
                                $('#limitList').bootstrapTable('refresh');
                                return false;
                            });
                        }
                    }
                });
            }
        }
    });
</script>