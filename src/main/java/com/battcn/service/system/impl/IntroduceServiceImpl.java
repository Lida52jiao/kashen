package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Introduce;
import com.battcn.mapper.IntroduceMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.IntroduceService;
@Service
public class IntroduceServiceImpl extends BaseServiceImpl<Introduce> implements IntroduceService {
	
	@Autowired
	private IntroduceMapper introduceMapper; 

}
