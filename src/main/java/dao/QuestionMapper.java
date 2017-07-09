package main.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import main.java.entity.Question;
import main.java.util.Page;

public interface QuestionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Question question);

    int insertSelective(Question question);

    Question selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Question question);

    int updateByPrimaryKey(Question question);
    //问题列表
    List<Question> listQuestion(@Param("question") Question question, @Param("page") Page page);
	
	//按条件查询书籍信息
	List<Question> selectByParams(@Param("question") Question question);
	
	int countQuestion(Question question);
    //随机查询10条
    List<Question> SelectRandTen();
    //批量插入问题
    
  	void insertQuestionBatch(List<Question> list);
}