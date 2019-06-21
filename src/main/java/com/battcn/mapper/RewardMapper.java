package com.battcn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.battcn.entity.Reward;

public interface RewardMapper extends Mapper<Reward>{
	List<Reward> gets();
	
	void getLock(@Param("id")Integer id);
	
	Reward selectById(@Param("id")Integer id);
	
	int updateRewardById(Reward r);
}