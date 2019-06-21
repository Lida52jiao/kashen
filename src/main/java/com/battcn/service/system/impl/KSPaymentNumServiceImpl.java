package com.battcn.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.FixReward;
import com.battcn.entity.FixRewardExample;
import com.battcn.entity.PaymentNum;
import com.battcn.entity.PaymentNumExample;
import com.battcn.mapper.FixRewardMapper;
import com.battcn.mapper.PaymentNumMapper;
import com.battcn.service.system.KSPaymentNumService;
import com.battcn.util.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
@Service
public class KSPaymentNumServiceImpl implements KSPaymentNumService {
	
	@Autowired
	private PaymentNumMapper paymentNumMapper;
	@Autowired
	private FixRewardMapper fixRewardMapper;

	@Override
	public PageInfo<PaymentNum> getPage() {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		PageHelper.startPage(pageNum, pageSize);
		String orderField = request.getParameter("sort");
		String orderDirection = request.getParameter("order");
		if (StringUtil.isNotEmpty(orderField)) {
			PageHelper.orderBy(orderField);
			if (StringUtil.isNotEmpty(orderDirection)) {
				PageHelper.orderBy(orderField + " " + orderDirection);
			}
		}
		PaymentNumExample example = new PaymentNumExample();
		List<PaymentNum> list = paymentNumMapper.selectByExample(example);
		FixRewardExample fixexample = new FixRewardExample();
		FixReward fix = fixRewardMapper.selectByExample(fixexample).get(0);
		List<PaymentNum> newList = new ArrayList<PaymentNum>();
		Long number = new Long(0);
		for(PaymentNum num:list){
			if(num.getRewardOrNot().equals("N")){//小咖不交钱所以不分钱
				num.setOneMer(number);
				num.setTwoMer(number);
				num.setThreeMer(number);
				newList.add(num);
			} else {
				num.setOneMer(fix.getOneMer());
				num.setTwoMer(fix.getTwoMer());
				num.setThreeMer(fix.getThreeMer());
				newList.add(num);
			}
		}
		return new PageInfo<PaymentNum>(newList);
	}

	@Override
	public Integer updateData(Long id,Long fixReward, Long subsidy,Long oneMer,Long twoMer, Long threeMer,String rewardOrNot) {
		Long lonum = new Long(0);
		PaymentNum pay = paymentNumMapper.selectByPrimaryKey(id);
		Long six = new Long(100);
		pay.setFixReward(fixReward*six);
		pay.setSubsidy(subsidy*six);
		pay.setRewardOrNot(rewardOrNot);
		Integer count = paymentNumMapper.updateByPrimaryKey(pay);
		FixRewardExample fixexample = new FixRewardExample();
		FixReward fix = fixRewardMapper.selectByExample(fixexample).get(0);
		fix.setOneMer(oneMer*six);
		fix.setTwoMer(twoMer*six);
		fix.setThreeMer(threeMer*six);
		if(!oneMer.equals(lonum)){
			fixRewardMapper.updateByPrimaryKey(fix);
		}
		return count;
	}

	@Override
	public PaymentNum getByIdentity() {
		PaymentNumExample example = new PaymentNumExample();
		example.or().andPaymentIdentityEqualTo("bigShot");
		List<PaymentNum> list = paymentNumMapper.selectByExample(example);
		if(list.size()==1){
			return list.get(0);
		}
		return new PaymentNum();
	}

	@Override
	public PaymentNum getById(Long id) {
		PaymentNum pay = paymentNumMapper.selectByPrimaryKey(id);
		return pay;
	}

	@Override
	public Integer confirmData(PaymentNum num) {
		PaymentNum pay = paymentNumMapper.selectByPrimaryKey(num.getId());
		Long six = new Long(100);
		pay.setPaymentNum(num.getPaymentNum()*six);
		Integer count = paymentNumMapper.updateByPrimaryKey(pay);
		return count;
	}

}
