package com.battcn.service.system;

import java.util.List;

import com.battcn.entity.KSAgentRateTable;

public interface KSgentRateTableSAervice {
	
	List<KSAgentRateTable> getAgentRateByLevel(String agentLevel);

}
