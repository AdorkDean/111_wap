$(function(){
	  $(".health-close").click(function(){
	    	$(this).siblings("input[type='file']").val("");
	    	$(this).siblings("a").children("img").remove();
	    	
	    });
	$(document).ready(function(){
		$("input[type=file]").on('click',function(){
				if(typeof FileReader==='undefined'){ 
				$(this).change(function(){
						loadImgForIE(this);
					})
				}else{ 
					$(this).change(function(){
						readFile(this);
					})
				} 
		})
	});
	function readFile(obj){
		var file = obj.files[0]; 
		if(!/image\/\w+/.test(file.type)){ 
			alert("文件必须为图片！"); 
			return false; 
		}
		var reader = new FileReader(); 
		reader.readAsDataURL(file); 
		
			
		reader.onload = function(e){
			if(this.result.length>5242880){
				alert("上传图片不能大于5M！");
				return false;
			}
				$(obj).siblings("a")[0].innerHTML = '<img style="width:40px;height:40px;" src="'+this.result+'" alt="" id=""/>' ;
			
		} 
	}
	function loadImgForIE(obj){
		var imgPath=$(obj).val();
		$(obj).siblings("a")[0].innerHTML = '<img src="'+this.result+'" alt="" id=""/>' ;
	}
	
});

	function subForm(){
		var imageFile1 = $("input[name='imageFile1']").val();//资质
		var imageFile2 = $("input[name='imageFile2']").val();
		var imageFile3 = $("input[name='imageFile3']").val();
		var imageFile4 = $("input[name='imageFile4']").val();
		var diseaseDescrip=$("#diseaseDescrip").val();
		
		if((imageFile1==null||imageFile1=="")&&(imageFile2==null||imageFile2=="")&&
				(imageFile3==null||imageFile3=="")&&(imageFile4==null||imageFile4=="")&&($.trim(diseaseDescrip)=="")){
			alert("您至少要上传一张病历照片或者填写病情描述");
			return;
		}
		if($.trim(diseaseDescrip)!=""){
			if(diseaseDescrip.length>249){
				alert("病情描述不能大于250个字");
				return;
			}
		}
		$("#formBtn").submit();
	}
