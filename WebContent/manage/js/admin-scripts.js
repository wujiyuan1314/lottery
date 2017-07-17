//当浏览器窗口大小改变时重载网页
/*window.onresize=function(){
    window.location.reload();
}*/
  function fnChangeName(value,jsArray){
   var result="";
   for(var i=0;i<jsArray.length;i++){
       if((""+value)==(jsArray[i][0]+"")){
		       result=jsArray[i][1];
		       break;
	       }
       }
       return result;
}
//页面加载时给img和a标签添加draggable属性
(function () {
    $('img').attr('draggable', 'false');
    $('a').attr('draggable', 'false');
})();
 
//设置Cookie
function setCookie(name, value, time) {
    var strsec = getsec(time);
    var exp = new Date();
    exp.setTime(exp.getTime() + strsec * 1);
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}
function getsec(str) {
    var str1 = str.substring(1, str.length) * 1;
    var str2 = str.substring(0, 1);
    if (str2 == "s") {
        return str1 * 1000;
    } else if (str2 == "h") {
        return str1 * 60 * 60 * 1000;
    } else if (str2 == "d") {
        return str1 * 24 * 60 * 60 * 1000;
    }
}
 
//获取Cookie
function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg)) {
        return unescape(arr[2]);
    } else {
        return null;
    }
}

var checkall=document.getElementsByName("checkbox[]");  
//全选
function select(){
	for(var $i=0;$i<checkall.length;$i++){  
		checkall[$i].checked=true;  
	}  
};
//反选
function reverse(){
	for(var $i=0;$i<checkall.length;$i++){  
		if(checkall[$i].checked){  
			checkall[$i].checked=false;  
		}else{  
			checkall[$i].checked=true;  
		}  
	}  
}     
//全不选     
function noselect(){ 
	for(var $i=0;$i<checkall.length;$i++){  
		checkall[$i].checked=false;  
	}  
} 
 
//IE6-9禁止用户选中文本
/*document.body.onselectstart = document.body.ondrag = function () {
    return false;
};*/
 
//启用工具提示
$('[data-toggle="tooltip"]').tooltip();
 

//禁止右键菜单
/*window.oncontextmenu = function(){
	return false;
};*/

/*自定义右键菜单*/
/*(function () {
    var oMenu = document.getElementById("rightClickMenu");
    var aLi = oMenu.getElementsByTagName("li");
	//加载后隐藏自定义右键菜单
	//oMenu.style.display = "none";
    //菜单鼠标移入/移出样式
    for (i = 0; i < aLi.length; i++) {
        //鼠标移入样式
        aLi[i].onmouseover = function () {
            $(this).addClass('rightClickMenuActive');
			//this.className = "rightClickMenuActive";
        };
        //鼠标移出样式
        aLi[i].onmouseout = function () {
            $(this).removeClass('rightClickMenuActive');
			//this.className = "";
        };
    }
    //自定义菜单
    document.oncontextmenu = function (event) {
		$(oMenu).fadeOut(0);
        var event = event || window.event;
        var style = oMenu.style;
        $(oMenu).fadeIn(300);
		//style.display = "block";
        style.top = event.clientY + "px";
        style.left = event.clientX + "px";
        return false;
    };
    //页面点击后自定义菜单消失
    document.onclick = function () {
        $(oMenu).fadeOut(100);
		//oMenu.style.display = "none"
    }
})();*/

/*禁止键盘操作*/
/*document.onkeydown=function(event){
	var e = event || window.event || arguments.callee.caller.arguments[0];
	if((e.keyCode === 123) || (e.ctrlKey) || (e.ctrlKey) && (e.keyCode === 85)){
		return false;
	}
}; */

//Console
//try {
//    if (window.console && window.console.log) {
 //       console.log("\n欢迎访问异清轩博客！\n\n在本站可以看到前端技术，后端程序，网站内容管理系统等文章；\n\n还有我的程序人生！！！\n");
 //       console.log("\n请记住我们的网址：%c www.ylsat.com", "color:red");
 //       console.log("\nPOWERED BY WY ALL RIGHTS RESERVED");
  //  }
//} catch (e) {};
/**
 * 修改当前页码，调用后台重新查询
 */
function changeCurrentPage(currentPage){
	
	document.getElementById("currentPage").value = currentPage;
	//alert(document.getElementById("currentPage").value);
	//alert(currentPage);
	document.forms["pageform"].submit();
}
var type_JsArray=[['',''],['0','单选'],['1','多选']];
/**
 * 查找某个问题
 */
