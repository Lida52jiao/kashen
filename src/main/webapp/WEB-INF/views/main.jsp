<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=basePath%>js/highcharts.js"></script>
<script type="text/javascript" src="<%=basePath%>My97DatePicker/WdatePicker.js"></script>
<div class="wrapper wrapper-content animated fadeInRight"
	style="height: 50%">
	<div class="ibox float-e-margins">
		<div class="ibox-content">
			<form role="form" class="form-inline" id="userCountForm">
				<div class="form-group">
					<div class="col-sm-8">
						<input type="text" placeholder="开始时间" name="searchstartTime" id="formhkstarttime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
							<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
						<span class="help-block m-b-none"></span>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-8">
						<input type="text" placeholder="结束时间" name="searchendTime" id="formhkfinishtime" class="Wdate" onclick="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd'})">
							<!-- validate="{required:true,messages:{required:'请填写角色名'}}"> -->
							<span class="help-block m-b-none"></span>
					</div>
				</div>
				<div class="form-group">
						<span  style="margin-left:300px;" ><input class="btn btn btn-primary" type="button" value="搜索" onclick="userCount()"></span>
				</div> 
			</form>
		</div>
	</div>
</div>
 <div id="container" style="min-width:300px;height:400px"></div> 
  <div id="containerT" style="min-width:300px;height:400px"></div> 
<script type="text/javascript">
// $(function(){
// 	userCount = function() {
// 		if($("#userCountForm").valid()){
// 			layer.confirm('报表生成中，请耐心等待！！！');
// 			//第一个报表的ajax
// 			$.ajax({
// 				type: "POST",
// 				url: rootPath + "/userdata.shtml",
// 				timeout : 50000, //超时时间设置，单位毫秒
// 				data : $('#userCountForm').serializeArray(),
// 				success: function(data){
// 						var chart = Highcharts.chart('container',{
// 					    chart: {
// 					        type: 'column'
// 					    },
// 					    title: {
// 					        text: '平台用户统计'
// 					    },
// 					    subtitle: {
// 					        text: '默认展示本月一号至当前时间(' +formatDate(new Date().getTime())+')的统计数据'
// 					    },
// 					    xAxis: {
// 					        categories: [
// 					            '注册用户','未实名用户','实名用户','小咖用户','大咖用户','代理商',"运营商"
// 					        ],
// 					        crosshair: true
// 					    },
// 					    yAxis: {
// 					        min: 0,
// 					        title: {
// 					            text: '人数(位)与收益(元)'
// 					        }
// 					    },
// 					    tooltip: {
// 					        // head + 每个 point + footer 拼接成完整的 table
// 					        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
// 					        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
// 					        '<td style="padding:0"><b>{point.y:.2f} </b></td></tr>',
// 					        footerFormat: '</table>',
// 					        shared: true,
// 					        useHTML: true
// 					    },
// 					    plotOptions: {
// 					        column: {
// 					            borderWidth: 0
// 					        }
// 					    },
// 					    series: [{
// 					        name: '人数',
// 					        data:data.intList,
// 					        dataLabels: {
// 					            enabled: true,
// 					            rotation: -360,
// 					            color: '#FFFFFF',
// 					            align: 'right',
// 					            format: '{point.y:.2f}', // :.1f 为保留 1 位小数
// 					            y: 5
// 					        }
// 					    }, {
// 					        name: '平台收益',
// 					        data:data.bigList,
// 					        dataLabels: {
// 					            enabled: true,
// 					            rotation: -360,
// 					            color: '#FFFFFF',
// 					            align: 'right',
// 					            format: '{point.y:.2f}', // :.1f 为保留 1 位小数2f依次类推
// 					            y: 5
// 					        }
// 					    }]
// 					});
// 					function formatDate(time){
// 					    var date = new Date(time);
//
// 					    var year = date.getFullYear(),
// 					        month = date.getMonth() + 1,//月份是从0开始的
// 					        day = date.getDate(),
// 					        hour = date.getHours(),
// 					        min = date.getMinutes(),
// 					        sec = date.getSeconds();
// 					    var newTime = year + '/' +
// 					                month + '/' +
// 					                day + ' ' +
// 					                hour + ':' +
// 					                min + ':' +
// 					                sec;
// 					    return newTime;
// 					}
// 				}
// 			});
			//第二个报表的ajax
			/* $.ajax({
				type: "POST", 
				url: rootPath + "/userdataT.shtml",
				timeout : 50000, //超时时间设置，单位毫秒
				data : $('#userCountForm').serializeArray(),
				success: function(data){
						var chart = Highcharts.chart('containerT',{
					    chart: {
					        type: 'column'
					    },
					    title: {
					        text: '平台用户分润、提现统计'
					    },
					    subtitle: {
					        text: '默认展示本月一号至当前时间(' +formatDate(new Date().getTime())+')的统计数据'
					    },
					    xAxis: {
					        categories: [
					            '用户分润','用户提现'
					        ],
					        crosshair: true
					    },
					    yAxis: {
					        min: 0,
					        title: {
					            text: '人数(位)与金额(元)'
					        }
					    },
					    tooltip: {
					        // head + 每个 point + footer 拼接成完整的 table
					        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
					        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
					        '<td style="padding:0"><b>{point.y:.2f} </b></td></tr>',
					        footerFormat: '</table>',
					        shared: true,
					        useHTML: true
					    },
					    plotOptions: {
					        column: {
					            borderWidth: 0
					        }
					    },
					    series: [{
					        name: '金额',
					        data:data.userFeeList,
					        dataLabels: {
					            enabled: true,
					            rotation: -360,
					            color: '#FFFFFF',
					            align: 'right',
					            format: '{point.y:.2f}', // :.1f 为保留 1 位小数
					            y: 5
					        }
					    }, {
					        name: '人数',
					        data:data.userWithdrawList,
					        dataLabels: {
					            enabled: true,
					            rotation: -360,
					            color: '#FFFFFF',
					            align: 'right',
					            format: '{point.y:.2f}', // :.1f 为保留 1 位小数2f依次类推
					            y: 5
					        }
					    }]
					});
					function formatDate(time){
					    var date = new Date(time);
					
					    var year = date.getFullYear(),
					        month = date.getMonth() + 1,//月份是从0开始的
					        day = date.getDate(),
					        hour = date.getHours(),
					        min = date.getMinutes(),
					        sec = date.getSeconds();
					    var newTime = year + '/' +
					                month + '/' +
					                day + ' ' +
					                hour + ':' +
					                min + ':' +
					                sec;
					    return newTime;         
					}
				}
// 			}); */
// 		}
// 	}
// });
// userCount();
</script>
