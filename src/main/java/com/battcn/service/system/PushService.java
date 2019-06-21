package com.battcn.service.system;

import com.battcn.entity.JiGuang;
import com.battcn.entity.Push;
import com.battcn.service.BaseService;

public interface PushService extends BaseService<Push> {
	
	JiGuang getjiGuang(String institutionId);

	JiGuang find(String appId, String institutionId);
	
}
