package com.battcn.controller.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.battcn.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.CircleOfCardService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("Circle")
public class CircleOfCardCtl extends BaseController {

	@Autowired
	private CircleOfCardService circleOfCardService;

	@Autowired
	private AppNameService appNameService;

	@RequestMapping("NeedPass")
	@SystemLog(module = "卡友圈", methods = "待审核内容")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/circleOfCard/needPassList";
	}

	@RequestMapping("NeedPassList")
	@ResponseBody
	public PageInfo<Circle> needPassList(String shows, HttpServletRequest request) {
		UserEntity k = UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if (k.getMerId().startsWith("T")) {
			map.put("shows", shows);
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<Circle> page = circleOfCardService.getCircleList(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if (null != appNames) {
			map.put("shows", shows);
			map.put("appId", appNames.getAppId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<Circle> page = circleOfCardService.getCircleList(map);
			return page;
		}
		PageInfo<Circle> page = new PageInfo<Circle>();
		return page;
	}

	@RequestMapping("PassList")
	@SystemLog(module = "卡友圈", methods = "已审核内容")
	public String passList(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/circleOfCard/passList";
	}

	@RequestMapping("getPassList")
	@ResponseBody
	public PageInfo<Circle> passList(String shows, HttpServletRequest request) {
		UserEntity k = UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if (k.getMerId().startsWith("T")) {
			map.put("shows", shows);
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<Circle> page = circleOfCardService.getCircleList(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if (null != appNames) {
			map.put("shows", shows);
			map.put("appId", appNames.getAppId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<Circle> page = circleOfCardService.getCircleList(map);
			return page;
		}
		PageInfo<Circle> page = new PageInfo<Circle>();
		return page;
	}

	@RequestMapping("CircleAlert")
	@SystemLog(module = "卡友圈", methods = "编辑审核页面")
	public String alert(Model model, String id) {
		model.addAttribute("id", id);
		return "/system/circleOfCard/updateCircle";
	}

	@RequestMapping(value = "editCircle", produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String updateCircle(String circleId, String shows) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", circleId);
		map.put("shows", shows);
		String message = circleOfCardService.UpdateCircle(map);
		return message;
	}

	@RequestMapping(value = "deleteCircle", produces = "text/html;charset=UTF-8;")
	@ResponseBody
	public String delete(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		String message = circleOfCardService.DeleteCircle(map);
		return message;
	}


	//Material----Material-----Material
	@RequestMapping("scNeedPass")
	@SystemLog(module = "卡友圈", methods = "待审核内容")
	public String sclist(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/circleOfCard/scneedPassList";
	}

	@RequestMapping("scneedPassList")
	@ResponseBody
	public PageInfo<Material> scneedPassList(String shows, HttpServletRequest request) {
		UserEntity k = UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if (k.getMerId().startsWith("T")) {
			map.put("shows", "F");
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<Material> page = circleOfCardService.getMaterialList(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if (null != appNames) {
			map.put("shows", shows);
			map.put("appId", appNames.getAppId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<Material> page = circleOfCardService.getMaterialList(map);
			return page;
		}
		PageInfo<Material> page = new PageInfo<Material>();
		return page;
	}

	@RequestMapping("scPassList")
	@SystemLog(module = "卡友圈", methods = "已审核内容")
	public String scpassList(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/circleOfCard/scPassList";
	}

	@RequestMapping("scgetPassList")
	@ResponseBody
	public PageInfo<Material> sccpassList(String shows, HttpServletRequest request) {
		UserEntity k = UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if (k.getMerId().startsWith("T")) {
			map.put("shows", "T");
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<Material> page = circleOfCardService.getMaterialList(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O
		if(null != appNames){
			map.put("shows", shows);
			map.put("appId", appNames.getAppId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<Material> page = circleOfCardService.getMaterialList(map);
			return page;
		}
		PageInfo<Material> page = new PageInfo<Material>();
		return page;
	}


	@RequestMapping("MaterialAlert")
	@SystemLog(module = "卡友圈", methods = "素材编辑审核页面")
	public String scalert(Model model,String id) {
		model.addAttribute("id", id);
		return "/system/circleOfCard/scupdateMaterial";
	}

	@RequestMapping(value = "editMaterial",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String updateMaterial(String id, String shows) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("shows", shows);
		String message = circleOfCardService.UpdateMaterial(map);
		return message;
	}

	@RequestMapping(value = "deleteMaterial",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String scdelete(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		String message = circleOfCardService.DeleteMaterial(map);
		return message;
	}
}
