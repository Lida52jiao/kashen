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
                <label class="col-sm-3 control-label">平台名称：</label>
                <div class="col-sm-8">
                    <input id="institutionName" name="institutionName"  class="form-control" type="text" value="${CashSet.institutionName }" readonly="readonly">
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                    <span class="help-block m-b-none">
                </div>
                <label class="col-sm-3 control-label">手续费设置：</label>
                <div class="col-sm-8">
                    <input id="aliWithdrawFee" name="aliWithdrawFee"  class="form-control" type="text" value="${CashSet.aliWithdrawFee }" >
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                    <span class="help-block m-b-none">
                </div>
                <label class="col-sm-3 control-label">最小提现金额：</label>
                <div class="col-sm-8">
                    <input id="aliWithdrawMin" name="aliWithdrawMin"  class="form-control" type="text" value="${CashSet.aliWithdrawMin }" >
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                    <span class="help-block m-b-none">
                </div>
                <label class="col-sm-3 control-label">最大提现金额：</label>
                <div class="col-sm-8">
                    <input id="aliWithdrawMax" name="aliWithdrawMax"  class="form-control" type="text" value="${CashSet.aliWithdrawMax }" >
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                    <span class="help-block m-b-none">
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
                    url: rootPath + "/Transactional/setCash.shtml",
                    data: $('#roleForcash').serializeArray(),
                    success: function(data){
                        if(data == "success") {
                            layer.confirm('设置成功', function(index) {
                                battcn.closeWindow();
                                $('#setCash').bootstrapTable('refresh');
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