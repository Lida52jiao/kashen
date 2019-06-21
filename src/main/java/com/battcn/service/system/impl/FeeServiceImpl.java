package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Fee;
import com.battcn.mapper.FeeMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.FeeService;

@Service
public class FeeServiceImpl extends BaseServiceImpl<Fee> implements FeeService {
	
	@Autowired
	private FeeMapper feeMapper;

}
