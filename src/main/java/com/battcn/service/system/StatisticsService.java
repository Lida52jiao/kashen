package com.battcn.service.system;

import com.battcn.entity.CountT1Entity;
import com.battcn.entity.PlanEntity;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface StatisticsService extends BaseService<CountT1Entity> {

     PageInfo<CountT1Entity> findT1LIST(Map<String, Object> map);

     String amountCount(Map<String, Object> map);

     PlanEntity merchantCount(Map<String, Object> map);

}
