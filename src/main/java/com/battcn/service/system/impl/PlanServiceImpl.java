package com.battcn.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.battcn.entity.PlanGroup;
import com.battcn.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.constant.Constaint;
import com.battcn.entity.JiGuang;
import com.battcn.entity.PlanEntity;
import com.battcn.mapper.PlanMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.PlanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;


/**
 * Created by bin on 2017/11/6.
 */
@Service
public class PlanServiceImpl extends BaseServiceImpl<PlanEntity> implements PlanService{
    @Autowired
    private PlanMapper planMapper;
    @Override
    public PageInfo<PlanEntity> queryPlanForList(PlanEntity entity) {
        return this.queryPageForList(entity);
    }
    @Override
    public PageInfo<PlanEntity> getListByTime(String merchantsId,Long startTime, Long endTime) {
        HttpServletRequest request = CommonUtil.getHttpRequest();
        Integer pageNum = CommonUtil.valueOf(request.getParameter("pageNum"), 1);
        Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"), 10);
        PageHelper.startPage(pageNum, pageSize);
        String orderField = request.getParameter("sort");
        String orderDirection = request.getParameter("order");
        if (StringUtil.isNotEmpty(orderField)) {
            PageHelper.orderBy(orderField);
            if (StringUtil.isNotEmpty(orderDirection)) {
                PageHelper.orderBy(orderField + " " + orderDirection);
            }
        }
        return new PageInfo<PlanEntity>(planMapper.getListByTime(merchantsId,startTime,endTime));
    }
   /* @Override
    public PageInfo<PlanEntity> getListByAll(String merchantsId, String state, String ratio, String totalDay, String number, String bankCode, String name, String idCardNo, String cardNo, String phone,Long startTime, Long endTime) {
        HttpServletRequest request = CommonUtil.getHttpRequest();
        Integer pageNum = CommonUtil.valueOf(request.getParameter("pageNum"), 1);
        Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"), 10);
        PageHelper.startPage(pageNum, pageSize);
        String orderField = request.getParameter("sort");
        String orderDirection = request.getParameter("order");
        if (StringUtil.isNotEmpty(orderField)) {
            PageHelper.orderBy(orderField);
            if (StringUtil.isNotEmpty(orderDirection)) {
                PageHelper.orderBy(orderField + " " + orderDirection);
            }
        }
        return new PageInfo<PlanEntity>(planMapper.getListByAll(merchantsId,state,ratio,totalDay,number,bankCode,name,idCardNo,cardNo,phone,startTime,endTime));
    }*/
    
