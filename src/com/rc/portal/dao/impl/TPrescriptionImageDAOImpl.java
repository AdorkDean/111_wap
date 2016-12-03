package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TPrescriptionImageDAO;
import com.rc.portal.vo.TPrescriptionImage;
import com.rc.portal.vo.TPrescriptionImageExample;

public class TPrescriptionImageDAOImpl implements TPrescriptionImageDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TPrescriptionImageDAOImpl() {
        super();
    }

    public TPrescriptionImageDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TPrescriptionImageExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_prescription_image.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TPrescriptionImageExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_prescription_image.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TPrescriptionImage key = new TPrescriptionImage();
        key.setId(id);
        int rows = sqlMapClient.delete("t_prescription_image.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TPrescriptionImage record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_prescription_image.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TPrescriptionImage record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_prescription_image.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TPrescriptionImageExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_prescription_image.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TPrescriptionImage selectByPrimaryKey(Long id) throws SQLException {
        TPrescriptionImage key = new TPrescriptionImage();
        key.setId(id);
        TPrescriptionImage record = (TPrescriptionImage) sqlMapClient.queryForObject("t_prescription_image.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TPrescriptionImage record, TPrescriptionImageExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_prescription_image.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TPrescriptionImage record, TPrescriptionImageExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_prescription_image.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TPrescriptionImage record) throws SQLException {
        int rows = sqlMapClient.update("t_prescription_image.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TPrescriptionImage record) throws SQLException {
        int rows = sqlMapClient.update("t_prescription_image.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TPrescriptionImageExample {
        private Object record;

        public UpdateByExampleParms(Object record, TPrescriptionImageExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TPrescriptionImageExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_prescription_image.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
