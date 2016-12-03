package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.THealthyPlanDAO;
import com.rc.portal.vo.THealthyPlan;
import com.rc.portal.vo.THealthyPlanExample;

public class THealthyPlanDAOImpl implements THealthyPlanDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public THealthyPlanDAOImpl() {
        super();
    }

    public THealthyPlanDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(THealthyPlanExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_healthy_plan.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(THealthyPlanExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_healthy_plan.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        THealthyPlan key = new THealthyPlan();
        key.setId(id);
        int rows = sqlMapClient.delete("t_healthy_plan.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(THealthyPlan record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_healthy_plan.ibatorgenerated_insert", record);
    }

    public Long insertSelective(THealthyPlan record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_healthy_plan.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(THealthyPlanExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_healthy_plan.ibatorgenerated_selectByExample", example);
        return list;
    }

    public THealthyPlan selectByPrimaryKey(Long id) throws SQLException {
        THealthyPlan key = new THealthyPlan();
        key.setId(id);
        THealthyPlan record = (THealthyPlan) sqlMapClient.queryForObject("t_healthy_plan.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(THealthyPlan record, THealthyPlanExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_healthy_plan.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(THealthyPlan record, THealthyPlanExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_healthy_plan.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(THealthyPlan record) throws SQLException {
        int rows = sqlMapClient.update("t_healthy_plan.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(THealthyPlan record) throws SQLException {
        int rows = sqlMapClient.update("t_healthy_plan.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends THealthyPlanExample {
        private Object record;

        public UpdateByExampleParms(Object record, THealthyPlanExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(THealthyPlanExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_healthy_plan.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
