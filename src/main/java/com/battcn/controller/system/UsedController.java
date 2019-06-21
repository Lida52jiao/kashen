package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.Used;
import com.battcn.service.system.UsedService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Used")
public class UsedController extends BaseController {
	
	@Autowired
	private UsedService usedService;
	
	@RequestMapping("list")
	@SystemLog(module = "系统设置", methods = "查询年月费")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/used/list";
	}
	
	@RequestMapping(value = "getUsed")
	@ResponseBody
	public PageInfo<Used> get() {
		PageInfo<Used> n=usedService.queryPageForList();
		return n;
	}
	
	@RequestMapping("editUIalter")
	public String editUIalter(Model model, Long id) {
		if (id != null) {
			model.addAttribute("used", usedService.findByPrimaryKey(id));
		}
		return "/system/used/editUIalter";
	}
	
	@RequestMapping("alter")
	@ResponseBody
	public String alter(String month, String year) {
		Used used=new Used();
		used.setId((long)1);
		Used h=usedService.findByObject(used);
		h.setMonth(month);
		h.setYear(year);
		return usedService.update(h);
	}
	
}
