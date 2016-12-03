package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.CTestDelDAO;
import com.rc.portal.service.CTestDelManager;
import com.rc.portal.vo.CTestDel;
import com.rc.portal.vo.CTestDelExample;

public class CTestDelManagerImpl  implements CTestDelManager {

    private CTestDelDAO ctestdeldao;

    public CTestDelManagerImpl() {
        super();
    }
    public void  setCtestdeldao(CTestDelDAO ctestdeldao){
        this.ctestdeldao=ctestdeldao;
    }
    public CTestDelDAO getCtestdeldao(){
        return this.ctestdeldao;
    }
    public     int countByExample(CTestDelExample example) throws SQLException{
        return ctestdeldao. countByExample( example);
    }

    public     int deleteByExample(CTestDelExample example) throws SQLException{
        return ctestdeldao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Integer id) throws SQLException{
        return ctestdeldao. deleteByPrimaryKey( id);
    }

    public     Long insert(CTestDel record) throws SQLException{
        return ctestdeldao. insert( record);
    }

    public     Long insertSelective(CTestDel record) throws SQLException{
        return ctestdeldao. insertSelective( record);
    }

    public     List selectByExample(CTestDelExample example) throws SQLException{
        return ctestdeldao. selectByExample( example);
    }

    public     CTestDel selectByPrimaryKey(Integer id) throws SQLException{
        return ctestdeldao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(CTestDel record, CTestDelExample example) throws SQLException{
        return ctestdeldao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(CTestDel record, CTestDelExample example) throws SQLException{
        return ctestdeldao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(CTestDel record) throws SQLException{
        return ctestdeldao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(CTestDel record) throws SQLException{
        return ctestdeldao. updateByPrimaryKey( record);
    }


}
