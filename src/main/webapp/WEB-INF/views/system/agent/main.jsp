<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- 导入jqueryeasyui的资源. js, css
	JSP页面指令, 容器解析. tomcat解析.页面指令标签不会到客户端.在服务器解析后,将结果发送的客户端.
	相当于 request.include();
 --%>
<%@ include file="/WEB-INF/views/system/agent/resources.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		// 为树增加双击事件
		// $() -> 选择器, 获取jQuery对象
		// $().tree() -> easyui函数. 相当于将JQuery对象转化成easyui组件.
		// 可以传递参数.参数是json对象.json的属性为tree组件的属性/事件名称.
		$("#menuTree").tree({
			// node就是当前出发双击事件的树节点.
			'onDblClick':function(node){
				if($("#menuTree").tree("isLeaf",node.target)){
					// 叶子节点. 为center增加内容. 将内容修改为当前节点的connurl属性值.
					// node.connurl
					// $().html(node.connurl);
					// 页面跳转 - 
					// 1. 直接通过javascript绘制页面.[麻烦]
					// 2. 访问远程服务器.通过服务器进行页面跳转.
					// 2.1 在center区域面板中增加一个<iframe>标签.
					
					/* var iframe="<iframe src='${pageContext.request.contextPath}"+node.connurl+"' width='100%' height='100%' frameborder='0' name='workarea'></iframe>";
					var center = $("#workarea");
					// 判断当前节点是否有对应的选项卡.如果有前端显示.如果没有新增选项卡.
					// 如果有对应的选项卡,返回选项卡对象.如果没有返回null
					var tab = center.tabs("getTab", node.text);
					if(tab){ // 如果tab非空,为真.空位假.
						center.tabs("select", node.text);
					}else{
						center.tabs("add", {
							'title':node.text,
							'content':iframe,
							'selected':true,
							'closable':true
						});
					} */
					
					/* 
					 * 没有选项卡的实现方式
					center.empty();
					center.append(iframe);
					 */
					 
					// 2.2 通过区域面板属性href进行页面的跳转.
					/*
					 * href - 将远程服务器返回的数据.作为div标签的内部元素显示的.
					 * href:/menu/menuManagement
					 * 将服务器返回的数据,在div中显示  <table>
					 * <div><table></div>
					 * 远程访问的服务器结果,如果包含javascript或css样式,必须定义在body标签内.
					 */
					/* 
					var center = $("#workarea");
					center.panel({
						'href':node.connurl // a.jsp
					});
					 */
				}else{
					// 非叶子节点. 调用方法.展开或关闭节点.
					// 先判断当前节点的状态. state. 如果是open需要关闭.如果是closed需要展开.
					// 展开和关闭都有方法.
					if(node.state == "open"){
						// 关闭节点
						$("#menuTree").tree("collapse", node.target);
					}else{
						// 展开节点
						$("#menuTree").tree("expand", node.target);
					}
				}
			}
		});
	});
</script>
</head>
<%-- 页面布局. 布局为上, 左, 右三部分.
	class - 类ID, 所有的easyui的类ID命名方式
		easyui-组件名称 
			如 : 
				布局 - easyui-layout
				数据表格 - easyui-datagrid
				树 - easyui-tree
 --%>
<body class="easyui-layout" data-options="fit:false">   
	<%-- 布局中分块面板
		每一个布局分块是一个div标签.
		重要的属性 - 
			data-options : 是easyui自定义使用的.不是html定义的.
				用于定义easyui中组件的属性的.
				可配置的属性值,都可以在帮助文档中查找.都叫属性.
	 --%>
    <%-- <div data-options="region:'north',title:'测试页面头信息',split:false" 
    	style="height:100px; padding:20px; text-align:right; font-size:14px">
    	${sessionScope.loginUser.fullname}
    </div>   --%> 
    <div data-options="region:'west',title:'West',split:false" style="width:260px;">
    	<%-- 使用easyui-tree组件,实现树状菜单显示.
    		easyui-tree
    			默认使用AJAX异步访问查询数据的.
    	 --%>
    	<ul id="menuTree" class="easyui-tree" data-options="
    		url:'${pageContext.request.contextPath}/Tree/findsAgent.shtml',
    		animate:true
    	"></ul>
    </div>   
    <%-- 底层使用的是easyui中的panel面板
    	在layout的不同区域中,使用panel做面板显示.
     --%>
    <!-- <div class="easyui-tabs" data-options="region:'center',title:'center title'" 
    	style="padding:5px;background:#eee; width:100%; hegiht:100%" id="workarea">
    	<div data-options="title:'测试首页先选卡', closable:false, selected:true">
    	测试中间展示内容1
    	</div>
    	<div data-options="title:'test1', closable:true">
    	测试中间展示内容2
    	</div>
    	<div data-options="title:'test2', closable:true">
    	测试中间展示内容3
    	</div>
    </div>    -->
</body>  
</html>











