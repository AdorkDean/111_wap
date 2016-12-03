package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.THealthyPlanCombinationDAO;
import com.rc.portal.vo.THealthyPlanCombination;
import com.rc.portal.vo.THealthyPlanCombinationExample;

public class THealthyPlanCombinationDAOImpl implements THealthyPlanCombinationDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public THealthyPlanCombinationDAOImpl() {
        super();
    }

    public THealthyPlanCombinationDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(THealthyPlanCombinationExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_healthy_plan_combination.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(THealthyPlanCombinationExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_healthy_plan_combination.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        THealthyPlanCombination key = new THealthyPlanCombination();
        key.setId(id);
        int rows = sqlMapClient.delete("t_healthy_plan_combination.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(THealthyPlanCombination record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_healthy_plan_combination.ibatorgenerated_insert", record);
    }

    public Long insertSelective(THealthyPlanCombination record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_healthy_plan_combination.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(THealthyPlanCombinationExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_healthy_plan_combination.ibatorgenerated_selectByExample", example);
        return list;
    }

    public THealthyPlanCombination selectByPrimaryKey(Long id) throws SQLException {
        THealthyPlanCombination key = new THealthyPlanCombination();
        key.setId(id);
        THealthyPlanCombination record = (THealthyPlanCombination) sqlMapClient.queryForObject("t_healthy_plan_combination.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(THealthyPlanCombination record, THealthyPlanCombinationExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_healthy_plan_combination.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(THealthyPlanCombination record, THealthyPlanCombinationExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_healthy_plan_combination.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(THealthyPlanCombination record) throws SQLException {
        int rows = sqlMapClient.update("t_healthy_plan_combination.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(THealthyPlanCombination record) throws SQLException {
        int rows = sqlMapClient.update("t_healthy_plan_combination.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends THealthyPlanCombinationExample {
        private Object record;

        public UpdateByExampleParms(Object record, THealthyPlanCombinationExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(THealthyPlanCombinationExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_healthy_plan_combination.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