	@Override
	public PageInfo<PlanEntity> getListByAll(String merchantId,String state,String name,String cardNo,String phone,String starttime,String finishtime) {
		 HttpServletRequest request = CommonUtil.getHttpRequest();
	        Integer pageNum = CommonUtil.valueOf(request.getParameter("pageNum"), 1);
	        Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"), 10);
	        PageHelper.startPage(pageNum, pageSize);
	        String orderField = request.getParameter("sort");
	        String orderDirection = request.getParameter("order");
	        if (StringUtil.isNotEmpty(orderField)) {
	            PageHelper.orderBy(orderField);
	            if (StringUtil.isNotEmpty(orderDirection)) {
	                PageHelper.orderBy(orderField + " " + orderDirection);
	            }
	        }
	        return new PageInfo<PlanEntity>(planMapper.getListByAll(merchantId,state,name,cardNo,phone,starttime,finishtime));
	}
	@Override
	public PageInfo<PlanEntity> query(String merchantId, String state,
			String name, String cardNo, String phone, String starttime,
			String finishtime, List<String> sList) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("merchantId", merchantId);
		map.put("state", state);
		map.put("name", name);
		map.put("cardNo", cardNo);
		map.put("phone", phone);
		map.put("starttime", starttime);
		map.put("finishtime", finishtime);
		map.put("sList", sList);
		HttpServletRequest request = CommonUtil.getHttpRequest();
        Integer pageNum = CommonUtil.valueOf(request.getParameter("pageNum"), 1);
        Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"), 10);
        PageHelper.startPage(pageNum, pageSize);
        String orderField = request.getParameter("sort");
        String orderDirection = request.getParameter("order");
        if (StringUtil.isNotEmpty(orderField)) {
            PageHelper.orderBy(orderField);
            if (StringUtil.isNotEmpty(orderDirection)) {
                PageHelper.orderBy(orderField + " " + orderDirection);
            }
        }
        return new PageInfo<PlanEntity>(planMapper.query(map));
	}
	@Override
	public PageInfo<PlanEntity> gets(String merchantId, String merId,
			String state, String name, String cardNo, String phone,
			String starttime, String finishtime) {
		 HttpServletRequest request = CommonUtil.getHttpRequest();
	        Integer pageNum = CommonUtil.valueOf(request.getParameter("pageNum"), 1);
	        Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"), 10);
	        PageHelper.startPage(pageNum, pageSize);
	        String orderField = request.getParameter("sort");
	        String orderDirection = request.getParameter("order");
	        if (StringUtil.isNotEmpty(orderField)) {
	            PageHelper.orderBy(orderField);
	            if (StringUtil.isNotEmpty(orderDirection)) {
	                PageHelper.orderBy(orderField + " " + orderDirection);
	            }
	        }
	        return new PageInfo<PlanEntity>(planMapper.gets(merchantId,merId,state,name,cardNo,phone,starttime,finishtime));
	}
	@Override
	public String get(String merchantId, String orderNo) {
		HashMap<String,Object> hashMap=new HashMap<>();
		long time = System.currentTimeMillis();
		hashMap.put("merchantId", merchantId);
		hashMap.put("orderNo", orderNo);
		hashMap.put("time", time+"");
		String sign = SignUtil.createYJSign(hashMap);
		hashMap.put("sign", sign);
		String resultJsonStr = HttpClientUtils.doPost(Constaint.BuDan, hashMap);
		//YJResult result= JSON.parseObject(resultJsonStr,YJResult.class);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		return job.get("data").toString() == null ? "未知" : job.get("data").toString();
	}
	//查询计划
	@Override
	public PageInfo<PlanEntity> selectPlan(Map<String, Object> map) {
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
		map.put("order",orderDirection);
		String url = APIUtil.Find_Plan_by_Time;
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		List<PlanEntity> list = JSON.parseArray(t.getString("list"),PlanEntity.class);
		PageInfo<PlanEntity> page = new PageInfo<PlanEntity>();
		page.setList(list);
		page.setPageSize(Integer.parseInt(t.getString("pageSize")));
		page.setPages(Integer.parseInt(t.getString("pages")));
		page.setTotal(Integer.parseInt(t.getString("total")));
		return page;
	}
	@Override
	public PageInfo<PlanGroup> queryInterruptPlanList(Map<String, Object> map) {
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
		map.put("order",orderDirection);
		String url = APIUtil.Find_Group_History;
		String result=new HttpClientUtils().doPost(url,map);
		System.out.println(result);
		JSONObject t = JSONObject.parseObject(result);
		String string = t.get("data").toString();
		JSONObject object = JSONObject.parseObject(string);
		List<PlanGroup> list = JSON.parseArray(object.getString("list"),PlanGroup.class);
		PageInfo<PlanGroup> page = new PageInfo<PlanGroup>();
		page.setList(list);
		page.setPageSize(Integer.parseInt(object.getString("pageSize")));
		page.setPages(Integer.parseInt(object.getString("pages")));
		page.setTotal(Integer.parseInt(object.getString("total")));
		return page;
	}

	@Override
	public String clearPlan(String groupId) {
		Map<String, Object> map = new HashMap<>();
		map.put("groupId", groupId);
		String url = APIUtil.clear;
		String result=new HttpClientUtils().doPost(url,map);
		System.out.println("result:"+result);
		return result;
	}
}
