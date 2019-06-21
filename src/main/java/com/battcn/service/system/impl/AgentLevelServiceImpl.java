package com.battcn.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.AgentLevel;
import com.battcn.entity.AgentLevelExample;
import com.battcn.mapper.AgentLevelMapper;
import com.battcn.service.system.AgentLevelService;
@Service
public class AgentLevelServiceImpl implements AgentLevelService {
	
	@Autowired
	private AgentLevelMapper agentLevelMapper;

	@Override
	public List<AgentLevel> getAgentLevel(String level) {
		AgentLevelExample example = new AgentLevelExample();
		//AgentLevelExample.Criteria cri = new AgentLevelExample().createCriteria();
		if("1".equals(level)){
			example.or().andLevelLessThan(level);
		}
		
		List<AgentLevel> list = agentLevelMapper.selectByExample(example);
		
		return list;
	}

	@Override
	public List<AgentLevel> getAgentLevel() {
		AgentLevelExample example = new AgentLevelExample();
		List<AgentLevel> list = agentLevelMapper.selectByExample(example);
		return list;
	}

}
