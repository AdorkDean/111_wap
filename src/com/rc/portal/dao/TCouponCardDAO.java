package com.rc.portal.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TCouponCard;
import com.rc.portal.vo.TCouponCardExample;

public interface TCouponCardDAO {
    int countByExample(TCouponCardExample example) throws SQLException;

    int deleteByExample(TCouponCardExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TCouponCard record) throws SQLException;

    Long insertSelective(TCouponCard record) throws SQLException;

    List selectByExample(TCouponCardExample example) throws SQLException;

    TCouponCard selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TCouponCard record, TCouponCardExample example) throws SQLException;

    int updateByExample(TCouponCard record, TCouponCardExample example) throws SQLException;

    int updateByPrimaryKeySelective(TCouponCard record) throws SQLException;

    int updateByPrimaryKey(TCouponCard record) throws SQLException;


}
