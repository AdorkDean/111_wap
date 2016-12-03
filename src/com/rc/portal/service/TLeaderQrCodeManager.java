package com.rc.portal.service;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.rc.portal.vo.TLeader;
import com.rc.portal.vo.TLeaderQrCode;
import com.rc.portal.vo.TLeaderQrCodeExample;
import com.rc.portal.vo.TMember;

public interface TLeaderQrCodeManager {
    int countByExample(TLeaderQrCodeExample example) throws SQLException;

    int deleteByExample(TLeaderQrCodeExample example) throws SQLException;

    int deleteByPrimaryKey(Long id) throws SQLException;

    Long insert(TLeaderQrCode record) throws SQLException;

    Long insertSelective(TLeaderQrCode record) throws SQLException;

    List selectByExample(TLeaderQrCodeExample example) throws SQLException;

    TLeaderQrCode selectByPrimaryKey(Long id) throws SQLException;

    int updateByExampleSelective(TLeaderQrCode record, TLeaderQrCodeExample example) throws SQLException;

    int updateByExample(TLeaderQrCode record, TLeaderQrCodeExample example) throws SQLException;

    int updateByPrimaryKeySelective(TLeaderQrCode record) throws SQLException;

    int updateByPrimaryKey(TLeaderQrCode record) throws SQLException;

    /**
     * 生成二维码(药房海报),药房海报
     * @param tMember
     * @param tLeader
     * @throws IOException 
     * @throws SQLException 
     */
	Long createPharmacyPoster(TMember tMember, TLeader tLeader,String qianzhui) throws IOException, SQLException;

	/**
	 * 生成领袖海报,仅局限于领袖海报
	 * @param tMember
	 * @param tLeader
	 * @param qianzhui
	 * @return
	 * @throws FileNotFoundException 
	 * @throws IOException 
	 * @throws SQLException 
	 */
	Long createLeaderPoster(TMember tMember, TLeader tLeader, String qianzhui) throws FileNotFoundException, IOException, SQLException;



}
