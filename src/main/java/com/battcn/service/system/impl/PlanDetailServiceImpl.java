package com.battcn.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.battcn.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.MerChants;
import com.battcn.entity.PlanDetail;
import com.battcn.entity.PlanDetailEntity;
import com.battcn.entity.PlanEntity;
import com.battcn.entity.State;
import com.battcn.entity.Transactional;
import com.battcn.mapper.PlanDetailMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.MerChantsService;
import com.battcn.service.system.PlanDetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

/**
 * Created by bin on 2017/11/6.
 */
@Service
public class PlanDetailServiceImpl extends BaseServiceImpl<PlanDetailEntity> implements PlanDetailService{
    @Autowired
    private PlanDetailMapper planDetailMapper;
	@Autowired
	private MerChantsService merChantsService;
	
    @Override
    public PageInfo<PlanDetailEntity> queryPlanDetailForList(PlanDetailEntity entity) {
        return this.queryPageForList(entity);
    }
    @Override
    public List<PlanDetailEntity> getExpireList() {
        Long date=new Date().getTime();
        return planDetailMapper.getExpireList(date);
    }
	@Override
	public PageInfo<PlanDetail> gain(PlanDetail t) {
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
		return new PageInfo<PlanDetail>(planDetailMapper.gain(t));
	}
	/*@Override
	public PageInfo<PlanDetail> query(PlanDetail t) {
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
		return new PageInfo<PlanDetail>(planDetailMapper.query(t));
	}*/
	@Override
	public PageInfo<Transactional> recieve(Map<String, Object> map) {
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
		String url = "http://172.31.109.39/yj-account/transactional/findTransactionalByTime";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		List<Transactional> list = JSON.parseArray(t.getString("list"),Transactional.class);
		List<Transactional> list2 = new ArrayList<Transactional>();
		PageInfo<Transactional> page = new PageInfo<Transactional>();
		for(Transactional tt: list){
			MerChants mm = new MerChants();
			mm.setMerChantId(tt.getMerchantId());
			MerChants m = merChantsService.findByObject(mm);
			if(m!=null){
				tt.setMerName(m.getMerName());
			}
			list2.add(tt);
		}
		page.setList(list2);
		page.setPageSize(Integer.parseInt(t.getString("pageSize")));
		page.setPages(Integer.parseInt(t.getString("pages")));
		page.setTotal(Integer.parseInt(t.getString("total")));
		return page;
	}
	@Override
	public PageInfo<Transactional> gets(Transactional transactional) {
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
		return new PageInfo<Transactional>(planDetailMapper.gets(transactional));
	}
	@Override
	public PageInfo<Transactional> query(Transactional transactional) {
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
		return new PageInfo<Transactional>(planDetailMapper.query(transactional));
	}
	@Override
	public PageInfo<PlanDetailEntity> get(PlanDetailEntity planDetail) {
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
		return new PageInfo<PlanDetailEntity>(planDetailMapper.getPlanDetaillist(planDetail));
	}
	//查询计划详情
	@Override
	public PageInfo<PlanDetailEntity> selectPlanDetail(Map<String, Object> map) {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		String orderField = request.getParameter("sort");
		String orderDirection = request.getParameter("order");
		map.put("pageNum", pageNum.toString());
		map.put("pageSize", pageSize.toString());
		if(orderField!=null){
			map.put("sort", orderField);
		}
		if(orderDirection!=null){
			map.put("order",orderDirection);
		}
		String url = APIUtil.Find_Plan_Detial_by_Time;
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		List<PlanDetailEntity> list = JSON.parseArray(t.getString("list"),PlanDetailEntity.class);
		PageInfo<PlanDetailEntity> page = new PageInfo<PlanDetailEntity>();
		page.setList(list);
		page.setPageSize(Integer.parseInt(t.getString("pageSize")));
		page.setPages(Integer.parseInt(t.getString("pages")));
		page.setTotal(Integer.parseInt(t.getString("total")));
		return page;
	}



	//查看订单计划失败的原因并更新订单的状态
	@Override
	public String updateAndCheckPlanState(Map<String, Object> map, String aisleCode) {
		String url = APIUtil.Find_Plan;
		String threeUrl = "/order/findState";
		String result=new HttpClientUtils().doPost(url+aisleCode+threeUrl,map);
		JSONObject t = JSONObject.parseObject(result);
		String message = t.getString("respDesc");
		logger.info(message);
		return message;
	}
	//补消费的ld01订单状态
	@Override
	public String updateConsumePlanState(Map<String, Object> map, String aisleCode) {
		String url = APIUtil.Find_Plan;
		String threeUrl="/order/anewRepayment";
		String result=new HttpClientUtils().doPost(url+aisleCode+threeUrl,map);
		JSONObject t = JSONObject.parseObject(result);
		String message = t.getString("respDesc");
		return message;
	}
	//补消费的ld01订单状态
	@Override
	public String updaterepayPlanState(Map<String, Object> map, String aisleCode) {
		String url = APIUtil.Find_Plan;
		String threeUrl="/order/anewRepayment";
		String result=new HttpClientUtils().doPost(url+aisleCode+threeUrl,map);
		JSONObject t = JSONObject.parseObject(result);
		String message = t.getString("respDesc");
		return message;
	}
}
