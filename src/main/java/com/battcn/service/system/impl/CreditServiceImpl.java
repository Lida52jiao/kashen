package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Credit;
import com.battcn.mapper.CreditMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.CreditService;

@Service
public class CreditServiceImpl extends BaseServiceImpl<Credit> implements CreditService {
	
	@Autowired
	private CreditMapper creditMapper;

}
