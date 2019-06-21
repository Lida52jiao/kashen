package com.battcn.service.system.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.RepayWithdrawEntity;
import com.battcn.entity.Transactional;
import com.battcn.service.system.RepayRecordService;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtils;
import com.github.pagehelper.PageInfo;
@Service
public class RepayRecordServiceImpl implements RepayRecordService {

	@Override
	public PageInfo<RepayWithdrawEntity> getList(Map<String, Object> map) {
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
		String url = "http://172.31.109.39/yj-account/withdraw/findWithdrawByTime";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		List<RepayWithdrawEntity> list = JSON.parseArray(t.getString("list"),RepayWithdrawEntity.class);
		PageInfo<RepayWithdrawEntity> page = new PageInfo<RepayWithdrawEntity>();
		page.setList(list);
		page.setPageSize(Integer.parseInt(t.getString("pageSize")));
		page.setPages(Integer.parseInt(t.getString("pages")));
		page.setTotal(Integer.parseInt(t.getString("total")));
		return page;
	}

}
