<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
	<div class="ibox-content">
		<form enctype="multipart/form-data" class="form-horizontal m-t required-validate" id="IPush">
			 <div class="doc-buttons">
				<c:forEach items="${res}" var="key">
					 ${key.description}  
				</c:forEach>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">app：</label>
				<div class="col-sm-8">
				<select name="appName">
             	<option value="${appName.appName}">${appName.appName}</option>
				</select>
			</div>
			</div>
            <div class="form-group">
                <label class="col-sm-3 control-label">上传图片：</label>
                <div class="col-sm-8">
                    <input id="imgUrl" name="imgUrl"  class="inPush" type="file" value="">
                    <span class="help-block m-b-none"></span>
                </div>
            </div>
			<div class="form-group">
				<%--<label class="col-sm-3 control-label">内容：</label>
				<div class="col-sm-8">
					<textarea name="msg" rows="5" cols="35" class="inPush"
							  onkeyup="huitextarealength(this)"></textarea>
					<p class="textarea-numberbar">
						<em class="textarea-length">0</em>/
						<am>100</am>
					</p>
					<span class="help-block m-b-none"></span>
				</div>--%>
				<label class="col-sm-3 control-label">内容：</label>
				<div class="col-sm-8">
				<textarea onkeydown="checkMaxInput(this,300)"
						  onkeyup="checkMaxInput(this,300)"
						  onfocus="checkMaxInput(this,300)"
						  onblur="checkMaxInput(this,300);resetMaxmsg()"
						  style="width:99%;height:190px;"
						  placeholder="这里写内容"
							name="msg" class="inPush"></textarea>
				<span class="help-block m-b-none"></span>
				</div>
			</div>
            <div class="form-group">
                <label class="col-sm-3 control-label">标题:</label>
                <div class="col-sm-8">
                    <input id="title" name="title"  class="inPush" type="text" value=""/>
                </div>
            </div>
			<div class="form-group">
				<label class="col-sm-3 control-label">动作路径:</label>
				<div class="col-sm-8">
					<input id="actionPath" name="actionPath"  class="inPush" type="text" value="">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><input type="button" value="提交" onclick="savePush()"></label>
				<label class="col-sm-3 control-label"><input type="button" value="重置" onclick="reset()"></label>	
			</div> 
		</form>
			<div class="table-responsive">
				<table id="inPush" data-toolbar="#toolbar"
					data-show-refresh="true" data-show-toggle="true"
					data-show-columns="true" data-show-export="true"
					data-show-footer="false" data-mobile-responsive="true">
				</table>
			</div>
	</div>
</div>
<script>
   $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>
