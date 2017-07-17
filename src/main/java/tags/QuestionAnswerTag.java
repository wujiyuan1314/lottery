package main.java.tags;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import main.java.entity.QuestionAnswer;
import main.java.service.QuestionAnswerService;

public class QuestionAnswerTag extends RequestContextAwareTag{
	private static final long serialVersionUID = 1L;
	private int questionid;
	@Autowired
	QuestionAnswerService questionAnswerService;
	public int doEndTag() throws JspException {
		return super.doEndTag();
	}
	@Override
	public int doStartTagInternal() throws JspException {
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		try{
			JspWriter out = pageContext.getOut();
			questionAnswerService=this.getRequestContext().getWebApplicationContext().getBean(QuestionAnswerService.class);
			List<QuestionAnswer> questionAnswers=questionAnswerService.selectByQuestionId(questionid);
			request.setAttribute("questionAnswers", questionAnswers);
		}catch (Exception e) {
			throw new JspException(e);
		}
		return EVAL_PAGE;
	}
	//--------------------------
	public int getQuestionid() {
		return questionid;
	}
	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}
}
