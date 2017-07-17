package main.java.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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

import main.java.entity.AwardInfo;
import main.java.service.AwardInfoService;
import main.java.util.DateUtil;
import main.java.util.Function;

@Controller
@RequestMapping("/awardInfo")
public class AwardInfoController {
	public static Logger logger = Logger.getLogger(AwardInfoController.class);
	
	@Autowired
	AwardInfoService awardInfoService;

	@RequestMapping(value="/awardInfos")
	public String listAwardInfo(Model model, @ModelAttribute AwardInfo awardInfo, HttpServletRequest request) {
		List<AwardInfo> awardInfos = awardInfoService.listAwardInfo(awardInfo);
		String jsArray[][]={{"1","一等奖"},{"2","二等奖"},{"3","三等奖"}};
		for(AwardInfo award:awardInfos){
			award.setAwardGrade(Function.getTitleJsArray(award.getAwardGrade(), jsArray));
		}
		model.addAttribute("awardInfos",awardInfos);
		return "manage/award/award_list";
	}
    /**
	 * 跳转奖品添加界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/awardInfoadd",method=RequestMethod.GET)
	public String addAwardInfo(Model model){
		model.addAttribute(new AwardInfo());
		return "awardInfo/awardInfo_add";
	}
   /**
    * 添加奖品
    * @param awardInfo
    * @param br
    * @return
    */
    @RequestMapping(value="/awardInfoadd",method=RequestMethod.POST)
	public String addAwardInfo(HttpServletRequest request){
    	String awardName=request.getParameter("awardName");
    	String awardGrade=request.getParameter("awardGrade");
    	int awardNum=Function.getInt(request.getParameter("awardNum"),0);
    	BigDecimal probability=BigDecimal.valueOf(Double.valueOf(request.getParameter("probability")));
    	AwardInfo awardInfo=new AwardInfo();
    	awardInfo.setAwardName(awardName);
    	awardInfo.setAwardGrade(awardGrade);
    	awardInfo.setAwardNum(awardNum);
    	awardInfo.setProbability(probability);
    	awardInfo.setAddtime(DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr()));
    	int isOk=awardInfoService.insertAwardInfo(awardInfo);
    	return "redirect:/awardInfo/awardInfos";
	}
    /**
     * 查找一个奖品
     * @param id
     * @param response
     */
  	@RequestMapping(value="/findAwardInfo",method=RequestMethod.POST)
  	public void editAwardInfo(int id,HttpServletResponse response){
  		try {
  			AwardInfo awardinfo =awardInfoService.getAwardInfoByID(id);
  	  		String json="{id:'"+awardinfo.getId()+"',awardName:'"+awardinfo.getAwardName()+"',awardGrade:'"+awardinfo.getAwardGrade()+"',awardNum:'"+awardinfo.getAwardNum()+"',probability:'"+awardinfo.getProbability()+"'}";
  	  	    response.setCharacterEncoding("UTF-8");
  	  		PrintWriter out=response.getWriter();
			out.print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
  	 /**
     * 修改奖品
     * @param awardInfo
     * @param br
     * @return
     */
     @RequestMapping(value="/awardInfoedit",method=RequestMethod.POST)
 	public String editAwardInfo(@Validated AwardInfo awardInfo,BindingResult br){
     	if(br.hasErrors()){
     		return "awardInfo/awardInfo_edit";
     	}
     	int isOk=awardInfoService.editAwardInfo(awardInfo);
 		return "welcome";
 	}
     /**
      * 删除奖品
      * @param id
      * @return
      */
      @RequestMapping(value="/awardInfodel",method=RequestMethod.POST)
  	public String delAwardInfo(int id){
     	 awardInfoService.deleteAwardInfo(id);
  		return "manage/award/award_list";
  	}
      /**
       * 批量删除奖品
       * @param ids
       * @return
       */
       @RequestMapping(value="/awardInfosdels")
   	public String delAwardInfos(int ids[]){
     	  awardInfoService.deleteAwardInfos(ids);
   		return "awardInfo/awardInfo_list";
   	}
}
