package com.battcn.service.system.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.KSAgentRateTable;
import com.battcn.entity.KSAgentRateTableExample;
import com.battcn.mapper.KSAgentRateTableMapper;
import com.battcn.service.system.KSAgentRateTableService;
import com.battcn.util.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
@Service
public class KSAgentRateTableServiceImpl implements KSAgentRateTableService {

	@Autowired
	private KSAgentRateTableMapper ksAgentRateTableMapper;

	public List<KSAgentRateTable> getAgentRateByLevel(String agentLevel) {
		KSAgentRateTableExample example = new KSAgentRateTableExample();
		KSAgentRateTableExample.Criteria cri = new KSAgentRateTableExample().createCriteria();
		if(!StringUtils.isBlank(agentLevel)){
			cri.andAgentLevelEqualTo(agentLevel);
		}
		example.or(cri);
		List<KSAgentRateTable> list = ksAgentRateTableMapper.selectByExample(example);
		return list;
	}

	@Override
	public PageInfo<KSAgentRateTable> getList(String appId, String aislecode) {
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
		KSAgentRateTableExample example = new KSAgentRateTableExample();
		KSAgentRateTableExample.Criteria cri = new KSAgentRateTableExample().createCriteria();
		if(!StringUtils.isBlank(appId)){
			cri.andAppIdEqualTo(appId);
		}
		if(!StringUtils.isBlank(aislecode)){
			cri.andAisleCodeEqualTo(aislecode);
		}
		example.or(cri);
		List<KSAgentRateTable> list = ksAgentRateTableMapper.selectByExample(example);
		return new PageInfo<KSAgentRateTable>(list);
	}

	@Override
	public List<KSAgentRateTable> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KSAgentRateTable getById(Long id) {
		KSAgentRateTable rateTable = ksAgentRateTableMapper.selectByPrimaryKey(id);
		return rateTable;
	}

	@Override
	public Integer updateRate(KSAgentRateTable agentrate) {
		KSAgentRateTable rateTable = ksAgentRateTableMapper.selectByPrimaryKey(agentrate.getId());
		return null;
	}

	@Override
	public void find(String merChantId, String appId, String institutionId,
			List<KSAgentRateTable> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<KSAgentRateTable> getKSAgentRateTableList(String appId,
			String merType) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
