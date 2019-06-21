package com.battcn.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.AgentBasePrice;
import com.battcn.entity.AgentBasePriceExample;
import com.battcn.mapper.AgentBasePriceMapper;
import com.battcn.service.system.AgentBasePriceService;
import com.github.pagehelper.PageInfo;

@Service
public class AgentBasePriceServiceImpl implements AgentBasePriceService{

	@Autowired
	private AgentBasePriceMapper agentBasePriceMapper;

	@Override
	public PageInfo<AgentBasePrice> getBasePriceList() {
		AgentBasePriceExample example = new AgentBasePriceExample();
		List<AgentBasePrice> list = agentBasePriceMapper.selectByExample(example);
		return new PageInfo<AgentBasePrice>(list);
	}

	@Override
	public AgentBasePrice getBasePriceById(Long id) {
		return agentBasePriceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(AgentBasePrice base) {
		AgentBasePrice newbase = agentBasePriceMapper.selectByPrimaryKey(base.getId());
		newbase.setRepayfee(base.getRepayfee()*10);
		newbase.setNocardintegralfee(base.getNocardintegralfee()*10);
		newbase.setNocardintegraldfee(base.getNocardintegraldfee()*100);
		newbase.setHcardintegralfee(base.getHcardintegralfee()*10);
		newbase.setHcardintegraldfee(base.getHcardintegraldfee()*100);
		return agentBasePriceMapper.updateByPrimaryKey(newbase);
	}

	@Override
	public List<AgentBasePrice> getAgentLevel() {
		AgentBasePriceExample example = new AgentBasePriceExample();
		List<AgentBasePrice> list = agentBasePriceMapper.selectByExample(example);
		return list;
	}

	@Override
	public AgentBasePrice getByAgentLevel(String agentlevel) {
		AgentBasePriceExample example = new AgentBasePriceExample();
		example.or().andAgentlevelEqualTo(agentlevel);
		AgentBasePrice agent = agentBasePriceMapper.selectByExample(example).get(0);
		return agent;
	}

}
