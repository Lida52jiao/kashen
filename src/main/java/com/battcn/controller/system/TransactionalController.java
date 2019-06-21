package com.battcn.controller.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.battcn.entity.*;
import com.battcn.service.system.*;
import com.battcn.util.CommonUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Transactional/")
public class TransactionalController extends BaseController{
    @Autowired
    private TransactionalService transactionalService;
    @Autowired
   	private TradeService tradeService;
    @Autowired
	private AgentService agentService;
    @Autowired
	public TransactionService transactionService;
    @Autowired
   	private ChangelRateService changelRateService;
    @Autowired
   	private AppNameService appNameService;
    
    @RequestMapping("list")
	@SystemLog(module = "分润管理", methods = "分润详情")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/transactional/list";
	}
    
    @RequestMapping("findTransactional")
    @ResponseBody
    public PageInfo<TransactionalEntity> findTransactional(String orderNo){
    	if("".equals(orderNo)){
    		TransactionalEntity t=new TransactionalEntity();
    		t.setOrderNo("1");
    		PageInfo<TransactionalEntity> tList=transactionalService.queryPageForList(t);
    		return tList;
    	}
    	TransactionalEntity t=new TransactionalEntity();
    	t.setOrderNo(orderNo);
        return transactionalService.queryTransactionalForList(t);
    }
    @RequestMapping("lists")
   	@SystemLog(module = "分润管理", methods = "还款分润统计")
   	public String rePayCount(Model model) {
    	//判断身份展示输入框
    	UserEntity k=UserEntityUtil.getUserFromSession();
    	if(k.getMerId().startsWith("T")){
    		model.addAttribute("type", "1");
    	}
    	AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			model.addAttribute("type", "1");
		}
   		model.addAttribute("res", findResByUser());
   		model.addAttribute("app", appNameService.getList());
   		return "/system/transactional/lists";
   	}
    
