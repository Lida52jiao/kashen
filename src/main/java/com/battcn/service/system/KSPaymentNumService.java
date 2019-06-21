package com.battcn.service.system;

import com.battcn.entity.PaymentNum;
import com.github.pagehelper.PageInfo;

public interface KSPaymentNumService {
	
	PageInfo<PaymentNum> getPage();
	
	PaymentNum getByIdentity();
	
	PaymentNum getById(Long id);
	
	Integer updateData(Long id,Long fixReward, Long subsidy,Long oneMer,Long twoMer, Long threeMer,String rewardOrNot);
	
	Integer confirmData(PaymentNum num);

}
