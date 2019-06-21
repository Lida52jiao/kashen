package com.battcn.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Agent;
import com.battcn.service.system.AgentService;
import com.battcn.service.system.SelectAgentLevelService;
@Service
public class SelectAgentLevelServiceImpl implements SelectAgentLevelService{

	
		@Autowired
		private AgentService agentService;
		//利用递归查询代理商底下无限级人员
		@Override
		public List<Agent> agentLevel(List<Agent> hList){
			List<Agent> newList = new ArrayList<Agent>();
			List<Agent> aList= new ArrayList<Agent>();
			for(Agent a:hList){
				Agent newn=new Agent();
				newn.setOneMerId(a.getMerId());
				aList=agentService.queryObjectForList(newn);//查询下级代理
				newList.addAll(aList);
			}
			return newList;
		}

}
