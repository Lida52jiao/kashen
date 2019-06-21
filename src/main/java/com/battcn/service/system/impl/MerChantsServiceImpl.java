package com.battcn.service.system.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Mer;
import com.battcn.entity.MerChants;
import com.battcn.mapper.MerChantsMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.MerChantsService;
import com.battcn.util.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

@Service
public class MerChantsServiceImpl extends BaseServiceImpl<MerChants> implements
		MerChantsService {

	@Autowired
	private MerChantsMapper merChantsMapper;

	@Override
	public PageInfo<MerChants> gain(MerChants m) {
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
		List<MerChants> list = merChantsMapper.gain(m);
		return new PageInfo<MerChants>(list);
	}

	@Override
	public PageInfo<MerChants> query(Mer m) {
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
		return new PageInfo<MerChants>(merChantsMapper.query(m));
	}

	@Override
	public void recieve(MerChants t) {
		merChantsMapper.alter(t);
	}

	@Override
	public void alter(MerChants t) {
		merChantsMapper.amend(t);
	}

	@Override
	public PageInfo<MerChants> gains(MerChants m) {
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
		return new PageInfo<MerChants>(merChantsMapper.gets(m));
	}
	@Override
	public List<MerChants> getList(MerChants mer) {
		return merChantsMapper.gain(mer);
	}
}
