package com.battcn.entity;

import java.util.ArrayList;
import java.util.List;

public class AgentMerLevelExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_mp_agentmerlevel
     *
     * @mbggenerated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_mp_agentmerlevel
     *
     * @mbggenerated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_mp_agentmerlevel
     *
     * @mbggenerated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_agentmerlevel
     *
     * @mbggenerated
     */
    public AgentMerLevelExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_agentmerlevel
     *
     * @mbggenerated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_agentmerlevel
     *
     * @mbggenerated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_agentmerlevel
     *
     * @mbggenerated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_agentmerlevel
     *
     * @mbggenerated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_agentmerlevel
     *
     * @mbggenerated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_agentmerlevel
     *
     * @mbggenerated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_agentmerlevel
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
     * This method corresponds to the database table t_mp_agentmerlevel
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
     * This method corresponds to the database table t_mp_agentmerlevel
     *
     * @mbggenerated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_agentmerlevel
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
     * This class corresponds to the database table t_mp_agentmerlevel
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

        public Criteria andLevelnameIsNull() {
            addCriterion("levelName is null");
            return (Criteria) this;
        }

        public Criteria andLevelnameIsNotNull() {
            addCriterion("levelName is not null");
            return (Criteria) this;
        }

        public Criteria andLevelnameEqualTo(String value) {
            addCriterion("levelName =", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameNotEqualTo(String value) {
            addCriterion("levelName <>", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameGreaterThan(String value) {
            addCriterion("levelName >", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameGreaterThanOrEqualTo(String value) {
            addCriterion("levelName >=", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameLessThan(String value) {
            addCriterion("levelName <", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameLessThanOrEqualTo(String value) {
            addCriterion("levelName <=", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameLike(String value) {
            addCriterion("levelName like", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameNotLike(String value) {
            addCriterion("levelName not like", value, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameIn(List<String> values) {
            addCriterion("levelName in", values, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameNotIn(List<String> values) {
            addCriterion("levelName not in", values, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameBetween(String value1, String value2) {
            addCriterion("levelName between", value1, value2, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevelnameNotBetween(String value1, String value2) {
            addCriterion("levelName not between", value1, value2, "levelname");
            return (Criteria) this;
        }

        public Criteria andLevellogoIsNull() {
            addCriterion("levelLogo is null");
            return (Criteria) this;
        }

        public Criteria andLevellogoIsNotNull() {
            addCriterion("levelLogo is not null");
            return (Criteria) this;
        }

        public Criteria andLevellogoEqualTo(String value) {
            addCriterion("levelLogo =", value, "levellogo");
            return (Criteria) this;
        }

        public Criteria andLevellogoNotEqualTo(String value) {
            addCriterion("levelLogo <>", value, "levellogo");
            return (Criteria) this;
        }

        public Criteria andLevellogoGreaterThan(String value) {
            addCriterion("levelLogo >", value, "levellogo");
            return (Criteria) this;
        }

        public Criteria andLevellogoGreaterThanOrEqualTo(String value) {
            addCriterion("levelLogo >=", value, "levellogo");
            return (Criteria) this;
        }

        public Criteria andLevellogoLessThan(String value) {
            addCriterion("levelLogo <", value, "levellogo");
            return (Criteria) this;
        }

        public Criteria andLevellogoLessThanOrEqualTo(String value) {
            addCriterion("levelLogo <=", value, "levellogo");
            return (Criteria) this;
        }

        public Criteria andLevellogoLike(String value) {
            addCriterion("levelLogo like", value, "levellogo");
            return (Criteria) this;
        }

        public Criteria andLevellogoNotLike(String value) {
            addCriterion("levelLogo not like", value, "levellogo");
            return (Criteria) this;
        }

        public Criteria andLevellogoIn(List<String> values) {
            addCriterion("levelLogo in", values, "levellogo");
            return (Criteria) this;
        }

        public Criteria andLevellogoNotIn(List<String> values) {
            addCriterion("levelLogo not in", values, "levellogo");
            return (Criteria) this;
        }

        public Criteria andLevellogoBetween(String value1, String value2) {
            addCriterion("levelLogo between", value1, value2, "levellogo");
            return (Criteria) this;
        }

        public Criteria andLevellogoNotBetween(String value1, String value2) {
            addCriterion("levelLogo not between", value1, value2, "levellogo");
            return (Criteria) this;
        }

        public Criteria andLevelbcardIsNull() {
            addCriterion("levelBCard is null");
            return (Criteria) this;
        }

        public Criteria andLevelbcardIsNotNull() {
            addCriterion("levelBCard is not null");
            return (Criteria) this;
        }

        public Criteria andLevelbcardEqualTo(String value) {
            addCriterion("levelBCard =", value, "levelbcard");
            return (Criteria) this;
        }

        public Criteria andLevelbcardNotEqualTo(String value) {
            addCriterion("levelBCard <>", value, "levelbcard");
            return (Criteria) this;
        }

        public Criteria andLevelbcardGreaterThan(String value) {
            addCriterion("levelBCard >", value, "levelbcard");
            return (Criteria) this;
        }

        public Criteria andLevelbcardGreaterThanOrEqualTo(String value) {
            addCriterion("levelBCard >=", value, "levelbcard");
            return (Criteria) this;
        }

        public Criteria andLevelbcardLessThan(String value) {
            addCriterion("levelBCard <", value, "levelbcard");
            return (Criteria) this;
        }

        public Criteria andLevelbcardLessThanOrEqualTo(String value) {
            addCriterion("levelBCard <=", value, "levelbcard");
            return (Criteria) this;
        }

        public Criteria andLevelbcardLike(String value) {
            addCriterion("levelBCard like", value, "levelbcard");
            return (Criteria) this;
        }

        public Criteria andLevelbcardNotLike(String value) {
            addCriterion("levelBCard not like", value, "levelbcard");
            return (Criteria) this;
        }

        public Criteria andLevelbcardIn(List<String> values) {
            addCriterion("levelBCard in", values, "levelbcard");
            return (Criteria) this;
        }

        public Criteria andLevelbcardNotIn(List<String> values) {
            addCriterion("levelBCard not in", values, "levelbcard");
            return (Criteria) this;
        }

        public Criteria andLevelbcardBetween(String value1, String value2) {
            addCriterion("levelBCard between", value1, value2, "levelbcard");
            return (Criteria) this;
        }

        public Criteria andLevelbcardNotBetween(String value1, String value2) {
            addCriterion("levelBCard not between", value1, value2, "levelbcard");
            return (Criteria) this;
        }

        public Criteria andLevelweightIsNull() {
            addCriterion("levelWeight is null");
            return (Criteria) this;
        }

        public Criteria andLevelweightIsNotNull() {
            addCriterion("levelWeight is not null");
            return (Criteria) this;
        }

        public Criteria andLevelweightEqualTo(Long value) {
            addCriterion("levelWeight =", value, "levelweight");
            return (Criteria) this;
        }

        public Criteria andLevelweightNotEqualTo(Long value) {
            addCriterion("levelWeight <>", value, "levelweight");
            return (Criteria) this;
        }

        public Criteria andLevelweightGreaterThan(Long value) {
            addCriterion("levelWeight >", value, "levelweight");
            return (Criteria) this;
        }

        public Criteria andLevelweightGreaterThanOrEqualTo(Long value) {
            addCriterion("levelWeight >=", value, "levelweight");
            return (Criteria) this;
        }

        public Criteria andLevelweightLessThan(Long value) {
            addCriterion("levelWeight <", value, "levelweight");
            return (Criteria) this;
        }

        public Criteria andLevelweightLessThanOrEqualTo(Long value) {
            addCriterion("levelWeight <=", value, "levelweight");
            return (Criteria) this;
        }

        public Criteria andLevelweightIn(List<Long> values) {
            addCriterion("levelWeight in", values, "levelweight");
            return (Criteria) this;
        }

        public Criteria andLevelweightNotIn(List<Long> values) {
            addCriterion("levelWeight not in", values, "levelweight");
            return (Criteria) this;
        }

        public Criteria andLevelweightBetween(Long value1, Long value2) {
            addCriterion("levelWeight between", value1, value2, "levelweight");
            return (Criteria) this;
        }

        public Criteria andLevelweightNotBetween(Long value1, Long value2) {
            addCriterion("levelWeight not between", value1, value2, "levelweight");
            return (Criteria) this;
        }

        public Criteria andMerlevelIsNull() {
            addCriterion("merLevel is null");
            return (Criteria) this;
        }

        public Criteria andMerlevelIsNotNull() {
            addCriterion("merLevel is not null");
            return (Criteria) this;
        }

        public Criteria andMerlevelEqualTo(String value) {
            addCriterion("merLevel =", value, "merlevel");
            return (Criteria) this;
        }

        public Criteria andMerlevelNotEqualTo(String value) {
            addCriterion("merLevel <>", value, "merlevel");
            return (Criteria) this;
        }

        public Criteria andMerlevelGreaterThan(String value) {
            addCriterion("merLevel >", value, "merlevel");
            return (Criteria) this;
        }

        public Criteria andMerlevelGreaterThanOrEqualTo(String value) {
            addCriterion("merLevel >=", value, "merlevel");
            return (Criteria) this;
        }

        public Criteria andMerlevelLessThan(String value) {
            addCriterion("merLevel <", value, "merlevel");
            return (Criteria) this;
        }

        public Criteria andMerlevelLessThanOrEqualTo(String value) {
            addCriterion("merLevel <=", value, "merlevel");
            return (Criteria) this;
        }

        public Criteria andMerlevelLike(String value) {
            addCriterion("merLevel like", value, "merlevel");
            return (Criteria) this;
        }

        public Criteria andMerlevelNotLike(String value) {
            addCriterion("merLevel not like", value, "merlevel");
            return (Criteria) this;
        }

        public Criteria andMerlevelIn(List<String> values) {
            addCriterion("merLevel in", values, "merlevel");
            return (Criteria) this;
        }

        public Criteria andMerlevelNotIn(List<String> values) {
            addCriterion("merLevel not in", values, "merlevel");
            return (Criteria) this;
        }

        public Criteria andMerlevelBetween(String value1, String value2) {
            addCriterion("merLevel between", value1, value2, "merlevel");
            return (Criteria) this;
        }

        public Criteria andMerlevelNotBetween(String value1, String value2) {
            addCriterion("merLevel not between", value1, value2, "merlevel");
            return (Criteria) this;
        }

        public Criteria andAgentornotIsNull() {
            addCriterion("agentOrNot is null");
            return (Criteria) this;
        }

        public Criteria andAgentornotIsNotNull() {
            addCriterion("agentOrNot is not null");
            return (Criteria) this;
        }

        public Criteria andAgentornotEqualTo(String value) {
            addCriterion("agentOrNot =", value, "agentornot");
            return (Criteria) this;
        }

        public Criteria andAgentornotNotEqualTo(String value) {
            addCriterion("agentOrNot <>", value, "agentornot");
            return (Criteria) this;
        }

        public Criteria andAgentornotGreaterThan(String value) {
            addCriterion("agentOrNot >", value, "agentornot");
            return (Criteria) this;
        }

        public Criteria andAgentornotGreaterThanOrEqualTo(String value) {
            addCriterion("agentOrNot >=", value, "agentornot");
            return (Criteria) this;
        }

        public Criteria andAgentornotLessThan(String value) {
            addCriterion("agentOrNot <", value, "agentornot");
            return (Criteria) this;
        }

        public Criteria andAgentornotLessThanOrEqualTo(String value) {
            addCriterion("agentOrNot <=", value, "agentornot");
            return (Criteria) this;
        }

        public Criteria andAgentornotLike(String value) {
            addCriterion("agentOrNot like", value, "agentornot");
            return (Criteria) this;
        }

        public Criteria andAgentornotNotLike(String value) {
            addCriterion("agentOrNot not like", value, "agentornot");
            return (Criteria) this;
        }

        public Criteria andAgentornotIn(List<String> values) {
            addCriterion("agentOrNot in", values, "agentornot");
            return (Criteria) this;
        }

        public Criteria andAgentornotNotIn(List<String> values) {
            addCriterion("agentOrNot not in", values, "agentornot");
            return (Criteria) this;
        }

        public Criteria andAgentornotBetween(String value1, String value2) {
            addCriterion("agentOrNot between", value1, value2, "agentornot");
            return (Criteria) this;
        }

        public Criteria andAgentornotNotBetween(String value1, String value2) {
            addCriterion("agentOrNot not between", value1, value2, "agentornot");
            return (Criteria) this;
        }

        public Criteria andAgentstatusIsNull() {
            addCriterion("agentStatus is null");
            return (Criteria) this;
        }

        public Criteria andAgentstatusIsNotNull() {
            addCriterion("agentStatus is not null");
            return (Criteria) this;
        }

        public Criteria andAgentstatusEqualTo(String value) {
            addCriterion("agentStatus =", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusNotEqualTo(String value) {
            addCriterion("agentStatus <>", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusGreaterThan(String value) {
            addCriterion("agentStatus >", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusGreaterThanOrEqualTo(String value) {
            addCriterion("agentStatus >=", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusLessThan(String value) {
            addCriterion("agentStatus <", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusLessThanOrEqualTo(String value) {
            addCriterion("agentStatus <=", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusLike(String value) {
            addCriterion("agentStatus like", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusNotLike(String value) {
            addCriterion("agentStatus not like", value, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusIn(List<String> values) {
            addCriterion("agentStatus in", values, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusNotIn(List<String> values) {
            addCriterion("agentStatus not in", values, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusBetween(String value1, String value2) {
            addCriterion("agentStatus between", value1, value2, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentstatusNotBetween(String value1, String value2) {
            addCriterion("agentStatus not between", value1, value2, "agentstatus");
            return (Criteria) this;
        }

        public Criteria andAgentidIsNull() {
            addCriterion("agentId is null");
            return (Criteria) this;
        }

        public Criteria andAgentidIsNotNull() {
            addCriterion("agentId is not null");
            return (Criteria) this;
        }

        public Criteria andAgentidEqualTo(String value) {
            addCriterion("agentId =", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidNotEqualTo(String value) {
            addCriterion("agentId <>", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidGreaterThan(String value) {
            addCriterion("agentId >", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidGreaterThanOrEqualTo(String value) {
            addCriterion("agentId >=", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidLessThan(String value) {
            addCriterion("agentId <", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidLessThanOrEqualTo(String value) {
            addCriterion("agentId <=", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidLike(String value) {
            addCriterion("agentId like", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidNotLike(String value) {
            addCriterion("agentId not like", value, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidIn(List<String> values) {
            addCriterion("agentId in", values, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidNotIn(List<String> values) {
            addCriterion("agentId not in", values, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidBetween(String value1, String value2) {
            addCriterion("agentId between", value1, value2, "agentid");
            return (Criteria) this;
        }

        public Criteria andAgentidNotBetween(String value1, String value2) {
            addCriterion("agentId not between", value1, value2, "agentid");
            return (Criteria) this;
        }

        public Criteria andShowornotIsNull() {
            addCriterion("showOrNot is null");
            return (Criteria) this;
        }

        public Criteria andShowornotIsNotNull() {
            addCriterion("showOrNot is not null");
            return (Criteria) this;
        }

        public Criteria andShowornotEqualTo(String value) {
            addCriterion("showOrNot =", value, "showornot");
            return (Criteria) this;
        }

        public Criteria andShowornotNotEqualTo(String value) {
            addCriterion("showOrNot <>", value, "showornot");
            return (Criteria) this;
        }

        public Criteria andShowornotGreaterThan(String value) {
            addCriterion("showOrNot >", value, "showornot");
            return (Criteria) this;
        }

        public Criteria andShowornotGreaterThanOrEqualTo(String value) {
            addCriterion("showOrNot >=", value, "showornot");
            return (Criteria) this;
        }

        public Criteria andShowornotLessThan(String value) {
            addCriterion("showOrNot <", value, "showornot");
            return (Criteria) this;
        }

        public Criteria andShowornotLessThanOrEqualTo(String value) {
            addCriterion("showOrNot <=", value, "showornot");
            return (Criteria) this;
        }

        public Criteria andShowornotLike(String value) {
            addCriterion("showOrNot like", value, "showornot");
            return (Criteria) this;
        }

        public Criteria andShowornotNotLike(String value) {
            addCriterion("showOrNot not like", value, "showornot");
            return (Criteria) this;
        }

        public Criteria andShowornotIn(List<String> values) {
            addCriterion("showOrNot in", values, "showornot");
            return (Criteria) this;
        }

        public Criteria andShowornotNotIn(List<String> values) {
            addCriterion("showOrNot not in", values, "showornot");
            return (Criteria) this;
        }

        public Criteria andShowornotBetween(String value1, String value2) {
            addCriterion("showOrNot between", value1, value2, "showornot");
            return (Criteria) this;
        }

        public Criteria andShowornotNotBetween(String value1, String value2) {
            addCriterion("showOrNot not between", value1, value2, "showornot");
            return (Criteria) this;
        }

        public Criteria andFuncexplainIsNull() {
            addCriterion("funcExplain is null");
            return (Criteria) this;
        }

        public Criteria andFuncexplainIsNotNull() {
            addCriterion("funcExplain is not null");
            return (Criteria) this;
        }

        public Criteria andFuncexplainEqualTo(String value) {
            addCriterion("funcExplain =", value, "funcexplain");
            return (Criteria) this;
        }

        public Criteria andFuncexplainNotEqualTo(String value) {
            addCriterion("funcExplain <>", value, "funcexplain");
            return (Criteria) this;
        }

        public Criteria andFuncexplainGreaterThan(String value) {
            addCriterion("funcExplain >", value, "funcexplain");
            return (Criteria) this;
        }

        public Criteria andFuncexplainGreaterThanOrEqualTo(String value) {
            addCriterion("funcExplain >=", value, "funcexplain");
            return (Criteria) this;
        }

        public Criteria andFuncexplainLessThan(String value) {
            addCriterion("funcExplain <", value, "funcexplain");
            return (Criteria) this;
        }

        public Criteria andFuncexplainLessThanOrEqualTo(String value) {
            addCriterion("funcExplain <=", value, "funcexplain");
            return (Criteria) this;
        }

        public Criteria andFuncexplainLike(String value) {
            addCriterion("funcExplain like", value, "funcexplain");
            return (Criteria) this;
        }

        public Criteria andFuncexplainNotLike(String value) {
            addCriterion("funcExplain not like", value, "funcexplain");
            return (Criteria) this;
        }

        public Criteria andFuncexplainIn(List<String> values) {
            addCriterion("funcExplain in", values, "funcexplain");
            return (Criteria) this;
        }

        public Criteria andFuncexplainNotIn(List<String> values) {
            addCriterion("funcExplain not in", values, "funcexplain");
            return (Criteria) this;
        }

        public Criteria andFuncexplainBetween(String value1, String value2) {
            addCriterion("funcExplain between", value1, value2, "funcexplain");
            return (Criteria) this;
        }

        public Criteria andFuncexplainNotBetween(String value1, String value2) {
            addCriterion("funcExplain not between", value1, value2, "funcexplain");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleIsNull() {
            addCriterion("usableModule is null");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleIsNotNull() {
            addCriterion("usableModule is not null");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleEqualTo(String value) {
            addCriterion("usableModule =", value, "usablemodule");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleNotEqualTo(String value) {
            addCriterion("usableModule <>", value, "usablemodule");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleGreaterThan(String value) {
            addCriterion("usableModule >", value, "usablemodule");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleGreaterThanOrEqualTo(String value) {
            addCriterion("usableModule >=", value, "usablemodule");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleLessThan(String value) {
            addCriterion("usableModule <", value, "usablemodule");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleLessThanOrEqualTo(String value) {
            addCriterion("usableModule <=", value, "usablemodule");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleLike(String value) {
            addCriterion("usableModule like", value, "usablemodule");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleNotLike(String value) {
            addCriterion("usableModule not like", value, "usablemodule");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleIn(List<String> values) {
            addCriterion("usableModule in", values, "usablemodule");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleNotIn(List<String> values) {
            addCriterion("usableModule not in", values, "usablemodule");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleBetween(String value1, String value2) {
            addCriterion("usableModule between", value1, value2, "usablemodule");
            return (Criteria) this;
        }

        public Criteria andUsablemoduleNotBetween(String value1, String value2) {
            addCriterion("usableModule not between", value1, value2, "usablemodule");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_mp_agentmerlevel
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
     * This class corresponds to the database table t_mp_agentmerlevel
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