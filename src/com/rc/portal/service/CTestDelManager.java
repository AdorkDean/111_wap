package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.CTestDel;
import com.rc.portal.vo.CTestDelExample;

public interface CTestDelManager {
    int countByExample(CTestDelExample example) throws SQLException;

    int deleteByExample(CTestDelExample example) throws SQLException;

    int deleteByPrimaryKey(Integer id) throws SQLException;

    Long insert(CTestDel record) throws SQLException;

    Long insertSelective(CTestDel record) throws SQLException;

    List selectByExample(CTestDelExample example) throws SQLException;

    CTestDel selectByPrimaryKey(Integer id) throws SQLException;

    int updateByExampleSelective(CTestDel record, CTestDelExample example) throws SQLException;

    int updateByExample(CTestDel record, CTestDelExample example) throws SQLException;

    int updateByPrimaryKeySelective(CTestDel record) throws SQLException;

    int updateByPrimaryKey(CTestDel record) throws SQLException;



}