//    /**
//     *
//     * @param merchantId
//     * @param agentId
//     * @param appId
//     * @param planId
//     * @param isLd
//     * @param aislecode
//     * @param payType ld01=1 ld02=2
//     * @param starttime
//     * @param finishtime
//     * @return
//     * @throws ParseException
//     */
//    @RequestMapping("rePayCount")
//    @ResponseBody
//    public PageInfo<ReturnCount> rePayCount( String merchantId,
//            String agentId, String appId,
//            String planId, String isLd,
//            String type, String aislecode,
//            String payType, String starttime, String finishtime) throws ParseException {
//
//	UserEntity k=UserEntityUtil.getUserFromSession();
//	Map<String, Object> map = new HashMap<String, Object>();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		long startSeconds = 0;
//		long finishSeconds = 0;
//		if(!"".endsWith(starttime)){
//			 startSeconds = sdf.parse(starttime+" 00:00:00").getTime();//毫秒
//		}
//		if(!"".endsWith(finishtime)){
//			finishSeconds = sdf.parse(finishtime+" 23:59:59").getTime();//毫秒
//		}
//		String module = "";
//		String typenew = "";
//		String payTypenew = "";
//		//module字段在八月份将会全面顶替type字段
//		if(!StringUtils.isBlank(aislecode)){
//			if(aislecode.equals("ld01")){
//				typenew = "1";
//				payTypenew = "1";
//				isLd = "1";
//			} else if(aislecode.equals("ld02")) {
//				typenew = "3";
//				payTypenew = "2";
//				isLd = "1";
//			} else if(aislecode.equals("ld06")){
//				//快付通是非落地的
//				module = "repayment";
//				payTypenew = "2";
//				isLd = "0";
//			} else {
//				module = "repayment";
//				payTypenew = "2";
//				isLd = "1";
//			}
//		}else {
//			module = "repayment";
//			payTypenew = "2";
//		}
//		//总平台查询所有
//		if(k.getMerId().startsWith("T")){
//			if(!StringUtils.isBlank(isLd)){
//				map.put("isLd", isLd);
//			}
//			if(!StringUtils.isBlank(planId)){
//				map.put("planId", planId);
//			}
//			if(!StringUtils.isBlank(merchantId)){
//				map.put("merchantId", merchantId);
//			}
//			if(!StringUtils.isBlank(appId)){
//				map.put("appId", appId);
//			}
//			if(!StringUtils.isBlank(module)){
//				map.put("module", module);
//			}
//			if(!StringUtils.isBlank(typenew)){
//				map.put("type", typenew);
//			}
//			if(!StringUtils.isBlank(payTypenew)){
//				map.put("payType", payTypenew);
//			}
//			if(!StringUtils.isBlank(aislecode)){
//				map.put("aisleCode", aislecode);
//			}
//			if(!StringUtils.isBlank(agentId)){
//				map.put("agentId", agentId);
//			}
//			if(startSeconds !=0){
//				map.put("startTime", String.valueOf(startSeconds));
//			}
//			if(finishSeconds != 0){
//				map.put("finishTime", String.valueOf(finishSeconds));
//			}
//			map.put("institutionId", InstitutionIdNumber.AGENT);
//			List<ReturnCount> list=new ArrayList<ReturnCount>();
//			PageInfo<ReturnCount> t=new PageInfo<ReturnCount>();
//			ReturnCount count = transactionalService.getRePayCount(map);
//			list.add(count);
//			t.setList(list);
//
//			return t;
//		}
//		AppName appName = new AppName();
//		appName.setAgentId(k.getMerId());
//		AppName appNames = appNameService.findByObject(appName);
//		//O单
//		if(null != appNames){
//			if(!StringUtils.isBlank(isLd)){
//				map.put("isLd", isLd);
//			}
//			if(!StringUtils.isBlank(planId)){
//				map.put("planId", planId);
//			}
//			if(!StringUtils.isBlank(merchantId)){
//				map.put("merchantId", merchantId);
//			}
//			if(!StringUtils.isBlank(module)){
//				map.put("module", module);
//			}
//			if(!StringUtils.isBlank(typenew)){
//				map.put("type", typenew);
//			}
//			if(!StringUtils.isBlank(payTypenew)){
//				map.put("payType", payTypenew);
//			}
//			if(!StringUtils.isBlank(aislecode)){
//				map.put("aisleCode", aislecode);
//			}
//			if(!StringUtils.isBlank(agentId)){
//				map.put("agentId", agentId);
//			}
//			if(startSeconds !=0){
//				map.put("startTime", String.valueOf(startSeconds));
//			}
//			if(finishSeconds != 0){
//				map.put("finishTime", String.valueOf(finishSeconds));
//			}
//			map.put("appId", appNames.getAppId());
//			map.put("institutionId", InstitutionIdNumber.AGENT);
//			List<ReturnCount> list=new ArrayList<ReturnCount>();
//			PageInfo<ReturnCount> t=new PageInfo<ReturnCount>();
//			ReturnCount count = transactionalService.getRePayCount(map);
//			list.add(count);
//			t.setList(list);
//
//			return t;
//		}
//		//代理商自己查自己的
//		if(!StringUtils.isBlank(isLd)){
//			map.put("isLd", isLd);
//		}
//		if(!StringUtils.isBlank(planId)){
//			map.put("planId", planId);
//		}
//		if(!StringUtils.isBlank(merchantId)){
//			map.put("merchantId", merchantId);
//		}
//		if(!StringUtils.isBlank(appId)){
//			map.put("appId", appId);
//		}
//		if(!StringUtils.isBlank(module)){
//			map.put("module", module);
//		}
//		if(!StringUtils.isBlank(typenew)){
//			map.put("type", typenew);
//		}
//		if(!StringUtils.isBlank(payTypenew)){
//			map.put("payType", payTypenew);
//		}
//		if(!StringUtils.isBlank(aislecode)){
//			map.put("aisleCode", aislecode);
//		}
//		if(startSeconds !=0){
//			map.put("startTime", String.valueOf(startSeconds));
//		}
//		if(finishSeconds != 0){
//			map.put("finishTime", String.valueOf(finishSeconds));
//		}
//		map.put("agentId", k.getMerId());
//		map.put("institutionId", InstitutionIdNumber.AGENT);
//		List<ReturnCount> list=new ArrayList<ReturnCount>();
//		PageInfo<ReturnCount> t=new PageInfo<ReturnCount>();
//		ReturnCount count = transactionalService.getRePayCount(map);
//		list.add(count);
//		t.setList(list);
//
//		return t;
//    }
    @RequestMapping("noCardList")
   	@SystemLog(module = "分润管理", methods = "还款分润统计")
   	public String noCardlists(Model model) {
    	//判断身份展示输入框
    	UserEntity k=UserEntityUtil.getUserFromSession();
    	if(k.getMerId().startsWith("T")){
    		model.addAttribute("type", "1");
    	}
    	AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			model.addAttribute("type", "1");
		}
    	model.addAttribute("key",changelRateService.getaisle());
   		model.addAttribute("res", findResByUser());
   		model.addAttribute("app", appNameService.getList());
   		return "/system/transactional/NoCardLists";
   	}
    
    
