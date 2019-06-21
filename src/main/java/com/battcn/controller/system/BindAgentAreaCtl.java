package com.battcn.controller.system;

import java.util.ArrayList;
import java.util.List;

import com.battcn.service.system.AgentBindService;
import com.battcn.service.system.BindAreaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.Agent;
import com.battcn.entity.AgentBindArea;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AgentAreaService;
import com.battcn.service.system.AgentService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("bindAgentArea")
public class BindAgentAreaCtl extends BaseController {
	
	@Autowired
	private AgentAreaService agentAreaService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private BindAreaService bindAreaService;
	@Autowired
	private AgentBindService agentBindService;

	@RequestMapping("bindArea")
	@SystemLog(module = "代理商管理", methods = "代理商绑定区域")
	public String bindArea(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/agentArea/bindAgentArea";
	}
	
	@RequestMapping("getBindArea")
	@SystemLog(module = "代理商管理", methods = "代理商绑定区域查询列表")
	public String getBindArea(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/agentArea/bindAgentAreaList";
	}
	
	@RequestMapping("getBindAreaList")
	@ResponseBody
	public PageInfo<AgentBindArea> getList(String agentId, String merchantId, String areaName){
		UserEntity k=UserEntityUtil.getUserFromSession();
		AgentBindArea agentBindArea = new AgentBindArea();
		if(!StringUtils.isBlank(agentId))
		{
			agentBindArea.setAgentId(agentId);
		}
		if(!StringUtils.isBlank(merchantId))
		{
			agentBindArea.setMerChantId(merchantId);
		}
		if(!StringUtils.isBlank(areaName))
		{
			agentBindArea.setAreaName(areaName);
		}
		PageInfo<AgentBindArea> page = bindAreaService.queryPageForList(agentBindArea);
		PageInfo<AgentBindArea> newPage = new PageInfo<AgentBindArea>();
		List<AgentBindArea> list = page.getList();
		List<AgentBindArea> newList = new ArrayList<AgentBindArea>();
		//循环遍历匹配姓名
		for(AgentBindArea agent: list){
			Agent n = new Agent();
			n.setMerId(agent.getAgentId());
			Agent h=agentService.find(n);
			if(h!=null){
				if(agent.getAgentId().equals(h.getMerId())){
					agent.setMerName(h.getMerName());
				}
				newList.add(agent);
			}
		}
		newPage.setList(newList);
		newPage.setPages(page.getPages());
		newPage.setTotal(page.getTotal());
		newPage.setPageSize(page.getPageSize());
		return newPage;
	}
	@RequestMapping(value = "deleteBindArea",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String delete(Long id) {
		String message = agentAreaService.DeleteBindArea(id);
		return message;
	}

	//绑区域关系
	@RequestMapping(value="confirm",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String confirmBind(String merId, String merChantId, String province,String city,String region) {
		String message = agentBindService.bindArea(merId, merChantId, province, city, region);
		return message;
	}
}
