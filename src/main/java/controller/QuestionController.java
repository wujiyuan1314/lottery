package main.java.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.java.entity.Question;
import main.java.service.QuestionService;
import main.java.util.Page;

@Controller
@RequestMapping("/question")
public class QuestionController {
	public static Logger logger = Logger.getLogger(QuestionController.class);
	
	@Autowired
	QuestionService questionService;

	@RequestMapping(value="/questions")
	public String listQuestion(Model model, @ModelAttribute Question question, @ModelAttribute Page page, HttpServletRequest request) {
		
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(question.toString());
		List<Question> questions = questionService.listQuestion(question,page);
		model.addAttribute("questions",questions);
		return "question/question_list";
	}
    /**
     * 批量添加问题信息
     * @param filepath
     * @return
     */
    @RequestMapping(value="/questionaddbatch",method=RequestMethod.POST)
    public void addQuestionBatch(String filepath){
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
    /**
	 * 跳转问题信息添加界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/questionadd",method=RequestMethod.GET)
	public String addQuestion(Model model){
		model.addAttribute(new Question());
		return "question/question_add";
	}
   /**
    * 添加问题信息
    * @param question
    * @param br
    * @return
    */
    @RequestMapping(value="/questionadd",method=RequestMethod.POST)
	public String addQuestion(@Validated Question question,BindingResult br){
    	if(br.hasErrors()){
    		return "question/question_add";
    	}
    	int isOk=questionService.insertQuestion(question);
    	return "question/question_list";
	}
    /**
  	 * 跳转问题修改界面
  	 * @param model
  	 * @return
  	 */
  	@RequestMapping(value="/questionedit",method=RequestMethod.GET)
  	public String editQuestion(Model model,int id){
  		Question question=questionService.getQuestionByID(id);
  		model.addAttribute(question);
  		return "question_edit";
  	}
  	 /**
     * 修改问题信息
     * @param question
     * @param br
     * @return
     */
     @RequestMapping(value="/questionedit",method=RequestMethod.POST)
 	public String editQuestion(@Validated Question question,BindingResult br){
     	if(br.hasErrors()){
     		return "question/question_edit";
     	}
     	int isOk=questionService.editQuestion(question);
 		return "welcome";
 	}
     /**
      * 删除问题信息
      * @param id
      * @return
      */
      @RequestMapping(value="/questiondel")
  	public String delQuestion(int id){
     	 questionService.deleteQuestion(id);
  		return "question/question_list";
  	}
      /**
       * 批量删除问题信息
       * @param ids
       * @return
       */
       @RequestMapping(value="/questionsdels")
   	public String delQuestions(int ids[]){
     	  questionService.deleteQuestions(ids);
   		return "question/question_list";
   	}
}
