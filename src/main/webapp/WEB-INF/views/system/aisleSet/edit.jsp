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
                <div class="col-sm-8">
                    <input id="id" name="id"  class="form-control" type="hidden" value="${id } " >
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                    <span class="help-block m-b-none"></span>
                </div>
                <label class="col-sm-3 control-label">当前序号：</label>
                <div class="col-sm-8">
                    <input id="orderBy" name="orderBy"  class="form-control" type="text" value="${orderBy }" >
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                    <span class="help-block m-b-none"></span>
                </div>
                <label class="col-sm-3 control-label">通道名称：</label>
                <div class="col-sm-8">
                    <input id="aisleName" name="aisleName"  class="form-control" type="text" value="${aisleName }" >
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                    <span class="help-block m-b-none"></span>
                </div>
                <label class="col-sm-3 control-label">通道描述：</label>
                <div class="col-sm-8">
                    <textarea id="description" name="description"  class="form-control"  >${description }</textarea>
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                    <span class="help-block m-b-none"></span>
                </div>
                <label class="col-sm-3 control-label">支持银行：</label>
                <div class="col-sm-8">
                    <input id="remarks" name="remarks"  class="form-control" type="text" value="${remarks }" >
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
                    url: rootPath + "/AisleSet/editList.shtml",
                    data: $('#roleForcash').serializeArray(),
                    success: function(data){
                        if(data == "success") {
                            layer.confirm('设置成功', function(index) {
                                battcn.closeWindow();
                                $('#aislleTable').bootstrapTable('refresh');
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