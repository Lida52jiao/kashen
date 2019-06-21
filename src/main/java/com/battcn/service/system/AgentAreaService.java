package com.battcn.service.system;

import com.battcn.entity.AgentBindArea;
import com.battcn.entity.MerChants;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;

public interface AgentAreaService  {
	
	public PageInfo<AgentBindArea> getList(String agentId, String merchantId, String areaName);
	
	public String DeleteBindArea(Long id);

//	public PageInfo<AgentBindArea> querylist(AgentBindArea agent);
}
