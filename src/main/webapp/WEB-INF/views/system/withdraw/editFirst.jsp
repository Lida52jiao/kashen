<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="editFirst">
			  <input type="hidden" name="orderNo" value="${withdraw.orderNo }"/>
			<div class="form-group">
					<label class="col-sm-3 control-label">状态：</label>
					<div class="radio i-checks">
                      	<label><input type="radio" value="4" name="state" class="input form-control"> <i></i> 通过</label>
                      	<label><input type="radio" value="5" name="state" class="input form-control"> <i></i> 不通过</label>
                    </div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">失败原因：</label>
				<div class="col-sm-3 control-label">
					<textarea name="remarks" rows="2" cols="25" class="input form-control" ></textarea> 
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
  		if($("#editFirst").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/WithdrawEntity/alter.shtml",
				data: $('#editFirst').serializeArray(),
				success: function(data){
					if(data == "f") {
						layer.confirm('请填写失败原因', function(index) {
							battcn.closeWindow();
							$('#first').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "fail") {
						layer.confirm('审核失败', function(index) {
							battcn.closeWindow();
							$('#first').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#first').bootstrapTable('refresh');
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