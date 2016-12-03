package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.THealthyPlanClassificationDAO;
import com.rc.portal.vo.THealthyPlanClassification;
import com.rc.portal.vo.THealthyPlanClassificationExample;

public class THealthyPlanClassificationDAOImpl implements THealthyPlanClassificationDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public THealthyPlanClassificationDAOImpl() {
        super();
    }

    public THealthyPlanClassificationDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(THealthyPlanClassificationExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_healthy_plan_classification.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(THealthyPlanClassificationExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_healthy_plan_classification.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        THealthyPlanClassification key = new THealthyPlanClassification();
        key.setId(id);
        int rows = sqlMapClient.delete("t_healthy_plan_classification.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(THealthyPlanClassification record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_healthy_plan_classification.ibatorgenerated_insert", record);
    }

    public Long insertSelective(THealthyPlanClassification record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_healthy_plan_classification.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(THealthyPlanClassificationExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_healthy_plan_classification.ibatorgenerated_selectByExample", example);
        return list;
    }

    public THealthyPlanClassification selectByPrimaryKey(Long id) throws SQLException {
        THealthyPlanClassification key = new THealthyPlanClassification();
        key.setId(id);
        THealthyPlanClassification record = (THealthyPlanClassification) sqlMapClient.queryForObject("t_healthy_plan_classification.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(THealthyPlanClassification record, THealthyPlanClassificationExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_healthy_plan_classification.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(THealthyPlanClassification record, THealthyPlanClassificationExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_healthy_plan_classification.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(THealthyPlanClassification record) throws SQLException {
        int rows = sqlMapClient.update("t_healthy_plan_classification.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(THealthyPlanClassification record) throws SQLException {
        int rows = sqlMapClient.update("t_healthy_plan_classification.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends THealthyPlanClassificationExample {
        private Object record;

        public UpdateByExampleParms(Object record, THealthyPlanClassificationExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(THealthyPlanClassificationExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_healthy_plan_classification.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
