package com.battcn.controller.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.battcn.annotation.SystemLog;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.battcn.entity.AppName;
import com.battcn.entity.CardBanner;
import com.battcn.entity.Information;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.UseCardStrategyService;
import com.battcn.util.UPLoadOss;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("CardStrategy")
public class UseCardStrategyCtl extends BaseController {

	@Autowired
	private UseCardStrategyService useCardStrategyService;
	
	@Autowired
	private AppNameService appNameService;
	
	@RequestMapping("BannerList")
	@SystemLog(module = "Banner管理", methods = "获取banner列表")
	public String bannerList(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/cardStrategy/bannerList";
	}

	@RequestMapping("getBannerList")
	@ResponseBody
	public PageInfo<CardBanner> bannerList(String shows, HttpServletRequest request){
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(k.getMerId().startsWith("T")){
				map.put("shows", shows);
				map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<CardBanner> page = useCardStrategyService.getBannerList(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			map.put("shows", shows);
			map.put("appId", appNames.getAppId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<CardBanner> page = useCardStrategyService.getBannerList(map);
			return page;
		}
		PageInfo<CardBanner> page = new PageInfo<CardBanner>();
		return page;
	}
	
	@RequestMapping("BannerInsert")
	@SystemLog(module = "banner管理", methods = "新增banner")
	public String insert(Model model) {
		model.addAttribute("app", appNameService.getList());
		return "/system/cardStrategy/insertBanner";
	}
	
	@RequestMapping(value = "insertBanner",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String insertBanner(String appId,
							   String forwardURL,
							   @RequestParam("imgs") MultipartFile fileimgs,
							   HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isBlank(appId)){
			map.put("appId", appId);
		}
		if(!StringUtils.isBlank(forwardURL)){
			map.put("forwardURL", forwardURL);
		}
		if (!fileimgs.isEmpty()) {
			map.put("imgURL", UPLoadOss.getResult(fileimgs, request));
		}
		map.put("creatDate", System.currentTimeMillis()+"");
		map.put("institutionId", InstitutionIdNumber.AGENT);
		String message = useCardStrategyService.InsertBanner(map);
		return message;
	}
	
	@RequestMapping("BannerUpdate")
	@SystemLog(module = "banner管理", methods = "编辑banner")
	public String alert(Model model,String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		model.addAttribute("goods", useCardStrategyService.getBannerById(map));
		return "/system/cardStrategy/updateBanner";
	}
	
	@RequestMapping(value = "editBanner",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String updateBanner(String bannerId,  String forwardURL,
			   @RequestParam("imgs") MultipartFile fileimgs,
			   HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", bannerId);
		if(!StringUtils.isBlank(forwardURL)){
			map.put("forwardURL", forwardURL);
		}
		if (!fileimgs.isEmpty()) {
			map.put("imgURL", UPLoadOss.getResult(fileimgs, request));
		}
		String message = useCardStrategyService.UpdateBanner(map);
		return message;
	}
	
	@RequestMapping(value = "deleteBanner",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String delete(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		String message = useCardStrategyService.DeleteBanner(map);
		return message;
	}
	//文章管理
	@RequestMapping("InformationList")
	@SystemLog(module = "文章管理", methods = "获取文章列表")
	public String InformationList(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/cardStrategy/informationList";
	}

	@RequestMapping("getInformationList")
	@ResponseBody
	public PageInfo<Information> informationList(String shows, HttpServletRequest request){
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(k.getMerId().startsWith("T")){
				map.put("shows", shows);
				map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<Information> page = useCardStrategyService.getInformationList(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			map.put("shows", shows);
			map.put("appId", appNames.getAppId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<Information> page = useCardStrategyService.getInformationList(map);
			return page;
		}
		PageInfo<Information> page = new PageInfo<Information>();
		return page;
	}
	
	@RequestMapping("InformationInsert")
	@SystemLog(module = "文章管理", methods = "新增文章")
	public String insertInformation(Model model) {
		model.addAttribute("app", appNameService.getList());
		return "/system/cardStrategy/insertInformation";
	}
	
	@RequestMapping(value = "insertInformation",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String insertInformation(String appId,
								    String forwardURL,
								    String type,
								    String titles,
								    String content,
									@RequestParam("imgs") MultipartFile fileimgs,
									HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isBlank(appId)){
			map.put("appId", appId);
		}
		if(!StringUtils.isBlank(forwardURL)){
			map.put("forwardURL", forwardURL);
		}
		if (!fileimgs.isEmpty()) {
			map.put("imgURL", UPLoadOss.getResult(fileimgs, request));
		}
		if(!StringUtils.isBlank(type)){
			map.put("type", type);
		}
		if(!StringUtils.isBlank(titles)){
			map.put("titles", titles);
		}
		if(!StringUtils.isBlank(content)){
			map.put("content", content);
		}
		map.put("creatDate", System.currentTimeMillis()+"");
		map.put("institutionId", InstitutionIdNumber.AGENT);
		String message = useCardStrategyService.InsertInformation(map);
		return message;
	}
	
	@RequestMapping("InformationUpdate")
	@SystemLog(module = "文章管理", methods = "编辑文章")
	public String alertInformation(Model model,String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		model.addAttribute("goods", useCardStrategyService.getInformationById(map));
		return "/system/cardStrategy/updateInformation";
	}
	
	@RequestMapping(value = "editInformation",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String updateInformation(String informationId, String forwardURL,
								    String type,
								    String titles,
								    String content,
									@RequestParam("imgs") MultipartFile fileimgs,
									HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", informationId);
		if(!StringUtils.isBlank(forwardURL)){
			map.put("forwardURL", forwardURL);
		}
		if(!StringUtils.isBlank(type)){
			map.put("type", type);
		}
		if(!StringUtils.isBlank(titles)){
			map.put("titles", titles);
		}
		if(!StringUtils.isBlank(content)){
			map.put("content", content);
		}
		if (!fileimgs.isEmpty()) {
			map.put("imgURL", UPLoadOss.getResult(fileimgs, request));
		}
		String message = useCardStrategyService.UpdateInformation(map);
		return message;
	}
	
	@RequestMapping(value = "deleteInformation",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String deleteInformation(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		String message = useCardStrategyService.DeleteInformation(map);
		return message;
	}
}
