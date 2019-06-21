<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="Fee">
			 <input type="hidden" name="oneMerId" value="${USER_SESSION_KEY.merId}"/>
			<div class="form-group">
				<label class="col-sm-3 control-label">绑定商户号：</label>
				<div class="col-sm-8">
					<input id="merId" name="merId"  class="form-control" type="text" value="">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">代发费用：</label>
				<div class="col-sm-8">
					<input id="generationFee" name="generationFee"  class="form-control" type="text" value="">
						<span class="help-block m-b-none">
				</div>%
			</div>
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
				url: rootPath + "/Transaction/insertgenerationFee.shtml",
				data: $('#Fee').serializeArray(),
				success: function(data){
					if(data == "f"){
						layer.confirm('设置的代发费用过低,请重新设置', function(index) {
							battcn.closeWindow();
							$('#Fee').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "n"){
						layer.confirm('请先联系你的上级代理商,设置你的代发费用', function(index) {
							battcn.closeWindow();
							$('#Agent').bootstrapTable('refresh');
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