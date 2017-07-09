package main.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import main.java.dao.AwardInfoMapper;
import main.java.entity.AwardInfo;
import main.java.service.AwardInfoService;

public class AwardInfoServiceImpl implements AwardInfoService {
	@Autowired
	AwardInfoMapper awardInfoMapper;
	@Override
	public List<AwardInfo> listAwardInfo(AwardInfo awardInfo) {
		// TODO Auto-generated method stub
		return awardInfoMapper.listAwardInfo(awardInfo);
	}

	@Override
	public int editAwardInfo(AwardInfo awardInfo) {
		// TODO Auto-generated method stub
		return awardInfoMapper.updateByPrimaryKeySelective(awardInfo);
	}

	@Override
	public int insertAwardInfo(AwardInfo awardInfo) {
		// TODO Auto-generated method stub
		return awardInfoMapper.insertSelective(awardInfo);
	}

	@Override
	public int deleteAwardInfo(int id) {
		// TODO Auto-generated method stub
		return awardInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteAwardInfos(int[] ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.length;i++){
			awardInfoMapper.deleteByPrimaryKey(ids[i]);
		}

	}

	@Override
	public AwardInfo getAwardInfoByID(int id) {
		// TODO Auto-generated method stub
		return awardInfoMapper.selectByPrimaryKey(id);
	}

}
