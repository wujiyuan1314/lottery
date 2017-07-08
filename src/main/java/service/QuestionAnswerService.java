package main.java.service;

import java.io.File;

import main.java.entity.QuestionAnswer;

public interface QuestionAnswerService {
	
	/**
	 * 批量导入答案信息
	 * @param file
	 */
	void uploadQuestionAnswer(File file);
	/**
	 * 修改一个答案
	 * @param questionanswer
	 */
	int editQuestionAnswer(QuestionAnswer questionanswer);
	/**
	 * 添加一个答案
	 * @param questionanswer
	 */
	int insertQuestionAnswer(QuestionAnswer questionanswer);
	
	/**
	 * 删除一个答案
	 * @param id
	 */
	int deleteQuestionAnswer(int id);
	
	/**
	 * 批量删除答案
	 * @param ids
	 */
	void deleteQuestionAnswers(int[] ids);
	
	/**
	 * 根据ID获取答案信息
	 * @param id
	 * @return
	 */
	QuestionAnswer getQuestionAnswerByID(int id);
}
