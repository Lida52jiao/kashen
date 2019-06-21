<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="editbannerForm">
			 <input id="bannerId" name="bannerId" type="hidden" value="${goods.id}"> 
			<div class="form-group">
				<label class="col-sm-4 control-label">展示图：</label>
				<div class="col-sm-8">
						<input id="imgs" name="imgs" type="file" value=""> 
						<img class="col-sm-4 control-label" src="${goods.imgURL }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">跳转链接：</label>
				<div class="col-sm-8">
					<input id="forwardURL" name="forwardURL"  class="form-control" type="text" value="${goods.forwardURL }"> 
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
  		var form = new FormData(document.getElementById("editbannerForm"));
  		if($("#editbannerForm").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/CardStrategy/editBanner.shtml",
				data: form,
				contentType:false,
				processData:false,
				mimeType:"multipart/form-data",
				success: function(data){
					layer.confirm(data, function(index) {
						battcn.closeWindow();
						$('#banner').bootstrapTable('refresh');
			        	return false;
 						});
				}
			});
  		}
	} 
	
 }); 
</script>