<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form role="form" class="form-inline" id="twoMenuListForm">
			<div class="input-group">
				<c:forEach items="${menu}" var="key">
			 		<input id="menuId" name="menuId" type="hidden" value="${key.id }">
			 		<div class="col-sm-3">
			 			<input class="col-sm-9 btn btn btn-primary" type="button" value="${key.name }">
			 			<input id="level" name="level" class="form-control" value="${key.level }">
			 		</div>
				</c:forEach>
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
	 		if($("#twoMenuListForm").valid()){
	 			layer.confirm('修改很漫长，请耐心等待！！！！');
	 			$.ajax({
				type: "POST", 
				url: rootPath + "/MenuOrder/updateMenuOrder.shtml",
				data: $('#twoMenuListForm').serializeArray(),
				success: function(data){
						layer.confirm(data, function(index) {
							location.reload();
							battcn.closeWindow();
							});
				}
			});
	 		}
		} 
	}); 
</script>