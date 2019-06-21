<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.sp {
		color: red;
	}
</style>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=basePath%>js/country_1.js"></script>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<div class="doc-buttons">
			<c:forEach items="${res}" var="key">
				${key.description}
			</c:forEach>
		</div>
		<form class="form-horizontal m-t required-validate" id="bindArea">
			<div class="form-group">
				<label class="col-sm-3 control-label">代理商编号：</label>
				<div class="col-sm-8">
					<input id="merIds" name="merId"  class="floorNumber" type="text" value="">
						<span class="help-block m-b-none"></span>
				</div>
			</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">商户编号：</label>
					<div class="col-sm-8">
						<input id="merChantId" name="merChantId"  class="floorNumber" type="text" value="">
							<span class="help-block m-b-none"></span>
					</div>
				</div>
				<div class="form-group">
						<div>
							<label class="col-sm-3 control-label" >选择绑定的区域(省)：</label><br>
						 	<select class="floorNumber" id="province" name="province" next="city"><option value="-1">=省级=</option></select>
						 	<span class="help-block m-b-none"></span>
					 	</div>
					 	<div >
					 		<label class="col-sm-3 control-label" >选择绑定的区域(市)：</label><br>
		                	<select class="floorNumber" id="city"  name="city" next="region"><option value="-1">=市级=</option></select>
	                		<span class="help-block m-b-none"></span>
	                	</div>
	                	<div >
	                		<label class="col-sm-3 control-label" >选择绑定的区域(县)：</label>
		                	<select class="floorNumber" id="region" name="region" next="street" name="countryId"><option value="-1">=县级=</option></select>
							<span class="help-block m-b-none"></span>
						</div>
				</div>
			<div class="form-group">
				<span  style="margin-left:300px;" ><input class="btn btn btn-primary" type="button" value="提交" onclick="savearea()"></span>
				<span  style="margin-left:100px;" ><input class="btn btn btn-primary" type="button" value="重置" onclick="reset()"></span>
			</div> 
		</form>
	</div>
</div>
<script>
   $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>
<script>


</script>
<script type="text/javascript">
    function AddArea() {
        $.ajax({
            type: "POST",
            url: rootPath + "/addArea/getCity.shtml",
        });
    }

    $(function(){
	 savearea = function(obj) {
		 var temp1 = document.getElementById("generationFee");
		var temp3 = document.getElementById("merIds");
		var temp4 = document.getElementById("merChantId");
		 var merId = $("#merIds").val();
		 var merChantId = $("#merChantId").val();
		 var province = $("#province").val();
		 var city = $("#city").val();
		 var region = $("#region").val();
		var reg1 = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
		var reg2 = /^[M][0-9]+$/;
		var reg3 = /^[C][0-9]+$/;
		if($("#merIds").val() == ''){
			alert('请输入代理商编号')
		}else if($("#merIds").val().indexOf(' ') >= 0){
			alert('代理商编号不能有空格')
		}else if(!reg3.test(temp3.value)){
			alert('代理商编号格式有误')
		}else if($("#merChantId").val() == ''){
			alert('请输入商户号')
		}else if($("#merChantId").val().indexOf(' ') >= 0){
			alert('商户号不能有空格')
		}else if(!reg2.test(temp4.value)){
			alert('商户号格式有误')
		} else if($("#province").val()=='-1'){
			alert('请选择绑定区域')
		}else if($("#bindArea").valid()){
  			$.ajax({
				type: "POST",
				url: "http://47.105.150.17/" + "/Agent/confirm?merId="+merId+"&merChantId="+merChantId+"&province="+province+"&city="+city+"&region="+region,
				/*data: $('#bindArea').serializeArray(),*/
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