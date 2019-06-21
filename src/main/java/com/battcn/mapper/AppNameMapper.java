package com.battcn.mapper;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.battcn.entity.AppName;

public interface AppNameMapper extends Mapper<AppName> {

	List<AppName> getAppList();
	
	List<AppName> getAppByAgentId(String agentId);
	
	AppName getApp(String agentId);
}
