package com.battcn.service.system.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.AgentMerLevel;
import com.battcn.entity.AgentMerLevelExample;
import com.battcn.entity.Num;
import com.battcn.mapper.AgentMerLevelMapper;
import com.battcn.service.system.AgentMerLevelService;
import com.battcn.service.system.NumService;
import com.battcn.util.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
@Service
public class AgentMerLevelServiceImpl implements AgentMerLevelService {
	
	@Autowired
	private AgentMerLevelMapper agentMerLevelMapper;
	@Autowired
	private NumService numService; 

	@Override
	public PageInfo<AgentMerLevel> getList() {
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
		AgentMerLevelExample example = new AgentMerLevelExample();
		List<AgentMerLevel> list = agentMerLevelMapper.selectByExample(example);
		return new PageInfo<AgentMerLevel>(list);
	}

	@Override
	public Integer editManage(AgentMerLevel level) {
		AgentMerLevel merLevel = agentMerLevelMapper.selectByPrimaryKey(level.getId());
		merLevel.setLevelname(level.getLevelname());
		if(!StringUtils.isBlank(level.getLevellogo())){
			merLevel.setLevellogo(level.getLevellogo());
		}
		if(!StringUtils.isBlank(level.getLevelbcard())){
			merLevel.setLevelbcard(level.getLevelbcard());
		}
		merLevel.setLevelweight(level.getLevelweight());
		merLevel.setAgentornot(level.getAgentornot());
		merLevel.setShowornot(level.getShowornot());
		merLevel.setFuncexplain(level.getFuncexplain());
		merLevel.setUsablemodule(level.getUsablemodule());
		Integer count = agentMerLevelMapper.updateByPrimaryKey(merLevel);
		return count;
	}

	@Override
	public Integer addManage(AgentMerLevel level) {
		Integer count = agentMerLevelMapper.insert(level);
		Num num = new Num();
		num.setMertype(String.valueOf(level.getLevelweight()));
		num.setLevel(level.getLevelname());
		num.setPaytype("hy"+String.valueOf(level.getLevelweight()));
		num.setViptype("F");
		num.setLevelweight(Long.valueOf(level.getLevelweight()));
		numService.addNum(num);
		return count;
	}

	@Override
	public AgentMerLevel getById(Long id) {
		AgentMerLevel lev = agentMerLevelMapper.selectByPrimaryKey(id);
		return lev;
	}

	@Override
	public AgentMerLevel getByWeight(Long levelweight) {
		AgentMerLevelExample example = new AgentMerLevelExample();
		example.or().andLevelweightEqualTo(levelweight);
		List<AgentMerLevel> list = agentMerLevelMapper.selectByExample(example);
		if(list.size()>0){
			AgentMerLevel level = list.get(0);
			return level;
		}
		return new AgentMerLevel();
	}

}
