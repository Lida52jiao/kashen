package com.battcn.service.system.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.battcn.constant.Constaint;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.entity.JiGuang;
import com.battcn.entity.Push;
import com.battcn.mapper.PushMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.PushService;
import com.battcn.util.HttpClientUtils;

@Service
public class PushServiceImpl extends BaseServiceImpl<Push> implements PushService {
	
	@Autowired
	private PushMapper pushMapper;

	@Override
	public JiGuang getjiGuang(String institutionId) {
		Map<String, Object> param = new HashMap<>();
		param.put("institutionId", InstitutionIdNumber.AGENT);
		param.put("agentId", institutionId);
		String resultJsonStr = HttpClientUtils.doPost(Constaint.JiGuang, param);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		JiGuang jiGuang=new JiGuang(job.get("appkey").toString(), job.get("secret").toString(), job.get("appId").toString(), job.get("appName").toString());
		return jiGuang;
	}

	@Override
	public JiGuang find(String appId, String institutionId) {
		Map<String, Object> param = new HashMap<>();
		param.put("institutionId", InstitutionIdNumber.AGENT);
		param.put("agentId", institutionId);
		param.put("appId", appId);
		String resultJsonStr = HttpClientUtils.doPost(Constaint.JiGuang, param);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		JiGuang jiGuang=new JiGuang(job.get("appkey").toString(), job.get("secret").toString(), job.get("appId").toString(), job.get("appName").toString());
		return jiGuang;
	}

}
