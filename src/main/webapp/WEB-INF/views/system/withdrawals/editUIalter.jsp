<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="roleFormalterWithdrawals">
			<div class="form-group">
				<label class="col-sm-4 control-label">无卡有积分费率(%)：</label>
				<div class="col-sm-8">
					<input id="fee0" name="fee0"  class="form-control" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">无卡有积分笔数费(元/笔)：</label>
				<div class="col-sm-8">
					<input id="d0fee" name="d0fee"  class="form-control" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">无卡无积分费率(%)：</label>
				<div class="col-sm-8">
					<input id="fee1" name="fee1"  class="form-control" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">无卡无积分笔数费(元/笔)：</label>
				<div class="col-sm-8">
					<input id="d1fee" name="d1fee"  class="form-control" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
		</form>
	</div>
</div>
<script>
   $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>
<script>
$("#fee0").val(${withdrawals.fee0/10 });
$("#d0fee").val(${withdrawals.d0fee/100 });
$("#fee1").val(${withdrawals.fee1/10 });
$("#d1fee").val(${withdrawals.d1fee/100 });
</script>
<script type="text/javascript">

 $(function(){
  	save = function(obj) {
  		if($("#roleFormalterWithdrawals").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Withdrawals/alter.shtml",
				data: $('#roleFormalterWithdrawals').serializeArray(),
				success: function(data){
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#Withdrawals').bootstrapTable('refresh');
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