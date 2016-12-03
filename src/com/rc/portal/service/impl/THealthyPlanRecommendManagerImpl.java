package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.THealthyPlanRecommendDAO;
import com.rc.portal.service.THealthyPlanRecommendManager;
import com.rc.portal.vo.THealthyPlanRecommend;
import com.rc.portal.vo.THealthyPlanRecommendExample;

public class THealthyPlanRecommendManagerImpl  implements THealthyPlanRecommendManager {

    private THealthyPlanRecommendDAO thealthyplanrecommenddao;

    public THealthyPlanRecommendManagerImpl() {
        super();
    }
    public void  setThealthyplanrecommenddao(THealthyPlanRecommendDAO thealthyplanrecommenddao){
        this.thealthyplanrecommenddao=thealthyplanrecommenddao;
    }
    public THealthyPlanRecommendDAO getThealthyplanrecommenddao(){
        return this.thealthyplanrecommenddao;
    }
    public     int countByExample(THealthyPlanRecommendExample example) throws SQLException{
        return thealthyplanrecommenddao. countByExample( example);
    }

    public     int deleteByExample(THealthyPlanRecommendExample example) throws SQLException{
        return thealthyplanrecommenddao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return thealthyplanrecommenddao. deleteByPrimaryKey( id);
    }

    public     Long insert(THealthyPlanRecommend record) throws SQLException{
        return thealthyplanrecommenddao. insert( record);
    }

    public     Long insertSelective(THealthyPlanRecommend record) throws SQLException{
        return thealthyplanrecommenddao. insertSelective( record);
    }

    public     List selectByExample(THealthyPlanRecommendExample example) throws SQLException{
        return thealthyplanrecommenddao. selectByExample( example);
    }

    public     THealthyPlanRecommend selectByPrimaryKey(Long id) throws SQLException{
        return thealthyplanrecommenddao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(THealthyPlanRecommend record, THealthyPlanRecommendExample example) throws SQLException{
        return thealthyplanrecommenddao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(THealthyPlanRecommend record, THealthyPlanRecommendExample example) throws SQLException{
        return thealthyplanrecommenddao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(THealthyPlanRecommend record) throws SQLException{
        return thealthyplanrecommenddao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(THealthyPlanRecommend record) throws SQLException{
        return thealthyplanrecommenddao. updateByPrimaryKey( record);
    }


}
