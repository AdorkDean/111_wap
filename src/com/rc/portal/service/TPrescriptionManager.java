package com.rc.portal.service;



import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TPrescription;
import com.rc.portal.vo.TPrescriptionExample;

public interface TPrescriptionManager {
    int countByExample(TPrescriptionExample example) throws SQLException;

    int deleteByExample(TPrescriptionExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TPrescription record) throws SQLException;

    Long insertSelective(TPrescription record) throws SQLException;

    List selectByExampleWithBLOBs(TPrescriptionExample example) throws SQLException;

    List selectByExampleWithoutBLOBs(TPrescriptionExample example) throws SQLException;

    TPrescription selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TPrescription record, TPrescriptionExample example) throws SQLException;

    int updateByExampleWithBLOBs(TPrescription record, TPrescriptionExample example) throws SQLException;

    int updateByExampleWithoutBLOBs(TPrescription record, TPrescriptionExample example) throws SQLException;

    int updateByPrimaryKeySelective(TPrescription record) throws SQLException;

    int updateByPrimaryKeyWithBLOBs(TPrescription record) throws SQLException;

    int updateByPrimaryKeyWithoutBLOBs(TPrescription record) throws SQLException;

	Integer insertPrescription(TPrescription tPrescription, List<String> imgList) throws Exception;

	int deletePre(Long preid) throws Exception;



}
