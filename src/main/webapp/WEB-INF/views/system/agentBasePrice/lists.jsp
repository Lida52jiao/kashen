<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="baseFormalter">
			 <input id="id" name="id"  class="form-control" type="hidden" value="${base.id }" >
			<div class="form-group">
				<label class="col-sm-4 control-label">还款费率(%)：</label>
				<div class="col-sm-8">
					<input id="repayfee" name="repayfee"  class="form-control" type="text" value="0">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">无卡无积分费率(%)：</label>
				<div class="col-sm-8">
					<input id="nocardintegralfee" name="nocardintegralfee"  class="form-control" type="text" value="0"> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">无卡无积分笔数费(元/笔)：</label>
				<div class="col-sm-8">
					<input id="nocardintegraldfee" name="nocardintegraldfee"  class="form-control" type="text" value="0">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">无卡有积分费率(%)：</label>
				<div class="col-sm-8">
					<input id="hcardintegralfee" name="hcardintegralfee"  class="form-control" type="text" value="0">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">无卡有积分笔数费(元/笔)：</label>
				<div class="col-sm-8">
					<input id="hcardintegraldfee" name="hcardintegraldfee"  class="form-control" type="text" value="0"
					>
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
$("#repayfee").val(parseFloat(${base.repayfee/10}).toFixed(2));
$("#nocardintegralfee").val(parseFloat(${base.nocardintegralfee/10}).toFixed(2));
$("#nocardintegraldfee").val(${base.nocardintegraldfee/100});
$("#hcardintegralfee").val(parseFloat(${base.hcardintegralfee/10}).toFixed(2));
$("#hcardintegraldfee").val(${base.hcardintegraldfee/100});
</script>
<script type="text/javascript">

 $(function(){
  	save = function(obj) {
  		if($("#baseFormalter").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/AgentBasePrice/alter.shtml",
				data: $('#baseFormalter').serializeArray(),
				success: function(data){
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#basePriceTable').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "error") {
						layer.confirm('更改失败', function(index) {
							battcn.closeWindow();
							$('#basePriceTable').bootstrapTable('refresh');
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