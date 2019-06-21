package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Risk;
import com.battcn.mapper.RiskMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.RiskService;

@Service
public class RiskServiceImpl extends BaseServiceImpl<Risk> implements RiskService {
	
	@Autowired
	private RiskMapper riskMapper;

}
