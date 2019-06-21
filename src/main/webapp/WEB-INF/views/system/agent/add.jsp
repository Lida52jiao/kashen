<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="agent">
			<div class="form-group">
				<label class="col-sm-3 control-label">名称：</label>
				<div class="col-sm-8">
					<input id="userName" name="userName"  class="agent" type="text" value="">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">邮箱：</label>
				<div class="col-sm-8">
					<input id="email" name="email"  class="agent" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">手机号：</label>
				<div class="col-sm-8">
					<input id="tel" name="tel"  class="agent" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">账号：</label>
				<div class="col-sm-8">
					<input id="accountName" name="accountName"  class="agent" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">密码：</label>
				<div class="col-sm-8">
					<input id="passWord" name="passWord"  class="agent" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">选择代理商：</label>
				<div class="col-sm-8">
				<select id="merId" name="merId"  class="agent">
	  					<c:forEach var="agent" items="${agent}" >
             			<option value="${agent.merId}">${agent.merName}</option>
      				    </c:forEach>
	  					</select>
					<span class="input-group-btn">
					</span>
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
				<label class="col-sm-3 control-label"><input type="button" value="提交" onclick="saveagents()"></label>
				<label class="col-sm-3 control-label"><input type="button" value="重置" onclick="reset()"></label>	
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
  	saveagents = function(obj) {
  		if($("#agent").valid()){
  			layer.confirm('已提交')
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Agent/adds.shtml",
				data: $('#agent').serializeArray(),
				success: function(data){
					if(data == "f"){
						layer.confirm('请重新新增', function(index) {
							battcn.closeWindow();
							$('.agent').val("");
							$('#agent').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "fail"){
						layer.confirm('账户已存在', function(index) {
							battcn.closeWindow();
							$('.agent').val("");
							$('#agent').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('.agent').val("");
							$('#agent').bootstrapTable('refresh');
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