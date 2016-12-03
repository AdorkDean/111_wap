package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TTaskExecExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public TTaskExecExample() {
        oredCriteria = new ArrayList();


    }

    protected TTaskExecExample(TTaskExecExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;


    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;


    }

    public String getOrderByClause() {
        return orderByClause;


    }

    public List getOredCriteria() {
        return oredCriteria;


    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);


    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);


        }
        return criteria;


    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;


    }

    public void clear() {
        oredCriteria.clear();


    }

    public static class Criteria {
        protected List criteriaWithoutValue;

        protected List criteriaWithSingleValue;

        protected List criteriaWithListValue;

        protected List criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList();
            criteriaWithSingleValue = new ArrayList();
            criteriaWithListValue = new ArrayList();
            criteriaWithBetweenValue = new ArrayList();


        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;


        }

        public List getCriteriaWithoutValue() {
            return criteriaWithoutValue;


        }

        public List getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;


        }

        public List getCriteriaWithListValue() {
            return criteriaWithListValue;


        }

        public List getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;


        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");


            }
            criteriaWithoutValue.add(condition);


        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");


            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);


        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");


            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);


        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");


            }
            List list = new ArrayList();
            list.add(value1);
            list.add(value2);
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);


        }

        public Criteria andTaskidIsNull() {
            addCriterion("taskId is null");
            return this;


        }

        public Criteria andTaskidIsNotNull() {
            addCriterion("taskId is not null");
            return this;


        }

        public Criteria andTaskidEqualTo(String value) {
            addCriterion("taskId =", value, "taskid");
            return this;


        }

        public Criteria andTaskidNotEqualTo(String value) {
            addCriterion("taskId <>", value, "taskid");
            return this;


        }

        public Criteria andTaskidGreaterThan(String value) {
            addCriterion("taskId >", value, "taskid");
            return this;


        }

        public Criteria andTaskidGreaterThanOrEqualTo(String value) {
            addCriterion("taskId >=", value, "taskid");
            return this;


        }

        public Criteria andTaskidLessThan(String value) {
            addCriterion("taskId <", value, "taskid");
            return this;


        }

        public Criteria andTaskidLessThanOrEqualTo(String value) {
            addCriterion("taskId <=", value, "taskid");
            return this;


        }

        public Criteria andTaskidLike(String value) {
            addCriterion("taskId like", value, "taskid");
            return this;


        }

        public Criteria andTaskidNotLike(String value) {
            addCriterion("taskId not like", value, "taskid");
            return this;


        }

        public Criteria andTaskidIn(List values) {
            addCriterion("taskId in", values, "taskid");
            return this;


        }

        public Criteria andTaskidNotIn(List values) {
            addCriterion("taskId not in", values, "taskid");
            return this;


        }

        public Criteria andTaskidBetween(String value1, String value2) {
            addCriterion("taskId between", value1, value2, "taskid");
            return this;


        }

        public Criteria andTaskidNotBetween(String value1, String value2) {
            addCriterion("taskId not between", value1, value2, "taskid");
            return this;


        }

        public Criteria andLastexecutedIsNull() {
            addCriterion("lastExecuted is null");
            return this;


        }

        public Criteria andLastexecutedIsNotNull() {
            addCriterion("lastExecuted is not null");
            return this;


        }

        public Criteria andLastexecutedEqualTo(String value) {
            addCriterion("lastExecuted =", value, "lastexecuted");
            return this;


        }

        public Criteria andLastexecutedNotEqualTo(String value) {
            addCriterion("lastExecuted <>", value, "lastexecuted");
            return this;


        }

        public Criteria andLastexecutedGreaterThan(String value) {
            addCriterion("lastExecuted >", value, "lastexecuted");
            return this;


        }

        public Criteria andLastexecutedGreaterThanOrEqualTo(String value) {
            addCriterion("lastExecuted >=", value, "lastexecuted");
            return this;


        }

        public Criteria andLastexecutedLessThan(String value) {
            addCriterion("lastExecuted <", value, "lastexecuted");
            return this;


        }

        public Criteria andLastexecutedLessThanOrEqualTo(String value) {
            addCriterion("lastExecuted <=", value, "lastexecuted");
            return this;


        }

        public Criteria andLastexecutedLike(String value) {
            addCriterion("lastExecuted like", value, "lastexecuted");
            return this;


        }

        public Criteria andLastexecutedNotLike(String value) {
            addCriterion("lastExecuted not like", value, "lastexecuted");
            return this;


        }

        public Criteria andLastexecutedIn(List values) {
            addCriterion("lastExecuted in", values, "lastexecuted");
            return this;


        }

        public Criteria andLastexecutedNotIn(List values) {
            addCriterion("lastExecuted not in", values, "lastexecuted");
            return this;


        }

        public Criteria andLastexecutedBetween(String value1, String value2) {
            addCriterion("lastExecuted between", value1, value2, "lastexecuted");
            return this;


        }

        public Criteria andLastexecutedNotBetween(String value1, String value2) {
            addCriterion("lastExecuted not between", value1, value2, "lastexecuted");
            return this;


        }

        public Criteria andTasknameIsNull() {
            addCriterion("taskName is null");
            return this;


        }

        public Criteria andTasknameIsNotNull() {
            addCriterion("taskName is not null");
            return this;


        }

        public Criteria andTasknameEqualTo(String value) {
            addCriterion("taskName =", value, "taskname");
            return this;


        }

        public Criteria andTasknameNotEqualTo(String value) {
            addCriterion("taskName <>", value, "taskname");
            return this;


        }

        public Criteria andTasknameGreaterThan(String value) {
            addCriterion("taskName >", value, "taskname");
            return this;


        }

        public Criteria andTasknameGreaterThanOrEqualTo(String value) {
            addCriterion("taskName >=", value, "taskname");
            return this;


        }

        public Criteria andTasknameLessThan(String value) {
            addCriterion("taskName <", value, "taskname");
            return this;


        }

        public Criteria andTasknameLessThanOrEqualTo(String value) {
            addCriterion("taskName <=", value, "taskname");
            return this;


        }

        public Criteria andTasknameLike(String value) {
            addCriterion("taskName like", value, "taskname");
            return this;


        }

        public Criteria andTasknameNotLike(String value) {
            addCriterion("taskName not like", value, "taskname");
            return this;


        }

        public Criteria andTasknameIn(List values) {
            addCriterion("taskName in", values, "taskname");
            return this;


        }

        public Criteria andTasknameNotIn(List values) {
            addCriterion("taskName not in", values, "taskname");
            return this;


        }

        public Criteria andTasknameBetween(String value1, String value2) {
            addCriterion("taskName between", value1, value2, "taskname");
            return this;


        }

        public Criteria andTasknameNotBetween(String value1, String value2) {
            addCriterion("taskName not between", value1, value2, "taskname");
            return this;


        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return this;


        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return this;


        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return this;


        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return this;


        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return this;


        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return this;


        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return this;


        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return this;


        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return this;


        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return this;


        }

        public Criteria andRemarkIn(List values) {
            addCriterion("remark in", values, "remark");
            return this;


        }

        public Criteria andRemarkNotIn(List values) {
            addCriterion("remark not in", values, "remark");
            return this;


        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return this;


        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return this;


        }


    }


}
