<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="rewardFormalter">
			 <input id="id" name="id"  class="form-control" type="hidden" value="${reward.id }" >
			<div class="form-group">
				<label class="col-sm-3 control-label">平台(%)：</label>
				<div class="col-sm-8">
					<input id="institutionid" name="institutionid"  class="form-control" type="text" value="0"
					onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
					 onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">一级代理(%)：</label>
				<div class="col-sm-8">
					<input id="firstagent" name="firstagent"  class="form-control" type="text" value="0" 
					onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
					 onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">二级代理(%)：</label>
				<div class="col-sm-8">
					<input id="secondagent" name="secondagent"  class="form-control" type="text" value="0"
					onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
					 onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">会员(%)：</label>
				<div class="col-sm-8">
					<input id="member" name="member"  class="form-control" type="text" value="0"
					onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
					 onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">直推人(%)：</label>
				<div class="col-sm-8">
					<input id="referee" name="referee"  class="form-control" type="text" value="0"
					 onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
					 onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^0-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}">
						<span class="help-block m-b-none">
				</div>
			</div>
		</form>
	</div>
</div>
<script>
   $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>
<script>
$("#institutionid").val(${reward.institutionid*100  });
$("#firstagent").val(${reward.firstagent*100  });
$("#secondagent").val(${reward.secondagent*100  });
$("#member").val(${reward.member*100 });
$("#referee").val(${reward.referee*100  });
</script>
<script type="text/javascript">

 $(function(){
  	save = function(obj) {
  		if($("#rewardFormalter").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Reward/alter.shtml",
				data: $('#rewardFormalter').serializeArray(),
				success: function(data){
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#rewardTable').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "error") {
						layer.confirm('更改失败', function(index) {
							battcn.closeWindow();
							$('#rewardTable').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "notHundred") {
						layer.confirm('调整不合理，不满足相加100%', function(index) {
							battcn.closeWindow();
							$('#rewardTable').bootstrapTable('refresh');
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