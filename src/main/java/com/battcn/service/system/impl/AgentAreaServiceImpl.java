package com.battcn.service.system.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.battcn.entity.MerChants;
import com.battcn.service.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.AgentBindArea;
import com.battcn.entity.AgentBindAreaExample;
import com.battcn.mapper.AgentBindAreaMapper;
import com.battcn.service.system.AgentAreaService;
import com.battcn.util.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
@Service
public class AgentAreaServiceImpl extends BaseServiceImpl<AgentBindArea> implements AgentAreaService {

	@Autowired
	private AgentBindAreaMapper agentBindAreaMapper;
	@Override
	public PageInfo<AgentBindArea> getList(String agentId, String merchantId, String areaName) {
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
		AgentBindAreaExample example = new AgentBindAreaExample();
		AgentBindAreaExample.Criteria cri = new AgentBindAreaExample().createCriteria();
		/*if(!StringUtils.isBlank(agentId)){
			cri.andAgentidLike("%"+agentId+"%");
		}
		if(!StringUtils.isBlank(merchantId)){
			cri.andMerchantidLike(("%"+merchantId+"%"));
		}
		if(!StringUtils.isBlank(areaName)){
			cri.andAreanameLike("%"+areaName+"%");
		}
		example.or(cri);*/
		List<AgentBindArea> list = agentBindAreaMapper.selectByExample(example);
		return new PageInfo<AgentBindArea>(list);
	}
	@Override
	public String DeleteBindArea(Long id) {
		Integer count = agentBindAreaMapper.deleteByPrimaryKey(id);
		if(count == 1){
			return "删除成功";
		}
		return "删除失败";
	}


//	@Override
//	public PageInfo<AgentBindArea> querylist(AgentBindArea agentBindArea) {
//		HttpServletRequest request = CommonUtil.getHttpRequest();
//		Integer pageNum = CommonUtil
//				.valueOf(request.getParameter("pageNum"), 1);
//		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
//				10);
//		PageHelper.startPage(pageNum, pageSize);
//		String orderField = request.getParameter("sort");
//		String orderDirection = request.getParameter("order");
//		if (StringUtil.isNotEmpty(orderField)) {
//			PageHelper.orderBy(orderField);
//			if (StringUtil.isNotEmpty(orderDirection)) {
//				PageHelper.orderBy(orderField + " " + orderDirection);
//			}
//		}
//		return new PageInfo<AgentBindArea>(agentBindAreaMapper.querylist(agentBindArea));
//	}
}
