package com.battcn.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.FixReward;
import com.battcn.entity.FixRewardExample;
import com.battcn.mapper.FixRewardMapper;
import com.battcn.service.system.KSFixRewardService;
@Service
public class KSFixRewardServiceImpl implements KSFixRewardService {
	
	@Autowired
	private FixRewardMapper fixRewardMapper;
	@Override
	public FixReward getData() {
		FixRewardExample example = new FixRewardExample();
		List<FixReward> list = fixRewardMapper.selectByExample(example);
		return list.get(0);
	}

	@Override
	public Integer updateData(FixReward fix) {
		// TODO Auto-generated method stub
		return null;
	}

}
