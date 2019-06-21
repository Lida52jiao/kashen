<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="merCodeFormalter">
			 
			<div class="form-group">
				<label class="col-sm-3 control-label">代理商号：</label>
				<div class="col-sm-8">
					<input id="merCode" name="agentId"  class="form-control" type="text" value="${merCode.merId }" readonly="readonly">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">代理商名称：</label>
				<div class="col-sm-8">
					<input id="merCode" name="merName"  class="form-control" type="text" value="${merCode.merName }" readonly="readonly">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">激活码数量：</label>
				<div class="col-sm-8">
					<input id="merCode" name="totalCode"  class="form-control" type="text" value="">
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
  		if($("#merCodeFormalter").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/MerCode/alter.shtml",
				data: $('#merCodeFormalter').serializeArray(),
				success: function(data){
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#merCodeTable').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "t") {
						layer.confirm('激活码数量不足', function(index) {
							battcn.closeWindow();
							$('#merCodeTable').bootstrapTable('refresh');
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