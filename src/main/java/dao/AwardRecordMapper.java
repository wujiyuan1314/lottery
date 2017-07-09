package main.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import main.java.entity.AwardRecord;
import main.java.util.Page;

public interface AwardRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AwardRecord awardRecord);

    int insertSelective(AwardRecord awardRecord);

    AwardRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AwardRecord awardRecord);

    int updateByPrimaryKey(AwardRecord awardRecord);
    //奖品列表
    List<AwardRecord> listAwardRecord(@Param("awardRecord") AwardRecord awardRecord,@Param("page") Page page);
    
    int countAwardRecord(AwardRecord awardRecord);
}