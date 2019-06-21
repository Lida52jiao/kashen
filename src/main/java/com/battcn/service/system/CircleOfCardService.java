package com.battcn.service.system;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.battcn.entity.Material;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.CheckinEntity;
import com.battcn.entity.Circle;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtils;
import com.github.pagehelper.PageInfo;

@Service
public class CircleOfCardService {

	public PageInfo<Circle> getCircleList(Map<String, Object> map) {
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
		String url = "http://47.104.25.59:1172/circle/select";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,PageInfo.class);
	}
	public String UpdateCircle(Map<String, Object> map) {
		String url = "http://47.104.25.59:1172/circle/alter";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("respCode");
		if("0000".equals(data)){
			return "审核完成";
		}
		return "审核失败";
	}

	public String DeleteCircle(Map<String, Object> map) {
		String url = "http://47.104.25.59:1172/circle/delete";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("respCode");
		if("0000".equals(data)){
			return "删除成功";
		}
		return "删除失败";
	}

	//Material----Material----Material
	public PageInfo<Material> getMaterialList(Map<String, Object> map) {
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
		String url = "http://47.104.25.59:1172/material/select";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		return JSON.parseObject(data,PageInfo.class);
	}
	public String UpdateMaterial(Map<String, Object> map) {
		String url = "http://47.104.25.59:1172/material/alter";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("respCode");
		if("0000".equals(data)){
			return "审核完成";
		}
		return "审核失败";
	}

	public String DeleteMaterial(Map<String, Object> map) {
		String url = "http://47.104.25.59:1172/material/delete";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("respCode");
		if("0000".equals(data)){
			return "删除成功";
		}
		return "删除失败";
	}
}
