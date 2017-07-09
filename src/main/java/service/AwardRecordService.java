package main.java.service;

import java.util.List;

import main.java.entity.AwardRecord;
import main.java.util.Page;
public interface AwardRecordService {
	/**
	 * 根据输入信息条件查询抽奖状态列表，并分页显示
	 * @param bookInfo
	 * @param page
	 * @return
	 */
	List<AwardRecord> listAwardRecord(AwardRecord awardRecord, Page page);
	/**
	 * 修改一个抽奖状态
	 * @param awardRecord
	 */
	int editAwardRecord(AwardRecord awardRecord);
	/**
	 * 添加一个抽奖状态
	 * @param awardRecord
	 */
	int insertAwardRecord(AwardRecord awardRecord);
	
	/**
	 * 删除一个抽奖状态
	 * @param id
	 */
	int deleteAwardRecord(int id);
	
	/**
	 * 批量删除抽奖状态
	 * @param ids
	 */
	void deleteAwardRecords(int[] ids);
	
	/**
	 * 根据ID获取抽奖状态信息
	 * @param id
	 * @return
	 */
	AwardRecord getAwardRecordByID(int id);
}
