package com.battcn.controller.system;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.constant.InstitutionIdNumber;
import com.battcn.entity.AppName;
import com.battcn.entity.RepayWithdrawEntity;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.RepayRecordService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/Repay/")
public class RepayRecordController {
	
	@Autowired
	private RepayRecordService repayRecordService;
	
	@Autowired
	private AppNameService appNameService;
	
	@RequestMapping("getRecord")
	public String getRecord(Model model){
		return "/system/repayRecord/list";
	}
	
	@RequestMapping("RecordList")
	@ResponseBody
	public PageInfo<RepayWithdrawEntity> getList(String merchantName ,
												 String merchantPhone ,
												 String merchantId ,
												 String state ,
												 String orderNo ,
												 String startTime ,
												 String finishTime) throws ParseException, UnsupportedEncodingException{
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		merchantName = new String(merchantName.getBytes("iso8859-1"), "utf-8");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long start = 0;
		if(!"".equals(startTime) && null != startTime){
			 start=formatter.parse(startTime).getTime();//毫秒
		}
		long end=0;
		if(!"".equals(finishTime) && null != finishTime){
			end=formatter.parse(finishTime).getTime();//毫秒 
		}
		if(k.getMerId().startsWith("T")){
			map.put("merchantName",merchantName);
			map.put("merchantPhone",merchantPhone);
			map.put("merchantId",merchantId);
			map.put("state",state);
			map.put("orderNo",orderNo);
			map.put("institutionId",InstitutionIdNumber.AGENT);
			if(start!=0){
				map.put("startTime", String.valueOf(start));
			}
			if(end!=0){
				map.put("finishTime", String.valueOf(end));
			}
			PageInfo<RepayWithdrawEntity> page = repayRecordService.getList(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			map.put("merchantName",merchantName);
			map.put("merchantPhone",merchantPhone);
			map.put("merchantId",merchantId);
			map.put("state",state);
			map.put("orderNo",orderNo);
			map.put("appId",appNames.getAppId());
			map.put("institutionId",InstitutionIdNumber.AGENT);
			if(start!=0){
				map.put("startTime", String.valueOf(start));
			}
			if(end!=0){
				map.put("finishTime", String.valueOf(end));
			}
			PageInfo<RepayWithdrawEntity> page = repayRecordService.getList(map);
			return page;
		}
		map.put("merchantName",merchantName);
		map.put("merchantPhone",merchantPhone);
		map.put("merchantId",merchantId);
		map.put("state",state);
		map.put("orderNo",orderNo);
		map.put("agentId",k.getMerId());
		map.put("institutionId",InstitutionIdNumber.AGENT);
		if(start!=0){
			map.put("startTime", String.valueOf(start));
		}
		if(end!=0){
			map.put("finishTime", String.valueOf(end));
		}
		PageInfo<RepayWithdrawEntity> page = repayRecordService.getList(map);
		return page;
	}

}
