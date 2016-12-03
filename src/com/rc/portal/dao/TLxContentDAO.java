package com.rc.portal.dao;


import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TLxContent;
import com.rc.portal.vo.TLxContentExample;

public interface TLxContentDAO {
    int countByExample(TLxContentExample example) throws SQLException;

    int deleteByExample(TLxContentExample example) throws SQLException;

    int deleteByPrimaryKey(Integer id) throws SQLException;

    Long insert(TLxContent record) throws SQLException;

    Integer insertSelective(TLxContent record) throws SQLException;

    List selectByExample(TLxContentExample example) throws SQLException;

    TLxContent selectByPrimaryKey(Integer id) throws SQLException;

    int updateByExampleSelective(TLxContent record, TLxContentExample example) throws SQLException;

    int updateByExample(TLxContent record, TLxContentExample example) throws SQLException;

    int updateByPrimaryKeySelective(TLxContent record) throws SQLException;

    int updateByPrimaryKey(TLxContent record) throws SQLException;


}
