<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="roleForm">
			 
			<div class="form-group">
				<label class="col-sm-3 control-label">绑定商户号：</label>
				<div class="col-sm-8">
					<input id="merId" name="merId"  class="form-control" type="text" value="${agent.merId }" readonly="readonly">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">通道结算底价：</label>
				<div class="col-sm-8">
					<input id="floorNumber" name="floorNumber"  class="form-control" type="text" value="${agent.floorNumber }">
						<span class="help-block m-b-none">
				</div>%
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">代发费用：</label>
				<div class="col-sm-8">
					<input id="generationFee" name="generationFee"  class="form-control" type="text" value="${agent.generationFee }">
						<span class="help-block m-b-none">
				</div>%
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
  		if($("#roleForm").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Transaction/mergeOrInsert.shtml",
				data: $('#roleForm').serializeArray(),
				success: function(data){
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#agentTable').bootstrapTable('refresh');
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