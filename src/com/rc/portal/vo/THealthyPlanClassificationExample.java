package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class THealthyPlanClassificationExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public THealthyPlanClassificationExample() {
        oredCriteria = new ArrayList();


    }

    protected THealthyPlanClassificationExample(THealthyPlanClassificationExample example) {
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

        public Criteria andClassificationNameIsNull() {
            addCriterion("classification_name is null");
            return this;


        }

        public Criteria andClassificationNameIsNotNull() {
            addCriterion("classification_name is not null");
            return this;


        }

        public Criteria andClassificationNameEqualTo(String value) {
            addCriterion("classification_name =", value, "classificationName");
            return this;


        }

        public Criteria andClassificationNameNotEqualTo(String value) {
            addCriterion("classification_name <>", value, "classificationName");
            return this;


        }

        public Criteria andClassificationNameGreaterThan(String value) {
            addCriterion("classification_name >", value, "classificationName");
            return this;


        }

        public Criteria andClassificationNameGreaterThanOrEqualTo(String value) {
            addCriterion("classification_name >=", value, "classificationName");
            return this;


        }

        public Criteria andClassificationNameLessThan(String value) {
            addCriterion("classification_name <", value, "classificationName");
            return this;


        }

        public Criteria andClassificationNameLessThanOrEqualTo(String value) {
            addCriterion("classification_name <=", value, "classificationName");
            return this;


        }

        public Criteria andClassificationNameLike(String value) {
            addCriterion("classification_name like", value, "classificationName");
            return this;


        }

        public Criteria andClassificationNameNotLike(String value) {
            addCriterion("classification_name not like", value, "classificationName");
            return this;


        }

        public Criteria andClassificationNameIn(List values) {
            addCriterion("classification_name in", values, "classificationName");
            return this;


        }

        public Criteria andClassificationNameNotIn(List values) {
            addCriterion("classification_name not in", values, "classificationName");
            return this;


        }

        public Criteria andClassificationNameBetween(String value1, String value2) {
            addCriterion("classification_name between", value1, value2, "classificationName");
            return this;


        }

        public Criteria andClassificationNameNotBetween(String value1, String value2) {
            addCriterion("classification_name not between", value1, value2, "classificationName");
            return this;


        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return this;


        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return this;


        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return this;


        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return this;


        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return this;


        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return this;


        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return this;


        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return this;


        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return this;


        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return this;


        }

        public Criteria andContentIn(List values) {
            addCriterion("content in", values, "content");
            return this;


        }

        public Criteria andContentNotIn(List values) {
            addCriterion("content not in", values, "content");
            return this;


        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return this;


        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return this;


        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return this;


        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return this;


        }

        public Criteria andWeightEqualTo(Integer value) {
            addCriterion("weight =", value, "weight");
            return this;


        }

        public Criteria andWeightNotEqualTo(Integer value) {
            addCriterion("weight <>", value, "weight");
            return this;


        }

        public Criteria andWeightGreaterThan(Integer value) {
            addCriterion("weight >", value, "weight");
            return this;


        }

        public Criteria andWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight >=", value, "weight");
            return this;


        }

        public Criteria andWeightLessThan(Integer value) {
            addCriterion("weight <", value, "weight");
            return this;


        }

        public Criteria andWeightLessThanOrEqualTo(Integer value) {
            addCriterion("weight <=", value, "weight");
            return this;


        }

        public Criteria andWeightIn(List values) {
            addCriterion("weight in", values, "weight");
            return this;


        }

        public Criteria andWeightNotIn(List values) {
            addCriterion("weight not in", values, "weight");
            return this;


        }

        public Criteria andWeightBetween(Integer value1, Integer value2) {
            addCriterion("weight between", value1, value2, "weight");
            return this;


        }

        public Criteria andWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return this;


        }

        public Criteria andClassificationLevelIsNull() {
            addCriterion("classification_level is null");
            return this;


        }

        public Criteria andClassificationLevelIsNotNull() {
            addCriterion("classification_level is not null");
            return this;


        }

        public Criteria andClassificationLevelEqualTo(Integer value) {
            addCriterion("classification_level =", value, "classificationLevel");
            return this;


        }

        public Criteria andClassificationLevelNotEqualTo(Integer value) {
            addCriterion("classification_level <>", value, "classificationLevel");
            return this;


        }

        public Criteria andClassificationLevelGreaterThan(Integer value) {
            addCriterion("classification_level >", value, "classificationLevel");
            return this;


        }

        public Criteria andClassificationLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("classification_level >=", value, "classificationLevel");
            return this;


        }

        public Criteria andClassificationLevelLessThan(Integer value) {
            addCriterion("classification_level <", value, "classificationLevel");
            return this;


        }

        public Criteria andClassificationLevelLessThanOrEqualTo(Integer value) {
            addCriterion("classification_level <=", value, "classificationLevel");
            return this;


        }

        public Criteria andClassificationLevelIn(List values) {
            addCriterion("classification_level in", values, "classificationLevel");
            return this;


        }

        public Criteria andClassificationLevelNotIn(List values) {
            addCriterion("classification_level not in", values, "classificationLevel");
            return this;


        }

        public Criteria andClassificationLevelBetween(Integer value1, Integer value2) {
            addCriterion("classification_level between", value1, value2, "classificationLevel");
            return this;


        }

        public Criteria andClassificationLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("classification_level not between", value1, value2, "classificationLevel");
            return this;


        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return this;


        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return this;


        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("parent_id =", value, "parentId");
            return this;


        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("parent_id <>", value, "parentId");
            return this;


        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("parent_id >", value, "parentId");
            return this;


        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("parent_id >=", value, "parentId");
            return this;


        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("parent_id <", value, "parentId");
            return this;


        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("parent_id <=", value, "parentId");
            return this;


        }

        public Criteria andParentIdIn(List values) {
            addCriterion("parent_id in", values, "parentId");
            return this;


        }

        public Criteria andParentIdNotIn(List values) {
            addCriterion("parent_id not in", values, "parentId");
            return this;


        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return this;


        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return this;


        }

        public Criteria andCreateDtIsNull() {
            addCriterion("create_dt is null");
            return this;


        }

        public Criteria andCreateDtIsNotNull() {
            addCriterion("create_dt is not null");
            return this;


        }

        public Criteria andCreateDtEqualTo(Date value) {
            addCriterion("create_dt =", value, "createDt");
            return this;


        }

        public Criteria andCreateDtNotEqualTo(Date value) {
            addCriterion("create_dt <>", value, "createDt");
            return this;


        }

        public Criteria andCreateDtGreaterThan(Date value) {
            addCriterion("create_dt >", value, "createDt");
            return this;


        }

        public Criteria andCreateDtGreaterThanOrEqualTo(Date value) {
            addCriterion("create_dt >=", value, "createDt");
            return this;


        }

        public Criteria andCreateDtLessThan(Date value) {
            addCriterion("create_dt <", value, "createDt");
            return this;


        }

        public Criteria andCreateDtLessThanOrEqualTo(Date value) {
            addCriterion("create_dt <=", value, "createDt");
            return this;


        }

        public Criteria andCreateDtIn(List values) {
            addCriterion("create_dt in", values, "createDt");
            return this;


        }

        public Criteria andCreateDtNotIn(List values) {
            addCriterion("create_dt not in", values, "createDt");
            return this;


        }

        public Criteria andCreateDtBetween(Date value1, Date value2) {
            addCriterion("create_dt between", value1, value2, "createDt");
            return this;


        }

        public Criteria andCreateDtNotBetween(Date value1, Date value2) {
            addCriterion("create_dt not between", value1, value2, "createDt");
            return this;


        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return this;


        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return this;


        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return this;


        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return this;


        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return this;


        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return this;


        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return this;


        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return this;


        }

        public Criteria andStatusIn(List values) {
            addCriterion("status in", values, "status");
            return this;


        }

        public Criteria andStatusNotIn(List values) {
            addCriterion("status not in", values, "status");
            return this;


        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return this;


        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return this;


        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return this;


        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return this;


        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return this;


        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return this;


        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return this;


        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return this;


        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return this;


        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return this;


        }

        public Criteria andTypeIn(List values) {
            addCriterion("type in", values, "type");
            return this;


        }

        public Criteria andTypeNotIn(List values) {
            addCriterion("type not in", values, "type");
            return this;


        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return this;


        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return this;


        }


    }


}
