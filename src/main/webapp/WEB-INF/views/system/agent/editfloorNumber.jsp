<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="FloorNumber">
			 <input type="hidden" name="oneMerId" value="${USER_SESSION_KEY.merId}"/>
				<div class="form-group">
					<label class="col-sm-4 control-label">代理商编号：</label>
					<div class="col-sm-8">
						<input id="merIds" name="merId"  class="floorNumber" type="text" value="">
							<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
							<span class="help-block m-b-none"></span>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label">绑定商户号：</label>
					<div class="col-sm-8">
						<input id="merChantIds" name="merChantId"  class="floorNumber" type="text" value="">
							<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
							<span class="help-block m-b-none"></span>
					</div>
				</div>
			<div class="form-group">
					<label class="col-sm-4 control-label">选择代理商等级：</label>
					<div class="col-sm-8">
					<select name="agentlevel" id="agentlevel" class="agentStatus">
						<option value ="">请选择代理商等级  </option>
		  				<c:forEach items="${agentLevel}" var="key">
							 <option value ="${key.level}">${key.levelname}  </option>
						</c:forEach>
	  				</select>
					<span class="input-group-btn">
					</span>
					</div>
				</div>
			<div class="form-group">
				<span  style="margin-left:300px;" ><input class="btn btn btn-primary" type="button" value="提交" onclick="savefloorNumber()"></span>
				<span  style="margin-left:100px;" ><input class="btn btn btn-primary" type="button" value="重置" onclick="reset()"></span>
			</div> 
		</form>
	</div>
</div>
<script>
   $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>

<script type="text/javascript">

 $(function(){
  	savefloorNumber = function(obj) {
  		var temp1 = document.getElementById("generationFee");
		var temp2 = document.getElementById("generationFeeRepayment");
		var temp3 = document.getElementById("merIds");
		var temp4 = document.getElementById("merChantIds");
		var reg1 = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
		var reg2 = /^[M][0-9]+$/;
		var reg3 = /^[TC][0-9]+$/;
		if($("#merIds").val() == ''){
			alert('请输入代理商编号')
		}else if($("#merIds").val().indexOf(' ') >= 0){
			alert('代理商编号不能有空格')
		}else if(!reg3.test(temp3.value)){
			alert('代理商编号格式有误')
		}else if($("#merChantIds").val() == ''){
			alert('请输入商户号')
		}else if($("#merChantIds").val().indexOf(' ') >= 0){
			alert('商户号不能有空格')
		}else if(!reg2.test(temp4.value)){
			alert('商户号格式有误')
		}else if($("#agentlevel").val() == ''){
			alert('请选择代理商等级')
		}else if($("#FloorNumber").valid()){
			layer.confirm('提交时间可能较长请耐心等待！！！！');
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Transaction/insertFloorNumber.shtml",
				data: $('#FloorNumber').serializeArray(),
				success: function(data){
					if(data == "s"){
						layer.confirm('都为必填项', function(index) {
							battcn.closeWindow();
							$('.floorNumber').val("");
							$('#Agent').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "t"){
						layer.confirm('绑定失败,代理商已被绑定', function(index) {
							battcn.closeWindow();
							$('.floorNumber').val("");
							$('#Agent').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "ff"){
						layer.confirm('绑定失败,商户已被绑定', function(index) {
							battcn.closeWindow();
							$('.floorNumber').val("");
							$('#Agent').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "tt"){
						layer.confirm('不存在此代理商', function(index) {
							battcn.closeWindow();
							$('.floorNumber').val("");
							$('#Agent').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "ss"){
						layer.confirm('商户不存在', function(index) {
							battcn.closeWindow();
							$('.floorNumber').val("");
							$('#Agent').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "f"){
						layer.confirm('请先联系你的上级代理商,设置你的结算底价或代发费用', function(index) {
							battcn.closeWindow();
							$('.floorNumber').val("");
							$('#Agent').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "fail"){
						layer.confirm('设置的结算底价或代发费用过低,请重新设置', function(index) {
							battcn.closeWindow();
							$('.floorNumber').val("");
							$('#Agent').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('.floorNumber').val("");
							$('#FloorNumber').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "error") {
						layer.confirm('保存失败', function(index) {
							battcn.closeWindow();
							$('.floorNumber').val("");
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