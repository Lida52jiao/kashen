package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.controller.BaseController;
import com.battcn.entity.AppName;
import com.battcn.entity.MerChantsRate;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.MerchantsRateService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/merchantsRate/")
public class MerchantsRateController extends BaseController {
	
	@Autowired
	private MerchantsRateService merchantsRateService;
	@Autowired
	private AppNameService appNameService;

	@RequestMapping("List")
	public String merchantModel(Model model){
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		model.addAttribute("aislecode", merchantsRateService.getList());
		return "/system/MerchantsRate/list";
	}
	@RequestMapping("getList")
	@ResponseBody
	public PageInfo<MerChantsRate> getList(String appId, String aislecode,String isrepayment){
		UserEntity k=UserEntityUtil.getUserFromSession();
		//总平台查询所有
		if(k.getMerId().startsWith("T")){
			PageInfo<MerChantsRate> page = merchantsRateService.getList(appId,aislecode,isrepayment);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			PageInfo<MerChantsRate> page = merchantsRateService.getList(appNames.getAppId(),aislecode,isrepayment);
			return page;
		}
		PageInfo<MerChantsRate> page = new PageInfo<MerChantsRate>();
		return page;
	}
	@RequestMapping("alert")
	public String merchantAlertModel(Model model,Long id){
		MerChantsRate merChantsRate = merchantsRateService.getById(id);
		model.addAttribute("MerchantsRate", merChantsRate);
		return "/system/MerchantsRate/editmerChantsRate";
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String UpdateMerchantRate(MerChantsRate merChantsRate){
		Integer message =  merchantsRateService.updateRate(merChantsRate);
		if(message == 1 ){
			return "success";
		}
		return "error";
	}
	
}
