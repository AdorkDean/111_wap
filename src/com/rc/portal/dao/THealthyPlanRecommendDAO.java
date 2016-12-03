package com.rc.portal.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.THealthyPlanRecommend;
import com.rc.portal.vo.THealthyPlanRecommendExample;

public interface THealthyPlanRecommendDAO {
    int countByExample(THealthyPlanRecommendExample example) throws SQLException;

    int deleteByExample(THealthyPlanRecommendExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(THealthyPlanRecommend record) throws SQLException;

    Long insertSelective(THealthyPlanRecommend record) throws SQLException;

    List selectByExample(THealthyPlanRecommendExample example) throws SQLException;

    THealthyPlanRecommend selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(THealthyPlanRecommend record, THealthyPlanRecommendExample example) throws SQLException;

    int updateByExample(THealthyPlanRecommend record, THealthyPlanRecommendExample example) throws SQLException;

    int updateByPrimaryKeySelective(THealthyPlanRecommend record) throws SQLException;

    int updateByPrimaryKey(THealthyPlanRecommend record) throws SQLException;


}
