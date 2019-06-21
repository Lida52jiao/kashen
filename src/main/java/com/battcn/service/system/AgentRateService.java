package com.battcn.service.system;

import com.battcn.entity.AgentRate;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;

public interface AgentRateService {

	public PageInfo<AgentRate> getList();
	
	public Integer update(AgentRate agentRate);
	
	public PageInfo<AgentRate> queryList(String agentid);
	
	public Integer insert(AgentRate agentRate);
}
