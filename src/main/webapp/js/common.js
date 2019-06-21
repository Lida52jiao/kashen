/*--------------公用js库--------------*/

/*------------表格功能区域------------*/

//data:请求数据(默认为null)
var data = null;
//total:总数据条数(默认为0)
var total = 0;
//page:页码(默认为0)
var page = 0;
//rows:每页显示条数(默认为0)
var rows = 0;
//fun:请求该函数的函数名(默认为null)
var fun = null;
	

$.fn.addTableArea = function(data,total,page,rows,fun){
	
	var fn = $(this);                //获取当前表格对象
 	var fnId = $(this).attr("id");   //获取当前表格对象id
 	//设置表格字段标识符
 	var tdParams = [];
 	fn.find("thead th").not(":first").each(function(){
 		var mm = {};
 		mm["key"] = $(this).attr("key");
 		mm["class"] = $(this).attr("class");
 		tdParams.push(mm);
 	});
 	
 	//调用加载表格以及页码函数
 	if(tdParams.length > 0){
 		addTableArea();              //执行加载表格
 		addPageArea();               //执行加载页码
 		tableFanyeOrToPage();        //执行绑定翻页和跳页
 	}
 	
 	
 	
 	/*加载表格区域函数*/
 	function addTableArea(){
 		fn.find("tbody").empty();
 		if(data != null && data != ""){
 			$.each(data,function(key,value){
 				fn.find("tbody").append(
 					'<tr>'+
 				        '<td><input name="" type="checkbox" value="" /></td>'+returnTds(value)+
 			        '</tr>' 
 				);
 	 		});
 			function returnTds(tdData){
 				var tds = "";
 				$.each(tdParams,function(key,value){
 					if(value["class"] == ""){
 						tds += '<td>'+ (tdData[value["key"]] == null? "":tdData[value["key"]]) +'</td>';
 					}else if(value["class"] == "fk"){
 						tds += '<td><a href="#" class="tablelink">查看</a></td>';
 					}else if(value["class"] == "auto"){
 						tds += '<td title="'+ (tdData[value["key"]] == null? "":tdData[value["key"]]) +'">'+ (tdData[value["key"]] == null? "":(tdData[value["key"]].length>10? (tdData[value["key"]].substring(0,10)+'...'):tdData[value["key"]])) +'</td>';
 					}
 				});
 				return tds;
 			}
 			$('.tablelist tbody tr:odd').addClass('odd');
 		}
 	}
 	

	/*加载页码区域函数*/
	function addPageArea(){
		$("#"+ fnId +"-pageArea").remove();
		fn.parent().after(
			'<div id="'+ fnId +'-pageArea" class="pagin">'+
		    	'<div class="message">共<i class="blue">'+ total +'</i>条记录，当前显示第&nbsp;<i class="blue">'+ page +'&nbsp;</i>页，共&nbsp;<i class="blue">'+ getPages() +'&nbsp;</i>页</div>'+
		        '<ul class="paginList">'+
			     	'<li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>'+
			        '<li class="paginItem"><a href="javascript:;">'+ page +'</a></li>'+
			        '<li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>'+
		        	'<li style="float:left;margin-left:30px;line-height:2.5;">跳转第&nbsp;<input type="text" class="pageNum" />&nbsp;页</li>'+
		        '</ul>'+
		    '</div>'
		);
	}
	
	/*返回总页码函数*/
	function getPages(){
		var pages = 0;
		if(rows != 0 && rows != "" && total != 0 && total != ""){
			if(total%rows == 0){
				pages = total/rows;
			}else{
				pages = parseInt(total/rows)+1;
			}
			return pages;
		}else{
			return pages;
		}
	}
	
	
	/*实现翻页或跳页功能*/
	function tableFanyeOrToPage(){
		if(fun != null){
			//前翻页功能
			$(".pagepre").click(function(){
				if(page > 1){
					page--;
					eval(fun+'('+ page + ','+ rows +')');      //回调函数
				}
			});
			//后翻页功能
			$(".pagenxt").click(function(){
				if(page < getPages()){
					page++;
					eval(fun+'('+ page + ','+ rows +')');      //回调函数
				}
			});
			//跳页功能
			$(".pageNum").keydown(function(){
				if(event.keyCode == 13){
					if($(this).val() != ""){
						if($(this).val() >= 1 && $(this).val() <= getPages()){
							page = $(this).val();
							eval(fun+'('+ page + ','+ rows +')');      //回调函数
						}else{
							showInform("您所输入页码不在有效范围！");
						}
					}else{
						showInform("您所输入页码不能为空！");
					}
				}
			});
		}
	}
	
	
	/*实现提示信息功能*/
	function showInform(info){
		$(".inform").text(info);
		$(".inform").fadeIn(300);
		setTimeout(function(){
			$(".inform").fadeOut(300);
		},1500);
	}
	
	
	
}