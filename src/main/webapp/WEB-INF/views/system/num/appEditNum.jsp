<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="roleFormalterNumT">
		<input id="id" name="id"  class="form-control" type="hidden" value="${num.id}" >
			<div class="form-group">
				<label class="col-sm-3 control-label">金额：</label>
				<div class="col-sm-8">
					<input id="num" name="num"  class="form-control" onblur="numAll()" type="text" value="${num.num}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">有效期（天）：</label>
				<div class="col-sm-8">
					<input id="validtime" name="validtime"  class="form-control" type="text" value="${num.validtime}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">直推：</label>
				<div class="col-sm-8">
					<input id="onemerchant" name="onemerchant"  class="form-control" type="text" value="${num.onemerchant}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">间推：</label>
				<div class="col-sm-8">
					<input id="twomerchant" name="twomerchant"  class="form-control" type="text" value="${num.twomerchant}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">间间推：</label>
				<div class="col-sm-8">
					<input id="threemerchant" name="threemerchant"  class="form-control" type="text" value="${num.threemerchant}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">直接代理：</label>
				<div class="col-sm-8">
					<input id="oneagent" name="oneagent"  class="form-control" type="text" value="${num.oneagent}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">间接代理：</label>
				<div class="col-sm-8">
					<input id="twoagent" name="twoagent"  class="form-control" type="text" value="${num.twoagent}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">县级代理：</label>
				<div class="col-sm-8">
					<input id="countyagent" name="countyagent"  class="form-control" type="text" value="${num.countyagent}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">市级代理：</label>
				<div class="col-sm-8">
					<input id="cityagent" name="cityagent"  class="form-control" type="text" value="${num.cityagent}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">省级代理：</label>
				<div class="col-sm-8">
					<input id="provinceagent" name="provinceagent"  class="form-control" type="text" value="${num.provinceagent}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">顶级代理：</label>
				<div class="col-sm-8">
					<input id="topagent" name="topagent"  class="form-control" type="text" value="${num.topagent}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">平台：</label>
				<div class="col-sm-8">
					<input id="institution" name="institution"  class="form-control" type="text" value="${num.institution}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">推荐人数：</label>
				<div class="col-sm-8">
					<input id="amount" name="amount"  class="form-control" type="text" value="${num.amount}">
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
function numAll(){
	var amount = $("#num").val();
	if(amount > 5000){
		layer.confirm("升级金额大于5000无法使用在线付费，系统默认联系客户!!");
	}

}
</script>
<script type="text/javascript">

 $(function(){
  	save = function(obj) {
  		if($("#roleFormalterNumT").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Num/alertT.shtml",
				data: $('#roleFormalterNumT').serializeArray(),
				success: function(data){
						layer.confirm(data, function(index) {
							battcn.closeWindow();
							$('#numTTables').bootstrapTable('refresh');
				        	return false;
 						});
				}
			});
  		}
	} 
	
 }); 
</script>