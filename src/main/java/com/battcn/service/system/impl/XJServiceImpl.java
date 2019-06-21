package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.XJ;
import com.battcn.mapper.XJMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.XJService;
@Service
public class XJServiceImpl extends BaseServiceImpl<XJ> implements XJService {
	
	@Autowired
	private XJMapper xjMapper; 

}
