package com.battcn.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.AgentMerLevel;
import com.battcn.service.system.AgentMerLevelService;
import com.battcn.util.UPLoadOss;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("agentMerLevel")
public class AgentMerLevelCtl extends BaseController {
	
	@Autowired
	private AgentMerLevelService agentMerLevelService;

	@RequestMapping("manageLevel")
	@SystemLog(module = "代理商管理", methods = "代理商绑和用户等级管理")
	public String manageLevel(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/agentMerLevel/manageList";
	}
	
	@RequestMapping("manageList")
	@ResponseBody
	public PageInfo<AgentMerLevel> manageList() {
		PageInfo<AgentMerLevel> page = agentMerLevelService.getList();
		return page;
	}
	@RequestMapping(value="editManageLevel",produces="text/html;charset=UTF-8;")
	public String editLevel(Model model,Long id) {
		model.addAttribute("level", agentMerLevelService.getById(id));
		return "/system/agentMerLevel/editManageLevel";
	}
	@RequestMapping(value="editManage",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String editManage(
			Long id,
			String levelname,
			Long levelweight,
			String agentornot,
			String showornot,
			@RequestParam("levellogo") MultipartFile file,
			@RequestParam("levelbcard") MultipartFile fileimgs,
			String funcexplain,
			String usablemodule,
			HttpServletRequest request) {
		String resultLogo = "";
		String resultBack = "";
		//图标
		if (!file.isEmpty()) {
			resultLogo = UPLoadOss.getResult(file, request);
		}
		if (!fileimgs.isEmpty()) {
			resultBack = UPLoadOss.getResult(fileimgs, request);
		}
		AgentMerLevel level = new AgentMerLevel();
		level.setId(id);
		level.setLevelname(levelname);
		level.setLevellogo(resultLogo);
		level.setLevelbcard(resultBack);
		level.setLevelweight(levelweight);
		level.setAgentornot(agentornot);
		level.setShowornot(showornot);
		level.setFuncexplain(funcexplain);
		level.setUsablemodule(usablemodule);
		Integer count = agentMerLevelService.editManage(level);
		if(count == 1){
			return "修改成功！！";
		}
		return "修改失败！！";
	}
	
	@RequestMapping(value="addManageLevel",produces="text/html;charset=UTF-8;")
	public String addLevel(Model model, Long id) {
		
		return "/system/agentMerLevel/addManageLevel";
	}
	@RequestMapping(value="addManage",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String addManage(
			String levelname,
			Long levelweight,
			String agentornot,
			String showornot,
			@RequestParam("levellogo") MultipartFile file,
			@RequestParam("levelbcard") MultipartFile fileimgs,
			String funcexplain,
			String usablemodule,
			HttpServletRequest request) {
		String resultLogo = "";
		String resultBack = "";
		//图标
		if(!file.isEmpty()){
			resultLogo = UPLoadOss.getResult(file, request);
		}
		
		//卡背
		if(!fileimgs.isEmpty()){
			resultBack = UPLoadOss.getResult(fileimgs, request);
		}
		
		AgentMerLevel level = new AgentMerLevel();
		level.setLevelname(levelname);
		level.setLevellogo(resultLogo);
		level.setLevelbcard(resultBack);
		level.setLevelweight(levelweight);
		level.setAgentornot(agentornot);
		if("N".equals(agentornot)){
			level.setAgentstatus(agentornot);
		}
		if(!"N".equals(agentornot)){
			level.setMerlevel("N");
		}
		level.setShowornot(showornot);
		level.setFuncexplain(funcexplain);
		level.setUsablemodule(usablemodule);
		Integer count = agentMerLevelService.addManage(level);//向num表添加一条数据
		if(count == 1){
			return "添加成功！！";
		}
		return "添加失败！！";
	}
}
