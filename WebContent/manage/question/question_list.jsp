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
<title>问题管理</title>
</head>
<body class="user-select">

<%@include file="../top.jsp" %>
  
  <div class="row">
  
    <aside class="col-sm-3 col-md-2 col-lg-2 sidebar">
	<ul class="nav nav-sidebar">
	    <li><a href="<%=basePath %>/manage/index.jsp">首页</a></li>
	  </ul>
	  <ul class="nav nav-sidebar">
	    <li  class="active"><a href="<%=basePath %>/question/questions">问题管理</a></li>
	  </ul>
	  <ul class="nav nav-sidebar">
	    <li><a href="<%=basePath %>/awardInfo/awardInfos">奖品管理</a></li>
	  </ul>
	  <ul class="nav nav-sidebar">
	    <li><a href="<%=basePath %>/awardRecord/awardRecords">中奖纪录</a></li>
	  </ul>
	</aside>
    
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-lg-10 col-md-offset-2 main" id="main">
      <div class="row">
        <div class="col-md-5">
          <h1 class="page-header" onclick="javascript:window.location.reload();" style="cursor:pointer;">添加问题</h1>
          <form action="/lottery/question/updatequestion" method="post" id="addquestionform" autocomplete="off">
            <input type="hidden" name="id" id="id" value="">
            <input type="hidden" name="answernum" id="answernum" value="1">
            <div class="form-group">
              <label for="category-questionTitle">问题描述</label>
              <textarea class="form-control" id="category-questionTitle" name="questionTitle" rows="4"  required autocomplete="off">
              </textarea>
              <span class="prompt-text">描述问题的详细信息。</span>
            </div>
            
            <div class="form-group">
              <label for="category-fname">问题类型</label>
              <select id="category-type" class="form-control" name="type">
                <option value="0" selected>单选</option>
                <option value="1">多选</option>
              </select>
            </div>
            
            <div class="form-group">
             <label for="category-answerA">答案1</label>
             <input type="text" id="category-answer" name="answer1" class="form-control" placeholder="在此处输入答案1" required autocomplete="off">
             <input type="hidden" name="answerid1" id="answerid1" value="">
              <select id="category-type" class="form-control" name="isright1">
                <option value="0" selected>不是正确答案</option>
                <option value="1">是正确答案</option>
              </select>
            </div>
            
            <button class="btn btn-primary" type="submit" name="submit">提交问题</button>
            <a class="btn btn-primary" onclick="addAnswer(1);" id="addAnswer">继续添加答案</a>
          </form>
        </div>
        <div class="col-md-7">
          <h1 class="page-header">问题管理</h1>
          <div class="table-responsive">
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th><span class="glyphicon glyphicon-paperclip"></span> <span class="visible-lg">序号</span></th>
                  <th><span class="glyphicon glyphicon-file"></span> <span class="visible-lg">问题描述</span></th>
                  <th><span class="glyphicon glyphicon-list-alt"></span> <span class="visible-lg">类型</span></th>
                  <th><span class="glyphicon glyphicon-pencil"></span> <span class="visible-lg">操作</span></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${questions}" var="question" varStatus="st">
	                <tr onclick="findOne('${question.id}');">
	                  <td>${st.index+1 }</td>
	                  <td style="width:72%;">${question.questionTitle }</td>
	                  <td>${question.type }</td>
	                  <td> <a rel="${question.id }">删除</a></td>
	                </tr>
                </c:forEach>
              </tbody>
            </table>
            <sf:form value="/lottery/question/questions" method="post" name="pageform">
              <input type="hidden" name="currentPage" id="currentPage" value="1" />
            </sf:form>
            <ul style="width:94%;padding-left:24%;">
                  <li style="float:left;width:30%;text-align:center;">共<font style="font-size:14px;color:#A23434;">${page.totalNumber}</font>条记录，当前第<font style="font-size:14px;color:#A23434;">${page.currentPage}</font>页</li>
                  <li style="float:left;width:10%;text-align:center;"><a href="javascript:changeCurrentPage('1')">首页</a></li>
                  <li style="float:left;width:10%;text-align:center;"><a href="javascript:changeCurrentPage('${page.currentPage -1}')">上一页</a></li>
                  <li style="float:left;width:10%;text-align:center;"><a href="javascript:changeCurrentPage('${page.currentPage +1}')">下一页</a></li>
                  <li style="float:left;width:10%;text-align:center;"><a href="javascript:changeCurrentPage('${page.totalPage}')">尾页</a></li>
            </ul><br>
            <span class="prompt-text"><strong>注：</strong>删除一个问题也会删除与该问题相关的答案信息!</span> </div>
        </div>
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
					url:basePath+"/question/questiondel",
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
</script>
</body>
</html>
