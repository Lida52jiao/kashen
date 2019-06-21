package com.battcn.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.CheckinEntity;
import com.battcn.entity.ConfigEntity;
import com.battcn.entity.IntegrateOrderEntity;
import com.battcn.entity.PointLogEntity;
import com.battcn.entity.ShopEntity;
import com.battcn.service.system.IntegrateService;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtils;
import com.github.pagehelper.PageInfo;
@Service
public class IntegrateServiceImpl implements IntegrateService {

	@Override
	public PageInfo<CheckinEntity> getIntegrateList(Map<String, Object> map) {
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
		String url = "http://47.104.25.59:1090/checkin/findCheckin";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,PageInfo.class);
	}
	//获得该账户总积分
	@Override
	public String totalIntegrate(String merchantId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchantId", merchantId);
		String url = "http://47.104.25.59:1090/point/findPoint";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		JSONObject p = JSONObject.parseObject(data);
		String point = p.getString("point");
		return point;
	}
	@Override
	public PageInfo<ShopEntity> getIntegrateGoodsList(Map<String, Object> map) {
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
		String url = "http://47.104.25.59:1090/shop/findShop";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,PageInfo.class);
	}
	@Override
	public PageInfo<ShopEntity> getIntegrateGoodById(Map<String, Object> map) {
		String url = "http://47.104.25.59:1090/shop/findShop";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,PageInfo.class);
	}
	@Override
	public String updateIntegrateGoodById(Map<String, Object> map) {
		String url = "http://47.104.25.59:1090/shop/updateShop";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return data;
	}
	@Override
	public String addIntegrateGood(Map<String, Object> map) {
		String url = "http://47.104.25.59:1090/shop/addShop";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return data;
	}
	@Override
	public PageInfo<IntegrateOrderEntity> getIntegrateOrderList(Map<String, Object> map) {
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
		String url = "http://47.104.25.59:1090/order/findOrder";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,PageInfo.class);
	}
	@Override
	public String updateIntegrateOrderByNo(Map<String, Object> map) {
		String url = "http://47.104.25.59:1090/order/updateOrder";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return data;
	}
	@Override
	public PageInfo<PointLogEntity> getIntegrateDetail(Map<String, Object> map) {
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
		String url = "http://47.104.25.59:1090/pointLog/findPointLog";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,PageInfo.class);
	}
	@Override
	public PageInfo<ConfigEntity> getIntegrateRule(Map<String, Object> map) {
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
		String url = "http://47.104.25.59:1090/config/findConfig";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,PageInfo.class);
	}
	@Override
	public String setIntegrateRule(Map<String, Object> map) {
		String url = "http://47.104.25.59:1090/config/updateConfig";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return data;
	}

}
