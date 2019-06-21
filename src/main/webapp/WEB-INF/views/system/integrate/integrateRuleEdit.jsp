<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style>

</style>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="integrateRuleedit">
			<input id="id" name="id" type="hidden" value="${goods.list[0].id}"> 
			<div class="form-group">
				<label class="col-sm-4 control-label">交易满多少元获得1积分：</label>
				<div class="col-sm-8">
					<input id="pay" name="pay"  class="form-control" type="text" value="${goods.list[0].pay }"> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">实名认证获得积分数：</label>
				<div class="col-sm-8">
					<input id="realName" name="realName"  class="form-control" type="text" value="${goods.list[0].realName }"> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">签到获得积分数（基数）*天：</label>
				<div class="col-sm-8">
					<input id="checkin" name="checkin"  class="form-control" type="text" value="${goods.list[0].checkin }"> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">积分规则：</label>
				<div class="col-sm-8">
					<textarea id="remarks" name="remarks" rows="2" cols="25" class="form-control" >
					${goods.list[0].remarks }
					</textarea> 
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

 $(function(){
  	save = function(obj) {
  		if($("#integrateRuleedit").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Integrage/IntegrateRuleSet.shtml",
				timeout : 50000, //超时时间设置，单位毫秒
				data: $("#integrateRuleedit").serializeArray(),
				success: function(data){
						layer.confirm(data, function(index) {
							battcn.closeWindow();
							$('#integrateRuleTable').bootstrapTable('refresh');
				        	return false;
 						});
				}
			});
  		}
	} 
 }); 
</script>