package com.battcn.service.system.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Agent;
import com.battcn.entity.AgentRate;
import com.battcn.entity.AgentRateExample;
import com.battcn.entity.MerChants;
import com.battcn.entity.Transaction;
import com.battcn.entity.UserEntity;
import com.battcn.mapper.AgentRateMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.AgentRateService;
import com.battcn.service.system.AgentService;
import com.battcn.service.system.MerChantsService;
import com.battcn.service.system.TransactionService;
import com.battcn.util.CommonUtil;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
@Service
public class AgentRateServiceImpl implements AgentRateService {

	@Autowired
	private AgentRateMapper agentRateMapper;
	@Autowired
	public TransactionService transactionService;
	@Autowired
	private AgentService agentService;
	
	@Override
	public PageInfo<AgentRate> getList() {
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
		AgentRateExample example = new AgentRateExample();
		AgentRateExample.Criteria cri = new AgentRateExample().createCriteria();
		example.or(cri);
		
		
		
		List<AgentRate> list = agentRateMapper.selectByExample(example);
		
		
		return new PageInfo<AgentRate>(list);
	}

//	@Override
//	public Integer update(AgentRate agentRate) {
//		UserEntity k=UserEntityUtil.getUserFromSession();
//		AgentRateExample example = new AgentRateExample();
//		example.or().andAgentidEqualTo(agentRate.getAgentid());
//		List<AgentRate> list = agentRateMapper.selectByExample(example);
//		BigDecimal big = new BigDecimal("100");
//		BigDecimal bige = new BigDecimal(agentRate.getRate().toString());
//		Transaction n=new Transaction();
//		n.setMerId(agentRate.getAgentid());
//		Transaction h=transactionService.findByObject(n);
//		//根据代理商id查询修改人代理商姓名
//		Agent m=new Agent();
//		m.setMerId(agentRate.getAgentid());
//		List<Agent> newm = agentService.queryObjectForList(m);
//		String name = "";
//		if(newm.size()>0){
//			name = newm.get(0).getMerName();
//		}
//		//有数据
//		if(list.size()!=0){
//			for(AgentRate a : list){
//				//网关相同更新费率
//				if(a.getAislecode().equals(agentRate.getAislecode())){
//					a.setRate(bige.divide(big));
//					h.setUpdateDate(new Date());//更新日期
//					h.setUpdateName(k.getUserName());//更新姓名
//					transactionService.update(h);
//					return agentRateMapper.updateByPrimaryKeySelective(a);
//				}
//			}
//			agentRate.setD0fee(200);
//			agentRate.setRate(bige.divide(big));
//			h.setUpdateDate(new Date());
//			h.setUpdateName(k.getUserName());
//			transactionService.update(h);
//			return agentRateMapper.insert(agentRate);
//		}
//		//无数据直接插入
//		agentRate.setD0fee(200);
//		agentRate.setRate(bige.divide(big));
//		h.setUpdateDate(new Date());
//		h.setUpdateName(k.getUserName());
//		transactionService.update(h);
//		return agentRateMapper.insert(agentRate);
//	}
	@Override
	public Integer update(AgentRate agentRate) {
		UserEntity k=UserEntityUtil.getUserFromSession();
		//按逗号分割传进来的数值
		String[] newAgentId = null;
		Integer count = 0;
		try{
		newAgentId = agentRate.getAgentid().split(",");
		}catch(Exception e){

			return 0;
		}
		List<String> alists = Arrays.asList(newAgentId);
		List<String> alist = new ArrayList(alists);
		for(int i = 0; i< alist.size();i++) {
			if(alist.get(i).equals(k.getMerId())) {
				alist.remove(alist.get(i));
			}
		}
		String[] newMerchantId = agentRate.getMerchantid().split(",");
		List<String> mlists = Arrays.asList(newMerchantId);
		List<String> mlist = new ArrayList(mlists);
		Transaction t = new Transaction();
		t.setMerId(k.getMerId());
		t = transactionService.findByObject(t);
		for(int i = 0; i< mlist.size();i++) {
			if(mlist.get(i).equals(t.getMerChantId())) {
				mlist.remove(mlist.get(i));
			}
		}
		BigDecimal big = new BigDecimal("100");
		BigDecimal bige = new BigDecimal(agentRate.getRate().toString());

		BigDecimal low = new BigDecimal("0");
		BigDecimal heigh  = BigDecimal.valueOf(0.01);
		AgentRateExample exCodes = new AgentRateExample();
		exCodes.or().andAislecodeEqualTo(agentRate.getAislecode()).andAgentidEqualTo(k.getMerId());
		List<AgentRate> rl = agentRateMapper.selectByExample(exCodes);
		for(AgentRate r:rl){
			low = r.getRate();
		}
		Transaction h = new Transaction();
		List<AgentRate> list = new ArrayList<AgentRate>();
		
		//遍历传进的数组
		for(int i = 0; i < newAgentId.length; i++ ){
			AgentRateExample example = new AgentRateExample();
			example.or().andAgentidEqualTo(newAgentId[i]);
			list = agentRateMapper.selectByExample(example);
			
			Transaction n=new Transaction();
			n.setMerId(newAgentId[i]);
			h = transactionService.findByObject(n);
			
			//根据代理商id查询修改人代理商姓名
			Agent m=new Agent();
			m.setMerId(newAgentId[i]);
			List<Agent> newm = agentService.queryObjectForList(m);
			String name = "";
			if(newm.size()>0){
				name = newm.get(0).getMerName();
			}
		
			//费率表有数据有数据
			if(list.size() > 0){
				List<AgentRate> listCode = new ArrayList<AgentRate>();
				for(AgentRate a : list){
					//查看该网关是否为空
					AgentRateExample exCode = new AgentRateExample();
					exCode.or().andAislecodeEqualTo(agentRate.getAislecode()).andAgentidEqualTo(newAgentId[i]);
					listCode = agentRateMapper.selectByExample(exCode);
					//网关相同更新费率
						if(a.getAislecode().equals(agentRate.getAislecode())){
							if(bige.divide(big).compareTo(low)==-1)
							{
								count = 8888;
								return  count;
							}
							if(bige.divide(big).compareTo(heigh)==1)
							{
								count = 9999;
								return  count;
							}
							a.setRate(bige.divide(big));
							h.setUpdateDate(new Date());//更新日期
							h.setUpdateName(k.getUserName());//更新姓名
							transactionService.update(h);
							count = agentRateMapper.updateByPrimaryKeySelective(a);
						} else if(!a.getAislecode().equals(agentRate.getAislecode()) && listCode.size()==0) {
							//网关不同且费率表为空插入数据
							AgentRate nAgentRate = new AgentRate();
							nAgentRate.setD0fee(200);
							nAgentRate.setRate(bige.divide(big));
							nAgentRate.setAgentid(newAgentId[i]);
							nAgentRate.setAislecode(agentRate.getAislecode());
							nAgentRate.setMerchantid(newMerchantId[i]);
							h.setUpdateDate(new Date());
							h.setUpdateName(k.getUserName());
							transactionService.update(h);
							count = agentRateMapper.insert(nAgentRate);
						} else if(!a.getAislecode().equals(agentRate.getAislecode()) && listCode.size()==0) {
							//网关不同且费率表为空插入数据
							AgentRate nAgentRate = new AgentRate();
							nAgentRate.setD0fee(200);
							nAgentRate.setRate(bige.divide(big));
							nAgentRate.setAgentid(newAgentId[i]);
							nAgentRate.setAislecode(agentRate.getAislecode());
							nAgentRate.setMerchantid(newMerchantId[i]);
							h.setUpdateDate(new Date());
							h.setUpdateName(k.getUserName());
							transactionService.update(h);
							count = agentRateMapper.insert(nAgentRate);
						}
					}
				
				} else {
						//无数据直接插入
						AgentRate newAgentRate = new AgentRate();
						newAgentRate = new AgentRate();
						newAgentRate.setD0fee(200);
						newAgentRate.setRate(bige.divide(big));
						newAgentRate.setAgentid(newAgentId[i]);
						newAgentRate.setAislecode(agentRate.getAislecode());
						newAgentRate.setMerchantid(newMerchantId[i]);
					h.setUpdateDate(new Date());
					h.setUpdateName(k.getUserName());
					transactionService.update(h);
					count =  agentRateMapper.insert(newAgentRate);
				}
		}
		return count;
		
	}
	@Override
	public PageInfo<AgentRate> queryList(String agentid) {
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
		AgentRateExample example = new AgentRateExample();
		AgentRateExample.Criteria cri = new AgentRateExample().createCriteria();
		cri.andAgentidEqualTo(agentid);
		example.or(cri);
		List<AgentRate> list = agentRateMapper.selectByExample(example);
		return new PageInfo<AgentRate>(list);
	}

	@Override
	public Integer insert(AgentRate agentRate) {
		Integer count = agentRateMapper.insert(agentRate);
		return count;
	}

}
