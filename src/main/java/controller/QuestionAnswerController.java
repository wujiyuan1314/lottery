package main.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.java.entity.QuestionAnswer;
import main.java.service.QuestionAnswerService;

@Controller
@RequestMapping("/questionanswer")
public class QuestionAnswerController {
	public static Logger logger = Logger.getLogger(QuestionAnswerController.class);
	
	@Autowired
	QuestionAnswerService questionanswerService;
    /**
     * 按照questionid查找答案
     * @param model
     * @param questionid
     * @return
     */
	public String selectByQuestionId(Model model,int questionid){
		List<QuestionAnswer> questionanswers=questionanswerService.selectByQuestionId(questionid);
		model.addAttribute(questionanswers);
		return "questionanswer/questionanswer_list";
	}
    /**
     * 批量添加问题信息
     * @param filepath
     * @return
     */
    @RequestMapping(value="/questionansweraddbatch",method=RequestMethod.POST)
    public String addQuestionAnswerBatch(String filepath){
    	filepath="F://books/questionanswers.xls";
    	File file=new File(filepath);
    	questionanswerService.uploadQuestionAnswer(file);
    	return "questionanswer/questionanswer_list";
    }
    /**
     * 验证答案是否满70分
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value="/verifyanswer",method=RequestMethod.POST)
    public void verifyAnswer(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	response.setCharacterEncoding("UTF-8");
    	PrintWriter out=response.getWriter();
    	String json="{success:0,msg:'提交失败'}";
    	
    	int answers[] = new int[10];
    	for(int i=0;i<answers.length;i++){
    		String answer=request.getParameter("item"+(i+1));
    		if(answer==null||answer.equals("")){
    			json="{success:0,msg:'第"+(i+1)+"题未选择答案'}";
    			out.println(json);
    			return;
    		}else{
    			answers[i]=Integer.valueOf(answer);
    		}
    	}
    	
    	int score=0;//分数
    	String erroranswer="";
    	for(int j=0;j<answers.length;j++){
    		QuestionAnswer questionAnswer=questionanswerService.getQuestionAnswerByID(answers[j]);
    		if(questionAnswer.getIsright().equals("1")){
    			score+=10;
    		}else{
    			if(erroranswer.equals("")){
    				erroranswer+=(j+1)+"";
    			}else{
    				erroranswer+="-"+(j+1)+"";
    			}
    		}
    	}
    	
    	if(score<70){
    		json="{success:2,msg:'抱歉！，您的总分不满70分，不能抽奖',erroranswer:'"+erroranswer+"'}";
    	}else{
    		json="{success:1,msg:'恭喜你，总分超过70分，可以进行抽奖',erroranswer:'"+erroranswer+"'}";
    	}
    	
    	out.print(json);
    }
}
