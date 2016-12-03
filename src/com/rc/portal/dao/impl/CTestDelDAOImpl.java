package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.CTestDelDAO;
import com.rc.portal.vo.CTestDel;
import com.rc.portal.vo.CTestDelExample;

public class CTestDelDAOImpl implements CTestDelDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public CTestDelDAOImpl() {
        super();
    }

    public CTestDelDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(CTestDelExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("c_test_del.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(CTestDelExample example) throws SQLException {
        int rows = sqlMapClient.delete("c_test_del.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Integer id) throws SQLException {
        CTestDel key = new CTestDel();
        key.setId(id);
        int rows = sqlMapClient.delete("c_test_del.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(CTestDel record) throws SQLException {
		return (Long)        sqlMapClient.insert("c_test_del.ibatorgenerated_insert", record);
    }

    public Long insertSelective(CTestDel record) throws SQLException {
		return (Long)        sqlMapClient.insert("c_test_del.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(CTestDelExample example) throws SQLException {
        List list = sqlMapClient.queryForList("c_test_del.ibatorgenerated_selectByExample", example);
        return list;
    }

    public CTestDel selectByPrimaryKey(Integer id) throws SQLException {
        CTestDel key = new CTestDel();
        key.setId(id);
        CTestDel record = (CTestDel) sqlMapClient.queryForObject("c_test_del.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(CTestDel record, CTestDelExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("c_test_del.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(CTestDel record, CTestDelExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("c_test_del.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(CTestDel record) throws SQLException {
        int rows = sqlMapClient.update("c_test_del.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(CTestDel record) throws SQLException {
        int rows = sqlMapClient.update("c_test_del.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends CTestDelExample {
        private Object record;

        public UpdateByExampleParms(Object record, CTestDelExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(CTestDelExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("c_test_del.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
