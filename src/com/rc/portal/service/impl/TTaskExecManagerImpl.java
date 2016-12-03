package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TTaskExecDAO;
import com.rc.portal.service.TTaskExecManager;
import com.rc.portal.vo.TTaskExec;
import com.rc.portal.vo.TTaskExecExample;

public class TTaskExecManagerImpl  implements TTaskExecManager {

    private TTaskExecDAO ttaskexecdao;

    public TTaskExecManagerImpl() {
        super();
    }
    public void  setTtaskexecdao(TTaskExecDAO ttaskexecdao){
        this.ttaskexecdao=ttaskexecdao;
    }
    public TTaskExecDAO getTtaskexecdao(){
        return this.ttaskexecdao;
    }
    public     int countByExample(TTaskExecExample example) throws SQLException{
        return ttaskexecdao. countByExample( example);
    }

    public     int deleteByExample(TTaskExecExample example) throws SQLException{
        return ttaskexecdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(String taskid) throws SQLException{
        return ttaskexecdao. deleteByPrimaryKey( taskid);
    }

    public     Long insert(TTaskExec record) throws SQLException{
        return ttaskexecdao. insert( record);
    }

    public     Long insertSelective(TTaskExec record) throws SQLException{
        return ttaskexecdao. insertSelective( record);
    }

    public     List selectByExample(TTaskExecExample example) throws SQLException{
        return ttaskexecdao. selectByExample( example);
    }

    public     TTaskExec selectByPrimaryKey(String taskid) throws SQLException{
        return ttaskexecdao. selectByPrimaryKey( taskid);
    }

    public     int updateByExampleSelective(TTaskExec record, TTaskExecExample example) throws SQLException{
        return ttaskexecdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TTaskExec record, TTaskExecExample example) throws SQLException{
        return ttaskexecdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TTaskExec record) throws SQLException{
        return ttaskexecdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TTaskExec record) throws SQLException{
        return ttaskexecdao. updateByPrimaryKey( record);
    }


}
