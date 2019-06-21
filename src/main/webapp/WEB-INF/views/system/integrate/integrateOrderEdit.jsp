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
		<form class="form-horizontal m-t required-validate" id="integrateOrderedit">
			<input id="orderNo" name="orderNo" type="hidden" value="${goods.list[0].orderNo}"> 
			<div class="form-group">
				<label class="col-sm-4 control-label">商品名称：</label>
				<div class="col-sm-8">
					<input id="shopName" name="shopName"  class="form-control" type="text" value="${goods.list[0].shopName }"> 
						<span class="help-block m-b-none">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">兑换审批：</label>
				<div class="col-sm-8">
					 <input name="state" id="Permit" type="radio" value="1" >已下单
	    			 <input name="state" id="waitPermit" type="radio" value="2">代发货
	    			 <input name="state" id="allPermit" type="radio" value="3" >已发货
	    			 <input name="state" id="shutPermit" type="radio" value="4">已关闭
	    		</div>
			</div> 
		</form>
	</div>
</div> 
<script>
   $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>
<script type="text/javascript">
var state = ${goods.list[0].state};
if(state == 1){
	$("#Permit").attr("checked",true);
}else if(state == 2){
	$("#waitPermit").attr("checked",true);
}else if(state == 3){
	$("#allPermit").attr("checked",true);
}else if(state == 4){
	$("#shutPermit").attr("checked",true);
}
</script>
<script type="text/javascript">

 $(function(){
  	save = function(obj) {
  		if($("#integrateOrderedit").valid()){
  			$.ajax({
				type: "POST", 
				url: rootPath + "/Integrage/editIntegrateOrder.shtml",
				timeout : 50000, //超时时间设置，单位毫秒
				data:  $('#integrateOrderedit').serializeArray(),
				success: function(data){
						layer.confirm(data, function(index) {
							battcn.closeWindow();
							$('#integrateOrderTable').bootstrapTable('refresh');
				        	return false;
 						});
				}
			});
  		}
	} 
 }); 
</script>