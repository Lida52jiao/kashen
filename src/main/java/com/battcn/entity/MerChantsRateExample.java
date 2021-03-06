package com.battcn.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MerChantsRateExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    public MerChantsRateExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMertypeIsNull() {
            addCriterion("merType is null");
            return (Criteria) this;
        }

        public Criteria andMertypeIsNotNull() {
            addCriterion("merType is not null");
            return (Criteria) this;
        }

        public Criteria andMertypeEqualTo(String value) {
            addCriterion("merType =", value, "mertype");
            return (Criteria) this;
        }

        public Criteria andMertypeNotEqualTo(String value) {
            addCriterion("merType <>", value, "mertype");
            return (Criteria) this;
        }

        public Criteria andMertypeGreaterThan(String value) {
            addCriterion("merType >", value, "mertype");
            return (Criteria) this;
        }

        public Criteria andMertypeGreaterThanOrEqualTo(String value) {
            addCriterion("merType >=", value, "mertype");
            return (Criteria) this;
        }

        public Criteria andMertypeLessThan(String value) {
            addCriterion("merType <", value, "mertype");
            return (Criteria) this;
        }

        public Criteria andMertypeLessThanOrEqualTo(String value) {
            addCriterion("merType <=", value, "mertype");
            return (Criteria) this;
        }

        public Criteria andMertypeLike(String value) {
            addCriterion("merType like", value, "mertype");
            return (Criteria) this;
        }

        public Criteria andMertypeNotLike(String value) {
            addCriterion("merType not like", value, "mertype");
            return (Criteria) this;
        }

        public Criteria andMertypeIn(List<String> values) {
            addCriterion("merType in", values, "mertype");
            return (Criteria) this;
        }

        public Criteria andMertypeNotIn(List<String> values) {
            addCriterion("merType not in", values, "mertype");
            return (Criteria) this;
        }

        public Criteria andMertypeBetween(String value1, String value2) {
            addCriterion("merType between", value1, value2, "mertype");
            return (Criteria) this;
        }

        public Criteria andMertypeNotBetween(String value1, String value2) {
            addCriterion("merType not between", value1, value2, "mertype");
            return (Criteria) this;
        }

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(BigDecimal value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(BigDecimal value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(BigDecimal value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(BigDecimal value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<BigDecimal> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<BigDecimal> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andD0feeIsNull() {
            addCriterion("d0Fee is null");
            return (Criteria) this;
        }

        public Criteria andD0feeIsNotNull() {
            addCriterion("d0Fee is not null");
            return (Criteria) this;
        }

        public Criteria andD0feeEqualTo(Long value) {
            addCriterion("d0Fee =", value, "d0fee");
            return (Criteria) this;
        }

        public Criteria andD0feeNotEqualTo(Long value) {
            addCriterion("d0Fee <>", value, "d0fee");
            return (Criteria) this;
        }

        public Criteria andD0feeGreaterThan(Long value) {
            addCriterion("d0Fee >", value, "d0fee");
            return (Criteria) this;
        }

        public Criteria andD0feeGreaterThanOrEqualTo(Long value) {
            addCriterion("d0Fee >=", value, "d0fee");
            return (Criteria) this;
        }

        public Criteria andD0feeLessThan(Long value) {
            addCriterion("d0Fee <", value, "d0fee");
            return (Criteria) this;
        }

        public Criteria andD0feeLessThanOrEqualTo(Long value) {
            addCriterion("d0Fee <=", value, "d0fee");
            return (Criteria) this;
        }

        public Criteria andD0feeIn(List<Long> values) {
            addCriterion("d0Fee in", values, "d0fee");
            return (Criteria) this;
        }

        public Criteria andD0feeNotIn(List<Long> values) {
            addCriterion("d0Fee not in", values, "d0fee");
            return (Criteria) this;
        }

        public Criteria andD0feeBetween(Long value1, Long value2) {
            addCriterion("d0Fee between", value1, value2, "d0fee");
            return (Criteria) this;
        }

        public Criteria andD0feeNotBetween(Long value1, Long value2) {
            addCriterion("d0Fee not between", value1, value2, "d0fee");
            return (Criteria) this;
        }

        public Criteria andAislecodeIsNull() {
            addCriterion("aisleCode is null");
            return (Criteria) this;
        }

        public Criteria andAislecodeIsNotNull() {
            addCriterion("aisleCode is not null");
            return (Criteria) this;
        }

        public Criteria andAislecodeEqualTo(String value) {
            addCriterion("aisleCode =", value, "aislecode");
            return (Criteria) this;
        }

        public Criteria andAislecodeNotEqualTo(String value) {
            addCriterion("aisleCode <>", value, "aislecode");
            return (Criteria) this;
        }

        public Criteria andAislecodeGreaterThan(String value) {
            addCriterion("aisleCode >", value, "aislecode");
            return (Criteria) this;
        }

        public Criteria andAislecodeGreaterThanOrEqualTo(String value) {
            addCriterion("aisleCode >=", value, "aislecode");
            return (Criteria) this;
        }

        public Criteria andAislecodeLessThan(String value) {
            addCriterion("aisleCode <", value, "aislecode");
            return (Criteria) this;
        }

        public Criteria andAislecodeLessThanOrEqualTo(String value) {
            addCriterion("aisleCode <=", value, "aislecode");
            return (Criteria) this;
        }

        public Criteria andAislecodeLike(String value) {
            addCriterion("aisleCode like", value, "aislecode");
            return (Criteria) this;
        }

        public Criteria andAislecodeNotLike(String value) {
            addCriterion("aisleCode not like", value, "aislecode");
            return (Criteria) this;
        }

        public Criteria andAislecodeIn(List<String> values) {
            addCriterion("aisleCode in", values, "aislecode");
            return (Criteria) this;
        }

        public Criteria andAislecodeNotIn(List<String> values) {
            addCriterion("aisleCode not in", values, "aislecode");
            return (Criteria) this;
        }

        public Criteria andAislecodeBetween(String value1, String value2) {
            addCriterion("aisleCode between", value1, value2, "aislecode");
            return (Criteria) this;
        }

        public Criteria andAislecodeNotBetween(String value1, String value2) {
            addCriterion("aisleCode not between", value1, value2, "aislecode");
            return (Criteria) this;
        }

        public Criteria andAppidIsNull() {
            addCriterion("appId is null");
            return (Criteria) this;
        }

        public Criteria andAppidIsNotNull() {
            addCriterion("appId is not null");
            return (Criteria) this;
        }

        public Criteria andAppidEqualTo(String value) {
            addCriterion("appId =", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotEqualTo(String value) {
            addCriterion("appId <>", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThan(String value) {
            addCriterion("appId >", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThanOrEqualTo(String value) {
            addCriterion("appId >=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThan(String value) {
            addCriterion("appId <", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThanOrEqualTo(String value) {
            addCriterion("appId <=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLike(String value) {
            addCriterion("appId like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotLike(String value) {
            addCriterion("appId not like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidIn(List<String> values) {
            addCriterion("appId in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotIn(List<String> values) {
            addCriterion("appId not in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidBetween(String value1, String value2) {
            addCriterion("appId between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotBetween(String value1, String value2) {
            addCriterion("appId not between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentIsNull() {
            addCriterion("isRepayment is null");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentIsNotNull() {
            addCriterion("isRepayment is not null");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentEqualTo(String value) {
            addCriterion("isRepayment =", value, "isrepayment");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentNotEqualTo(String value) {
            addCriterion("isRepayment <>", value, "isrepayment");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentGreaterThan(String value) {
            addCriterion("isRepayment >", value, "isrepayment");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentGreaterThanOrEqualTo(String value) {
            addCriterion("isRepayment >=", value, "isrepayment");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentLessThan(String value) {
            addCriterion("isRepayment <", value, "isrepayment");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentLessThanOrEqualTo(String value) {
            addCriterion("isRepayment <=", value, "isrepayment");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentLike(String value) {
            addCriterion("isRepayment like", value, "isrepayment");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentNotLike(String value) {
            addCriterion("isRepayment not like", value, "isrepayment");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentIn(List<String> values) {
            addCriterion("isRepayment in", values, "isrepayment");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentNotIn(List<String> values) {
            addCriterion("isRepayment not in", values, "isrepayment");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentBetween(String value1, String value2) {
            addCriterion("isRepayment between", value1, value2, "isrepayment");
            return (Criteria) this;
        }

        public Criteria andIsrepaymentNotBetween(String value1, String value2) {
            addCriterion("isRepayment not between", value1, value2, "isrepayment");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_mp_merchantsrate
     *
     * @mbggenerated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}