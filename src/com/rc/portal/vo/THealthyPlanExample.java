package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class THealthyPlanExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public THealthyPlanExample() {
        oredCriteria = new ArrayList();


    }

    protected THealthyPlanExample(THealthyPlanExample example) {
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

        public Criteria andClassificationIdIsNull() {
            addCriterion("classification_id is null");
            return this;


        }

        public Criteria andClassificationIdIsNotNull() {
            addCriterion("classification_id is not null");
            return this;


        }

        public Criteria andClassificationIdEqualTo(Long value) {
            addCriterion("classification_id =", value, "classificationId");
            return this;


        }

        public Criteria andClassificationIdNotEqualTo(Long value) {
            addCriterion("classification_id <>", value, "classificationId");
            return this;


        }

        public Criteria andClassificationIdGreaterThan(Long value) {
            addCriterion("classification_id >", value, "classificationId");
            return this;


        }

        public Criteria andClassificationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("classification_id >=", value, "classificationId");
            return this;


        }

        public Criteria andClassificationIdLessThan(Long value) {
            addCriterion("classification_id <", value, "classificationId");
            return this;


        }

        public Criteria andClassificationIdLessThanOrEqualTo(Long value) {
            addCriterion("classification_id <=", value, "classificationId");
            return this;


        }

        public Criteria andClassificationIdIn(List values) {
            addCriterion("classification_id in", values, "classificationId");
            return this;


        }

        public Criteria andClassificationIdNotIn(List values) {
            addCriterion("classification_id not in", values, "classificationId");
            return this;


        }

        public Criteria andClassificationIdBetween(Long value1, Long value2) {
            addCriterion("classification_id between", value1, value2, "classificationId");
            return this;


        }

        public Criteria andClassificationIdNotBetween(Long value1, Long value2) {
            addCriterion("classification_id not between", value1, value2, "classificationId");
            return this;


        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return this;


        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return this;


        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return this;


        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return this;


        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return this;


        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return this;


        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return this;


        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return this;


        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return this;


        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return this;


        }

        public Criteria andNameIn(List values) {
            addCriterion("name in", values, "name");
            return this;


        }

        public Criteria andNameNotIn(List values) {
            addCriterion("name not in", values, "name");
            return this;


        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return this;


        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return this;


        }

        public Criteria andPlanExplainIsNull() {
            addCriterion("plan_explain is null");
            return this;


        }

        public Criteria andPlanExplainIsNotNull() {
            addCriterion("plan_explain is not null");
            return this;


        }

        public Criteria andPlanExplainEqualTo(String value) {
            addCriterion("plan_explain =", value, "planExplain");
            return this;


        }

        public Criteria andPlanExplainNotEqualTo(String value) {
            addCriterion("plan_explain <>", value, "planExplain");
            return this;


        }

        public Criteria andPlanExplainGreaterThan(String value) {
            addCriterion("plan_explain >", value, "planExplain");
            return this;


        }

        public Criteria andPlanExplainGreaterThanOrEqualTo(String value) {
            addCriterion("plan_explain >=", value, "planExplain");
            return this;


        }

        public Criteria andPlanExplainLessThan(String value) {
            addCriterion("plan_explain <", value, "planExplain");
            return this;


        }

        public Criteria andPlanExplainLessThanOrEqualTo(String value) {
            addCriterion("plan_explain <=", value, "planExplain");
            return this;


        }

        public Criteria andPlanExplainLike(String value) {
            addCriterion("plan_explain like", value, "planExplain");
            return this;


        }

        public Criteria andPlanExplainNotLike(String value) {
            addCriterion("plan_explain not like", value, "planExplain");
            return this;


        }

        public Criteria andPlanExplainIn(List values) {
            addCriterion("plan_explain in", values, "planExplain");
            return this;


        }

        public Criteria andPlanExplainNotIn(List values) {
            addCriterion("plan_explain not in", values, "planExplain");
            return this;


        }

        public Criteria andPlanExplainBetween(String value1, String value2) {
            addCriterion("plan_explain between", value1, value2, "planExplain");
            return this;


        }

        public Criteria andPlanExplainNotBetween(String value1, String value2) {
            addCriterion("plan_explain not between", value1, value2, "planExplain");
            return this;


        }

        public Criteria andBannerImgUrlIsNull() {
            addCriterion("banner_img_url is null");
            return this;


        }

        public Criteria andBannerImgUrlIsNotNull() {
            addCriterion("banner_img_url is not null");
            return this;


        }

        public Criteria andBannerImgUrlEqualTo(String value) {
            addCriterion("banner_img_url =", value, "bannerImgUrl");
            return this;


        }

        public Criteria andBannerImgUrlNotEqualTo(String value) {
            addCriterion("banner_img_url <>", value, "bannerImgUrl");
            return this;


        }

        public Criteria andBannerImgUrlGreaterThan(String value) {
            addCriterion("banner_img_url >", value, "bannerImgUrl");
            return this;


        }

        public Criteria andBannerImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("banner_img_url >=", value, "bannerImgUrl");
            return this;


        }

        public Criteria andBannerImgUrlLessThan(String value) {
            addCriterion("banner_img_url <", value, "bannerImgUrl");
            return this;


        }

        public Criteria andBannerImgUrlLessThanOrEqualTo(String value) {
            addCriterion("banner_img_url <=", value, "bannerImgUrl");
            return this;


        }

        public Criteria andBannerImgUrlLike(String value) {
            addCriterion("banner_img_url like", value, "bannerImgUrl");
            return this;


        }

        public Criteria andBannerImgUrlNotLike(String value) {
            addCriterion("banner_img_url not like", value, "bannerImgUrl");
            return this;


        }

        public Criteria andBannerImgUrlIn(List values) {
            addCriterion("banner_img_url in", values, "bannerImgUrl");
            return this;


        }

        public Criteria andBannerImgUrlNotIn(List values) {
            addCriterion("banner_img_url not in", values, "bannerImgUrl");
            return this;


        }

        public Criteria andBannerImgUrlBetween(String value1, String value2) {
            addCriterion("banner_img_url between", value1, value2, "bannerImgUrl");
            return this;


        }

        public Criteria andBannerImgUrlNotBetween(String value1, String value2) {
            addCriterion("banner_img_url not between", value1, value2, "bannerImgUrl");
            return this;


        }

        public Criteria andPlanImgUrlIsNull() {
            addCriterion("plan_img_url is null");
            return this;


        }

        public Criteria andPlanImgUrlIsNotNull() {
            addCriterion("plan_img_url is not null");
            return this;


        }

        public Criteria andPlanImgUrlEqualTo(String value) {
            addCriterion("plan_img_url =", value, "planImgUrl");
            return this;


        }

        public Criteria andPlanImgUrlNotEqualTo(String value) {
            addCriterion("plan_img_url <>", value, "planImgUrl");
            return this;


        }

        public Criteria andPlanImgUrlGreaterThan(String value) {
            addCriterion("plan_img_url >", value, "planImgUrl");
            return this;


        }

        public Criteria andPlanImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("plan_img_url >=", value, "planImgUrl");
            return this;


        }

        public Criteria andPlanImgUrlLessThan(String value) {
            addCriterion("plan_img_url <", value, "planImgUrl");
            return this;


        }

        public Criteria andPlanImgUrlLessThanOrEqualTo(String value) {
            addCriterion("plan_img_url <=", value, "planImgUrl");
            return this;


        }

        public Criteria andPlanImgUrlLike(String value) {
            addCriterion("plan_img_url like", value, "planImgUrl");
            return this;


        }

        public Criteria andPlanImgUrlNotLike(String value) {
            addCriterion("plan_img_url not like", value, "planImgUrl");
            return this;


        }

        public Criteria andPlanImgUrlIn(List values) {
            addCriterion("plan_img_url in", values, "planImgUrl");
            return this;


        }

        public Criteria andPlanImgUrlNotIn(List values) {
            addCriterion("plan_img_url not in", values, "planImgUrl");
            return this;


        }

        public Criteria andPlanImgUrlBetween(String value1, String value2) {
            addCriterion("plan_img_url between", value1, value2, "planImgUrl");
            return this;


        }

        public Criteria andPlanImgUrlNotBetween(String value1, String value2) {
            addCriterion("plan_img_url not between", value1, value2, "planImgUrl");
            return this;


        }

        public Criteria andReminderIsNull() {
            addCriterion("reminder is null");
            return this;


        }

        public Criteria andReminderIsNotNull() {
            addCriterion("reminder is not null");
            return this;


        }

        public Criteria andReminderEqualTo(String value) {
            addCriterion("reminder =", value, "reminder");
            return this;


        }

        public Criteria andReminderNotEqualTo(String value) {
            addCriterion("reminder <>", value, "reminder");
            return this;


        }

        public Criteria andReminderGreaterThan(String value) {
            addCriterion("reminder >", value, "reminder");
            return this;


        }

        public Criteria andReminderGreaterThanOrEqualTo(String value) {
            addCriterion("reminder >=", value, "reminder");
            return this;


        }

        public Criteria andReminderLessThan(String value) {
            addCriterion("reminder <", value, "reminder");
            return this;


        }

        public Criteria andReminderLessThanOrEqualTo(String value) {
            addCriterion("reminder <=", value, "reminder");
            return this;


        }

        public Criteria andReminderLike(String value) {
            addCriterion("reminder like", value, "reminder");
            return this;


        }

        public Criteria andReminderNotLike(String value) {
            addCriterion("reminder not like", value, "reminder");
            return this;


        }

        public Criteria andReminderIn(List values) {
            addCriterion("reminder in", values, "reminder");
            return this;


        }

        public Criteria andReminderNotIn(List values) {
            addCriterion("reminder not in", values, "reminder");
            return this;


        }

        public Criteria andReminderBetween(String value1, String value2) {
            addCriterion("reminder between", value1, value2, "reminder");
            return this;


        }

        public Criteria andReminderNotBetween(String value1, String value2) {
            addCriterion("reminder not between", value1, value2, "reminder");
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

        public Criteria andDeleteStatusIsNull() {
            addCriterion("delete_status is null");
            return this;


        }

        public Criteria andDeleteStatusIsNotNull() {
            addCriterion("delete_status is not null");
            return this;


        }

        public Criteria andDeleteStatusEqualTo(Integer value) {
            addCriterion("delete_status =", value, "deleteStatus");
            return this;


        }

        public Criteria andDeleteStatusNotEqualTo(Integer value) {
            addCriterion("delete_status <>", value, "deleteStatus");
            return this;


        }

        public Criteria andDeleteStatusGreaterThan(Integer value) {
            addCriterion("delete_status >", value, "deleteStatus");
            return this;


        }

        public Criteria andDeleteStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("delete_status >=", value, "deleteStatus");
            return this;


        }

        public Criteria andDeleteStatusLessThan(Integer value) {
            addCriterion("delete_status <", value, "deleteStatus");
            return this;


        }

        public Criteria andDeleteStatusLessThanOrEqualTo(Integer value) {
            addCriterion("delete_status <=", value, "deleteStatus");
            return this;


        }

        public Criteria andDeleteStatusIn(List values) {
            addCriterion("delete_status in", values, "deleteStatus");
            return this;


        }

        public Criteria andDeleteStatusNotIn(List values) {
            addCriterion("delete_status not in", values, "deleteStatus");
            return this;


        }

        public Criteria andDeleteStatusBetween(Integer value1, Integer value2) {
            addCriterion("delete_status between", value1, value2, "deleteStatus");
            return this;


        }

        public Criteria andDeleteStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("delete_status not between", value1, value2, "deleteStatus");
            return this;


        }

        public Criteria andWxShareUrlIsNull() {
            addCriterion("wx_share_url is null");
            return this;


        }

        public Criteria andWxShareUrlIsNotNull() {
            addCriterion("wx_share_url is not null");
            return this;


        }

        public Criteria andWxShareUrlEqualTo(String value) {
            addCriterion("wx_share_url =", value, "wxShareUrl");
            return this;


        }

        public Criteria andWxShareUrlNotEqualTo(String value) {
            addCriterion("wx_share_url <>", value, "wxShareUrl");
            return this;


        }

        public Criteria andWxShareUrlGreaterThan(String value) {
            addCriterion("wx_share_url >", value, "wxShareUrl");
            return this;


        }

        public Criteria andWxShareUrlGreaterThanOrEqualTo(String value) {
            addCriterion("wx_share_url >=", value, "wxShareUrl");
            return this;


        }

        public Criteria andWxShareUrlLessThan(String value) {
            addCriterion("wx_share_url <", value, "wxShareUrl");
            return this;


        }

        public Criteria andWxShareUrlLessThanOrEqualTo(String value) {
            addCriterion("wx_share_url <=", value, "wxShareUrl");
            return this;


        }

        public Criteria andWxShareUrlLike(String value) {
            addCriterion("wx_share_url like", value, "wxShareUrl");
            return this;


        }

        public Criteria andWxShareUrlNotLike(String value) {
            addCriterion("wx_share_url not like", value, "wxShareUrl");
            return this;


        }

        public Criteria andWxShareUrlIn(List values) {
            addCriterion("wx_share_url in", values, "wxShareUrl");
            return this;


        }

        public Criteria andWxShareUrlNotIn(List values) {
            addCriterion("wx_share_url not in", values, "wxShareUrl");
            return this;


        }

        public Criteria andWxShareUrlBetween(String value1, String value2) {
            addCriterion("wx_share_url between", value1, value2, "wxShareUrl");
            return this;


        }

        public Criteria andWxShareUrlNotBetween(String value1, String value2) {
            addCriterion("wx_share_url not between", value1, value2, "wxShareUrl");
            return this;


        }

        public Criteria andWxShareContentIsNull() {
            addCriterion("wx_share_content is null");
            return this;


        }

        public Criteria andWxShareContentIsNotNull() {
            addCriterion("wx_share_content is not null");
            return this;


        }

        public Criteria andWxShareContentEqualTo(String value) {
            addCriterion("wx_share_content =", value, "wxShareContent");
            return this;


        }

        public Criteria andWxShareContentNotEqualTo(String value) {
            addCriterion("wx_share_content <>", value, "wxShareContent");
            return this;


        }

        public Criteria andWxShareContentGreaterThan(String value) {
            addCriterion("wx_share_content >", value, "wxShareContent");
            return this;


        }

        public Criteria andWxShareContentGreaterThanOrEqualTo(String value) {
            addCriterion("wx_share_content >=", value, "wxShareContent");
            return this;


        }

        public Criteria andWxShareContentLessThan(String value) {
            addCriterion("wx_share_content <", value, "wxShareContent");
            return this;


        }

        public Criteria andWxShareContentLessThanOrEqualTo(String value) {
            addCriterion("wx_share_content <=", value, "wxShareContent");
            return this;


        }

        public Criteria andWxShareContentLike(String value) {
            addCriterion("wx_share_content like", value, "wxShareContent");
            return this;


        }

        public Criteria andWxShareContentNotLike(String value) {
            addCriterion("wx_share_content not like", value, "wxShareContent");
            return this;


        }

        public Criteria andWxShareContentIn(List values) {
            addCriterion("wx_share_content in", values, "wxShareContent");
            return this;


        }

        public Criteria andWxShareContentNotIn(List values) {
            addCriterion("wx_share_content not in", values, "wxShareContent");
            return this;


        }

        public Criteria andWxShareContentBetween(String value1, String value2) {
            addCriterion("wx_share_content between", value1, value2, "wxShareContent");
            return this;


        }

        public Criteria andWxShareContentNotBetween(String value1, String value2) {
            addCriterion("wx_share_content not between", value1, value2, "wxShareContent");
            return this;


        }

        public Criteria andGoodsCategoryIdIsNull() {
            addCriterion("goods_category_id is null");
            return this;


        }

        public Criteria andGoodsCategoryIdIsNotNull() {
            addCriterion("goods_category_id is not null");
            return this;


        }

        public Criteria andGoodsCategoryIdEqualTo(Long value) {
            addCriterion("goods_category_id =", value, "goodsCategoryId");
            return this;


        }

        public Criteria andGoodsCategoryIdNotEqualTo(Long value) {
            addCriterion("goods_category_id <>", value, "goodsCategoryId");
            return this;


        }

        public Criteria andGoodsCategoryIdGreaterThan(Long value) {
            addCriterion("goods_category_id >", value, "goodsCategoryId");
            return this;


        }

        public Criteria andGoodsCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_category_id >=", value, "goodsCategoryId");
            return this;


        }

        public Criteria andGoodsCategoryIdLessThan(Long value) {
            addCriterion("goods_category_id <", value, "goodsCategoryId");
            return this;


        }

        public Criteria andGoodsCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("goods_category_id <=", value, "goodsCategoryId");
            return this;


        }

        public Criteria andGoodsCategoryIdIn(List values) {
            addCriterion("goods_category_id in", values, "goodsCategoryId");
            return this;


        }

        public Criteria andGoodsCategoryIdNotIn(List values) {
            addCriterion("goods_category_id not in", values, "goodsCategoryId");
            return this;


        }

        public Criteria andGoodsCategoryIdBetween(Long value1, Long value2) {
            addCriterion("goods_category_id between", value1, value2, "goodsCategoryId");
            return this;


        }

        public Criteria andGoodsCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("goods_category_id not between", value1, value2, "goodsCategoryId");
            return this;


        }


    }


}
