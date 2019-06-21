package com.battcn.service.system;

import java.util.Map;

import com.battcn.entity.RepayWithdrawEntity;
import com.github.pagehelper.PageInfo;

public interface RepayRecordService {
	
	PageInfo<RepayWithdrawEntity> getList(Map<String, Object> map);

}
