<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="alertNum">
		<input id="id" name="id"  class="form-control" type="hidden" value="${payment.id}" >
		<input id="paymentNum" name="paymentNum"  class="form-control" type="hidden" value="${paymenttotal.paymentNum}" >
		<input id="irewardOrNot" name="irewardOrNot"  class="form-control" type="hidden" value="${payment.rewardOrNot}" >
			<div class="form-group">
				<label class="col-sm-4 control-label">此等级的直推、间推、间间推是否享有：</label>
				<div class="col-sm-8">
					 <input name="rewardOrNot" id="isrewardOrNot" type="radio" value="Y" >是
	    			 <input name="rewardOrNot" id="norewardOrNot" type="radio" value="N">否
	    		</div>
			</div> 
			<div id="show">
				<div class="form-group">
					<label class="col-sm-3 control-label">直推（元）：</label>
					<div class="col-sm-8">
						<input id="oneMer" name="oneMer"  class="form-control" type="text" value="${fix.oneMer}">
							<span class="help-block m-b-none">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">间推（元）：</label>
					<div class="col-sm-8">
						<input id="twoMer" name="twoMer"  class="form-control" type="text" value="${fix.twoMer}">
							<span class="help-block m-b-none">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">间间推（元）：</label>
					<div class="col-sm-8">
						<input id="threeMer" name="threeMer"  class="form-control" type="text" value="${fix.threeMer}">
							<span class="help-block m-b-none">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">直接代理（元）：</label>
					<div class="col-sm-8">
						<input id="fixReward" name="fixReward"  class="form-control" type="text" value="${payment.fixReward}">
							<span class="help-block m-b-none">
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">补贴（元）：</label>
					<div class="col-sm-8">
						<input id="subsidy" name="subsidy"  class="form-control" type="text" value="${payment.subsidy}">
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
var isrewardOrNot = $("#irewardOrNot").val();
if(isrewardOrNot == "Y"){
	$("#isrewardOrNot").attr("checked",true);
}else if(isrewardOrNot == "N"){
	$("#norewardOrNot").attr("checked",true);
}
if(isrewardOrNot == "N"){
	$("#show").css("display","none");
}
</script>
<script type="text/javascript">
$("#oneMer").val(${fix.oneMer/100});
$("#twoMer").val(${fix.twoMer/100});
$("#threeMer").val(${fix.threeMer/100});
$("#fixReward").val(${payment.fixReward/100});
$("#subsidy").val(${payment.subsidy/100});
$("#paymentNum").val(${paymenttotal.paymentNum/100 });
</script>
<script type="text/javascript">
$(function(){
	save = function(obj) {
		var oneMer = Number($("#oneMer").val());
		var twoMer = Number($("#twoMer").val());
		var threeMer = Number($("#threeMer").val());
		var fixReward = Number($("#fixReward").val());
		var subsidy = Number($("#subsidy").val());
		var paymentNum = Number($("#paymentNum").val());
		if(oneMer+twoMer+threeMer+fixReward+subsidy>paymentNum){
			alert("分出总钱数多于升大咖缴费数")
		} else if($("#alertNum").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/KSPaymentFix/alert.shtml",
				data: $('#alertNum').serializeArray(),
				success: function(data){
					layer.confirm(data, function(index) {
						battcn.closeWindow();
						$('#paymentNumTables').bootstrapTable('refresh');
			        	return false;
 						});

				}
			});
  		}
	} 
	
 }); 
</script>