package com.battcn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.battcn.entity.Tran;
import com.battcn.entity.Transaction;

public interface TransactionMapper extends Mapper<Transaction> {

	public List<Transaction> selectAllList(@Param("merId")String merId,@Param("agentName")String agentName,@Param("merChantId")String merChantId);
	
	List<Transaction> selectByPageList(Tran tran);
	
	List<Transaction> selectByPageForNoCardList(@Param("appId")String appId,@Param("merId")String merId,@Param("agentStatus")String agentStatus);
}
