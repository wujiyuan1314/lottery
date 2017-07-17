package main.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import main.java.entity.QuestionAnswer;

public interface QuestionAnswerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuestionAnswer questionanswer);

    int insertSelective(QuestionAnswer questionanswer);

    QuestionAnswer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuestionAnswer questionanswer);

    int updateByPrimaryKey(QuestionAnswer questionanswer);
    //按问题ID查询该问题答案
    List<QuestionAnswer> selectByQuestionId(@Param("questionid") Integer questionid);
    //批量插入问题
  	void insertQuestionAnswerBatch(List<QuestionAnswer> list);
}