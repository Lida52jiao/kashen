<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="code">
			  <input type="hidden" name="oneMerId" value="${agent.oneMerId}"/>
			<div class="form-group">
				<label class="col-sm-3 control-label">代理商号：</label>
				<div class="col-sm-8">
					<input id="merId" name="merId"  class="form-control" type="text" value="${agent.merId }" readonly="readonly">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">数量：</label>
				<div class="col-sm-8">
					<input id="codes" name="codes"  class="form-control" type="text" value="">
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
  		if($("#code").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Code/allotsCode.shtml",
				data: $('#code').serializeArray(),
				success: function(data){
					if(data == "f"){
						layer.confirm('激活码数量不足', function(index) {
							battcn.closeWindow();
							$('#agentwithcode').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "fail"){
						layer.confirm('划拨的激活码数量不能低于50', function(index) {
							battcn.closeWindow();
							$('#agentwithcode').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#agentwithcode').bootstrapTable('refresh');
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