<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="roleFormpwd">
			 
			<div class="form-group">
				<label class="col-sm-3 control-label">代理商号：</label>
				<div class="col-sm-8">
					<input id="merId" name="merId"  class="form-control" type="text" value="${agent.merId }" readonly="readonly">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
				<label class="col-sm-3 control-label">代理姓名：</label>
				<div class="col-sm-8">
					<input id="merName" name="merName"  class="form-control" type="text" value="${agent.merName }" readonly="readonly">
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
  		if($("#roleFormpwd").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Agent/reset.shtml",
				data: $('#roleFormpwd').serializeArray(),
				success: function(data){
					if(data == "success") {
						layer.confirm('重置成功,密码为：12345', function(index) {
							battcn.closeWindow();
							$('#agentwithpwd').bootstrapTable('refresh');
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