package main.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import main.java.entity.Question;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question question);

    int insertSelective(Question question);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question question);

    int updateByPrimaryKey(Question question);
    //随机查询10条
    List<Question> SelectRandTen();
    //批量插入问题
    
  	void insertQuestionBatch(List<Question> list);
}