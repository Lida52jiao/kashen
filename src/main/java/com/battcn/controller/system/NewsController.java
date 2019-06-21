package com.battcn.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.constant.Constaint;
import com.battcn.entity.*;
import com.battcn.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.controller.BaseController;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.NewsService;
import com.battcn.service.system.PushService;
import com.battcn.util.CommonUtil;
import com.battcn.util.UserEntityUtil;
import com.battcn.util.YJ;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

@Controller
@RequestMapping("News")
public class NewsController extends BaseController {
	
	@Autowired
	private NewsService newsService;
	@Autowired
	private PushService pushService;
	@Autowired
	private AppNameService appNameService;
	
	@RequestMapping("add")
	@ResponseBody
	public String add(String name, String msg){
		if(!"".equals(name) && !"".equals(msg) ){
			Push h=new Push();
			h.setName(name);
			Push n=pushService.findByObject(h);
			YJ.pushAll("", msg, n.getSecret(), n.getAppkey());
			News news=new News("System", msg, System.currentTimeMillis()+"");
			return newsService.save(news);
		}
		return "f";	
	}
	
	
	@RequestMapping("list")
	public String list(HttpServletRequest request) {
		return "/system/news/list";
	}
	
	
	@RequestMapping("adda")
	@ResponseBody
	public String aaa(String merChantId, String msg){
		if(!"".equals(merChantId) && !"".equals(msg) ){
			YJ.pushAll(merChantId, msg, "5ce0a55158c6d8c86635e053", "3e0acc0997e27a2bf5fab968");
			News news=new News("System", msg, System.currentTimeMillis()+"");
			return newsService.save(news);
		}
		return "f";	
		
	}
	@RequestMapping("get")
	@ResponseBody
	public PageInfo<Message> selectNews(){
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String,Object> map = new HashMap<>();
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		PageHelper.startPage(pageNum, pageSize);
		String orderField = request.getParameter("sort");
		String orderDirection = request.getParameter("order");
		if (StringUtil.isNotEmpty(orderField)) {
			PageHelper.orderBy(orderField);
			if (StringUtil.isNotEmpty(orderDirection)) {
				PageHelper.orderBy(orderField + " " + orderDirection);
			}
		}
		map.put("institutionId", Constaint.AGENT);
		map.put("type", "1");
		if(k.getMerId().startsWith("T")){
//			List<News> list = newsService.queryObjectForList(news);
			String result = HttpClientUtil.doPost(Constaint.BIND,map);
			JSONObject t = JSON.parseObject(result);
			String data = t.getString("data");
			JSONObject d = JSON.parseObject(data);
			List<Message> list = JSON.parseArray(d.getString("list"),Message.class);
			return new PageInfo<Message>(list);
		}
		if(null != appNames){
//			news.setMerChantId(appNames.getAppId());
			map.put("appId",appNames.getAppId());
		}
		String result = HttpClientUtil.doPost(Constaint.BIND,map);
		JSONObject t = JSON.parseObject(result);
		String data = t.getString("data");
		JSONObject d = JSON.parseObject(data);
		List<Message> list = JSON.parseArray(d.getString("list"),Message.class);
		return new PageInfo<Message>(list);
	}
	@RequestMapping("delete")
	@ResponseBody
	public String deleteNews(String id ){
		String[] newIds = id.toString().split(",");
		String count = "";
		for(String newId: newIds){
			count = newsService.delete(Long.parseLong(newId));
		}
		return count;
	}
}
