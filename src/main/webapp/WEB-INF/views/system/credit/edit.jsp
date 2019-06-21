<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="Credit">
			 
			<div class="form-group">
				<label class="col-sm-3 control-label">网关号：</label>
				<div class="col-sm-8">
					<input id="gateId" name="gateId"  class="form-control" type="text" value="${credit.gateId }" readonly="readonly">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">网关名称：</label>
				<div class="col-sm-8">
					<input id="gateName" name="gateName"  class="form-control" type="text" value="${credit.gateName }" readonly="readonly">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">成本价：</label>
				<div class="col-sm-8">
					<input id="cost" name="cost"  class="form-control" type="text" value="${credit.cost }" readonly="readonly">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">交易方式：</label>
				<div class="col-sm-8">
					<input id="transaction" name="transaction"  class="form-control" type="text" value="${credit.transaction }" readonly="readonly">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">终端费率：</label>
				<div class="col-sm-8">
					<input id="merchantFee" name="merchantFee"  class="form-control" type="text" value="${credit.merchantFee }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<%-- <div class="form-group">
				<label class="col-sm-3 control-label">还款提现手续费：</label>
				<div class="col-sm-8">
					<input id="generationFee" name="generationFee"  class="form-control" type="text" value="${credit.generationFee }">
						<span class="help-block m-b-none">
				</div>
			</div> --%>
			<div class="form-group">
				<label class="col-sm-3 control-label">计划还款手续费：</label>
				<div class="col-sm-8">
					<input id="generationFeeRepayment" name="generationFeeRepayment"  class="form-control" type="text" value="${credit.generationFeeRepayment }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<%-- <label class="col-sm-3 control-label">是否启用：</label>
				<div class="col-sm-8">
					<div class="radio i-checks">
                      	<label><input type="radio" value="0" checked="checked" name="isEnabled"> <i></i> 禁用</label>
                      	<label><input type="radio" value="1" ${role.isEnabled == 1 ? 'checked="checked" ' : null} name="isEnabled"> <i></i> 启用</label>
                    </div>
				</div> --%>
				<div align="center">
					<input type="button" value="提交" onclick="save()">
					 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" value="重置" onclick="reset()">
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
  		if($("#Credit").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Credit/reset.shtml",
				data: $('#Credit').serializeArray(),
				success: function(data){
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#Credit').bootstrapTable('refresh');
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