package com.battcn.service.system.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Subsidy;
import com.battcn.entity.SubsidyExample;
import com.battcn.mapper.SubsidyMapper;
import com.battcn.service.system.SubsidyService;
@Service
public class SubsidyServiceImpl implements SubsidyService {
	
	@Autowired
	private SubsidyMapper subsidyMapper;

	@Override
	public Integer editOrInsertSubsidy(Subsidy subsidy) {
		SubsidyExample example = new SubsidyExample();
		List<Subsidy> list = subsidyMapper.selectByExample(example);
		BigDecimal bigDecimal = new BigDecimal(10000);
		if(list.size() == 0){
			Subsidy sub = new Subsidy();
			sub.setTwoagent((subsidy.getTwoagent()).divide(bigDecimal));
			sub.setCountryagent((subsidy.getCountryagent()).divide(bigDecimal));
			sub.setCityagent((subsidy.getCityagent()).divide(bigDecimal));
			sub.setProvinceagent((subsidy.getProvinceagent()).divide(bigDecimal));
			sub.setOnemerchant((subsidy.getOnemerchant()).divide(bigDecimal));
			sub.setTwomerchant((subsidy.getTwomerchant()).divide(bigDecimal));
			sub.setTopagent((subsidy.getTopagent()).divide(bigDecimal));
			Integer count = subsidyMapper.insert(subsidy);
			return count;
		}
		Subsidy sub = list.get(0);
		sub.setTwoagent((subsidy.getTwoagent()).divide(bigDecimal));
		sub.setCountryagent((subsidy.getCountryagent()).divide(bigDecimal));
		sub.setCityagent((subsidy.getCityagent()).divide(bigDecimal));
		sub.setProvinceagent((subsidy.getProvinceagent()).divide(bigDecimal));
		sub.setOnemerchant((subsidy.getOnemerchant()).divide(bigDecimal));
		sub.setTwomerchant((subsidy.getTwomerchant()).divide(bigDecimal));
		sub.setTopagent((subsidy.getTopagent()).divide(bigDecimal));
		Integer count = subsidyMapper.updateByPrimaryKey(sub);
		return count;
	}

	@Override
	public Subsidy getSubsidy() {
		SubsidyExample example = new SubsidyExample();
		List<Subsidy> list = subsidyMapper.selectByExample(example);
		if(list.size() != 0){
			Subsidy sub = list.get(0);
			return sub;
		}
		return new Subsidy();
	}

}
