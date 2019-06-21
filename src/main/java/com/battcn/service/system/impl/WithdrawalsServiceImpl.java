package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Withdrawals;
import com.battcn.mapper.WithdrawalsMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.WithdrawalsService;

@Service 
public class WithdrawalsServiceImpl extends BaseServiceImpl<Withdrawals> implements WithdrawalsService {
	
	@Autowired
	private WithdrawalsMapper withdrawalsMapper;

}
