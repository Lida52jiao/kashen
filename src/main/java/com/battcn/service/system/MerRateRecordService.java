package com.battcn.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.MerRateRecord;
import com.battcn.mapper.MerRateRecordMapper;

@Service
public class MerRateRecordService {
	
	@Autowired
	private MerRateRecordMapper merRateRecordMapper;
	
	public Integer insertChangeRecord(MerRateRecord record){
		Integer count = merRateRecordMapper.insert(record);
		return count;
	}

}
