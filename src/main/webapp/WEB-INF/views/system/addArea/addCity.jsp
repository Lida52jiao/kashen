<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=basePath%>js/country_1.js"></script>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form class="form-horizontal m-t required-validate" id="addCityFrom">
			<div class="form-group">
				<div class="form-group">
					<label class="col-sm-3 control-label">选择添加地区等级：
						<input class="addCityNumber" value="1" name="area" onclick="show()" type='radio'>市级区域
						<input class="addCityNumber" value="2" name="area" onclick="hide()" type='radio'>县级区域
					</label>
					<%--<div class="col-sm-8">--%>
						<%--<div class="radio i-checks">--%>
							<%--<label><input class="addCityNumber" type="radio" value="1" name="area"  onclick='show()'> <i></i> 市级区域</label>--%>
							<%--<label><input class="addCityNumber" type="radio" value="2" name="area"  onclick='hide()'> <i></i> 县级区域</label>--%>
						<%--</div>--%>
					<%--</div>--%>
				</div>
				<div>
					<label class="col-sm-3 control-label" >选择绑定的区域(省)：</label><br>
					<select class="addCityNumber" id="province" name="province" next="city"><option value="-1">=省级=</option></select>
					<span class="help-block m-b-none"></span>
				</div>
				<div id = "aa" >
					<label class="col-sm-3 control-label" >选择绑定的区域(市)：</label><br>
					<select class="addCityNumber" id="city"  name="city" next="region"><option value="-1">=市级=</option></select>
					<span class="help-block m-b-none"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">添加地区名称：</label>
				<div class="col-sm-8">
					<input id="area_name" name="area_name"  class="banner" type="text" value="">
					<span class="help-block m-b-none"></span>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">地区码：</label>
				<div class="col-sm-8">
					<input id="code" name="code"  class="banner" type="text" value="">
					<span class="help-block m-b-none"></span>
				</div>
			</div>
			<div class="form-group">
				<span  style="margin-left:300px;" ><input class="btn btn btn-primary" type="button" value="提交" onclick="addAreafun()"></span>
				<span  style="margin-left:100px;" ><input class="btn btn btn-primary" type="button" value="重置" onclick="reset()"></span>
			</div>
		</form>
	</div>
</div>
<script>
    $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>
<script>


</script>
<script type="text/javascript">


    // function divClick(){
	//
    //     var show="";
    //     var apm = document.getElementsByName("area");
    //     for(var i=0;i<apm.length;i++){
    //         if(apm[i].checked)
    //             show = apm[i].value;
    //     }
	//
    //     switch (show){
    //         case '1':
    //             document.getElementById("1").style.display="none";
    //             document.getElementById("2").style.display="block";
    //             break;
    //         case '2':
    //             document.getElementById("1").style.display="block";
    //             document.getElementById("2").style.display="none";
    //             break;
    //     }
    // }
    function show(){
        var a= document.getElementById("aa");
    	a.style.display = 'none'

    }
    function hide(){
        document.getElementById("aa").style.display = "block";
    }


    $(function(){
        addAreafun = function(obj) {
            var code = $("#code").val();
            var area_name = $("#area_name").val();
            var province = $("#province").val();
            var city = $("#city").val();
            var area =  $("input[name='area']:checked").val();
            if($("#addCityFrom").valid()){
                $.ajax({
                    type: "POST",
                    url: rootPath + "/addArea/addCity.shtml?code="+code+"&province="+province+"&city="+city+"&area_name="+area_name+"&area="+area,
                    /*data: $('#addCityFrom').serializeArray(),*/
                    success: function(data){
                        if(data === "Can not be empty") {
                            layer.confirm('不能为空', function(index) {
                                battcn.closeWindow();
                                $('.addCityNumber').val("");
                                return false;
                            });
                        }
                        if(data == "success") {
                        layer.confirm("添加地区成功", function(index) {
                            battcn.closeWindow();
                            $('.addCityNumber').val("");
                            return false;
                        });
                        }
                        if(data == "fail") {
                            layer.confirm("添加失败", function(index) {
                                battcn.closeWindow();
                                $('.addCityNumber').val("");
                                return false;
                            });
                        }
                    }
                });
            }
        }
    });
</script>