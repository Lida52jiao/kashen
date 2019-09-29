package com.battcn.controller.system;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.battcn.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.battcn.service.system.AgentService;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.IsLdRateService;
import com.battcn.service.system.PlanDetailService;
import com.battcn.service.system.PlanService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Plan")
public class PlanController extends BaseController {
	
	@Autowired
	private PlanService planService;
	@Autowired
	private PlanDetailService planDetailService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private AppNameService appNameService;
	@Autowired
	private IsLdRateService isLdRateService;
	
	@RequestMapping("list")
	@SystemLog(module = "还款计划", methods = "计划查询")
	public String list(Model model) {
		model.addAttribute("aisleCode",isLdRateService.getaisleGroup());
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		return "/system/plan/list";
	}

	@RequestMapping("findPlanByAll")
	@ResponseBody
	public PageInfo<PlanEntity> findPlanByAll(String pId,String merchantId,String phone,String cardNo,
											  String state,String starttime,String finishtime,String appId,String agentId,
											  String isLd,String aisleCode,String groupId) throws UnsupportedEncodingException, ParseException{
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long startSeconds = 0;
		long finishSeconds = 0;
		if(!"".endsWith(starttime)){
			startSeconds = sdf.parse(starttime).getTime();//毫秒
		}
		if(!"".endsWith(finishtime)){
			finishSeconds = sdf.parse(finishtime).getTime();//毫秒
		}
		//总平台查询所有
		if(k.getMerId().startsWith("T")){
			if(!StringUtils.isBlank(pId)){
				map.put("pId", pId);
			}
			if(!StringUtils.isBlank(aisleCode)){
				map.put("aisleCode", aisleCode);
			}
			if(!StringUtils.isBlank(isLd)){
				map.put("isLd", isLd);
			}
			if(!StringUtils.isBlank(merchantId)){
				map.put("merchantId", merchantId);
			}
			if(!StringUtils.isBlank(phone)){
				map.put("phone", phone);
			}
			if(!StringUtils.isBlank(cardNo)){
				map.put("cardNo", cardNo);
			}
			if(!StringUtils.isBlank(state)){
				map.put("state", state);
			}
			if(!StringUtils.isBlank(appId)){
				map.put("appId", appId);
			}
			if(!StringUtils.isBlank(agentId)){
				map.put("agentId", agentId);
			}
			if(!StringUtils.isBlank(groupId)){
				map.put("groupId", groupId);
			}
			if(startSeconds !=0){
				map.put("startTime", String.valueOf(startSeconds));
			}
			if(finishSeconds != 0){
				map.put("finishTime", String.valueOf(finishSeconds));
			}
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<PlanEntity> page = planService.selectPlan(map);
			for(PlanEntity p : page.getList()){
				p.setBasicAmount(p.getTotalAmount()-p.getAlreadyAmount());
			}
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			if(!StringUtils.isBlank(pId)){
				map.put("pId", pId);
			}
			if(!StringUtils.isBlank(aisleCode)){
				map.put("aisleCode", aisleCode);
			}
			if(!StringUtils.isBlank(isLd)){
				map.put("isLd", isLd);
			}
			if(!StringUtils.isBlank(merchantId)){
				map.put("merchantId", merchantId);
			}
			if(!StringUtils.isBlank(phone)){
				map.put("phone", phone);
			}
			if(!StringUtils.isBlank(cardNo)){
				map.put("cardNo", cardNo);
			}
			if(!StringUtils.isBlank(state)){
				map.put("state", state);
			}
			if(!StringUtils.isBlank(agentId)){
				map.put("agentId", agentId);
			}
			if(!StringUtils.isBlank(groupId)){
				map.put("groupId", groupId);
			}
			map.put("appId", appNames.getAppId());
			if(startSeconds !=0){
				map.put("startTime", String.valueOf(startSeconds));
			}
			if(finishSeconds != 0){
				map.put("finishTime", String.valueOf(finishSeconds));
			}
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<PlanEntity> page = planService.selectPlan(map);
			for(PlanEntity p : page.getList()){
				p.setBasicAmount(p.getTotalAmount()-p.getAlreadyAmount());
			}
			return page;
		}
		//代理商自己查自己的
		if(!StringUtils.isBlank(pId)){
			map.put("pId", pId);
		}
		if(!StringUtils.isBlank(aisleCode)){
			map.put("aisleCode", aisleCode);
		}
		if(!StringUtils.isBlank(isLd)){
			map.put("isLd", isLd);
		}
		if(!StringUtils.isBlank(merchantId)){
			map.put("merchantId", merchantId);
		}
		if(!StringUtils.isBlank(phone)){
			map.put("phone", phone);
		}
		if(!StringUtils.isBlank(cardNo)){
			map.put("cardNo", cardNo);
		}
		if(!StringUtils.isBlank(state)){
			map.put("state", state);
		}
		map.put("agentId", k.getMerId());
		if(!StringUtils.isBlank(agentId)){
			map.put("agentId", agentId);
		}
		if(!StringUtils.isBlank(groupId)){
			map.put("groupId", groupId);
		}
		if(startSeconds !=0){
			map.put("startTime", String.valueOf(startSeconds));
		}
		if(finishSeconds != 0){
			map.put("finishTime", String.valueOf(finishSeconds));
		}
		map.put("institutionId", InstitutionIdNumber.AGENT);
		PageInfo<PlanEntity> hList=planService.selectPlan(map);
		PageInfo<PlanEntity> newPage = new PageInfo<PlanEntity>();
		List<PlanEntity> tlist=hList.getList();
		List<PlanEntity> nlist =new ArrayList<>();
		for(PlanEntity a:tlist) {
//			String mp=a.getPhone();
//			mp = mp.substring(0,3) + "****"+ mp.substring(7,mp.length());
			String cno=a.getCardNo();
			if(!"".equals(cno)&&cno!=null) {
				cno ="******" + cno.substring(13, cno.length());
				a.setCardNo(cno);
			}
//			a.setPhone(mp);
			nlist.add(a);
		}
		newPage.setList(nlist);
		newPage.setPages(hList.getPages());
		newPage.setTotal(hList.getTotal());
		newPage.setPageSize(hList.getPageSize());
		return newPage;
	}
	
