package main.java.controller;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.java.service.QuestionAnswerService;

@Controller
@RequestMapping("/questionanswer")
public class QuestionAnswerController {
	public static Logger logger = Logger.getLogger(QuestionAnswerController.class);
	
	@Autowired
	QuestionAnswerService questionanswerService;

	
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
