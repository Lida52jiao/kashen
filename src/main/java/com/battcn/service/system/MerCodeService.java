package com.battcn.service.system;

import java.util.List;

import com.battcn.entity.MerCode;
import com.battcn.service.BaseService;

public interface MerCodeService extends BaseService<MerCode> {

	MerCode find(MerCode merCode);

	List<MerCode> recieve(MerCode n);

	String get(String merId, String agentId, String merName, int totalCode);

}
