package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.TTaskExecDAO;
import com.rc.portal.vo.TTaskExec;
import com.rc.portal.vo.TTaskExecExample;

public class TTaskExecDAOImpl implements TTaskExecDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public TTaskExecDAOImpl() {
        super();
    }

    public TTaskExecDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(TTaskExecExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_task_exec.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(TTaskExecExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_task_exec.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(String taskid) throws SQLException {
        TTaskExec key = new TTaskExec();
        key.setTaskid(taskid);
        int rows = sqlMapClient.delete("t_task_exec.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(TTaskExec record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_task_exec.ibatorgenerated_insert", record);
    }

    public Long insertSelective(TTaskExec record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_task_exec.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(TTaskExecExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_task_exec.ibatorgenerated_selectByExample", example);
        return list;
    }

    public TTaskExec selectByPrimaryKey(String taskid) throws SQLException {
        TTaskExec key = new TTaskExec();
        key.setTaskid(taskid);
        TTaskExec record = (TTaskExec) sqlMapClient.queryForObject("t_task_exec.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(TTaskExec record, TTaskExecExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_task_exec.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(TTaskExec record, TTaskExecExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_task_exec.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(TTaskExec record) throws SQLException {
        int rows = sqlMapClient.update("t_task_exec.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(TTaskExec record) throws SQLException {
        int rows = sqlMapClient.update("t_task_exec.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends TTaskExecExample {
        private Object record;

        public UpdateByExampleParms(Object record, TTaskExecExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(TTaskExecExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_task_exec.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
