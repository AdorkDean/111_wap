package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.rc.portal.vo.THealthyPlan;
import com.rc.portal.vo.THealthyPlanExample;
import com.rc.portal.webapp.model.HealthyPlanContentModel;

public interface THealthyPlanManager {
    int countByExample(THealthyPlanExample example) throws SQLException;

    int deleteByExample(THealthyPlanExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(THealthyPlan record) throws SQLException;

    Long insertSelective(THealthyPlan record) throws SQLException;

    @SuppressWarnings("rawtypes")
	List selectByExample(THealthyPlanExample example) throws SQLException;

    THealthyPlan selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(THealthyPlan record, THealthyPlanExample example) throws SQLException;

    int updateByExample(THealthyPlan record, THealthyPlanExample example) throws SQLException;

    int updateByPrimaryKeySelective(THealthyPlan record) throws SQLException;

    int updateByPrimaryKey(THealthyPlan record) throws SQLException;

    List<HealthyPlanContentModel> getHealthyPlanContent(Map<String, Object> map, String dataType);

}
