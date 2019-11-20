package com.battcn.controller.system;

import java.math.BigDecimal;
import java.util.List;

import com.battcn.entity.Transaction;
import com.battcn.service.system.*;
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
	@Autowired
	private TransactionService transactionService;

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

	/**
	 * 刷費率
	 */
	@RequestMapping("saveAgentRate")
	public void saveAgentRate(){
		List<Transaction> list = transactionService.queryObjectForList();
		System.out.println("list.size()"+list.size());
		for (int i = 0; i < list.size(); i++) {
			Transaction t = list.get(i);
			String agentStart = t.getAgentStatus();
			String merId = t.getMerId();
			String merChantId = t.getMerChantId();
			if (merId.equals("T00000009")) {
			} else {
				AgentRate rate = new AgentRate();
				if (agentStart.equals("1")) {//代理
					rate.setRate(new BigDecimal("0.0061"));
					rate.setD0fee(200);
				} else if (agentStart.equals("2")){//运营商
					rate.setRate(new BigDecimal("0.0058"));
					rate.setD0fee(200);
				}
				rate.setMerchantid(merChantId);
				rate.setAgentid(merId);
				rate.setAislecode("ybq");
				agentRateService.insert(rate);
			}
			System.out.println("i========="+i);
		}
	}
}
