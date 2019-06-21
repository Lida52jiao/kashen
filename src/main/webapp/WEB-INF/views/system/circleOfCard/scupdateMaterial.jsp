<%--
  Created by IntelliJ IDEA.
  User: Dada
  Date: 2018/11/23
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <form class="form-horizontal m-t required-validate" id="scpassForm">
            <input id="id" name="id" type="hidden" value="${id}">
            <div class="form-group">
                <label class="col-sm-4 control-label">是否通过审核：</label>
                <div class="col-sm-8">
                    <input name="shows" id="ispass" type="radio" value="T" >是
                    <input name="shows" id="nopass" type="radio" value="F">否
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
            var pass = $('input:radio[name="shows"]:checked').val();
            if(pass == null){
                alert('请选择是否通过审批')
            }
            if($("#scpassForm").valid()){
                $.ajax({
                    type: "POST",
                    url: rootPath + "/Circle/editMaterial.shtml",
                    data: $('#scpassForm').serializeArray(),
                    success: function(data){
                        layer.confirm(data, function(index) {
                            battcn.closeWindow();
                            $('#scneedPass').bootstrapTable('refresh');
                            return false;
                        });
                    }
                });
            }
        }

    });
</script>
