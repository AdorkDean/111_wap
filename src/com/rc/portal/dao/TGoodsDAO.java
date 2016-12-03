package com.rc.portal.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TGoods;
import com.rc.portal.vo.TGoodsExample;

public interface TGoodsDAO {
    int countByExample(TGoodsExample example) throws SQLException;

    int deleteByExample(TGoodsExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TGoods record) throws SQLException;

    Long insertSelective(TGoods record) throws SQLException;

    List selectByExample(TGoodsExample example) throws SQLException;

    TGoods selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TGoods record, TGoodsExample example) throws SQLException;

    int updateByExample(TGoods record, TGoodsExample example) throws SQLException;

    int updateByPrimaryKeySelective(TGoods record) throws SQLException;

    int updateByPrimaryKey(TGoods record) throws SQLException;


}
