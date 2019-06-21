<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="editForm">
			 <input id="id" name="id" type="hidden" value="${goods.id}"> 
			<div class="form-group">
				<label class="col-sm-4 control-label">商户号：</label>
				<div class="col-sm-8">
					<input id="merChantId" name="merChantId"  class="form-control" type="text" value="${goods.merChantId }"> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">商户姓名：</label>
				<div class="col-sm-8">
					<input id="merName" name="merName"  class="form-control" type="text" value="${goods.merName }"> 
						<span class="help-block m-b-none"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">商户电话：</label>
				<div class="col-sm-8">
					<input id="merMp" name="merMp"  class="form-control" type="text" value="${goods.merMp }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">代理商号：</label>
				<div class="col-sm-8">
					<input id="agentId" name="agentId"  class="form-control" type="text" value="${goods.agentId }"> 
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
  		var form = new FormData(document.getElementById("editForm"));
  		if($("#editForm").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/enterShow/edit.shtml",
				data: form,
				contentType:false,
				processData:false,
				mimeType:"multipart/form-data",
				success: function(data){
					layer.confirm(data, function(index) {
						battcn.closeWindow();
						$('#enterList').bootstrapTable('refresh');
			        	return false;
 						});
				}
			});
  		}
	} 
	
 }); 
</script>