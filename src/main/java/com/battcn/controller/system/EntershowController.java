package com.battcn.controller.system;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.AgentEnter;
import com.battcn.service.system.AgentEnterService;
import com.battcn.service.system.MerChantsService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("enterShow")
public class EntershowController extends BaseController 
{
	@Autowired
	private AgentEnterService agentEnterService;
	@Autowired
	private MerChantsService merChantsService;
	
	@RequestMapping("enter")
	@SystemLog(module = "商户管理", methods = "查询商户的信息")
	public String getList(Model model)
	{
		model.addAttribute("res", findResByUser());
		return "/system/merChants/enterlist";
	}
	
	@RequestMapping("queryList")
	@ResponseBody
	public PageInfo<AgentEnter> query(String merName,String merChantId,String merMp,String agentId) throws UnsupportedEncodingException
	{	
		
		AgentEnter agent = new AgentEnter();
		
		merName = new String(merName.getBytes("iso8859-1"), "utf-8");
		if(!StringUtils.isBlank(merName))
		{
			agent.setMerName(merName);
		}
		if(!StringUtils.isBlank(merChantId))
		{
			agent.setMerChantId(merChantId);
		}
		if(!StringUtils.isBlank(merMp))
		{
			agent.setMerMp(merMp);
		}
		if(!StringUtils.isBlank(agentId))
		{
			agent.setAgentId(agentId);
		}
		PageInfo<AgentEnter> newPage = agentEnterService.queryPageForList(agent);
		return newPage;
	}
	
	@RequestMapping("addenter")
	@SystemLog(module = "查询商户的信息", methods = "添加")
	public String insertInformation(Model model) {
		
		return "/system/merChants/addlist";
	}
	@RequestMapping("add")
	@ResponseBody
	public String add(String merName,
					String merChantId,
					String merMp,
					String agentId)
	{
		AgentEnter agent = new AgentEnter();
		agent.setMerName(merName);
		agent.setMerChantId(merChantId);
		agent.setMerMp(merMp);
		agent.setAgentId(agentId);
		String result = agentEnterService.save(agent);
		return result;
	}
	
	@RequestMapping("editenter")
	@SystemLog(module = "查询商户的信息", methods = "编辑")
	public String alertInformation(Model model,Integer id) {
		AgentEnter agent = agentEnterService.findByPrimaryKey(id);
		model.addAttribute("goods", agent);
		return "/system/merChants/editlist";
	}
	@RequestMapping("edit")
	@ResponseBody
	public String edit(Integer id,
			        String merName,
					String merChantId,
					String merMp,
					String agentId) throws UnsupportedEncodingException
	{
		AgentEnter agent = new AgentEnter();
		agent.setId(id);
		if(!StringUtils.isBlank(merName))
		{
		agent.setMerName(merName);
		}
		if(!StringUtils.isBlank(merChantId))
		{
		agent.setMerChantId(merChantId);
		}
		if(!StringUtils.isBlank(merMp))
		{
		agent.setMerMp(merMp);
		}
		if(!StringUtils.isBlank(agentId))
		{
		agent.setAgentId(agentId);
		}
		String result = agentEnterService.update(agent);
		return result;
	}
	@RequestMapping(value = "delenter",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String deleteInformation(Integer id) {
		String result = agentEnterService.delete(id);
		return result;
	}


	}

