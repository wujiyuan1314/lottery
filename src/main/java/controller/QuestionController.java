package main.java.controller;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.java.entity.Question;
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
    public void addBookBatch(String filepath){
    	filepath="F://questions/questions.xls";
    	File file=new File(filepath);
    	questionService.uploadQuestion(file);
    }
    /**
     * 随机查询10条记录
     * @param model
     * @return
     */
    @RequestMapping(value="/findrandten")
    public String SelectRandTen(Model model){
    	List<Question> list=questionService.SelectRandTen();
    	model.addAttribute("questions",list);
    	return "question/question_list";
    }
}
