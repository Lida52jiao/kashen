package com.battcn.controller.system;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.battcn.annotation.SystemLog;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.controller.BaseController;
import com.battcn.entity.Agent;
import com.battcn.entity.AgentLevelMerType;
import com.battcn.entity.AgentRate;
import com.battcn.entity.KSAgentRateTable;
import com.battcn.entity.MerChants;
import com.battcn.entity.MerChantsRate;
import com.battcn.entity.Rates;
import com.battcn.entity.SureRecord;
import com.battcn.entity.Tran;
import com.battcn.entity.Transaction;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AgentBasePriceService;
import com.battcn.service.system.AgentLevelMerTypeService;
import com.battcn.service.system.AgentRateService;
import com.battcn.service.system.AgentService;
import com.battcn.service.system.KSAgentRateTableService;
import com.battcn.service.system.MerChantsService;
import com.battcn.service.system.MerchantsRateService;
import com.battcn.service.system.RatesService;
import com.battcn.service.system.SelectAgentLevelService;
import com.battcn.service.system.SureRecordService;
import com.battcn.service.system.TransactionService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Transaction")
public class TransactionController extends BaseController {
	
	@Autowired
	public TransactionService transactionService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private SelectAgentLevelService selectAgentLevelService;

	
	@RequestMapping("insertFloorNumber")
	@SystemLog(module = "代理商管理", methods = "代理商绑定商户关系")
	@ResponseBody
	public String add(String oneMerId, String merId, String merChantId,String agentlevel, String sure) {

        return transactionService.bindagent(oneMerId, merId, merChantId,agentlevel,sure);
	}
	
