package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.AgentBasePrice;
import com.battcn.service.system.AgentBasePriceService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("AgentBasePrice")
public class AgentBasePriceController extends BaseController {

	@Autowired
	private AgentBasePriceService agentBasePriceService;
	
	@RequestMapping("list")
	@SystemLog(module = "代理商管理", methods = "代理商高等级低价设置")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/agentBasePrice/list";
	}
	
	@RequestMapping("getRewardList")
	@ResponseBody
	public PageInfo<AgentBasePrice> getList() {
		PageInfo<AgentBasePrice> page = agentBasePriceService.getBasePriceList();
		return page;
	}
	
	@RequestMapping("editBasePrice")
	public String edit(Model model, Long id) {
			model.addAttribute("base", agentBasePriceService.getBasePriceById(id));
		return "/system/agentBasePrice/lists";
	}
	
	@RequestMapping("alter")
	@ResponseBody
	public String alter(AgentBasePrice base) {
		int message = agentBasePriceService.updateById(base);
		if(message==1){
			return "success";
		}
		return "error";
	}
}
