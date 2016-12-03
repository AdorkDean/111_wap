package com.rc.portal.vo;



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rc.app.framework.webapp.model.BaseModel;

public class TPrescriptionExample  extends BaseModel{

    protected String orderByClause;

    protected List oredCriteria;

    public TPrescriptionExample() {
        oredCriteria = new ArrayList();


    }

    protected TPrescriptionExample(TPrescriptionExample example) {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return this;


        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return this;


        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return this;


        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return this;


        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return this;


        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return this;


        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return this;


        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return this;


        }

        public Criteria andUserIdIn(List values) {
            addCriterion("user_id in", values, "userId");
            return this;


        }

        public Criteria andUserIdNotIn(List values) {
            addCriterion("user_id not in", values, "userId");
            return this;


        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return this;


        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return this;


        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return this;


        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return this;


        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return this;


        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return this;


        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return this;


        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return this;


        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return this;


        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return this;


        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return this;


        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return this;


        }

        public Criteria andUserNameIn(List values) {
            addCriterion("user_name in", values, "userName");
            return this;


        }

        public Criteria andUserNameNotIn(List values) {
            addCriterion("user_name not in", values, "userName");
            return this;


        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return this;


        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return this;


        }

        public Criteria andDiseaseDescripIsNull() {
            addCriterion("disease_descrip is null");
            return this;


        }

        public Criteria andDiseaseDescripIsNotNull() {
            addCriterion("disease_descrip is not null");
            return this;


        }

        public Criteria andDiseaseDescripEqualTo(String value) {
            addCriterion("disease_descrip =", value, "diseaseDescrip");
            return this;


        }

        public Criteria andDiseaseDescripNotEqualTo(String value) {
            addCriterion("disease_descrip <>", value, "diseaseDescrip");
            return this;


        }

        public Criteria andDiseaseDescripGreaterThan(String value) {
            addCriterion("disease_descrip >", value, "diseaseDescrip");
            return this;


        }

        public Criteria andDiseaseDescripGreaterThanOrEqualTo(String value) {
            addCriterion("disease_descrip >=", value, "diseaseDescrip");
            return this;


        }

        public Criteria andDiseaseDescripLessThan(String value) {
            addCriterion("disease_descrip <", value, "diseaseDescrip");
            return this;


        }

        public Criteria andDiseaseDescripLessThanOrEqualTo(String value) {
            addCriterion("disease_descrip <=", value, "diseaseDescrip");
            return this;


        }

        public Criteria andDiseaseDescripLike(String value) {
            addCriterion("disease_descrip like", value, "diseaseDescrip");
            return this;


        }

        public Criteria andDiseaseDescripNotLike(String value) {
            addCriterion("disease_descrip not like", value, "diseaseDescrip");
            return this;


        }

        public Criteria andDiseaseDescripIn(List values) {
            addCriterion("disease_descrip in", values, "diseaseDescrip");
            return this;


        }

        public Criteria andDiseaseDescripNotIn(List values) {
            addCriterion("disease_descrip not in", values, "diseaseDescrip");
            return this;


        }

        public Criteria andDiseaseDescripBetween(String value1, String value2) {
            addCriterion("disease_descrip between", value1, value2, "diseaseDescrip");
            return this;


        }

        public Criteria andDiseaseDescripNotBetween(String value1, String value2) {
            addCriterion("disease_descrip not between", value1, value2, "diseaseDescrip");
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

        public Criteria andCallUsernameIsNull() {
            addCriterion("call_username is null");
            return this;


        }

        public Criteria andCallUsernameIsNotNull() {
            addCriterion("call_username is not null");
            return this;


        }

        public Criteria andCallUsernameEqualTo(String value) {
            addCriterion("call_username =", value, "callUsername");
            return this;


        }

        public Criteria andCallUsernameNotEqualTo(String value) {
            addCriterion("call_username <>", value, "callUsername");
            return this;


        }

        public Criteria andCallUsernameGreaterThan(String value) {
            addCriterion("call_username >", value, "callUsername");
            return this;


        }

        public Criteria andCallUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("call_username >=", value, "callUsername");
            return this;


        }

        public Criteria andCallUsernameLessThan(String value) {
            addCriterion("call_username <", value, "callUsername");
            return this;


        }

        public Criteria andCallUsernameLessThanOrEqualTo(String value) {
            addCriterion("call_username <=", value, "callUsername");
            return this;


        }

        public Criteria andCallUsernameLike(String value) {
            addCriterion("call_username like", value, "callUsername");
            return this;


        }

