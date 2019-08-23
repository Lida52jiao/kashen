package com.battcn.controller.system;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.battcn.entity.*;
import com.battcn.service.system.*;
import com.battcn.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.battcn.annotation.SystemLog;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Trade")
public class TradeController extends BaseController {
	
	@Autowired
	private TradeService tradeService;
	@Autowired
	public TransactionService transactionService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private PlanDetailService planDetailService;
	@Autowired
	private AppNameService appNameService;
	@Autowired
	private ChangelRateService changelRateService;
	@Autowired
	private IsLdRateService isLdRateService;
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private CardinformationService cardinformationService;

	@RequestMapping("list")
	@SystemLog(module = "交易及结算管理", methods = "充值及还款查询")
	public String list(Model model) {
		model.addAttribute("aisleCode",isLdRateService.getaisleGroup());
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		return "/system/trade/list";
	}
	
	@RequestMapping("lists")
	@SystemLog(module = "分润管理", methods = "分润查询")
	public String lists(Model model) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		return "/system/trade/lists";
	}
	@RequestMapping("getTransactionalEntityList")
	@ResponseBody
	public PageInfo<Transactional> list(String orderNo,
										String orderMerchantId,
										String merchantId,
										String module,
										String starttime,
										String appId) throws UnsupportedEncodingException, ParseException {
		UserEntity k=UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		Transaction tt = new Transaction();
		tt.setMerId(k.getMerId());
		tt = transactionService.findByObject(tt);
		//总平台查询所有
		if(k.getMerId().startsWith("T")){
			if(!StringUtils.isBlank(orderNo)){
				map.put("orderNo", orderNo);
			}
			if(!StringUtils.isBlank(orderMerchantId)){
				map.put("orderMerchantId", orderMerchantId);
			}
			if(!StringUtils.isBlank(merchantId)){
				map.put("merchantId", merchantId);
			}
			if(!StringUtils.isBlank(module)){
				map.put("module", module);
			}
			if(!StringUtils.isBlank(starttime)){
				map.put("createDate", starttime);
			}
			map.put("appId", appId);
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<Transactional> page = planDetailService.recieve(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			map.put("merchantId",tt.getMerChantId());
			if(!StringUtils.isBlank(orderNo)){
				map.put("orderNo", orderNo);
			}
			if(!StringUtils.isBlank(orderMerchantId)){
				map.put("orderMerchantId", orderMerchantId);
			}
			if(!StringUtils.isBlank(merchantId)){
				map.put("merchantId",merchantId );
			}
			if(!StringUtils.isBlank(module)){
				map.put("module", module);
			}
			if(!StringUtils.isBlank(starttime)){
				map.put("createDate", starttime);
			}
			map.put("appId", appNames.getAppId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<Transactional> page = planDetailService.recieve(map);
			for (Transactional tl: page.getList()) {
				if (tl.getLevel().equals("institution")) {
					tl.setFee((long) 0);
					tl.setRate("--");
				}
			}
			return page;
		}
		//受益人查自己
		if(!StringUtils.isBlank(orderNo)){
			map.put("orderNo", orderNo);
		}
		if(!StringUtils.isBlank(orderMerchantId)){
			map.put("orderMerchantId", orderMerchantId);
		}
		Transaction t = new Transaction();
		t.setMerId(k.getMerId());
		t = transactionService.findByObject(t);
		if(!StringUtils.isBlank(t.getMerChantId())){
			map.put("merchantId",t.getMerChantId() );
		}
		if(!StringUtils.isBlank(module)){
			map.put("module", module);
		}
		if(!StringUtils.isBlank(starttime)){
			map.put("createDate", starttime);
		}
		map.put("institutionId", InstitutionIdNumber.AGENT);
		PageInfo<Transactional> page = planDetailService.recieve(map);
		return page;

	}
	
	@RequestMapping("getList")
	@ResponseBody
	public PageInfo<PlanDetailEntity> getList(String orderNo,
											  String merchantId,
											  String name,
											  String phone,
											  String agentId,
											  String state,
											  String payState,
											  String repaymentState,
											  String executestartTime,
											  String appId,
											  String planId,
											  String isLd,
											  String aisleCode,
											  String payType,
											  String cycleId,
											  String groupId) throws UnsupportedEncodingException, ParseException {
		UserEntity k=UserEntityUtil.getUserFromSession();
		name = new String(name.getBytes("iso8859-1"), "utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat fmt= new SimpleDateFormat("yyyy-MM-dd");
		if(!StringUtils.isBlank(cycleId)){
			map.put("cycleId", cycleId);
		}
		//总平台查询所有
			if(k.getMerId().startsWith("T")){
				if(!StringUtils.isBlank(orderNo)){
					map.put("orderNo", orderNo);
				}
				if(!StringUtils.isBlank(aisleCode)){
					map.put("aisleCode", aisleCode);
				}
				if(!StringUtils.isBlank(isLd)){
					map.put("isLd", isLd);
				}
				if(!StringUtils.isBlank(payType)){
					map.put("payType", payType);
				}
				if(!StringUtils.isBlank(planId)){
					map.put("planId", planId);
				}
				if(!StringUtils.isBlank(merchantId)){
					map.put("merchantId", merchantId);
				}
				if(!StringUtils.isBlank(phone)){
					map.put("phone", phone);			
				}
				if(!StringUtils.isBlank(name)){
					map.put("name", name);
				}
				if(!StringUtils.isBlank(state)){
					map.put("state", state);
				}
				if(!StringUtils.isBlank(payState)){
					map.put("payState", payState);
				}
				if(!StringUtils.isBlank(repaymentState)){
					map.put("repaymentState", repaymentState);
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
				if(!StringUtils.isBlank(executestartTime)){
					map.put("executeDateStr", executestartTime);
				}
				map.put("institutionId", InstitutionIdNumber.AGENT);
			 PageInfo<PlanDetailEntity> page = planDetailService.selectPlanDetail(map);
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
				if(!StringUtils.isBlank(aisleCode)){
					map.put("aisleCode", aisleCode);
				}
				if(!StringUtils.isBlank(isLd)){
					map.put("isLd", isLd);
				}
				if(!StringUtils.isBlank(payType)){
					map.put("payType", payType);
				}
				if(!StringUtils.isBlank(planId)){
					map.put("planId", planId);
				}
				if(!StringUtils.isBlank(merchantId)){
					map.put("merchantId", merchantId);
				}
				if(!StringUtils.isBlank(phone)){
					map.put("phone", phone);			
				}
				if(!StringUtils.isBlank(name)){
					map.put("name", name);
				}
				if(!StringUtils.isBlank(state)){
					map.put("state", state);
				}
				if(!StringUtils.isBlank(payState)){
					map.put("payState", payState);
				}
				if(!StringUtils.isBlank(repaymentState)){
					map.put("repaymentState", repaymentState);
				}
				if(!StringUtils.isBlank(agentId)){
					map.put("agentId", agentId);
				}
				if(!StringUtils.isBlank(groupId)){
					map.put("groupId", groupId);
				}
				if(!StringUtils.isBlank(executestartTime)){
					map.put("executeDateStr", executestartTime);
				}
				map.put("appId", appNames.getAppId());
				map.put("institutionId", InstitutionIdNumber.AGENT);
				return planDetailService.selectPlanDetail(map);
			}
			//代理商自己查自己的
			if(!StringUtils.isBlank(orderNo)){
				map.put("orderNo", orderNo);
			}
			if(!StringUtils.isBlank(aisleCode)){
				map.put("aisleCode", aisleCode);
			}
			if(!StringUtils.isBlank(isLd)){
				map.put("isLd", isLd);
			}
			if(!StringUtils.isBlank(payType)){
				map.put("payType", payType);
			}
			if(!StringUtils.isBlank(planId)){
				map.put("planId", planId);
			}
			if(!StringUtils.isBlank(merchantId)){
				map.put("merchantId", merchantId);
			}
			if(!StringUtils.isBlank(phone)){
				map.put("phone", phone);			
			}
			if(!StringUtils.isBlank(name)){
				map.put("name", name);
			}
			if(!StringUtils.isBlank(state)){
				map.put("state", state);
			}
			if(!StringUtils.isBlank(payState)){
				map.put("payState", payState);
			}
			if(!StringUtils.isBlank(groupId)){
				map.put("groupId", groupId);
			}
			if(!StringUtils.isBlank(repaymentState)){
				map.put("repaymentState", repaymentState);
			}
			if(!StringUtils.isBlank(executestartTime)){
				map.put("executeDateStr", executestartTime);
			}
			map.put("agentId", k.getMerId());
			map.put("institutionId", InstitutionIdNumber.AGENT);
			return planDetailService.selectPlanDetail(map);
	}

	@RequestMapping("trade")
	@SystemLog(module = "交易及结算管理", methods = "交易明细查询")
	public String trade(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/trade/trade";
	}
	
	@RequestMapping("trades")
	@SystemLog(module = "无卡交易查询", methods = "无卡交易查询")
	public String trades(Model model) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		model.addAttribute("key",changelRateService.getaisle());
		return "/system/trade/tradeList";
	}
	@RequestMapping("selectAgentOrders")
	@ResponseBody
	public PageInfo<OrderEntity> select(String merChantId, String merName,
			String agentOrderNo, String starttime, String finishtime, String state,String appId,String aisleCode) throws UnsupportedEncodingException, ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long start = 0;
		if(!"".equals(starttime) && null != starttime){
			 start=formatter.parse(starttime).getTime();//毫秒
		}
		long end=0;
		if(!"".equals(finishtime) && null != finishtime){
			end=formatter.parse(finishtime).getTime();//毫秒 
		}
		//AgentOrders agentOrders = new AgentOrders();
		merName = new String(merName.getBytes("iso8859-1"), "utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		UserEntity k=UserEntityUtil.getUserFromSession();
		if(k.getMerId().startsWith("T")){
			map.put("merchantId", merChantId);
			map.put("name", merName);
			map.put("orderNo", agentOrderNo);
			if(start!=0){
				map.put("startTime", String.valueOf(start));
			}
			if(end!=0){
				map.put("finishTime", String.valueOf(end));
			}
			map.put("state", state);
			map.put("institutionId", k.getMerId());
			map.put("appId", appId);
			map.put("aisleCode", aisleCode);
			return tradeService.gain(map);
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			map.put("merchantId", merChantId);
			map.put("name", merName);
			map.put("orderNo", agentOrderNo);
			if(start!=0){
				map.put("startTime", String.valueOf(start));
			}
			if(end!=0){
				map.put("finishTime", String.valueOf(end));
			}
			map.put("state", state);
			map.put("institutionId", InstitutionIdNumber.AGENT);
			map.put("appId", appNames.getAppId());
			map.put("aisleCode", aisleCode);
			return tradeService.gain(map);
		}
		map.put("merchantId", merChantId);
		map.put("name", merName);
		map.put("orderNo", agentOrderNo);
		if(start!=0){
			map.put("startTime", String.valueOf(start));
		}
		if(end!=0){
			map.put("finishTime", String.valueOf(end));
		}
		map.put("state", state);
		map.put("agentId", k.getMerId());
		map.put("institutionId", InstitutionIdNumber.AGENT);
		return tradeService.query(map);
	}
	
	@RequestMapping("select")
	@SystemLog(module = "无卡交易统计", methods = "无卡交易统计")
	public String select(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/trade/selectList";
	}
	
	@RequestMapping("selectStatistics")
	@ResponseBody
	public PageInfo<XJStatistics> getXJ(String merChantId, String merName, String agentId, String starttime, String finishtime) throws UnsupportedEncodingException, ParseException {
		/*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date start = null;
		if(!"".equals(starttime) && null != starttime){
			 start=formatter.parse(starttime + " 00:00:00");
		}
		Date end=null;
		if(!"".equals(finishtime) && null != finishtime){
			end=formatter.parse(finishtime); 
		}*/
		//AgentOrders agentOrders = new AgentOrders();
		UserEntity k=UserEntityUtil.getUserFromSession();
    	Transaction transaction = new Transaction();
		transaction.setMerId(k.getMerId());
		merName = new String(merName.getBytes("iso8859-1"), "utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merId", k.getMerId());
		map.put("merChantId", merChantId);
		map.put("merChantsId", transactionService.findByObject(transaction).getMerChantId());
		map.put("merName", merName);
		map.put("agentId", agentId);
		map.put("institutionId", InstitutionIdNumber.AGENT);
		map.put("starttime", starttime);
		map.put("finishtime", finishtime);
		return tradeService.find(map);
	}
	/**
	 * 
	 * @param orderNo 订单号
	 * @param type 查询类型 1为更新订单状态、2为查询订单失败原因！！！
	 * @return
	 */
	@RequestMapping(value = "checkOrUpdatePlanState",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String checkOrUpdate(String orderNo ,Integer type, String aisleCode) {
		Map<String, Object> map = new HashMap<String, Object>();
			if(!StringUtils.isBlank(orderNo)){
				map.put("orderNo", orderNo);
			}
			String result = planDetailService.updateAndCheckPlanState(map,aisleCode);
			return result;
	}
	//ld01的
	@RequestMapping(value = "consumeUpdatePlanState",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String consumeUpdate(String orderNoStr ,String planId, String merchantId, String aisleCode) {
		String[] newPlanId = planId.split(",");
		String[] newMerchantId = merchantId.split(",");
		String[] newAisleCode = aisleCode.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
			if(!StringUtils.isBlank(orderNoStr)){
				map.put("orderNoStr", orderNoStr);
			}
			if(!StringUtils.isBlank(planId)){
				map.put("planId", newPlanId[0]);
			}
			if(!StringUtils.isBlank(merchantId)){
				map.put("merchantId", newMerchantId[0]);
			}
			if("ld02".equals(aisleCode)){
				return "请点击补还款Y";
			}
			String result = planDetailService.updateConsumePlanState(map,newAisleCode[0]);
			return result;
	}
	//ld02的
	@RequestMapping(value = "repayUpdatePlanState",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String repayUpdate(String orderNoStr ,String planId, String merchantId, String aisleCode) {
		String[] newPlanId = planId.split(",");
		String[] newMerchantId = merchantId.split(",");
		String[] newAisleCode = aisleCode.split(",");
		Map<String, Object> map = new HashMap<String, Object>();
			if(!StringUtils.isBlank(orderNoStr)){
				map.put("orderNoStr", orderNoStr);
			}
			if(!StringUtils.isBlank(planId)){
				map.put("planId", newPlanId[0]);
			}
			if(!StringUtils.isBlank(merchantId)){
				map.put("merchantId", newMerchantId[0]);
			}
			if("ld01".equals(aisleCode)){
				return "请点击补消费L";
			}
			String result = planDetailService.updaterepayPlanState(map,newAisleCode[0]);
			return result;
	}
	@RequestMapping("stores")
	@SystemLog(module = "开店", methods = "开店")
	public String stores(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/trade/storesLists";
	}
	
	@RequestMapping("getCommodityList")
	@ResponseBody
	public PageInfo<CommodityEntity> informationList(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("institutionId", InstitutionIdNumber.AGENT);
		PageInfo<CommodityEntity> page = tradeService.getCommodityList(map);
		return page;
	}
	
	@RequestMapping("storesInsert")
	@SystemLog(module = "开店", methods = "新增商品")
	public String insertInformation(Model model) {
		model.addAttribute("app", appNameService.getList());
		return "/system/trade/insertstores";
	}
	
	@RequestMapping(value = "insertstores",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String insertInformation(String appId,
									String open,
								    String commodityName,
								    String description,
									@RequestParam("img") MultipartFile fileimgs,
									Long price,
									Long salesVolume,
									HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtils.isBlank(appId)){
			map.put("appId", appId);
		}
		map.put("institutionId", InstitutionIdNumber.AGENT);
		if(!StringUtils.isBlank(open)){
			map.put("open", open);
		}
		if(!StringUtils.isBlank(commodityName)){
			map.put("commodityName", commodityName);
		}
		if(!StringUtils.isBlank(description)){
			map.put("description", description);
		}
		if (!fileimgs.isEmpty()) {
			map.put("img", UPLoadOss.getResult(fileimgs, request));
		}
		if(null != price){
			map.put("price", String.valueOf(price * 100));
		}
		if(null != salesVolume){
			map.put("salesVolume", String.valueOf(salesVolume));
		}
		String message = tradeService.insertstores(map);
		return message;
	}
	
	@RequestMapping("storesUpdate")
	@SystemLog(module = "开店", methods = "编辑商品")
	public String alertInformation(Model model,String commodityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commodityId", commodityId);
		CommodityEntity entity = tradeService.getstoresById(map);
		System.out.println("111111111111111"+entity.getCommodityName());
		model.addAttribute("goods", entity);
		return "/system/trade/updatestores";
	}
	
	@RequestMapping(value = "editstores",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String updateInformation(String commodityId,
									String open,
		    						String commodityName,
		    						String description,
		    						@RequestParam("img") MultipartFile fileimgs,
		    						Long price,
		    						Long salesVolume,
									HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commodityId", commodityId);
		map.put("institutionId", InstitutionIdNumber.AGENT);
		if(!StringUtils.isBlank(open)){
			map.put("open", open);
		}
		if(!StringUtils.isBlank(commodityName)){
			map.put("commodityName", commodityName);
		}
		if(!StringUtils.isBlank(description)){
			map.put("description", description);
		}
		if (!fileimgs.isEmpty()) {
			map.put("img", UPLoadOss.getResult(fileimgs, request));
		}
		if(null != price){
			map.put("price", String.valueOf(price*100));
		}
		if(null != salesVolume){
			map.put("salesVolume", String.valueOf(salesVolume));
		}
		String message = tradeService.editstores(map);
		return message;
	}
	
	@RequestMapping(value = "deletestores",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String deleteInformation(String commodityId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commodityId", commodityId);
		String message = tradeService.deletestores(map);
		return message;
	}
	@RequestMapping("/repairUnit")
	public String repairUnit(Model model, String cycleId, String orderNo, String merchantId, String cardNo, String appId){
		model.addAttribute("cycleId",cycleId);
		model.addAttribute("orderNo",orderNo);
		model.addAttribute("merchantId",merchantId);
		model.addAttribute("cardNo",cardNo);
		Cardinformation cardInfo = cardinformationService.queryCardInfoByCardNo(cardNo, appId);
		model.addAttribute("cardInfo", cardInfo);
		Map<String, Object> map = new HashMap<>();
		map.put("statementDate", cardInfo.getStatementdate());
		map.put("repaymentDate", cardInfo.getRepaymentdate());
		String url = APIUtil.Find_Date_List;
		String result = HttpClientUtils.doPost(url, map);
		System.out.println("result:"+result);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		List list = JSONObject.parseObject(data, List.class);
		for (int i = 0; i < list.size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String day = sdf.format(list.get(i));
			list.set(i,day);
		}
		model.addAttribute("unitTimeList", list);
		return "/system/trade/repairUnit";
	}

}
