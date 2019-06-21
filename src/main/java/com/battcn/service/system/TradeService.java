package com.battcn.service.system;

import java.util.List;
import java.util.Map;

import com.battcn.entity.AgentOrders;
import com.battcn.entity.CommodityEntity;
import com.battcn.entity.Order;
import com.battcn.entity.OrderEntity;
import com.battcn.entity.XJStatistics;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;

public interface TradeService extends BaseService<OrderEntity> {

	PageInfo<Order> gain(Order order);

	PageInfo<Order> query(Order order);

	/*Double find(String merchantId, String start, String end, String type, String state);
	
	Double finds(String merchantId, String start, String end, String type, String state, List<String> sList);

	Double trans(String merchantId, String start, String end, String type, String state, String agentId);*/

	//PageInfo<AgentOrders> gain(Map<String, Object> map);
	PageInfo<OrderEntity> gain(Map<String, Object> map);

	//PageInfo<AgentOrders> query(Map<String, Object> map);
	PageInfo<OrderEntity> query(Map<String, Object> map);

	PageInfo<XJStatistics> find(Map<String, Object> map);

	Double find(String merchantId, String type, String starttime, String finishtime, String merId);

	Double finds(String merchantId, String type, String starttime, String finishtime,
			List<String> sList);

	Double trans(String merchantId, String type, String starttime, String finishtime,
			String agentId);

	Double findreducedAmount(String merchantId, String type, String starttime,
			String finishtime, String merId);

	Double findsreducedAmount(String merchantId, String type, String starttime,
			String finishtime, List<String> sList);

	Double transreducedAmount(String merchantId, String type, String starttime,
			String finishtime, String agentId);
	PageInfo<CommodityEntity> getCommodityList(Map<String, Object> map);
	String insertstores(Map<String, Object> map);

	CommodityEntity getstoresById(Map<String, Object> map);

	String editstores(Map<String, Object> map);

	String deletestores(Map<String, Object> map);

}