	@RequestMapping("insertgenerationFee")
	@ResponseBody
	public String a(String oneMerId, String merId, String generationFee) {
		Transaction k=new Transaction();
		k.setMerId(oneMerId);
		Transaction t=transactionService.findByObject(k);
		if(t != null){
			double n=Double.parseDouble(t.getGenerationFee());
			double number=Double.parseDouble(generationFee);
			if(n>=number){
				Transaction transaction=new Transaction();
				transaction.setMerId(merId);
				Transaction h=transactionService.findByObject(transaction);
				h.setGenerationFee(generationFee);
				return transactionService.update(h);	
			}
			return "f";
		}
		return "n";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public PageInfo<Transaction> getList(String merId, String merName, String merMp) throws UnsupportedEncodingException {
		Agent n = null;
		UserEntity k=UserEntityUtil.getUserFromSession();
		if (!"".equals(merId) || !"".equals(merName) || !"".equals(merMp) ){
			n=new Agent();
			merName = new String(merName.getBytes("iso8859-1"), "utf-8");
			n.setMerId(merId);
			n.setMerName(merName);
			n.setMerMp(merMp);
			Agent h=agentService.find(n);
			if(h != null){
				List<Agent> list=new ArrayList<Agent>();
				List<Agent> agentList = get(list,h);
				Agent t=new Agent();
				t.setMerId(k.getMerId());
				if(agentList.contains(agentService.findByObject(t))){
					Transaction transaction=new Transaction();
					transaction.setMerId(h.getMerId());
					List<Transaction> tList=transactionService.queryObjectForList(transaction);
					PageInfo<Transaction> hList=new PageInfo<Transaction>(tList);
					return hList;
				}
			}
			Transaction transaction=new Transaction();
			transaction.setMerId("1");
			PageInfo<Transaction> tList=transactionService.queryPageForList(transaction);
			return tList;
		}
 		if(k.getMerId().startsWith("T")){
      			PageInfo<Transaction> hList=transactionService.queryPageForList();
			return hList;
		}
		n=new Agent();
		n.setOneMerId(k.getMerId());
		List<Agent> hList=agentService.queryObjectForList(n);//查询一级代理
		
		List<Agent> nList = selectAgentLevelService.agentLevel(hList);
		List<Agent> resultList = new ArrayList<Agent>();//存储所有的结果
		resultList.addAll(hList);
		resultList.addAll(nList);
		//O单递归查询下级代理
		while(nList.size() > 0){
			nList = selectAgentLevelService.agentLevel(nList);
			resultList.addAll(nList);
		}
		Tran tran = new Tran();
		List<String> stringlist = new ArrayList<String>();
		List<Transaction> tList=new ArrayList<Transaction>();
		for(Agent t : resultList){
			Agent agent=(Agent)t;
			stringlist.add(agent.getMerId());
		}
		tran.setList(stringlist);
		PageInfo<Transaction> tranList = transactionService.queryPage(tran);

		
		return tranList;
	}
	
	@RequestMapping("getListForNoCard")
	@ResponseBody
	public PageInfo<Transaction> getListForNoCard(String merId, String agentStatus, String appId) throws UnsupportedEncodingException {
		Agent n = null;
		UserEntity k=UserEntityUtil.getUserFromSession();
 		if(k.getMerId().startsWith("T")){
				Transaction transaction=new Transaction();
				if(!StringUtils.isBlank(merId)){
					transaction.setMerId(merId);
				}
				if(!StringUtils.isBlank(agentStatus)){
					transaction.setAgentStatus(agentStatus);
				}
				PageInfo<Transaction> tList=transactionService.selectPageForNoCardList(transaction,appId);
				return tList;
		}
		n=new Agent();
		n.setOneMerId(k.getMerId());
		List<Agent> hList=agentService.queryObjectForList(n);//查询一级代理
		
		//O单查找自己
		Agent single = new Agent();
		single.setMerId(k.getMerId());
		Agent self = agentService.findByObject(single);
				
		List<Agent> nList = selectAgentLevelService.agentLevel(hList);
		List<Agent> resultList = new ArrayList<Agent>();//存储所有的结果
		resultList.add(self);//O单将自己查找出来
		resultList.addAll(hList);
		resultList.addAll(nList);
		//O单递归查询下级代理
		while(nList.size() > 0){
			nList = selectAgentLevelService.agentLevel(nList);
			resultList.addAll(nList);
		}
		Tran tran = new Tran();
		List<String> stringlist = new ArrayList<String>();
		List<Transaction> tList=new ArrayList<Transaction>();
		for(Agent t : resultList){
			Agent agent=(Agent)t;
			stringlist.add(agent.getMerId());
		}
		tran.setList(stringlist);
		if(!StringUtils.isBlank(merId)){
			tran.setMerId(merId);
		}
		if(!StringUtils.isBlank(agentStatus)){
			tran.setAgentStatus(agentStatus);
		}
		PageInfo<Transaction> tranList = transactionService.queryPage(tran);

		
		return tranList;
	}
	public List<Agent> get(List<Agent> list,Agent t){
		if("".equals(t.getOneMerId()) || null == t.getOneMerId()){
			return list;
		}
		if(!"".equals(t.getOneMerId()) && null != t.getOneMerId()){
			Agent agent=new Agent();
			agent.setMerId(t.getOneMerId());
			Agent n=agentService.findByObject(agent);
			list.add(n);
			get(list,n);
		}
		return list;	
	}
	
	@RequestMapping("edits")
	public String edits(Model model, Long id) {
		if(id != null){
			Transaction t = transactionService.findByPrimaryKey(id);
			model.addAttribute("transaction", t);
		}
		return "/system/transaction/edits";
	}
	
	@RequestMapping("alter")
	@ResponseBody
	public String alter(String merId, String floorNumber, String generationFee, String generationFeeRepayment, String fee0, String d0fee, String fee1, String d1fee) {
		Transaction transaction=new Transaction();
		UserEntity k=UserEntityUtil.getUserFromSession();
		transaction.setMerId(k.getMerId());
		Transaction f=transactionService.findByObject(transaction);
		if(f != null){
			double floor=Double.parseDouble(f.getFloorNumber());
			double generation=Double.parseDouble(f.getGenerationFee());
			double generationRepayment=Double.parseDouble(f.getGenerationFeeRepayment());
//			double v=Double.parseDouble(f.getFee0())/10;
//			double t=Double.parseDouble(f.getD0fee())/100;
//			double i =Double.parseDouble(f.getFee1())/10;
//			double tt =Double.parseDouble(f.getD1fee())/100;
			double number=Double.parseDouble(floorNumber);
			double fee=Double.parseDouble(generationFee);
			double repayment=Double.parseDouble(generationFeeRepayment);
//			double fee01=Double.parseDouble(fee0);
//			double d0fee1=Double.parseDouble(d0fee);
//			double fee2=Double.parseDouble(fee1);
//			double d1fee2=Double.parseDouble(d1fee);
//			if(floor <= number && generation <= fee && generationRepayment <= repayment && v <= fee01 && t <= d0fee1 && i <= fee2 && tt <= d1fee2){
//				Transaction n=new Transaction();
//				n.setMerId(merId);
//				Transaction h=transactionService.findByObject(n);
//				h.setFloorNumber(floorNumber);
//				h.setGenerationFee(generationFee);
//				h.setGenerationFeeRepayment(generationFeeRepayment);
//				h.setFee0((fee01 * 10) + "");
//				h.setD0fee((d0fee1 * 100) + "");
//				h.setFee1((fee2 * 10) + "");
//				h.setD1fee((d1fee2 * 100) + "");
//				return transactionService.update(h);
//			}
			if(floor <= number && generation <= fee && generationRepayment <= repayment){
				Transaction n=new Transaction();
				n.setMerId(merId);
				Transaction h=transactionService.findByObject(n);
				h.setFloorNumber(floorNumber);
				h.setGenerationFee(generationFee);
				h.setGenerationFeeRepayment(generationFeeRepayment);
//				h.setFee0((fee01 * 10) + "");
//				h.setD0fee((d0fee1 * 100) + "");
//				h.setFee1((fee2 * 10) + "");
//				h.setD1fee((d1fee2 * 100) + "");
				return transactionService.update(h);
			}
			return "fail";
		}
		return "f";
	}

}
