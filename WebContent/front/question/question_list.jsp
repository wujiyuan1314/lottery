<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>  
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="questionanswer" uri="/questionanswer-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>问题列表</title>
<!-- basic styles -->
<link href="<%=basePath%>/front/question/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>/front/question/assets/css/font-awesome.min.css" />
<!--[if IE 7]>
  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
<![endif]-->
<!-- page specific plugin styles -->
<!-- ace styles -->
<link rel="stylesheet" href="<%=basePath%>/front/question/assets/css/ace.min.css" />
<link rel="stylesheet" href="<%=basePath%>/front/question/assets/css/index.css" />
<!--[if lte IE 8]>
  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
<![endif]-->
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="assets/js/html5shiv.js"></script>
<script src="assets/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript">
	function clickTrim(source){
    var id = source.id;
    $(".question_info").removeClass("clickTrim");
    $("#"+id).find("input").prop("checked","checked");
    $("#"+id).addClass("clickTrim");
}
</script>
<style>
.wizard-steps li.complete .error:before {
    display: block;
    position: absolute;
    top: 0;
    left: 0;
    bottom: 0;
    right: 0;
    line-height: 30px;
    text-align: center;
    border-radius: 100%;
    content: "\f00d";
    background-color: #FFF;
    z-index: 3;
    font-family: FontAwesome;
    font-size: 17px;
    color: #FF0000;
}
</style>
</head>
<body>
<div class="navbar navbar-default" id="navbar">
	<script type="text/javascript">
		try{ace.settings.check('navbar' , 'fixed')}catch(e){}
	</script>

	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand">
				<small>
					<i class="icon-flag"></i>
					答题抽奖活动
				</small>
			</a><!-- /.brand -->
		</div><!-- /.navbar-header -->

		
	</div><!-- /.container -->
</div>

<div class="main-container" id="main-container">
	<div class="main-container-inner">
		<a class="menu-toggler" id="menu-toggler" href="#">
			<span class="menu-text"></span>
		</a>

		<div class="main-content">
			

			<div class="page-content">
				<div class="page-header">
					<h1>
						总分达到70分可以获得一次抽奖机会
					</h1>
				</div><!-- /.page-header -->

				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
						<div class="row-fluid">
							<div class="span12">
								<div class="widget-box">
									<div class="widget-header widget-header-blue widget-header-flat">
										<h4 class="lighter">题目</h4>

									</div>

									<div class="widget-body">
										<div class="widget-main">
											<div id="fuelux-wizard" class="row-fluid" data-target="#step-container">
												<ul class="wizard-steps">
													<li data-target="#step1" class="active">
														<span class="step" id="xxstep1">1</span>
														<span class="title">第一题</span>
													</li>

													<li data-target="#step2">
														<span class="step" id="xxstep2">2</span>
														<span class="title">第二题</span>
													</li>

													<li data-target="#step3">
														<span class="step" id="xxstep3">3</span>
														<span class="title">第三题</span>
													</li>

													<li data-target="#step4">
														<span class="step" id="xxstep4">4</span>
														<span class="title">第四题</span>
													</li>
													<li data-target="#step5">
														<span class="step" id="xxstep5">5</span>
														<span class="title">第五题</span>
													</li>
													<li data-target="#step6">
														<span class="step" id="xxstep6">6</span>
														<span class="title">第六题</span>
													</li>
													<li data-target="#step7">
														<span class="step" id="xxstep7">7</span>
														<span class="title">第七题</span>
													</li>
													<li data-target="#step8">
														<span class="step" id="xxstep8">8</span>
														<span class="title">第八题</span>
													</li>
													<li data-target="#step9">
														<span class="step" id="xxstep9">9</span>
														<span class="title">第九题</span>
													</li>
													<li data-target="#step10">
														<span class="step" id="xxstep10">10</span>
														<span class="title">第十题</span>
													</li>
												</ul>
											</div>

											<hr />
											<div class="step-content row-fluid position-relative" id="step-container">
											<%int i=0; %>
											<form class="form-horizontal" id="sample-form">
												<c:forEach items="${questions}" var="question" varStatus="st">
												 <c:choose>
													<c:when test="${st.index==0 }">
														<div class="step-pane active" id="step${st.index+1 }">
															<div class="left">
																<h3 class="green">${question.questionTitle }</h3>
																<ul class="list-unstyled question" id="question7" name="">
																  <questionanswer:answer questionid="${question.id }">
																    <c:forEach items="${questionAnswers}" var="questionAnswer" varStatus="st1">
																       <li class="question_info" onclick="clickTrim(this)" id="itemitem${st.index}${st1.index}">
																       <input type="radio" name="item${st.index+1}" value="${questionAnswer.id }">&nbsp;<%=(char)('A'+i) %>.${ questionAnswer.answerdetail}</li>
																       <%i++; %>
																    </c:forEach>
																  </questionanswer:answer>
																</ul>
															</div>
														</div>
													</c:when> 
													<c:otherwise>
													    <div class="step-pane" id="step${st.index+1 }">
															<div class="left">
																<h3 class="green">${question.questionTitle }</h3>
																<ul class="list-unstyled question" id="question7" name="">
																  <questionanswer:answer questionid="${question.id }">
																    <c:forEach items="${questionAnswers}" var="questionAnswer" varStatus="st1">
																       <li class="question_info" onclick="clickTrim(this)" id="item${st.index}${st1.index}">
																       <input type="radio" name="item${st.index+1}" value="${questionAnswer.id }">&nbsp;<%=(char)('A'+i) %>.${ questionAnswer.answerdetail}</li>
																       <%i++; %>
																    </c:forEach>
																  </questionanswer:answer>
																</ul>
															</div>
														</div>
													</c:otherwise>
											     </c:choose>
											     <%i=0; %>
												</c:forEach>
										    </form>
										    
											</div>

											<hr />
											<div class="row-fluid wizard-actions">
												<button class="btn btn-success" id="submit">
													提交试卷
												</button>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<button class="btn btn-prev">
													<i class="icon-arrow-left"></i>
													Prev
												</button>

												<button class="btn btn-success btn-next" data-last="Finish ">
													Next
													<i class="icon-arrow-right icon-on-right"></i>
												</button>
											</div>
										</div><!-- /widget-main -->
									</div><!-- /widget-body -->
								</div>
							</div>
						</div>

						
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.page-content -->
		</div><!-- /.main-content -->
	</div><!-- /.main-container-inner -->

	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
		<i class="icon-double-angle-up icon-only bigger-110"></i>
	</a>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->


