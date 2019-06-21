package com.battcn.service.system;

import java.util.List;

import com.battcn.entity.AgentLevel;

public interface AgentLevelService {
	
	public List<AgentLevel> getAgentLevel(String level);
	
	public List<AgentLevel> getAgentLevel();
	
}
