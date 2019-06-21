package com.battcn.controller.system;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.battcn.annotation.SystemLog;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.battcn.entity.KSMerUsedArea;
import com.battcn.entity.MerChants;
import com.battcn.service.system.MerChantsService;
import com.battcn.service.system.MerchantsUsedAreaService;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
@Controller
@RequestMapping("MerChantsUsedArea")
public class MerChantsUsedAreaCtl extends BaseController{
	
	@Autowired
	private MerchantsUsedAreaService merchantsUsedAreaService;
	@Autowired
	private MerChantsService merChantsService;
	
	@RequestMapping("usedAreaHtml")
	@SystemLog(module = "用户管理", methods = "查询用户常用地区所在地信息")
	public String getUsedAreaList(Model model) {
		return "/system/merChants/UsedAreaList";
	}
	
	@RequestMapping("UsedAreaList")
	@ResponseBody
	public PageInfo<KSMerUsedArea> getUsedAreaList(String usedMerChantId,String usedMerMp) {
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
		KSMerUsedArea usedArea = new KSMerUsedArea();
		if(!StringUtils.isBlank(usedMerChantId)){
			usedArea.setMerChantId(usedMerChantId);
		}
		if(!StringUtils.isBlank(usedMerMp)){
			usedArea.setMerMp(usedMerMp);
		}
		PageInfo<KSMerUsedArea> page = merchantsUsedAreaService.queryPageForList(usedArea);
		return page;	
	}
	
	@RequestMapping("usedArea")
	@SystemLog(module = "用户管理", methods = "查询用户常用地区所在地信息")
	public String getUsedArea(Model model,String merChantId) {
		KSMerUsedArea usedArea = new KSMerUsedArea();
		usedArea.setMerChantId(merChantId);
		KSMerUsedArea entity = merchantsUsedAreaService.findByObject(usedArea);
		if(entity != null){
			model.addAttribute("usedArea", entity);
		}
		if(entity == null){
			KSMerUsedArea entityy  = new KSMerUsedArea();
			entityy.setMerChantId(merChantId);
			model.addAttribute("usedArea", entityy);
		}
		return "/system/merChants/editUsedArea";
	}
	
	@RequestMapping("updateUsedArea")
	@ResponseBody
	public String updateUsedArea(String merChantId,String province,String city,String region) {
		KSMerUsedArea usedArea = new KSMerUsedArea();
		usedArea.setMerChantId(merChantId);
		KSMerUsedArea entity = merchantsUsedAreaService.findByObject(usedArea);
		JSONObject proviceJson = getAreaMessage(province);
		JSONObject cityJson = getAreaMessage(city);
		JSONObject regionJson = getAreaMessage(region);
		//不为空更新
		if(entity!=null){
			entity.setProviceAreaId(proviceJson.getString("code"));
			entity.setProviceAreaName(proviceJson.getString("areaName"));
			entity.setCityAreaId(cityJson.getString("code"));
			entity.setCityAreaName(cityJson.getString("areaName"));
			entity.setCountryAreaId(regionJson.getString("code"));
			entity.setCountryAreaName(regionJson.getString("areaName"));
			String message = merchantsUsedAreaService.update(entity);
			return message;	
		}
		//为空根据商户信息进行插入
		MerChants n = new MerChants();
		n.setMerChantId(merChantId);
		MerChants h=merChantsService.findByObject(n);
		KSMerUsedArea entityy = new KSMerUsedArea();
		entityy.setMerChantId(merChantId);
		entityy.setMerName(h.getMerName());
		entityy.setMerMp(h.getMerMp());
		entityy.setCertNo(h.getCertNo());
		entityy.setInstitutionId(InstitutionIdNumber.AGENT);
		entityy.setAppId(h.getAppId());
		entityy.setCreatedTime(new Date());
		entityy.setProviceAreaId(proviceJson.getString("code"));
		entityy.setProviceAreaName(proviceJson.getString("areaName"));
		entityy.setCityAreaId(cityJson.getString("code"));
		entityy.setCityAreaName(cityJson.getString("areaName"));
		entityy.setCountryAreaId(regionJson.getString("code"));
		entityy.setCountryAreaName(regionJson.getString("areaName"));
		String message = merchantsUsedAreaService.save(entityy);
		return message;	
		
	}
	//通过省市县id查询省市县详细信息
	public JSONObject getAreaMessage(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		String url = "http://47.104.198.186:1178/community/getCountryById";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject json = JSONObject.parseObject(result);
		String newResult = json.getString("data");
		JSONObject newJson = JSONObject.parseObject(newResult);
		return newJson;
	}
	
}