<script type="text/javascript">
    function checkMaxInput(obj, maxLen) {
        if (obj == null || obj == undefined || obj == "") {
            return;
        }
        if (maxLen == null || maxLen == undefined || maxLen == "") {
            maxLen = 100;
        }
        var strResult;
        var $obj = $(obj);
        var newid = $obj.attr("id") + 'msg';
        if (obj.value.length > maxLen) { //如果输入的字数超过了限制
            obj.value = obj.value.substring(0, maxLen); //就去掉多余的字
            strResult = '<div style="text-align:right;"><span id="' + newid + '" class=\'Max_msg clearfix\' >剩余：' + (maxLen - obj.value.length) + '字</span></div>'; //计算并显示剩余字数
        }
        else {
            strResult = '<div style="text-align:right;"><span id="' + newid + '" class=\'Max_msg clearfix\' >剩余：' + (maxLen - obj.value.length) + '字</span></div>'; //计算并显示剩余字数
        }
        var $msg = $("#" + newid);
        if ($msg.length == 0) {
            $obj.after(strResult);
        }
        else {
            $msg.html(strResult);
        }
    }
    //清空剩除字数提醒信息
    function resetMaxmsg() {
        $("span.Max_msg").remove();
    }
 $(function(){
     savePush = function(obj) {
         var form = new FormData(document.getElementById("IPush"));
         if($("#IPush").valid()){
             layer.confirm('上传图片时间较长请耐心等待！');
             $.ajax({
                 type: "POST",
                 url: rootPath + "/indexPush/addPush.shtml",
                 data: form,
                 contentType:false,
                 processData:false,
                 success: function(data){
                     if(data == "s") {
                         layer.confirm('请填写完整', function(index) {
                             battcn.closeWindow();
                             $('.inPush').val("");
                             $('#inPush').bootstrapTable('refresh');
                             return false;
                         });
                     }
                     if(data == "f") {
                         layer.confirm('上传失败', function(index) {
                             battcn.closeWindow();
                             $('.inPush').val("");
                             $('#inPush').bootstrapTable('refresh');
                             return false;
                         });
                     }
                     if(data == "fail") {
                         layer.confirm('请选择图片', function(index) {
                             battcn.closeWindow();
                             $('.inPush').val("");
                             $('#inPush').bootstrapTable('refresh');
                             return false;
                         });
                     }
                     if(data == "success") {
                         layer.confirm('保存成功!是否关闭窗口?', function(index) {
                             battcn.closeWindow();
                             $('.inPush').val("");
                             $('#inPush').bootstrapTable('refresh');
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
<script type="text/javascript">
	function getUserIdSelectnews() {
		return $.map($("#inPush").bootstrapTable('getSelections'), function(
				row) {
			return row.id
		});
	}
	function deletePush() {
		var cbox = getUserIdSelectnews();
		if (cbox == "") {
			layer.msg("请选择删除项！！");
			return;
		}
		layer.confirm('是否删除？', function(index) {
			var url = rootPath + '/indexPush/delete.shtml?id=' + cbox;
			var result = CommnUtil.ajax(url);
			if (result == "success") {
				$('#inPush').bootstrapTable('refresh');
				layer.msg('删除成功');
			} else {
				layer.msg('删除失败');
			}
		});
	}
	
	//查询:目前只用一个参数,如果多个 请用 $("#A").val() !='' ||$("#B").val() !=''....
	function agentSearchnews() {
		$('#inPush').bootstrapTable('refresh');
	}
	//重写参数传递
	 function queryParamsnews(params) {
		var pageSize = params.limit;
		var sort = params.sort;
		var offset = params.offset;
		var order = params.order;
         var appName = $("#appName").val();
         var msg = $("#msg").val();
         var imgUrl = $("#imgUrl").val();
         var actionPath = $("#actionPath").val();
         var creatDate = $("#creatDate").val();
         var title = $("#title").val();


		var pageNum = offset / pageSize + 1;
		return {
			pageSize : pageSize,
			pageNum : pageNum,
			sort : sort,
			order : order,
            appName : appName,
            msg : msg,
            imgUrl : imgUrl,
            actionPath : actionPath,
            creatDate : creatDate,
			title : title
		}
	} 
	$('#inPush').bootstrapTable({
		url : rootPath + '/indexPush/getPush.shtml',
		height : '100%',
		sortName : 'id',
		sortOrder : 'desc',
		ajaxOptions: {async:true,timeout:50000},
		showColumns : true,
		showExport : true,
		striped : true,
		pagination : true,
		pageNumber : 1,
		pageSize : 10,
		pageList : "[10,20,30,All]",
		search : false,
		sidePagination : 'server',//服务端分页  client //客户端分页
		idField : 'id',
		uniqueId : 'id',
		responseHandler : responseHandler, //处理分页函数
		queryParams : queryParamsnews,//参数处理函数
		minimumCountColumns : 2,
		columns : [ {
			checkbox : true
		}, {
			field : 'appName',
			title : '推送给谁',
			align : 'center',
			valign : 'middle'
		}, {
			field : 'msg',
			title : '推送内容',
			align : 'center',
			valign : 'middle'
		}, {
            field : 'imgUrl',
            title : '推送图片',
            align : 'center',
            valign : 'middle',
            formatter:function (value) {
                return "<a href="+value+" target='_blank'>预览</a>"

            }
        }, {
            field : 'title',
            title : '标题',
            align : 'center',
            valign : 'middle'
        },{
            field : 'actionPath',
            title : '动作路径',
            align : 'center',
            valign : 'middle'
        }, {
			field : 'creatDate',
			title : '推送日期',
			align : 'center',
			valign : 'middle',
			sortable: true
		}]
	});
        //转换日期格式(时间戳转换为datetime格式)
    	function changeDateFormat(cellval) {
        var dateVal = cellval + "";
        if (cellval != null) {
            var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
            
            return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
        }
    }
</script>