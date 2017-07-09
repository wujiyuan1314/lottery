package main.java.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import main.java.entity.AwardInfo;

public interface AwardInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AwardInfo awardInfo);

    int insertSelective(AwardInfo awardInfo);

    AwardInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AwardInfo awardInfo);

    int updateByPrimaryKey(AwardInfo awardInfo);
    //奖品列表
    List<AwardInfo> listAwardInfo(@Param("awardInfo") AwardInfo awardInfo);
}