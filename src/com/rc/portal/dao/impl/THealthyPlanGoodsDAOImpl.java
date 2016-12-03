package com.rc.portal.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rc.app.framework.webapp.model.page.PageManager;
import com.rc.app.framework.webapp.model.page.PageWraper;
import com.rc.portal.dao.THealthyPlanGoodsDAO;
import com.rc.portal.vo.THealthyPlanGoods;
import com.rc.portal.vo.THealthyPlanGoodsExample;

public class THealthyPlanGoodsDAOImpl implements THealthyPlanGoodsDAO {
    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
    public THealthyPlanGoodsDAOImpl() {
        super();
    }

    public THealthyPlanGoodsDAOImpl(SqlMapClient sqlMapClient) {
        super();
        this.sqlMapClient = sqlMapClient;
    }

    public int countByExample(THealthyPlanGoodsExample example) throws SQLException {
        Integer count = (Integer)  sqlMapClient.queryForObject("t_healthy_plan_goods.ibatorgenerated_countByExample", example);
        return count.intValue();
    }

    public int deleteByExample(THealthyPlanGoodsExample example) throws SQLException {
        int rows = sqlMapClient.delete("t_healthy_plan_goods.ibatorgenerated_deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Long id) throws SQLException {
        THealthyPlanGoods key = new THealthyPlanGoods();
        key.setId(id);
        int rows = sqlMapClient.delete("t_healthy_plan_goods.ibatorgenerated_deleteByPrimaryKey", key);
        return rows;
    }

    public Long insert(THealthyPlanGoods record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_healthy_plan_goods.ibatorgenerated_insert", record);
    }

    public Long insertSelective(THealthyPlanGoods record) throws SQLException {
		return (Long)        sqlMapClient.insert("t_healthy_plan_goods.ibatorgenerated_insertSelective", record);
    }

    public List selectByExample(THealthyPlanGoodsExample example) throws SQLException {
        List list = sqlMapClient.queryForList("t_healthy_plan_goods.ibatorgenerated_selectByExample", example);
        return list;
    }

    public THealthyPlanGoods selectByPrimaryKey(Long id) throws SQLException {
        THealthyPlanGoods key = new THealthyPlanGoods();
        key.setId(id);
        THealthyPlanGoods record = (THealthyPlanGoods) sqlMapClient.queryForObject("t_healthy_plan_goods.ibatorgenerated_selectByPrimaryKey", key);
        return record;
    }

    public int updateByExampleSelective(THealthyPlanGoods record, THealthyPlanGoodsExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_healthy_plan_goods.ibatorgenerated_updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(THealthyPlanGoods record, THealthyPlanGoodsExample example) throws SQLException {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = sqlMapClient.update("t_healthy_plan_goods.ibatorgenerated_updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(THealthyPlanGoods record) throws SQLException {
        int rows = sqlMapClient.update("t_healthy_plan_goods.ibatorgenerated_updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(THealthyPlanGoods record) throws SQLException {
        int rows = sqlMapClient.update("t_healthy_plan_goods.ibatorgenerated_updateByPrimaryKey", record);
        return rows;
    }

    private static class UpdateByExampleParms extends THealthyPlanGoodsExample {
        private Object record;

        public UpdateByExampleParms(Object record, THealthyPlanGoodsExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
	public PageWraper selectByRepositoryByPage(THealthyPlanGoodsExample example) throws SQLException {
		PageWraper pw=null;
		int count=this.countByExample(example);
		List list = sqlMapClient.queryForList("t_healthy_plan_goods.selectByExampleByPage", example);
		System.out.println("��Դ��ҳ��ѯlist.size="+list.size());
		pw=PageManager.getPageWraper(example.getPageInfo(), list, count);
		return pw;
	}
}
