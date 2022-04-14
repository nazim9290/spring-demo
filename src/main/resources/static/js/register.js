
$(function(){
 
 $(".register_submit").click(function(){
  
  var id= $('#id').val()
  var name=$('#name').val()
  var sex= $('#sex').val()

	
	<!-- 正規表現 -->

	if(!id || !name ||  !sex){
	alert("入力してください。")
	e.preventDefault()
	}

	var pattern = /^([1-9][0-9]{0,2}|1000)$/
	if(!pattern.test(id)){
	alert("ID項目に数字と１０００までで入力してください。")
	e.preventDefault()
	}
	
	var pattern=/^.{1,50}$/
	if(!pattern.test(name)){
	alert("名前項目に50文字以下を入力してください。")
	e.preventDefault()
	}
	

	var pattern=/^男|女$/
	if(!pattern.test(sex)){
	alert("性別項目に男または女を入力してください。")
	e.preventDefault()
	}
	

	
});


}); 

