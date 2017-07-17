package main.java.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.dao.AwardRecordMapper;
import main.java.dao.AwardRecordMapper;
import main.java.entity.AwardRecord;
import main.java.entity.AwardRecord;
import main.java.service.AwardRecordService;
import main.java.util.Page;
@Service
public class AwardRecordServiceImpl implements AwardRecordService {
	@Autowired
	AwardRecordMapper awardRecordMapper;
	@Override
	public List<AwardRecord> listAwardRecord(AwardRecord awardRecord, Page page) {
		// TODO Auto-generated method stub
		int totalNumber = awardRecordMapper.countAwardRecord(awardRecord);
		page.setTotalNumber(totalNumber);
		List<AwardRecord> awardRecords = awardRecordMapper.listAwardRecord(awardRecord, page);
		return awardRecords;
	}

	@Override
	public int editAwardRecord(AwardRecord awardRecord) {
		// TODO Auto-generated method stub
		return awardRecordMapper.updateByPrimaryKeySelective(awardRecord);
	}

	@Override
	public int insertAwardRecord(AwardRecord awardRecord) {
		// TODO Auto-generated method stub
		return awardRecordMapper.insertSelective(awardRecord);
	}

	@Override
	public int deleteAwardRecord(int id) {
		// TODO Auto-generated method stub
		return awardRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void deleteAwardRecords(int[] ids) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.length;i++){
			awardRecordMapper.deleteByPrimaryKey(ids[i]);
		}
	}

	@Override
	public AwardRecord getAwardRecordByID(int id) {
		// TODO Auto-generated method stub
		return awardRecordMapper.selectByPrimaryKey(id);
	}

}
