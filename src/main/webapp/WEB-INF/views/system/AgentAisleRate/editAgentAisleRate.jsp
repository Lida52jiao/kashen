<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="merChantsRateForm">
			 <input id="id" name="id"  class="form-control" type="hidden" value="${MerchantsRate.id}">
			
			<div class="form-group">
				<label class="col-sm-3 control-label">用户等级：</label>
				<div class="col-sm-8">
					<input id="mertype" name="mertype"  class="form-control" disabled="disabled" type="text" value="${MerchantsRate.mertype}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">网关：</label>
				<div class="col-sm-8">
					<input id="aisleCode" name="aislecode"  class="form-control" type="text" disabled="disabled"  value="${MerchantsRate.aislecode}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">通道名称：</label>
				<div class="col-sm-8">
					<input id="aislecodeName" name="aislecodeName"  class="form-control"  disabled="disabled" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">费率%：</label>
				<div class="col-sm-8">
					<input id="rate" name="rate"  class="form-control" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">笔数费（元/笔）：</label>
				<div class="col-sm-8">
					<input id="d0fee" name="d0fee"  class="form-control" type="text" value="">
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
var mertype = $("#mertype").val();
if(mertype == "1"){
	$("#mertype").val("体验用户");
}
if(mertype == "2"){
	$("#mertype").val("终身用户");
}
var aislecode = $("#aisleCode").val();
if(aislecode == "yb"){
	$("#aislecodeName").val("快捷多商户");
}
if(aislecode == "rf"){
	$("#aislecodeName").val("银联快捷R");
}
if(aislecode == "zf01"){
	$("#aislecodeName").val("银联快捷Z");
}
if(aislecode == "easy"){
	$("#aislecodeName").val("易生");
}
if(aislecode == "yb01"){
	$("#aislecodeName").val("银联快捷Y");
}
if(aislecode == "xj"){
	$("#aislecodeName").val("银联快捷X");
}
if(aislecode == "ld01"){
	$("#aislecodeName").val("落地通道L");
}
if(aislecode == "ld02"){
	$("#aislecodeName").val("落地通道Y");
}
if(aislecode == "hz"){
	$("#aislecodeName").val("银联快捷H");
}
if(aislecode == "xjwk"){
	$("#aislecodeName").val("大额落地X");
}
if(aislecode == "ld03"){
	$("#aislecodeName").val("落地通道X");
}
if(aislecode == "hz01"){
	$("#aislecodeName").val("银联快捷H1");
}
if(aislecode == "ld04"){
	$("#aislecodeName").val("落地还款X");
}
if(aislecode == "cj"){
	$("#aislecodeName").val("大额快捷C");
}
if(aislecode == "ld05"){
	$("#aislecodeName").val("落地还款T");
}
if(aislecode == "ld06"){
	$("#aislecodeName").val("大额还款K");
}
if(aislecode == "ld07"){
	$("#aislecodeName").val("落地大额H");
}
if(aislecode == "ld12"){
    $("#aislecodeName").val("落地大额C");
}
if(aislecode == "ld09"){
    $("#aislecodeName").val("落地还款C");
}
if(aislecode == "ld11"){
    $("#aislecodeName").val("落地还款NT");
}
if(aislecode == "ld14"){
    $("#aislecodeName").val("小额落地HB");
}
if(aislecode == "ld15"){
    $("#aislecodeName").val("落地大额C2");
}
if(aislecode == "kft"){
	$("#aislecodeName").val("大额快捷K");
}
$("#rate").val(parseFloat(${MerchantsRate.rate*100}).toFixed(3));
$("#d0fee").val(${MerchantsRate.d0fee/100});
</script>
<script type="text/javascript">

 $(function(){
  	save = function(obj) {
  		var rate = $("#rate").val();
  		if(rate<0.58){
  			alert("费率不能低于0.58");
  		} else if($("#merChantsRateForm").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/merchantsRate/update.shtml",
				data: $('#merChantsRateForm').serializeArray(),
				success: function(data){
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#merChantsRate').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "error") {
						layer.confirm('保存失败!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#merChantsRate').bootstrapTable('refresh');
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