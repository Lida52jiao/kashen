package com.battcn.service.system;

import java.util.List;

import com.battcn.entity.IsLdRate;

public interface IsLdRateService {

	List<IsLdRate> getaisle();
	
	List<IsLdRate> getaisleGroup();
	
	List<IsLdRate> getaisle(String merId);
}
