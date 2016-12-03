package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.rc.portal.dao.TReturnDAO;
import com.rc.portal.dao.TReturnImageDAO;
import com.rc.portal.dao.TReturnItemDAO;
import com.rc.portal.service.TReturnManager;
import com.rc.portal.vo.TReturn;
import com.rc.portal.vo.TReturnExample;
import com.rc.portal.vo.TReturnImage;
import com.rc.portal.vo.TReturnItem;

public class TReturnManagerImpl  implements TReturnManager {

    private TReturnDAO treturndao;
    
    private TReturnItemDAO  treturnitemdao;
    
    private TReturnImageDAO treturnimagedao;

    public TReturnManagerImpl() {
        super();
    }
    public void  setTreturndao(TReturnDAO treturndao){
        this.treturndao=treturndao;
    }
    public TReturnDAO getTreturndao(){
        return this.treturndao;
    }
    public TReturnItemDAO getTreturnitemdao() {
		return treturnitemdao;
	}
	public void setTreturnitemdao(TReturnItemDAO treturnitemdao) {
		this.treturnitemdao = treturnitemdao;
	}
	public TReturnImageDAO getTreturnimagedao() {
		return treturnimagedao;
	}
	public void setTreturnimagedao(TReturnImageDAO treturnimagedao) {
		this.treturnimagedao = treturnimagedao;
	}
	public     int countByExample(TReturnExample example) throws SQLException{
        return treturndao. countByExample( example);
    }

    public     int deleteByExample(TReturnExample example) throws SQLException{
        return treturndao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return treturndao. deleteByPrimaryKey( id);
    }

    public     Long insert(TReturn record) throws SQLException{
        return treturndao. insert( record);
    }

    public     Long insertSelective(TReturn record) throws SQLException{
        return treturndao. insertSelective( record);
    }

    public     List selectByExample(TReturnExample example) throws SQLException{
        return treturndao. selectByExample( example);
    }

    public     TReturn selectByPrimaryKey(Long id) throws SQLException{
        return treturndao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TReturn record, TReturnExample example) throws SQLException{
        return treturndao. updateByExampleSelective( record, example);
    }

    public     int updateByExample(TReturn record, TReturnExample example) throws SQLException{
        return treturndao. updateByExample( record, example);
    }

    public     int updateByPrimaryKeySelective(TReturn record) throws SQLException{
        return treturndao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKey(TReturn record) throws SQLException{
        return treturndao. updateByPrimaryKey( record);
    }

    public int insert(TReturn tReturn,TReturnItem returnItem,List<TReturnImage> returnImages) throws SQLException{
    	Long insertSelective = treturndao.insertSelective(tReturn);
    	
    	returnItem.setReturnId(insertSelective);
    	
    	treturnitemdao.insertSelective(returnItem);
    	
    	for (TReturnImage tReturnImage : returnImages) {
    		tReturnImage.setReturnId(insertSelective);
    		treturnimagedao.insertSelective(tReturnImage);
		}
    	
    	return 1;
    }
}
