package com.battcn.service.system;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.CardBanner;
import com.battcn.entity.Information;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtils;
import com.github.pagehelper.PageInfo;

@Service
public class UseCardStrategyService {

	public PageInfo<CardBanner> getBannerList(Map<String, Object> map) {
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
		String url = "http://47.104.25.59:1172/banner/select";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,PageInfo.class);
	}
	public String InsertBanner(Map<String, Object> map) {
		String url = "http://47.104.25.59:1172/banner/insert";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("respCode");
		if("0000".equals(data)){
			return "添加成功";
		}
		return "添加失败";
	}
	public CardBanner getBannerById(Map<String, Object> map) {
		String url = "http://47.104.25.59:1172/banner/selects";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,CardBanner.class);
	}
	public String UpdateBanner(Map<String, Object> map) {
		String url = "http://47.104.25.59:1172/banner/alter";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("respCode");
		if("0000".equals(data)){
			return "修改成功";
		}
		return "修改失败";
	}
	public String DeleteBanner(Map<String, Object> map) {
		String url = "http://47.104.25.59:1172/banner/delete";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("respCode");
		if("0000".equals(data)){
			return "删除成功";
		}
		return "删除失败";
	}
	//文章管理
	public PageInfo<Information> getInformationList(Map<String, Object> map) {
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
		String url = "http://47.104.25.59:1172/information/select";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,PageInfo.class);
	}
	public Information getInformationById(Map<String, Object> map) {
		String url = "http://47.104.25.59:1172/information/selects";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,Information.class);
	}
	public String InsertInformation(Map<String, Object> map) {
		String url = "http://47.104.25.59:1172/information/insert";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("respCode");
		if("0000".equals(data)){
			return "添加成功";
		}
		return "添加失败";
	}
	
	public String UpdateInformation(Map<String, Object> map) {
		String url = "http://47.104.25.59:1172/information/alter";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("respCode");
		if("0000".equals(data)){
			return "修改成功";
		}
		return "修改失败";
	}
	public String DeleteInformation(Map<String, Object> map) {
		String url = "http://47.104.25.59:1172/information/delete";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("respCode");
		if("0000".equals(data)){
			return "删除成功";
		}
		return "删除失败";
	}
}
