package com.battcn.service.system;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.entity.HYUserIncomCount;
import com.battcn.entity.HYUserWithdrawCount;
import com.battcn.util.HttpClientUtils;

@Service
public class HYInstitutionFormService {

//	//平台分润统计
////	public List<BigDecimal> hyIncomCount(String startTime,String finishTime){
////		Map<String, Object> map = new HashMap<String, Object>();
////		map.put("institutionId",InstitutionIdNumber.AGENT);
////		map.put("appId", "0000");
////		if(!startTime.equals("0")){
////			map.put("startTime", startTime);
////		} else {
////			map.put("startTime", null);
////		}
////		if(!finishTime.equals("0")){
////			map.put("finishTime", finishTime);
////		} else {
////			map.put("finishTime", null);
////		}
////		String url = "http://47.104.25.59:1179/transactional/getKsInstiIncome";
////		String result=new HttpClientUtils().doPost(url,map);
////		List<BigDecimal> list = JSON.parseArray(result,BigDecimal.class);
////		return list;
////	}
////	//用户分润及提现统计
////	public HYUserWithdrawCount hyUserCount(String startTime,String finishTime){
////		Map<String, Object> map = new HashMap<String, Object>();
////		map.put("institutionId",InstitutionIdNumber.AGENT);
////		map.put("appId", "0000");
////		if(!startTime.equals("0")){
////			map.put("startTime", startTime);
////		} else {
////			map.put("startTime", null);
////		}
////		if(!finishTime.equals("0")){
////			map.put("finishTime", finishTime);
////		} else {
////			map.put("finishTime", null);
////		}
////		String url = "http://47.104.25.59:1179/transactional/getUserCount";
////		String result=new HttpClientUtils().doPost(url,map);
////		HYUserWithdrawCount count = JSON.parseObject(result,HYUserWithdrawCount.class);
////		return count;
////	}
}
