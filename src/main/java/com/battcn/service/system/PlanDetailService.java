package com.battcn.service.system;

import java.util.List;
import java.util.Map;

import com.battcn.entity.PlanDetail;
import com.battcn.entity.PlanDetailEntity;
import com.battcn.entity.PlanEntity;
import com.battcn.entity.State;
import com.battcn.entity.Transactional;
import com.battcn.service.BaseService;
import com.battcn.util.YJResult;
import com.github.pagehelper.PageInfo;

/**
 * Created by bin on 2017/11/6.
 */
public interface PlanDetailService extends BaseService<PlanDetailEntity> {
    PageInfo<PlanDetailEntity> queryPlanDetailForList(PlanDetailEntity entity);

    List<PlanDetailEntity> getExpireList();

	PageInfo<PlanDetail> gain(PlanDetail t);

	PageInfo<Transactional> recieve(Map<String, Object> map);
	
	PageInfo<Transactional> gets(Transactional transactional);

	PageInfo<Transactional> query(Transactional transactional);

	PageInfo<PlanDetailEntity> get(PlanDetailEntity planDetail);

	PageInfo<PlanDetailEntity> selectPlanDetail(Map<String, Object> map);

	
	//查看订单计划失败的原因并更新订单的状态
	String updateAndCheckPlanState(Map<String, Object> map, String aisleCode);
	
	//补消费的订单状态ld01
	String updateConsumePlanState(Map<String, Object> map, String aisleCode);
	
	//补还款的订单状态ld02
	String updaterepayPlanState(Map<String, Object> map, String aisleCode);
}
