package com.battcn.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.ChannelRate;
import com.battcn.entity.IsLdRate;
import com.battcn.entity.MerChants;
import com.battcn.mapper.ChannelRateMapper;
import com.battcn.mapper.IsLdRateMapper;
import com.battcn.mapper.MerChantsMapper;
import com.battcn.service.system.IsLdRateService;
@Service
public class IsLdRateServiceImpl implements IsLdRateService{

	@Autowired
	private IsLdRateMapper isLdRateMapper;
	@Autowired
	private MerChantsMapper merChantsMapper;
	
	@Override
	public List<IsLdRate> getaisle() {
		List<IsLdRate> list = isLdRateMapper.selectasile();
		return list;
	}

	@Override
	public List<IsLdRate> getaisle(String merId) {
		MerChants m = new MerChants();
		m.setMerChantId(merId);
		List<MerChants> list = merChantsMapper.gain(m);
		List<IsLdRate> isldlist = new ArrayList<IsLdRate>();
		if(list.size()>0){
			MerChants newm = list.get(0);
			String appId = newm.getAppId();
			isldlist = isLdRateMapper.asileGroupByAppId(appId);
		}
		return isldlist;
	}

	@Override
	public List<IsLdRate> getaisleGroup() {
		List<IsLdRate> isldlist = isLdRateMapper.asileGroup();
		return isldlist;
	}

}
