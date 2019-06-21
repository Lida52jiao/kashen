<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>

</style>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="integrateGoodsAdd">
			<div class="form-group">
				<label class="col-sm-4 control-label">选择app：</label>
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
				<label class="col-sm-4 control-label">商品名称：</label>
				<div class="col-sm-8">
					<input id="goodsName" name="goodsName"  class="form-control" type="text" value=""> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">商品描述：</label>
				<div class="col-sm-8">
					<input id="description" name="description"  class="form-control" type="text" value=""> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">商品所需积分：</label>
				<div class="col-sm-8">
					<input id="point" name="point"  class="form-control" type="text" value=""> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">首页展示图：</label>
				<div class="col-sm-8">
						<input id="imgone" name="imgone" type="file" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">轮播展示图：</label>
				<div class="col-sm-8">
						<input id="imgs" name="imgs" type="file" value=""> 
						<input id="imgs1" name="imgs1" type="file" value=""> 
						<input id="imgs2" name="imgs2" type="file" value=""> 
						<input id="imgs3" name="imgs3" type="file" value=""> 
						<input id="imgs4" name="imgs4" type="file" value=""> 
						<input id="imgs5" name="imgs5" type="file" value=""> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">详情展示图：</label>
				<div class="col-sm-8">
						<input id="detailImg" name="detailImg" type="file" value="">
						<input id="detailImg1" name="detailImg1" type="file" value="">
						<input id="detailImg2" name="detailImg2" type="file" value="">
						<input id="detailImg3" name="detailImg3" type="file" value="">
						<input id="detailImg4" name="detailImg4" type="file" value="">
						<input id="detailImg5" name="detailImg5" type="file" value="">
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
  		var form = new FormData(document.getElementById("integrateGoodsAdd"));
  		if($("#integrateGoodsAdd").valid()){
  			layer.confirm('上传中,时间较长请耐心等待！！！！');
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Integrage/add.shtml",
				timeout : 50000, //超时时间设置，单位毫秒
				data: form,
				contentType:false,
				processData:false,
				mimeType:"multipart/form-data",
				success: function(data){
						layer.confirm(data, function(index) {
							battcn.closeWindow();
							$('#integrateGoodsTable').bootstrapTable('refresh');
				        	return false;
 						});
				}
			});
  		}
	} 
 }); 
</script>