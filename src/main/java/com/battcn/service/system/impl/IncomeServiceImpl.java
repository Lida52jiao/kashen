package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Income;
import com.battcn.mapper.IncomeMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.IncomeService;

@Service
public class IncomeServiceImpl extends BaseServiceImpl<Income> implements IncomeService {
	
	@Autowired
	private IncomeMapper incomeMapper; 

}