	@RequestMapping("lists")
	@SystemLog(module = "还款计划", methods = "计划详情")
	public String lists(Model model,String planId) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("planId", planId);
		return "/system/plan/lists";
	}
	
	/**
     * 查询计划详情
     * @return
     */
    @RequestMapping("findPlanDetail")
    @ResponseBody
    public PageInfo<PlanDetailEntity> getList(String pId) throws UnsupportedEncodingException, ParseException {
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
			//总平台查询所有
			if(k.getMerId().startsWith("T")){
				map.put("planId", pId);
				map.put("institutionId", InstitutionIdNumber.AGENT);
			 PageInfo<PlanDetailEntity> page = planDetailService.selectPlanDetail(map);
			 return page;
    		}
			AppName appName = new AppName();
			appName.setAgentId(k.getMerId());
			AppName appNames = appNameService.findByObject(appName);
			//O单
			if(null != appNames){
				map.put("planId", pId);
				map.put("appId", appNames.getAppId());
				map.put("institutionId", InstitutionIdNumber.AGENT);
				return planDetailService.selectPlanDetail(map);
			}
			//代理商自己查自己的
			map.put("planId", pId);
			map.put("agentId", k.getMerId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			return planDetailService.selectPlanDetail(map);
	}
    
    @RequestMapping("anewRepayment")
	//@SystemLog(module = "还款计划", methods = "代付补单")
	public String anewRepayment(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/plan/repayment";
	}
    
    @RequestMapping("findPlanDetails")
    @ResponseBody
    public PageInfo<PlanDetailEntity> findPlanDetails(String merchantId){
    	PlanDetailEntity planDetail = new PlanDetailEntity();
    	if(!"".equals(merchantId)){
    		planDetail.setMerchantId(merchantId);
    	}
    	return planDetailService.get(planDetail);
    }
    
    @RequestMapping(value = "add", produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String anewRepayment(String id) {
    	PlanDetailEntity planDetail = planDetailService.findByPrimaryKey(id);
    	//System.out.println(planService.get(planDetail.getMerchantId(), planDetail.getOrderNo()));
    	return planService.get(planDetail.getMerchantId(), planDetail.getOrderNo());
	}
	@RequestMapping("interruptPlanList")
	@SystemLog(module = "还款计划", methods = "组合计划中断查询")
	public String interruptPlanList(Model model) {
		model.addAttribute("aisleCode",isLdRateService.getaisleGroup());
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		return "/system/plan/interruptPlanList";
	}

	@RequestMapping("queryInterruptPlanList")
	@ResponseBody
	public PageInfo<PlanGroup> queryInterruptPlanList(String groupId,
													  String merchantId,
													  String executeState,
													  String clearState,
													  String appId,
													  String startTime,
													  String finishTime){
		Map<String, Object> map = new HashMap<String, Object>();
		UserEntity k=UserEntityUtil.getUserFromSession();
		map.put("institutionId", k.getMerId());
		if(!StringUtils.isBlank(groupId)){
			map.put("groupId", groupId);
		}
		if(!StringUtils.isBlank(merchantId)){
			map.put("merchantId", merchantId);
		}
		if(!StringUtils.isBlank(executeState)){
			map.put("executeState", executeState);
		}
		if(!StringUtils.isBlank(clearState)){
			map.put("clearState", clearState);
		}
		if(!StringUtils.isBlank(appId)){
			map.put("appId", appId);
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!"".equals(startTime)){
				long startSeconds = sdf.parse(startTime).getTime();
				map.put("startTime", startSeconds);
			}
			if(!"".equals(finishTime)){
				long finishSeconds = sdf.parse(finishTime).getTime();
				map.put("finishTime", finishSeconds);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		PageInfo<PlanGroup> page = planService.queryInterruptPlanList(map);
		return page;
	}
	@RequestMapping("/clearPlan")
	@ResponseBody
	public String clearPlan(String groupId){
		return planService.clearPlan(groupId);
	}

}
