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
                    <input id="merChantId" name="merChantId" class="form-control" type="text" value="${merChantId}" readonly="readonly">
                    <span class="help-block m-b-none"></span>
                </div>
                <label class="col-sm-3 control-label">是否通过：</label>
                <div class="col-sm-8">
                    <select name="state" id="state" class="input form-control">
                        <option value ="">请选择</option>
                        <option value ="1">是</option>
                        <option value ="2">否</option>
                    </select>
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
                    url: rootPath + "/MerChants/checkRealName.shtml",
                    data: $('#roleForcash').serializeArray(),
                    success: function(data){
                        data = JSON.parse(data);
                        if(data.respCode == "0000") {
                            layer.confirm('处理成功', function(index) {
                                battcn.closeWindow();
                                $('#setCash').bootstrapTable('refresh');
                                return false;
                            });
                        }
                        if(data.respCode != "0000"){
                            layer.confirm('处理失败', function(index) {
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