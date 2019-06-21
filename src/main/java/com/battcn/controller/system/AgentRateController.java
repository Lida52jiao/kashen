package com.battcn.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.AgentLevel;
import com.battcn.entity.AgentRate;
import com.battcn.entity.AgentRateVo;
import com.battcn.service.system.AgentLevelService;
import com.battcn.service.system.AgentRateService;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.ChangelRateService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/AgentRate/")
public class AgentRateController extends BaseController {
	
	@Autowired
	private AgentRateService agentRateService;
	@Autowired
	private ChangelRateService changelRateService;
	@Autowired
	private AgentLevelService agentLevelService;
	@Autowired
	private AppNameService appNameService;

	@RequestMapping("list")
	@SystemLog(module = "代理商管理", methods = "查询代理商无卡底价设置")
	public String rateList(Model model){
		model.addAttribute("res", findResByUser());
		List<AgentLevel> agentList = agentLevelService.getAgentLevel();
		model.addAttribute("agentLevel", agentList);
		model.addAttribute("app", appNameService.getList());
		return "/system/agentRate/list";
	}
	
	@RequestMapping("getRateList")
	@ResponseBody
	public PageInfo<AgentRate> getList(Model model){
		PageInfo<AgentRate> page = agentRateService.getList();
		return page;
	}
	@RequestMapping("editRate")
	public String edit(Model model,String merId,String merChantId) {
		String[] newMerId = merId.split(",");
			model.addAttribute("key",changelRateService.getaisle(newMerId[0]));
			model.addAttribute("merId",merId);
			model.addAttribute("merChantId",merChantId);
		return "/system/agentRate/lists";
	}
	@RequestMapping("alert")
	@ResponseBody
	public String alert(AgentRate agentRate) {
		Integer message = agentRateService.update(agentRate);
		if(message==8888){
			return "low";
		}
		if(message==9999){
			return "height";
		}
		if(message != 0){
			return "success";
		}
		return "error";
	}
	@RequestMapping("RateList")
	public String querrateList(Model model,String agentid){
		model.addAttribute("agentid", agentid);
		return "/system/agentRate/agentRateList";
	}
	@RequestMapping("queryAlert")
	@ResponseBody
	public PageInfo<AgentRate> queryAlert(String agentid) {
		PageInfo<AgentRate> page = agentRateService.queryList(agentid);
		return page;
	}
}
