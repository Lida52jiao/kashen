<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="Banner">
			
			<div class="form-group">
				<label class="col-sm-3 control-label">app：</label>
				<div class="col-sm-8">
				<select name="appId"> 
    			
             	<option value="${t.appId}">${t.appName}</option>
      			 
				</select> 
			</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">上传轮播图：</label>
				<div class="col-sm-8">
					<input id="imgURL" name="imgURL"  class="banner" type="file" value="">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">图片名称：</label>
				<div class="col-sm-8">
					<input id="name" name="name"  class="banner" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">跳转链接：</label>
				<div class="col-sm-8">
					<input id="forwardURL" name="forwardURL"  class="banner" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">类型：</label>
				<!-- <div class="col-sm-8">
					<input id="status" name="status"  class="banner" type="text" value="">
						<span class="help-block m-b-none">
				</div> -->
				<div class="col-sm-8">
					<div class="radio i-checks">
                      	<label><input type="radio" value="0" name="status" class="banner"> <i></i> 首页</label>
                      	<label><input type="radio" value="1" name="status" class="banner"> <i></i> 信用卡申请(192*240)</label>
                      	<label><input type="radio" value="2" name="status" class="banner"> <i></i> 用卡百科(480*228)</label>
                      	<label><input type="radio" value="3" name="status" class="banner"> <i></i> 广告位(1080*360)</label>
                      	<label><input type="radio" value="4" name="status" class="banner"> <i></i> 分享页(750*1334)</label>
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
				<label class="col-sm-3 control-label"><input type="button" value="提交" onclick="saveBanner()"></label>
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
  	 saveBanner = function(obj) {
  	 	var form = new FormData(document.getElementById("Banner"));
  		if($("#Banner").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Banner/add.shtml",
				data: form,
				contentType:false,
				processData:false,
				mimeType:"multipart/form-data",
				success: function(data){
					if(data == "s") {
						layer.confirm('请选择类型', function(index) {
							battcn.closeWindow();
							$('.banner').val("");
							$('#Banner').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "f") {
						layer.confirm('上传失败', function(index) {
							battcn.closeWindow();
							$('.banner').val("");
							$('#Banner').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "fail") {
						layer.confirm('请选择图片', function(index) {
							battcn.closeWindow();
							$('.banner').val("");
							$('#Banner').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('.banner').val("");
							$('#Banner').bootstrapTable('refresh');
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