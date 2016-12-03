package com.rc.portal.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.THealthyPlanCombination;
import com.rc.portal.vo.THealthyPlanCombinationExample;

public interface THealthyPlanCombinationDAO {
    int countByExample(THealthyPlanCombinationExample example) throws SQLException;

    int deleteByExample(THealthyPlanCombinationExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(THealthyPlanCombination record) throws SQLException;

    Long insertSelective(THealthyPlanCombination record) throws SQLException;

    List selectByExample(THealthyPlanCombinationExample example) throws SQLException;

    THealthyPlanCombination selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(THealthyPlanCombination record, THealthyPlanCombinationExample example) throws SQLException;

    int updateByExample(THealthyPlanCombination record, THealthyPlanCombinationExample example) throws SQLException;

    int updateByPrimaryKeySelective(THealthyPlanCombination record) throws SQLException;

    int updateByPrimaryKey(THealthyPlanCombination record) throws SQLException;


}