//    @RequestMapping("noCardCount")
//    @ResponseBody
//    public PageInfo<ReturnCount> noCardCount(String merchantId,
//            String agentId,
//            String appId, String type,
//            String aislecode,
//            String starttime, String finishtime) throws ParseException {
//    	UserEntity k=UserEntityUtil.getUserFromSession();
//		Map<String, Object> map = new HashMap<String, Object>();
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			long startSeconds = 0;
//			long finishSeconds = 0;
//			if(!"".endsWith(starttime)){
//				 startSeconds = sdf.parse(starttime+" 00:00:00").getTime();//毫秒
//			}
//			if(!"".endsWith(finishtime)){
//				finishSeconds = sdf.parse(finishtime+" 23:59:59").getTime();//毫秒
//			}
//			//总平台查询所有
//			if(k.getMerId().startsWith("T")){
//				if(!StringUtils.isBlank(merchantId)){
//					map.put("merchantId", merchantId);
//				}
//				if(!StringUtils.isBlank(appId)){
//					map.put("appId", appId);
//				}
//				if(!StringUtils.isBlank(type)){
//					map.put("type", "epos");
//				}
//				if(!StringUtils.isBlank(aislecode)){
//					map.put("aisleCode", aislecode);
//				}
//				if(!StringUtils.isBlank(agentId)){
//					map.put("agentId", agentId);
//				}
//				if(startSeconds !=0){
//					map.put("startTime", String.valueOf(startSeconds));
//				}
//				if(finishSeconds != 0){
//					map.put("finishTime", String.valueOf(finishSeconds));
//				}
//				map.put("type", "epos");
//				map.put("institutionId", InstitutionIdNumber.AGENT);
//				List<ReturnCount> list=new ArrayList<ReturnCount>();
//				PageInfo<ReturnCount> t=new PageInfo<ReturnCount>();
//				ReturnCount count = transactionalService.getNoCardCount(map);
//				list.add(count);
//				t.setList(list);
//
//				return t;
//			}
//			AppName appName = new AppName();
//			appName.setAgentId(k.getMerId());
//			AppName appNames = appNameService.findByObject(appName);
//			//O单
//			if(null != appNames){
//				if(!StringUtils.isBlank(merchantId)){
//					map.put("merchantId", merchantId);
//				}
//				if(!StringUtils.isBlank(type)){
//					map.put("type", "epos");
//				}
//				if(!StringUtils.isBlank(aislecode)){
//					map.put("aisleCode", aislecode);
//				}
//				if(!StringUtils.isBlank(agentId)){
//					map.put("agentId", agentId);
//				}
//				if(startSeconds !=0){
//					map.put("startTime", String.valueOf(startSeconds));
//				}
//				if(finishSeconds != 0){
//					map.put("finishTime", String.valueOf(finishSeconds));
//				}
//				map.put("type", "epos");
//				map.put("appId", appNames.getAppId());
//				map.put("institutionId", InstitutionIdNumber.AGENT);
//				List<ReturnCount> list=new ArrayList<ReturnCount>();
//				PageInfo<ReturnCount> t=new PageInfo<ReturnCount>();
//				ReturnCount count = transactionalService.getNoCardCount(map);
//				list.add(count);
//				t.setList(list);
//
//				return t;
//			}
//			//代理商自己查自己的
//			if(!StringUtils.isBlank(merchantId)){
//				map.put("merchantId", merchantId);
//			}
//			if(!StringUtils.isBlank(appId)){
//				map.put("appId", appId);
//			}
//			if(!StringUtils.isBlank(type)){
//				map.put("type", "epos");
//			}
//			if(!StringUtils.isBlank(aislecode)){
//				map.put("aisleCode", aislecode);
//			}
//			if(startSeconds !=0){
//				map.put("startTime", String.valueOf(startSeconds));
//			}
//			if(finishSeconds != 0){
//				map.put("finishTime", String.valueOf(finishSeconds));
//			}
//			map.put("type", "epos");
//			map.put("agentId", k.getMerId());
//			map.put("institutionId", InstitutionIdNumber.AGENT);
//			List<ReturnCount> list=new ArrayList<ReturnCount>();
//			PageInfo<ReturnCount> t=new PageInfo<ReturnCount>();
//			ReturnCount count = transactionalService.getNoCardCount(map);
//			list.add(count);
//			t.setList(list);
//
//			return t;
//    }
    
    @RequestMapping("getListByDay")
    @ResponseBody
    public List<TransactionalStatistics> getListByDay(Integer dayNum,String merchantId){
        return transactionalService.getListByDay(dayNum,merchantId);
    }
    @RequestMapping("getListByMonth")
    @ResponseBody
    public List<TransactionalStatistics> getListByMonth(String merchantId){
        return transactionalService.getListByMonth(merchantId);
    }
    @RequestMapping("getListByYear")
    @ResponseBody
    public List<TransactionalStatistics> getListByYear(String merchantId){
        return transactionalService.getListByYear(merchantId);
    }
    @RequestMapping("getStatistics")
    @ResponseBody
    public TransactionalParmeter getStatistics(String merchantId){
        int dayNum=0;
        List<TransactionalStatistics> tsDay=transactionalService.getListByDay(dayNum,merchantId);
        List<TransactionalStatistics> tsMonth=transactionalService.getListByMonth(merchantId);
        List<TransactionalStatistics> tsYear=transactionalService.getListByYear(merchantId);

        TransactionalParmeter tp=new TransactionalParmeter();
        if (null!=tsDay&&0!=tsDay.size()){
            tp.setDay(tsDay.get(0).getTotalAmount());
        }
        if (null!=tsMonth&&0!=tsMonth.size()){
            tp.setMonth(tsMonth.get(0).getTotalAmount());
        }
        if (null!=tsYear&&0!=tsYear.size()){
            tp.setYear(tsYear.get(0).getTotalAmount());
        }
        return tp;
    }
	@RequestMapping("count")
	@SystemLog(module = "分润管理", methods = "分润详情")
	public String countby(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/transactional/allcash";
	}

	@RequestMapping("countbyArea")
	@ResponseBody
	public PageInfo<TransactionalEntity> findbyArea(String startTime,String finishTime) throws ParseException {
		Transaction n = new Transaction();
		UserEntity k=UserEntityUtil.getUserFromSession();
		n.setMerId(k.getMerId());
		n = transactionService.findByObject(n);
		Map<String,Object> map = new HashedMap();
		map.put("merchantId",n.getMerChantId());
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long startSeconds = 0;
		long finishSeconds = 0;
		if(!"".equals(startTime)){
			startSeconds = sdf.parse(startTime+" 00:00:00").getTime();//毫秒
			map.put("startTime", startSeconds);
			finishSeconds = sdf.parse(finishTime+" 23:59:59").getTime();//毫秒
			map.put("finishTime", finishSeconds);
		}else{
			//时间条件为空时默认前一天时间
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(today);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			map.put("startTime", calendar.getTimeInMillis()+"");
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			map.put("finishTime", calendar.getTimeInMillis()+"");
		}

		return transactionalService.countbyArea(map);
	}
	@RequestMapping("total")
	@ResponseBody
	public String count(String startTime, String finishTime) throws ParseException {
		Transaction n = new Transaction();
		UserEntity k=UserEntityUtil.getUserFromSession();
		n.setMerId(k.getMerId());
		n = transactionService.findByObject(n);
		Map<String,Object> map = new HashedMap();
		map.put("merchantId",n.getMerChantId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long startSeconds = 0;
		long finishSeconds = 0;
		Date today = new Date();
		if(!"".equals(startTime)){
			startSeconds = sdf.parse(startTime+" 00:00:00").getTime();//毫秒
			map.put("startTime", startSeconds);
			finishSeconds = sdf.parse(finishTime+" 23:59:59").getTime();//毫秒
			map.put("finishTime", finishSeconds);
		}else{
			//时间条件为空时默认前一天时间
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(today);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			map.put("startTime", calendar.getTimeInMillis());
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			map.put("finishTime", calendar.getTimeInMillis());
		}

		return transactionalService.count(map);
	}

	@RequestMapping("CashSet")
	@SystemLog(module = "分润管理", methods = "平台提现设置")
	public String setAmount(Model model) {
		model.addAttribute("res", findResByUser());
		return "system/CashSet/setCash";
	}
	@RequestMapping("findCash")
	@ResponseBody
	public PageInfo<CashSet> showCash()  {
		CashSet cs = new CashSet();
		Map<String,Object> map = new HashedMap();
		map.put("institutionId",InstitutionIdNumber.AGENT);
		cs=transactionalService.findCash(map);
		List<CashSet> list = new ArrayList<>();
		list.add(cs);
		PageInfo<CashSet> page = new PageInfo<CashSet>();
		page.setList(list);
		return page;
	}
	@RequestMapping("editCash")
	public String editCash(Model model) {
		CashSet cs = new CashSet();
		Map<String,Object> map = new HashedMap();
		map.put("institutionId",InstitutionIdNumber.AGENT);
		cs = transactionalService.findCash(map);
		cs.setAliWithdrawMin(cs.getAliWithdrawMin()/100);
		cs.setAliWithdrawMax(cs.getAliWithdrawMax()/100);
		cs.setAliWithdrawFee(cs.getAliWithdrawFee()/100);
		model.addAttribute("CashSet",cs);
		return "/system/CashSet/editCash";
	}
	@RequestMapping("setCash")
	@ResponseBody
	public String setCash(Long aliWithdrawFee,Long aliWithdrawMin,Long aliWithdrawMax)  {

		Map<String,Object> map = new HashedMap();
		map.put("institutionId",InstitutionIdNumber.AGENT);
		if(!"".equals(aliWithdrawFee)||!"".equals(aliWithdrawMax)||!"".equals(aliWithdrawMin)) {

			map.put("aliWithdrawFee", aliWithdrawFee*100);
			map.put("aliWithdrawMin",aliWithdrawMin*100);
			map.put("aliWithdrawMax",aliWithdrawMax*100);
		}
		System.out.println("----------------"+map);
		String data = new String();
		String result = transactionalService.setCash(map);
			if(result.equals("0000")){
				data = "success";
				System.out.println("--------------------" + data);
				return data;
			}
			else {
				data = "erorr";
				System.out.println("--------------------" + data);
				return data;
			}
		}


}
