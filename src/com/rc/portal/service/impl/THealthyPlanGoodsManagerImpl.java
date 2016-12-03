package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.THealthyPlanGoodsDAO;
import com.rc.portal.service.THealthyPlanGoodsManager;
import com.rc.portal.vo.THealthyPlanGoods;
import com.rc.portal.vo.THealthyPlanGoodsExample;

public class THealthyPlanGoodsManagerImpl  implements THealthyPlanGoodsManager {

    private THealthyPlanGoodsDAO thealthyplangoodsdao;

    public THealthyPlanGoodsManagerImpl() {
        super();
    }
    public void  setThealthyplangoodsdao(THealthyPlanGoodsDAO thealthyplangoodsdao){
        this.thealthyplangoodsdao=thealthyplangoodsdao;
    }
    public THealthyPlanGoodsDAO getThealthyplangoodsdao(){
        return this.thealthyplangoodsdao;
    }
    public     int countByExample(THealthyPlanGoodsExample example) throws SQLException{
        return thealthyplangoodsdao. countByExample( example);
    }

    public     int deleteByExample(THealthyPlanGoodsExample example) throws SQLException{
        return thealthyplangoodsdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return thealthyplangoodsdao. deleteByPrimaryKey( id);
    }

    public     Long insert(THealthyPlanGoods record) throws SQLException{
        return thealthyplangoodsdao. insert( record);
    }

    public     Long insertSelective(THealthyPlanGoods record) throws SQLException{
        return thealthyplangoodsdao. insertSelective( record);
    }

    public     List selectByExample(THealthyPlanGoodsExample example) throws SQLException{
        return thealthyplangoodsdao. selectByExample( example);
    }

    public     THealthyPlanGoods selectByPrimaryKey(Long id) throws SQLException{
        return thealthyplangoodsdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(THealthyPlanGoods record, THealthyPlanGoodsExample example) throws SQLException{
        return thealthyplangoodsdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(THealthyPlanGoods record, THealthyPlanGoodsExample example) throws SQLException{
        return thealthyplangoodsdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(THealthyPlanGoods record) throws SQLException{
        return thealthyplangoodsdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(THealthyPlanGoods record) throws SQLException{
        return thealthyplangoodsdao. updateByPrimaryKey( record);
    }


}
