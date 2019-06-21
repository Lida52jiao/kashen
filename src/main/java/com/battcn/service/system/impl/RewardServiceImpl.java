package com.battcn.service.system.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.battcn.entity.MerCode;
import com.battcn.entity.Reward;
import com.battcn.mapper.RewardMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.RewardService;
import com.github.pagehelper.PageInfo;

@Service
public class RewardServiceImpl extends BaseServiceImpl<Reward> implements RewardService {
	
	@Autowired
	private RewardMapper rewardMapper;


	@Override
	public PageInfo<Reward> recieve() {
		List<Reward> list = rewardMapper.gets();
		return new PageInfo<Reward>(list);
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public String get(Reward reward) {
		rewardMapper.getLock(reward.getId());
		Reward r = rewardMapper.selectById(reward.getId());
		BigDecimal bigDecimal = new BigDecimal(100);
		BigDecimal zero = new BigDecimal(0);
		BigDecimal first = reward.getFirstagent();
		BigDecimal second = reward.getSecondagent();
		BigDecimal member = reward.getMember();
		BigDecimal referee = reward.getReferee();
		BigDecimal insti = reward.getInstitutionid();
		if(first != null){
			r.setFirstagent(first.divide(bigDecimal));
		} else {
			r.setFirstagent(zero);
			first = zero;
		}
		if(second != null){
			r.setSecondagent(second.divide(bigDecimal));
		} else {
			r.setSecondagent(zero);
			second = zero;
		}
		if(member != null){
			r.setMember(member.divide(bigDecimal));
		} else {
			r.setMember(zero);
			member = zero;
		}
		if(referee != null ){
			r.setReferee(referee.divide(bigDecimal));
		} else {
			r.setReferee(zero);
			referee = zero;
		}
		if(insti != null){
			r.setInstitutionid(insti.divide(bigDecimal));
		} else {
			r.setInstitutionid(zero);
			insti = zero;
		}
		
		BigDecimal total = first.add(second.add(member.add(referee.add(insti))));
		int message = 0;
		if(bigDecimal.equals(total)){
			message = rewardMapper.updateRewardById(r);
		} else {
			return "notHundred";
		}
		if(message == 1){
			return "success";
		}
		return "error";
		
	}

	@Override
	public Reward selectById(Integer id) {
		return rewardMapper.selectById(id);
	}



}
