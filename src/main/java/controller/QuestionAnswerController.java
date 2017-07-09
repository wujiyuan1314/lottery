package main.java.controller;

import java.io.File;
import java.util.List;

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
    public String addBookBatch(String filepath){
    	filepath="F://questionanswers/questionanswers.xls";
    	File file=new File(filepath);
    	questionanswerService.uploadQuestionAnswer(file);
    	return "questionanswer/questionanswer_list";
    }
}
