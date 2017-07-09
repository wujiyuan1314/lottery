package main.java.controller;

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

import main.java.entity.AwardRecord;
import main.java.service.AwardRecordService;
import main.java.util.Page;

@Controller
@RequestMapping("/awardRecord")
public class AwardRecordController {
	public static Logger logger = Logger.getLogger(AwardRecordController.class);
	
	@Autowired
	AwardRecordService awardRecordService;

	@RequestMapping(value="/awardRecords")
	public String listAwardRecord(Model model, @ModelAttribute AwardRecord awardRecord,@ModelAttribute Page page, HttpServletRequest request) {
		String currentPageStr = request.getParameter("currentPage");
		logger.info(currentPageStr + "===========");
		if(currentPageStr != null){
			int currentPage = Integer.parseInt(currentPageStr);
			page.setCurrentPage(currentPage);
		}
		logger.info(page.toString());
		logger.info(awardRecord.toString());
		List<AwardRecord> awardRecords = awardRecordService.listAwardRecord(awardRecord,page);
		model.addAttribute("awardRecords",awardRecords);
		return "question/question_list";
	}
    /**
	 * 跳转中奖纪录添加界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/awardRecordadd",method=RequestMethod.GET)
	public String addAwardRecord(Model model){
		model.addAttribute(new AwardRecord());
		return "awardRecord/awardRecord_add";
	}
   /**
    * 添加中奖纪录
    * @param awardRecord
    * @param br
    * @return
    */
    @RequestMapping(value="/awardRecordadd",method=RequestMethod.POST)
	public String addAwardRecord(@Validated AwardRecord awardRecord,BindingResult br){
    	if(br.hasErrors()){
    		return "awardRecord/awardRecord_add";
    	}
    	int isOk=awardRecordService.insertAwardRecord(awardRecord);
    	return "awardRecord/awardRecord_list";
	}
    /**
  	 * 跳转中奖纪录修改界面
  	 * @param model
  	 * @return
  	 */
  	@RequestMapping(value="/awardRecordedit",method=RequestMethod.GET)
  	public String editAwardRecord(Model model,int id){
  		AwardRecord awardRecord=awardRecordService.getAwardRecordByID(id);
  		model.addAttribute(awardRecord);
  		return "awardRecord_edit";
  	}
  	 /**
     * 修改中奖纪录
     * @param awardRecord
     * @param br
     * @return
     */
     @RequestMapping(value="/awardRecordedit",method=RequestMethod.POST)
 	public String editAwardRecord(@Validated AwardRecord awardRecord,BindingResult br){
     	if(br.hasErrors()){
     		return "awardRecord/awardRecord_edit";
     	}
     	int isOk=awardRecordService.editAwardRecord(awardRecord);
 		return "welcome";
 	}
     /**
      * 删除中奖纪录
      * @param id
      * @return
      */
      @RequestMapping(value="/awardRecorddel")
  	public String delAwardRecord(int id){
     	 awardRecordService.deleteAwardRecord(id);
  		return "awardRecord/awardRecord_list";
  	}
      /**
       * 批量删除中奖纪录
       * @param ids
       * @return
       */
       @RequestMapping(value="/awardRecordsdels")
   	public String delAwardRecords(int ids[]){
     	  awardRecordService.deleteAwardRecords(ids);
   		return "awardRecord/awardRecord_list";
   	}
}
