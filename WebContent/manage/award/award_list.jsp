<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>奖品管理</title>
</head>
<body class="user-select">

<%@include file="../top.jsp" %>
  
  <div class="row">
  
    <aside class="col-sm-3 col-md-2 col-lg-2 sidebar">
	<ul class="nav nav-sidebar">
	    <li><a href="<%=basePath %>/manage/index.jsp">首页</a></li>
	  </ul>
	  <ul class="nav nav-sidebar">
	    <li><a href="<%=basePath %>/question/questions">问题管理</a></li>
	  </ul>
	  <ul class="nav nav-sidebar">
	    <li   class="active"><a href="<%=basePath %>/awardInfo/awardInfos">奖品管理</a></li>
	  </ul>
	  <ul class="nav nav-sidebar">
	    <li><a href="<%=basePath %>/awardRecord/awardRecords">中奖纪录</a></li>
	  </ul>
	</aside>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
      <div class="row">
        <div class="col-md-5">
          <h1 class="page-header" onclick="javascript:window.location.reload();" style="cursor:pointer;">添加奖品</h1>
          <form action="/lottery/awardInfo/awardInfoadd" method="post" id="awardaddform" autocomplete="off">
            <input type="hidden" name="id" id="id" value="">
            <div class="form-group">
              <label for="category-awardName">奖品名字</label>
              <input type="text" id="category-awardName" name="awardName" value="${awardinfo}" class="form-control" placeholder="在此处输入奖品名字" required autocomplete="off">
            </div>
            
            <div class="form-group">
              <label for="category-awardGrade">奖品等级</label>
              <select id="category-type" class="form-control" name="awardGrade">
                <option value="1" selected>一等奖</option>
                <option value="2">二等奖</option>
                <option value="3">三等奖</option>
              </select>
            </div>
            
            <div class="form-group">
             <label for="category-awardNum">奖品数量</label>
             <input type="text" id="category-awardNum" name="awardNum" value="${awardinfo}" class="form-control" placeholder="在此处输入奖品数量" required autocomplete="off">
            </div>
            
            <div class="form-group">
             <label for="category-probability">中奖几率</label>
             <input type="text" id="category-probability" name="probability" value="${awardinfo.probability}" class="form-control" placeholder="在此处输入中奖几率" required autocomplete="off">
            </div>
            
            <button class="btn btn-primary" type="submit" name="submit">提交</button>
          </form>
        </div>
        <div class="col-md-7">
          <h1 class="page-header">奖品管理</h1>
          <div class="table-responsive">
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th><span class="glyphicon glyphicon-paperclip"></span> <span class="visible-lg">序号</span></th>
                  <th><span class="glyphicon glyphicon-file"></span> <span class="visible-lg">奖品名字</span></th>
                  <th><span class="glyphicon glyphicon-file"></span> <span class="visible-lg">奖品等级</span></th>
                  <th><span class="glyphicon glyphicon-file"></span> <span class="visible-lg">竞品数量</span></th>
                  <th><span class="glyphicon glyphicon-file"></span> <span class="visible-lg">中奖几率</span></th>
                  <th><span class="glyphicon glyphicon-pencil"></span> <span class="visible-lg">操作</span></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${awardInfos}" var="awardInfo" varStatus="st">
	                <tr onclick="findOneAward('${awardInfo.id}');">
	                  <td>${st.index+1 }</td>
	                  <td>${awardInfo.awardName }</td>
	                  <td>${awardInfo.awardGrade }</td>
	                  <td>${awardInfo.awardNum }</td>
	                  <td>${awardInfo.probability }</td>
	                  <td> <a rel="${awardInfo.id}">删除</a></td>
	                </tr>
                </c:forEach>
              </tbody>
            </table>
      </div>
    </div>
  </div>
</section>

<%@include file="../bottom.jsp" %>

<script>
//是否确认删除
$(function(){   
	$("#main table tbody tr td a").click(function(){
		var name = $(this);
		var id = name.attr("rel"); //对应id  
		if (event.srcElement.outerText === "删除") 
		{
			if(window.confirm("此操作不可逆，是否确认？"))
			{
				$.ajax({
					type: "POST",
					url: basePath+"/awardInfo/awardInfodel",
					data: "id=" + id,
					cache: false, //不缓存此页面   
					success: function (data) {
						window.location.reload();
					}
				});
			};
		};
	});   
});
/**
 * 查找某个问题
 */
function findOneAward(id){
	var params_form = {
			id:id
	}
	var url=basePath+"/awardInfo/findAwardInfo";
	$.post(url,params_form,function(responseText){
        var data=eval("("+responseText+")"); 
		var awardName=data.awardName;   
		var awardGrade=data.awardGrade;
		var awardNum=data.awardNum;
		var probability=data.probability;
		
	    var content="";
		content+="<input type=\"hidden\" name=\"id\" id=\"id\" value=\""+id+"\">";
		
		content+="<div class=\"form-group\">";
		content+="<label for=\"category-awardName\">奖品名字</label>";
		content+="<input type=\"text\" id=\"category-awardName\" name=\"awardName\" value=\""+awardName+"\" class=\"form-control\" placeholder=\"在此处输入奖品名字\" required autocomplete=\"off\">";
		content+="</div>";
		
		content+="<div class=\"form-group\">";
		content+="<label for=\"category-awardGrade\">奖品等级</label>";
		content+="<select id=\"category-type\" class=\"form-control\" name=\"awardGrade\">";
		if(awardGrade==1){
			content+="<option value=\"1\" selected>一等奖</option>";
			content+="<option value=\"2\">二等奖</option>";
			content+="<option value=\"3\">三等奖</option>";
		}else if(awardGrade==2){
			content+="<option value=\"1\">一等奖</option>";
			content+="<option value=\"2\" selected>二等奖</option>";
			content+="<option value=\"3\">三等奖</option>";
		}else if(awardGrade==3){
			content+="<option value=\"1\">一等奖</option>";
			content+="<option value=\"2\">二等奖</option>";
			content+="<option value=\"3\" selected>三等奖</option>";
		}
		content+="</select>";
		content+="</div>";
		
		content+="<div class=\"form-group\">";
		content+="<label for=\"category-awardNum\">奖品数量</label>";
		content+="<input type=\"text\" id=\"category-awardNum\" name=\"awardNum\" value=\""+awardNum+"\" class=\"form-control\" placeholder=\"在此处输入奖品数量\" required autocomplete=\"off\">";
		content+="</div>";
		
		content+="<div class=\"form-group\">";
		content+="<label for=\"category-probability\">中奖几率</label>";
		content+="<input type=\"text\" id=\"category-probability\" name=\"probability\" value=\""+probability+"\" class=\"form-control\" placeholder=\"在此处输入中奖几率\" required autocomplete=\"off\">";
		content+="</div>";
		
		content+="<button class=\"btn btn-primary\" type=\"submit\" name=\"submit\">修改</button>&nbsp;";
		$("#awardaddform").html(function(){
			return content;
		})
    });             
}            
</script>
</body>
</html>
