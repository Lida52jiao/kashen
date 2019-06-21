<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tree.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!--图标样式-->

<link rel="stylesheet" type="text/css" href="<%=basePath%>css/bootstrap.min.css" />



<!--主要样式-->

<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css" />



<%-- <script type="text/javascript" src="<%=basePath%>js/jquery-1.7.2.min.js"></script> --%>

<script type="text/javascript">

$(function(){
	$.ajax({
		   url : "<%=basePath%>Agent/findsAgent.shtml",
		   type : 'GET',
		   async : false,
		   cache : false,
		   contentType : "application/json",
		   processData : false,
		   success : function(data) {
		  		 $("#first").append(data[0].merName+"|"+data[0].merId);
		   },
		   error : function(data) {
		      alert("error:链接服务器失败");
		   }
	});

    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');

   
        var children = $(this).parent('li.parent_li').find(' > ul > li');

        if (children.is(":visible")) {

            children.hide('fast');

            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');

        } else {

            children.show('fast');

            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');

        }
        
         $('.tree li.parent_li > span').on('click', function (e) {
    	var id=$(this).text().split("|");
    	$.ajax({
		   url : "<%=basePath%>Agent/findsAgent.shtml?merId="+id[1],
		   type : 'GET',
		   async : false,
		   cache : false,
		   contentType : "application/json",
		   processData : false,
		   success : function(data) {
		  /*  var children1="<li><span><i class='icon-leaf'></i>"+data[0].merName+"</span> <a href="+""+"></a><ul id="+data[0].merId+"></ul></li>"
			   $(this).innerHTML=children1; */
			    $("#T1").empty();
		  	 for(var i=0;i<data.length;i++){
		  	 	var h=data[i];
			   $("#T1").append("<li><span><i class='icon-minus-sign'></i>"+h.merName+"|"+h.merId+"</span><ul id="+h.merId+"></ul></li>");
		   }  
		   },
		   error : function(data) {
		      alert("error:链接服务器失败");
		   }
		});
		
        e.stopPropagation();

    });

});
</script>
  </head>
  
  <body>
   		<div class="tree well">

			<ul>
			
				<li>
			
					<span id="first"><i class="icon-folder-open"></i> </span>
			
					<ul id="T1">
					
						
					</ul>
			
				</li>
			
			</ul>
			
		</div>
  </body>
</html>
