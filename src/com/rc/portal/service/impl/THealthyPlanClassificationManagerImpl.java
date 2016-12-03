package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.THealthyPlanClassificationDAO;
import com.rc.portal.service.THealthyPlanClassificationManager;
import com.rc.portal.vo.THealthyPlanClassification;
import com.rc.portal.vo.THealthyPlanClassificationExample;

public class THealthyPlanClassificationManagerImpl  implements THealthyPlanClassificationManager {

    private THealthyPlanClassificationDAO thealthyplanclassificationdao;

    public THealthyPlanClassificationManagerImpl() {
        super();
    }
    public void  setThealthyplanclassificationdao(THealthyPlanClassificationDAO thealthyplanclassificationdao){
        this.thealthyplanclassificationdao=thealthyplanclassificationdao;
    }
    public THealthyPlanClassificationDAO getThealthyplanclassificationdao(){
        return this.thealthyplanclassificationdao;
    }
    public     int countByExample(THealthyPlanClassificationExample example) throws SQLException{
        return thealthyplanclassificationdao. countByExample( example);
    }

    public     int deleteByExample(THealthyPlanClassificationExample example) throws SQLException{
        return thealthyplanclassificationdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return thealthyplanclassificationdao. deleteByPrimaryKey( id);
    }

    public     Long insert(THealthyPlanClassification record) throws SQLException{
        return thealthyplanclassificationdao. insert( record);
    }

    public     Long insertSelective(THealthyPlanClassification record) throws SQLException{
        return thealthyplanclassificationdao. insertSelective( record);
    }

    public     List selectByExample(THealthyPlanClassificationExample example) throws SQLException{
        return thealthyplanclassificationdao. selectByExample( example);
    }

    public     THealthyPlanClassification selectByPrimaryKey(Long id) throws SQLException{
        return thealthyplanclassificationdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(THealthyPlanClassification record, THealthyPlanClassificationExample example) throws SQLException{
        return thealthyplanclassificationdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(THealthyPlanClassification record, THealthyPlanClassificationExample example) throws SQLException{
        return thealthyplanclassificationdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(THealthyPlanClassification record) throws SQLException{
        return thealthyplanclassificationdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(THealthyPlanClassification record) throws SQLException{
        return thealthyplanclassificationdao. updateByPrimaryKey( record);
    }


}
