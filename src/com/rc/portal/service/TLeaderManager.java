package com.rc.portal.service;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderExample;
import com.rc.portal.vo.TLeaderImage;
import com.rc.portal.vo.TMember;
import com.rc.portal.vo.TMemberBaseMessageExt;

public interface TLeaderManager {
	int countByExample(TLeaderExample example) throws SQLException;

	int deleteByExample(TLeaderExample example) throws SQLException;

	int deleteByPrimaryKey(Long id) throws SQLException;

	Long insert(TLeader record) throws SQLException;

	Long insertSelective(TLeader record) throws SQLException;

	List selectByExample(TLeaderExample example) throws SQLException;

	TLeader selectByPrimaryKey(Long id) throws SQLException;

	int updateByExampleSelective(TLeader record, TLeaderExample example) throws SQLException;

	int updateByExample(TLeader record, TLeaderExample example) throws SQLException;

	int updateByPrimaryKeySelective(TLeader record) throws SQLException;

	int updateByPrimaryKey(TLeader record) throws SQLException;

	void updateLeaderWithZizhi(TLeader tLeader, List<TLeaderImage> imageList) throws SQLException;

	TLeader selectLeaderByMemberId(Long id) throws SQLException;

	Long savetLeaderAndMember(TMember record,TLeader leader,TMemberBaseMessageExt base) throws SQLException;

	Long insertSelectiveLeaderAndGoods(TLeader record) throws SQLException;
}
