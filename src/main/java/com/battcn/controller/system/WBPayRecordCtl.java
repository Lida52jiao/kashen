package com.battcn.controller.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.battcn.entity.AppName;
import com.battcn.entity.PayRecord;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.WBPayRecordService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("/WB/")
public class WBPayRecordCtl extends BaseController{

	@Autowired
	private WBPayRecordService wBPayRecordService;
	@Autowired
	private AppNameService appNameService;
	
	@RequestMapping("payRecord")
	@SystemLog(module = "交易及结算管理", methods = "查询外币交易记录")
	public String getShtml(Model model){
		model.addAttribute("res", findResByUser());
		return "/system/wbPayRecord/list";
	}
	@RequestMapping("getList")
	@ResponseBody
	public PageInfo<PayRecord> getList(String orderid,
			String status, String agentid,String institutionid ,String appid,
			String mermp, String mername,String merchantid){
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		//总平台
		if(k.getMerId().startsWith("T")){
			map.put("orderid", orderid);
			map.put("status", status);
			map.put("agentid", agentid);
			map.put("mermp", mermp);
			map.put("mername", mername);
			map.put("merchantid", merchantid);
			map.put("institutionid", InstitutionIdNumber.AGENT);
			PageInfo<PayRecord> page = wBPayRecordService.getList(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			map.put("orderid", orderid);
			map.put("status", status);
			map.put("agentid", agentid);
			map.put("mermp", mermp);
			map.put("mername", mername);
			map.put("merchantid", merchantid);
			map.put("institutionid", InstitutionIdNumber.AGENT);
			map.put("appid", appNames.getAppId());
			PageInfo<PayRecord> page = wBPayRecordService.getList(map);
			return page;
		}
		return new PageInfo<PayRecord>();
	}
}
