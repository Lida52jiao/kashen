<%--
  Created by IntelliJ IDEA.
  User: Dada
  Date: 2018/12/25
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <form class="form-horizontal m-t required-validate" id="roleForcash">
            <div class="form-group">
                <%--<label class="col-sm-3 control-label">ID：</label>
                <div class="col-sm-8">
                    <input id="id" name="id"  class="form-control" type="text" value="${id } "readonly = "readonly" >
                    <span class="help-block m-b-none"></span>
                </div>--%>
                    <input id="id" name="id"  class="form-control" type="hidden" value="${id}" >
                <%--<label class="col-sm-3 control-label">卡种：</label>
                <div class="col-sm-8">
                    <input id="wsName" name="wsName"  class="form-control" type="text" value="${wsName}" readonly = "readonly">
                    <span class="help-block m-b-none"></span>
                </div>--%>
                <label class="col-sm-3 control-label">申卡人返佣(%)：</label>
                <div class="col-sm-8">
                    <input id="self" name="self"  class="form-control" type="text" value="${self}" >
                    <span class="help-block m-b-none"></span>
                </div>
                <label class="col-sm-3 control-label">直推人返佣(%)：</label>
                <div class="col-sm-8">
                    <input id="oneMer" name="oneMer"  class="form-control" type="text" value="${oneMer}" >
                    <span class="help-block m-b-none"></span>
                </div>
                <label class="col-sm-3 control-label">间推人返佣(%)：</label>
                <div class="col-sm-8">
                    <input id="twoMer" name="twoMer"  class="form-control" type="text" value="${twoMer}" >
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                    <span class="help-block m-b-none"></span>
                </div>
                <label class="col-sm-3 control-label">直接代理返佣(%)：</label>
                <div class="col-sm-8">
                    <input id="oneAgent" name="oneAgent"  class="form-control" type="text" value="${oneAgent}" >
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                    <span class="help-block m-b-none"></span>
                </div>
                <label class="col-sm-3 control-label">间接代理返佣(%)：</label>
                <div class="col-sm-8">
                    <input id="towAgent" name="towAgent"  class="form-control" type="text" value="${towAgent}" >
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
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
            if($("#roleForcash").valid()){
                $.ajax({
                    type: "POST",
                    url: rootPath + "/WsConfig/edit.shtml",
                    data: $('#roleForcash').serializeArray(),
                    success: function(data){
                        if(data == "SUCCESS") {
                            layer.confirm('设置成功', function(index) {
                                battcn.closeWindow();
                                $('#redPocketTable').bootstrapTable('refresh');
                                return false;
                            });
                        }else if (data == "error"){
                            layer.confirm("总百分比不能超过80！", function(index) {
                                battcn.closeWindow();
                                $('#redPocketTable').bootstrapTable('refresh');
                                return false;
                            });
                        } else {
                            layer.confirm("配置有误请检查金额", function(index) {
                                battcn.closeWindow();
                                $('#redPocketTable').bootstrapTable('refresh');
                                return false;
                            });
                        }
                        battcn.toastrsAlert({
                            code: data.success ? 'success' :'error',
                            msg: data.success ? '成功' :'失败'
                        });
                    }
                });
            }
        }

    });
</script>