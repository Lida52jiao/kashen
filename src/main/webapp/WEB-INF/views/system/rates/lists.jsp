<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="RateFormalter">
			<input id="agentid" name="agentid"  class="form-control" type="hidden" value="${merId}" >
			<input id="merChantid" name="merchantid"  class="form-control" type="hidden" value="${merChantId}" >
			<div class="form-group">
				<label class="col-sm-4 control-label">通道名称：</label>
				<div class="col-sm-8">
					<select name="aislecode" id="aislecode" class="input form-control" >
						<option value ="">请选择网关  </option>
						<c:forEach items="${key}" var="key">
							<c:if test="${key.aislecode=='ld01'}">
								<option value ="${key.aislecode}">落地通道L  </option>
							</c:if>
							<c:if test="${key.aislecode=='ld02'}">
								<option value ="${key.aislecode}">落地通道Y  </option>
							</c:if>
							<c:if test="${key.aislecode=='ld03'}">
								<option value ="${key.aislecode}">落地通道X  </option>
							</c:if>
							<c:if test="${key.aislecode=='ld04'}">
								<option value ="${key.aislecode}">落地还款X  </option>
							</c:if>
							<c:if test="${key.aislecode=='ld05'}">
								<option value ="${key.aislecode}">落地还款T  </option>
							</c:if>
							<c:if test="${key.aislecode=='ld06'}">
								<option value ="${key.aislecode}">大额还款K </option>
							</c:if>
							<c:if test="${key.aislecode=='ld07'}">
								<option value ="${key.aislecode}">大额还款H </option>
							</c:if>
                            <c:if test="${key.aislecode=='ld09'}">
                                <option value ="${key.aislecode}">落地还款C  </option>
                            </c:if>
							<c:if test="${key.aislecode=='ld11'}">
								<option value ="${key.aislecode}">落地还款NT  </option>
							</c:if>
							<c:if test="${key.aislecode=='ld12'}">
								<option value ="${key.aislecode}">落地大额C  </option>
							</c:if>
							<c:if test="${key.aislecode=='ld14'}">
								<option value ="${key.aislecode}">小额落地HB </option>
							</c:if>
							<c:if test="${key.aislecode=='ld15'}">
								<option value ="${key.aislecode}">大额落地C2 </option>
							</c:if>
							<c:if test="${key.aislecode=='ld16'}">
								<option value ="${key.aislecode}">小额落地C2 </option>
							</c:if>
							<c:if test="${key.aislecode=='ld13'}">
								<option value ="${key.aislecode}">落地小额D </option>
							</c:if>
							<c:if test="${key.aislecode=='ld17'}">
								<option value ="${key.aislecode}">组合计划T </option>
							</c:if>
							<c:if test="${key.aislecode=='ybq'}">
								<option value ="${key.aislecode}">大额快捷M </option>
							</c:if>
							<c:if test="${key.aislecode=='sq'}">
								<option value ="${key.aislecode}">小额落地S </option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-4 control-label">对应费率(%)：</label>
				<div class="col-sm-8">
					<input id="rate" name="rate"  class="form-control" type="text" value="0">
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


</script>
<script type="text/javascript">

	$(function(){
		save = function(obj) {
			if($("#aislecode option:selected").val() == ''){
				alert("请选择要设置的通道")
			} else if($("#RateFormalter").valid()){
				layer.confirm('如果您是批量修改,时间较长请耐心等待！！！！');
				$.ajax({
					type: "POST",
					url: rootPath + "/Rates/alert.shtml",
					timeout : 50000, //超时时间设置，单位毫秒
					data: $('#RateFormalter').serializeArray(),
					success: function(data){
						if(data == "success") {
							layer.confirm('保存成功!是否关闭窗口?', function(index) {
								battcn.closeWindow();
								$('#RateTable').bootstrapTable('refresh');
								return false;
							});
						}
						if(data == "error") {
							layer.confirm('更改失败', function(index) {
								battcn.closeWindow();
								$('#RateTable').bootstrapTable('refresh');
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