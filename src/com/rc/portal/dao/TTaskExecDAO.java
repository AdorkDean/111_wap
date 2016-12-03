package com.rc.portal.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TTaskExec;
import com.rc.portal.vo.TTaskExecExample;

public interface TTaskExecDAO {
    int countByExample(TTaskExecExample example) throws SQLException;

    int deleteByExample(TTaskExecExample example) throws SQLException;

    int deleteByPrimaryKey(String taskid) throws SQLException;

    Long insert(TTaskExec record) throws SQLException;

    Long insertSelective(TTaskExec record) throws SQLException;

    List selectByExample(TTaskExecExample example) throws SQLException;

    TTaskExec selectByPrimaryKey(String taskid) throws SQLException;

    int updateByExampleSelective(TTaskExec record, TTaskExecExample example) throws SQLException;

    int updateByExample(TTaskExec record, TTaskExecExample example) throws SQLException;

    int updateByPrimaryKeySelective(TTaskExec record) throws SQLException;

    int updateByPrimaryKey(TTaskExec record) throws SQLException;


}
