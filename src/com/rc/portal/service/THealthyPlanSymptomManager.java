package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.THealthyPlanSymptom;
import com.rc.portal.vo.THealthyPlanSymptomExample;

public interface THealthyPlanSymptomManager {
    int countByExample(THealthyPlanSymptomExample example) throws SQLException;

    int deleteByExample(THealthyPlanSymptomExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(THealthyPlanSymptom record) throws SQLException;

    Long insertSelective(THealthyPlanSymptom record) throws SQLException;

    List selectByExample(THealthyPlanSymptomExample example) throws SQLException;

    THealthyPlanSymptom selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(THealthyPlanSymptom record, THealthyPlanSymptomExample example) throws SQLException;

    int updateByExample(THealthyPlanSymptom record, THealthyPlanSymptomExample example) throws SQLException;

    int updateByPrimaryKeySelective(THealthyPlanSymptom record) throws SQLException;

    int updateByPrimaryKey(THealthyPlanSymptom record) throws SQLException;



}
