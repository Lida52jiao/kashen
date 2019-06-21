<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=basePath%>js/country_1.js"></script>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="usedAreaForm">
			 
			<div class="form-group">
				<label class="col-sm-3 control-label">商户号：</label>
				<div class="col-sm-8">
					<input id="merChantId" name="merChantId"  class="form-control" type="text" value="${usedArea.merChantId }" readonly="readonly">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商户常住地(省)：</label>
				<div class="col-sm-8">
					<input id="usedProvice" name="usedProvice"  class="form-control" type="text" value="${usedArea.proviceAreaName }" readonly="readonly">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商户常住地(市)：</label>
				<div class="col-sm-8">
					<input id="usedCity" name="usedCity"  class="form-control" type="text" value="${usedArea.cityAreaName }" readonly="readonly">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商户常住地(县)：</label>
				<div class="col-sm-8">
					<input id="usedCountry" name="usedCountry"  class="form-control" type="text" value="${usedArea.countryAreaName }" readonly="readonly">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<div>
					<label class="col-sm-3 control-label" >选择常住地(省)：</label><br>
				 	<select class="floorNumber" id="province" name="province" next="city"><option value="-1">=省级=</option></select>
				 	<span class="help-block m-b-none"></span>
			 	</div>
			 	<div >
			 		<label class="col-sm-3 control-label" >选择常住地(市)：</label><br>
                	<select class="floorNumber" id="city"  name="city" next="region"><option value="-1">=市级=</option></select>
               		<span class="help-block m-b-none"></span>
               	</div>
               	<div >
               		<label class="col-sm-3 control-label" >选择常住地(县)：</label>
                	<select class="floorNumber" id="region" name="region" next="street" name="countryId"><option value="-1">=县级=</option></select>
					<span class="help-block m-b-none"></span>
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
		if($("#province").val()=='-1'){
			alert('请选择常住地(省)')
		}else if($("#city").val()=='-1'){
			alert('请选择常住地(市)')
		} else if($("#region").val()=='-1'){
			alert('请选择常住地(县)')
		}else if($("#usedAreaForm").valid()){
 			$.ajax({
				type: "POST", 
				url: rootPath + "/MerChantsUsedArea/updateUsedArea.shtml",
				data: $('#usedAreaForm').serializeArray(),
				success: function(data){
						layer.confirm(data, function(index) {
							battcn.closeWindow();
							$('.floorNumber').val("");
				        	return false;
						});
				}
			});
 		}
	} 
	
}); 
</script>