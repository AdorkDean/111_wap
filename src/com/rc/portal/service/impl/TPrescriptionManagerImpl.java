package com.rc.portal.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.rc.portal.dao.TPrescriptionDAO;
import com.rc.portal.dao.TPrescriptionImageDAO;
import com.rc.portal.service.TPrescriptionManager;
import com.rc.portal.vo.TPrescription;
import com.rc.portal.vo.TPrescriptionExample;
import com.rc.portal.vo.TPrescriptionImage;
import com.rc.portal.vo.TPrescriptionImageExample;

public class TPrescriptionManagerImpl  implements TPrescriptionManager {

    private TPrescriptionDAO tprescriptiondao;
    private TPrescriptionImageDAO tprescriptionimagedao;

    public TPrescriptionManagerImpl() {
        super();
    }
    
    
    public TPrescriptionImageDAO getTprescriptionimagedao() {
		return tprescriptionimagedao;
	}


	public void setTprescriptionimagedao(TPrescriptionImageDAO tprescriptionimagedao) {
		this.tprescriptionimagedao = tprescriptionimagedao;
	}


	public void  setTprescriptiondao(TPrescriptionDAO tprescriptiondao){
        this.tprescriptiondao=tprescriptiondao;
    }
    public TPrescriptionDAO getTprescriptiondao(){
        return this.tprescriptiondao;
    }
    public     int countByExample(TPrescriptionExample example) throws SQLException{
        return tprescriptiondao. countByExample( example);
    }

    public     int deleteByExample(TPrescriptionExample example) throws SQLException{
        return tprescriptiondao. deleteByExample( example);
    }

    public     int deleteByPrimaryKey(Long id) throws SQLException{
        return tprescriptiondao. deleteByPrimaryKey( id);
    }

    public     Long insert(TPrescription record) throws SQLException{
        return tprescriptiondao. insert( record);
    }

    public     Long insertSelective(TPrescription record) throws SQLException{
        return tprescriptiondao. insertSelective( record);
    }

    public     List selectByExampleWithBLOBs(TPrescriptionExample example) throws SQLException{
        return tprescriptiondao. selectByExampleWithBLOBs( example);
    }

    public     List selectByExampleWithoutBLOBs(TPrescriptionExample example) throws SQLException{
        return tprescriptiondao. selectByExampleWithoutBLOBs( example);
    }

    public     TPrescription selectByPrimaryKey(Long id) throws SQLException{
        return tprescriptiondao. selectByPrimaryKey( id);
    }

    public     int updateByExampleSelective(TPrescription record, TPrescriptionExample example) throws SQLException{
        return tprescriptiondao. updateByExampleSelective( record, example);
    }

    public     int updateByExampleWithBLOBs(TPrescription record, TPrescriptionExample example) throws SQLException{
        return tprescriptiondao. updateByExampleWithBLOBs( record, example);
    }

    public     int updateByExampleWithoutBLOBs(TPrescription record, TPrescriptionExample example) throws SQLException{
        return tprescriptiondao. updateByExampleWithoutBLOBs( record, example);
    }

    public     int updateByPrimaryKeySelective(TPrescription record) throws SQLException{
        return tprescriptiondao. updateByPrimaryKeySelective( record);
    }

    public     int updateByPrimaryKeyWithBLOBs(TPrescription record) throws SQLException{
        return tprescriptiondao. updateByPrimaryKeyWithBLOBs( record);
    }

    public     int updateByPrimaryKeyWithoutBLOBs(TPrescription record) throws SQLException{
        return tprescriptiondao. updateByPrimaryKeyWithoutBLOBs( record);
    }
	@Override
	public Integer insertPrescription(TPrescription tPrescription,
			List<String> imgList) throws Exception {
		Integer flag=0;
		if(imgList!=null&&imgList.size()>0){
			tPrescription.setImageUrl(imgList.get(0));
			Long preid = tprescriptiondao.insertSelective(tPrescription);
			if(preid!=null&&preid!=0){
				for(String imgurl:imgList){
					TPrescriptionImage tpi=new TPrescriptionImage();
					tpi.setPrescriptionId(preid);
					tpi.setPath(imgurl);
					tpi.setCreateDate(new Date());
					tpi.setModifyDate(new Date());
					Long imageid = tprescriptionimagedao.insertSelective(tpi);
					flag=imageid.intValue();
				}
				
			}
		}else{
			Long preid = tprescriptiondao.insertSelective(tPrescription);
			flag=preid.intValue();
		}
		return flag;
	}


	@Override
	public int deletePre(Long preid) throws Exception {
		TPrescriptionImageExample tpie=new TPrescriptionImageExample();
		tpie.createCriteria().andPrescriptionIdEqualTo(preid);
		 tprescriptionimagedao.deleteByExample(tpie);
		 int flag =tprescriptiondao.deleteByPrimaryKey(preid);
		return flag;
	}


}
