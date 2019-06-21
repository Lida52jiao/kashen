package com.battcn.service.system;

import java.util.List;
import java.util.Map;

import com.battcn.entity.PlanEntity;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;

/**
 * Created by bin on 2017/11/6.
 */
public interface PlanService extends BaseService<PlanEntity>{
    PageInfo<PlanEntity> queryPlanForList(PlanEntity entity);

    PageInfo<PlanEntity> getListByTime(String merchantsId,Long startTime, Long endTime);

    //PageInfo<PlanEntity> getListByAll(String merchantsId,String state,String ratio,String totalDay,String number,String bankCode,String name,String idCardNo,String cardNo,String phone, Long startTime, Long endTime);

	PageInfo<PlanEntity> getListByAll(String merchantId,String state,String name,String cardNo,String phone,String starttime,String finishtime);

	PageInfo<PlanEntity> query(String merchantId, String state, String name,
			String cardNo, String phone, String starttime, String finishtime,
			List<String> sList);

	PageInfo<PlanEntity> gets(String merchantId, String merId, String state,
			String name, String cardNo, String phone, String starttime,
			String finishtime);

	String get(String merchantId, String orderNo);
	
	PageInfo<PlanEntity> selectPlan(Map<String, Object> map);
}
