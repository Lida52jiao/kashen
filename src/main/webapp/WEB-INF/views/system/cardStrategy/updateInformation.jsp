<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="informationForm">
			 <input id="informationId" name="informationId" type="hidden" value="${goods.id}"> 
			<div class="form-group">
				<label class="col-sm-4 control-label">图标：</label>
				<div class="col-sm-8">
						<input id="imgs" name="imgs" type="file" value=""> 
						<img class="col-sm-4 control-label" src="${goods.imgURL }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">属性：</label>
				<div class="col-sm-8">
					 <input name="type" id="type1" type="radio" value="1" >推荐
	    			 <input name="type" id="type2" type="radio" value="2">提额
	    			 <input name="type" id="type3" type="radio" value="3" >办卡
	    			 <input name="type" id="type4" type="radio" value="4">热点
	    		</div>
			</div> 
			<div class="form-group">
				<label class="col-sm-4 control-label">标题：</label>
				<div class="col-sm-8">
					<input id="titles" name="titles"  class="form-control" type="text" value="${goods.titles }"> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">预览标题：</label>
				<div class="col-sm-8">
					<input id="content" name="content"  class="form-control" type="text" value="${goods.content }"> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">文章跳转链接：</label>
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
var type = ${goods.type};
if(type == 1){
	$("#type1").attr("checked",true);
}else if(type == 2){
	$("#type2").attr("checked",true);
}else if(type == 3){
	$("#type3").attr("checked",true);
}else if(type == 4){
	$("#type4").attr("checked",true);
}
</script>
<script type="text/javascript">

 $(function(){
  	save = function(obj) {
  		var form = new FormData(document.getElementById("informationForm"));
  		if($("#informationForm").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/CardStrategy/editInformation.shtml",
				data: form,
				contentType:false,
				processData:false,
				mimeType:"multipart/form-data",
				success: function(data){
					layer.confirm(data, function(index) {
						battcn.closeWindow();
						$('#information').bootstrapTable('refresh');
			        	return false;
 						});
				}
			});
  		}
	} 
	
 }); 
</script>