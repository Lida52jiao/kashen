<%--
  Created by IntelliJ IDEA.
  User: Dada
  Date: 2019/1/24
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="ibox float-e-margins">
    <div class="ibox-content">
        <form class="form-horizontal m-t required-validate" id="form">
            <div class="form-group">
                <label class="col-sm-3 control-label">姓名：</label>
                <div class="col-sm-8">
                    <input id="name" name="name" class="form-control" type="text" value="${name}" readonly="readonly">
                    <span class="help-block m-b-none"></span>
                </div>
                <label class="col-sm-3 control-label">商户号：</label>
                <div class="col-sm-8">
                    <input id="merchantId" name="merChantId" class="form-control" type="text" value="${merChantId}" readonly="readonly">
                    <span class="help-block m-b-none"></span>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">身份证正面：</label>
                    <div class="col-sm-8">
                        <input id="userIDCardA" name="userIDCardA"  class="inPush" type="file" value="" onchange="yasuo(this,'userIDCardA')">
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">身份证反面：</label>
                    <div class="col-sm-8">
                        <input id="userIDCardB" name="userIDCardB"  class="inPush" type="file" value="" onchange="yasuo(this,'userIDCardB')">
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">银行卡正面：</label>
                    <div class="col-sm-8">
                        <input id="cardImgA" name="cardImgA"  class="inPush" type="file" value="" onchange="yasuo(this,'cardImgA')">
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">手持身份证正面：</label>
                    <div class="col-sm-8">
                        <input id="faceImg" name="faceImg"  class="inPush" type="file" value="" onchange="yasuo(this,'faceImg')">
                        <span class="help-block m-b-none"></span>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<script>
    $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});
</script>
<script type="text/javascript">
    // var form = new FormData(); // FormData 对象
    save = function(obj) {
        var form = new FormData(document.getElementById("form"));
        $.each(objData,function (k,v) {
            if(v){
                form.set(k,v);
            }
        });
        if($("#form").valid()){
            layer.confirm('上传图片时间较长请耐心等待！！！！');
            $.ajax({
                type: "POST",
                url: rootPath + "/MerChants/addFaceImgUrl.shtml",
                data: form,
                contentType:false,
                processData:false,
                mimeType:"multipart/form-data",
                success: function(data){
                    if(data == "merChantId") {
                        layer.confirm('商户号为空！',{btn:["确认"]}, function(index) {
                            layer.close(index);
                            return false;
                        });
                    }
                    if(data == "fail") {
                        layer.confirm('请选择资料补充文件!', {btn:["确认"]}, function(index) {
                            layer.close(index);
                            return false;
                        });
                    }
                    if(data == "f") {
                        layer.confirm('上传失败！',{btn:["确认"]}, function(index) {
                            layer.close(index);
                            return false;
                        });
                    }
                    if(data == "success") {
                        layer.confirm('补充资料成功！', {btn:["确认"]}, function(index) {
                            form = new FormData();
                            battcn.closeWindow();
                            $('#merChants').bootstrapTable('refresh');
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
    };
    var objData = {
        userIDCardA:"",
        userIDCardB:"",
        cardImgA:"",
        faceImg:""
    }
    function yasuo(self,name) {
        var fileObj = document.getElementById(name).files[0]; // js 获取文件对象
        var aa = 800*1024;
        console.log("fileObj.size:"+fileObj.size+"aa"+aa);
        if (fileObj.size > aa) {//大于八百kb进行压缩
            var file = $(self)[0].files[0];
            console.log("file:"+file);
            photoCompress(file,{quality: 0.2},function (base64Codes) {
                console.log("base64Codes:"+base64Codes);
                var bl = convertBase64UrlToBlob(base64Codes);
                objData[name] = bl;
            });
        }
    }
    /*
        三个参数
        file：一个是文件(类型是图片格式)，
        w：一个是文件压缩的后宽度，宽度越小，字节越小
        objDiv：一个是容器或者回调函数
        photoCompress()
        */
    function photoCompress(file, w, objDiv) {
        var ready = new FileReader();
        /*开始读取指定的Blob对象或File对象中的内容. 当读取操作完成时,readyState属性的值会成为DONE,如果设置了onloadend事件处理程序,则调用之.同时,result属性中将包含一个data: URL格式的字符串以表示所读取文件的内容.*/
        ready.readAsDataURL(file);
        ready.onload = function () {
            var re = this.result;
            canvasDataURL(re, w, objDiv)
        }
    }

    function canvasDataURL(path, obj, callback) {
        var img = new Image();
        img.src = path;
        img.onload = function () {
            var that = this;
            // 默认按比例压缩
            var w = that.width,
                h = that.height,
                scale = w / h;
            w = obj.width || w;
            h = obj.height || (w / scale);
            var quality = 0.7; // 默认图片质量为0.7
            //生成canvas
            var canvas = document.createElement('canvas');
            var ctx = canvas.getContext('2d');
            // 创建属性节点
            var anw = document.createAttribute("width");
            anw.nodeValue = w;
            var anh = document.createAttribute("height");
            anh.nodeValue = h;
            canvas.setAttributeNode(anw);
            canvas.setAttributeNode(anh);
            ctx.drawImage(that, 0, 0, w, h);
            // 图像质量
            if (obj.quality && obj.quality <= 1 && obj.quality > 0) {
                quality = obj.quality;
            }
            // quality值越小，所绘制出的图像越模糊
            var base64 = canvas.toDataURL('image/jpeg', quality);
            // 回调函数返回base64的值
            callback(base64);
        }
    }
    /**
     * 将以base64的图片url数据转换为Blob
     * @param urlData
     *            用url方式表示的base64图片数据
     */
    function convertBase64UrlToBlob(urlData) {
        var arr = urlData.split(','),
            mime = arr[0].match(/:(.*?);/)[1],
            bstr = atob(arr[1]),
            n = bstr.length,
            u8arr = new Uint8Array(n);
        while (n--) {
            u8arr[n] = bstr.charCodeAt(n);
        }
        return new Blob([u8arr], {
            type: mime
        });
    }
</script>