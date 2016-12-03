package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.THealthyPlanCombinationDAO;
import com.rc.portal.service.THealthyPlanCombinationManager;
import com.rc.portal.vo.THealthyPlanCombination;
import com.rc.portal.vo.THealthyPlanCombinationExample;

public class THealthyPlanCombinationManagerImpl  implements THealthyPlanCombinationManager {

    private THealthyPlanCombinationDAO thealthyplancombinationdao;

    public THealthyPlanCombinationManagerImpl() {
        super();
    }
    public void  setThealthyplancombinationdao(THealthyPlanCombinationDAO thealthyplancombinationdao){
        this.thealthyplancombinationdao=thealthyplancombinationdao;
    }
    public THealthyPlanCombinationDAO getThealthyplancombinationdao(){
        return this.thealthyplancombinationdao;
    }
    public     int countByExample(THealthyPlanCombinationExample example) throws SQLException{
        return thealthyplancombinationdao. countByExample( example);
    }

    public     int deleteByExample(THealthyPlanCombinationExample example) throws SQLException{
        return thealthyplancombinationdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return thealthyplancombinationdao. deleteByPrimaryKey( id);
    }

    public     Long insert(THealthyPlanCombination record) throws SQLException{
        return thealthyplancombinationdao. insert( record);
    }

    public     Long insertSelective(THealthyPlanCombination record) throws SQLException{
        return thealthyplancombinationdao. insertSelective( record);
    }

    public     List selectByExample(THealthyPlanCombinationExample example) throws SQLException{
        return thealthyplancombinationdao. selectByExample( example);
    }

    public     THealthyPlanCombination selectByPrimaryKey(Long id) throws SQLException{
        return thealthyplancombinationdao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(THealthyPlanCombination record, THealthyPlanCombinationExample example) throws SQLException{
        return thealthyplancombinationdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(THealthyPlanCombination record, THealthyPlanCombinationExample example) throws SQLException{
        return thealthyplancombinationdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(THealthyPlanCombination record) throws SQLException{
        return thealthyplancombinationdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(THealthyPlanCombination record) throws SQLException{
        return thealthyplancombinationdao. updateByPrimaryKey( record);
    }


}
