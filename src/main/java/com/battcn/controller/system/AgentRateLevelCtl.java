package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.controller.BaseController;
import com.battcn.entity.AppName;
import com.battcn.entity.KSAgentRateTable;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.KSAgentRateTableService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/agentTableRate/")
public class AgentRateLevelCtl extends BaseController{

	@Autowired
	private AppNameService appNameService;
	
	@Autowired
	private KSAgentRateTableService ksAgentRateTableSAervice;
	
	@RequestMapping("List")
	public String agentModel(Model model){
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		model.addAttribute("aislecode", ksAgentRateTableSAervice.getList());
		return "/system/AgentAisleRate/list";
	}
	@RequestMapping("getList")
	@ResponseBody
	public PageInfo<KSAgentRateTable> getList(String appId, String aislecode ){
		UserEntity k=UserEntityUtil.getUserFromSession();
		//总平台查询所有
		if(k.getMerId().startsWith("T")){
		PageInfo<KSAgentRateTable> page = ksAgentRateTableSAervice.getList(appId,aislecode);
		return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			PageInfo<KSAgentRateTable> page = ksAgentRateTableSAervice.getList(appNames.getAppId(),aislecode);
			return page;
		}
		PageInfo<KSAgentRateTable> page = new PageInfo<KSAgentRateTable>();
		return page;
	}
	@RequestMapping("alert")
	public String agentAlertModel(Model model,Long id){
		KSAgentRateTable merChantsRate = ksAgentRateTableSAervice.getById(id);
		model.addAttribute("MerchantsRate", merChantsRate);
		return "/system/AgentAisleRate/editAgentAisleRate";
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String UpdateagentRate(KSAgentRateTable merChantsRate){
		Integer message =  ksAgentRateTableSAervice.updateRate(merChantsRate);
		if(message == 1 ){
			return "success";
		}
		return "error";
	}
}
