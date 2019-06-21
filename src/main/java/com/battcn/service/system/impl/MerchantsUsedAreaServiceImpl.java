package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.KSMerUsedArea;
import com.battcn.mapper.MerchantsUsedAreaMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.MerchantsUsedAreaService;
@Service
public class MerchantsUsedAreaServiceImpl extends BaseServiceImpl<KSMerUsedArea> implements
MerchantsUsedAreaService  {
	
	@Autowired
	private MerchantsUsedAreaMapper merchantsUsedAreaMapper;

}
