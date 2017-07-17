package main.java.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.dao.QuestionMapper;
import main.java.entity.Question;
import main.java.exception.ExcelException;
import main.java.service.QuestionService;
import main.java.util.DateUtil;
import main.java.util.Function;
import main.java.util.JxlExcelUtil;
import main.java.util.Page;



@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	QuestionMapper questionMapper;
	@Override
	public List<Question> listQuestion(Question question, Page page){
		int totalNumber = questionMapper.countQuestion(question);
		page.setTotalNumber(totalNumber);
		List<Question> questions = questionMapper.listQuestion(question, page);
		return questions;
	}
	@Override
	public void uploadQuestion(File file) {
		// TODO Auto-generated method stub
		List<Question> list=new ArrayList<Question>();//存放每条数据的List
		try {
			InputStream in = new FileInputStream(file);
			LinkedHashMap<String, String> fieldMap=new LinkedHashMap<String, String>();
			fieldMap.put("问题描述", "questionTitle");
			fieldMap.put("问题类型", "type");
			String uniqueFields[]={"问题描述"};
			list=JxlExcelUtil.excelToList(in, "Sheet1", Question.class, fieldMap, uniqueFields);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExcelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Question question:list){
			int id=Function.getUUID();
			Date addtime=DateUtil.parseDateTime(DateUtil.getCurrentDateTimeStr());//创建时间
			question.setId(id);
			question.setAddtime(addtime);
		}
		if(list.size()>0){
			questionMapper.insertQuestionBatch(list);//批量插入数据
		}
	}

	@Override
	public int editQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionMapper.updateByPrimaryKey(question);
	}

	@Override
	public int insertQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionMapper.insertSelective(question);
	}

	@Override
	public int deleteQuestion(int id) {
		// TODO Auto-generated method stub
		return questionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteQuestions(int[] ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.length;i++){
			questionMapper.deleteByPrimaryKey(ids[i]);
		}
	}

	@Override
	public Question getQuestionByID(int id) {
		// TODO Auto-generated method stub
		return questionMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Question> SelectRandTen() {
		// TODO Auto-generated method stub
		return questionMapper.SelectRandTen();
	}


}
