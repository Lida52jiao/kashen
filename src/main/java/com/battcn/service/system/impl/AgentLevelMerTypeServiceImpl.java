package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.AgentLevelMerType;
import com.battcn.mapper.AgentLevelMerTypeMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.AgentLevelMerTypeService;
@Service
public class AgentLevelMerTypeServiceImpl extends BaseServiceImpl<AgentLevelMerType> implements AgentLevelMerTypeService {

	@Autowired
	private AgentLevelMerTypeMapper agentLevelMerTypeMapper;
}
