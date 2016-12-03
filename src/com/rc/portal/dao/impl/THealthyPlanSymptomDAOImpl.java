package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.THealthyPlanSymptomDAO;
import com.rc.portal.vo.THealthyPlanSymptom;
import com.rc.portal.vo.THealthyPlanSymptomExample;

public class THealthyPlanSymptomDAOImpl implements THealthyPlanSymptomDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public THealthyPlanSymptomDAOImpl() {
        super();
    }

    public THealthyPlanSymptomDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(THealthyPlanSymptomExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_healthy_plan_symptom.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(THealthyPlanSymptomExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_healthy_plan_symptom.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        THealthyPlanSymptom key = new THealthyPlanSymptom();
        key.setId(id);
        int rows = sqlMapClient.delete("t_healthy_plan_symptom.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(THealthyPlanSymptom record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_healthy_plan_symptom.ibatorgenerated_insert", record);
    }

    public Long insertSelective(THealthyPlanSymptom record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_healthy_plan_symptom.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(THealthyPlanSymptomExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_healthy_plan_symptom.ibatorgenerated_selectByExample", example);
        return list;
    }

    public THealthyPlanSymptom selectByPrimaryKey(Long id) throws SQLException {
        THealthyPlanSymptom key = new THealthyPlanSymptom();
        key.setId(id);
        THealthyPlanSymptom record = (THealthyPlanSymptom) sqlMapClient.queryForObject("t_healthy_plan_symptom.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(THealthyPlanSymptom record, THealthyPlanSymptomExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_healthy_plan_symptom.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(THealthyPlanSymptom record, THealthyPlanSymptomExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_healthy_plan_symptom.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(THealthyPlanSymptom record) throws SQLException {
        int rows = sqlMapClient.update("t_healthy_plan_symptom.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(THealthyPlanSymptom record) throws SQLException {
        int rows = sqlMapClient.update("t_healthy_plan_symptom.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends THealthyPlanSymptomExample {
        private Object record;

        public UpdateByExampleParms(Object record, THealthyPlanSymptomExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(THealthyPlanSymptomExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_healthy_plan_symptom.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
