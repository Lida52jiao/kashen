package com.battcn.service.system;

import java.util.List;

import com.battcn.entity.MerChantsRate;
import com.github.pagehelper.PageInfo;

public interface MerchantsRateService {
	
	public PageInfo<MerChantsRate> getList(String appId, String aislecode,String isrepayment);
	
	public List<MerChantsRate> getList();
	
	public MerChantsRate getById(Long id);
	
	public Integer updateRate(MerChantsRate merchantsrate);
	
	public void find(String merChantId, String appId, String institutionId, List<MerChantsRate> list);
	
	public List<MerChantsRate> getMerchantsRateList(String appId,String merType);
	
}
