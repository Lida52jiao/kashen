package com.battcn.service.system.impl;

import org.springframework.stereotype.Service;

import com.battcn.entity.ButtomEntity;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.ButtomService;
import com.github.pagehelper.PageInfo;

@Service
public class ButtomServiceImpl extends BaseServiceImpl<ButtomEntity> implements
		ButtomService {
	public PageInfo<ButtomEntity> queryButtomForList() {
		return this.queryPageForList();
	}
}
