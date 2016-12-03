package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TPrescriptionImageDAO;
import com.rc.portal.service.TPrescriptionImageManager;
import com.rc.portal.vo.TPrescriptionImage;
import com.rc.portal.vo.TPrescriptionImageExample;

public class TPrescriptionImageManagerImpl  implements TPrescriptionImageManager {

    private TPrescriptionImageDAO tprescriptionimagedao;

    public TPrescriptionImageManagerImpl() {
        super();
    }
    public void  setTprescriptionimagedao(TPrescriptionImageDAO tprescriptionimagedao){
        this.tprescriptionimagedao=tprescriptionimagedao;
    }
    public TPrescriptionImageDAO getTprescriptionimagedao(){
        return this.tprescriptionimagedao;
    }
    public     int countByExample(TPrescriptionImageExample example) throws SQLException{
        return tprescriptionimagedao. countByExample( example);
    }

    public     int deleteByExample(TPrescriptionImageExample example) throws SQLException{
        return tprescriptionimagedao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tprescriptionimagedao. deleteByPrimaryKey( id);
    }

    public     Long insert(TPrescriptionImage record) throws SQLException{
        return tprescriptionimagedao. insert( record);
    }

    public     Long insertSelective(TPrescriptionImage record) throws SQLException{
        return tprescriptionimagedao. insertSelective( record);
    }

    public     List selectByExample(TPrescriptionImageExample example) throws SQLException{
        return tprescriptionimagedao. selectByExample( example);
    }

    public     TPrescriptionImage selectByPrimaryKey(Long id) throws SQLException{
        return tprescriptionimagedao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TPrescriptionImage record, TPrescriptionImageExample example) throws SQLException{
        return tprescriptionimagedao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TPrescriptionImage record, TPrescriptionImageExample example) throws SQLException{
        return tprescriptionimagedao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TPrescriptionImage record) throws SQLException{
        return tprescriptionimagedao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TPrescriptionImage record) throws SQLException{
        return tprescriptionimagedao. updateByPrimaryKey( record);
    }


}
