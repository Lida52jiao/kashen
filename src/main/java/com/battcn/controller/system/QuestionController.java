package com.battcn.controller.system;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.battcn.entity.AppName;
import com.battcn.entity.Interlocution;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.QuestionService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Question")
public class QuestionController extends BaseController {
	
	@Autowired
	private QuestionService questionService;
	@Autowired
	private AppNameService appNameService;
	
	@RequestMapping("edit")
	public String add(Model model) {
		model.addAttribute("t", questionService.queryObjectForList());
		model.addAttribute("s", appNameService.getList());
		return "/system/question/add";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String edits(String title, String appId, String question, String answer, String answers){
		UserEntity k=UserEntityUtil.getUserFromSession();
		if(!StringUtils.isBlank(title) && !StringUtils.isBlank(appId) && !StringUtils.isBlank(question) && !StringUtils.isBlank(answer)){
			return questionService.save(title,appId,question,answer,answers,k.getMerId());
		}
		return "f";	
	}
	
	@RequestMapping("list")
	public String lists(Model model) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("s", appNameService.getList());
		return "/system/question/list";
	}
	
	@RequestMapping("select")
	@ResponseBody
	public PageInfo<Interlocution> select(String appId){
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String,Object> map=new HashMap<>();
		//总平台查询所有
		if(k.getMerId().startsWith("T")){
			map.put("institutionId", InstitutionIdNumber.AGENT);
			if(!StringUtils.isBlank(appId)){
				map.put("appId", appId);
			}
			PageInfo<Interlocution> list = questionService.query(map);
			return list;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			map.put("appId", appNames.getAppId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<Interlocution> page = questionService.query(map);
			return page;
		}
		PageInfo<Interlocution> page = new PageInfo<Interlocution>();
		return page;
	}
	
	@RequestMapping("alter")
	public String alter(Model model, String id) {
		Map<String,Object> map=new HashMap<>();
		map.put("id", id);
		Interlocution interlocution = questionService.recieve(map);
		model.addAttribute("s", interlocution);
		return "/system/question/alter";
	}
	
	@RequestMapping("alters")
	@ResponseBody
	public String alters(String ids, String questions, String answers, String forwardURLs) {
		Map<String,Object> map=new HashMap<>();
		map.put("id", ids);
		map.put("question", questions);
		map.put("answer", answers);
		map.put("forwardURL", forwardURLs);
		return questionService.alter(map);
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(String id) {
		Map<String,Object> map=new HashMap<>();
		map.put("id", id);
		return questionService.deletes(map);
	}

}
