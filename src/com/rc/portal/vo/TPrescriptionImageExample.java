package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TPrescriptionImageExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public TPrescriptionImageExample() {
        oredCriteria = new ArrayList();


    }

    protected TPrescriptionImageExample(TPrescriptionImageExample example) {
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

        public Criteria andPrescriptionIdIsNull() {
            addCriterion("prescription_id is null");
            return this;


        }

        public Criteria andPrescriptionIdIsNotNull() {
            addCriterion("prescription_id is not null");
            return this;


        }

        public Criteria andPrescriptionIdEqualTo(Long value) {
            addCriterion("prescription_id =", value, "prescriptionId");
            return this;


        }

        public Criteria andPrescriptionIdNotEqualTo(Long value) {
            addCriterion("prescription_id <>", value, "prescriptionId");
            return this;


        }

        public Criteria andPrescriptionIdGreaterThan(Long value) {
            addCriterion("prescription_id >", value, "prescriptionId");
            return this;


        }

        public Criteria andPrescriptionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("prescription_id >=", value, "prescriptionId");
            return this;


        }

        public Criteria andPrescriptionIdLessThan(Long value) {
            addCriterion("prescription_id <", value, "prescriptionId");
            return this;


        }

        public Criteria andPrescriptionIdLessThanOrEqualTo(Long value) {
            addCriterion("prescription_id <=", value, "prescriptionId");
            return this;


        }

        public Criteria andPrescriptionIdIn(List values) {
            addCriterion("prescription_id in", values, "prescriptionId");
            return this;


        }

        public Criteria andPrescriptionIdNotIn(List values) {
            addCriterion("prescription_id not in", values, "prescriptionId");
            return this;


        }

        public Criteria andPrescriptionIdBetween(Long value1, Long value2) {
            addCriterion("prescription_id between", value1, value2, "prescriptionId");
            return this;


        }

        public Criteria andPrescriptionIdNotBetween(Long value1, Long value2) {
            addCriterion("prescription_id not between", value1, value2, "prescriptionId");
            return this;


        }

        public Criteria andPathIsNull() {
            addCriterion("path is null");
            return this;


        }

        public Criteria andPathIsNotNull() {
            addCriterion("path is not null");
            return this;


        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("path =", value, "path");
            return this;


        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("path <>", value, "path");
            return this;


        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("path >", value, "path");
            return this;


        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("path >=", value, "path");
            return this;


        }

        public Criteria andPathLessThan(String value) {
            addCriterion("path <", value, "path");
            return this;


        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("path <=", value, "path");
            return this;


        }

        public Criteria andPathLike(String value) {
            addCriterion("path like", value, "path");
            return this;


        }

        public Criteria andPathNotLike(String value) {
            addCriterion("path not like", value, "path");
            return this;


        }

        public Criteria andPathIn(List values) {
            addCriterion("path in", values, "path");
            return this;


        }

        public Criteria andPathNotIn(List values) {
            addCriterion("path not in", values, "path");
            return this;


        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("path between", value1, value2, "path");
            return this;


        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("path not between", value1, value2, "path");
            return this;


        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return this;


        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return this;


        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return this;


        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return this;


        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return this;


        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return this;


        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return this;


        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return this;


        }

        public Criteria andCreateDateIn(List values) {
            addCriterion("create_date in", values, "createDate");
            return this;


        }

        public Criteria andCreateDateNotIn(List values) {
            addCriterion("create_date not in", values, "createDate");
            return this;


        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return this;


        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return this;


        }

        public Criteria andModifyDateIsNull() {
            addCriterion("modify_date is null");
            return this;


        }

        public Criteria andModifyDateIsNotNull() {
            addCriterion("modify_date is not null");
            return this;


        }

        public Criteria andModifyDateEqualTo(Date value) {
            addCriterion("modify_date =", value, "modifyDate");
            return this;


        }

        public Criteria andModifyDateNotEqualTo(Date value) {
            addCriterion("modify_date <>", value, "modifyDate");
            return this;


        }

        public Criteria andModifyDateGreaterThan(Date value) {
            addCriterion("modify_date >", value, "modifyDate");
            return this;


        }

        public Criteria andModifyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_date >=", value, "modifyDate");
            return this;


        }

        public Criteria andModifyDateLessThan(Date value) {
            addCriterion("modify_date <", value, "modifyDate");
            return this;


        }

        public Criteria andModifyDateLessThanOrEqualTo(Date value) {
            addCriterion("modify_date <=", value, "modifyDate");
            return this;


        }

        public Criteria andModifyDateIn(List values) {
            addCriterion("modify_date in", values, "modifyDate");
            return this;


        }

        public Criteria andModifyDateNotIn(List values) {
            addCriterion("modify_date not in", values, "modifyDate");
            return this;


        }

        public Criteria andModifyDateBetween(Date value1, Date value2) {
            addCriterion("modify_date between", value1, value2, "modifyDate");
            return this;


        }

        public Criteria andModifyDateNotBetween(Date value1, Date value2) {
            addCriterion("modify_date not between", value1, value2, "modifyDate");
            return this;


        }


    }


}
