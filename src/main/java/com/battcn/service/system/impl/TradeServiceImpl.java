package com.battcn.service.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.AgentOrders;
import com.battcn.entity.CommodityEntity;
import com.battcn.entity.Order;
import com.battcn.entity.OrderEntity;
import com.battcn.entity.XJStatistics;
import com.battcn.mapper.AlipayOrderEntityMapper;
import com.battcn.mapper.TradeMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.TradeService;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

@Service
public class TradeServiceImpl extends BaseServiceImpl<OrderEntity> implements TradeService {
	
	@Autowired
	private TradeMapper tradeMapper;
	@Autowired
	private AlipayOrderEntityMapper alipayOrderEntityMapper;

	@Override
	public PageInfo<Order> gain(Order order) {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		PageHelper.startPage(pageNum, pageSize);
		String orderField = request.getParameter("sort");
		String orderDirection = request.getParameter("order");
		if (StringUtil.isNotEmpty(orderField)) {
			PageHelper.orderBy(orderField);
			if (StringUtil.isNotEmpty(orderDirection)) {
				PageHelper.orderBy(orderField + " " + orderDirection);
			}
		}
		return new PageInfo<Order>(tradeMapper.gain(order));
	}

	@Override
	public PageInfo<Order> query(Order order) {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		PageHelper.startPage(pageNum, pageSize);
		String orderField = request.getParameter("sort");
		String orderDirection = request.getParameter("order");
		if (StringUtil.isNotEmpty(orderField)) {
			PageHelper.orderBy(orderField);
			if (StringUtil.isNotEmpty(orderDirection)) {
				PageHelper.orderBy(orderField + " " + orderDirection);
			}
		}
		return new PageInfo<Order>(tradeMapper.query(order));
	}
	@Override
	public PageInfo<OrderEntity> gain(Map<String, Object> map) {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		String orderField = request.getParameter("sort");
		String orderDirection = request.getParameter("order");
		map.put("pageNum", pageNum.toString());
		map.put("pageSize", pageSize.toString());
		map.put("sort", orderField);
		map.put("order", orderDirection);
		String url = "http://47.104.25.147/yj-epos/order/findOrderByTime";
		String result=new HttpClientUtils().doPost(url,map);
		return JSON.parseObject(result,PageInfo.class);
	}
	@Override
	public PageInfo<OrderEntity> query(Map<String, Object> map) {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		String orderField = request.getParameter("sort");
		String orderDirection = request.getParameter("order");
		map.put("pageNum", pageNum.toString());
		map.put("pageSize", pageSize.toString());
		map.put("sort", orderField);
		map.put("order", orderDirection);
		String url = "http://47.104.25.147/yj-epos/order/findOrderByTime";
		String result=new HttpClientUtils().doPost(url,map);
		return JSON.parseObject(result,PageInfo.class);
	}
//	@Override
//	public PageInfo<AgentOrders> query(Map<String, Object> map) {
//		HttpServletRequest request = CommonUtil.getHttpRequest();
//		Integer pageNum = CommonUtil
//				.valueOf(request.getParameter("pageNum"), 1);
//		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
//				10);
//		map.put("pageNum", pageNum.toString());
//		map.put("pageSize", pageSize.toString());
//		String url = "http://47.104.161.254/xjpay/AgentOrder/select.shtml";
//		String result=new HttpClientUtils().doPost(url,map);
//		JSONObject t = JSONObject.parseObject(result);
//		//"total":35,"pageSize":10,"currentPage":1,"totalPages":4,"rows"
//		List<AgentOrders> agentOrder = JSON.parseArray(t.getString("rows"),AgentOrders.class);
//		PageInfo<AgentOrders> agentOrders = new PageInfo<AgentOrders>();
//		agentOrders.setList(agentOrder);
//		agentOrders.setPageSize(Integer.parseInt(t.getString("pageSize")));
//		agentOrders.setPages(Integer.parseInt(t.getString("totalPages")));
//		agentOrders.setTotal(Integer.parseInt(t.getString("total")));
//		
//		return agentOrders;
//	}

