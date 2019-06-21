package com.battcn.service.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.Interlocution;
import com.battcn.entity.Title;
import com.battcn.entity.Withdraw;
import com.battcn.service.system.QuestionService;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtils;
import com.github.pagehelper.PageInfo;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Override
	public List<Title> queryObjectForList() {
		String resultJsonStr = HttpClientUtils.doPost("http://47.104.25.59:1172/title/select");
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		List<Title> title = JSON.parseArray(job.getString("data"),Title.class);
		return title;
	}

	@Override
	public String save(String title, String appId, String question,
			String answer, String answers, String merId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("question", question);
		map.put("answer", answer);
		map.put("type", title);
		map.put("forwardURL", answers);
		map.put("institutionId", merId);
		map.put("appId", appId);
		String resultJsonStr = HttpClientUtils.doPost("http://47.104.25.59:1172/interlocution/insert", map);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		String respCode = job.getString("respCode");
		if("0000".equals(respCode)){
			return "success";
		}
		return "f";
	}

	@Override
	public PageInfo<Interlocution> query(Map<String, Object> map) {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		map.put("pageNum", pageNum.toString());
		map.put("pageSize", pageSize.toString());
		String url = "http://47.104.25.59:1172/interlocution/select";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		//"total":35,"pageSize":10,"currentPage":1,"totalPages":4,"rows"
		JSONObject data = JSONObject.parseObject(t.getString("data"));
		List<Interlocution> list = JSON.parseArray(data.getString("list"),Interlocution.class);
		PageInfo<Interlocution> tList = new PageInfo<Interlocution>();
		tList.setList(list);
		tList.setPageSize(Integer.parseInt(data.getString("pageSize")));
		tList.setPages(Integer.parseInt(data.getString("pages")));
		tList.setTotal(Integer.parseInt(data.getString("total")));
		
		return tList;
	}

	@Override
	public Interlocution recieve(Map<String, Object> map) {
		String resultJsonStr = HttpClientUtils.doPost("http://47.104.25.59:1172/interlocution/selects", map);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		Interlocution interlocution = JSON.parseObject(job.getString("data"), Interlocution.class);
		return interlocution;
	}

	@Override
	public String alter(Map<String, Object> map) {
		String resultJsonStr = HttpClientUtils.doPost("http://47.104.25.59:1172/interlocution/alter", map);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		String respCode = job.getString("respCode");
		if("0000".equals(respCode)){
			return "success";
		}
		return "f";
	}

	@Override
	public String deletes(Map<String, Object> map) {
		String resultJsonStr = HttpClientUtils.doPost("http://47.104.25.59:1172/interlocution/delete", map);
		JSONObject job = JSONObject.parseObject(resultJsonStr);
		String respCode = job.getString("respCode");
		if("0000".equals(respCode)){
			return "success";
		}
		return "f";
	}

}
