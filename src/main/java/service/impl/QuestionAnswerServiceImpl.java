package main.java.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import main.java.dao.QuestionAnswerMapper;
import main.java.entity.QuestionAnswer;
import main.java.exception.ExcelException;
import main.java.service.QuestionAnswerService;
import main.java.util.DateUtil;
import main.java.util.JxlExcelUtil;




public class QuestionAnswerServiceImpl implements QuestionAnswerService {
	QuestionAnswerMapper questionanswerMapper;

	@Override
	public void uploadQuestionAnswer(File file) {
		// TODO Auto-generated method stub
		List<QuestionAnswer> list=new ArrayList<QuestionAnswer>();//存放每条数据的List
		try {
			InputStream in = new FileInputStream(file);
			LinkedHashMap<String, String> fieldMap=new LinkedHashMap<String, String>();
			fieldMap.put("所属问题", "questionid");
			fieldMap.put("答案详情", "answerdetail");
			fieldMap.put("是否正确答案", "isright");
			String uniqueFields[]={"答案详情"};
			list=JxlExcelUtil.excelToList(in, "Sheet1", QuestionAnswer.class, fieldMap, uniqueFields);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExcelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(QuestionAnswer questionanswer:list){
			Date addtime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
			questionanswer.setAddtime(addtime);
		}
		if(list.size()>0){
			questionanswerMapper.insertQuestionAnswerBatch(list);//批量插入数据
		}
	}

	@Override
	public int editQuestionAnswer(QuestionAnswer questionanswer) {
		// TODO Auto-generated method stub
		return questionanswerMapper.updateByPrimaryKey(questionanswer);
	}

	@Override
	public int insertQuestionAnswer(QuestionAnswer questionanswer) {
		// TODO Auto-generated method stub
		return questionanswerMapper.insertSelective(questionanswer);
	}

	@Override
	public int deleteQuestionAnswer(int id) {
		// TODO Auto-generated method stub
		return questionanswerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteQuestionAnswers(int[] ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.length;i++){
			questionanswerMapper.deleteByPrimaryKey(ids[i]);
		}
	}

	@Override
	public QuestionAnswer getQuestionAnswerByID(int id) {
		// TODO Auto-generated method stub
		return questionanswerMapper.selectByPrimaryKey(id);
	}


}
