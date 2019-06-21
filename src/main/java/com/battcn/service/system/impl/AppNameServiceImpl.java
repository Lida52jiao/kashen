package com.battcn.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.AppName;
import com.battcn.entity.UserEntity;
import com.battcn.mapper.AppNameMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.AppNameService;
import com.battcn.util.UserEntityUtil;
@Service
public class AppNameServiceImpl extends BaseServiceImpl<AppName> implements AppNameService {
	
	@Autowired
	private AppNameMapper appNameMapper;

	@Override
	public List<AppName> getList() {
		UserEntity k=UserEntityUtil.getUserFromSession();
		if(k.getMerId().startsWith("T")){
			return appNameMapper.getAppList();
		}
		return appNameMapper.getAppByAgentId(k.getMerId());
	}

	@Override
	public AppName getApp(String agentId) {
		return appNameMapper.getApp(agentId);
	}
}
