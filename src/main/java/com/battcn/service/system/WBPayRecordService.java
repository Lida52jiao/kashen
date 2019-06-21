package com.battcn.service.system;

import java.util.Map;

import com.battcn.entity.PayRecord;
import com.github.pagehelper.PageInfo;

public interface WBPayRecordService {

	PageInfo<PayRecord> getList(Map<String, Object> map);
}
