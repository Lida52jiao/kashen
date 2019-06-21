<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="FloorNumber">
			 <input type="hidden" name="oneMerId" value="${USER_SESSION_KEY.merId}"/>
			<div class="form-group">
				<label class="col-sm-3 control-label">代理商编号：</label>
				<div class="col-sm-8">
					<input id="merId" name="merId"  class="floorNumber" type="text" value="">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">绑定商户号：</label>
				<div class="col-sm-8">
					<input id="merChantId" name="merChantId"  class="floorNumber" type="text" value="">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<!-- <div class="form-group">
				<label class="col-sm-3 control-label">选择代理商等级：</label>
				<div class="col-sm-8">
					<input id="floorNumber" name="floorNumber"  class="floorNumber" type="text" value="">
				</div>
			</div> -->
			<div class="form-group">
					<label class="col-sm-3 control-label">选择代理商等级：</label>
					<div class="col-sm-8">
					<select name="floorNumber" id="floorNumber" class="floorNumber">
	  					<option value ="0.65" selected = "selected">运营中心</option>
	  					<option value ="0.60">城市合伙人</option>
	  					</select>
					<span class="input-group-btn">
					</span>
					</div>
				</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">提现代发费(元/笔)：</label>
				<div class="col-sm-8">
					<input id="generationFee" name="generationFee"  class="floorNumber" type="text" value="">
				</div>
			</div> 
			<div class="form-group">
				<label class="col-sm-3 control-label">还款代发费(元/笔)：</label>
				<div class="col-sm-8">
					<input id="generationFeeRepayment" name="generationFeeRepayment"  class="floorNumber" type="text" value="">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">无卡费率费(%)：</label>
				<div class="col-sm-8">
					<input id="fee0" name="fee0"  class="floorNumber" type="text" value="">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">无卡提现费(元/笔)：</label>
				<div class="col-sm-8">
					<input id="d0fee" name="d0fee"  class="floorNumber" type="text" value="">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><input type="button" value="提交" onclick="savefloorNumber()"></label>
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
  	savefloorNumber = function(obj) {
  		if($("#FloorNumber").valid()){
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