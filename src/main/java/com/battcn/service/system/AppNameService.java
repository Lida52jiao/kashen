package com.battcn.service.system;

import java.util.List;

import com.battcn.entity.AppName;
import com.battcn.service.BaseService;

public interface AppNameService extends BaseService<AppName> {

	List<AppName> getList();
	
	AppName getApp(String agentId);
}
