package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.entity.EnjoyOrNot;
import com.battcn.service.system.EnjoyService;

@Controller
@RequestMapping("Enjoy")
public class EnjoyOrNotCtl {
	
	@Autowired
	private EnjoyService enjoyService; 

	@RequestMapping(value="confirm",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String alert(String  enjoy) {
		EnjoyOrNot orNot = new EnjoyOrNot();
		orNot.setMinornot(enjoy);
		Integer count = enjoyService.editEnjoy(orNot);
		if(count == 1){
			return "修改成功！！";
		}
		return "修改失败！！！";
	}
}