	@Override
	public PageInfo<XJStatistics> find(Map<String, Object> map) {
		String url = "http://118.190.148.212/xjpay/AgentOrder/getXJStatistics.shtml";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		XJStatistics h = new XJStatistics(Double.parseDouble(t.getString("total")), Double.parseDouble(t.getString("fee")), Double.parseDouble(t.getString("balanceProfit")), Double.parseDouble(t.getString("profit")), t.getString("cost"));
		List<XJStatistics> list = new ArrayList<XJStatistics>();
		list.add(h);
		return new PageInfo<XJStatistics>(list);
	}

	@Override
	public Double find(String merchantId, String type, String starttime, String finishtime, String merId) {
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("type", type);
		map.put("start", starttime);
		map.put("end", finishtime);
		map.put("merId", merId);
		if("".equals(type) || "2".equals(type)){
			Double trans=tradeMapper.get(map);
			return trans;
		}
		Double trans=alipayOrderEntityMapper.gain(map);
		return trans;
	}

	@Override
	public Double finds(String merchantId, String type, String starttime, String finishtime,
			List<String> sList) {
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("type", type);
		map.put("start", starttime);
		map.put("end", finishtime);
		map.put("tList", sList);
		if("".equals(type) || "2".equals(type)){
			Double trans=tradeMapper.gets(map);
			return trans;
		}
		Double trans=alipayOrderEntityMapper.gets(map);
		return trans;
	}

	@Override
	public Double trans(String merchantId, String type, String starttime, String finishtime,
			String agentId) {
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("type", type);
		map.put("start", starttime);
		map.put("end", finishtime);
		map.put("agentId", agentId);
		if("".equals(type) || "2".equals(type)){
			Double trans=tradeMapper.alter(map);
			return trans;
		}
		Double trans=alipayOrderEntityMapper.alter(map);
		return trans;
	}

	@Override
	public Double findreducedAmount(String merchantId, String type,
			String starttime, String finishtime, String merId) {
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("type", type);
		map.put("start", starttime);
		map.put("end", finishtime);
		map.put("merId", merId);
		if("".equals(type) || "2".equals(type)){
			return (double) 0;
		}
		Double trans=alipayOrderEntityMapper.gains(map);
		return trans == null ? 0 : trans;
	}

	@Override
	public Double findsreducedAmount(String merchantId, String type, String starttime,
			String finishtime, List<String> sList) {
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("type", type);
		map.put("start", starttime);
		map.put("end", finishtime);
		map.put("tList", sList);
		if("".equals(type) || "2".equals(type)){
			return (double) 0;
		}
		Double trans=alipayOrderEntityMapper.getAmount(map);
		return trans == null ? 0 : trans;
	}

	@Override
	public Double transreducedAmount(String merchantId, String type, String starttime,
			String finishtime, String agentId) {
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("type", type);
		map.put("start", starttime);
		map.put("end", finishtime);
		map.put("agentId", agentId);
		if("".equals(type) || "2".equals(type)){
			return (double) 0;
		}
		Double trans=alipayOrderEntityMapper.selectAmounts(map);
		return trans == null ? 0 : trans;
	}
	@Override
	public PageInfo<CommodityEntity> getCommodityList(Map<String, Object> map) {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		String orderField = request.getParameter("sort");
		String orderDirection = request.getParameter("order");
		map.put("pageNum", pageNum.toString());
		map.put("pageSize", pageSize.toString());
		map.put("sort", orderField);
		map.put("order", orderDirection);
		String url = "http://47.104.25.147/yj-epos/shop/findCommodity";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,PageInfo.class);
	}
	@Override
	public String insertstores(Map<String, Object> map) {
		String url = "http://47.104.25.147/yj-epos/shop/saveCommodity";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("respCode");
		if("0000".equals(data)){
			return "添加成功";
		}
		return "添加失败";
	}

	@Override
	public CommodityEntity getstoresById(Map<String, Object> map) {
		String url = "http://47.104.25.147/yj-epos/shop/findCommodityById";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,CommodityEntity.class);
	}

	@Override
	public String editstores(Map<String, Object> map) {
		String url = "http://47.104.25.147/yj-epos/shop/updateCommodity";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("respCode");
		if("0000".equals(data)){
			return "修改成功";
		}
		return "修改失败";
	}

	@Override
	public String deletestores(Map<String, Object> map) {
		String url = "http://47.104.25.147/yj-epos/shop/deleteCommodity";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("respCode");
		if("0000".equals(data)){
			return "删除成功";
		}
		return "删除失败";
	}
}
