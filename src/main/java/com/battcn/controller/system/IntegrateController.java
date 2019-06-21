package com.battcn.controller.system;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
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
import com.battcn.entity.CheckinEntity;
import com.battcn.entity.ConfigEntity;
import com.battcn.entity.IntegrateOrderEntity;
import com.battcn.entity.PointLogEntity;
import com.battcn.entity.ShopEntity;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.IntegrateService;
import com.battcn.util.UPLoadOss;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Integrage")
public class IntegrateController extends BaseController {
	
	@Autowired
	private IntegrateService integrateService;
	
	@Autowired
	private AppNameService appNameService;
	
	@RequestMapping("IntegrateList")
	@SystemLog(module = "积分管理", methods = "商户积分明细查询")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		return "/system/integrate/integrateList";
	}

	@RequestMapping("getIntegrateList")
	@ResponseBody
	public PageInfo<CheckinEntity> getList(String mername, String merchantId, String phone,String appId,HttpServletRequest request) throws UnsupportedEncodingException{
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(k.getMerId().startsWith("T")){
			if(!StringUtils.isBlank(mername)){
				mername = new String(mername.getBytes("iso8859-1"), "utf-8");
				map.put("name", mername);
			}
			if(!StringUtils.isBlank(merchantId)){
				map.put("merchantId", merchantId);
				request.setAttribute("totalintegrate", integrateService.totalIntegrate(merchantId));
			}
			if(!StringUtils.isBlank(phone)){
				map.put("phone", phone);
			}
			if(!StringUtils.isBlank(appId)){
				map.put("appId", appId);
			}
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<CheckinEntity> page = integrateService.getIntegrateList(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			if(!StringUtils.isBlank(mername)){
				mername = new String(mername.getBytes("iso8859-1"), "utf-8");
				map.put("name", mername);
			}
			if(!StringUtils.isBlank(merchantId)){
				map.put("merchantId", merchantId);
				request.setAttribute("totalintegrate", integrateService.totalIntegrate(merchantId));
			}
			if(!StringUtils.isBlank(phone)){
				map.put("phone", phone);
			}
			map.put("appId", appNames.getAppId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<CheckinEntity> page = integrateService.getIntegrateList(map);
			return page;
		}
		PageInfo<CheckinEntity> page = new PageInfo<CheckinEntity>();
		return page;
	}
	
	@RequestMapping("IntegrateGoodsList")
	@SystemLog(module = "积分管理", methods = "积分商城商品管理")
	public String goodslist(Model model) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		return "/system/integrate/integrateGoodsList";
	}
	
	@RequestMapping("getIntegrateGoodsList")
	@ResponseBody
	public PageInfo<ShopEntity> getGoodsList(String goodsId, String isUsed,String goodsName,String appId) throws UnsupportedEncodingException{
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(k.getMerId().startsWith("T")){
			if(!StringUtils.isBlank(goodsName)){
				goodsName = new String(goodsName.getBytes("iso8859-1"), "utf-8");
				map.put("name", goodsName);
			}
			if(!StringUtils.isBlank(goodsId)){
				map.put("id", goodsId);
			}
			if(!StringUtils.isBlank(isUsed)){
				map.put("isUsed", isUsed);
			}
			if(!StringUtils.isBlank(appId)){
				map.put("appId", appId);
			}
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<ShopEntity> page = integrateService.getIntegrateGoodsList(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			if(!StringUtils.isBlank(goodsName)){
				goodsName = new String(goodsName.getBytes("iso8859-1"), "utf-8");
				map.put("name", goodsName);
			}
			if(!StringUtils.isBlank(goodsId)){
				map.put("id", goodsId);
			}
			if(!StringUtils.isBlank(isUsed)){
				map.put("isUsed", isUsed);
			}
			map.put("appId", appNames.getAppId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<ShopEntity> page = integrateService.getIntegrateGoodsList(map);
			return page;
		}
		PageInfo<ShopEntity> page = new PageInfo<ShopEntity>();
		return page;
	}
	
	@RequestMapping("IntegrateGoodById")
	@SystemLog(module = "积分管理", methods = "编辑查看积分商品")
	public String goodsById(Model model,String goodsId) {
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", goodsId);
			PageInfo<ShopEntity> goods = integrateService.getIntegrateGoodById(map);
			model.addAttribute("goods",goods);
		return "/system/integrate/integrateGoodEdit";
	}
		
	@RequestMapping(value = "editIntegrate",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String editAlert(
						String isUsed,
						String goodsId,
						String goodsName,
						String description,
						@RequestParam("imgone") MultipartFile file,
						@RequestParam("imgs") MultipartFile fileimgs,
						@RequestParam("imgs1") MultipartFile fileimgs1,
						@RequestParam("imgs2") MultipartFile fileimgs2,
						@RequestParam("imgs3") MultipartFile fileimgs3,
						@RequestParam("imgs4") MultipartFile fileimgs4,
						@RequestParam("imgs5") MultipartFile fileimgs5,
						@RequestParam("detailImg") MultipartFile filedetailImg,
						@RequestParam("detailImg1") MultipartFile filedetailImg1,
						@RequestParam("detailImg2") MultipartFile filedetailImg2,
						@RequestParam("detailImg3") MultipartFile filedetailImg3,
						@RequestParam("detailImg4") MultipartFile filedetailImg4,
						@RequestParam("detailImg5") MultipartFile filedetailImg5,
						String point,
						HttpServletRequest request
						 ) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		String imgsAll="";
		String imgsAll1="";
		String imgsAll2="";
		String imgsAll3="";
		String imgsAll4="";
		String imgsAll5="";
		String detailImgAll="";
		String detailImgAll1="";
		String detailImgAll2="";
		String detailImgAll3="";
		String detailImgAll4="";
		String detailImgAll5="";
		if(!StringUtils.isBlank(goodsId)){
			map.put("id", goodsId);
		}
		if(!StringUtils.isBlank(goodsName)){
			map.put("name", goodsName);
		}
		if(!StringUtils.isBlank(description)){
			map.put("description", description);
		}
		if(!StringUtils.isBlank(isUsed)){
			map.put("isUsed", isUsed);
		}
		if (!file.isEmpty()) {
			map.put("img", UPLoadOss.getResult(file, request));
		}
		if (!fileimgs.isEmpty()) {
			imgsAll = UPLoadOss.getResult(fileimgs, request);
		}
		if (!fileimgs1.isEmpty()) {
			imgsAll1 = UPLoadOss.getResult(fileimgs1, request);
		}
		if (!fileimgs2.isEmpty()) {
			imgsAll2 = UPLoadOss.getResult(fileimgs2, request);
		}
		if (!fileimgs3.isEmpty()) {
			imgsAll3 = UPLoadOss.getResult(fileimgs3, request);
		}
		if (!fileimgs4.isEmpty()) {
			imgsAll4 = UPLoadOss.getResult(fileimgs4, request);
		}
		if (!fileimgs5.isEmpty()) {
			imgsAll5 = UPLoadOss.getResult(fileimgs5, request);
		}
		if(!fileimgs.isEmpty() || !fileimgs1.isEmpty() || !fileimgs2.isEmpty() ||!fileimgs3.isEmpty() ||
				!fileimgs4.isEmpty() || !fileimgs5.isEmpty()){
			map.put("imgs",imgsAll+","+imgsAll1+","+imgsAll2+","+imgsAll3+","+imgsAll4+","+imgsAll5);
		}
		if (!filedetailImg.isEmpty()) {
			detailImgAll = UPLoadOss.getResult(filedetailImg, request);
		}
		if (!filedetailImg1.isEmpty()) {
			detailImgAll1 = UPLoadOss.getResult(filedetailImg1, request);
		}
		if (!filedetailImg2.isEmpty()) {
			detailImgAll2 = UPLoadOss.getResult(filedetailImg2, request);
		}
		if (!filedetailImg3.isEmpty()) {
			detailImgAll3 = UPLoadOss.getResult(filedetailImg3, request);
		}
		if (!filedetailImg4.isEmpty()) {
			detailImgAll4 = UPLoadOss.getResult(filedetailImg4, request);
		}
		if (!filedetailImg5.isEmpty()) {
			detailImgAll5 = UPLoadOss.getResult(filedetailImg5, request);
		}
		if(!filedetailImg.isEmpty() || !filedetailImg1.isEmpty() || !filedetailImg2.isEmpty() || !filedetailImg3.isEmpty()
				|| !filedetailImg4.isEmpty() || !filedetailImg5.isEmpty()){
			map.put("detailImg",detailImgAll+","+detailImgAll1+","+detailImgAll2+","+detailImgAll3+","+detailImgAll4+","+detailImgAll5);
		}
		if(!StringUtils.isBlank(point)){
			map.put("point", point);
		}
		String message = integrateService.updateIntegrateGoodById(map);
		return message;
	}
	
	@RequestMapping("IntegrateGoodAdd")
	@SystemLog(module = "积分管理", methods = "添加积分商品")
	public String goodsAdd(Model model) {
		model.addAttribute("app", appNameService.getList());
		return "/system/integrate/integrateGoodAdd";
	}
		
	@RequestMapping(value = "add",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String add(
						String goodsName,
						String description,
						@RequestParam("imgone") MultipartFile file,
						@RequestParam("imgs") MultipartFile fileimgs,
						@RequestParam("imgs1") MultipartFile fileimgs1,
						@RequestParam("imgs2") MultipartFile fileimgs2,
						@RequestParam("imgs3") MultipartFile fileimgs3,
						@RequestParam("imgs4") MultipartFile fileimgs4,
						@RequestParam("imgs5") MultipartFile fileimgs5,
						@RequestParam("detailImg") MultipartFile filedetailImg,
						@RequestParam("detailImg1") MultipartFile filedetailImg1,
						@RequestParam("detailImg2") MultipartFile filedetailImg2,
						@RequestParam("detailImg3") MultipartFile filedetailImg3,
						@RequestParam("detailImg4") MultipartFile filedetailImg4,
						@RequestParam("detailImg5") MultipartFile filedetailImg5,
						String point,
						String appId,
						HttpServletRequest request
						 ) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		String imgsAll="";
		String imgsAll1="";
		String imgsAll2="";
		String imgsAll3="";
		String imgsAll4="";
		String imgsAll5="";
		String detailImgAll="";
		String detailImgAll1="";
		String detailImgAll2="";
		String detailImgAll3="";
		String detailImgAll4="";
		String detailImgAll5="";
		if(!StringUtils.isBlank(goodsName)){
			map.put("name", goodsName);
		}
		if(!StringUtils.isBlank(description)){
			map.put("description", description);
		}
		if (!file.isEmpty()) {
			map.put("img", UPLoadOss.getResult(file, request));
		}
		if (!fileimgs.isEmpty()) {
			imgsAll = UPLoadOss.getResult(fileimgs, request);
		}
		if (!fileimgs1.isEmpty()) {
			imgsAll1 = UPLoadOss.getResult(fileimgs1, request);
		}
		if (!fileimgs2.isEmpty()) {
			imgsAll2 = UPLoadOss.getResult(fileimgs2, request);
		}
		if (!fileimgs3.isEmpty()) {
			imgsAll3 = UPLoadOss.getResult(fileimgs3, request);
		}
		if (!fileimgs4.isEmpty()) {
			imgsAll4 = UPLoadOss.getResult(fileimgs4, request);
		}
		if (!fileimgs5.isEmpty()) {
			imgsAll5 = UPLoadOss.getResult(fileimgs5, request);
		}
		if (!filedetailImg.isEmpty()) {
			detailImgAll = UPLoadOss.getResult(filedetailImg, request);
		}
		if (!filedetailImg1.isEmpty()) {
			detailImgAll1 = UPLoadOss.getResult(filedetailImg1, request);
		}
		if (!filedetailImg2.isEmpty()) {
			detailImgAll2 = UPLoadOss.getResult(filedetailImg2, request);
		}
		if (!filedetailImg3.isEmpty()) {
			detailImgAll3 = UPLoadOss.getResult(filedetailImg3, request);
		}
		if (!filedetailImg4.isEmpty()) {
			detailImgAll4 = UPLoadOss.getResult(filedetailImg4, request);
		}
		if (!filedetailImg5.isEmpty()) {
			detailImgAll5 = UPLoadOss.getResult(filedetailImg5, request);
		}
			map.put("imgs", imgsAll+","+imgsAll1+","+imgsAll2+","+imgsAll3+","+imgsAll4+","+imgsAll5);
			map.put("detailImg", detailImgAll+","+detailImgAll1+","+detailImgAll2+","+detailImgAll3+","+detailImgAll4+","+detailImgAll5);
		if(!StringUtils.isBlank(point)){
			map.put("point", point);
		}
		if(!StringUtils.isBlank(appId)){
			map.put("appId", appId);
		}
		map.put("institutionId", InstitutionIdNumber.AGENT);
		map.put("isUsed", "1");
		String message = integrateService.addIntegrateGood(map);
		return message;
	}
	@RequestMapping("IntegrateOrder")
	@SystemLog(module = "积分管理", methods = "订单管理")
	public String goodsOrder(Model model) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		return "/system/integrate/integrateOrderList";
	}
	@RequestMapping("getIntegrateOrderList")
	@ResponseBody
	public PageInfo<IntegrateOrderEntity> getGoodsOrderList(String orderNo, String name,String agentId,
			String merchantId,String phone,String appId) throws UnsupportedEncodingException{
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(k.getMerId().startsWith("T")){
			if(!StringUtils.isBlank(name)){
				name = new String(name.getBytes("iso8859-1"), "utf-8");
				map.put("name", name);
			}
			if(!StringUtils.isBlank(orderNo)){
				map.put("orderNo", orderNo);
			}
			if(!StringUtils.isBlank(agentId)){
				map.put("agentId", agentId);
			}
			if(!StringUtils.isBlank(merchantId)){
				map.put("merchantId", merchantId);
			}
			if(!StringUtils.isBlank(phone)){
				map.put("phone", phone);
			}
			if(!StringUtils.isBlank(appId)){
				map.put("appId", appId);
			}

			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<IntegrateOrderEntity> page = integrateService.getIntegrateOrderList(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			if(!StringUtils.isBlank(name)){
				name = new String(name.getBytes("iso8859-1"), "utf-8");
				map.put("name", name);
			}
			if(!StringUtils.isBlank(orderNo)){
				map.put("orderNo", orderNo);
			}
			if(!StringUtils.isBlank(agentId)){
				map.put("agentId", agentId);
			}
			if(!StringUtils.isBlank(merchantId)){
				map.put("merchantId", merchantId);
			}
			if(!StringUtils.isBlank(phone)){
				map.put("phone", phone);
			}
			map.put("appId", appNames.getAppId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<IntegrateOrderEntity> page = integrateService.getIntegrateOrderList(map);
			return page;
		}
		PageInfo<IntegrateOrderEntity> page = new PageInfo<IntegrateOrderEntity>();
		return page;
	}
	@RequestMapping("IntegrateGoodByNo")
	@SystemLog(module = "积分管理", methods = "编辑订单")
	public String goodsOrderByNo(Model model,String orderNo) {
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
			map.put("orderNo", orderNo);
			PageInfo<IntegrateOrderEntity> goods = integrateService.getIntegrateOrderList(map);
			model.addAttribute("goods",goods);
		return "/system/integrate/integrateOrderEdit";
	}
	@RequestMapping(value="editIntegrateOrder",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String OrderEdit(String orderNo, String shopName,String state) throws UnsupportedEncodingException{
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
			if(!StringUtils.isBlank(shopName)){
				map.put("shopName", shopName);
			}
			if(!StringUtils.isBlank(orderNo)){
				map.put("orderNo", orderNo);
			}
			if(!StringUtils.isBlank(state)){
				map.put("state", state);
			}
			String data = integrateService.updateIntegrateOrderByNo(map);
			return data;
	}
	@RequestMapping("IntegrateDetail")
	@SystemLog(module = "积分管理", methods = "积分明细")
	public String IntegrateDetail(Model model) {
		model.addAttribute("app", appNameService.getList());
		return "/system/integrate/integrateDetailList";
	}
	@RequestMapping("IntegrateDetailList")
	@ResponseBody
	public PageInfo<PointLogEntity> IntegrateDetailList(String orderNo, String name,String appId,
			String agentId,String merchantId,String phone) throws UnsupportedEncodingException{
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(k.getMerId().startsWith("T")){
			if(!StringUtils.isBlank(orderNo)){
				map.put("orderNo", orderNo);
			}
			if(!StringUtils.isBlank(name)){
				map.put("name", name);
			}
			if(!StringUtils.isBlank(agentId)){
				map.put("agentId", agentId);
			}
			if(!StringUtils.isBlank(merchantId)){
				map.put("merchantId", merchantId);
			}
			if(!StringUtils.isBlank(phone)){
				map.put("phone", phone);
			}
			if(!StringUtils.isBlank(appId)){
				map.put("appId", appId);
			}
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<PointLogEntity> page = integrateService.getIntegrateDetail(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			if(!StringUtils.isBlank(orderNo)){
				map.put("orderNo", orderNo);
			}
			if(!StringUtils.isBlank(name)){
				map.put("name", name);
			}
			if(!StringUtils.isBlank(agentId)){
				map.put("agentId", agentId);
			}
			if(!StringUtils.isBlank(merchantId)){
				map.put("merchantId", merchantId);
			}
			if(!StringUtils.isBlank(phone)){
				map.put("phone", phone);
			}
			map.put("appId", appNames.getAppId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<PointLogEntity> page = integrateService.getIntegrateDetail(map);
			return page;
		}
		PageInfo<PointLogEntity> page = new PageInfo<PointLogEntity>();
		return page;
	}
	@RequestMapping("IntegrateRule")
	@SystemLog(module = "积分管理", methods = "积分规则列表")
	public String IntegrateRule(Model model) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		return "/system/integrate/integrateRuleList";
	}
	
	@RequestMapping("IntegrateRuleList")
	@ResponseBody
	public PageInfo<ConfigEntity> IntegrateRuleList() throws UnsupportedEncodingException{
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(k.getMerId().startsWith("T")){
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<ConfigEntity> page = integrateService.getIntegrateRule(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			map.put("appId", appNames.getAppId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<ConfigEntity> page = integrateService.getIntegrateRule(map);
			return page;
		}
		PageInfo<ConfigEntity> page = new PageInfo<ConfigEntity>();
		return page;
	}
	@RequestMapping("IntegrateRuleEdit")
	@SystemLog(module = "积分管理", methods = "积分规则设定")
	public String IntegrateRuleEdit(Model model,String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		PageInfo<ConfigEntity> goods = integrateService.getIntegrateRule(map);
		model.addAttribute("goods",goods);
		return "/system/integrate/integrateRuleEdit";
	}
	@RequestMapping("IntegrateRuleSet")
	@ResponseBody
	public String IntegrateRuleSet(String id,String pay, String realName,String checkin,String remarks) throws UnsupportedEncodingException{
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isBlank(id)){
			map.put("id", id);
		}
		if(!StringUtils.isBlank(pay)){
			map.put("pay", pay);
		}
		if(!StringUtils.isBlank(realName)){
			map.put("realName", realName);
		}
		if(!StringUtils.isBlank(checkin)){
			map.put("checkin", checkin);
		}
		if(!StringUtils.isBlank(remarks)){
			map.put("remarks", remarks);
		}
		String data = integrateService.setIntegrateRule(map);
		return data;
	}
}
