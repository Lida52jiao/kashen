package com.battcn.service.system;

import java.util.List;

import com.battcn.entity.AgentBasePrice;
import com.github.pagehelper.PageInfo;


public interface AgentBasePriceService{

	PageInfo<AgentBasePrice> getBasePriceList();
	
	AgentBasePrice getBasePriceById(Long id);
	
	int updateById(AgentBasePrice base);
	
	List<AgentBasePrice> getAgentLevel();
	
	AgentBasePrice getByAgentLevel(String agentlevel);
}
