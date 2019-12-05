	<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="merChantsForm">
			 
			<div class="form-group">
				<label class="col-sm-3 control-label">商户号：</label>
				<div class="col-sm-8">
					<input id="merChantId" name="merChantId"  class="form-control" type="text" value="${merChants.merChantId }" readonly="readonly">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
		<%-- 	<div class="form-group">
				<label class="col-sm-3 control-label">名称：</label>
				<div class="col-sm-8">
					<input id="merName" name="merName"  class="form-control" type="text" value="${merChants.merName }" readonly="readonly">
						<span class="help-block m-b-none">
				</div>
			</div> --%>
			<div class="form-group">
				<label class="col-sm-3 control-label">手机号：</label>
				<div class="col-sm-8">
					<input id="merMp" name="merMp"  class="form-control" type="text" value="${merChants.merMp }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">代理商等级：</label>
				<div class="col-sm-8">
					<input id="agentStatus" name="agentStatus"  class="form-control" type="text" value="${merChants.agentStatus }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商户等级：</label>
				<div class="col-sm-8">
					<input id="merType" name="merType"  class="form-control" type="text" value="${merChants.merType }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">功能是否打开：</label>
				<label><input type="radio" value="1" ${merChants.isNotUse == 1 ? 'checked="checked" ' : null} name="isNotUse"> <i></i> 是(可用)</label>
				<label><input type="radio" value="2" ${merChants.isNotUse == 2 ? 'checked="checked" ' : null} name="isNotUse"> <i></i> 否(不可用)</label>

				<span class="help-block m-b-none"></span>
			</div>
			<div style ="display:none">
				<div class="form-group">
					<label class="col-sm-3 control-label">费率：</label>
					<div class="col-sm-8">
						<input id="merChantFee" name="merChantFee"  class="form-control" type="text" value="${merChants.merChantFee }">
							<span class="help-block m-b-none">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">提现代发费：</label>
					<div class="col-sm-8">
						<input id="generationFee" name="generationFee"  class="form-control" type="text" value="${merChants.generationFee }">
							<span class="help-block m-b-none">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">还款代发费：</label>
					<div class="col-sm-8">
						<input id="generationFeeRepayment" name="generationFeeRepayment"  class="form-control" type="text" value="${merChants.generationFeeRepayment }">
							<span class="help-block m-b-none">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">增加的天数：</label>
					<div class="col-sm-8">
						<input id="time" name="time"  class="form-control" type="text" value="0">
							<span class="help-block m-b-none">
					</div>
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
  		if($("#merChantsForm").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/MerChants/alter.shtml",
				data: $('#merChantsForm').serializeArray(),
				success: function(data){
					if(data == "f") {
						layer.confirm('请联系上级代理商设置结算底价以及代发费用', function(index) {
							battcn.closeWindow();
							$('#merChants').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "s") {
						layer.confirm('商户的费率不能低于0.85', function(index) {
							battcn.closeWindow();
							$('#merChants').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "fail") {
						layer.confirm('商户费率或代发费用不能低于你的', function(index) {
							battcn.closeWindow();
							$('#merChants').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#merChants').bootstrapTable('refresh');
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