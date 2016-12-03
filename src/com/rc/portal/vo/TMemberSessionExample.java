package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TMemberSessionExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public TMemberSessionExample() {
        oredCriteria = new ArrayList();


    }

    protected TMemberSessionExample(TMemberSessionExample example) {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return this;


        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return this;


        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return this;


        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return this;


        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return this;


        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return this;


        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return this;


        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return this;


        }

        public Criteria andIdIn(List values) {
            addCriterion("id in", values, "id");
            return this;


        }

        public Criteria andIdNotIn(List values) {
            addCriterion("id not in", values, "id");
            return this;


        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return this;


        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return this;


        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return this;


        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return this;


        }

        public Criteria andUseridEqualTo(Long value) {
            addCriterion("userId =", value, "userid");
            return this;


        }

        public Criteria andUseridNotEqualTo(Long value) {
            addCriterion("userId <>", value, "userid");
            return this;


        }

        public Criteria andUseridGreaterThan(Long value) {
            addCriterion("userId >", value, "userid");
            return this;


        }

        public Criteria andUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("userId >=", value, "userid");
            return this;


        }

        public Criteria andUseridLessThan(Long value) {
            addCriterion("userId <", value, "userid");
            return this;


        }

        public Criteria andUseridLessThanOrEqualTo(Long value) {
            addCriterion("userId <=", value, "userid");
            return this;


        }

        public Criteria andUseridIn(List values) {
            addCriterion("userId in", values, "userid");
            return this;


        }

        public Criteria andUseridNotIn(List values) {
            addCriterion("userId not in", values, "userid");
            return this;


        }

        public Criteria andUseridBetween(Long value1, Long value2) {
            addCriterion("userId between", value1, value2, "userid");
            return this;


        }

        public Criteria andUseridNotBetween(Long value1, Long value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return this;


        }

        public Criteria andSessionidIsNull() {
            addCriterion("sessionId is null");
            return this;


        }

        public Criteria andSessionidIsNotNull() {
            addCriterion("sessionId is not null");
            return this;


        }

        public Criteria andSessionidEqualTo(String value) {
            addCriterion("sessionId =", value, "sessionid");
            return this;


        }

        public Criteria andSessionidNotEqualTo(String value) {
            addCriterion("sessionId <>", value, "sessionid");
            return this;


        }

        public Criteria andSessionidGreaterThan(String value) {
            addCriterion("sessionId >", value, "sessionid");
            return this;


        }

        public Criteria andSessionidGreaterThanOrEqualTo(String value) {
            addCriterion("sessionId >=", value, "sessionid");
            return this;


        }

        public Criteria andSessionidLessThan(String value) {
            addCriterion("sessionId <", value, "sessionid");
            return this;


        }

        public Criteria andSessionidLessThanOrEqualTo(String value) {
            addCriterion("sessionId <=", value, "sessionid");
            return this;


        }

        public Criteria andSessionidLike(String value) {
            addCriterion("sessionId like", value, "sessionid");
            return this;


        }

        public Criteria andSessionidNotLike(String value) {
            addCriterion("sessionId not like", value, "sessionid");
            return this;


        }

        public Criteria andSessionidIn(List values) {
            addCriterion("sessionId in", values, "sessionid");
            return this;


        }

        public Criteria andSessionidNotIn(List values) {
            addCriterion("sessionId not in", values, "sessionid");
            return this;


        }

        public Criteria andSessionidBetween(String value1, String value2) {
            addCriterion("sessionId between", value1, value2, "sessionid");
            return this;


        }

        public Criteria andSessionidNotBetween(String value1, String value2) {
            addCriterion("sessionId not between", value1, value2, "sessionid");
            return this;


        }


    }


}
