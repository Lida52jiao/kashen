package com.battcn.mapper;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.battcn.entity.Order;
import com.battcn.entity.OrderEntity;

public interface TradeMapper extends Mapper<OrderEntity> {

	List<Order> gain(Order order);

	List<Order> query(Order order);
	
	Double get(Map<String, Object> map);
	
	Double gets(Map<String, Object> map);

	Double alter(Map<String, Object> map);

}
