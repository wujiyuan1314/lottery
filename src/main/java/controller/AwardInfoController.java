package main.java.controller;

import java.util.List;

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

@Controller
@RequestMapping("/awardInfo")
public class AwardInfoController {
	public static Logger logger = Logger.getLogger(AwardInfoController.class);
	
	@Autowired
	AwardInfoService awardInfoService;

	@RequestMapping(value="/awardInfos")
	public String listAwardInfo(Model model, @ModelAttribute AwardInfo awardInfo) {
		List<AwardInfo> awardInfos = awardInfoService.listAwardInfo(awardInfo);
		model.addAttribute("awardInfos",awardInfos);
		return "awardInfo/awardInfo_list";
	}
    /**
	 * 跳转奖品信息添加界面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/awardInfoadd",method=RequestMethod.GET)
	public String addAwardInfo(Model model){
		model.addAttribute(new AwardInfo());
		return "awardInfo/awardInfo_add";
	}
   /**
    * 添加奖品信息
    * @param awardInfo
    * @param br
    * @return
    */
    @RequestMapping(value="/awardInfoadd",method=RequestMethod.POST)
	public String addAwardInfo(@Validated AwardInfo awardInfo,BindingResult br){
    	if(br.hasErrors()){
    		return "awardInfo/awardInfo_add";
    	}
    	int isOk=awardInfoService.insertAwardInfo(awardInfo);
    	return "awardInfo/awardInfo_list";
	}
    /**
  	 * 跳转奖品修改界面
  	 * @param model
  	 * @return
  	 */
  	@RequestMapping(value="/awardInfoedit",method=RequestMethod.GET)
  	public String editAwardInfo(Model model,int id){
  		AwardInfo awardInfo=awardInfoService.getAwardInfoByID(id);
  		model.addAttribute(awardInfo);
  		return "awardInfo_edit";
  	}
  	 /**
     * 修改奖品信息
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
      * 删除奖品信息
      * @param id
      * @return
      */
      @RequestMapping(value="/awardInfodel")
  	public String delAwardInfo(int id){
     	 awardInfoService.deleteAwardInfo(id);
  		return "awardInfo/awardInfo_list";
  	}
      /**
       * 批量删除奖品信息
       * @param ids
       * @return
       */
       @RequestMapping(value="/awardInfosdels")
   	public String delAwardInfos(int ids[]){
     	  awardInfoService.deleteAwardInfos(ids);
   		return "awardInfo/awardInfo_list";
   	}
}
