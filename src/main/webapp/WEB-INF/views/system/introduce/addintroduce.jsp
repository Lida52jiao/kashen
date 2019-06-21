<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="Introduce">
			
			<div class="form-group">
				<label class="col-sm-3 control-label">上传图片：</label>
				<div class="col-sm-8">
					<input id="imgURL" name="imgURL"  class="introduce" type="file" value="">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">标题：</label>
				<div class="col-sm-8">
					<input id="titles" name="titles"  class="introduce" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">内容简介：</label>
				<div class="col-sm-8">
					<input id="content" name="content"  class="introduce" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">跳转链接：</label>
				<div class="col-sm-8">
					<input id="forwardURL" name="forwardURL"  class="introduce" type="text" value="">
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
			<!-- <div class="form-group">
				<label class="col-sm-3 control-label"><input type="button" value="提交" onclick="saveIntroduce()"></label>
				<label class="col-sm-3 control-label"><input type="button" value="重置" onclick="reset()"></label>	
			</div>  -->
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
  		var form = new FormData(document.getElementById("Introduce"));
  		if($("#Introduce").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Introduce/add.shtml",
				data: form,
				contentType:false,
				processData:false,
				mimeType:"multipart/form-data",
				success: function(data){
					if(data == "f") {
						layer.confirm('上传失败', function(index) {
							battcn.closeWindow();
							//$('.banner').val("");
							$('#introduceTable').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "fail") {
						layer.confirm('请选择图片', function(index) {
							battcn.closeWindow();
							//$('.banner').val("");
							$('#introduceTable').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							//$('.input').val("");
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