package main.java.controller;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import main.java.service.QuestionService;

@Controller
@RequestMapping("/question")
public class QuestionController {
	public static Logger logger = Logger.getLogger(QuestionController.class);
	
	@Autowired
	QuestionService questionService;

	
    /**
     * 批量添加问题信息
     * @param filepath
     * @return
     */
    @RequestMapping(value="/questionaddbatch",method=RequestMethod.POST)
    public String addBookBatch(String filepath){
    	filepath="F://questions/questions.xls";
    	File file=new File(filepath);
    	questionService.uploadQuestion(file);
    	return "question/question_list";
    }
}
