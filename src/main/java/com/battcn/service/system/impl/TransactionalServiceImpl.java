package com.battcn.service.system.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.CashSet;
import com.battcn.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.battcn.entity.ReturnCount;
import com.battcn.entity.TransactionalEntity;
import com.battcn.entity.TransactionalStatistics;
import com.battcn.mapper.TransactionalMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.TransactionalService;
import com.battcn.util.HttpClientUtils;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

@Service
public class TransactionalServiceImpl extends BaseServiceImpl<TransactionalEntity> implements TransactionalService {
    @Autowired
    private TransactionalMapper transactionalMapper;
    @Override
    public PageInfo<TransactionalEntity> queryTransactionalForList(TransactionalEntity entity) {
        return this.queryPageForList(entity);
    }

    @Override
    public List<TransactionalStatistics> getListByDay(Integer dayNum,String merchantId){
        return transactionalMapper.getListByDay(dayNum,merchantId);
    }
    @Override
    public List<TransactionalStatistics> getListByMonth(String merchantId){
        return transactionalMapper.getListByMonth(merchantId);
    }
    @Override
    public List<TransactionalStatistics> getListByYear(String merchantId){
        return transactionalMapper.getListByYear(merchantId);
    }
	@Override
	public Double cash(String merchantId, String type, String starttime, String finishtime, String merId) {
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("type", type);
		map.put("start", starttime);
		map.put("end", finishtime);
		map.put("merId", merId);
		Double cash=transactionalMapper.cash(map);
		return cash;
	}
	@Override
	public Double institution(String merchantId, String type, String starttime,
			String finishtime, String merId, String merchantIds) {
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("type", type);
		map.put("start", starttime);
		map.put("end", finishtime);
		map.put("merId", merId);
		map.put("merchantIds", merchantIds);
		Double institution=transactionalMapper.query(map);
		return institution;
	}
	@Override
	public Double cashs(String merchantId, String type, String starttime, String finishtime,
			List<String> sList) {
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("type", type);
		map.put("start", starttime);
		map.put("end", finishtime);
		map.put("tList", sList);
		Double cash=transactionalMapper.get(map);
		return cash;
	}
	@Override
	public Double institutions(String merchantId, String type, String starttime,
			String finishtime, List<String> sList, String merchantIds) {
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("type", type);
		map.put("start", starttime);
		map.put("end", finishtime);
		map.put("tList", sList);
		map.put("merchantIds", merchantIds);
		Double institution=transactionalMapper.gets(map);
		return institution;
	}
	@Override
	public Double find(String merchantId, String type, String starttime, String finishtime,
			String agentId) {
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("type", type);
		map.put("start", starttime);
		map.put("end", finishtime);
		map.put("agentId", agentId);
		Double cash=transactionalMapper.alter(map);
		return cash;
	}
	@Override
	public Double finds(String merchantId, String type, String starttime, String finishtime,
			String agentId, String merchantIds) {
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("type", type);
		map.put("start", starttime);
		map.put("end", finishtime);
		map.put("agentId", agentId);
		map.put("merchantIds", merchantIds);
		Double institution=transactionalMapper.amend(map);
		return institution;
	}
//	@Override
//	public ReturnCount getRePayCount(Map<String, Object> map) {
//		String url = "http://47.104.25.59:1179/repayMentCount";
//		String result=new HttpClientUtils().doPost(url,map);
//		ReturnCount count = JSON.parseObject(result,ReturnCount.class);
//		return count;
//	}
//	@Override
//	public ReturnCount getNoCardCount(Map<String, Object> map) {
//		String url = "http://47.104.25.59:1179/NoCardCount";
//		String result=new HttpClientUtils().doPost(url,map);
//		ReturnCount count = JSON.parseObject(result,ReturnCount.class);
//		return count;
//	}

	@Override
	public PageInfo<TransactionalEntity> countbyArea(Map<String, Object> map) {
		String url = "http://47.104.22.226/yj-account/transactional/findTransactionalByArea";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,PageInfo.class);
	}

	@Override
	public String count(Map<String, Object> map) {
		String url = "http://47.104.22.226/yj-account/transactional/countByArea";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject job = JSONObject.parseObject(result);
		String data = job.getString("data");
		JSONObject jobs = JSONObject.parseObject(data);
		String totalFee = jobs.getString("totalFee");
		return totalFee;
	}

	@Override
	public CashSet findCash(Map<String, Object> map) {
		String url = "http://47.104.4.155/yj-account/config/amount";
		String result = new HttpClientUtils().doPost(url, map);
		CashSet cs = JSON.parseObject(result).getJSONObject("data").toJavaObject(CashSet.class);
//		JSON.parseObject(t .getString("data")).getString("aliWithdrawFee");
		return cs;
	}

	@Override
	public String setCash(Map<String, Object> map) {
		String url = "http://47.104.4.155/yj-account/config/update";
		String result = new HttpClientUtils().doPost(url, map);
		JSONObject job = JSONObject.parseObject(result);
		String respCode = job.getString("respCode");
		return respCode;
	}
}
