package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.THealthyPlanRecommendDAO;
import com.rc.portal.vo.THealthyPlanRecommend;
import com.rc.portal.vo.THealthyPlanRecommendExample;

public class THealthyPlanRecommendDAOImpl implements THealthyPlanRecommendDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public THealthyPlanRecommendDAOImpl() {
        super();
    }

    public THealthyPlanRecommendDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(THealthyPlanRecommendExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_healthy_plan_recommend.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(THealthyPlanRecommendExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_healthy_plan_recommend.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        THealthyPlanRecommend key = new THealthyPlanRecommend();
        key.setId(id);
        int rows = sqlMapClient.delete("t_healthy_plan_recommend.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(THealthyPlanRecommend record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_healthy_plan_recommend.ibatorgenerated_insert", record);
    }

    public Long insertSelective(THealthyPlanRecommend record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_healthy_plan_recommend.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(THealthyPlanRecommendExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_healthy_plan_recommend.ibatorgenerated_selectByExample", example);
        return list;
    }

    public THealthyPlanRecommend selectByPrimaryKey(Long id) throws SQLException {
        THealthyPlanRecommend key = new THealthyPlanRecommend();
        key.setId(id);
        THealthyPlanRecommend record = (THealthyPlanRecommend) sqlMapClient.queryForObject("t_healthy_plan_recommend.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(THealthyPlanRecommend record, THealthyPlanRecommendExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_healthy_plan_recommend.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(THealthyPlanRecommend record, THealthyPlanRecommendExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_healthy_plan_recommend.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(THealthyPlanRecommend record) throws SQLException {
        int rows = sqlMapClient.update("t_healthy_plan_recommend.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(THealthyPlanRecommend record) throws SQLException {
        int rows = sqlMapClient.update("t_healthy_plan_recommend.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends THealthyPlanRecommendExample {
        private Object record;

        public UpdateByExampleParms(Object record, THealthyPlanRecommendExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(THealthyPlanRecommendExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_healthy_plan_recommend.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
