<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="introduceForm">
			 <input type="hidden" name="id" value="${introduce.id}"/>
			<div class="form-group">
				<label class="col-sm-3 control-label">标题：</label>
				<div class="col-sm-8">
					<input id="titles" name="titles"  class="form-control" type="text" value="${introduce.titles }">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">内容简介：</label>
				<div class="col-sm-8">
					<input id="content" name="content"  class="form-control" type="text" value="${introduce.content }">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">跳转链接：</label>
				<div class="col-sm-8">
					<input id="forwardURL" name="forwardURL"  class="form-control" type="text" value="${introduce.forwardURL }">
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
  		if($("#introduceForm").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Introduce/alter.shtml",
				data: $('#introduceForm').serializeArray(),
				success: function(data){
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#introduceTable').bootstrapTable('refresh');
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