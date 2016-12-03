package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TShortBuyDAO;
import com.rc.portal.service.TShortBuyManager;
import com.rc.portal.vo.TShortBuy;
import com.rc.portal.vo.TShortBuyExample;

public class TShortBuyManagerImpl  implements TShortBuyManager {

    private TShortBuyDAO tshortbuydao;

    public TShortBuyManagerImpl() {
        super();
    }
    public void  setTshortbuydao(TShortBuyDAO tshortbuydao){
        this.tshortbuydao=tshortbuydao;
    }
    public TShortBuyDAO getTshortbuydao(){
        return this.tshortbuydao;
    }
    public     int countByExample(TShortBuyExample example) throws SQLException{
        return tshortbuydao. countByExample( example);
    }

    public     int deleteByExample(TShortBuyExample example) throws SQLException{
        return tshortbuydao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tshortbuydao. deleteByPrimaryKey( id);
    }

    public     Long insert(TShortBuy record) throws SQLException{
        return tshortbuydao. insert( record);
    }

    public     Long insertSelective(TShortBuy record) throws SQLException{
        return tshortbuydao. insertSelective( record);
    }

    public     List selectByExample(TShortBuyExample example) throws SQLException{
        return tshortbuydao. selectByExample( example);
    }

    public     TShortBuy selectByPrimaryKey(Long id) throws SQLException{
        return tshortbuydao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TShortBuy record, TShortBuyExample example) throws SQLException{
        return tshortbuydao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TShortBuy record, TShortBuyExample example) throws SQLException{
        return tshortbuydao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TShortBuy record) throws SQLException{
        return tshortbuydao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TShortBuy record) throws SQLException{
        return tshortbuydao. updateByPrimaryKey( record);
    }


}
