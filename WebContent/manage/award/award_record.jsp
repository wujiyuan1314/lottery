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
	    <li><a href="<%=basePath %>/awardInfo/awardInfos">奖品管理</a></li>
	  </ul>
	  <ul class="nav nav-sidebar">
	    <li class="active"><a href="<%=basePath %>/awardRecord/awardRecords">中奖纪录</a></li>
	  </ul>
	</aside>
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
      <div class="row">
        <div class="col-md-7" style="width:100%;">
          <h1 class="page-header">中奖纪录管理</h1>
          <div class="table-responsive">
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th><span class="glyphicon glyphicon-paperclip"></span> <span class="visible-lg">序号</span></th>
                  <th><span class="glyphicon glyphicon-file"></span> <span class="visible-lg">中奖电话</span></th>
                  <th><span class="glyphicon glyphicon-file"></span> <span class="visible-lg">中奖等级</span></th>
                  <th><span class="glyphicon glyphicon-file"></span> <span class="visible-lg">是否已领取</span></th>
                  <th><span class="glyphicon glyphicon-file"></span> <span class="visible-lg">中奖时间</span></th>
                  <th><span class="glyphicon glyphicon-pencil"></span> <span class="visible-lg">操作</span></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${awardRecords}" var="awardRecord" varStatus="st">
	                <tr>
	                  <td>${st.index+1 }</td>
	                  <td>${awardRecord.mobile }</td>
	                  <td>${awardRecord.awareid }</td>
	                  <td>${awardRecord.state }</td>
	                  <td><fmt:formatDate value="${awardRecord.addtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	                  </td>
	                  <td> <a rel="${awardRecord.id}">删除</a></td>
	                </tr>
                </c:forEach>
              </tbody>
            </table>
            <sf:form value="/lottery/question/questions" method="post" name="pageform">
              <input type="hidden" name="currentPage" id="currentPage" value="1" />
            </sf:form>
            <ul style="width:94%;padding-left:20%;">
                  <li style="float:left;width:30%;text-align:center;">共<font style="font-size:14px;color:#A23434;">${page.totalNumber}</font>条记录，当前第<font style="font-size:14px;color:#A23434;">${page.currentPage}</font>页</li>
                  <li style="float:left;width:10%;text-align:center;"><a href="javascript:changeCurrentPage('1')">首页</a></li>
                  <li style="float:left;width:10%;text-align:center;"><a href="javascript:changeCurrentPage('${page.currentPage -1}')">上一页</a></li>
                  <li style="float:left;width:10%;text-align:center;"><a href="javascript:changeCurrentPage('${page.currentPage +1}')">下一页</a></li>
                  <li style="float:left;width:10%;text-align:center;"><a href="javascript:changeCurrentPage('${page.totalPage}')">尾页</a></li>
            </ul><br>
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
