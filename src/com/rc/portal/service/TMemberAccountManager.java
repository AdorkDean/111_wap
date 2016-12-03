package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TMemberAccount;
import com.rc.portal.vo.TMemberAccountExample;

public interface TMemberAccountManager {
    int countByExample(TMemberAccountExample example) throws SQLException;

    int deleteByExample(TMemberAccountExample example) throws SQLException;

    int deleteByPrimaryKey(Long memberId) throws SQLException;

    Long insert(TMemberAccount record) throws SQLException;

    Long insertSelective(TMemberAccount record) throws SQLException;

    List selectByExample(TMemberAccountExample example) throws SQLException;

    TMemberAccount selectByPrimaryKey(Long memberId) throws SQLException;

    int updateByExampleSelective(TMemberAccount record, TMemberAccountExample example) throws SQLException;

    int updateByExample(TMemberAccount record, TMemberAccountExample example) throws SQLException;

    int updateByPrimaryKeySelective(TMemberAccount record) throws SQLException;

    int updateByPrimaryKey(TMemberAccount record) throws SQLException;

	void updateLeaderMemberAccount(Double waitingAmount, Long memberId) throws SQLException;



}