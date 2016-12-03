package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TMemberReceiverLatLonDAO;
import com.rc.portal.service.TMemberReceiverLatLonManager;
import com.rc.portal.vo.TMemberReceiverLatLon;
import com.rc.portal.vo.TMemberReceiverLatLonExample;

public class TMemberReceiverLatLonManagerImpl  implements TMemberReceiverLatLonManager {

    private TMemberReceiverLatLonDAO tmemberreceiverlatlondao;

    public TMemberReceiverLatLonManagerImpl() {
        super();
    }
    public void  setTmemberreceiverlatlondao(TMemberReceiverLatLonDAO tmemberreceiverlatlondao){
        this.tmemberreceiverlatlondao=tmemberreceiverlatlondao;
    }
    public TMemberReceiverLatLonDAO getTmemberreceiverlatlondao(){
        return this.tmemberreceiverlatlondao;
    }
    public     int countByExample(TMemberReceiverLatLonExample example) throws SQLException{
        return tmemberreceiverlatlondao. countByExample( example);
    }

    public     int deleteByExample(TMemberReceiverLatLonExample example) throws SQLException{
        return tmemberreceiverlatlondao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tmemberreceiverlatlondao. deleteByPrimaryKey( id);
    }

    public     Long insert(TMemberReceiverLatLon record) throws SQLException{
        return tmemberreceiverlatlondao. insert( record);
    }

    public     Long insertSelective(TMemberReceiverLatLon record) throws SQLException{
        return tmemberreceiverlatlondao. insertSelective( record);
    }

    public     List selectByExample(TMemberReceiverLatLonExample example) throws SQLException{
        return tmemberreceiverlatlondao. selectByExample( example);
    }

    public     TMemberReceiverLatLon selectByPrimaryKey(Long id) throws SQLException{
        return tmemberreceiverlatlondao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TMemberReceiverLatLon record, TMemberReceiverLatLonExample example) throws SQLException{
        return tmemberreceiverlatlondao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TMemberReceiverLatLon record, TMemberReceiverLatLonExample example) throws SQLException{
        return tmemberreceiverlatlondao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TMemberReceiverLatLon record) throws SQLException{
        return tmemberreceiverlatlondao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TMemberReceiverLatLon record) throws SQLException{
        return tmemberreceiverlatlondao. updateByPrimaryKey( record);
    }


}
