package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.SureRecord;
import com.battcn.mapper.SureRecordMapper;
import com.battcn.service.system.SureRecordService;
@Service
public class SureRecordServiceImpl implements SureRecordService {
	
	@Autowired
	private SureRecordMapper sureRecordMapper;

	@Override
	public Integer insertSureRecord(SureRecord record) {
		Integer count = sureRecordMapper.insert(record);
		return count;
	}

}
