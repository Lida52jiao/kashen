package com.battcn.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Profit;
import com.battcn.entity.ProfitExample;
import com.battcn.mapper.ProfitMapper;
import com.battcn.service.system.ProfitService;
@Service
public class ProfitServiceImpl implements ProfitService {
	
	@Autowired
	private ProfitMapper profitMapper;

	@Override
	public String getProfit() {
		ProfitExample example = new ProfitExample();
		List<Profit> list = profitMapper.selectByExample(example);
		if(list.size()!=0){
			return list.get(0).getProfitmodel();
		}
		return "";
	}

}
