package com.battcn.service.system.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.battcn.entity.*;
import com.battcn.service.system.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.constant.InstitutionIdNumber;
import com.battcn.mapper.TransactionMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.util.CommonUtil;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class TransactionServiceImpl extends BaseServiceImpl<Transaction> implements TransactionService {
	
	@Autowired
	private TransactionMapper transactionMapper;
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private AgentLevelMerTypeService agentLevelMerTypeService;
	@Autowired
	private MerchantsRateService merchantsRateService;
	@Autowired
	private RatesService ratesService;
	@Autowired
	private SureRecordService sureRecordService;
	@Autowired
	private AgentRateService agentRateService;
//	private TransactionService transactionService;
	@Override
	public PageInfo<Transaction> selectPageForList(String merId,String agentName,String merChantId) {
		
		
		
		List<Transaction> list = transactionMapper.selectAllList(merId,agentName,merChantId);
		return new PageInfo<Transaction>(list);
	}

	@Override
	public PageInfo<Transaction> queryPage(Tran tran) {
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
		
		List<Transaction> list = transactionMapper.selectByPageList(tran);
		PageInfo<Transaction> page = new PageInfo<Transaction>(list);
		List<Transaction> newList = new ArrayList<Transaction>();
		for(Transaction t:list){
			String merChantId = t.getMerChantId();
			MerChants m = new MerChants();
			m.setMerChantId(merChantId);
			MerChants merChants = merChantsService.findByObject(m);
			t.setAppName(merChants.getAppName());
			newList.add(t);
		}
		
		PageInfo<Transaction> newPage = new PageInfo<Transaction>();
		newPage.setList(newList);
		newPage.setPageSize(page.getPageSize());
		newPage.setPages(page.getPages());
		newPage.setTotal(page.getTotal());
		return newPage;
	}
	@Override
	public PageInfo<Transaction> selectPageForNoCardList(Transaction transaction,String appId) {
		UserEntity k=UserEntityUtil.getUserFromSession();
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
		List<Transaction> list = transactionMapper.selectByPageForNoCardList(appId,transaction.getMerId(), transaction.getAgentStatus());
		PageInfo<Transaction> page = new PageInfo<Transaction>(list);
		List<Transaction> newList = new ArrayList<Transaction>();
		for(Transaction t:list){
			String merChantId = t.getMerChantId();
			MerChants m = new MerChants();
			m.setMerChantId(merChantId);
			MerChants merChants = merChantsService.findByObject(m);
			t.setAppName(merChants.getAppName());
			//总平台不能修改自己，我们的个人账号才能修改
			if(!t.getMerId().equals(InstitutionIdNumber.AGENT) || k.getAccountName().equals("superAdmin")){
				newList.add(t);
			}
		}
		
		PageInfo<Transaction> newPage = new PageInfo<Transaction>();
		newPage.setList(newList);
		newPage.setPageSize(page.getPageSize());
		newPage.setPages(page.getPages());
		newPage.setTotal(page.getTotal());
		return newPage;
	//	return new PageInfo<Transaction>(list);
	}
	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
	@Override
	public String bindagent(String oneMerId, String merId, String merChantId,String agentlevel, String sure) {
		if("".equals(oneMerId) || "".equals(merId) || "".equals(merChantId)){
			return "s";
		}
		UserEntity userk=UserEntityUtil.getUserFromSession();
		Agent agent=new Agent();
		agent.setMerId(merId);
		Agent v=agentService.findByObject(agent);
		if(v == null){
			return "tt";
		}
		Transaction k=new Transaction();
		k.setMerChantId(merChantId);
		Transaction ss = transactionMapper.selectOne(k);
		if(ss != null){
			return "ff";
		}
		Transaction transactions=new Transaction();
		transactions.setMerId(merId);
		Transaction tt=transactionMapper.selectOne(transactions);
		if(tt != null){
			return "t";
		}
		Transaction savetransation=new Transaction();
		savetransation.setMerId(merId);
		savetransation.setAgentName(v.getMerName());
		savetransation.setMerChantId(merChantId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		savetransation.setCreatDate(sdf.format(new Date()));
		savetransation.setAgentStatus(agentlevel);
		Integer message = transactionMapper.insert(savetransation);
		MerChants m = new MerChants();
		m.setMerChantId(merChantId);
		MerChants newm = merChantsService.findByObject(m);
		AgentLevelMerType levelType = new AgentLevelMerType();
		levelType.setAgentLevel(agentlevel);
		//查询代理等级对应的商户等级
		AgentLevelMerType agentmerType = agentLevelMerTypeService.findByObject(levelType);
		//升级代理商，默认将代理费的通道费率设置入表（费率来自商户通道表）
		List<MerChantsRate> rateList =  merchantsRateService.getMerchantsRateList("0000", agentmerType.getMerType());
		String zeroMessage = "";
		for(MerChantsRate rate:rateList){
			//无卡的
			if(rate.getIsrepayment().equals("N")){
				AgentRate agentRate = new AgentRate();
				agentRate.setMerchantid(merChantId);
				agentRate.setAgentid(merId);
				agentRate.setRate(rate.getRate());
				agentRate.setD0fee(rate.getD0fee().intValue());
				agentRate.setAislecode(rate.getAislecode());
				agentRateService.insert(agentRate);

			} else if(rate.getIsrepayment().equals("Y")){//还款的
				Rates rates = new Rates();
				rates.setMerchantid(merChantId);
				rates.setAgentid(merId);
				rates.setRate(rate.getRate());
				rates.setD0fee(rate.getD0fee().intValue());
				rates.setAislecode(rate.getAislecode());
				ratesService.insert(rates);
			}
		}
		//将商户同步转移
		//记录一下谁选择了将商户同步转移，带走给了谁,存储在t_mp_surerecord中
		SureRecord record = new SureRecord();
		record.setUseuser(userk.getUserName()+"##"+userk.getMerId());
		record.setUndoagentid(newm.getAgentId());
		record.setBindagentid(merId);
		record.setBindmerchantid(merChantId);
		record.setAgentstatus(agentlevel);
		record.setBindtime(new Date());
		sureRecordService.insertSureRecord(record);
		List<Agent> upAgentList=agentService.getUpAgentListByMerId(merChantId);
		Map<String,String> upAgentMap=new HashedMap();
		for (Agent a:upAgentList){
			if(a!=null||!"".equals(a)) {
				upAgentMap.put(a.getMerId(), a.getMerId());
			}
			}
		//查询此商户是不是别人的直推、如果是更改这些商户的代理商号为此商户的
		MerChants onem = new MerChants();
		onem.setOneMerId(merChantId);
		List<MerChants> onelist = merChantsService.queryObjectForList(onem);
		//查询此商户是不是别人的间推、如果是更改这些商户的代理商号为此商户的
		MerChants twom = new MerChants();
		twom.setTwoMerId(merChantId);
		List<MerChants> twolist = merChantsService.queryObjectForList(twom);
		//查询此商户是不是别人的间间推。如果是更改这些商户的代理商号为此商户的
		MerChants threem = new MerChants();
		threem.setThreeMerId(merChantId);
		List<MerChants> threelist = merChantsService.queryObjectForList(threem);

		List<MerChants> allList=new ArrayList<>();
		allList.addAll(onelist);
		allList.addAll(twolist);
		allList.addAll(threelist);
		for(MerChants mer123: allList){
			if ("N".equals(mer123.getAgentStatus())){//不是代理
				if (null!=upAgentMap.get(mer123.getAgentId())){
					//本商户的上级代理是升级人上级所有代理中的一个  就带走
					mer123.setAgentId(merId);
					merChantsService.update(mer123);
				}
			} else {//是代理
				Agent t=new Agent();
				t.setMerId(mer123.getAgentId());
				t=agentService.findByObject(t);
				if (oneMerId.equals(t.getOneMerId())){
					//本代理的上级代理跟升级人的上级代理相同就带走
					t.setOneMerId(merId);
					agentService.update(t);
				}
			}

		}
		newm.setAgentStatus(agentlevel);
		newm.setMerType(agentmerType.getMerType());
//        newm.setMerType("3");//将代理的等级与商户等级对应起来（帐期机器人是对应起来，有为统一写死3）
		newm.setAgentId(merId);
		merChantsService.update(newm);
		return "success";

	}
}
