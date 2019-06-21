<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="alter">
			 <input type="hidden" name="merId" value="${USER_SESSION_KEY.merId}"/>
			 <input type="hidden" name="merName" value="${USER_SESSION_KEY.userName}"/>
			<div class="form-group">
				<label class="col-sm-3 control-label">输入旧密码：</label>
				<div class="col-sm-8">
					<input id="password" name="password"  class="alter" type="password" value="">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">输入新密码：</label>
				<div class="col-sm-8">
					<input id="newpassword" name="newpassword"  class="alter" type="password" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<div class="form-group">
				<label class="col-sm-3 control-label"><input type="button" value="提交" onclick="savealters()"></label>
				<label class="col-sm-3 control-label"><input type="button" value="重置" onclick="reset()"></label>	
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
  	savealters = function(obj) {
  		if($("#alter").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Agent/amend.shtml",
				data: $('#alter').serializeArray(),
				success: function(data){
					if(data == "f"){
						layer.confirm('旧密码输入错误,请重新修改', function(index) {
							battcn.closeWindow();
							$('.alter').val("");
							$('#Agent').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('.alter').val("");
							$('#alter').bootstrapTable('refresh');
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