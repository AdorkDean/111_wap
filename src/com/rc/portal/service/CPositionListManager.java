package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.CPositionList;
import com.rc.portal.vo.CPositionListExample;

public interface CPositionListManager {
    int countByExample(CPositionListExample example) throws SQLException;

    int deleteByExample(CPositionListExample example) throws SQLException;

    int deleteByPrimaryKey(Integer id) throws SQLException;

    Long insert(CPositionList record) throws SQLException;

    Long insertSelective(CPositionList record) throws SQLException;

    List selectByExampleWithBLOBs(CPositionListExample example) throws SQLException;

    List selectByExampleWithoutBLOBs(CPositionListExample example) throws SQLException;

    CPositionList selectByPrimaryKey(Integer id) throws SQLException;

    int updateByExampleSelective(CPositionList record, CPositionListExample example) throws SQLException;

    int updateByExampleWithBLOBs(CPositionList record, CPositionListExample example) throws SQLException;

    int updateByExampleWithoutBLOBs(CPositionList record, CPositionListExample example) throws SQLException;

    int updateByPrimaryKeySelective(CPositionList record) throws SQLException;

    int updateByPrimaryKeyWithBLOBs(CPositionList record) throws SQLException;

    int updateByPrimaryKeyWithoutBLOBs(CPositionList record) throws SQLException;



}
