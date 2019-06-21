package com.battcn.service.system;

import java.util.Map;

import com.battcn.entity.Withdraw;
import com.battcn.entity.WithdrawEntity;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;

public interface WithdrawEntityService extends BaseService<WithdrawEntity> {

	PageInfo<Withdraw> query(Map<String, Object> map);

	PageInfo<Withdraw> gain(Map<String, Object> map);

	String find(String orderNo, String remarks, String userName);

	PageInfo<Withdraw> find(Map<String, Object> map);
	
	PageInfo<Withdraw> getList(Map<String, Object> map);

	Object finds(String id);

	String updates(String userName, String orderNo, String state, String remarks);

	String finds(String orderNo, String remarks, String userName);
	
	PageInfo<Withdraw> selectLists(Map<String, Object> map);

	String alter(String userName, String orderNo, String state, String remarks);

	PageInfo<Withdraw> getWithdraw(Map<String, Object> map);

}
