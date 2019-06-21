package com.battcn.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.battcn.entity.MerCode;
import com.battcn.mapper.MerCodeMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.MerCodeService;

@Service
public class MerCodeServiceImpl extends BaseServiceImpl<MerCode> implements MerCodeService {
	
	@Autowired
	private MerCodeMapper merCodeMapper;

	@Override
	public MerCode find(MerCode merCode) {
		return merCodeMapper.get(merCode);
	}

	@Override
	public List<MerCode> recieve(MerCode n) {
		return merCodeMapper.gets(n);
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public String get(String merId, String agentId, String merName, int totalCode) {
		merCodeMapper.getLock(merId);
		merCodeMapper.getLock(agentId);
		MerCode merCode = new MerCode();
		merCode.setMerId(merId);
		MerCode t = merCodeMapper.get(merCode);
		if(t.getAssign() >= totalCode){
			merCodeMapper.alter(t.getMerId(), 0, 0, -totalCode);
			merCodeMapper. renew(agentId, totalCode);
			return "success";
		}
		return "t";
	} 

}
