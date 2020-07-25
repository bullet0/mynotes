package arbitration;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ArbitrationJobExample {
  protected String curpage;
  protected String pagesize;

  public String getCurpage() {
    return curpage;
  }

  public void setCurpage(String curpage) {
    this.curpage = curpage;
  }

  public String getPagesize() {
    return pagesize;
  }

  public void setPagesize(String pagesize) {
    this.pagesize = pagesize;
  }

  protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ArbitrationJobExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
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
        orderByClause = null;
        distinct = false;
    }

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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("del_flag like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("del_flag not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andBeginDayIsNull() {
            addCriterion("begin_day is null");
            return (Criteria) this;
        }

        public Criteria andBeginDayIsNotNull() {
            addCriterion("begin_day is not null");
            return (Criteria) this;
        }

        public Criteria andBeginDayEqualTo(String value) {
            addCriterion("begin_day =", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria andBeginDayNotEqualTo(String value) {
            addCriterion("begin_day <>", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria andBeginDayGreaterThan(String value) {
            addCriterion("begin_day >", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria andBeginDayGreaterThanOrEqualTo(String value) {
            addCriterion("begin_day >=", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria andBeginDayLessThan(String value) {
            addCriterion("begin_day <", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria andBeginDayLessThanOrEqualTo(String value) {
            addCriterion("begin_day <=", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria andBeginDayLike(String value) {
            addCriterion("begin_day like", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria andBeginDayNotLike(String value) {
            addCriterion("begin_day not like", value, "beginDay");
            return (Criteria) this;
        }

        public Criteria andBeginDayIn(List<String> values) {
            addCriterion("begin_day in", values, "beginDay");
            return (Criteria) this;
        }

        public Criteria andBeginDayNotIn(List<String> values) {
            addCriterion("begin_day not in", values, "beginDay");
            return (Criteria) this;
        }

        public Criteria andBeginDayBetween(String value1, String value2) {
            addCriterion("begin_day between", value1, value2, "beginDay");
            return (Criteria) this;
        }

        public Criteria andBeginDayNotBetween(String value1, String value2) {
            addCriterion("begin_day not between", value1, value2, "beginDay");
            return (Criteria) this;
        }

        public Criteria andBeginMonthIsNull() {
            addCriterion("begin_month is null");
            return (Criteria) this;
        }

        public Criteria andBeginMonthIsNotNull() {
            addCriterion("begin_month is not null");
            return (Criteria) this;
        }

        public Criteria andBeginMonthEqualTo(String value) {
            addCriterion("begin_month =", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria andBeginMonthNotEqualTo(String value) {
            addCriterion("begin_month <>", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria andBeginMonthGreaterThan(String value) {
            addCriterion("begin_month >", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria andBeginMonthGreaterThanOrEqualTo(String value) {
            addCriterion("begin_month >=", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria andBeginMonthLessThan(String value) {
            addCriterion("begin_month <", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria andBeginMonthLessThanOrEqualTo(String value) {
            addCriterion("begin_month <=", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria andBeginMonthLike(String value) {
            addCriterion("begin_month like", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria andBeginMonthNotLike(String value) {
            addCriterion("begin_month not like", value, "beginMonth");
            return (Criteria) this;
        }

        public Criteria andBeginMonthIn(List<String> values) {
            addCriterion("begin_month in", values, "beginMonth");
            return (Criteria) this;
        }

        public Criteria andBeginMonthNotIn(List<String> values) {
            addCriterion("begin_month not in", values, "beginMonth");
            return (Criteria) this;
        }

        public Criteria andBeginMonthBetween(String value1, String value2) {
            addCriterion("begin_month between", value1, value2, "beginMonth");
            return (Criteria) this;
        }

        public Criteria andBeginMonthNotBetween(String value1, String value2) {
            addCriterion("begin_month not between", value1, value2, "beginMonth");
            return (Criteria) this;
        }

        public Criteria andBeginYearIsNull() {
            addCriterion("begin_year is null");
            return (Criteria) this;
        }

        public Criteria andBeginYearIsNotNull() {
            addCriterion("begin_year is not null");
            return (Criteria) this;
        }

        public Criteria andBeginYearEqualTo(String value) {
            addCriterion("begin_year =", value, "beginYear");
            return (Criteria) this;
        }

        public Criteria andBeginYearNotEqualTo(String value) {
            addCriterion("begin_year <>", value, "beginYear");
            return (Criteria) this;
        }

        public Criteria andBeginYearGreaterThan(String value) {
            addCriterion("begin_year >", value, "beginYear");
            return (Criteria) this;
        }

        public Criteria andBeginYearGreaterThanOrEqualTo(String value) {
            addCriterion("begin_year >=", value, "beginYear");
            return (Criteria) this;
        }

        public Criteria andBeginYearLessThan(String value) {
            addCriterion("begin_year <", value, "beginYear");
            return (Criteria) this;
        }

        public Criteria andBeginYearLessThanOrEqualTo(String value) {
            addCriterion("begin_year <=", value, "beginYear");
            return (Criteria) this;
        }

        public Criteria andBeginYearLike(String value) {
            addCriterion("begin_year like", value, "beginYear");
            return (Criteria) this;
        }

        public Criteria andBeginYearNotLike(String value) {
            addCriterion("begin_year not like", value, "beginYear");
            return (Criteria) this;
        }

        public Criteria andBeginYearIn(List<String> values) {
            addCriterion("begin_year in", values, "beginYear");
            return (Criteria) this;
        }

        public Criteria andBeginYearNotIn(List<String> values) {
            addCriterion("begin_year not in", values, "beginYear");
            return (Criteria) this;
        }

        public Criteria andBeginYearBetween(String value1, String value2) {
            addCriterion("begin_year between", value1, value2, "beginYear");
            return (Criteria) this;
        }

        public Criteria andBeginYearNotBetween(String value1, String value2) {
            addCriterion("begin_year not between", value1, value2, "beginYear");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitIsNull() {
            addCriterion("borrow_term_unit is null");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitIsNotNull() {
            addCriterion("borrow_term_unit is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitEqualTo(String value) {
            addCriterion("borrow_term_unit =", value, "borrowTermUnit");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitNotEqualTo(String value) {
            addCriterion("borrow_term_unit <>", value, "borrowTermUnit");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitGreaterThan(String value) {
            addCriterion("borrow_term_unit >", value, "borrowTermUnit");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitGreaterThanOrEqualTo(String value) {
            addCriterion("borrow_term_unit >=", value, "borrowTermUnit");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitLessThan(String value) {
            addCriterion("borrow_term_unit <", value, "borrowTermUnit");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitLessThanOrEqualTo(String value) {
            addCriterion("borrow_term_unit <=", value, "borrowTermUnit");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitLike(String value) {
            addCriterion("borrow_term_unit like", value, "borrowTermUnit");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitNotLike(String value) {
            addCriterion("borrow_term_unit not like", value, "borrowTermUnit");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitIn(List<String> values) {
            addCriterion("borrow_term_unit in", values, "borrowTermUnit");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitNotIn(List<String> values) {
            addCriterion("borrow_term_unit not in", values, "borrowTermUnit");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitBetween(String value1, String value2) {
            addCriterion("borrow_term_unit between", value1, value2, "borrowTermUnit");
            return (Criteria) this;
        }

        public Criteria andBorrowTermUnitNotBetween(String value1, String value2) {
            addCriterion("borrow_term_unit not between", value1, value2, "borrowTermUnit");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsIsNull() {
            addCriterion("borrow_terms is null");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsIsNotNull() {
            addCriterion("borrow_terms is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsEqualTo(String value) {
            addCriterion("borrow_terms =", value, "borrowTerms");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsNotEqualTo(String value) {
            addCriterion("borrow_terms <>", value, "borrowTerms");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsGreaterThan(String value) {
            addCriterion("borrow_terms >", value, "borrowTerms");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsGreaterThanOrEqualTo(String value) {
            addCriterion("borrow_terms >=", value, "borrowTerms");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsLessThan(String value) {
            addCriterion("borrow_terms <", value, "borrowTerms");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsLessThanOrEqualTo(String value) {
            addCriterion("borrow_terms <=", value, "borrowTerms");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsLike(String value) {
            addCriterion("borrow_terms like", value, "borrowTerms");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsNotLike(String value) {
            addCriterion("borrow_terms not like", value, "borrowTerms");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsIn(List<String> values) {
            addCriterion("borrow_terms in", values, "borrowTerms");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsNotIn(List<String> values) {
            addCriterion("borrow_terms not in", values, "borrowTerms");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsBetween(String value1, String value2) {
            addCriterion("borrow_terms between", value1, value2, "borrowTerms");
            return (Criteria) this;
        }

        public Criteria andBorrowTermsNotBetween(String value1, String value2) {
            addCriterion("borrow_terms not between", value1, value2, "borrowTerms");
            return (Criteria) this;
        }

        public Criteria andBorrowUriIsNull() {
            addCriterion("borrow_uri is null");
            return (Criteria) this;
        }

        public Criteria andBorrowUriIsNotNull() {
            addCriterion("borrow_uri is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowUriEqualTo(String value) {
            addCriterion("borrow_uri =", value, "borrowUri");
            return (Criteria) this;
        }

        public Criteria andBorrowUriNotEqualTo(String value) {
            addCriterion("borrow_uri <>", value, "borrowUri");
            return (Criteria) this;
        }

        public Criteria andBorrowUriGreaterThan(String value) {
            addCriterion("borrow_uri >", value, "borrowUri");
            return (Criteria) this;
        }

        public Criteria andBorrowUriGreaterThanOrEqualTo(String value) {
            addCriterion("borrow_uri >=", value, "borrowUri");
            return (Criteria) this;
        }

        public Criteria andBorrowUriLessThan(String value) {
            addCriterion("borrow_uri <", value, "borrowUri");
            return (Criteria) this;
        }

        public Criteria andBorrowUriLessThanOrEqualTo(String value) {
            addCriterion("borrow_uri <=", value, "borrowUri");
            return (Criteria) this;
        }

        public Criteria andBorrowUriLike(String value) {
            addCriterion("borrow_uri like", value, "borrowUri");
            return (Criteria) this;
        }

        public Criteria andBorrowUriNotLike(String value) {
            addCriterion("borrow_uri not like", value, "borrowUri");
            return (Criteria) this;
        }

        public Criteria andBorrowUriIn(List<String> values) {
            addCriterion("borrow_uri in", values, "borrowUri");
            return (Criteria) this;
        }

        public Criteria andBorrowUriNotIn(List<String> values) {
            addCriterion("borrow_uri not in", values, "borrowUri");
            return (Criteria) this;
        }

        public Criteria andBorrowUriBetween(String value1, String value2) {
            addCriterion("borrow_uri between", value1, value2, "borrowUri");
            return (Criteria) this;
        }

        public Criteria andBorrowUriNotBetween(String value1, String value2) {
            addCriterion("borrow_uri not between", value1, value2, "borrowUri");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameIsNull() {
            addCriterion("borrower_name is null");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameIsNotNull() {
            addCriterion("borrower_name is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameEqualTo(String value) {
            addCriterion("borrower_name =", value, "borrowerName");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameNotEqualTo(String value) {
            addCriterion("borrower_name <>", value, "borrowerName");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameGreaterThan(String value) {
            addCriterion("borrower_name >", value, "borrowerName");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameGreaterThanOrEqualTo(String value) {
            addCriterion("borrower_name >=", value, "borrowerName");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameLessThan(String value) {
            addCriterion("borrower_name <", value, "borrowerName");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameLessThanOrEqualTo(String value) {
            addCriterion("borrower_name <=", value, "borrowerName");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameLike(String value) {
            addCriterion("borrower_name like", value, "borrowerName");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameNotLike(String value) {
            addCriterion("borrower_name not like", value, "borrowerName");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameIn(List<String> values) {
            addCriterion("borrower_name in", values, "borrowerName");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameNotIn(List<String> values) {
            addCriterion("borrower_name not in", values, "borrowerName");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameBetween(String value1, String value2) {
            addCriterion("borrower_name between", value1, value2, "borrowerName");
            return (Criteria) this;
        }

        public Criteria andBorrowerNameNotBetween(String value1, String value2) {
            addCriterion("borrower_name not between", value1, value2, "borrowerName");
            return (Criteria) this;
        }

        public Criteria andContractDateIsNull() {
            addCriterion("contract_date is null");
            return (Criteria) this;
        }

        public Criteria andContractDateIsNotNull() {
            addCriterion("contract_date is not null");
            return (Criteria) this;
        }

        public Criteria andContractDateEqualTo(String value) {
            addCriterion("contract_date =", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateNotEqualTo(String value) {
            addCriterion("contract_date <>", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateGreaterThan(String value) {
            addCriterion("contract_date >", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateGreaterThanOrEqualTo(String value) {
            addCriterion("contract_date >=", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateLessThan(String value) {
            addCriterion("contract_date <", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateLessThanOrEqualTo(String value) {
            addCriterion("contract_date <=", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateLike(String value) {
            addCriterion("contract_date like", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateNotLike(String value) {
            addCriterion("contract_date not like", value, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateIn(List<String> values) {
            addCriterion("contract_date in", values, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateNotIn(List<String> values) {
            addCriterion("contract_date not in", values, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateBetween(String value1, String value2) {
            addCriterion("contract_date between", value1, value2, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractDateNotBetween(String value1, String value2) {
            addCriterion("contract_date not between", value1, value2, "contractDate");
            return (Criteria) this;
        }

        public Criteria andContractMoneyIsNull() {
            addCriterion("contract_money is null");
            return (Criteria) this;
        }

        public Criteria andContractMoneyIsNotNull() {
            addCriterion("contract_money is not null");
            return (Criteria) this;
        }

        public Criteria andContractMoneyEqualTo(String value) {
            addCriterion("contract_money =", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyNotEqualTo(String value) {
            addCriterion("contract_money <>", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyGreaterThan(String value) {
            addCriterion("contract_money >", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("contract_money >=", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyLessThan(String value) {
            addCriterion("contract_money <", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyLessThanOrEqualTo(String value) {
            addCriterion("contract_money <=", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyLike(String value) {
            addCriterion("contract_money like", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyNotLike(String value) {
            addCriterion("contract_money not like", value, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyIn(List<String> values) {
            addCriterion("contract_money in", values, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyNotIn(List<String> values) {
            addCriterion("contract_money not in", values, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyBetween(String value1, String value2) {
            addCriterion("contract_money between", value1, value2, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractMoneyNotBetween(String value1, String value2) {
            addCriterion("contract_money not between", value1, value2, "contractMoney");
            return (Criteria) this;
        }

        public Criteria andContractNoIsNull() {
            addCriterion("contract_no is null");
            return (Criteria) this;
        }

        public Criteria andContractNoIsNotNull() {
            addCriterion("contract_no is not null");
            return (Criteria) this;
        }

        public Criteria andContractNoEqualTo(String value) {
            addCriterion("contract_no =", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotEqualTo(String value) {
            addCriterion("contract_no <>", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoGreaterThan(String value) {
            addCriterion("contract_no >", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoGreaterThanOrEqualTo(String value) {
            addCriterion("contract_no >=", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLessThan(String value) {
            addCriterion("contract_no <", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLessThanOrEqualTo(String value) {
            addCriterion("contract_no <=", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoLike(String value) {
            addCriterion("contract_no like", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotLike(String value) {
            addCriterion("contract_no not like", value, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoIn(List<String> values) {
            addCriterion("contract_no in", values, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotIn(List<String> values) {
            addCriterion("contract_no not in", values, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoBetween(String value1, String value2) {
            addCriterion("contract_no between", value1, value2, "contractNo");
            return (Criteria) this;
        }

        public Criteria andContractNoNotBetween(String value1, String value2) {
            addCriterion("contract_no not between", value1, value2, "contractNo");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriIsNull() {
            addCriterion("debt_notice_uri is null");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriIsNotNull() {
            addCriterion("debt_notice_uri is not null");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriEqualTo(String value) {
            addCriterion("debt_notice_uri =", value, "debtNoticeUri");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriNotEqualTo(String value) {
            addCriterion("debt_notice_uri <>", value, "debtNoticeUri");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriGreaterThan(String value) {
            addCriterion("debt_notice_uri >", value, "debtNoticeUri");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriGreaterThanOrEqualTo(String value) {
            addCriterion("debt_notice_uri >=", value, "debtNoticeUri");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriLessThan(String value) {
            addCriterion("debt_notice_uri <", value, "debtNoticeUri");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriLessThanOrEqualTo(String value) {
            addCriterion("debt_notice_uri <=", value, "debtNoticeUri");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriLike(String value) {
            addCriterion("debt_notice_uri like", value, "debtNoticeUri");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriNotLike(String value) {
            addCriterion("debt_notice_uri not like", value, "debtNoticeUri");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriIn(List<String> values) {
            addCriterion("debt_notice_uri in", values, "debtNoticeUri");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriNotIn(List<String> values) {
            addCriterion("debt_notice_uri not in", values, "debtNoticeUri");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriBetween(String value1, String value2) {
            addCriterion("debt_notice_uri between", value1, value2, "debtNoticeUri");
            return (Criteria) this;
        }

        public Criteria andDebtNoticeUriNotBetween(String value1, String value2) {
            addCriterion("debt_notice_uri not between", value1, value2, "debtNoticeUri");
            return (Criteria) this;
        }

        public Criteria andEndDayIsNull() {
            addCriterion("end_day is null");
            return (Criteria) this;
        }

        public Criteria andEndDayIsNotNull() {
            addCriterion("end_day is not null");
            return (Criteria) this;
        }

        public Criteria andEndDayEqualTo(String value) {
            addCriterion("end_day =", value, "endDay");
            return (Criteria) this;
        }

        public Criteria andEndDayNotEqualTo(String value) {
            addCriterion("end_day <>", value, "endDay");
            return (Criteria) this;
        }

        public Criteria andEndDayGreaterThan(String value) {
            addCriterion("end_day >", value, "endDay");
            return (Criteria) this;
        }

        public Criteria andEndDayGreaterThanOrEqualTo(String value) {
            addCriterion("end_day >=", value, "endDay");
            return (Criteria) this;
        }

        public Criteria andEndDayLessThan(String value) {
            addCriterion("end_day <", value, "endDay");
            return (Criteria) this;
        }

        public Criteria andEndDayLessThanOrEqualTo(String value) {
            addCriterion("end_day <=", value, "endDay");
            return (Criteria) this;
        }

        public Criteria andEndDayLike(String value) {
            addCriterion("end_day like", value, "endDay");
            return (Criteria) this;
        }

        public Criteria andEndDayNotLike(String value) {
            addCriterion("end_day not like", value, "endDay");
            return (Criteria) this;
        }

        public Criteria andEndDayIn(List<String> values) {
            addCriterion("end_day in", values, "endDay");
            return (Criteria) this;
        }

        public Criteria andEndDayNotIn(List<String> values) {
            addCriterion("end_day not in", values, "endDay");
            return (Criteria) this;
        }

        public Criteria andEndDayBetween(String value1, String value2) {
            addCriterion("end_day between", value1, value2, "endDay");
            return (Criteria) this;
        }

        public Criteria andEndDayNotBetween(String value1, String value2) {
            addCriterion("end_day not between", value1, value2, "endDay");
            return (Criteria) this;
        }

        public Criteria andEndMonthIsNull() {
            addCriterion("end_month is null");
            return (Criteria) this;
        }

        public Criteria andEndMonthIsNotNull() {
            addCriterion("end_month is not null");
            return (Criteria) this;
        }

        public Criteria andEndMonthEqualTo(String value) {
            addCriterion("end_month =", value, "endMonth");
            return (Criteria) this;
        }

        public Criteria andEndMonthNotEqualTo(String value) {
            addCriterion("end_month <>", value, "endMonth");
            return (Criteria) this;
        }

        public Criteria andEndMonthGreaterThan(String value) {
            addCriterion("end_month >", value, "endMonth");
            return (Criteria) this;
        }

        public Criteria andEndMonthGreaterThanOrEqualTo(String value) {
            addCriterion("end_month >=", value, "endMonth");
            return (Criteria) this;
        }

        public Criteria andEndMonthLessThan(String value) {
            addCriterion("end_month <", value, "endMonth");
            return (Criteria) this;
        }

        public Criteria andEndMonthLessThanOrEqualTo(String value) {
            addCriterion("end_month <=", value, "endMonth");
            return (Criteria) this;
        }

        public Criteria andEndMonthLike(String value) {
            addCriterion("end_month like", value, "endMonth");
            return (Criteria) this;
        }

        public Criteria andEndMonthNotLike(String value) {
            addCriterion("end_month not like", value, "endMonth");
            return (Criteria) this;
        }

        public Criteria andEndMonthIn(List<String> values) {
            addCriterion("end_month in", values, "endMonth");
            return (Criteria) this;
        }

        public Criteria andEndMonthNotIn(List<String> values) {
            addCriterion("end_month not in", values, "endMonth");
            return (Criteria) this;
        }

        public Criteria andEndMonthBetween(String value1, String value2) {
            addCriterion("end_month between", value1, value2, "endMonth");
            return (Criteria) this;
        }

        public Criteria andEndMonthNotBetween(String value1, String value2) {
            addCriterion("end_month not between", value1, value2, "endMonth");
            return (Criteria) this;
        }

        public Criteria andEndYearIsNull() {
            addCriterion("end_year is null");
            return (Criteria) this;
        }

        public Criteria andEndYearIsNotNull() {
            addCriterion("end_year is not null");
            return (Criteria) this;
        }

        public Criteria andEndYearEqualTo(String value) {
            addCriterion("end_year =", value, "endYear");
            return (Criteria) this;
        }

        public Criteria andEndYearNotEqualTo(String value) {
            addCriterion("end_year <>", value, "endYear");
            return (Criteria) this;
        }

        public Criteria andEndYearGreaterThan(String value) {
            addCriterion("end_year >", value, "endYear");
            return (Criteria) this;
        }

        public Criteria andEndYearGreaterThanOrEqualTo(String value) {
            addCriterion("end_year >=", value, "endYear");
            return (Criteria) this;
        }

        public Criteria andEndYearLessThan(String value) {
            addCriterion("end_year <", value, "endYear");
            return (Criteria) this;
        }

        public Criteria andEndYearLessThanOrEqualTo(String value) {
            addCriterion("end_year <=", value, "endYear");
            return (Criteria) this;
        }

        public Criteria andEndYearLike(String value) {
            addCriterion("end_year like", value, "endYear");
            return (Criteria) this;
        }

        public Criteria andEndYearNotLike(String value) {
            addCriterion("end_year not like", value, "endYear");
            return (Criteria) this;
        }

        public Criteria andEndYearIn(List<String> values) {
            addCriterion("end_year in", values, "endYear");
            return (Criteria) this;
        }

        public Criteria andEndYearNotIn(List<String> values) {
            addCriterion("end_year not in", values, "endYear");
            return (Criteria) this;
        }

        public Criteria andEndYearBetween(String value1, String value2) {
            addCriterion("end_year between", value1, value2, "endYear");
            return (Criteria) this;
        }

        public Criteria andEndYearNotBetween(String value1, String value2) {
            addCriterion("end_year not between", value1, value2, "endYear");
            return (Criteria) this;
        }

        public Criteria andField1IsNull() {
            addCriterion("field1 is null");
            return (Criteria) this;
        }

        public Criteria andField1IsNotNull() {
            addCriterion("field1 is not null");
            return (Criteria) this;
        }

        public Criteria andField1EqualTo(String value) {
            addCriterion("field1 =", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1NotEqualTo(String value) {
            addCriterion("field1 <>", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1GreaterThan(String value) {
            addCriterion("field1 >", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1GreaterThanOrEqualTo(String value) {
            addCriterion("field1 >=", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1LessThan(String value) {
            addCriterion("field1 <", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1LessThanOrEqualTo(String value) {
            addCriterion("field1 <=", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1Like(String value) {
            addCriterion("field1 like", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1NotLike(String value) {
            addCriterion("field1 not like", value, "field1");
            return (Criteria) this;
        }

        public Criteria andField1In(List<String> values) {
            addCriterion("field1 in", values, "field1");
            return (Criteria) this;
        }

        public Criteria andField1NotIn(List<String> values) {
            addCriterion("field1 not in", values, "field1");
            return (Criteria) this;
        }

        public Criteria andField1Between(String value1, String value2) {
            addCriterion("field1 between", value1, value2, "field1");
            return (Criteria) this;
        }

        public Criteria andField1NotBetween(String value1, String value2) {
            addCriterion("field1 not between", value1, value2, "field1");
            return (Criteria) this;
        }

        public Criteria andField10IsNull() {
            addCriterion("field10 is null");
            return (Criteria) this;
        }

        public Criteria andField10IsNotNull() {
            addCriterion("field10 is not null");
            return (Criteria) this;
        }

        public Criteria andField10EqualTo(String value) {
            addCriterion("field10 =", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10NotEqualTo(String value) {
            addCriterion("field10 <>", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10GreaterThan(String value) {
            addCriterion("field10 >", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10GreaterThanOrEqualTo(String value) {
            addCriterion("field10 >=", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10LessThan(String value) {
            addCriterion("field10 <", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10LessThanOrEqualTo(String value) {
            addCriterion("field10 <=", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10Like(String value) {
            addCriterion("field10 like", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10NotLike(String value) {
            addCriterion("field10 not like", value, "field10");
            return (Criteria) this;
        }

        public Criteria andField10In(List<String> values) {
            addCriterion("field10 in", values, "field10");
            return (Criteria) this;
        }

        public Criteria andField10NotIn(List<String> values) {
            addCriterion("field10 not in", values, "field10");
            return (Criteria) this;
        }

        public Criteria andField10Between(String value1, String value2) {
            addCriterion("field10 between", value1, value2, "field10");
            return (Criteria) this;
        }

        public Criteria andField10NotBetween(String value1, String value2) {
            addCriterion("field10 not between", value1, value2, "field10");
            return (Criteria) this;
        }

        public Criteria andField11IsNull() {
            addCriterion("field11 is null");
            return (Criteria) this;
        }

        public Criteria andField11IsNotNull() {
            addCriterion("field11 is not null");
            return (Criteria) this;
        }

        public Criteria andField11EqualTo(String value) {
            addCriterion("field11 =", value, "field11");
            return (Criteria) this;
        }

        public Criteria andField11NotEqualTo(String value) {
            addCriterion("field11 <>", value, "field11");
            return (Criteria) this;
        }

        public Criteria andField11GreaterThan(String value) {
            addCriterion("field11 >", value, "field11");
            return (Criteria) this;
        }

        public Criteria andField11GreaterThanOrEqualTo(String value) {
            addCriterion("field11 >=", value, "field11");
            return (Criteria) this;
        }

        public Criteria andField11LessThan(String value) {
            addCriterion("field11 <", value, "field11");
            return (Criteria) this;
        }

        public Criteria andField11LessThanOrEqualTo(String value) {
            addCriterion("field11 <=", value, "field11");
            return (Criteria) this;
        }

        public Criteria andField11Like(String value) {
            addCriterion("field11 like", value, "field11");
            return (Criteria) this;
        }

        public Criteria andField11NotLike(String value) {
            addCriterion("field11 not like", value, "field11");
            return (Criteria) this;
        }

        public Criteria andField11In(List<String> values) {
            addCriterion("field11 in", values, "field11");
            return (Criteria) this;
        }

        public Criteria andField11NotIn(List<String> values) {
            addCriterion("field11 not in", values, "field11");
            return (Criteria) this;
        }

        public Criteria andField11Between(String value1, String value2) {
            addCriterion("field11 between", value1, value2, "field11");
            return (Criteria) this;
        }

        public Criteria andField11NotBetween(String value1, String value2) {
            addCriterion("field11 not between", value1, value2, "field11");
            return (Criteria) this;
        }

        public Criteria andField12IsNull() {
            addCriterion("field12 is null");
            return (Criteria) this;
        }

        public Criteria andField12IsNotNull() {
            addCriterion("field12 is not null");
            return (Criteria) this;
        }

        public Criteria andField12EqualTo(String value) {
            addCriterion("field12 =", value, "field12");
            return (Criteria) this;
        }

        public Criteria andField12NotEqualTo(String value) {
            addCriterion("field12 <>", value, "field12");
            return (Criteria) this;
        }

        public Criteria andField12GreaterThan(String value) {
            addCriterion("field12 >", value, "field12");
            return (Criteria) this;
        }

        public Criteria andField12GreaterThanOrEqualTo(String value) {
            addCriterion("field12 >=", value, "field12");
            return (Criteria) this;
        }

        public Criteria andField12LessThan(String value) {
            addCriterion("field12 <", value, "field12");
            return (Criteria) this;
        }

        public Criteria andField12LessThanOrEqualTo(String value) {
            addCriterion("field12 <=", value, "field12");
            return (Criteria) this;
        }

        public Criteria andField12Like(String value) {
            addCriterion("field12 like", value, "field12");
            return (Criteria) this;
        }

        public Criteria andField12NotLike(String value) {
            addCriterion("field12 not like", value, "field12");
            return (Criteria) this;
        }

        public Criteria andField12In(List<String> values) {
            addCriterion("field12 in", values, "field12");
            return (Criteria) this;
        }

        public Criteria andField12NotIn(List<String> values) {
            addCriterion("field12 not in", values, "field12");
            return (Criteria) this;
        }

        public Criteria andField12Between(String value1, String value2) {
            addCriterion("field12 between", value1, value2, "field12");
            return (Criteria) this;
        }

        public Criteria andField12NotBetween(String value1, String value2) {
            addCriterion("field12 not between", value1, value2, "field12");
            return (Criteria) this;
        }

        public Criteria andField13IsNull() {
            addCriterion("field13 is null");
            return (Criteria) this;
        }

        public Criteria andField13IsNotNull() {
            addCriterion("field13 is not null");
            return (Criteria) this;
        }

        public Criteria andField13EqualTo(String value) {
            addCriterion("field13 =", value, "field13");
            return (Criteria) this;
        }

        public Criteria andField13NotEqualTo(String value) {
            addCriterion("field13 <>", value, "field13");
            return (Criteria) this;
        }

        public Criteria andField13GreaterThan(String value) {
            addCriterion("field13 >", value, "field13");
            return (Criteria) this;
        }

        public Criteria andField13GreaterThanOrEqualTo(String value) {
            addCriterion("field13 >=", value, "field13");
            return (Criteria) this;
        }

        public Criteria andField13LessThan(String value) {
            addCriterion("field13 <", value, "field13");
            return (Criteria) this;
        }

        public Criteria andField13LessThanOrEqualTo(String value) {
            addCriterion("field13 <=", value, "field13");
            return (Criteria) this;
        }

        public Criteria andField13Like(String value) {
            addCriterion("field13 like", value, "field13");
            return (Criteria) this;
        }

        public Criteria andField13NotLike(String value) {
            addCriterion("field13 not like", value, "field13");
            return (Criteria) this;
        }

        public Criteria andField13In(List<String> values) {
            addCriterion("field13 in", values, "field13");
            return (Criteria) this;
        }

        public Criteria andField13NotIn(List<String> values) {
            addCriterion("field13 not in", values, "field13");
            return (Criteria) this;
        }

        public Criteria andField13Between(String value1, String value2) {
            addCriterion("field13 between", value1, value2, "field13");
            return (Criteria) this;
        }

        public Criteria andField13NotBetween(String value1, String value2) {
            addCriterion("field13 not between", value1, value2, "field13");
            return (Criteria) this;
        }

        public Criteria andField14IsNull() {
            addCriterion("field14 is null");
            return (Criteria) this;
        }

        public Criteria andField14IsNotNull() {
            addCriterion("field14 is not null");
            return (Criteria) this;
        }

        public Criteria andField14EqualTo(String value) {
            addCriterion("field14 =", value, "field14");
            return (Criteria) this;
        }

        public Criteria andField14NotEqualTo(String value) {
            addCriterion("field14 <>", value, "field14");
            return (Criteria) this;
        }

        public Criteria andField14GreaterThan(String value) {
            addCriterion("field14 >", value, "field14");
            return (Criteria) this;
        }

        public Criteria andField14GreaterThanOrEqualTo(String value) {
            addCriterion("field14 >=", value, "field14");
            return (Criteria) this;
        }

        public Criteria andField14LessThan(String value) {
            addCriterion("field14 <", value, "field14");
            return (Criteria) this;
        }

        public Criteria andField14LessThanOrEqualTo(String value) {
            addCriterion("field14 <=", value, "field14");
            return (Criteria) this;
        }

        public Criteria andField14Like(String value) {
            addCriterion("field14 like", value, "field14");
            return (Criteria) this;
        }

        public Criteria andField14NotLike(String value) {
            addCriterion("field14 not like", value, "field14");
            return (Criteria) this;
        }

        public Criteria andField14In(List<String> values) {
            addCriterion("field14 in", values, "field14");
            return (Criteria) this;
        }

        public Criteria andField14NotIn(List<String> values) {
            addCriterion("field14 not in", values, "field14");
            return (Criteria) this;
        }

        public Criteria andField14Between(String value1, String value2) {
            addCriterion("field14 between", value1, value2, "field14");
            return (Criteria) this;
        }

        public Criteria andField14NotBetween(String value1, String value2) {
            addCriterion("field14 not between", value1, value2, "field14");
            return (Criteria) this;
        }

        public Criteria andField15IsNull() {
            addCriterion("field15 is null");
            return (Criteria) this;
        }

        public Criteria andField15IsNotNull() {
            addCriterion("field15 is not null");
            return (Criteria) this;
        }

        public Criteria andField15EqualTo(String value) {
            addCriterion("field15 =", value, "field15");
            return (Criteria) this;
        }

        public Criteria andField15NotEqualTo(String value) {
            addCriterion("field15 <>", value, "field15");
            return (Criteria) this;
        }

        public Criteria andField15GreaterThan(String value) {
            addCriterion("field15 >", value, "field15");
            return (Criteria) this;
        }

        public Criteria andField15GreaterThanOrEqualTo(String value) {
            addCriterion("field15 >=", value, "field15");
            return (Criteria) this;
        }

        public Criteria andField15LessThan(String value) {
            addCriterion("field15 <", value, "field15");
            return (Criteria) this;
        }

        public Criteria andField15LessThanOrEqualTo(String value) {
            addCriterion("field15 <=", value, "field15");
            return (Criteria) this;
        }

        public Criteria andField15Like(String value) {
            addCriterion("field15 like", value, "field15");
            return (Criteria) this;
        }

        public Criteria andField15NotLike(String value) {
            addCriterion("field15 not like", value, "field15");
            return (Criteria) this;
        }

        public Criteria andField15In(List<String> values) {
            addCriterion("field15 in", values, "field15");
            return (Criteria) this;
        }

        public Criteria andField15NotIn(List<String> values) {
            addCriterion("field15 not in", values, "field15");
            return (Criteria) this;
        }

        public Criteria andField15Between(String value1, String value2) {
            addCriterion("field15 between", value1, value2, "field15");
            return (Criteria) this;
        }

        public Criteria andField15NotBetween(String value1, String value2) {
            addCriterion("field15 not between", value1, value2, "field15");
            return (Criteria) this;
        }

        public Criteria andField16IsNull() {
            addCriterion("field16 is null");
            return (Criteria) this;
        }

        public Criteria andField16IsNotNull() {
            addCriterion("field16 is not null");
            return (Criteria) this;
        }

        public Criteria andField16EqualTo(String value) {
            addCriterion("field16 =", value, "field16");
            return (Criteria) this;
        }

        public Criteria andField16NotEqualTo(String value) {
            addCriterion("field16 <>", value, "field16");
            return (Criteria) this;
        }

        public Criteria andField16GreaterThan(String value) {
            addCriterion("field16 >", value, "field16");
            return (Criteria) this;
        }

        public Criteria andField16GreaterThanOrEqualTo(String value) {
            addCriterion("field16 >=", value, "field16");
            return (Criteria) this;
        }

        public Criteria andField16LessThan(String value) {
            addCriterion("field16 <", value, "field16");
            return (Criteria) this;
        }

        public Criteria andField16LessThanOrEqualTo(String value) {
            addCriterion("field16 <=", value, "field16");
            return (Criteria) this;
        }

        public Criteria andField16Like(String value) {
            addCriterion("field16 like", value, "field16");
            return (Criteria) this;
        }

        public Criteria andField16NotLike(String value) {
            addCriterion("field16 not like", value, "field16");
            return (Criteria) this;
        }

        public Criteria andField16In(List<String> values) {
            addCriterion("field16 in", values, "field16");
            return (Criteria) this;
        }

        public Criteria andField16NotIn(List<String> values) {
            addCriterion("field16 not in", values, "field16");
            return (Criteria) this;
        }

        public Criteria andField16Between(String value1, String value2) {
            addCriterion("field16 between", value1, value2, "field16");
            return (Criteria) this;
        }

        public Criteria andField16NotBetween(String value1, String value2) {
            addCriterion("field16 not between", value1, value2, "field16");
            return (Criteria) this;
        }

        public Criteria andField17IsNull() {
            addCriterion("field17 is null");
            return (Criteria) this;
        }

        public Criteria andField17IsNotNull() {
            addCriterion("field17 is not null");
            return (Criteria) this;
        }

        public Criteria andField17EqualTo(String value) {
            addCriterion("field17 =", value, "field17");
            return (Criteria) this;
        }

        public Criteria andField17NotEqualTo(String value) {
            addCriterion("field17 <>", value, "field17");
            return (Criteria) this;
        }

        public Criteria andField17GreaterThan(String value) {
            addCriterion("field17 >", value, "field17");
            return (Criteria) this;
        }

        public Criteria andField17GreaterThanOrEqualTo(String value) {
            addCriterion("field17 >=", value, "field17");
            return (Criteria) this;
        }

        public Criteria andField17LessThan(String value) {
            addCriterion("field17 <", value, "field17");
            return (Criteria) this;
        }

        public Criteria andField17LessThanOrEqualTo(String value) {
            addCriterion("field17 <=", value, "field17");
            return (Criteria) this;
        }

        public Criteria andField17Like(String value) {
            addCriterion("field17 like", value, "field17");
            return (Criteria) this;
        }

        public Criteria andField17NotLike(String value) {
            addCriterion("field17 not like", value, "field17");
            return (Criteria) this;
        }

        public Criteria andField17In(List<String> values) {
            addCriterion("field17 in", values, "field17");
            return (Criteria) this;
        }

        public Criteria andField17NotIn(List<String> values) {
            addCriterion("field17 not in", values, "field17");
            return (Criteria) this;
        }

        public Criteria andField17Between(String value1, String value2) {
            addCriterion("field17 between", value1, value2, "field17");
            return (Criteria) this;
        }

        public Criteria andField17NotBetween(String value1, String value2) {
            addCriterion("field17 not between", value1, value2, "field17");
            return (Criteria) this;
        }

        public Criteria andField19IsNull() {
            addCriterion("field19 is null");
            return (Criteria) this;
        }

        public Criteria andField19IsNotNull() {
            addCriterion("field19 is not null");
            return (Criteria) this;
        }

        public Criteria andField19EqualTo(String value) {
            addCriterion("field19 =", value, "field19");
            return (Criteria) this;
        }

        public Criteria andField19NotEqualTo(String value) {
            addCriterion("field19 <>", value, "field19");
            return (Criteria) this;
        }

        public Criteria andField19GreaterThan(String value) {
            addCriterion("field19 >", value, "field19");
            return (Criteria) this;
        }

        public Criteria andField19GreaterThanOrEqualTo(String value) {
            addCriterion("field19 >=", value, "field19");
            return (Criteria) this;
        }

        public Criteria andField19LessThan(String value) {
            addCriterion("field19 <", value, "field19");
            return (Criteria) this;
        }

        public Criteria andField19LessThanOrEqualTo(String value) {
            addCriterion("field19 <=", value, "field19");
            return (Criteria) this;
        }

        public Criteria andField19Like(String value) {
            addCriterion("field19 like", value, "field19");
            return (Criteria) this;
        }

        public Criteria andField19NotLike(String value) {
            addCriterion("field19 not like", value, "field19");
            return (Criteria) this;
        }

        public Criteria andField19In(List<String> values) {
            addCriterion("field19 in", values, "field19");
            return (Criteria) this;
        }

        public Criteria andField19NotIn(List<String> values) {
            addCriterion("field19 not in", values, "field19");
            return (Criteria) this;
        }

        public Criteria andField19Between(String value1, String value2) {
            addCriterion("field19 between", value1, value2, "field19");
            return (Criteria) this;
        }

        public Criteria andField19NotBetween(String value1, String value2) {
            addCriterion("field19 not between", value1, value2, "field19");
            return (Criteria) this;
        }

        public Criteria andField2IsNull() {
            addCriterion("field2 is null");
            return (Criteria) this;
        }

        public Criteria andField2IsNotNull() {
            addCriterion("field2 is not null");
            return (Criteria) this;
        }

        public Criteria andField2EqualTo(String value) {
            addCriterion("field2 =", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2NotEqualTo(String value) {
            addCriterion("field2 <>", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2GreaterThan(String value) {
            addCriterion("field2 >", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2GreaterThanOrEqualTo(String value) {
            addCriterion("field2 >=", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2LessThan(String value) {
            addCriterion("field2 <", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2LessThanOrEqualTo(String value) {
            addCriterion("field2 <=", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2Like(String value) {
            addCriterion("field2 like", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2NotLike(String value) {
            addCriterion("field2 not like", value, "field2");
            return (Criteria) this;
        }

        public Criteria andField2In(List<String> values) {
            addCriterion("field2 in", values, "field2");
            return (Criteria) this;
        }

        public Criteria andField2NotIn(List<String> values) {
            addCriterion("field2 not in", values, "field2");
            return (Criteria) this;
        }

        public Criteria andField2Between(String value1, String value2) {
            addCriterion("field2 between", value1, value2, "field2");
            return (Criteria) this;
        }

        public Criteria andField2NotBetween(String value1, String value2) {
            addCriterion("field2 not between", value1, value2, "field2");
            return (Criteria) this;
        }

        public Criteria andField20IsNull() {
            addCriterion("field20 is null");
            return (Criteria) this;
        }

        public Criteria andField20IsNotNull() {
            addCriterion("field20 is not null");
            return (Criteria) this;
        }

        public Criteria andField20EqualTo(String value) {
            addCriterion("field20 =", value, "field20");
            return (Criteria) this;
        }

        public Criteria andField20NotEqualTo(String value) {
            addCriterion("field20 <>", value, "field20");
            return (Criteria) this;
        }

        public Criteria andField20GreaterThan(String value) {
            addCriterion("field20 >", value, "field20");
            return (Criteria) this;
        }

        public Criteria andField20GreaterThanOrEqualTo(String value) {
            addCriterion("field20 >=", value, "field20");
            return (Criteria) this;
        }

        public Criteria andField20LessThan(String value) {
            addCriterion("field20 <", value, "field20");
            return (Criteria) this;
        }

        public Criteria andField20LessThanOrEqualTo(String value) {
            addCriterion("field20 <=", value, "field20");
            return (Criteria) this;
        }

        public Criteria andField20Like(String value) {
            addCriterion("field20 like", value, "field20");
            return (Criteria) this;
        }

        public Criteria andField20NotLike(String value) {
            addCriterion("field20 not like", value, "field20");
            return (Criteria) this;
        }

        public Criteria andField20In(List<String> values) {
            addCriterion("field20 in", values, "field20");
            return (Criteria) this;
        }

        public Criteria andField20NotIn(List<String> values) {
            addCriterion("field20 not in", values, "field20");
            return (Criteria) this;
        }

        public Criteria andField20Between(String value1, String value2) {
            addCriterion("field20 between", value1, value2, "field20");
            return (Criteria) this;
        }

        public Criteria andField20NotBetween(String value1, String value2) {
            addCriterion("field20 not between", value1, value2, "field20");
            return (Criteria) this;
        }

        public Criteria andField23IsNull() {
            addCriterion("field23 is null");
            return (Criteria) this;
        }

        public Criteria andField23IsNotNull() {
            addCriterion("field23 is not null");
            return (Criteria) this;
        }

        public Criteria andField23EqualTo(String value) {
            addCriterion("field23 =", value, "field23");
            return (Criteria) this;
        }

        public Criteria andField23NotEqualTo(String value) {
            addCriterion("field23 <>", value, "field23");
            return (Criteria) this;
        }

        public Criteria andField23GreaterThan(String value) {
            addCriterion("field23 >", value, "field23");
            return (Criteria) this;
        }

        public Criteria andField23GreaterThanOrEqualTo(String value) {
            addCriterion("field23 >=", value, "field23");
            return (Criteria) this;
        }

        public Criteria andField23LessThan(String value) {
            addCriterion("field23 <", value, "field23");
            return (Criteria) this;
        }

        public Criteria andField23LessThanOrEqualTo(String value) {
            addCriterion("field23 <=", value, "field23");
            return (Criteria) this;
        }

        public Criteria andField23Like(String value) {
            addCriterion("field23 like", value, "field23");
            return (Criteria) this;
        }

        public Criteria andField23NotLike(String value) {
            addCriterion("field23 not like", value, "field23");
            return (Criteria) this;
        }

        public Criteria andField23In(List<String> values) {
            addCriterion("field23 in", values, "field23");
            return (Criteria) this;
        }

        public Criteria andField23NotIn(List<String> values) {
            addCriterion("field23 not in", values, "field23");
            return (Criteria) this;
        }

        public Criteria andField23Between(String value1, String value2) {
            addCriterion("field23 between", value1, value2, "field23");
            return (Criteria) this;
        }

        public Criteria andField23NotBetween(String value1, String value2) {
            addCriterion("field23 not between", value1, value2, "field23");
            return (Criteria) this;
        }

        public Criteria andField24IsNull() {
            addCriterion("field24 is null");
            return (Criteria) this;
        }

        public Criteria andField24IsNotNull() {
            addCriterion("field24 is not null");
            return (Criteria) this;
        }

        public Criteria andField24EqualTo(String value) {
            addCriterion("field24 =", value, "field24");
            return (Criteria) this;
        }

        public Criteria andField24NotEqualTo(String value) {
            addCriterion("field24 <>", value, "field24");
            return (Criteria) this;
        }

        public Criteria andField24GreaterThan(String value) {
            addCriterion("field24 >", value, "field24");
            return (Criteria) this;
        }

        public Criteria andField24GreaterThanOrEqualTo(String value) {
            addCriterion("field24 >=", value, "field24");
            return (Criteria) this;
        }

        public Criteria andField24LessThan(String value) {
            addCriterion("field24 <", value, "field24");
            return (Criteria) this;
        }

        public Criteria andField24LessThanOrEqualTo(String value) {
            addCriterion("field24 <=", value, "field24");
            return (Criteria) this;
        }

        public Criteria andField24Like(String value) {
            addCriterion("field24 like", value, "field24");
            return (Criteria) this;
        }

        public Criteria andField24NotLike(String value) {
            addCriterion("field24 not like", value, "field24");
            return (Criteria) this;
        }

        public Criteria andField24In(List<String> values) {
            addCriterion("field24 in", values, "field24");
            return (Criteria) this;
        }

        public Criteria andField24NotIn(List<String> values) {
            addCriterion("field24 not in", values, "field24");
            return (Criteria) this;
        }

        public Criteria andField24Between(String value1, String value2) {
            addCriterion("field24 between", value1, value2, "field24");
            return (Criteria) this;
        }

        public Criteria andField24NotBetween(String value1, String value2) {
            addCriterion("field24 not between", value1, value2, "field24");
            return (Criteria) this;
        }

        public Criteria andField26IsNull() {
            addCriterion("field26 is null");
            return (Criteria) this;
        }

        public Criteria andField26IsNotNull() {
            addCriterion("field26 is not null");
            return (Criteria) this;
        }

        public Criteria andField26EqualTo(String value) {
            addCriterion("field26 =", value, "field26");
            return (Criteria) this;
        }

        public Criteria andField26NotEqualTo(String value) {
            addCriterion("field26 <>", value, "field26");
            return (Criteria) this;
        }

        public Criteria andField26GreaterThan(String value) {
            addCriterion("field26 >", value, "field26");
            return (Criteria) this;
        }

        public Criteria andField26GreaterThanOrEqualTo(String value) {
            addCriterion("field26 >=", value, "field26");
            return (Criteria) this;
        }

        public Criteria andField26LessThan(String value) {
            addCriterion("field26 <", value, "field26");
            return (Criteria) this;
        }

        public Criteria andField26LessThanOrEqualTo(String value) {
            addCriterion("field26 <=", value, "field26");
            return (Criteria) this;
        }

        public Criteria andField26Like(String value) {
            addCriterion("field26 like", value, "field26");
            return (Criteria) this;
        }

        public Criteria andField26NotLike(String value) {
            addCriterion("field26 not like", value, "field26");
            return (Criteria) this;
        }

        public Criteria andField26In(List<String> values) {
            addCriterion("field26 in", values, "field26");
            return (Criteria) this;
        }

        public Criteria andField26NotIn(List<String> values) {
            addCriterion("field26 not in", values, "field26");
            return (Criteria) this;
        }

        public Criteria andField26Between(String value1, String value2) {
            addCriterion("field26 between", value1, value2, "field26");
            return (Criteria) this;
        }

        public Criteria andField26NotBetween(String value1, String value2) {
            addCriterion("field26 not between", value1, value2, "field26");
            return (Criteria) this;
        }

        public Criteria andField27IsNull() {
            addCriterion("field27 is null");
            return (Criteria) this;
        }

        public Criteria andField27IsNotNull() {
            addCriterion("field27 is not null");
            return (Criteria) this;
        }

        public Criteria andField27EqualTo(String value) {
            addCriterion("field27 =", value, "field27");
            return (Criteria) this;
        }

        public Criteria andField27NotEqualTo(String value) {
            addCriterion("field27 <>", value, "field27");
            return (Criteria) this;
        }

        public Criteria andField27GreaterThan(String value) {
            addCriterion("field27 >", value, "field27");
            return (Criteria) this;
        }

        public Criteria andField27GreaterThanOrEqualTo(String value) {
            addCriterion("field27 >=", value, "field27");
            return (Criteria) this;
        }

        public Criteria andField27LessThan(String value) {
            addCriterion("field27 <", value, "field27");
            return (Criteria) this;
        }

        public Criteria andField27LessThanOrEqualTo(String value) {
            addCriterion("field27 <=", value, "field27");
            return (Criteria) this;
        }

        public Criteria andField27Like(String value) {
            addCriterion("field27 like", value, "field27");
            return (Criteria) this;
        }

        public Criteria andField27NotLike(String value) {
            addCriterion("field27 not like", value, "field27");
            return (Criteria) this;
        }

        public Criteria andField27In(List<String> values) {
            addCriterion("field27 in", values, "field27");
            return (Criteria) this;
        }

        public Criteria andField27NotIn(List<String> values) {
            addCriterion("field27 not in", values, "field27");
            return (Criteria) this;
        }

        public Criteria andField27Between(String value1, String value2) {
            addCriterion("field27 between", value1, value2, "field27");
            return (Criteria) this;
        }

        public Criteria andField27NotBetween(String value1, String value2) {
            addCriterion("field27 not between", value1, value2, "field27");
            return (Criteria) this;
        }

        public Criteria andField28IsNull() {
            addCriterion("field28 is null");
            return (Criteria) this;
        }

        public Criteria andField28IsNotNull() {
            addCriterion("field28 is not null");
            return (Criteria) this;
        }

        public Criteria andField28EqualTo(String value) {
            addCriterion("field28 =", value, "field28");
            return (Criteria) this;
        }

        public Criteria andField28NotEqualTo(String value) {
            addCriterion("field28 <>", value, "field28");
            return (Criteria) this;
        }

        public Criteria andField28GreaterThan(String value) {
            addCriterion("field28 >", value, "field28");
            return (Criteria) this;
        }

        public Criteria andField28GreaterThanOrEqualTo(String value) {
            addCriterion("field28 >=", value, "field28");
            return (Criteria) this;
        }

        public Criteria andField28LessThan(String value) {
            addCriterion("field28 <", value, "field28");
            return (Criteria) this;
        }

        public Criteria andField28LessThanOrEqualTo(String value) {
            addCriterion("field28 <=", value, "field28");
            return (Criteria) this;
        }

        public Criteria andField28Like(String value) {
            addCriterion("field28 like", value, "field28");
            return (Criteria) this;
        }

        public Criteria andField28NotLike(String value) {
            addCriterion("field28 not like", value, "field28");
            return (Criteria) this;
        }

        public Criteria andField28In(List<String> values) {
            addCriterion("field28 in", values, "field28");
            return (Criteria) this;
        }

        public Criteria andField28NotIn(List<String> values) {
            addCriterion("field28 not in", values, "field28");
            return (Criteria) this;
        }

        public Criteria andField28Between(String value1, String value2) {
            addCriterion("field28 between", value1, value2, "field28");
            return (Criteria) this;
        }

        public Criteria andField28NotBetween(String value1, String value2) {
            addCriterion("field28 not between", value1, value2, "field28");
            return (Criteria) this;
        }

        public Criteria andField29IsNull() {
            addCriterion("field29 is null");
            return (Criteria) this;
        }

        public Criteria andField29IsNotNull() {
            addCriterion("field29 is not null");
            return (Criteria) this;
        }

        public Criteria andField29EqualTo(String value) {
            addCriterion("field29 =", value, "field29");
            return (Criteria) this;
        }

        public Criteria andField29NotEqualTo(String value) {
            addCriterion("field29 <>", value, "field29");
            return (Criteria) this;
        }

        public Criteria andField29GreaterThan(String value) {
            addCriterion("field29 >", value, "field29");
            return (Criteria) this;
        }

        public Criteria andField29GreaterThanOrEqualTo(String value) {
            addCriterion("field29 >=", value, "field29");
            return (Criteria) this;
        }

        public Criteria andField29LessThan(String value) {
            addCriterion("field29 <", value, "field29");
            return (Criteria) this;
        }

        public Criteria andField29LessThanOrEqualTo(String value) {
            addCriterion("field29 <=", value, "field29");
            return (Criteria) this;
        }

        public Criteria andField29Like(String value) {
            addCriterion("field29 like", value, "field29");
            return (Criteria) this;
        }

        public Criteria andField29NotLike(String value) {
            addCriterion("field29 not like", value, "field29");
            return (Criteria) this;
        }

        public Criteria andField29In(List<String> values) {
            addCriterion("field29 in", values, "field29");
            return (Criteria) this;
        }

        public Criteria andField29NotIn(List<String> values) {
            addCriterion("field29 not in", values, "field29");
            return (Criteria) this;
        }

        public Criteria andField29Between(String value1, String value2) {
            addCriterion("field29 between", value1, value2, "field29");
            return (Criteria) this;
        }

        public Criteria andField29NotBetween(String value1, String value2) {
            addCriterion("field29 not between", value1, value2, "field29");
            return (Criteria) this;
        }

        public Criteria andField3IsNull() {
            addCriterion("field3 is null");
            return (Criteria) this;
        }

        public Criteria andField3IsNotNull() {
            addCriterion("field3 is not null");
            return (Criteria) this;
        }

        public Criteria andField3EqualTo(String value) {
            addCriterion("field3 =", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3NotEqualTo(String value) {
            addCriterion("field3 <>", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3GreaterThan(String value) {
            addCriterion("field3 >", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3GreaterThanOrEqualTo(String value) {
            addCriterion("field3 >=", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3LessThan(String value) {
            addCriterion("field3 <", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3LessThanOrEqualTo(String value) {
            addCriterion("field3 <=", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3Like(String value) {
            addCriterion("field3 like", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3NotLike(String value) {
            addCriterion("field3 not like", value, "field3");
            return (Criteria) this;
        }

        public Criteria andField3In(List<String> values) {
            addCriterion("field3 in", values, "field3");
            return (Criteria) this;
        }

        public Criteria andField3NotIn(List<String> values) {
            addCriterion("field3 not in", values, "field3");
            return (Criteria) this;
        }

        public Criteria andField3Between(String value1, String value2) {
            addCriterion("field3 between", value1, value2, "field3");
            return (Criteria) this;
        }

        public Criteria andField3NotBetween(String value1, String value2) {
            addCriterion("field3 not between", value1, value2, "field3");
            return (Criteria) this;
        }

        public Criteria andField30IsNull() {
            addCriterion("field30 is null");
            return (Criteria) this;
        }

        public Criteria andField30IsNotNull() {
            addCriterion("field30 is not null");
            return (Criteria) this;
        }

        public Criteria andField30EqualTo(String value) {
            addCriterion("field30 =", value, "field30");
            return (Criteria) this;
        }

        public Criteria andField30NotEqualTo(String value) {
            addCriterion("field30 <>", value, "field30");
            return (Criteria) this;
        }

        public Criteria andField30GreaterThan(String value) {
            addCriterion("field30 >", value, "field30");
            return (Criteria) this;
        }

        public Criteria andField30GreaterThanOrEqualTo(String value) {
            addCriterion("field30 >=", value, "field30");
            return (Criteria) this;
        }

        public Criteria andField30LessThan(String value) {
            addCriterion("field30 <", value, "field30");
            return (Criteria) this;
        }

        public Criteria andField30LessThanOrEqualTo(String value) {
            addCriterion("field30 <=", value, "field30");
            return (Criteria) this;
        }

        public Criteria andField30Like(String value) {
            addCriterion("field30 like", value, "field30");
            return (Criteria) this;
        }

        public Criteria andField30NotLike(String value) {
            addCriterion("field30 not like", value, "field30");
            return (Criteria) this;
        }

        public Criteria andField30In(List<String> values) {
            addCriterion("field30 in", values, "field30");
            return (Criteria) this;
        }

        public Criteria andField30NotIn(List<String> values) {
            addCriterion("field30 not in", values, "field30");
            return (Criteria) this;
        }

        public Criteria andField30Between(String value1, String value2) {
            addCriterion("field30 between", value1, value2, "field30");
            return (Criteria) this;
        }

        public Criteria andField30NotBetween(String value1, String value2) {
            addCriterion("field30 not between", value1, value2, "field30");
            return (Criteria) this;
        }

        public Criteria andField31IsNull() {
            addCriterion("field31 is null");
            return (Criteria) this;
        }

        public Criteria andField31IsNotNull() {
            addCriterion("field31 is not null");
            return (Criteria) this;
        }

        public Criteria andField31EqualTo(String value) {
            addCriterion("field31 =", value, "field31");
            return (Criteria) this;
        }

        public Criteria andField31NotEqualTo(String value) {
            addCriterion("field31 <>", value, "field31");
            return (Criteria) this;
        }

        public Criteria andField31GreaterThan(String value) {
            addCriterion("field31 >", value, "field31");
            return (Criteria) this;
        }

        public Criteria andField31GreaterThanOrEqualTo(String value) {
            addCriterion("field31 >=", value, "field31");
            return (Criteria) this;
        }

        public Criteria andField31LessThan(String value) {
            addCriterion("field31 <", value, "field31");
            return (Criteria) this;
        }

        public Criteria andField31LessThanOrEqualTo(String value) {
            addCriterion("field31 <=", value, "field31");
            return (Criteria) this;
        }

        public Criteria andField31Like(String value) {
            addCriterion("field31 like", value, "field31");
            return (Criteria) this;
        }

        public Criteria andField31NotLike(String value) {
            addCriterion("field31 not like", value, "field31");
            return (Criteria) this;
        }

        public Criteria andField31In(List<String> values) {
            addCriterion("field31 in", values, "field31");
            return (Criteria) this;
        }

        public Criteria andField31NotIn(List<String> values) {
            addCriterion("field31 not in", values, "field31");
            return (Criteria) this;
        }

        public Criteria andField31Between(String value1, String value2) {
            addCriterion("field31 between", value1, value2, "field31");
            return (Criteria) this;
        }

        public Criteria andField31NotBetween(String value1, String value2) {
            addCriterion("field31 not between", value1, value2, "field31");
            return (Criteria) this;
        }

        public Criteria andField32IsNull() {
            addCriterion("field32 is null");
            return (Criteria) this;
        }

        public Criteria andField32IsNotNull() {
            addCriterion("field32 is not null");
            return (Criteria) this;
        }

        public Criteria andField32EqualTo(String value) {
            addCriterion("field32 =", value, "field32");
            return (Criteria) this;
        }

        public Criteria andField32NotEqualTo(String value) {
            addCriterion("field32 <>", value, "field32");
            return (Criteria) this;
        }

        public Criteria andField32GreaterThan(String value) {
            addCriterion("field32 >", value, "field32");
            return (Criteria) this;
        }

        public Criteria andField32GreaterThanOrEqualTo(String value) {
            addCriterion("field32 >=", value, "field32");
            return (Criteria) this;
        }

        public Criteria andField32LessThan(String value) {
            addCriterion("field32 <", value, "field32");
            return (Criteria) this;
        }

        public Criteria andField32LessThanOrEqualTo(String value) {
            addCriterion("field32 <=", value, "field32");
            return (Criteria) this;
        }

        public Criteria andField32Like(String value) {
            addCriterion("field32 like", value, "field32");
            return (Criteria) this;
        }

        public Criteria andField32NotLike(String value) {
            addCriterion("field32 not like", value, "field32");
            return (Criteria) this;
        }

        public Criteria andField32In(List<String> values) {
            addCriterion("field32 in", values, "field32");
            return (Criteria) this;
        }

        public Criteria andField32NotIn(List<String> values) {
            addCriterion("field32 not in", values, "field32");
            return (Criteria) this;
        }

        public Criteria andField32Between(String value1, String value2) {
            addCriterion("field32 between", value1, value2, "field32");
            return (Criteria) this;
        }

        public Criteria andField32NotBetween(String value1, String value2) {
            addCriterion("field32 not between", value1, value2, "field32");
            return (Criteria) this;
        }

        public Criteria andField33IsNull() {
            addCriterion("field33 is null");
            return (Criteria) this;
        }

        public Criteria andField33IsNotNull() {
            addCriterion("field33 is not null");
            return (Criteria) this;
        }

        public Criteria andField33EqualTo(String value) {
            addCriterion("field33 =", value, "field33");
            return (Criteria) this;
        }

        public Criteria andField33NotEqualTo(String value) {
            addCriterion("field33 <>", value, "field33");
            return (Criteria) this;
        }

        public Criteria andField33GreaterThan(String value) {
            addCriterion("field33 >", value, "field33");
            return (Criteria) this;
        }

        public Criteria andField33GreaterThanOrEqualTo(String value) {
            addCriterion("field33 >=", value, "field33");
            return (Criteria) this;
        }

        public Criteria andField33LessThan(String value) {
            addCriterion("field33 <", value, "field33");
            return (Criteria) this;
        }

        public Criteria andField33LessThanOrEqualTo(String value) {
            addCriterion("field33 <=", value, "field33");
            return (Criteria) this;
        }

        public Criteria andField33Like(String value) {
            addCriterion("field33 like", value, "field33");
            return (Criteria) this;
        }

        public Criteria andField33NotLike(String value) {
            addCriterion("field33 not like", value, "field33");
            return (Criteria) this;
        }

        public Criteria andField33In(List<String> values) {
            addCriterion("field33 in", values, "field33");
            return (Criteria) this;
        }

        public Criteria andField33NotIn(List<String> values) {
            addCriterion("field33 not in", values, "field33");
            return (Criteria) this;
        }

        public Criteria andField33Between(String value1, String value2) {
            addCriterion("field33 between", value1, value2, "field33");
            return (Criteria) this;
        }

        public Criteria andField33NotBetween(String value1, String value2) {
            addCriterion("field33 not between", value1, value2, "field33");
            return (Criteria) this;
        }

        public Criteria andField34IsNull() {
            addCriterion("field34 is null");
            return (Criteria) this;
        }

        public Criteria andField34IsNotNull() {
            addCriterion("field34 is not null");
            return (Criteria) this;
        }

        public Criteria andField34EqualTo(String value) {
            addCriterion("field34 =", value, "field34");
            return (Criteria) this;
        }

        public Criteria andField34NotEqualTo(String value) {
            addCriterion("field34 <>", value, "field34");
            return (Criteria) this;
        }

        public Criteria andField34GreaterThan(String value) {
            addCriterion("field34 >", value, "field34");
            return (Criteria) this;
        }

        public Criteria andField34GreaterThanOrEqualTo(String value) {
            addCriterion("field34 >=", value, "field34");
            return (Criteria) this;
        }

        public Criteria andField34LessThan(String value) {
            addCriterion("field34 <", value, "field34");
            return (Criteria) this;
        }

        public Criteria andField34LessThanOrEqualTo(String value) {
            addCriterion("field34 <=", value, "field34");
            return (Criteria) this;
        }

        public Criteria andField34Like(String value) {
            addCriterion("field34 like", value, "field34");
            return (Criteria) this;
        }

        public Criteria andField34NotLike(String value) {
            addCriterion("field34 not like", value, "field34");
            return (Criteria) this;
        }

        public Criteria andField34In(List<String> values) {
            addCriterion("field34 in", values, "field34");
            return (Criteria) this;
        }

        public Criteria andField34NotIn(List<String> values) {
            addCriterion("field34 not in", values, "field34");
            return (Criteria) this;
        }

        public Criteria andField34Between(String value1, String value2) {
            addCriterion("field34 between", value1, value2, "field34");
            return (Criteria) this;
        }

        public Criteria andField34NotBetween(String value1, String value2) {
            addCriterion("field34 not between", value1, value2, "field34");
            return (Criteria) this;
        }

        public Criteria andField35IsNull() {
            addCriterion("field35 is null");
            return (Criteria) this;
        }

        public Criteria andField35IsNotNull() {
            addCriterion("field35 is not null");
            return (Criteria) this;
        }

        public Criteria andField35EqualTo(String value) {
            addCriterion("field35 =", value, "field35");
            return (Criteria) this;
        }

        public Criteria andField35NotEqualTo(String value) {
            addCriterion("field35 <>", value, "field35");
            return (Criteria) this;
        }

        public Criteria andField35GreaterThan(String value) {
            addCriterion("field35 >", value, "field35");
            return (Criteria) this;
        }

        public Criteria andField35GreaterThanOrEqualTo(String value) {
            addCriterion("field35 >=", value, "field35");
            return (Criteria) this;
        }

        public Criteria andField35LessThan(String value) {
            addCriterion("field35 <", value, "field35");
            return (Criteria) this;
        }

        public Criteria andField35LessThanOrEqualTo(String value) {
            addCriterion("field35 <=", value, "field35");
            return (Criteria) this;
        }

        public Criteria andField35Like(String value) {
            addCriterion("field35 like", value, "field35");
            return (Criteria) this;
        }

        public Criteria andField35NotLike(String value) {
            addCriterion("field35 not like", value, "field35");
            return (Criteria) this;
        }

        public Criteria andField35In(List<String> values) {
            addCriterion("field35 in", values, "field35");
            return (Criteria) this;
        }

        public Criteria andField35NotIn(List<String> values) {
            addCriterion("field35 not in", values, "field35");
            return (Criteria) this;
        }

        public Criteria andField35Between(String value1, String value2) {
            addCriterion("field35 between", value1, value2, "field35");
            return (Criteria) this;
        }

        public Criteria andField35NotBetween(String value1, String value2) {
            addCriterion("field35 not between", value1, value2, "field35");
            return (Criteria) this;
        }

        public Criteria andField36IsNull() {
            addCriterion("field36 is null");
            return (Criteria) this;
        }

        public Criteria andField36IsNotNull() {
            addCriterion("field36 is not null");
            return (Criteria) this;
        }

        public Criteria andField36EqualTo(String value) {
            addCriterion("field36 =", value, "field36");
            return (Criteria) this;
        }

        public Criteria andField36NotEqualTo(String value) {
            addCriterion("field36 <>", value, "field36");
            return (Criteria) this;
        }

        public Criteria andField36GreaterThan(String value) {
            addCriterion("field36 >", value, "field36");
            return (Criteria) this;
        }

        public Criteria andField36GreaterThanOrEqualTo(String value) {
            addCriterion("field36 >=", value, "field36");
            return (Criteria) this;
        }

        public Criteria andField36LessThan(String value) {
            addCriterion("field36 <", value, "field36");
            return (Criteria) this;
        }

        public Criteria andField36LessThanOrEqualTo(String value) {
            addCriterion("field36 <=", value, "field36");
            return (Criteria) this;
        }

        public Criteria andField36Like(String value) {
            addCriterion("field36 like", value, "field36");
            return (Criteria) this;
        }

        public Criteria andField36NotLike(String value) {
            addCriterion("field36 not like", value, "field36");
            return (Criteria) this;
        }

        public Criteria andField36In(List<String> values) {
            addCriterion("field36 in", values, "field36");
            return (Criteria) this;
        }

        public Criteria andField36NotIn(List<String> values) {
            addCriterion("field36 not in", values, "field36");
            return (Criteria) this;
        }

        public Criteria andField36Between(String value1, String value2) {
            addCriterion("field36 between", value1, value2, "field36");
            return (Criteria) this;
        }

        public Criteria andField36NotBetween(String value1, String value2) {
            addCriterion("field36 not between", value1, value2, "field36");
            return (Criteria) this;
        }

        public Criteria andField4IsNull() {
            addCriterion("field4 is null");
            return (Criteria) this;
        }

        public Criteria andField4IsNotNull() {
            addCriterion("field4 is not null");
            return (Criteria) this;
        }

        public Criteria andField4EqualTo(String value) {
            addCriterion("field4 =", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4NotEqualTo(String value) {
            addCriterion("field4 <>", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4GreaterThan(String value) {
            addCriterion("field4 >", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4GreaterThanOrEqualTo(String value) {
            addCriterion("field4 >=", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4LessThan(String value) {
            addCriterion("field4 <", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4LessThanOrEqualTo(String value) {
            addCriterion("field4 <=", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4Like(String value) {
            addCriterion("field4 like", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4NotLike(String value) {
            addCriterion("field4 not like", value, "field4");
            return (Criteria) this;
        }

        public Criteria andField4In(List<String> values) {
            addCriterion("field4 in", values, "field4");
            return (Criteria) this;
        }

        public Criteria andField4NotIn(List<String> values) {
            addCriterion("field4 not in", values, "field4");
            return (Criteria) this;
        }

        public Criteria andField4Between(String value1, String value2) {
            addCriterion("field4 between", value1, value2, "field4");
            return (Criteria) this;
        }

        public Criteria andField4NotBetween(String value1, String value2) {
            addCriterion("field4 not between", value1, value2, "field4");
            return (Criteria) this;
        }

        public Criteria andField5IsNull() {
            addCriterion("field5 is null");
            return (Criteria) this;
        }

        public Criteria andField5IsNotNull() {
            addCriterion("field5 is not null");
            return (Criteria) this;
        }

        public Criteria andField5EqualTo(String value) {
            addCriterion("field5 =", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5NotEqualTo(String value) {
            addCriterion("field5 <>", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5GreaterThan(String value) {
            addCriterion("field5 >", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5GreaterThanOrEqualTo(String value) {
            addCriterion("field5 >=", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5LessThan(String value) {
            addCriterion("field5 <", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5LessThanOrEqualTo(String value) {
            addCriterion("field5 <=", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5Like(String value) {
            addCriterion("field5 like", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5NotLike(String value) {
            addCriterion("field5 not like", value, "field5");
            return (Criteria) this;
        }

        public Criteria andField5In(List<String> values) {
            addCriterion("field5 in", values, "field5");
            return (Criteria) this;
        }

        public Criteria andField5NotIn(List<String> values) {
            addCriterion("field5 not in", values, "field5");
            return (Criteria) this;
        }

        public Criteria andField5Between(String value1, String value2) {
            addCriterion("field5 between", value1, value2, "field5");
            return (Criteria) this;
        }

        public Criteria andField5NotBetween(String value1, String value2) {
            addCriterion("field5 not between", value1, value2, "field5");
            return (Criteria) this;
        }

        public Criteria andField6IsNull() {
            addCriterion("field6 is null");
            return (Criteria) this;
        }

        public Criteria andField6IsNotNull() {
            addCriterion("field6 is not null");
            return (Criteria) this;
        }

        public Criteria andField6EqualTo(String value) {
            addCriterion("field6 =", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6NotEqualTo(String value) {
            addCriterion("field6 <>", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6GreaterThan(String value) {
            addCriterion("field6 >", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6GreaterThanOrEqualTo(String value) {
            addCriterion("field6 >=", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6LessThan(String value) {
            addCriterion("field6 <", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6LessThanOrEqualTo(String value) {
            addCriterion("field6 <=", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6Like(String value) {
            addCriterion("field6 like", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6NotLike(String value) {
            addCriterion("field6 not like", value, "field6");
            return (Criteria) this;
        }

        public Criteria andField6In(List<String> values) {
            addCriterion("field6 in", values, "field6");
            return (Criteria) this;
        }

        public Criteria andField6NotIn(List<String> values) {
            addCriterion("field6 not in", values, "field6");
            return (Criteria) this;
        }

        public Criteria andField6Between(String value1, String value2) {
            addCriterion("field6 between", value1, value2, "field6");
            return (Criteria) this;
        }

        public Criteria andField6NotBetween(String value1, String value2) {
            addCriterion("field6 not between", value1, value2, "field6");
            return (Criteria) this;
        }

        public Criteria andField7IsNull() {
            addCriterion("field7 is null");
            return (Criteria) this;
        }

        public Criteria andField7IsNotNull() {
            addCriterion("field7 is not null");
            return (Criteria) this;
        }

        public Criteria andField7EqualTo(String value) {
            addCriterion("field7 =", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7NotEqualTo(String value) {
            addCriterion("field7 <>", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7GreaterThan(String value) {
            addCriterion("field7 >", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7GreaterThanOrEqualTo(String value) {
            addCriterion("field7 >=", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7LessThan(String value) {
            addCriterion("field7 <", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7LessThanOrEqualTo(String value) {
            addCriterion("field7 <=", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7Like(String value) {
            addCriterion("field7 like", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7NotLike(String value) {
            addCriterion("field7 not like", value, "field7");
            return (Criteria) this;
        }

        public Criteria andField7In(List<String> values) {
            addCriterion("field7 in", values, "field7");
            return (Criteria) this;
        }

        public Criteria andField7NotIn(List<String> values) {
            addCriterion("field7 not in", values, "field7");
            return (Criteria) this;
        }

        public Criteria andField7Between(String value1, String value2) {
            addCriterion("field7 between", value1, value2, "field7");
            return (Criteria) this;
        }

        public Criteria andField7NotBetween(String value1, String value2) {
            addCriterion("field7 not between", value1, value2, "field7");
            return (Criteria) this;
        }

        public Criteria andField8IsNull() {
            addCriterion("field8 is null");
            return (Criteria) this;
        }

        public Criteria andField8IsNotNull() {
            addCriterion("field8 is not null");
            return (Criteria) this;
        }

        public Criteria andField8EqualTo(String value) {
            addCriterion("field8 =", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8NotEqualTo(String value) {
            addCriterion("field8 <>", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8GreaterThan(String value) {
            addCriterion("field8 >", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8GreaterThanOrEqualTo(String value) {
            addCriterion("field8 >=", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8LessThan(String value) {
            addCriterion("field8 <", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8LessThanOrEqualTo(String value) {
            addCriterion("field8 <=", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8Like(String value) {
            addCriterion("field8 like", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8NotLike(String value) {
            addCriterion("field8 not like", value, "field8");
            return (Criteria) this;
        }

        public Criteria andField8In(List<String> values) {
            addCriterion("field8 in", values, "field8");
            return (Criteria) this;
        }

        public Criteria andField8NotIn(List<String> values) {
            addCriterion("field8 not in", values, "field8");
            return (Criteria) this;
        }

        public Criteria andField8Between(String value1, String value2) {
            addCriterion("field8 between", value1, value2, "field8");
            return (Criteria) this;
        }

        public Criteria andField8NotBetween(String value1, String value2) {
            addCriterion("field8 not between", value1, value2, "field8");
            return (Criteria) this;
        }

        public Criteria andField9IsNull() {
            addCriterion("field9 is null");
            return (Criteria) this;
        }

        public Criteria andField9IsNotNull() {
            addCriterion("field9 is not null");
            return (Criteria) this;
        }

        public Criteria andField9EqualTo(String value) {
            addCriterion("field9 =", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9NotEqualTo(String value) {
            addCriterion("field9 <>", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9GreaterThan(String value) {
            addCriterion("field9 >", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9GreaterThanOrEqualTo(String value) {
            addCriterion("field9 >=", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9LessThan(String value) {
            addCriterion("field9 <", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9LessThanOrEqualTo(String value) {
            addCriterion("field9 <=", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9Like(String value) {
            addCriterion("field9 like", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9NotLike(String value) {
            addCriterion("field9 not like", value, "field9");
            return (Criteria) this;
        }

        public Criteria andField9In(List<String> values) {
            addCriterion("field9 in", values, "field9");
            return (Criteria) this;
        }

        public Criteria andField9NotIn(List<String> values) {
            addCriterion("field9 not in", values, "field9");
            return (Criteria) this;
        }

        public Criteria andField9Between(String value1, String value2) {
            addCriterion("field9 between", value1, value2, "field9");
            return (Criteria) this;
        }

        public Criteria andField9NotBetween(String value1, String value2) {
            addCriterion("field9 not between", value1, value2, "field9");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeIsNull() {
            addCriterion("id_card_type is null");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeIsNotNull() {
            addCriterion("id_card_type is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeEqualTo(String value) {
            addCriterion("id_card_type =", value, "idCardType");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeNotEqualTo(String value) {
            addCriterion("id_card_type <>", value, "idCardType");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeGreaterThan(String value) {
            addCriterion("id_card_type >", value, "idCardType");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeGreaterThanOrEqualTo(String value) {
            addCriterion("id_card_type >=", value, "idCardType");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeLessThan(String value) {
            addCriterion("id_card_type <", value, "idCardType");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeLessThanOrEqualTo(String value) {
            addCriterion("id_card_type <=", value, "idCardType");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeLike(String value) {
            addCriterion("id_card_type like", value, "idCardType");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeNotLike(String value) {
            addCriterion("id_card_type not like", value, "idCardType");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeIn(List<String> values) {
            addCriterion("id_card_type in", values, "idCardType");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeNotIn(List<String> values) {
            addCriterion("id_card_type not in", values, "idCardType");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeBetween(String value1, String value2) {
            addCriterion("id_card_type between", value1, value2, "idCardType");
            return (Criteria) this;
        }

        public Criteria andIdCardTypeNotBetween(String value1, String value2) {
            addCriterion("id_card_type not between", value1, value2, "idCardType");
            return (Criteria) this;
        }

        public Criteria andIdNoUriIsNull() {
            addCriterion("id_no_uri is null");
            return (Criteria) this;
        }

        public Criteria andIdNoUriIsNotNull() {
            addCriterion("id_no_uri is not null");
            return (Criteria) this;
        }

        public Criteria andIdNoUriEqualTo(String value) {
            addCriterion("id_no_uri =", value, "idNoUri");
            return (Criteria) this;
        }

        public Criteria andIdNoUriNotEqualTo(String value) {
            addCriterion("id_no_uri <>", value, "idNoUri");
            return (Criteria) this;
        }

        public Criteria andIdNoUriGreaterThan(String value) {
            addCriterion("id_no_uri >", value, "idNoUri");
            return (Criteria) this;
        }

        public Criteria andIdNoUriGreaterThanOrEqualTo(String value) {
            addCriterion("id_no_uri >=", value, "idNoUri");
            return (Criteria) this;
        }

        public Criteria andIdNoUriLessThan(String value) {
            addCriterion("id_no_uri <", value, "idNoUri");
            return (Criteria) this;
        }

        public Criteria andIdNoUriLessThanOrEqualTo(String value) {
            addCriterion("id_no_uri <=", value, "idNoUri");
            return (Criteria) this;
        }

        public Criteria andIdNoUriLike(String value) {
            addCriterion("id_no_uri like", value, "idNoUri");
            return (Criteria) this;
        }

        public Criteria andIdNoUriNotLike(String value) {
            addCriterion("id_no_uri not like", value, "idNoUri");
            return (Criteria) this;
        }

        public Criteria andIdNoUriIn(List<String> values) {
            addCriterion("id_no_uri in", values, "idNoUri");
            return (Criteria) this;
        }

        public Criteria andIdNoUriNotIn(List<String> values) {
            addCriterion("id_no_uri not in", values, "idNoUri");
            return (Criteria) this;
        }

        public Criteria andIdNoUriBetween(String value1, String value2) {
            addCriterion("id_no_uri between", value1, value2, "idNoUri");
            return (Criteria) this;
        }

        public Criteria andIdNoUriNotBetween(String value1, String value2) {
            addCriterion("id_no_uri not between", value1, value2, "idNoUri");
            return (Criteria) this;
        }

        public Criteria andIdnoIsNull() {
            addCriterion("idno is null");
            return (Criteria) this;
        }

        public Criteria andIdnoIsNotNull() {
            addCriterion("idno is not null");
            return (Criteria) this;
        }

        public Criteria andIdnoEqualTo(String value) {
            addCriterion("idno =", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoNotEqualTo(String value) {
            addCriterion("idno <>", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoGreaterThan(String value) {
            addCriterion("idno >", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoGreaterThanOrEqualTo(String value) {
            addCriterion("idno >=", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoLessThan(String value) {
            addCriterion("idno <", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoLessThanOrEqualTo(String value) {
            addCriterion("idno <=", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoLike(String value) {
            addCriterion("idno like", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoNotLike(String value) {
            addCriterion("idno not like", value, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoIn(List<String> values) {
            addCriterion("idno in", values, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoNotIn(List<String> values) {
            addCriterion("idno not in", values, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoBetween(String value1, String value2) {
            addCriterion("idno between", value1, value2, "idno");
            return (Criteria) this;
        }

        public Criteria andIdnoNotBetween(String value1, String value2) {
            addCriterion("idno not between", value1, value2, "idno");
            return (Criteria) this;
        }

        public Criteria andJobStatusIsNull() {
            addCriterion("job_status is null");
            return (Criteria) this;
        }

        public Criteria andJobStatusIsNotNull() {
            addCriterion("job_status is not null");
            return (Criteria) this;
        }

        public Criteria andJobStatusEqualTo(String value) {
            addCriterion("job_status =", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotEqualTo(String value) {
            addCriterion("job_status <>", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusGreaterThan(String value) {
            addCriterion("job_status >", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusGreaterThanOrEqualTo(String value) {
            addCriterion("job_status >=", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusLessThan(String value) {
            addCriterion("job_status <", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusLessThanOrEqualTo(String value) {
            addCriterion("job_status <=", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusLike(String value) {
            addCriterion("job_status like", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotLike(String value) {
            addCriterion("job_status not like", value, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusIn(List<String> values) {
            addCriterion("job_status in", values, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotIn(List<String> values) {
            addCriterion("job_status not in", values, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusBetween(String value1, String value2) {
            addCriterion("job_status between", value1, value2, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStatusNotBetween(String value1, String value2) {
            addCriterion("job_status not between", value1, value2, "jobStatus");
            return (Criteria) this;
        }

        public Criteria andJobStepIsNull() {
            addCriterion("job_step is null");
            return (Criteria) this;
        }

        public Criteria andJobStepIsNotNull() {
            addCriterion("job_step is not null");
            return (Criteria) this;
        }

        public Criteria andJobStepEqualTo(Integer value) {
            addCriterion("job_step =", value, "jobStep");
            return (Criteria) this;
        }

        public Criteria andJobStepNotEqualTo(Integer value) {
            addCriterion("job_step <>", value, "jobStep");
            return (Criteria) this;
        }

        public Criteria andJobStepGreaterThan(Integer value) {
            addCriterion("job_step >", value, "jobStep");
            return (Criteria) this;
        }

        public Criteria andJobStepGreaterThanOrEqualTo(Integer value) {
            addCriterion("job_step >=", value, "jobStep");
            return (Criteria) this;
        }

        public Criteria andJobStepLessThan(Integer value) {
            addCriterion("job_step <", value, "jobStep");
            return (Criteria) this;
        }

        public Criteria andJobStepLessThanOrEqualTo(Integer value) {
            addCriterion("job_step <=", value, "jobStep");
            return (Criteria) this;
        }

        public Criteria andJobStepIn(List<Integer> values) {
            addCriterion("job_step in", values, "jobStep");
            return (Criteria) this;
        }

        public Criteria andJobStepNotIn(List<Integer> values) {
            addCriterion("job_step not in", values, "jobStep");
            return (Criteria) this;
        }

        public Criteria andJobStepBetween(Integer value1, Integer value2) {
            addCriterion("job_step between", value1, value2, "jobStep");
            return (Criteria) this;
        }

        public Criteria andJobStepNotBetween(Integer value1, Integer value2) {
            addCriterion("job_step not between", value1, value2, "jobStep");
            return (Criteria) this;
        }

        public Criteria andLoanUriIsNull() {
            addCriterion("loan_uri is null");
            return (Criteria) this;
        }

        public Criteria andLoanUriIsNotNull() {
            addCriterion("loan_uri is not null");
            return (Criteria) this;
        }

        public Criteria andLoanUriEqualTo(String value) {
            addCriterion("loan_uri =", value, "loanUri");
            return (Criteria) this;
        }

        public Criteria andLoanUriNotEqualTo(String value) {
            addCriterion("loan_uri <>", value, "loanUri");
            return (Criteria) this;
        }

        public Criteria andLoanUriGreaterThan(String value) {
            addCriterion("loan_uri >", value, "loanUri");
            return (Criteria) this;
        }

        public Criteria andLoanUriGreaterThanOrEqualTo(String value) {
            addCriterion("loan_uri >=", value, "loanUri");
            return (Criteria) this;
        }

        public Criteria andLoanUriLessThan(String value) {
            addCriterion("loan_uri <", value, "loanUri");
            return (Criteria) this;
        }

        public Criteria andLoanUriLessThanOrEqualTo(String value) {
            addCriterion("loan_uri <=", value, "loanUri");
            return (Criteria) this;
        }

        public Criteria andLoanUriLike(String value) {
            addCriterion("loan_uri like", value, "loanUri");
            return (Criteria) this;
        }

        public Criteria andLoanUriNotLike(String value) {
            addCriterion("loan_uri not like", value, "loanUri");
            return (Criteria) this;
        }

        public Criteria andLoanUriIn(List<String> values) {
            addCriterion("loan_uri in", values, "loanUri");
            return (Criteria) this;
        }

        public Criteria andLoanUriNotIn(List<String> values) {
            addCriterion("loan_uri not in", values, "loanUri");
            return (Criteria) this;
        }

        public Criteria andLoanUriBetween(String value1, String value2) {
            addCriterion("loan_uri between", value1, value2, "loanUri");
            return (Criteria) this;
        }

        public Criteria andLoanUriNotBetween(String value1, String value2) {
            addCriterion("loan_uri not between", value1, value2, "loanUri");
            return (Criteria) this;
        }

        public Criteria andMobilesIsNull() {
            addCriterion("mobiles is null");
            return (Criteria) this;
        }

        public Criteria andMobilesIsNotNull() {
            addCriterion("mobiles is not null");
            return (Criteria) this;
        }

        public Criteria andMobilesEqualTo(String value) {
            addCriterion("mobiles =", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesNotEqualTo(String value) {
            addCriterion("mobiles <>", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesGreaterThan(String value) {
            addCriterion("mobiles >", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesGreaterThanOrEqualTo(String value) {
            addCriterion("mobiles >=", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesLessThan(String value) {
            addCriterion("mobiles <", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesLessThanOrEqualTo(String value) {
            addCriterion("mobiles <=", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesLike(String value) {
            addCriterion("mobiles like", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesNotLike(String value) {
            addCriterion("mobiles not like", value, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesIn(List<String> values) {
            addCriterion("mobiles in", values, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesNotIn(List<String> values) {
            addCriterion("mobiles not in", values, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesBetween(String value1, String value2) {
            addCriterion("mobiles between", value1, value2, "mobiles");
            return (Criteria) this;
        }

        public Criteria andMobilesNotBetween(String value1, String value2) {
            addCriterion("mobiles not between", value1, value2, "mobiles");
            return (Criteria) this;
        }

        public Criteria andOverdueDateIsNull() {
            addCriterion("overdue_date is null");
            return (Criteria) this;
        }

        public Criteria andOverdueDateIsNotNull() {
            addCriterion("overdue_date is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueDateEqualTo(String value) {
            addCriterion("overdue_date =", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateNotEqualTo(String value) {
            addCriterion("overdue_date <>", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateGreaterThan(String value) {
            addCriterion("overdue_date >", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateGreaterThanOrEqualTo(String value) {
            addCriterion("overdue_date >=", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateLessThan(String value) {
            addCriterion("overdue_date <", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateLessThanOrEqualTo(String value) {
            addCriterion("overdue_date <=", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateLike(String value) {
            addCriterion("overdue_date like", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateNotLike(String value) {
            addCriterion("overdue_date not like", value, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateIn(List<String> values) {
            addCriterion("overdue_date in", values, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateNotIn(List<String> values) {
            addCriterion("overdue_date not in", values, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateBetween(String value1, String value2) {
            addCriterion("overdue_date between", value1, value2, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateNotBetween(String value1, String value2) {
            addCriterion("overdue_date not between", value1, value2, "overdueDate");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitIsNull() {
            addCriterion("overdue_date_unit is null");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitIsNotNull() {
            addCriterion("overdue_date_unit is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitEqualTo(String value) {
            addCriterion("overdue_date_unit =", value, "overdueDateUnit");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitNotEqualTo(String value) {
            addCriterion("overdue_date_unit <>", value, "overdueDateUnit");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitGreaterThan(String value) {
            addCriterion("overdue_date_unit >", value, "overdueDateUnit");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitGreaterThanOrEqualTo(String value) {
            addCriterion("overdue_date_unit >=", value, "overdueDateUnit");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitLessThan(String value) {
            addCriterion("overdue_date_unit <", value, "overdueDateUnit");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitLessThanOrEqualTo(String value) {
            addCriterion("overdue_date_unit <=", value, "overdueDateUnit");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitLike(String value) {
            addCriterion("overdue_date_unit like", value, "overdueDateUnit");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitNotLike(String value) {
            addCriterion("overdue_date_unit not like", value, "overdueDateUnit");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitIn(List<String> values) {
            addCriterion("overdue_date_unit in", values, "overdueDateUnit");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitNotIn(List<String> values) {
            addCriterion("overdue_date_unit not in", values, "overdueDateUnit");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitBetween(String value1, String value2) {
            addCriterion("overdue_date_unit between", value1, value2, "overdueDateUnit");
            return (Criteria) this;
        }

        public Criteria andOverdueDateUnitNotBetween(String value1, String value2) {
            addCriterion("overdue_date_unit not between", value1, value2, "overdueDateUnit");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesIsNull() {
            addCriterion("overdue_dates is null");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesIsNotNull() {
            addCriterion("overdue_dates is not null");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesEqualTo(String value) {
            addCriterion("overdue_dates =", value, "overdueDates");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesNotEqualTo(String value) {
            addCriterion("overdue_dates <>", value, "overdueDates");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesGreaterThan(String value) {
            addCriterion("overdue_dates >", value, "overdueDates");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesGreaterThanOrEqualTo(String value) {
            addCriterion("overdue_dates >=", value, "overdueDates");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesLessThan(String value) {
            addCriterion("overdue_dates <", value, "overdueDates");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesLessThanOrEqualTo(String value) {
            addCriterion("overdue_dates <=", value, "overdueDates");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesLike(String value) {
            addCriterion("overdue_dates like", value, "overdueDates");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesNotLike(String value) {
            addCriterion("overdue_dates not like", value, "overdueDates");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesIn(List<String> values) {
            addCriterion("overdue_dates in", values, "overdueDates");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesNotIn(List<String> values) {
            addCriterion("overdue_dates not in", values, "overdueDates");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesBetween(String value1, String value2) {
            addCriterion("overdue_dates between", value1, value2, "overdueDates");
            return (Criteria) this;
        }

        public Criteria andOverdueDatesNotBetween(String value1, String value2) {
            addCriterion("overdue_dates not between", value1, value2, "overdueDates");
            return (Criteria) this;
        }

        public Criteria andPaymentDateIsNull() {
            addCriterion("payment_date is null");
            return (Criteria) this;
        }

        public Criteria andPaymentDateIsNotNull() {
            addCriterion("payment_date is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentDateEqualTo(String value) {
            addCriterion("payment_date =", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateNotEqualTo(String value) {
            addCriterion("payment_date <>", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateGreaterThan(String value) {
            addCriterion("payment_date >", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateGreaterThanOrEqualTo(String value) {
            addCriterion("payment_date >=", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateLessThan(String value) {
            addCriterion("payment_date <", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateLessThanOrEqualTo(String value) {
            addCriterion("payment_date <=", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateLike(String value) {
            addCriterion("payment_date like", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateNotLike(String value) {
            addCriterion("payment_date not like", value, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateIn(List<String> values) {
            addCriterion("payment_date in", values, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateNotIn(List<String> values) {
            addCriterion("payment_date not in", values, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateBetween(String value1, String value2) {
            addCriterion("payment_date between", value1, value2, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPaymentDateNotBetween(String value1, String value2) {
            addCriterion("payment_date not between", value1, value2, "paymentDate");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressIsNull() {
            addCriterion("permanent_address is null");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressIsNotNull() {
            addCriterion("permanent_address is not null");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressEqualTo(String value) {
            addCriterion("permanent_address =", value, "permanentAddress");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressNotEqualTo(String value) {
            addCriterion("permanent_address <>", value, "permanentAddress");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressGreaterThan(String value) {
            addCriterion("permanent_address >", value, "permanentAddress");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressGreaterThanOrEqualTo(String value) {
            addCriterion("permanent_address >=", value, "permanentAddress");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressLessThan(String value) {
            addCriterion("permanent_address <", value, "permanentAddress");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressLessThanOrEqualTo(String value) {
            addCriterion("permanent_address <=", value, "permanentAddress");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressLike(String value) {
            addCriterion("permanent_address like", value, "permanentAddress");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressNotLike(String value) {
            addCriterion("permanent_address not like", value, "permanentAddress");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressIn(List<String> values) {
            addCriterion("permanent_address in", values, "permanentAddress");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressNotIn(List<String> values) {
            addCriterion("permanent_address not in", values, "permanentAddress");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressBetween(String value1, String value2) {
            addCriterion("permanent_address between", value1, value2, "permanentAddress");
            return (Criteria) this;
        }

        public Criteria andPermanentAddressNotBetween(String value1, String value2) {
            addCriterion("permanent_address not between", value1, value2, "permanentAddress");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNull() {
            addCriterion("product_code is null");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNotNull() {
            addCriterion("product_code is not null");
            return (Criteria) this;
        }

        public Criteria andProductCodeEqualTo(String value) {
            addCriterion("product_code =", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotEqualTo(String value) {
            addCriterion("product_code <>", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThan(String value) {
            addCriterion("product_code >", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThanOrEqualTo(String value) {
            addCriterion("product_code >=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThan(String value) {
            addCriterion("product_code <", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThanOrEqualTo(String value) {
            addCriterion("product_code <=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLike(String value) {
            addCriterion("product_code like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotLike(String value) {
            addCriterion("product_code not like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeIn(List<String> values) {
            addCriterion("product_code in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotIn(List<String> values) {
            addCriterion("product_code not in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeBetween(String value1, String value2) {
            addCriterion("product_code between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotBetween(String value1, String value2) {
            addCriterion("product_code not between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andServiceUriIsNull() {
            addCriterion("service_uri is null");
            return (Criteria) this;
        }

        public Criteria andServiceUriIsNotNull() {
            addCriterion("service_uri is not null");
            return (Criteria) this;
        }

        public Criteria andServiceUriEqualTo(String value) {
            addCriterion("service_uri =", value, "serviceUri");
            return (Criteria) this;
        }

        public Criteria andServiceUriNotEqualTo(String value) {
            addCriterion("service_uri <>", value, "serviceUri");
            return (Criteria) this;
        }

        public Criteria andServiceUriGreaterThan(String value) {
            addCriterion("service_uri >", value, "serviceUri");
            return (Criteria) this;
        }

        public Criteria andServiceUriGreaterThanOrEqualTo(String value) {
            addCriterion("service_uri >=", value, "serviceUri");
            return (Criteria) this;
        }

        public Criteria andServiceUriLessThan(String value) {
            addCriterion("service_uri <", value, "serviceUri");
            return (Criteria) this;
        }

        public Criteria andServiceUriLessThanOrEqualTo(String value) {
            addCriterion("service_uri <=", value, "serviceUri");
            return (Criteria) this;
        }

        public Criteria andServiceUriLike(String value) {
            addCriterion("service_uri like", value, "serviceUri");
            return (Criteria) this;
        }

        public Criteria andServiceUriNotLike(String value) {
            addCriterion("service_uri not like", value, "serviceUri");
            return (Criteria) this;
        }

        public Criteria andServiceUriIn(List<String> values) {
            addCriterion("service_uri in", values, "serviceUri");
            return (Criteria) this;
        }

        public Criteria andServiceUriNotIn(List<String> values) {
            addCriterion("service_uri not in", values, "serviceUri");
            return (Criteria) this;
        }

        public Criteria andServiceUriBetween(String value1, String value2) {
            addCriterion("service_uri between", value1, value2, "serviceUri");
            return (Criteria) this;
        }

        public Criteria andServiceUriNotBetween(String value1, String value2) {
            addCriterion("service_uri not between", value1, value2, "serviceUri");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriIsNull() {
            addCriterion("swap_debt_uri is null");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriIsNotNull() {
            addCriterion("swap_debt_uri is not null");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriEqualTo(String value) {
            addCriterion("swap_debt_uri =", value, "swapDebtUri");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriNotEqualTo(String value) {
            addCriterion("swap_debt_uri <>", value, "swapDebtUri");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriGreaterThan(String value) {
            addCriterion("swap_debt_uri >", value, "swapDebtUri");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriGreaterThanOrEqualTo(String value) {
            addCriterion("swap_debt_uri >=", value, "swapDebtUri");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriLessThan(String value) {
            addCriterion("swap_debt_uri <", value, "swapDebtUri");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriLessThanOrEqualTo(String value) {
            addCriterion("swap_debt_uri <=", value, "swapDebtUri");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriLike(String value) {
            addCriterion("swap_debt_uri like", value, "swapDebtUri");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriNotLike(String value) {
            addCriterion("swap_debt_uri not like", value, "swapDebtUri");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriIn(List<String> values) {
            addCriterion("swap_debt_uri in", values, "swapDebtUri");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriNotIn(List<String> values) {
            addCriterion("swap_debt_uri not in", values, "swapDebtUri");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriBetween(String value1, String value2) {
            addCriterion("swap_debt_uri between", value1, value2, "swapDebtUri");
            return (Criteria) this;
        }

        public Criteria andSwapDebtUriNotBetween(String value1, String value2) {
            addCriterion("swap_debt_uri not between", value1, value2, "swapDebtUri");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsIsNull() {
            addCriterion("total_periods is null");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsIsNotNull() {
            addCriterion("total_periods is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsEqualTo(String value) {
            addCriterion("total_periods =", value, "totalPeriods");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsNotEqualTo(String value) {
            addCriterion("total_periods <>", value, "totalPeriods");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsGreaterThan(String value) {
            addCriterion("total_periods >", value, "totalPeriods");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsGreaterThanOrEqualTo(String value) {
            addCriterion("total_periods >=", value, "totalPeriods");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsLessThan(String value) {
            addCriterion("total_periods <", value, "totalPeriods");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsLessThanOrEqualTo(String value) {
            addCriterion("total_periods <=", value, "totalPeriods");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsLike(String value) {
            addCriterion("total_periods like", value, "totalPeriods");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsNotLike(String value) {
            addCriterion("total_periods not like", value, "totalPeriods");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsIn(List<String> values) {
            addCriterion("total_periods in", values, "totalPeriods");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsNotIn(List<String> values) {
            addCriterion("total_periods not in", values, "totalPeriods");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsBetween(String value1, String value2) {
            addCriterion("total_periods between", value1, value2, "totalPeriods");
            return (Criteria) this;
        }

        public Criteria andTotalPeriodsNotBetween(String value1, String value2) {
            addCriterion("total_periods not between", value1, value2, "totalPeriods");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNull() {
            addCriterion("task_id is null");
            return (Criteria) this;
        }

        public Criteria andTaskIdIsNotNull() {
            addCriterion("task_id is not null");
            return (Criteria) this;
        }

        public Criteria andTaskIdEqualTo(String value) {
            addCriterion("task_id =", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotEqualTo(String value) {
            addCriterion("task_id <>", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThan(String value) {
            addCriterion("task_id >", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("task_id >=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThan(String value) {
            addCriterion("task_id <", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLessThanOrEqualTo(String value) {
            addCriterion("task_id <=", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdLike(String value) {
            addCriterion("task_id like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotLike(String value) {
            addCriterion("task_id not like", value, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdIn(List<String> values) {
            addCriterion("task_id in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotIn(List<String> values) {
            addCriterion("task_id not in", values, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdBetween(String value1, String value2) {
            addCriterion("task_id between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andTaskIdNotBetween(String value1, String value2) {
            addCriterion("task_id not between", value1, value2, "taskId");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andContactsIsNull() {
            addCriterion("contacts is null");
            return (Criteria) this;
        }

        public Criteria andContactsIsNotNull() {
            addCriterion("contacts is not null");
            return (Criteria) this;
        }

        public Criteria andContactsEqualTo(String value) {
            addCriterion("contacts =", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotEqualTo(String value) {
            addCriterion("contacts <>", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsGreaterThan(String value) {
            addCriterion("contacts >", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsGreaterThanOrEqualTo(String value) {
            addCriterion("contacts >=", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLessThan(String value) {
            addCriterion("contacts <", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLessThanOrEqualTo(String value) {
            addCriterion("contacts <=", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLike(String value) {
            addCriterion("contacts like", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotLike(String value) {
            addCriterion("contacts not like", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsIn(List<String> values) {
            addCriterion("contacts in", values, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotIn(List<String> values) {
            addCriterion("contacts not in", values, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsBetween(String value1, String value2) {
            addCriterion("contacts between", value1, value2, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotBetween(String value1, String value2) {
            addCriterion("contacts not between", value1, value2, "contacts");
            return (Criteria) this;
        }

        public Criteria andEmailsIsNull() {
            addCriterion("emails is null");
            return (Criteria) this;
        }

        public Criteria andEmailsIsNotNull() {
            addCriterion("emails is not null");
            return (Criteria) this;
        }

        public Criteria andEmailsEqualTo(String value) {
            addCriterion("emails =", value, "emails");
            return (Criteria) this;
        }

        public Criteria andEmailsNotEqualTo(String value) {
            addCriterion("emails <>", value, "emails");
            return (Criteria) this;
        }

        public Criteria andEmailsGreaterThan(String value) {
            addCriterion("emails >", value, "emails");
            return (Criteria) this;
        }

        public Criteria andEmailsGreaterThanOrEqualTo(String value) {
            addCriterion("emails >=", value, "emails");
            return (Criteria) this;
        }

        public Criteria andEmailsLessThan(String value) {
            addCriterion("emails <", value, "emails");
            return (Criteria) this;
        }

        public Criteria andEmailsLessThanOrEqualTo(String value) {
            addCriterion("emails <=", value, "emails");
            return (Criteria) this;
        }

        public Criteria andEmailsLike(String value) {
            addCriterion("emails like", value, "emails");
            return (Criteria) this;
        }

        public Criteria andEmailsNotLike(String value) {
            addCriterion("emails not like", value, "emails");
            return (Criteria) this;
        }

        public Criteria andEmailsIn(List<String> values) {
            addCriterion("emails in", values, "emails");
            return (Criteria) this;
        }

        public Criteria andEmailsNotIn(List<String> values) {
            addCriterion("emails not in", values, "emails");
            return (Criteria) this;
        }

        public Criteria andEmailsBetween(String value1, String value2) {
            addCriterion("emails between", value1, value2, "emails");
            return (Criteria) this;
        }

        public Criteria andEmailsNotBetween(String value1, String value2) {
            addCriterion("emails not between", value1, value2, "emails");
            return (Criteria) this;
        }

        public Criteria andIdentityNoIsNull() {
            addCriterion("identity_no is null");
            return (Criteria) this;
        }

        public Criteria andIdentityNoIsNotNull() {
            addCriterion("identity_no is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityNoEqualTo(String value) {
            addCriterion("identity_no =", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoNotEqualTo(String value) {
            addCriterion("identity_no <>", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoGreaterThan(String value) {
            addCriterion("identity_no >", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoGreaterThanOrEqualTo(String value) {
            addCriterion("identity_no >=", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoLessThan(String value) {
            addCriterion("identity_no <", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoLessThanOrEqualTo(String value) {
            addCriterion("identity_no <=", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoLike(String value) {
            addCriterion("identity_no like", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoNotLike(String value) {
            addCriterion("identity_no not like", value, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoIn(List<String> values) {
            addCriterion("identity_no in", values, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoNotIn(List<String> values) {
            addCriterion("identity_no not in", values, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoBetween(String value1, String value2) {
            addCriterion("identity_no between", value1, value2, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityNoNotBetween(String value1, String value2) {
            addCriterion("identity_no not between", value1, value2, "identityNo");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeIsNull() {
            addCriterion("identity_type is null");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeIsNotNull() {
            addCriterion("identity_type is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeEqualTo(String value) {
            addCriterion("identity_type =", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeNotEqualTo(String value) {
            addCriterion("identity_type <>", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeGreaterThan(String value) {
            addCriterion("identity_type >", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeGreaterThanOrEqualTo(String value) {
            addCriterion("identity_type >=", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeLessThan(String value) {
            addCriterion("identity_type <", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeLessThanOrEqualTo(String value) {
            addCriterion("identity_type <=", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeLike(String value) {
            addCriterion("identity_type like", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeNotLike(String value) {
            addCriterion("identity_type not like", value, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeIn(List<String> values) {
            addCriterion("identity_type in", values, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeNotIn(List<String> values) {
            addCriterion("identity_type not in", values, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeBetween(String value1, String value2) {
            addCriterion("identity_type between", value1, value2, "identityType");
            return (Criteria) this;
        }

        public Criteria andIdentityTypeNotBetween(String value1, String value2) {
            addCriterion("identity_type not between", value1, value2, "identityType");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentIsNull() {
            addCriterion("legal_represent is null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentIsNotNull() {
            addCriterion("legal_represent is not null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentEqualTo(String value) {
            addCriterion("legal_represent =", value, "legalRepresent");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentNotEqualTo(String value) {
            addCriterion("legal_represent <>", value, "legalRepresent");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentGreaterThan(String value) {
            addCriterion("legal_represent >", value, "legalRepresent");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentGreaterThanOrEqualTo(String value) {
            addCriterion("legal_represent >=", value, "legalRepresent");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentLessThan(String value) {
            addCriterion("legal_represent <", value, "legalRepresent");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentLessThanOrEqualTo(String value) {
            addCriterion("legal_represent <=", value, "legalRepresent");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentLike(String value) {
            addCriterion("legal_represent like", value, "legalRepresent");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentNotLike(String value) {
            addCriterion("legal_represent not like", value, "legalRepresent");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentIn(List<String> values) {
            addCriterion("legal_represent in", values, "legalRepresent");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentNotIn(List<String> values) {
            addCriterion("legal_represent not in", values, "legalRepresent");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentBetween(String value1, String value2) {
            addCriterion("legal_represent between", value1, value2, "legalRepresent");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentNotBetween(String value1, String value2) {
            addCriterion("legal_represent not between", value1, value2, "legalRepresent");
            return (Criteria) this;
        }

        public Criteria andPostIsNull() {
            addCriterion("post is null");
            return (Criteria) this;
        }

        public Criteria andPostIsNotNull() {
            addCriterion("post is not null");
            return (Criteria) this;
        }

        public Criteria andPostEqualTo(String value) {
            addCriterion("post =", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotEqualTo(String value) {
            addCriterion("post <>", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostGreaterThan(String value) {
            addCriterion("post >", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostGreaterThanOrEqualTo(String value) {
            addCriterion("post >=", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLessThan(String value) {
            addCriterion("post <", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLessThanOrEqualTo(String value) {
            addCriterion("post <=", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLike(String value) {
            addCriterion("post like", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotLike(String value) {
            addCriterion("post not like", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostIn(List<String> values) {
            addCriterion("post in", values, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotIn(List<String> values) {
            addCriterion("post not in", values, "post");
            return (Criteria) this;
        }

        public Criteria andPostBetween(String value1, String value2) {
            addCriterion("post between", value1, value2, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotBetween(String value1, String value2) {
            addCriterion("post not between", value1, value2, "post");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriIsNull() {
            addCriterion("id_card_pdf_uri is null");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriIsNotNull() {
            addCriterion("id_card_pdf_uri is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriEqualTo(String value) {
            addCriterion("id_card_pdf_uri =", value, "idCardPdfUri");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriNotEqualTo(String value) {
            addCriterion("id_card_pdf_uri <>", value, "idCardPdfUri");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriGreaterThan(String value) {
            addCriterion("id_card_pdf_uri >", value, "idCardPdfUri");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriGreaterThanOrEqualTo(String value) {
            addCriterion("id_card_pdf_uri >=", value, "idCardPdfUri");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriLessThan(String value) {
            addCriterion("id_card_pdf_uri <", value, "idCardPdfUri");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriLessThanOrEqualTo(String value) {
            addCriterion("id_card_pdf_uri <=", value, "idCardPdfUri");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriLike(String value) {
            addCriterion("id_card_pdf_uri like", value, "idCardPdfUri");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriNotLike(String value) {
            addCriterion("id_card_pdf_uri not like", value, "idCardPdfUri");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriIn(List<String> values) {
            addCriterion("id_card_pdf_uri in", values, "idCardPdfUri");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriNotIn(List<String> values) {
            addCriterion("id_card_pdf_uri not in", values, "idCardPdfUri");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriBetween(String value1, String value2) {
            addCriterion("id_card_pdf_uri between", value1, value2, "idCardPdfUri");
            return (Criteria) this;
        }

        public Criteria andIdCardPdfUriNotBetween(String value1, String value2) {
            addCriterion("id_card_pdf_uri not between", value1, value2, "idCardPdfUri");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(String value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("number like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("number not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andRepayNumIsNull() {
            addCriterion("repay_num is null");
            return (Criteria) this;
        }

        public Criteria andRepayNumIsNotNull() {
            addCriterion("repay_num is not null");
            return (Criteria) this;
        }

        public Criteria andRepayNumEqualTo(String value) {
            addCriterion("repay_num =", value, "repayNum");
            return (Criteria) this;
        }

        public Criteria andRepayNumNotEqualTo(String value) {
            addCriterion("repay_num <>", value, "repayNum");
            return (Criteria) this;
        }

        public Criteria andRepayNumGreaterThan(String value) {
            addCriterion("repay_num >", value, "repayNum");
            return (Criteria) this;
        }

        public Criteria andRepayNumGreaterThanOrEqualTo(String value) {
            addCriterion("repay_num >=", value, "repayNum");
            return (Criteria) this;
        }

        public Criteria andRepayNumLessThan(String value) {
            addCriterion("repay_num <", value, "repayNum");
            return (Criteria) this;
        }

        public Criteria andRepayNumLessThanOrEqualTo(String value) {
            addCriterion("repay_num <=", value, "repayNum");
            return (Criteria) this;
        }

        public Criteria andRepayNumLike(String value) {
            addCriterion("repay_num like", value, "repayNum");
            return (Criteria) this;
        }

        public Criteria andRepayNumNotLike(String value) {
            addCriterion("repay_num not like", value, "repayNum");
            return (Criteria) this;
        }

        public Criteria andRepayNumIn(List<String> values) {
            addCriterion("repay_num in", values, "repayNum");
            return (Criteria) this;
        }

        public Criteria andRepayNumNotIn(List<String> values) {
            addCriterion("repay_num not in", values, "repayNum");
            return (Criteria) this;
        }

        public Criteria andRepayNumBetween(String value1, String value2) {
            addCriterion("repay_num between", value1, value2, "repayNum");
            return (Criteria) this;
        }

        public Criteria andRepayNumNotBetween(String value1, String value2) {
            addCriterion("repay_num not between", value1, value2, "repayNum");
            return (Criteria) this;
        }

        public Criteria andField37IsNull() {
            addCriterion("field37 is null");
            return (Criteria) this;
        }

        public Criteria andField37IsNotNull() {
            addCriterion("field37 is not null");
            return (Criteria) this;
        }

        public Criteria andField37EqualTo(String value) {
            addCriterion("field37 =", value, "field37");
            return (Criteria) this;
        }

        public Criteria andField37NotEqualTo(String value) {
            addCriterion("field37 <>", value, "field37");
            return (Criteria) this;
        }

        public Criteria andField37GreaterThan(String value) {
            addCriterion("field37 >", value, "field37");
            return (Criteria) this;
        }

        public Criteria andField37GreaterThanOrEqualTo(String value) {
            addCriterion("field37 >=", value, "field37");
            return (Criteria) this;
        }

        public Criteria andField37LessThan(String value) {
            addCriterion("field37 <", value, "field37");
            return (Criteria) this;
        }

        public Criteria andField37LessThanOrEqualTo(String value) {
            addCriterion("field37 <=", value, "field37");
            return (Criteria) this;
        }

        public Criteria andField37Like(String value) {
            addCriterion("field37 like", value, "field37");
            return (Criteria) this;
        }

        public Criteria andField37NotLike(String value) {
            addCriterion("field37 not like", value, "field37");
            return (Criteria) this;
        }

        public Criteria andField37In(List<String> values) {
            addCriterion("field37 in", values, "field37");
            return (Criteria) this;
        }

        public Criteria andField37NotIn(List<String> values) {
            addCriterion("field37 not in", values, "field37");
            return (Criteria) this;
        }

        public Criteria andField37Between(String value1, String value2) {
            addCriterion("field37 between", value1, value2, "field37");
            return (Criteria) this;
        }

        public Criteria andField37NotBetween(String value1, String value2) {
            addCriterion("field37 not between", value1, value2, "field37");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyIsNull() {
            addCriterion("loan_money is null");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyIsNotNull() {
            addCriterion("loan_money is not null");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyEqualTo(String value) {
            addCriterion("loan_money =", value, "loanMoney");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyNotEqualTo(String value) {
            addCriterion("loan_money <>", value, "loanMoney");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyGreaterThan(String value) {
            addCriterion("loan_money >", value, "loanMoney");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("loan_money >=", value, "loanMoney");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyLessThan(String value) {
            addCriterion("loan_money <", value, "loanMoney");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyLessThanOrEqualTo(String value) {
            addCriterion("loan_money <=", value, "loanMoney");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyLike(String value) {
            addCriterion("loan_money like", value, "loanMoney");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyNotLike(String value) {
            addCriterion("loan_money not like", value, "loanMoney");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyIn(List<String> values) {
            addCriterion("loan_money in", values, "loanMoney");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyNotIn(List<String> values) {
            addCriterion("loan_money not in", values, "loanMoney");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyBetween(String value1, String value2) {
            addCriterion("loan_money between", value1, value2, "loanMoney");
            return (Criteria) this;
        }

        public Criteria andLoanMoneyNotBetween(String value1, String value2) {
            addCriterion("loan_money not between", value1, value2, "loanMoney");
            return (Criteria) this;
        }

        public Criteria andMsgIsNull() {
            addCriterion("msg is null");
            return (Criteria) this;
        }

        public Criteria andMsgIsNotNull() {
            addCriterion("msg is not null");
            return (Criteria) this;
        }

        public Criteria andMsgEqualTo(String value) {
            addCriterion("msg =", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotEqualTo(String value) {
            addCriterion("msg <>", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThan(String value) {
            addCriterion("msg >", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThanOrEqualTo(String value) {
            addCriterion("msg >=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThan(String value) {
            addCriterion("msg <", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThanOrEqualTo(String value) {
            addCriterion("msg <=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLike(String value) {
            addCriterion("msg like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotLike(String value) {
            addCriterion("msg not like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgIn(List<String> values) {
            addCriterion("msg in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotIn(List<String> values) {
            addCriterion("msg not in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgBetween(String value1, String value2) {
            addCriterion("msg between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotBetween(String value1, String value2) {
            addCriterion("msg not between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andNoticeDateIsNull() {
            addCriterion("notice_date is null");
            return (Criteria) this;
        }

        public Criteria andNoticeDateIsNotNull() {
            addCriterion("notice_date is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeDateEqualTo(String value) {
            addCriterion("notice_date =", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateNotEqualTo(String value) {
            addCriterion("notice_date <>", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateGreaterThan(String value) {
            addCriterion("notice_date >", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateGreaterThanOrEqualTo(String value) {
            addCriterion("notice_date >=", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateLessThan(String value) {
            addCriterion("notice_date <", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateLessThanOrEqualTo(String value) {
            addCriterion("notice_date <=", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateLike(String value) {
            addCriterion("notice_date like", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateNotLike(String value) {
            addCriterion("notice_date not like", value, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateIn(List<String> values) {
            addCriterion("notice_date in", values, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateNotIn(List<String> values) {
            addCriterion("notice_date not in", values, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateBetween(String value1, String value2) {
            addCriterion("notice_date between", value1, value2, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andNoticeDateNotBetween(String value1, String value2) {
            addCriterion("notice_date not between", value1, value2, "noticeDate");
            return (Criteria) this;
        }

        public Criteria andApplyNoIsNull() {
            addCriterion("apply_no is null");
            return (Criteria) this;
        }

        public Criteria andApplyNoIsNotNull() {
            addCriterion("apply_no is not null");
            return (Criteria) this;
        }

        public Criteria andApplyNoEqualTo(String value) {
            addCriterion("apply_no =", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoNotEqualTo(String value) {
            addCriterion("apply_no <>", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoGreaterThan(String value) {
            addCriterion("apply_no >", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoGreaterThanOrEqualTo(String value) {
            addCriterion("apply_no >=", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoLessThan(String value) {
            addCriterion("apply_no <", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoLessThanOrEqualTo(String value) {
            addCriterion("apply_no <=", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoLike(String value) {
            addCriterion("apply_no like", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoNotLike(String value) {
            addCriterion("apply_no not like", value, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoIn(List<String> values) {
            addCriterion("apply_no in", values, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoNotIn(List<String> values) {
            addCriterion("apply_no not in", values, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoBetween(String value1, String value2) {
            addCriterion("apply_no between", value1, value2, "applyNo");
            return (Criteria) this;
        }

        public Criteria andApplyNoNotBetween(String value1, String value2) {
            addCriterion("apply_no not between", value1, value2, "applyNo");
            return (Criteria) this;
        }

        public Criteria andSendedIsNull() {
            addCriterion("sended is null");
            return (Criteria) this;
        }

        public Criteria andSendedIsNotNull() {
            addCriterion("sended is not null");
            return (Criteria) this;
        }

        public Criteria andSendedEqualTo(String value) {
            addCriterion("sended =", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedNotEqualTo(String value) {
            addCriterion("sended <>", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedGreaterThan(String value) {
            addCriterion("sended >", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedGreaterThanOrEqualTo(String value) {
            addCriterion("sended >=", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedLessThan(String value) {
            addCriterion("sended <", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedLessThanOrEqualTo(String value) {
            addCriterion("sended <=", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedLike(String value) {
            addCriterion("sended like", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedNotLike(String value) {
            addCriterion("sended not like", value, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedIn(List<String> values) {
            addCriterion("sended in", values, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedNotIn(List<String> values) {
            addCriterion("sended not in", values, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedBetween(String value1, String value2) {
            addCriterion("sended between", value1, value2, "sended");
            return (Criteria) this;
        }

        public Criteria andSendedNotBetween(String value1, String value2) {
            addCriterion("sended not between", value1, value2, "sended");
            return (Criteria) this;
        }

        public Criteria andExistenceIsNull() {
            addCriterion("existence is null");
            return (Criteria) this;
        }

        public Criteria andExistenceIsNotNull() {
            addCriterion("existence is not null");
            return (Criteria) this;
        }

        public Criteria andExistenceEqualTo(String value) {
            addCriterion("existence =", value, "existence");
            return (Criteria) this;
        }

        public Criteria andExistenceNotEqualTo(String value) {
            addCriterion("existence <>", value, "existence");
            return (Criteria) this;
        }

        public Criteria andExistenceGreaterThan(String value) {
            addCriterion("existence >", value, "existence");
            return (Criteria) this;
        }

        public Criteria andExistenceGreaterThanOrEqualTo(String value) {
            addCriterion("existence >=", value, "existence");
            return (Criteria) this;
        }

        public Criteria andExistenceLessThan(String value) {
            addCriterion("existence <", value, "existence");
            return (Criteria) this;
        }

        public Criteria andExistenceLessThanOrEqualTo(String value) {
            addCriterion("existence <=", value, "existence");
            return (Criteria) this;
        }

        public Criteria andExistenceLike(String value) {
            addCriterion("existence like", value, "existence");
            return (Criteria) this;
        }

        public Criteria andExistenceNotLike(String value) {
            addCriterion("existence not like", value, "existence");
            return (Criteria) this;
        }

        public Criteria andExistenceIn(List<String> values) {
            addCriterion("existence in", values, "existence");
            return (Criteria) this;
        }

        public Criteria andExistenceNotIn(List<String> values) {
            addCriterion("existence not in", values, "existence");
            return (Criteria) this;
        }

        public Criteria andExistenceBetween(String value1, String value2) {
            addCriterion("existence between", value1, value2, "existence");
            return (Criteria) this;
        }

        public Criteria andExistenceNotBetween(String value1, String value2) {
            addCriterion("existence not between", value1, value2, "existence");
            return (Criteria) this;
        }

        public Criteria andSended2IsNull() {
            addCriterion("sended2 is null");
            return (Criteria) this;
        }

        public Criteria andSended2IsNotNull() {
            addCriterion("sended2 is not null");
            return (Criteria) this;
        }

        public Criteria andSended2EqualTo(Byte value) {
            addCriterion("sended2 =", value, "sended2");
            return (Criteria) this;
        }

        public Criteria andSended2NotEqualTo(Byte value) {
            addCriterion("sended2 <>", value, "sended2");
            return (Criteria) this;
        }

        public Criteria andSended2GreaterThan(Byte value) {
            addCriterion("sended2 >", value, "sended2");
            return (Criteria) this;
        }

        public Criteria andSended2GreaterThanOrEqualTo(Byte value) {
            addCriterion("sended2 >=", value, "sended2");
            return (Criteria) this;
        }

        public Criteria andSended2LessThan(Byte value) {
            addCriterion("sended2 <", value, "sended2");
            return (Criteria) this;
        }

        public Criteria andSended2LessThanOrEqualTo(Byte value) {
            addCriterion("sended2 <=", value, "sended2");
            return (Criteria) this;
        }

        public Criteria andSended2In(List<Byte> values) {
            addCriterion("sended2 in", values, "sended2");
            return (Criteria) this;
        }

        public Criteria andSended2NotIn(List<Byte> values) {
            addCriterion("sended2 not in", values, "sended2");
            return (Criteria) this;
        }

        public Criteria andSended2Between(Byte value1, Byte value2) {
            addCriterion("sended2 between", value1, value2, "sended2");
            return (Criteria) this;
        }

        public Criteria andSended2NotBetween(Byte value1, Byte value2) {
            addCriterion("sended2 not between", value1, value2, "sended2");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlIsNull() {
            addCriterion("arbitration_msg_url is null");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlIsNotNull() {
            addCriterion("arbitration_msg_url is not null");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlEqualTo(String value) {
            addCriterion("arbitration_msg_url =", value, "arbitrationMsgUrl");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlNotEqualTo(String value) {
            addCriterion("arbitration_msg_url <>", value, "arbitrationMsgUrl");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlGreaterThan(String value) {
            addCriterion("arbitration_msg_url >", value, "arbitrationMsgUrl");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("arbitration_msg_url >=", value, "arbitrationMsgUrl");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlLessThan(String value) {
            addCriterion("arbitration_msg_url <", value, "arbitrationMsgUrl");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlLessThanOrEqualTo(String value) {
            addCriterion("arbitration_msg_url <=", value, "arbitrationMsgUrl");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlLike(String value) {
            addCriterion("arbitration_msg_url like", value, "arbitrationMsgUrl");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlNotLike(String value) {
            addCriterion("arbitration_msg_url not like", value, "arbitrationMsgUrl");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlIn(List<String> values) {
            addCriterion("arbitration_msg_url in", values, "arbitrationMsgUrl");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlNotIn(List<String> values) {
            addCriterion("arbitration_msg_url not in", values, "arbitrationMsgUrl");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlBetween(String value1, String value2) {
            addCriterion("arbitration_msg_url between", value1, value2, "arbitrationMsgUrl");
            return (Criteria) this;
        }

        public Criteria andArbitrationMsgUrlNotBetween(String value1, String value2) {
            addCriterion("arbitration_msg_url not between", value1, value2, "arbitrationMsgUrl");
            return (Criteria) this;
        }

        public Criteria andArbitrationSendDateIsNull() {
            addCriterion("arbitration_send_date is null");
            return (Criteria) this;
        }

        public Criteria andArbitrationSendDateIsNotNull() {
            addCriterion("arbitration_send_date is not null");
            return (Criteria) this;
        }

        public Criteria andArbitrationSendDateEqualTo(Date value) {
            addCriterionForJDBCDate("arbitration_send_date =", value, "arbitrationSendDate");
            return (Criteria) this;
        }

        public Criteria andArbitrationSendDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("arbitration_send_date <>", value, "arbitrationSendDate");
            return (Criteria) this;
        }

        public Criteria andArbitrationSendDateGreaterThan(Date value) {
            addCriterionForJDBCDate("arbitration_send_date >", value, "arbitrationSendDate");
            return (Criteria) this;
        }

        public Criteria andArbitrationSendDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("arbitration_send_date >=", value, "arbitrationSendDate");
            return (Criteria) this;
        }

        public Criteria andArbitrationSendDateLessThan(Date value) {
            addCriterionForJDBCDate("arbitration_send_date <", value, "arbitrationSendDate");
            return (Criteria) this;
        }

        public Criteria andArbitrationSendDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("arbitration_send_date <=", value, "arbitrationSendDate");
            return (Criteria) this;
        }

        public Criteria andArbitrationSendDateIn(List<Date> values) {
            addCriterionForJDBCDate("arbitration_send_date in", values, "arbitrationSendDate");
            return (Criteria) this;
        }

        public Criteria andArbitrationSendDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("arbitration_send_date not in", values, "arbitrationSendDate");
            return (Criteria) this;
        }

        public Criteria andArbitrationSendDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("arbitration_send_date between", value1, value2, "arbitrationSendDate");
            return (Criteria) this;
        }

        public Criteria andArbitrationSendDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("arbitration_send_date not between", value1, value2, "arbitrationSendDate");
            return (Criteria) this;
        }

        public Criteria andDebtmsgSendDateIsNull() {
            addCriterion("debtmsg_send_date is null");
            return (Criteria) this;
        }

        public Criteria andDebtmsgSendDateIsNotNull() {
            addCriterion("debtmsg_send_date is not null");
            return (Criteria) this;
        }

        public Criteria andDebtmsgSendDateEqualTo(Date value) {
            addCriterionForJDBCDate("debtmsg_send_date =", value, "debtmsgSendDate");
            return (Criteria) this;
        }

        public Criteria andDebtmsgSendDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("debtmsg_send_date <>", value, "debtmsgSendDate");
            return (Criteria) this;
        }

        public Criteria andDebtmsgSendDateGreaterThan(Date value) {
            addCriterionForJDBCDate("debtmsg_send_date >", value, "debtmsgSendDate");
            return (Criteria) this;
        }

        public Criteria andDebtmsgSendDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("debtmsg_send_date >=", value, "debtmsgSendDate");
            return (Criteria) this;
        }

        public Criteria andDebtmsgSendDateLessThan(Date value) {
            addCriterionForJDBCDate("debtmsg_send_date <", value, "debtmsgSendDate");
            return (Criteria) this;
        }

        public Criteria andDebtmsgSendDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("debtmsg_send_date <=", value, "debtmsgSendDate");
            return (Criteria) this;
        }

        public Criteria andDebtmsgSendDateIn(List<Date> values) {
            addCriterionForJDBCDate("debtmsg_send_date in", values, "debtmsgSendDate");
            return (Criteria) this;
        }

        public Criteria andDebtmsgSendDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("debtmsg_send_date not in", values, "debtmsgSendDate");
            return (Criteria) this;
        }

        public Criteria andDebtmsgSendDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("debtmsg_send_date between", value1, value2, "debtmsgSendDate");
            return (Criteria) this;
        }

        public Criteria andDebtmsgSendDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("debtmsg_send_date not between", value1, value2, "debtmsgSendDate");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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
