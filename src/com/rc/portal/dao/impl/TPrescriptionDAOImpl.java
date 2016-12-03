package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TPrescriptionDAO;
import com.rc.portal.vo.TPrescription;
import com.rc.portal.vo.TPrescriptionExample;

public class TPrescriptionDAOImpl implements TPrescriptionDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TPrescriptionDAOImpl() {
        super();
    }

    public TPrescriptionDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TPrescriptionExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_prescription.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TPrescriptionExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_prescription.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TPrescription key = new TPrescription();
        key.setId(id);
        int rows = sqlMapClient.delete("t_prescription.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TPrescription record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_prescription.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TPrescription record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_prescription.ibatorgenerated_insertSelective", record);
    }

    public List selectByExampleWithBLOBs(TPrescriptionExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_prescription.ibatorgenerated_selectByExampleWithBLOBs", example);
        return list;
    }

    public List selectByExampleWithoutBLOBs(TPrescriptionExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_prescription.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TPrescription selectByPrimaryKey(Long id) throws SQLException {
        TPrescription key = new TPrescription();
        key.setId(id);
        TPrescription record = (TPrescription) sqlMapClient.queryForObject("t_prescription.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TPrescription record, TPrescriptionExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_prescription.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExampleWithBLOBs(TPrescription record, TPrescriptionExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_prescription.ibatorgenerated_updateByExampleWithBLOBs", parms);
        return rows;
    }

    public int updateByExampleWithoutBLOBs(TPrescription record, TPrescriptionExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_prescription.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TPrescription record) throws SQLException {
        int rows = sqlMapClient.update("t_prescription.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKeyWithBLOBs(TPrescription record) throws SQLException {
        int rows = sqlMapClient.update("t_prescription.ibatorgenerated_updateByPrimaryKeyWithBLOBs", record);
        return rows;
    }

    public int updateByPrimaryKeyWithoutBLOBs(TPrescription record) throws SQLException {
        int rows = sqlMapClient.update("t_prescription.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TPrescriptionExample {
        private Object record;

        public UpdateByExampleParms(Object record, TPrescriptionExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TPrescriptionExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_prescription.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
