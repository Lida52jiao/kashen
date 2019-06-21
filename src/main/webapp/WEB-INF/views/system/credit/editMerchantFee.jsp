<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="Fee">
			  <input type="hidden" name="agentId" value="${USER_SESSION_KEY.merId}"/>
			<div class="form-group">
				<label class="col-sm-3 control-label">结算底价：</label>
				<div class="col-sm-8">
					<input id="floorNumber" name="floorNumber"  class="form-control" type="text" value="${fee.floorNumber }" readonly="readonly">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">终端费率：</label>
				<div class="col-sm-8">
					<input id="merchantFee" name="merchantFee"  class="form-control" type="text" value="${fee.merchantFee }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<%-- <label class="col-sm-3 control-label">是否启用：</label>
				<div class="col-sm-8">
					<div class="radio i-checks">
                      	<label><input type="radio" value="0" checked="checked" name="isEnabled"> <i></i> 禁用</label>
                      	<label><input type="radio" value="1" ${role.isEnabled == 1 ? 'checked="checked" ' : null} name="isEnabled"> <i></i> 启用</label>
                    </div>
				</div> --%>
				<div align="center">
					<input type="button" value="提交" onclick="save()">
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="重置" onclick="reset()">
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
  		if($("#Fee").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Fee/addMerchantFee.shtml",
				data: $('#Fee').serializeArray(),
				success: function(data){
					if(data == "f") {
						layer.confirm('请联系上级代理商设置结算底价', function(index) {
							battcn.closeWindow();
							$('#Fee').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "s") {
						layer.confirm('终端的费率不能低于0.85', function(index) {
							battcn.closeWindow();
							$('#Fee').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "fail") {
						layer.confirm('终端的费率不能低于你的结算底价', function(index) {
							battcn.closeWindow();
							$('#Fee').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#Fee').bootstrapTable('refresh');
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