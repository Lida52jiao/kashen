package com.battcn.service.system;


import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.*;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;

public interface TransactionalService extends BaseService<TransactionalEntity> {
    PageInfo<TransactionalEntity> queryTransactionalForList(TransactionalEntity entity);

    List<TransactionalStatistics> getListByDay(Integer dayNum, String merchantId);

    List<TransactionalStatistics> getListByMonth(String merchantId);

    List<TransactionalStatistics> getListByYear(String merchantId);

	Double cash(String merchantId, String type, String starttime, String finishtime, String merId);

	Double institution(String merchantId, String type, String starttime, String finishtime, String merId, String merchantIds);

	Double cashs(String merchantId, String type, String starttime, String finishtime,
			List<String> sList);

	Double institutions(String merchantId, String type, String starttime, String finishtime,
			List<String> sList, String merchantIds);

	Double find(String merchantId, String type, String starttime, String finishtime,
			String agentId);

	Double finds(String merchantId, String type, String starttime, String finishtime,
			String agentId, String merchantIds);
	
//	ReturnCount getRePayCount(Map<String, Object> map);
//
//	ReturnCount getNoCardCount(Map<String, Object> map);

	PageInfo<TransactionalEntity> countbyArea(Map<String, Object> map);

	String count(Map<String, Object> map);

	String setCash(Map<String, Object> map);

	CashSet findCash(Map<String, Object> map);
}
