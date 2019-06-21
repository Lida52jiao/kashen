package com.battcn.service.system;

import org.apache.ibatis.annotations.Param;

import com.battcn.entity.Reward;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;

public interface RewardService extends BaseService<Reward> {


	PageInfo<Reward> recieve();

	String get(Reward reward);

	Reward selectById(Integer Id);
}
