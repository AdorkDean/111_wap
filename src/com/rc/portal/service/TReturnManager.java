package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TReturn;
import com.rc.portal.vo.TReturnExample;
import com.rc.portal.vo.TReturnImage;
import com.rc.portal.vo.TReturnItem;

public interface TReturnManager {
    int countByExample(TReturnExample example) throws SQLException;

    int deleteByExample(TReturnExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TReturn record) throws SQLException;

    Long insertSelective(TReturn record) throws SQLException;

    List selectByExample(TReturnExample example) throws SQLException;

    TReturn selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TReturn record, TReturnExample example) throws SQLException;

    int updateByExample(TReturn record, TReturnExample example) throws SQLException;

    int updateByPrimaryKeySelective(TReturn record) throws SQLException;

    int updateByPrimaryKey(TReturn record) throws SQLException;

    int insert(TReturn record,TReturnItem returnItem,List<TReturnImage> returnImages) throws SQLException;

}
