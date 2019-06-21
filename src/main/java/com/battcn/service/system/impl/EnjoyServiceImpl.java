package com.battcn.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.EnjoyOrNot;
import com.battcn.entity.EnjoyOrNotExample;
import com.battcn.mapper.EnjoyOrNotMapper;
import com.battcn.service.system.EnjoyService;
@Service
public class EnjoyServiceImpl implements EnjoyService {
	
	@Autowired
	private EnjoyOrNotMapper enjoyOrNotMapper;

	@Override
	public String getEnjoy() {
		EnjoyOrNotExample example = new EnjoyOrNotExample();
		List<EnjoyOrNot> list = enjoyOrNotMapper.selectByExample(example);
		if(list.size()!=0){
			return list.get(0).getMinornot();
		}
		return "";
	}

	@Override
	public Integer editEnjoy(EnjoyOrNot enjoy) {
		EnjoyOrNotExample example = new EnjoyOrNotExample();
		List<EnjoyOrNot> list = enjoyOrNotMapper.selectByExample(example);
		if(list.size()==0){
			Integer count = enjoyOrNotMapper.insert(enjoy);
			return count;
		} else if(list.size()==1){
			EnjoyOrNot not = list.get(0);
			not.setMinornot(enjoy.getMinornot());
			not.setMaxornot("Y");
			Integer count = enjoyOrNotMapper.updateByPrimaryKey(not);
			return count;
		}
		return 0;
	}

	
}
