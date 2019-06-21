package com.battcn.mapper;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.battcn.entity.Agent;

public interface AgentMapper extends Mapper<Agent> {

	Agent get(Agent n);

	List<Agent> query(Agent n);

	List<Agent> list(Agent n);
}
