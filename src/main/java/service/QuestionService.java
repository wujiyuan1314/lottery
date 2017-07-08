package main.java.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import main.java.entity.Question;

public interface QuestionService {
	
	/**
	 * 批量导入问题信息
	 * @param file
	 */
	void uploadQuestion(File file);
	/**
	 * 修改一个问题
	 * @param question
	 */
	int editQuestion(Question question);
	/**
	 * 添加一个问题
	 * @param question
	 */
	int insertQuestion(Question question);
	
	/**
	 * 删除一个问题
	 * @param id
	 */
	int deleteQuestion(int id);
	
	/**
	 * 批量删除问题
	 * @param ids
	 */
	void deleteQuestions(int[] ids);
	
	/**
	 * 根据ID获取问题信息
	 * @param id
	 * @return
	 */
	Question getQuestionByID(int id);
}
