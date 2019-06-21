<%--
  Created by IntelliJ IDEA.
  User: Dada
  Date: 2019/1/24
  Time: 19:45
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
                <label class="col-sm-3 control-label">商户号：</label>
                <div class="col-sm-8">
                    <input id="merchantId" name="merchantId"  class="form-control" type="text" value="${mer.merChantId }" readonly="readonly">
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                    <span class="help-block m-b-none"></span>
                </div>
                <label class="col-sm-3 control-label">当前返佣账户余额：</label>
                <div class="col-sm-8">
                    <input id="balanceProfit" name="balanceProfit"  class="form-control" type="text" value="${mer.balanceProfit }" readonly="readonly">
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                    <span class="help-block m-b-none"></span>
                </div>
                <label class="col-sm-3 control-label">已冻结余额：</label>
                <div class="col-sm-8">
                    <input id="balanceProfitFrozen" name="balanceProfitFrozen"  class="form-control" type="text" value="${mer.balanceProfitFrozen }" readonly="readonly">
                    <!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
                    <span class="help-block m-b-none"></span>
                </div>
                <label class="col-sm-3 control-label">冻结余额：</label>
                <div class="col-sm-8">
                    <input id="amount" name="amount"  class="form-control" type="text"  >
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
                    url: rootPath + "/MerChants/freeze.shtml",
                    data: $('#roleForcash').serializeArray(),
                    success: function(data){
                        if(data == "success") {
                            layer.confirm('设置成功', function(index) {
                                battcn.closeWindow();
                                $('#setCash').bootstrapTable('refresh');
                                return false;
                            });
                        }
                        if(data ===null){
                            layer.confirm('设置失败', function(index) {
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