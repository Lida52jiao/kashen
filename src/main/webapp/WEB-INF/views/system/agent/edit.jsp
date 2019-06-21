<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="Agent">
			<div class="form-group">
				<label class="col-sm-3 control-label">名称：</label>
				<div class="col-sm-8">
					<input id="merName" name="merName"  class="input" type="text" value="">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">邮箱：</label>
				<div class="col-sm-8">
					<input id="mailbox" name="mailbox"  class="input" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">手机号：</label>
				<div class="col-sm-8">
					<input id="merMp" name="merMp"  class="input" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">账号：</label>
				<div class="col-sm-8">
					<input id="accountNumber" name="accountNumber"  class="input" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">密码：</label>
				<div class="col-sm-8">
					<input id="password" name="password"  class="input" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">上级代理商编号：</label>
				<div class="col-sm-8">
					<input id="oneMerIds" name="oneMerId"  class="t" type="text" value="">
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
			<div class="form-group">
				<span  style="margin-left:300px;" ><input class="btn btn btn-primary" type="button" value="提交" onclick="saveAgents()"></span>
				<span  style="margin-left:100px;" ><input class="btn btn btn-primary" type="button" value="重置" onclick="reset()"></span>	
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
  	saveAgents = function(obj) {
  		var temp = document.getElementById("mailbox");
  		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
  		var mypho = document.getElementById("merMp");
  		//var mobile=/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
		var mobile=/^[1][0-9][0-9]{9}$/;
  		var oneMerId = document.getElementById("oneMerIds");
  		var tt = /^[CT][0-9]+$/;
  		if($("#merName").val() == ''){
			alert('名称不能为空')
		}else if($("#merName").val().indexOf(' ') >= 0){
			alert('请输入正确的名称格式')
		}else if($("#mailbox").val() == ''){
			alert('邮箱不能为空')
		}else if(!myreg.test(temp.value)){
			alert('请输入正确的邮箱格式')
		}else if($("#merMp").val() == ''){
			alert('手机号不能为空')
		}else if(!mobile.test(mypho.value)){
			alert('请输入正确的手机号码')
		}else if($("#accountNumber").val() == ''){
			alert('账号不能为空')
		}else if($("#accountNumber").val().indexOf(' ') >= 0){
			alert('账号不能有空格')
		}else if($("#password").val() == ''){
			alert('密码不能为空')
		}else if($("#password").val().indexOf(' ') >= 0){
			alert('密码不能有空格')
		}else if($("#oneMerIds").val() == ''){
			alert('请输入代理商编号')
		}else if($("#oneMerIds").val().indexOf(' ') >= 0){
			alert('代理商编号不能有空格')
		}else if(!tt.test(oneMerId.value)){
			alert('代理商编号格式有问题')
		}else if($("#Agent").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Agent/add.shtml",
				data: $('#Agent').serializeArray(),
				success: function(data){
					if(data == "f"){
						layer.confirm('请重新开户', function(index) {
							battcn.closeWindow();
							$('.input').val("");
							$('#Agent').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "fail"){
						layer.confirm('账户已存在', function(index) {
							battcn.closeWindow();
							$('.input').val("");
							$('#Agent').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('.input').val("");
							$('#Agent').bootstrapTable('refresh');
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