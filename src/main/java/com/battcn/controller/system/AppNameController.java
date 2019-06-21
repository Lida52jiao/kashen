package com.battcn.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.controller.BaseController;
import com.battcn.entity.AppName;
import com.battcn.service.system.AppNameService;

@Controller
@RequestMapping("AppName")
public class AppNameController extends BaseController {
	
	@Autowired
	private AppNameService appNameService;
	
	@RequestMapping("appList")
	public List<AppName> getAppNameList(){
		
		return appNameService.getList();
	}

}
