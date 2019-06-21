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
		<form class="form-horizontal m-t required-validate" id="integrateGoodsedit">
			<input id="goodsId" name="goodsId" type="hidden" value="${goods.list[0].id}"> 
			<div class="form-group">
				<label class="col-sm-4 control-label">是否可兑换：</label>
				<div class="col-sm-8">
					 <input name="isUsed" id="used" type="radio" value="1" >是
	    			 <input name="isUsed" id="noused" type="radio" value="0">否
	    		</div>
			</div> 
			<div class="form-group">
				<label class="col-sm-4 control-label">商品名称：</label>
				<div class="col-sm-8">
					<input id="goodsName" name="goodsName"  class="form-control" type="text" value="${goods.list[0].name }"> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">商品描述：</label>
				<div class="col-sm-8">
					<input id="description" name="description"  class="form-control" type="text" value="${goods.list[0].description }"> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">商品所需积分：</label>
				<div class="col-sm-8">
					<input id="point" name="point"  class="form-control" type="text" value="${goods.list[0].point }"> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">首页展示图：</label>
				<div class="col-sm-8">
						<input id="imgone" name="imgone" type="file" value="">
						<img class="col-sm-5" id="imgpho" src="${goods.list[0].img }"/>
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
						<input id="imgshid" name="imgshid" type="hidden" value="${goods.list[0].imgs }">
						<div id="imgsAll">
						</div>
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
						<input id="detailImghid" name="detailImghid" type="hidden" value="${goods.list[0].detailImg }">
						<div id="detailImgAll">
						</div>
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
var imgsAll = $("#imgshid").val();
var im = imgsAll.split(",");
var detailImgAll = $("#detailImghid").val();
var de = detailImgAll.split(",");
for(var i=0;i<im.length;i++){
	var img=$('<img class="col-sm-2" id="imgs" src="'+im[i]+'"/>');
	$('#imgsAll').append(img);
}
for(var i=0;i<de.length;i++){
	var img=$('<img class="col-sm-2" id="detailImg" src="'+de[i]+'"/>');
	$('#detailImgAll').append(img);
}
</script>
<script type="text/javascript">
var isUsed = ${goods.list[0].isUsed};
if(isUsed == 1){
	$("#used").attr("checked",true);
}else if(isUsed == 0){
	$("#noused").attr("checked",true);
}
</script>
<script type="text/javascript">
var isUsed = ${goods.list[0].isUsed};
</script>
<script type="text/javascript">

 $(function(){
  	save = function(obj) {
  		var form = new FormData(document.getElementById("integrateGoodsedit"));
  		if($("#integrateGoodsedit").valid()){
  			layer.confirm('上传中,时间较长请耐心等待！！！！');
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Integrage/editIntegrate.shtml",
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