function findOne(id){
	var params_form = {
			id:id
	}
	var url=basePath+"/question/findquestion";
	$.post(url,params_form,function(responseText){
        var data=eval("("+responseText+")"); 
		var questionTile=data.questionTile;   
		var type=data.type;
		var questionaswer=data.questionaswer;
		
	    var content="";
		content+="<input type=\"hidden\" name=\"id\" id=\"id\" value=\""+id+"\">";
		content+="<input type=\"hidden\" name=\"answernum\" id=\"answernum\" value=\""+questionaswer.length+"\">";
		content+="<div class=\"form-group\">";
		content+="<label for=\"category-questionTitle\">问题描述</label>";
		content+="<textarea class=\"form-control\" id=\"category-questionTitle\" name=\"questionTitle\" rows=\"4\"  required autocomplete=\"off\">";
		content+=questionTile;
		content+="</textarea>";
		content+="<span class=\"prompt-text\">描述问题的详细信息。</span>";
		content+="</div>";
		content+="<div class=\"form-group\">";
		content+="<label for=\"category-fname\">问题类型</label>";
		content+="<select id=\"category-type\" class=\"form-control\" name=\"type\">";
		if(type==0){
			content+="<option value=\"0\" selected>单选</option>";
			content+="<option value=\"1\">多选</option>";
		}else if(type==1){
			content+="<option value=\"0\">单选</option>";
			content+="<option value=\"1\" selected>多选</option>";
		}
		content+="";
		content+="";
		content+="</select>";
		content+="</div>";
		for(var i=0;i<questionaswer.length;i++){
			content+="<div class=\"form-group\">";
			content+="<label for=\"category-answerA\">答案"+(i+1)+"</label>";
			content+="<input type=\"text\" id=\"category-answer\" name=\"answer"+(i+1)+"\" value=\""+questionaswer[i]["answer"]+"\" class=\"form-control\" placeholder=\"在此处输入答案"+(i+1)+"\" required autocomplete=\"off\">";
			content+="<input type=\"hidden\" name=\"answerid"+(i+1)+"\" id=\"answerid"+(i+1)+"\" value=\""+questionaswer[i]["answerid"]+"\">";
			content+="<select id=\"category-type\" class=\"form-control\" name=\"isright"+(i+1)+"\">";
			var isright=questionaswer[i]["isright"];
			if(isright==0){
				content+="<option value=\"1\">是正确答案</option>";
				content+="<option value=\"0\" selected>不是正确答案</option>";
			}else if(isright==1){
				content+="<option value=\"1\" selected>是正确答案</option>";
				content+="<option value=\"0\">不是正确答案</option>";
			}
			content+="</select>";
			content+="</div>";
		}
		content+="<button class=\"btn btn-primary\" type=\"submit\" name=\"submit\">修改问题</button>&nbsp;";
		content+="<a class=\"btn btn-primary\" onclick=\"addAnswer("+questionaswer.length+");\" id=\"addAnswer\">继续添加答案</a>";
		$("#addquestionform").html(function(){
			return content;
		})
    });             
}
/**
 * 添加答案信息
 */
function addAnswer(num){
	if(num>=6){
		bootbox.alert("答案不能超过6个");
	}else{
		var content="<div class=\"form-group\">";
		content+="<label for=\"category-answerA\">答案"+(num+1)+"</label>";
		content+="<input type=\"text\" id=\"category-answer\" name=\"answer"+(num+1)+"\" class=\"form-control\" placeholder=\"在此处输入答案"+(num+1)+"\" required autocomplete=\"off\">";
		content+="<input type=\"hidden\" name=\"answerid"+(num+1)+"\" id=\"answerid"+(num+1)+"\" value=\"\">";
		content+="<select id=\"category-type\" class=\"form-control\" name=\"isright"+(num+1)+"\">";
		content+="<option value=\"0\" selected>不是正确答案</option>";
		content+="<option value=\"1\">是正确答案</option>";
		content+="</select>";
		content+="</div>";
		$(".form-group:eq("+(num+1)+")").after(function(){
			return content;
		})
		$("#addAnswer").attr("onclick","addAnswer("+(num+1)+")");
		$("#answernum").val((num+1));
	}
}