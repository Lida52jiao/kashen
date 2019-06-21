package com.battcn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.battcn.entity.MerCode;

public interface MerCodeMapper extends Mapper<MerCode> {
	
	void getLock(@Param("agentId")String agentId);

	MerCode get(MerCode merCode);

	void alter(@Param("agentId")String agentId, @Param("generatedCode")int generatedCode, @Param("notused")int notused, @Param("assign")int assign);

	void revises(@Param("agentId")String agentId, @Param("used")int used, @Param("notused")int notused);

	List<MerCode> gets(MerCode n);

	void renew(@Param("agentId")String agentId, @Param("totalCode")int totalCode);
	
}