<!-- <![endif]-->

<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

<!--[if !IE]> -->

<script type="text/javascript">
	window.jQuery || document.write("<script src='/lottery/front/question/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

<script type="text/javascript">
	if("ontouchend" in document) document.write("<script src='/lottery/front/question/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="<%=basePath%>/front/question/assets/js/bootstrap.min.js"></script>
<!-- page specific plugin scripts -->

<script src="<%=basePath%>/front/question/assets/js/fuelux/fuelux.wizard.min.js"></script>
<script src="<%=basePath%>/front/question/assets/js/jquery.validate.min.js"></script>
<script src="<%=basePath%>/front/question/assets/js/bootbox.min.js"></script>
<script src="<%=basePath%>/front/question/assets/js/select2.min.js"></script>

<!-- ace scripts -->

<script src="<%=basePath%>/front/question/assets/js/ace-elements.min.js"></script>
<script src="<%=basePath%>/front/question/assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->

<script type="text/javascript">
var basePath="<%=basePath%>";
	jQuery(function($) {
	
		$('[data-rel=tooltip]').tooltip();
	
		$(".select2").css('width','200px').select2({allowClear:true})
		.on('change', function(){
			$(this).closest('form').validate().element($(this));
		}); 
	
	
		var $validation = false;
		$('#fuelux-wizard').ace_wizard().on('change' , function(e, info){
			if(info.step == 1 && $validation) {
				if(!$('#validation-form').valid()) return false;
			}
		}).on('finished', function(e) {
			bootbox.dialog({
				message: "你的题目已经作答完毕，点击提交按钮可以提交答案。", 
				buttons: {
					"success" : {
						"label" : "OK",
						"className" : "btn-sm btn-primary"
					}
				}
			});
		}).on('stepclick', function(e){
			//return false;//prevent clicking on steps
		});
	
	
		$('#skip-validation').removeAttr('checked').on('click', function(){
			$validation = this.checked;
			if(this.checked) {
				$('#sample-form').hide();
				$('#validation-form').removeClass('hide');
			}
			else {
				$('#validation-form').addClass('hide');
				$('#sample-form').show();
			}
		});
							
		//documentation : http://docs.jquery.com/Plugins/Validation/validate	
		$('#modal-wizard .modal-header').ace_wizard();
		$('#modal-wizard .wizard-actions .btn[data-dismiss=modal]').removeAttr('disabled');

		$('#submit').click(function(){
			bootbox.confirm("确认提交？",function(res){
				if(res){
					var params_form = $("#sample-form").serialize();
					var url=basePath+"/questionanswer/verifyanswer";
					$.post(url,params_form,function(responseText){
				        var data=eval("("+responseText+")");  
						var success=data.success;                                
				 		if(success==0){                                          
				 			bootbox.alert(data.msg);                                  
		          		}else if(success==1){ 
		          			var erroranswer=data.erroranswer;
		          			bootbox.alert("操作成功!"); 
		          			if(erroranswer!=" "){
		          				var erroranswers=erroranswer.split("-");
		          				for(var i=0;i<erroranswers.length;i++){
		          					$("#xxstep"+erroranswers[i]).addClass("error");
		          					$("input[type='radio']").attr("disabled",true);
		          				}
		                	}
		          		}else if(success==2){    
		          			var erroranswer=data.erroranswer;
		          			bootbox.alert(data.msg);                              
		                	if(erroranswer!=" "){
		          				var erroranswers=erroranswer.split("-");
		          				for(var i=0;i<erroranswers.length;i++){
		          					$("#xxstep"+erroranswers[i]).addClass("error");
		          					$("input[type='radio']").attr("disabled",true);
		          				}
		                	}
		          		}                              
				    });                                 
				}	
			
			})
		});
	})
</script>
</body>
</html>