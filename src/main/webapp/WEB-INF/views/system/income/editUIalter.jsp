<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="roleFormalterIncome">
			<div class="form-group">
				<label class="col-sm-3 control-label">等级：</label>
				<div class="col-sm-8">
					<input id="level" name="level"  class="form-control" type="text" value="${income.level }" >
						<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">还款直接分润：</label>
				<div class="col-sm-8">
					<input id="first" name="first"  class="form-control" type="text" value="${income.first }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">还款间接分润：</label>
				<div class="col-sm-8">
					<input id="second" name="second"  class="form-control" type="text" value="${income.second }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">还款间间接分润：</label>
				<div class="col-sm-8">
					<input id="third" name="third"  class="form-control" type="text" value="${income.third }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">无卡直接分润：</label>
				<div class="col-sm-8">
					<input id="brushFirst" name="brushFirst"  class="form-control" type="text" value="${income.brushFirst }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">无卡间接分润：</label>
				<div class="col-sm-8">
					<input id="brushSecond" name="brushSecond"  class="form-control" type="text" value="${income.brushSecond }">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">无卡间间接分润：</label>
				<div class="col-sm-8">
					<input id="brushThird" name="brushThird"  class="form-control" type="text" value="${income.brushThird }">
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
$("#first").val((${income.first*10000 }).toFixed(2));
$("#second").val((${income.second*10000 }).toFixed(2));
$("#third").val((${income.third*10000 }).toFixed(2));
$("#brushFirst").val((${income.brushFirst*10000 }).toFixed(2));
$("#brushSecond").val((${income.brushSecond*10000}).toFixed(2));
$("#brushThird").val((${income.brushThird*10000}).toFixed(2));
</script>
<script type="text/javascript">

 $(function(){
  	save = function(obj) {
  		if($("#roleFormalterIncome").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Income/alter.shtml",
				data: $('#roleFormalterIncome').serializeArray(),
				success: function(data){
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('#incomeTable').bootstrapTable('refresh');
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