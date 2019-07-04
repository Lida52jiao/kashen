<%--
  Created by IntelliJ IDEA.
  User: N
  Date: 2019/6/24
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="ibox float-e-margins">
    <div class="ibox-content">
        <form class="form-horizontal m-t required-validate" id="from1">
            <input id="merId" name="merId" class="form-control" type="hidden" value="${u.merId}">
            <div class="form-group">
                <label class="col-sm-3 control-label">金额：</label>
                <div class="col-sm-8">
                    <input id="amount" name="amount" class="form-control" type="text" style="width: 200px">
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    function save(obj) {
        var merId = $('input[name="merId"]').val();
        var amount = $('input[name="amount"]').val()*100;
        if(amount == null || amount == ''){
            layer.msg("请输入金额！");
            return;
        }
        window.open("http://47.104.4.155:1172/account/addTotalBalance?amount="+amount+"&institutionId="+merId+"&type=add");
    }
</script>