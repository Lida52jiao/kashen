package com.battcn.service.system.impl;

import javax.servlet.http.HttpServletRequest;

import com.battcn.entity.MerChants;
import com.battcn.entity.Transaction;
import com.battcn.service.system.MerChantsService;
import com.battcn.service.system.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Agent;
import com.battcn.mapper.AgentMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.AgentService;
import com.battcn.util.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgentServiceImpl extends BaseServiceImpl<Agent> implements
		AgentService {

	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private TransactionService transactionService;
	@Autowired
    private MerChantsService merChantsService;
	@Override
	public Agent find(Agent n) {
		
		return agentMapper.get(n);
	}

	@Override
	public PageInfo<Agent> query(Agent n) {
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
		return new PageInfo<Agent>(agentMapper.query(n));
	}

	public PageInfo<Agent> list(Agent a) {
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
		return new PageInfo<Agent>(agentMapper.list(a));
	}

	@Override
	public List<Agent> getUpAgentListByMerId(String merchantId) {
		List<Agent> agentList=new ArrayList<>();

		Transaction transaction=new Transaction();
		transaction.setMerChantId(merchantId);
		transaction=transactionService.findByObject(transaction);
		if (transaction==null){
			MerChants mer=new MerChants();
			mer.setMerChantId(merchantId);
			mer=merChantsService.findByObject(mer);
			Agent agent=new Agent();
			agent.setMerId(mer.getAgentId());
			agent=agentMapper.selectOne(agent);
			agentList.add(agent);
			agentList=get(agentList,agent);
		}else {
			Agent agent=new Agent();
			agent.setMerId(transaction.getMerId());
			agent=agentMapper.selectOne(agent);
			agentList.add(agent);
			agentList=get(agentList,agent);
		}
		return agentList;
	}
	public List<Agent> get(List<Agent> list, Agent t){

		if(t == null || "".equals(t.getOneMerId()) || null == t.getOneMerId()){
			return list;
		}
		if(!"".equals(t.getOneMerId()) && null != t.getOneMerId()){
			Agent agent=new Agent();
			agent.setMerId(t.getOneMerId());
			Agent n=agentMapper.selectOne(agent);
			if(n!=null) {
				list.add(n);
			}
			get(list,n);
		}
		return list;
	}
}

