<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="a">
			<div class="form-group">
				<label class="col-sm-3 control-label">商户号：</label>
				<div class="col-sm-8">
					<input id="mers" name="merchantId"  class="s" type="text" value="">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">订单号：</label>
				<div class="col-sm-8">
					<input id="order" name="orderNo"  class="s" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<%-- <label class="col-sm-3 control-label">是否启用：</label>
				<div class="col-sm-8">
					<div class="radio i-checks">
                      	<label><input type="radio" value="0" checked="checked" name="isEnabled"> <i></i> 禁用</label>
                      	<label><input type="radio" value="1" ${role.isEnabled == 1 ? 'checked="checked" ' : null} name="isEnabled"> <i></i> 启用</label>
                    </div>
				</div> --%>
			<div class="form-group">
				<label class="col-sm-3 control-label"><input type="button" value="提交" onclick="saveA()"></label>
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
  	saveA = function(obj) {
  		if($("#mers").val() == ''){
			alert('商户号不能为空')
		}else if($("#mers").val().indexOf(' ') >= 0){
			alert('请输入正确的商户号格式')
		}else if($("#order").val() == ''){
			alert('订单号不能为空')
		}else if($("#order").val().indexOf(' ') >= 0){
			alert('订单号不能有空格')
		}else if($("#a").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Plan/add.shtml",
				data: $('#a').serializeArray(),
				success: function(data){
					$('.s').val("");
					$('#a').bootstrapTable('refresh');
					alert(data);
					/* if(data == "f"){
						layer.confirm('请重新开户', function(index) {
							battcn.closeWindow();
							$('.s').val("");
							$('#a').bootstrapTable('refresh');
				        	return false;
 						});
					} */
					/* if(data == "fail"){
						layer.confirm('账户已存在', function(index) {
							battcn.closeWindow();
							$('.s').val("");
							$('#a').bootstrapTable('refresh');
				        	return false;
 						});
					} */
					/* if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('.s').val("");
							$('#a').bootstrapTable('refresh');
				        	return false;
 						});
					} */
					/* battcn.toastrsAlert({
		       		     code: data.success ? 'success' :'error',
		       		     msg: data.success ? '成功' :'失败'
		       		}); */
				}
			});
  		}
	} 
	
 }); 
</script>