package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Used;
import com.battcn.mapper.UsedMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.UsedService;

@Service
public class UsedServiceImpl extends BaseServiceImpl<Used> implements UsedService {
	
	@Autowired
	private UsedMapper usedMapper;

}
