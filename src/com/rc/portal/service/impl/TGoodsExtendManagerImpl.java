package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TGoodsExtendDAO;
import com.rc.portal.service.TGoodsExtendManager;
import com.rc.portal.vo.TGoodsExtend;
import com.rc.portal.vo.TGoodsExtendExample;
import com.rc.portal.vo.TGoodsExtendWithBLOBs;

public class TGoodsExtendManagerImpl  implements TGoodsExtendManager {

    private TGoodsExtendDAO tgoodsextenddao;

    public TGoodsExtendManagerImpl() {
        super();
    }
    public void  setTgoodsextenddao(TGoodsExtendDAO tgoodsextenddao){
        this.tgoodsextenddao=tgoodsextenddao;
    }
    public TGoodsExtendDAO getTgoodsextenddao(){
        return this.tgoodsextenddao;
    }
    public     int countByExample(TGoodsExtendExample example) throws SQLException{
        return tgoodsextenddao. countByExample( example);
    }

    public     int deleteByExample(TGoodsExtendExample example) throws SQLException{
        return tgoodsextenddao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long goodsid) throws SQLException{
        return tgoodsextenddao. deleteByPrimaryKey( goodsid);
    }

    public     Long insert(TGoodsExtendWithBLOBs record) throws SQLException{
        return tgoodsextenddao. insert( record);
    }

    public     Long insertSelective(TGoodsExtendWithBLOBs record) throws SQLException{
        return tgoodsextenddao. insertSelective( record);
    }

    public     List selectByExampleWithBLOBs(TGoodsExtendExample example) throws SQLException{
        return tgoodsextenddao. selectByExampleWithBLOBs( example);
    }

    public     List selectByExampleWithoutBLOBs(TGoodsExtendExample example) throws SQLException{
        return tgoodsextenddao. selectByExampleWithoutBLOBs( example);
    }

    public     TGoodsExtendWithBLOBs selectByPrimaryKey(Long goodsid) throws SQLException{
        return tgoodsextenddao. selectByPrimaryKey( goodsid);
    }

    public     int updateByExampleSelective(TGoodsExtendWithBLOBs record, TGoodsExtendExample example) throws SQLException{
        return tgoodsextenddao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TGoodsExtendWithBLOBs record, TGoodsExtendExample example) throws SQLException{
        return tgoodsextenddao. updateByExample( record, example);
    }

    public     int updateByExample(TGoodsExtend record, TGoodsExtendExample example) throws SQLException{
        return tgoodsextenddao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TGoodsExtendWithBLOBs record) throws SQLException{
        return tgoodsextenddao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TGoodsExtendWithBLOBs record) throws SQLException{
        return tgoodsextenddao. updateByPrimaryKey( record);
    }

    public     int updateByPrimaryKey(TGoodsExtend record) throws SQLException{
        return tgoodsextenddao. updateByPrimaryKey( record);
    }


}
