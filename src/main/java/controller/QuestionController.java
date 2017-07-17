package main.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.java.entity.AwardRecord;
import main.java.entity.Question;
import main.java.entity.QuestionAnswer;
import main.java.service.QuestionAnswerService;
import main.java.service.QuestionService;
import main.java.util.DateUtil;
import main.java.util.Function;
import main.java.util.Page;

@Controller
@RequestMapping("/question")
public class QuestionController {
	public static Logger logger = Logger.getLogger(QuestionController.class);
	
	@Autowired
	QuestionService questionService;
	@Autowired
	QuestionAnswerService questionAnswerService;

	@RequestMapping(value="/questions")
	public String listQuestion(Model model, @ModelAttribute Question question, @ModelAttribute Page page, HttpServletRequest request) {
		
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}else{
			currentPageStr="1";
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(question.toString());
		List<Question> questions = questionService.listQuestion(question,page);
		String jsArray[][]={{"0","单选"},{"1","多选"}};
		for(Question q:questions){
			q.setType(Function.getTitleJsArray(q.getType(), jsArray));
		}
		model.addAttribute("questions",questions);
		return "manage/question/question_list";
	}
    /**
     * 批量添加问题信息
     * @param filepath
     * @return
     */
    @RequestMapping(value="/questionaddbatch",method=RequestMethod.POST)
    public void addQuestionBatch(String filepath){
    	filepath="F://books/questions.xls";
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
    	return "front/question/question_list";
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
    * 添加或修改问题信息
    * @param request
    * @return
    */
    @RequestMapping(value="/updatequestion",method=RequestMethod.POST)
	public String updteQuestion(HttpServletRequest request){
    	int id=Function.getInt(request.getParameter("id"),0);
    	String questionTitle=request.getParameter("questionTitle");
    	String type=request.getParameter("type");
    	Date addtime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());
    	int answernum=Function.getInt(request.getParameter("answernum"), 1);
    	
    	Question question=questionService.getQuestionByID(id);
    	if(question!=null){
    		question.setQuestionTitle(questionTitle);
    		question.setType(type);
    		questionService.editQuestion(question);
    		
    		for(int i=1;i<=answernum;i++){
    			int answerid=Function.getInt(request.getParameter("answerid"+i),0);
    			QuestionAnswer questionAnswer=questionAnswerService.getQuestionAnswerByID(answerid);
    			if(questionAnswer!=null){
    				String answer=request.getParameter("answer"+i);
        	    	String isright=request.getParameter("isright"+i);
    				questionAnswer.setAnswerdetail(answer);
    				questionAnswer.setIsright(isright);
    				questionAnswerService.editQuestionAnswer(questionAnswer);
    			}else{
    				questionAnswer=new QuestionAnswer();
    				String answer=request.getParameter("answer"+i);
        	    	String isright=request.getParameter("isright"+i);
        	    	questionAnswer.setAnswerdetail(answer);
        	    	questionAnswer.setAddtime(addtime);
        	    	questionAnswer.setIsright(isright);
        	    	questionAnswer.setQuestionid(id);
        	    	questionAnswerService.insertQuestionAnswer(questionAnswer);
    			}
    		}
    	}else{
    		question=new Question();//问题信息
    		id=Function.getUUID();
    		question.setId(id);
    		question.setQuestionTitle(questionTitle);
    		question.setType(type);
    		question.setAddtime(addtime);
    		questionService.insertQuestion(question);//插入一条数据
    		
    		for(int i=1;i<=answernum;i++){
    			QuestionAnswer questionAnswer=new QuestionAnswer();
    			String answer=request.getParameter("answer"+i);
    	    	String isright=request.getParameter("isright"+i);
    	    	questionAnswer.setAnswerdetail(answer);
    	    	questionAnswer.setAddtime(addtime);
    	    	questionAnswer.setIsright(isright);
    	    	questionAnswer.setQuestionid(id);
    	    	questionAnswerService.insertQuestionAnswer(questionAnswer);
    		}
    	}
    	return "redirect:/question/questions";
	}
    /**
     * 查找一个问题信息
     * @param id
     * @param response
     * @throws IOException 
     */
  	@RequestMapping(value="/findquestion",method=RequestMethod.POST)
  	public void findQuestion(int id,HttpServletResponse response) throws IOException{
  		response.setCharacterEncoding("UTF-8");
    	PrintWriter out=response.getWriter();
  		String json="";
  		Question question=questionService.getQuestionByID(id);
  	    if(question!=null){
  	    	List<QuestionAnswer> list=questionAnswerService.selectByQuestionId(question.getId());
  	    	String jsonanswer="[";
  	    	for(QuestionAnswer questionAnswer:list){
  	    		if(!"[".equals(jsonanswer)){
  	    			jsonanswer+=",{";
  	    			jsonanswer+="answerid:'"+questionAnswer.getId()+"'";
  	    			jsonanswer+=",answer:'"+questionAnswer.getAnswerdetail()+"'";
  	    			jsonanswer+=",isright:'"+questionAnswer.getIsright()+"'";
  	    			jsonanswer+="}";
  	    		}else{
  	    			jsonanswer+="{";
  	    			jsonanswer+="answerid:'"+questionAnswer.getId()+"'";
  	    			jsonanswer+=",answer:'"+questionAnswer.getAnswerdetail()+"'";
  	    			jsonanswer+=",isright:'"+questionAnswer.getIsright()+"'";
  	    			jsonanswer+="}";
  	    		}
  	    	}
  	    	jsonanswer+="]";
  	    	json="{questionTile:'"+question.getQuestionTitle()+"',type:'"+question.getType()+"',questionaswer:"+jsonanswer+"}";
  	    }
  	  out.print(json);
  	}
  	 /**
     * 修改问题信息
     * @param question
     * 
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
      @RequestMapping(value="/questiondel",method=RequestMethod.POST)
  	public String delQuestion(int id){
     	 questionService.deleteQuestion(id);
     	 List<QuestionAnswer> list=questionAnswerService.selectByQuestionId(id);
     	 int ids[]=new int[list.size()];
     	 for(int i=0;i<list.size();i++){
     		 ids[i]=list.get(i).getId();
     	 }
     	questionAnswerService.deleteQuestionAnswers(ids);
  		return "manage/question/question_list";
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
