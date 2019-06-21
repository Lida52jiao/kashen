<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="aaa">
			 
			<div class="form-group">
				<label class="col-sm-3 control-label">商户号：</label>
				<div class="col-sm-8">
					<input id="merChantId" name="merChantId"  class="a" type="text" value="" >
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">内容：</label>
				<div class="col-sm-8">
					<textarea name="msg" rows="2" cols="25" class="a" ></textarea> 
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
				<label class="col-sm-3 control-label"><input type="button" value="提交" onclick="savea()"></label>
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
  	savea = function(obj) {
  		if($("#aaa").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/News/adda.shtml",
				data: $('#aaa').serializeArray(),
				success: function(data){
					if(data == "f"){
						layer.confirm('填完整信息', function(index) {
							battcn.closeWindow();
							$('.a').val("");
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('.a').val("");
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