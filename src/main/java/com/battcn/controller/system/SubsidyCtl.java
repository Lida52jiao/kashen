package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.entity.Subsidy;
import com.battcn.service.system.SubsidyService;

@Controller
@RequestMapping("Subsidy")
public class SubsidyCtl {
	
	@Autowired
	private SubsidyService subsidyService; 

	@RequestMapping(value="confirm",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String alert(Subsidy subsidy) {
		Integer count = subsidyService.editOrInsertSubsidy(subsidy);
		if(count == 1){
			return "修改成功！！";
		}
		return "修改失败！！！";
	}
}
