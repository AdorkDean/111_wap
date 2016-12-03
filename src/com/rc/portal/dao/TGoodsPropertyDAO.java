package com.rc.portal.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TGoodsProperty;
import com.rc.portal.vo.TGoodsPropertyExample;

public interface TGoodsPropertyDAO {
    int countByExample(TGoodsPropertyExample example) throws SQLException;

    int deleteByExample(TGoodsPropertyExample example) throws SQLException;

    int deleteByPrimaryKey(Long goodsid) throws SQLException;

    Long insert(TGoodsProperty record) throws SQLException;

    Long insertSelective(TGoodsProperty record) throws SQLException;

    List selectByExample(TGoodsPropertyExample example) throws SQLException;

    TGoodsProperty selectByPrimaryKey(Long goodsid) throws SQLException;

    int updateByExampleSelective(TGoodsProperty record, TGoodsPropertyExample example) throws SQLException;

    int updateByExample(TGoodsProperty record, TGoodsPropertyExample example) throws SQLException;

    int updateByPrimaryKeySelective(TGoodsProperty record) throws SQLException;

    int updateByPrimaryKey(TGoodsProperty record) throws SQLException;


}
