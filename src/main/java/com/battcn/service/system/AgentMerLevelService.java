package com.battcn.service.system;

import com.battcn.entity.AgentMerLevel;
import com.github.pagehelper.PageInfo;

public interface AgentMerLevelService {
	
	PageInfo<AgentMerLevel> getList();
	
	Integer editManage(AgentMerLevel level);
	
	Integer addManage(AgentMerLevel level);
	
	AgentMerLevel getById(Long id);
	
	AgentMerLevel getByWeight(Long levelweight);
	

}
