package com.battcn.controller.system;

import java.util.List;

import com.battcn.entity.Rate;
import com.battcn.service.system.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.AgentLevel;
import com.battcn.entity.Rates;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/Rates/")
public class RatesController extends BaseController{

	@Autowired
	private RatesService ratesService;
	@Autowired
	private IsLdRateService isLdRateService;
	@Autowired
	private AgentLevelService agentLevelService;
	@Autowired
	private AppNameService appNameService;
	@Autowired
	private RatesdelService ratesdelService;
	
	@RequestMapping("list")
	@SystemLog(module = "代理商管理", methods = "查询代理商无卡底价设置")
	public String rateList(Model model){
		model.addAttribute("res", findResByUser());
		List<AgentLevel> agentList = agentLevelService.getAgentLevel();
		model.addAttribute("agentLevel", agentList);
		model.addAttribute("app", appNameService.getList());
		return "/system/rates/list";
	}
	
	@RequestMapping("getRateList")
	@ResponseBody
	public PageInfo<Rates> getList(Model model){
		PageInfo<Rates> page = ratesService.getList();
		return page;
	}
	@RequestMapping("editRate")
	public String edit(Model model,String merId,String merChantId) {
		String[] newMerId = merChantId.split(",");
			model.addAttribute("key",isLdRateService.getaisle(newMerId[0]));
			model.addAttribute("merId", merId);
			model.addAttribute("merChantId",merChantId);
		return "/system/rates/lists";
	}
	@RequestMapping("alert")
	@ResponseBody
	public String alert(Rates Rate) {
		Integer message = ratesService.update(Rate);
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
		return "/system/rates/ratesList";
	}
	@RequestMapping("queryAlert")
	@ResponseBody
	public PageInfo<Rates> queryAlert(String agentid) {
		PageInfo<Rates> page = ratesService.queryList(agentid);
		return page;
	}


	//手动刷rates
	@RequestMapping("qwer")
	@ResponseBody
	public List<Rate> qwer(String agentid) {
		Rate r = new Rate();
		r.setAislecode("ld07");
		List<Rate> page = ratesdelService.queryObjectForList(r);
		for(Rate a : page){
			Rate s = new Rate();
			s.setAislecode("ld12");
			s.setAgentid(a.getAgentid());
			s.setD0fee(a.getD0fee());
			s.setMerchantid(a.getMerchantid());
			s.setRate(a.getRate());
			ratesdelService.save(s);
		}
		return page;
	}
}
