<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="AgentRateFormalter">
			 <input id="agentid" name="agentid"  class="form-control" type="hidden" value="${merId}" >
			 <input id="merChantid" name="merchantid"  class="form-control" type="hidden" value="${merChantId}" >
			<div class="form-group">
				<label class="col-sm-4 control-label">通道名称：</label>
				<div class="col-sm-8">
					<select name="aislecode" id="aislecode" class="input form-control" >
							<option value ="">请选择网关  </option>
			                 <c:forEach items="${key}" var="key">
			                 	<c:if test="${key.aislecode=='rf'}">
									<option value ="${key.aislecode}">银联快捷R  </option>
								</c:if>
								<c:if test="${key.aislecode=='yb'}">
									<option value ="${key.aislecode}">快捷多商户  </option>
								</c:if>
								<c:if test="${key.aislecode=='zf01'}">
									<option value ="${key.aislecode}">银联快捷Z  </option>
								</c:if>
								<c:if test="${key.aislecode=='easy'}">
									<option value ="${key.aislecode}">易生  </option>
								</c:if>
								<c:if test="${key.aislecode=='yb01'}">
									<option value ="${key.aislecode}">银联快捷Y  </option>
								</c:if>
								<c:if test="${key.aislecode=='xj'}">
									<option value ="${key.aislecode}">银联快捷X  </option>
								</c:if>
								<c:if test="${key.aislecode=='hz'}">
									<option value ="${key.aislecode}">银联快捷H  </option>
								</c:if>
								<c:if test="${key.aislecode=='xjwk'}">
									<option value ="${key.aislecode}">大额落地X  </option>
								</c:if>
								<c:if test="${key.aislecode=='hz01'}">
									<option value ="${key.aislecode}">银联快捷H1  </option>
								</c:if>
								<c:if test="${key.aislecode=='cj'}">
									<option value ="${key.aislecode}">大额快捷C  </option>
								</c:if>
								<c:if test="${key.aislecode=='kft'}">
									<option value ="${key.aislecode}">大额快捷K  </option>
								</c:if>
								 <c:if test="${key.aislecode=='ybq'}">
									 <option value ="${key.aislecode}">大额快捷M </option>
								 </c:if>
								 <c:if test="${key.aislecode=='ybc'}">
									 <option value ="${key.aislecode}">云闪付M </option>
								 </c:if>
							</c:forEach>
		  			</select>
	  			</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">对应费率(%)：</label>
				<div class="col-sm-8">
					<input id="rate" name="rate"  class="form-control" type="text" value="0"> 
					<span class="help-block m-b-none"></span>
				</div>
			</div>
		</form>
	</div>
</div>
<script>
   $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>
<script type="text/javascript">


</script>
<script type="text/javascript">

 $(function(){
  	save = function(obj) {
  		if($("#aislecode option:selected").val() == ''){
  			alert("请选择要设置的通道")
  		} else if($("#AgentRateFormalter").valid()){
  			layer.confirm('如果您是批量修改,时间较长请耐心等待！！！！');
  			$.ajax({
				type: "POST", 
				url: rootPath + "/AgentRate/alert.shtml",
				timeout : 50000, //超时时间设置，单位毫秒
				data: $('#AgentRateFormalter').serializeArray(),
				success: function(data){
					if(data == "success") {
						layer.confirm('保存成功!是否关闭窗口?', function(index) {
							battcn.closeWindow();
							//$('#agentRateTable').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "error") {
						layer.confirm('更改失败', function(index) {
							battcn.closeWindow();
							$('#agentRateTable').bootstrapTable('refresh');
				        	return false;
 						});
					}
					if(data == "height"){
						layer.confirm('设置的结算底价过高,请重新设置', function(index) {
							battcn.closeWindow();
							$('.floorNumber').val("");
							$('#Agent').bootstrapTable('refresh');
							return false;
						});
					}
					if(data == "low"){
						layer.confirm('设置的结算底价过低,请重新设置', function(index) {
							battcn.closeWindow();
							$('.floorNumber').val("");
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