        public Criteria andCallUsernameNotLike(String value) {
            addCriterion("call_username not like", value, "callUsername");
            return this;


        }

        public Criteria andCallUsernameIn(List values) {
            addCriterion("call_username in", values, "callUsername");
            return this;


        }

        public Criteria andCallUsernameNotIn(List values) {
            addCriterion("call_username not in", values, "callUsername");
            return this;


        }

        public Criteria andCallUsernameBetween(String value1, String value2) {
            addCriterion("call_username between", value1, value2, "callUsername");
            return this;


        }

        public Criteria andCallUsernameNotBetween(String value1, String value2) {
            addCriterion("call_username not between", value1, value2, "callUsername");
            return this;


        }

        public Criteria andCallTimeIsNull() {
            addCriterion("call_time is null");
            return this;


        }

        public Criteria andCallTimeIsNotNull() {
            addCriterion("call_time is not null");
            return this;


        }

        public Criteria andCallTimeEqualTo(Date value) {
            addCriterion("call_time =", value, "callTime");
            return this;


        }

        public Criteria andCallTimeNotEqualTo(Date value) {
            addCriterion("call_time <>", value, "callTime");
            return this;


        }

        public Criteria andCallTimeGreaterThan(Date value) {
            addCriterion("call_time >", value, "callTime");
            return this;


        }

