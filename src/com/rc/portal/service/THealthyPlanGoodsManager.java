package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.THealthyPlanGoods;
import com.rc.portal.vo.THealthyPlanGoodsExample;

public interface THealthyPlanGoodsManager {
    int countByExample(THealthyPlanGoodsExample example) throws SQLException;

    int deleteByExample(THealthyPlanGoodsExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(THealthyPlanGoods record) throws SQLException;

    Long insertSelective(THealthyPlanGoods record) throws SQLException;

    List selectByExample(THealthyPlanGoodsExample example) throws SQLException;

    THealthyPlanGoods selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(THealthyPlanGoods record, THealthyPlanGoodsExample example) throws SQLException;

    int updateByExample(THealthyPlanGoods record, THealthyPlanGoodsExample example) throws SQLException;

    int updateByPrimaryKeySelective(THealthyPlanGoods record) throws SQLException;

    int updateByPrimaryKey(THealthyPlanGoods record) throws SQLException;



}
