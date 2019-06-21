package com.battcn.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.constant.Constaint;
import com.battcn.entity.Withdraw;
import com.battcn.entity.WithdrawEntity;
import com.battcn.mapper.WithdrawEntityMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.WithdrawEntityService;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

@Service
public class WithdrawEntityServiceImpl extends BaseServiceImpl<WithdrawEntity> implements WithdrawEntityService {
	
	@Autowired
	private WithdrawEntityMapper withdrawEntityMapper;

	@Override
	public PageInfo<Withdraw> query(Map<String, Object> map) {
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
		return new PageInfo<Withdraw>(withdrawEntityMapper.gain(map));
	}

	@Override
	public PageInfo<Withdraw> gain(Map<String, Object> map) {
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
		return new PageInfo<Withdraw>(withdrawEntityMapper.get(map));
	}

	@Override
	public String find(String orderNo, String remarks, String userName) {
		Map<String, Object> param = new HashMap<>();
		param.put("orderNo", orderNo);
		param.put("cause", remarks);
		param.put("name", userName);
		String resultJsonStr = HttpClientUtils.doPost(Constaint.F, param);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		if("0000".equals(job.get("respCode"))){
			return "success";
		}
		return "fail";
	}

	@Override
	public PageInfo<Withdraw> find(Map<String, Object> map) {
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
		return new PageInfo<Withdraw>(withdrawEntityMapper.finds(map));
	} 
	
	@Override
	public PageInfo<Withdraw> getList(Map<String, Object> map) {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		map.put("pageNum", pageNum.toString());
		map.put("pageSize", pageSize.toString());
		String url = "http://118.190.148.212/xjpay/withdraw/getfirstList.shtml";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		//"total":35,"pageSize":10,"currentPage":1,"totalPages":4,"rows"
		List<Withdraw> agentOrder = JSON.parseArray(t.getString("rows"),Withdraw.class);
		PageInfo<Withdraw> agentOrders = new PageInfo<Withdraw>();
		agentOrders.setList(agentOrder);
		agentOrders.setPageSize(Integer.parseInt(t.getString("pageSize")));
		agentOrders.setPages(Integer.parseInt(t.getString("totalPages")));
		agentOrders.setTotal(Integer.parseInt(t.getString("total")));
		
		return agentOrders;
	}

	@Override
	public Object finds(String id) {
		Map<String, Object> map = new HashMap<>();
		map.put("oId", id);
		String url = "http://118.190.148.212/xjpay/withdraw/getWithdraw.shtml";
		String result=new HttpClientUtils().doPost(url,map);
		Withdraw agentOrder = JSON.parseObject(result,Withdraw.class);
		return agentOrder;
	}

	@Override
	public String updates(String userName, String orderNo, String state, String remarks) {
		Map<String, Object> map = new HashMap<>();
		map.put("userName", userName);
		map.put("orderNo", orderNo);
		map.put("state", state);
		map.put("remarks", remarks);
		String url = "http://118.190.148.212/xjpay/withdraw/alterfirstWithdraw.shtml";
		String result=new HttpClientUtils().doPost(url,map);
		return result;
	}

	@Override
	public String finds(String orderNo, String remarks, String userName) {
		Map<String, Object> param = new HashMap<>();
		param.put("orderNo", orderNo);
		param.put("cause", remarks);
		param.put("name", userName);
		String url = "http://118.190.148.212/xjpay/withdraw/withdrawFail.shtml";
		String resultJsonStr = HttpClientUtils.doPost(url, param);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		if("0000".equals(job.get("respCode"))){
			return "success";
		}
		return "fail";
	}

	@Override
	public PageInfo<Withdraw> selectLists(Map<String, Object> map) {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		map.put("pageNum", pageNum.toString());
		map.put("pageSize", pageSize.toString());
		String url = "http://118.190.148.212/xjpay/withdraw/getsecondList.shtml";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		//"total":35,"pageSize":10,"currentPage":1,"totalPages":4,"rows"
		List<Withdraw> agentOrder = JSON.parseArray(t.getString("rows"),Withdraw.class);
		PageInfo<Withdraw> agentOrders = new PageInfo<Withdraw>();
		agentOrders.setList(agentOrder);
		agentOrders.setPageSize(Integer.parseInt(t.getString("pageSize")));
		agentOrders.setPages(Integer.parseInt(t.getString("totalPages")));
		agentOrders.setTotal(Integer.parseInt(t.getString("total")));
		
		return agentOrders;
	} 
	
	@Override
	public String alter(String userName, String orderNo, String state, String remarks) {
		Map<String, Object> map = new HashMap<>();
		map.put("userName", userName);
		map.put("orderNo", orderNo);
		map.put("state", state);
		map.put("remarks", remarks);
		String url = "http://118.190.148.212/xjpay/withdraw/altersecondWithdraw.shtml";
		String result=new HttpClientUtils().doPost(url,map);
		return result;
	}

	@Override
	public PageInfo<Withdraw> getWithdraw(Map<String, Object> map) {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		map.put("pageNum", pageNum.toString());
		map.put("pageSize", pageSize.toString());
		String url = "http://118.190.148.212/xjpay/withdraw/getWithdraws.shtml";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		//"total":35,"pageSize":10,"currentPage":1,"totalPages":4,"rows"
		List<Withdraw> agentOrder = JSON.parseArray(t.getString("rows"),Withdraw.class);
		PageInfo<Withdraw> agentOrders = new PageInfo<Withdraw>();
		agentOrders.setList(agentOrder);
		agentOrders.setPageSize(Integer.parseInt(t.getString("pageSize")));
		agentOrders.setPages(Integer.parseInt(t.getString("totalPages")));
		agentOrders.setTotal(Integer.parseInt(t.getString("total")));
		
		return agentOrders;
	}


}
