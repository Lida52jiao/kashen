<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
			<form class="form-horizontal m-t required-validate" id="storesForm">
			<div class="form-group">
				<label class="col-sm-3 control-label">选择app：</label>
				<div class="col-sm-8">
				<select name="appId" id="appId" class="input form-control" >
	                        <c:forEach items="${app}" var="key">
						 		<option value ="${key.appId}">${key.appName}  </option>
							</c:forEach>
  					</select>
				 <span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">是否上架：</label>
				<div class="col-sm-8">
					 <input name="open" id="type1" type="radio" value="0" >下架
	    			 <input name="open" id="type2" type="radio" value="1">上架
	    		</div>
			</div> 
			<div class="form-group">
				<label class="col-sm-3 control-label">商品名称：</label>
				<div class="col-sm-8">
					<input id="commodityName" name="commodityName"  class="form-control" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商品描述：</label>
				<div class="col-sm-8">
					<input id="description" name="description"  class="form-control" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商品图片：</label>
				<div class="col-sm-8">
						<input id="img" name="img" type="file" value=""> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商品价格：</label>
				<div class="col-sm-8">
					<input id="price" name="price"  class="form-control" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">销售量：</label>
				<div class="col-sm-8">
					<input id="salesVolume" name="salesVolume"  class="form-control" type="text" value="">
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
  		var form = new FormData(document.getElementById("storesForm"));
  		if($("#storesForm").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Trade/insertstores.shtml",
				data: form,
				contentType:false,
				processData:false,
				mimeType:"multipart/form-data",
				success: function(data){
						layer.confirm(data, function(index) {
							battcn.closeWindow();
							$('#stores').bootstrapTable('refresh');
				        	return false;
 						});
				}
			});
  		}
	} 
	
 }); 
</script>