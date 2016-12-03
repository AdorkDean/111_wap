package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TMemberSessionDAO;
import com.rc.portal.vo.TMemberSession;
import com.rc.portal.vo.TMemberSessionExample;

public class TMemberSessionDAOImpl implements TMemberSessionDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TMemberSessionDAOImpl() {
        super();
    }

    public TMemberSessionDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TMemberSessionExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_member_session.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TMemberSessionExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_member_session.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        TMemberSession key = new TMemberSession();
        key.setId(id);
        int rows = sqlMapClient.delete("t_member_session.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TMemberSession record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_member_session.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TMemberSession record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_member_session.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TMemberSessionExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_member_session.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TMemberSession selectByPrimaryKey(Long id) throws SQLException {
        TMemberSession key = new TMemberSession();
        key.setId(id);
        TMemberSession record = (TMemberSession) sqlMapClient.queryForObject("t_member_session.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TMemberSession record, TMemberSessionExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_member_session.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TMemberSession record, TMemberSessionExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_member_session.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TMemberSession record) throws SQLException {
        int rows = sqlMapClient.update("t_member_session.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TMemberSession record) throws SQLException {
        int rows = sqlMapClient.update("t_member_session.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TMemberSessionExample {
        private Object record;

        public UpdateByExampleParms(Object record, TMemberSessionExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TMemberSessionExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_member_session.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