        public Criteria andCallTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("call_time >=", value, "callTime");
            return this;


        }

        public Criteria andCallTimeLessThan(Date value) {
            addCriterion("call_time <", value, "callTime");
            return this;


        }

        public Criteria andCallTimeLessThanOrEqualTo(Date value) {
            addCriterion("call_time <=", value, "callTime");
            return this;


        }

        public Criteria andCallTimeIn(List values) {
            addCriterion("call_time in", values, "callTime");
            return this;


        }

        public Criteria andCallTimeNotIn(List values) {
            addCriterion("call_time not in", values, "callTime");
            return this;


        }

        public Criteria andCallTimeBetween(Date value1, Date value2) {
            addCriterion("call_time between", value1, value2, "callTime");
            return this;


        }

        public Criteria andCallTimeNotBetween(Date value1, Date value2) {
            addCriterion("call_time not between", value1, value2, "callTime");
            return this;


        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return this;


        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return this;


        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return this;


        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return this;


        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return this;


        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return this;


        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return this;


        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return this;


        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return this;


        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return this;


        }

        public Criteria andPhoneIn(List values) {
            addCriterion("phone in", values, "phone");
            return this;


        }

        public Criteria andPhoneNotIn(List values) {
            addCriterion("phone not in", values, "phone");
            return this;


        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return this;


        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return this;


        }

        public Criteria andOrderSnIsNull() {
            addCriterion("order_sn is null");
            return this;


        }

        public Criteria andOrderSnIsNotNull() {
            addCriterion("order_sn is not null");
            return this;


        }

        public Criteria andOrderSnEqualTo(String value) {
            addCriterion("order_sn =", value, "orderSn");
            return this;


        }

        public Criteria andOrderSnNotEqualTo(String value) {
            addCriterion("order_sn <>", value, "orderSn");
            return this;


        }

        public Criteria andOrderSnGreaterThan(String value) {
            addCriterion("order_sn >", value, "orderSn");
            return this;


        }

        public Criteria andOrderSnGreaterThanOrEqualTo(String value) {
            addCriterion("order_sn >=", value, "orderSn");
            return this;


        }

        public Criteria andOrderSnLessThan(String value) {
            addCriterion("order_sn <", value, "orderSn");
            return this;


        }

        public Criteria andOrderSnLessThanOrEqualTo(String value) {
            addCriterion("order_sn <=", value, "orderSn");
            return this;


        }

        public Criteria andOrderSnLike(String value) {
            addCriterion("order_sn like", value, "orderSn");
            return this;


        }

        public Criteria andOrderSnNotLike(String value) {
            addCriterion("order_sn not like", value, "orderSn");
            return this;


        }

        public Criteria andOrderSnIn(List values) {
            addCriterion("order_sn in", values, "orderSn");
            return this;


        }

        public Criteria andOrderSnNotIn(List values) {
            addCriterion("order_sn not in", values, "orderSn");
            return this;


        }

        public Criteria andOrderSnBetween(String value1, String value2) {
            addCriterion("order_sn between", value1, value2, "orderSn");
            return this;


        }

        public Criteria andOrderSnNotBetween(String value1, String value2) {
            addCriterion("order_sn not between", value1, value2, "orderSn");
            return this;


        }

        public Criteria andImageUrlIsNull() {
            addCriterion("image_url is null");
            return this;


        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("image_url is not null");
            return this;


        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("image_url =", value, "imageUrl");
            return this;


        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("image_url <>", value, "imageUrl");
            return this;


        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("image_url >", value, "imageUrl");
            return this;


        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("image_url >=", value, "imageUrl");
            return this;


        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("image_url <", value, "imageUrl");
            return this;


        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("image_url <=", value, "imageUrl");
            return this;


        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("image_url like", value, "imageUrl");
            return this;


        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("image_url not like", value, "imageUrl");
            return this;


        }

        public Criteria andImageUrlIn(List values) {
            addCriterion("image_url in", values, "imageUrl");
            return this;


        }

        public Criteria andImageUrlNotIn(List values) {
            addCriterion("image_url not in", values, "imageUrl");
            return this;


        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("image_url between", value1, value2, "imageUrl");
            return this;


        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("image_url not between", value1, value2, "imageUrl");
            return this;


        }

        public Criteria andUploadTimeIsNull() {
            addCriterion("upload_time is null");
            return this;


        }

        public Criteria andUploadTimeIsNotNull() {
            addCriterion("upload_time is not null");
            return this;


        }

        public Criteria andUploadTimeEqualTo(Date value) {
            addCriterion("upload_time =", value, "uploadTime");
            return this;


        }

        public Criteria andUploadTimeNotEqualTo(Date value) {
            addCriterion("upload_time <>", value, "uploadTime");
            return this;


        }

        public Criteria andUploadTimeGreaterThan(Date value) {
            addCriterion("upload_time >", value, "uploadTime");
            return this;


        }

        public Criteria andUploadTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("upload_time >=", value, "uploadTime");
            return this;


        }

        public Criteria andUploadTimeLessThan(Date value) {
            addCriterion("upload_time <", value, "uploadTime");
            return this;


        }

        public Criteria andUploadTimeLessThanOrEqualTo(Date value) {
            addCriterion("upload_time <=", value, "uploadTime");
            return this;


        }

        public Criteria andUploadTimeIn(List values) {
            addCriterion("upload_time in", values, "uploadTime");
            return this;


        }

        public Criteria andUploadTimeNotIn(List values) {
            addCriterion("upload_time not in", values, "uploadTime");
            return this;


        }

        public Criteria andUploadTimeBetween(Date value1, Date value2) {
            addCriterion("upload_time between", value1, value2, "uploadTime");
            return this;


        }

        public Criteria andUploadTimeNotBetween(Date value1, Date value2) {
            addCriterion("upload_time not between", value1, value2, "uploadTime");
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

        public Criteria andIfhelpIsNull() {
            addCriterion("ifhelp is null");
            return this;


        }

        public Criteria andIfhelpIsNotNull() {
            addCriterion("ifhelp is not null");
            return this;


        }

        public Criteria andIfhelpEqualTo(Integer value) {
            addCriterion("ifhelp =", value, "ifhelp");
            return this;


        }

        public Criteria andIfhelpNotEqualTo(Integer value) {
            addCriterion("ifhelp <>", value, "ifhelp");
            return this;


        }

        public Criteria andIfhelpGreaterThan(Integer value) {
            addCriterion("ifhelp >", value, "ifhelp");
            return this;


        }

        public Criteria andIfhelpGreaterThanOrEqualTo(Integer value) {
            addCriterion("ifhelp >=", value, "ifhelp");
            return this;


        }

        public Criteria andIfhelpLessThan(Integer value) {
            addCriterion("ifhelp <", value, "ifhelp");
            return this;


        }

        public Criteria andIfhelpLessThanOrEqualTo(Integer value) {
            addCriterion("ifhelp <=", value, "ifhelp");
            return this;


        }

        public Criteria andIfhelpIn(List values) {
            addCriterion("ifhelp in", values, "ifhelp");
            return this;


        }

        public Criteria andIfhelpNotIn(List values) {
            addCriterion("ifhelp not in", values, "ifhelp");
            return this;


        }

        public Criteria andIfhelpBetween(Integer value1, Integer value2) {
            addCriterion("ifhelp between", value1, value2, "ifhelp");
            return this;


        }

        public Criteria andIfhelpNotBetween(Integer value1, Integer value2) {
            addCriterion("ifhelp not between", value1, value2, "ifhelp");
            return this;


        }


    }


}
