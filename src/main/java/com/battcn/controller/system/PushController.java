package com.battcn.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.util.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.controller.BaseController;
import com.battcn.entity.JiGuang;
import com.battcn.entity.News;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.NewsService;
import com.battcn.service.system.PushService;
import com.battcn.util.UserEntityUtil;
import com.battcn.util.YJ;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("Push")
public class PushController extends BaseController {
	
	@Autowired
	private PushService pushService;
	@Autowired
	private NewsService newsService;
	
	@RequestMapping("list")
	public String list(Model model) {
		UserEntity k=UserEntityUtil.getUserFromSession();
		JiGuang jiGuang=pushService.getjiGuang(k.getMerId());
		model.addAttribute("res", findResByUser());
		model.addAttribute("jiGuang", jiGuang);
		return "/system/push/list";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String insertNews(String appId, String merChantId, String msg){
		if(!"".equals(appId) && !"".equals(msg)){
			Map<String,Object> param = new HashMap<>();
			param.put("msg", msg);
//			param.put("createDate", System.currentTimeMillis()+"");
			param.put("type","1");
			param.put("institutionId", InstitutionIdNumber.AGENT);
			param.put("appId",appId);
			String resultJsonStr = HttpClientUtils.doPost("http://47.104.25.59:1183/sendMessage/send", param);
			JSONObject job = JSONObject.parseObject(resultJsonStr);
			System.out.println(job);
			return "success";
		}
		return "f";	
	}
}
