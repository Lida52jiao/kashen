package com.battcn.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.ChannelRate;
import com.battcn.entity.MerChants;
import com.battcn.mapper.ChannelRateMapper;
import com.battcn.mapper.MerChantsMapper;
import com.battcn.service.system.ChangelRateService;
@Service
public class ChannelRateImpl implements ChangelRateService {

	@Autowired
	private ChannelRateMapper channelRateMapper;
	@Autowired
	private MerChantsMapper merChantsMapper;
	
	@Override
	public List<ChannelRate> getaisle() {
		List<ChannelRate> list = channelRateMapper.selectasile();
		return list;
	}
	@Override
	public List<ChannelRate> getaisle(String merId) {
		MerChants m = new MerChants();
		m.setAgentId(merId);
		List<MerChants> list = merChantsMapper.gain(m);
		List<ChannelRate> channelist = new ArrayList<ChannelRate>();
		if(list.size()>0){
			MerChants newm = list.get(0);
			String appId = newm.getAppId();
			channelist = channelRateMapper.asileGroupByAppId(appId);
		}
		return channelist;
	}



}
