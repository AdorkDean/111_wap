package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TPrescriptionImage;
import com.rc.portal.vo.TPrescriptionImageExample;

public interface TPrescriptionImageManager {
    int countByExample(TPrescriptionImageExample example) throws SQLException;

    int deleteByExample(TPrescriptionImageExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TPrescriptionImage record) throws SQLException;

    Long insertSelective(TPrescriptionImage record) throws SQLException;

    List selectByExample(TPrescriptionImageExample example) throws SQLException;

    TPrescriptionImage selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TPrescriptionImage record, TPrescriptionImageExample example) throws SQLException;

    int updateByExample(TPrescriptionImage record, TPrescriptionImageExample example) throws SQLException;

    int updateByPrimaryKeySelective(TPrescriptionImage record) throws SQLException;

    int updateByPrimaryKey(TPrescriptionImage record) throws SQLException;



}
