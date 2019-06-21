package com.battcn.service.system;

import com.battcn.entity.Agent;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;

import java.util.List;

public interface AgentService extends BaseService<Agent> {

	Agent find(Agent n);

	PageInfo<Agent> query(Agent n);

	PageInfo<Agent> list(Agent n);

	List<Agent> getUpAgentListByMerId(String merchantId);

	
}
