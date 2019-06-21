<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script> 
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="risks">
			  <input type="hidden" name="agentId" value="${USER_SESSION_KEY.merId}"/>
			<div class="form-group">
				<label class="col-sm-3 control-label">提现的开始时间：</label>
				<div class="col-sm-8">
					<input id="withdrawalsStarttime" name="withdrawalsStarttime"  class="Wdate" type="text" onclick="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm:ss'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">提现的结束时间：</label>
				<div class="col-sm-8">
					<input id="withdrawalsFinishtime" name="withdrawalsFinishtime"  class="Wdate" type="text" onclick="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm:ss'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">交易的开始时间：</label>
				<div class="col-sm-8">
					<input id="transactionStarttime" name="transactionStarttime"  class="Wdate" type="text" onclick="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm:ss'})">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">交易的结束时间：</label>
				<div class="col-sm-8">
					<input id="transactionFinishtime" name="transactionFinishtime"  class="Wdate" type="text" onclick="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm:ss'})">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">提交计划的开始时间：</label>
				<div class="col-sm-8">
					<input id="submitplanStarttime" name="submitplanStarttime"  class="Wdate" type="text" onclick="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm:ss'})">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">提交计划的结束时间：</label>
				<div class="col-sm-8">
					<input id="submitplanFinishtime" name="submitplanFinishtime"  class="Wdate" type="text" onclick="WdatePicker({lang:'zh-cn',dateFmt:'HH:mm:ss'})">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">每天最多的还款笔数：</label>
				<div class="col-sm-8">
					<input id="maximumRepayment" name="maximumRepayment" type="text" >
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">平均的还款金额：</label>
				<div class="col-sm-8">
					<input id="averageRepayment" name="averageRepayment" type="text" >
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商户日累计消费金额：</label>
				<div class="col-sm-8">
					<input id="consumptionAmount" name="consumptionAmount" type="text" >
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">商户日累计提现金额：</label>
				<div class="col-sm-8">
					<input id="withdrawalAmount" name="withdrawalAmount" type="text" >
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">单笔提现的最低金额：</label>
				<div class="col-sm-8">
					<input id="minimumAmount" name="minimumAmount" type="text" >
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">提现手续费：</label>
				<div class="col-sm-8">
					<input id="commissionFee" name="commissionFee" type="text" >
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">提现几次余额不足冻结账户：</label>
				<div class="col-sm-8">
					<input id="number" name="number" type="text" >
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><input type="button" value="提交" onclick="save()"></label>
				<label class="col-sm-3 control-label"><input type="button" value="重置" onclick="reset()"></label>
				
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
  		if($("#risks").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Risk/addRisk.shtml",
				data: $('#risks').serializeArray(),
				success: function(data){
				if(data == "fail") {
						layer.confirm('你已经设置', function(index) {
							battcn.closeWindow();
							$('#risks').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "f") {
						layer.confirm('都为必填项', function(index) {
							battcn.closeWindow();
							$('#risks').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#risks').bootstrapTable('refresh');
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