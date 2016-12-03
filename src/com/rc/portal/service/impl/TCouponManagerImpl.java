package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TCouponDAO;
import com.rc.portal.service.TCouponManager;
import com.rc.portal.vo.TCoupon;
import com.rc.portal.vo.TCouponExample;

public class TCouponManagerImpl  implements TCouponManager {

    private TCouponDAO tcoupondao;

    public TCouponManagerImpl() {
        super();
    }
    public void  setTcoupondao(TCouponDAO tcoupondao){
        this.tcoupondao=tcoupondao;
    }
    public TCouponDAO getTcoupondao(){
        return this.tcoupondao;
    }
    public     int countByExample(TCouponExample example) throws SQLException{
        return tcoupondao. countByExample( example);
    }

    public     int deleteByExample(TCouponExample example) throws SQLException{
        return tcoupondao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tcoupondao. deleteByPrimaryKey( id);
    }

    public     Long insert(TCoupon record) throws SQLException{
        return tcoupondao. insert( record);
    }

    public     Long insertSelective(TCoupon record) throws SQLException{
        return tcoupondao. insertSelective( record);
    }

    public     List selectByExample(TCouponExample example) throws SQLException{
        return tcoupondao. selectByExample( example);
    }

    public     TCoupon selectByPrimaryKey(Long id) throws SQLException{
        return tcoupondao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TCoupon record, TCouponExample example) throws SQLException{
        return tcoupondao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TCoupon record, TCouponExample example) throws SQLException{
        return tcoupondao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TCoupon record) throws SQLException{
        return tcoupondao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TCoupon record) throws SQLException{
        return tcoupondao. updateByPrimaryKey( record);
    }


}
