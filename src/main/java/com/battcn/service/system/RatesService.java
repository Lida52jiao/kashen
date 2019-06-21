package com.battcn.service.system;

import com.battcn.entity.Rates;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;

public interface RatesService{

public PageInfo<Rates> getList();
	
	public Integer update(Rates Rate);
	
	public PageInfo<Rates> queryList(String agentid);
	
	public Integer insert(Rates rates);
}
