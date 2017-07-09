package main.java.service;

import java.util.List;

import main.java.entity.AwardInfo;
public interface AwardInfoService {
	/**
	 * 根据输入信息条件查询奖品列表
	 * @param bookInfo
	 * @param page
	 * @return
	 */
	List<AwardInfo> listAwardInfo(AwardInfo awardInfo);
	/**
	 * 修改一个奖品
	 * @param awardInfo
	 */
	int editAwardInfo(AwardInfo awardInfo);
	/**
	 * 添加一个奖品
	 * @param awardInfo
	 */
	int insertAwardInfo(AwardInfo awardInfo);
	
	/**
	 * 删除一个奖品
	 * @param id
	 */
	int deleteAwardInfo(int id);
	
	/**
	 * 批量删除奖品
	 * @param ids
	 */
	void deleteAwardInfos(int[] ids);
	
	/**
	 * 根据ID获取奖品信息
	 * @param id
	 * @return
	 */
	AwardInfo getAwardInfoByID(int id);
}
