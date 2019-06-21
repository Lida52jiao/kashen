package com.battcn.service.system;

import com.battcn.entity.Tran;
import com.battcn.entity.Transaction;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface TransactionService extends BaseService<Transaction> {

	public PageInfo<Transaction> selectPageForList(String merId,String agentName,String merChantId);
	
	public PageInfo<Transaction> selectPageForNoCardList(Transaction transaction,String appId);
	
	public PageInfo<Transaction> queryPage(Tran tran);

	public String bindagent(String oneMerId, String merId, String merChantId,String agentlevel, String sure);

}
