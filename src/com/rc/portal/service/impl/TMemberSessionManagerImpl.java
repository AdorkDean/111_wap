package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TMemberSessionDAO;
import com.rc.portal.service.TMemberSessionManager;
import com.rc.portal.vo.TMemberSession;
import com.rc.portal.vo.TMemberSessionExample;

public class TMemberSessionManagerImpl  implements TMemberSessionManager {

    private TMemberSessionDAO tmembersessiondao;

    public TMemberSessionManagerImpl() {
        super();
    }
    public void  setTmembersessiondao(TMemberSessionDAO tmembersessiondao){
        this.tmembersessiondao=tmembersessiondao;
    }
    public TMemberSessionDAO getTmembersessiondao(){
        return this.tmembersessiondao;
    }
    public     int countByExample(TMemberSessionExample example) throws SQLException{
        return tmembersessiondao. countByExample( example);
    }

    public     int deleteByExample(TMemberSessionExample example) throws SQLException{
        return tmembersessiondao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tmembersessiondao. deleteByPrimaryKey( id);
    }

    public     Long insert(TMemberSession record) throws SQLException{
        return tmembersessiondao. insert( record);
    }

    public     Long insertSelective(TMemberSession record) throws SQLException{
        return tmembersessiondao. insertSelective( record);
    }

    public     List selectByExample(TMemberSessionExample example) throws SQLException{
        return tmembersessiondao. selectByExample( example);
    }

    public     TMemberSession selectByPrimaryKey(Long id) throws SQLException{
        return tmembersessiondao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TMemberSession record, TMemberSessionExample example) throws SQLException{
        return tmembersessiondao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TMemberSession record, TMemberSessionExample example) throws SQLException{
        return tmembersessiondao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TMemberSession record) throws SQLException{
        return tmembersessiondao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TMemberSession record) throws SQLException{
        return tmembersessiondao. updateByPrimaryKey( record);
    }


}
