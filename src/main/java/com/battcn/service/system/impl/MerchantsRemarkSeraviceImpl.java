package com.battcn.service.system.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.MerchantsRemark;
import com.battcn.entity.MerchantsRemarkExample;
import com.battcn.mapper.MerchantsRemarkMapper;

@Service
public class MerchantsRemarkSeraviceImpl {

	@Autowired
	private MerchantsRemarkMapper merchantsRemarkMapper;
	
	
	public List<MerchantsRemark> getAllMessage(MerchantsRemark remark){
		MerchantsRemarkExample example = new MerchantsRemarkExample();
		MerchantsRemarkExample.Criteria cri = new MerchantsRemarkExample().createCriteria();
		if(!StringUtils.isBlank(remark.getAgentstatus())){
			cri.andAgentstatusNotEqualTo(remark.getAgentstatus());
		}
		if(!StringUtils.isBlank(remark.getMermp())){
			cri.andMermpEqualTo(remark.getMermp());
		}
		if(!StringUtils.isBlank(remark.getUpmermp())){
			cri.andUpmermpEqualTo(remark.getUpmermp());
		}
		example.or(cri);
		List<MerchantsRemark> list = merchantsRemarkMapper.selectByExample(example);
		return list;
		
	}
	public List<MerchantsRemark> getAll(String merId){
		MerchantsRemarkExample example = new MerchantsRemarkExample();
		MerchantsRemarkExample.Criteria cri = new MerchantsRemarkExample().createCriteria();
		cri.andMerchantidEqualTo(merId);
		example.or(cri);
		List<MerchantsRemark> list = merchantsRemarkMapper.selectByExample(example);
		return list;
		
	}
	public Integer update(MerchantsRemark remark){
		
		Integer count = merchantsRemarkMapper.updateByPrimaryKey(remark);
		return 0;
		
	}
}
