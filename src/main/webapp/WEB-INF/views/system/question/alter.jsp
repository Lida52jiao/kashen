<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="interlocutionForm">
			<input type="hidden" name="ids" value="${s.id }"/>
			<div class="form-group">
				<label class="col-sm-3 control-label">问题：</label>
				<div class="col-sm-8">
					<input id="question" name="questions"  class="form-control" type="text" value="${s.question }">
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<%-- <div class="form-group">
				<label class="col-sm-3 control-label">名称：</label>
				<div class="col-sm-8">
					<input id="merName" name="merName"  class="form-control" type="text" value="${merChants.merName }">
						<span class="help-block m-b-none">
				</div>
			</div> --%>
			<div class="form-group">
				<label class="col-sm-3 control-label">答案：</label>
				<div class="col-sm-8">
					<input id="answer" name="answers"  class="form-control" type="text" value="${s.answer }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">跳转链接：</label>
				<div class="col-sm-8">
					<input id="forwardURL" name="forwardURLs"  class="form-control" type="text" value="${s.forwardURL }">
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
  		if($("#interlocutionForm").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Question/alters.shtml",
				data: $('#interlocutionForm').serializeArray(),
				success: function(data){
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#interlocution').bootstrapTable('refresh');
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