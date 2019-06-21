<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="Interlocutions">
          	<div class="form-group">
          		<label class="col-sm-3 control-label">类型：</label>
				<div class="col-sm-8">
					<select id="title" name="title" class="floorNumber" >
            		<option value ="">请选择问答类型 </option>
                   <c:forEach items="${t}" var="t">
                  <option value ="${t.id}">${t.type}</option>
                </c:forEach>
              </select>
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
          		<label class="col-sm-3 control-label">所属app：</label>
				<div class="col-sm-8">
					<select id="appId" name="appId" class="floorNumber" >
            		<option value ="">请选择app </option>
                   <c:forEach items="${s}" var="s">
                  <option value ="${s.appId}">${s.appName}</option>
                </c:forEach>
              </select>
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">问题：</label>
				<div class="col-sm-8">
					<input id="question" name="question"  class="floorNumber" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">答案：</label>
				<div class="col-sm-8">
					<input id="answer" name="answer"  class="floorNumber" type="text" value="">
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">跳转链接：</label>
				<div class="col-sm-8">
					<input id="answers" name="answers"  class="floorNumber" type="text" value="">
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
				<span  style="margin-left:300px;" ><input class="btn btn btn-primary" type="button" value="提交" onclick="saveInterlocution()"></span>
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
  	saveInterlocution = function(obj) {
  		if($("#Interlocutions").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Question/add.shtml",
				data: $('#Interlocutions').serializeArray(),
				success: function(data){
					if(data == "f"){
						layer.confirm('填完整信息', function(index) {
							battcn.closeWindow();
							$('.Interlocution').val("");
							$('#Interlocution').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "success") {
						layer.confirm('发送成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							$('.Interlocution').val("");
							$('#Interlocution').bootstrapTable('refresh');
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