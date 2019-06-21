$(function(){
	var province = 
	$('#province').change(function(){
		var province=$(this).val();
		$('.option2').remove();
		$('.option3').remove();
		$('.option4').remove();
		$('.option5').remove();
		$.ajax({
				type:'post',
				url:"http://47.104.198.186:1178/community/getCountrys/"+province,
                dataType:'json',
                success:function(data){
                	for(var i=0;i<data.data.length;i++){
                		var option=$('<option class="option2" value="'+data.data[i].id+'">'+data.data[i].areaName+'****身份前六位'+data.data[i].code+'</option>');
                		$('#city').append(option);
                	}
                }
			});
		//("#select1").val();
		
	});
	$('#city').change(function(){
		var province=$(this).val();
		$('.option3').remove();
		$('.option4').remove();
		$('.option5').remove();
		$.ajax({
				type:'post',
				url:"http://47.104.198.186:1178/community/getCountrys/"+province,
                dataType:'json',
                success:function(data){
                	for(var i=0;i<data.data.length;i++){
                		var option=$('<option class="option3" value="'+data.data[i].id+'">'+data.data[i].areaName+'****身份前六位'+data.data[i].code+'</option>');
                		$('#region').append(option);
                	}
                }
			});
	});
	$('#region').change(function(){
		var province=$(this).val();
		$('.option4').remove();
		$('.option5').remove();
		$.ajax({
				type:'post',
				url:"http://47.104.198.186:1178/community/getCountrys/"+province,
                dataType:'json',
                success:function(data){
                	for(var i=0;i<data.data.length;i++){
                		var option=$('<option class="option4" value="'+data.data[i].id+'">'+data.data[i].areaName+'****身份前六位'+data.data[i].code+'</option>');
                		$('#street').append(option);
                	}
                }
			});
	});
	$('#street').change(function(){
		var province=$(this).val();
		$('.option5').remove();
		$.ajax({
				type:'post',
				url:"http://47.104.198.186:1178/community/getCountrys/"+province,
                dataType:'json',
                success:function(data){
                	for(var i=0;i<data.data.length;i++){
                		var option=$('<option class="option5" value="'+data.data[i].id+'">'+data.data[i].areaName+'****身份前六位'+data.data[i].code+'</option>');
                		$('#community').append(option);
                	}
                }
			});
	});
});

	$(function(){
		$.ajax({
				type:'post',
				url:"http://47.104.198.186:1178/community/getCountrys/0",
                dataType:'json',
                success:function(data){
                	for(var i=0;i<data.data.length;i++){
                		var option=$('<option class="option1" value="'+data.data[i].id+'">'+data.data[i].areaName+'****身份前六位'+data.data[i].code+'</option>');
                		$('#province').append(option);
                	}
                }
			});
	});