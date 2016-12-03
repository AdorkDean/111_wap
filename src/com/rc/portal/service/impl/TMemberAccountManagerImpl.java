package com.rc.portal.service.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TMemberAccountDAO;
import com.rc.portal.service.TMemberAccountManager;
import com.rc.portal.vo.TMemberAccount;
import com.rc.portal.vo.TMemberAccountExample;

public class TMemberAccountManagerImpl  implements TMemberAccountManager {

    private TMemberAccountDAO tmemberaccountdao;

    public TMemberAccountManagerImpl() {
        super();
    }
    public void  setTmemberaccountdao(TMemberAccountDAO tmemberaccountdao){
        this.tmemberaccountdao=tmemberaccountdao;
    }
    public TMemberAccountDAO getTmemberaccountdao(){
        return this.tmemberaccountdao;
    }
    public     int countByExample(TMemberAccountExample example) throws SQLException{
        return tmemberaccountdao. countByExample( example);
    }

    public     int deleteByExample(TMemberAccountExample example) throws SQLException{
        return tmemberaccountdao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long memberId) throws SQLException{
        return tmemberaccountdao. deleteByPrimaryKey( memberId);
    }

    public     Long insert(TMemberAccount record) throws SQLException{
        return tmemberaccountdao. insert( record);
    }

    public     Long insertSelective(TMemberAccount record) throws SQLException{
        return tmemberaccountdao. insertSelective( record);
    }

    public     List selectByExample(TMemberAccountExample example) throws SQLException{
        return tmemberaccountdao. selectByExample( example);
    }

    public     TMemberAccount selectByPrimaryKey(Long memberId) throws SQLException{
        return tmemberaccountdao. selectByPrimaryKey( memberId);
    }

    public     int updateByExampleSelective(TMemberAccount record, TMemberAccountExample example) throws SQLException{
        return tmemberaccountdao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TMemberAccount record, TMemberAccountExample example) throws SQLException{
        return tmemberaccountdao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TMemberAccount record) throws SQLException{
        return tmemberaccountdao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TMemberAccount record) throws SQLException{
        return tmemberaccountdao. updateByPrimaryKey( record);
    }

    /**
     * 货到付款，给领袖加待入账金额
     * @param 
     */
	@Override
	public void updateLeaderMemberAccount(Double waitingAmount, Long memberId) throws SQLException {
		// tOrder→订单算金额，memberId是返现账户
		if (memberId != null && memberId > 0L) {
			TMemberAccount tMemberAccount = selectByPrimaryKey(memberId);
			if (tMemberAccount != null) {
				tMemberAccount.setWaitAmount(tMemberAccount.getWaitAmount() == null ? (new BigDecimal(waitingAmount))
						: tMemberAccount.getWaitAmount().add(new BigDecimal(waitingAmount)));
				updateByPrimaryKey(tMemberAccount);
			}
		}
	}
}
