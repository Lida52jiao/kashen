package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.AppName;
import com.battcn.entity.MerChantsRate;
import com.battcn.entity.Num;
import com.battcn.entity.NumExample;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.EnjoyService;
import com.battcn.service.system.NumService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Num")
public class NumController extends BaseController {
	
	@Autowired
	private NumService numService; 
	@Autowired
	private EnjoyService enjoyService; 
	@Autowired
	private AppNameService appNameService;
	
	@RequestMapping("numList")
	@SystemLog(module = "商户管理", methods = "商户升级模式")
	public String numList(Model model) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("enjoy", enjoyService.getEnjoy());
		return "/system/num/list";
	}
	@RequestMapping("getNumList")
	@ResponseBody
	public PageInfo<Num> getNumList() {
		UserEntity k=UserEntityUtil.getUserFromSession();
		NumExample example = new NumExample();
		example.or().andViptypeEqualTo("F");
		PageInfo<Num> page = numService.getList(example);
		return page;
	}
	
	@RequestMapping("editNum")
	public String editNum(Model model,Long id) {
		model.addAttribute("num", numService.getNum(id));
		return "/system/num/editNum";
	}
	
	@RequestMapping(value="alert",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String alert(Num num) {
		Integer count = numService.editNum(num);
		if(count == 1){
			return "修改成功！！";
		}
		return "修改失败！！！";
	}
	@RequestMapping("numTList")
	@SystemLog(module = "商户管理", methods = "app使用费")
	public String numAppTList(Model model) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("enjoy", enjoyService.getEnjoy());
		return "/system/num/appUseList";
	}
	@RequestMapping("getNumTList")
	@ResponseBody
	public PageInfo<Num> getNumAppTList() {
		UserEntity k=UserEntityUtil.getUserFromSession();
		NumExample example = new NumExample();
		example.or().andViptypeEqualTo("T");
		PageInfo<Num> page = numService.getList(example);
		return page;
	}
	
	@RequestMapping("editNumT")
	public String editNumApp(Model model,Long id) {
		model.addAttribute("num", numService.getNum(id));
		return "/system/num/appEditNum";
	}
	
	@RequestMapping(value="alertT",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String alertAppT(Num num) {
		Integer count = numService.editNum(num);
		if(count == 1){
			return "修改成功！！";
		}
		return "修改失败！！！";
	}
	
}
