package com.battcn.service.system.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.PayRecord;
import com.battcn.service.system.WBPayRecordService;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtils;
import com.github.pagehelper.PageInfo;
@Service
public class WBPayRecordServiceImpl implements WBPayRecordService {

	@Override
	public PageInfo<PayRecord> getList(Map<String, Object> map) {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		map.put("pages", pageNum.toString());
		map.put("pageSize", pageSize.toString());
		String url = "http://47.104.198.186:1199/getPayRecord";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		List<PayRecord> list = JSON.parseArray(t.getString("list"),PayRecord.class);
		PageInfo<PayRecord> page = new PageInfo<PayRecord>();
		page.setList(list);
		page.setPageSize(Integer.parseInt(t.getString("pageSize")));
		page.setPages(Integer.parseInt(t.getString("pages")));
		page.setTotal(Integer.parseInt(t.getString("total")));
		return page;
	}
}
