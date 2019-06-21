package com.battcn.service.system;

import java.util.Map;

import com.battcn.entity.CheckinEntity;
import com.battcn.entity.ConfigEntity;
import com.battcn.entity.IntegrateOrderEntity;
import com.battcn.entity.PointLogEntity;
import com.battcn.entity.ShopEntity;
import com.github.pagehelper.PageInfo;

public interface IntegrateService {
	
	PageInfo<CheckinEntity> getIntegrateList(Map<String, Object> map);
	
	String totalIntegrate(String merchantId);
	
	PageInfo<ShopEntity> getIntegrateGoodsList(Map<String, Object> map);
	
	PageInfo<ShopEntity> getIntegrateGoodById(Map<String, Object> map);
	
	String updateIntegrateGoodById(Map<String, Object> map);
	
	String addIntegrateGood(Map<String, Object> map);
	
	PageInfo<IntegrateOrderEntity> getIntegrateOrderList(Map<String, Object> map);
	
	String updateIntegrateOrderByNo(Map<String, Object> map);
	
	PageInfo<PointLogEntity> getIntegrateDetail(Map<String, Object> map); 
	
	PageInfo<ConfigEntity> getIntegrateRule(Map<String, Object> map); 
	
	String setIntegrateRule(Map<String, Object> map);
	
}
