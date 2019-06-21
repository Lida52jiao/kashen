<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="addAgentLevelForm">
			 <input id="id" name="id"  class="form-control" type="hidden" value="">
			<div class="form-group">
				<label class="col-sm-3 control-label">等级名称：</label>
				<div class="col-sm-8">
					<input id="levelname" name="levelname"  class="form-control" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
		 	<div class="form-group">
				<label class="col-sm-3 control-label">等级图标：</label>
				<div class="col-sm-8">
					<input id="levellogo" name="levellogo"  class="form-control" type="file" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">等级卡背：</label>
				<div class="col-sm-8">
					<input id="levelbcard" name="levelbcard"  class="form-control" type="file" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">等级权重：</label>
				<div class="col-sm-8">
					<input id="levelweight" name="levelweight"  class="form-control" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否为代理：</label>
				<div class="col-sm-8">
					 <input name="agentornot" id="agent" type="radio" value="Y" >是
	    			 <input name="agentornot" id="notagent" type="radio" value="N">否
	    		</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否显示：</label>
				<div class="col-sm-8">
					 <input name="showornot" id="show" type="radio" value="Y" >是
	    			 <input name="showornot" id="notshow" type="radio" value="N">否
	    		</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">权益说明：</label>
				<div class="col-sm-8">
					<textarea id="funcexplain" name="funcexplain" rows="6" cols="25" class="form-control" >
					</textarea> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">可用功能：</label>
				<div class="col-sm-8">
					<textarea id="usablemodule" name="usablemodule" rows="6" cols="25" class="form-control" >
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
  		var form = new FormData(document.getElementById("addAgentLevelForm"));
  		if($("#addAgentLevelForm").valid()){
  			layer.confirm('上传中,时间较长请耐心等待！！！！');
  			$.ajax({
				type: "POST", 
				url: rootPath + "/agentMerLevel/addManage.shtml",
				data: form,
				contentType:false,
				processData:false,
				mimeType:"multipart/form-data",
				success: function(data){
						layer.confirm(data, function(index) {
							battcn.closeWindow();
							$('#agentMerLevelTable').bootstrapTable('refresh');
				        	return false;
 						});
				}
			});
  		}
	} 
	
 }); 
</script>