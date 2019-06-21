package com.battcn.service.system;

import java.util.List;

import com.battcn.entity.KSAgentRateTable;
import com.github.pagehelper.PageInfo;

public interface KSAgentRateTableService {

	List<KSAgentRateTable> getAgentRateByLevel(String agentLevel);
	
	public PageInfo<KSAgentRateTable> getList(String appId, String aislecode);
	
	public List<KSAgentRateTable> getList();
	
	public KSAgentRateTable getById(Long id);
	
	public Integer updateRate(KSAgentRateTable merchantsrate);
	
	public void find(String merChantId, String appId, String institutionId, List<KSAgentRateTable> list);
	
	public List<KSAgentRateTable> getKSAgentRateTableList(String appId,String merType);
}
