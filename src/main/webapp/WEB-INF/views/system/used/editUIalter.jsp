<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="roleFormalterUsed">
			<div class="form-group">
				<label class="col-sm-3 control-label">激活码费：</label>
				<div class="col-sm-8">
					<input id="month" name="month"  class="form-control" type="text" value="${used.month }" >
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<%-- <div class="form-group">
				<label class="col-sm-3 control-label">年费：</label>
				<div class="col-sm-8">
					<input id="year" name="year"  class="form-control" type="text" value="${used.year }">
						<span class="help-block m-b-none">
				</div>
			</div> --%>
		</form>
	</div>
</div>
<script>
   $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>

<script type="text/javascript">

 $(function(){
  	save = function(obj) {
  		if($("#roleFormalterUsed").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Used/alter.shtml",
				data: $('#roleFormalterUsed').serializeArray(),
				success: function(data){
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#usedTable').bootstrapTable('refresh');
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