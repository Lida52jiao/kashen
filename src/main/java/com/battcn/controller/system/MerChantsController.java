package com.battcn.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.annotation.SystemLog;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.battcn.entity.*;
import com.battcn.service.system.*;
import com.battcn.util.*;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("MerChants")
public class MerChantsController extends BaseController {

	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	public TransactionService transactionService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private AppNameService appNameService;
	@Autowired
	private AgentMerLevelService agentMerLevelService;

	
	@RequestMapping("list")
	@SystemLog(module = "用户管理", methods = "查询用户的信息")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		return "/system/merChants/list";
	}

	@RequestMapping("getMerChants")
	@ResponseBody
	public PageInfo<MerChants> getList(String merChantId,
									   String merName,
									   String merMp,
									   String certNo,
									   String merStat,
									   String agentStatus,
									   String merType,
									   String status,
									   String agentId,
									   String frozen,
									   String appId) throws UnsupportedEncodingException {
		UserEntity k= UserEntityUtil.getUserFromSession();
		List<MerChants> newList = new ArrayList<MerChants>();
		PageInfo<MerChants> newPage = new PageInfo<MerChants>(newList);
		//总平台
		if(k.getMerId().startsWith("T")){
			MerChants m = new MerChants();
			m.setInstitutionId(k.getMerId());
			m.setMerChantId(merChantId);
			m.setMerName(merName);
			m.setMerMp(merMp);
			m.setCertNo(certNo);
			m.setMerStat(merStat);
			m.setStatus(status);
			m.setAgentId(agentId);
			m.setFrozen(frozen);
			m.setAppId(appId);
			m.setMerType(merType);
			m.setAgentStatus(agentStatus);
			PageInfo<MerChants> page = merChantsService.gain(m);

			for(MerChants n:page.getList()){
				AgentMerLevel level = agentMerLevelService.getByWeight(Long.parseLong(n.getMerType()));
				n.setMerType(level.getLevelname());
				newList.add(n);
			}
			newPage.setPages(page.getPages());
			newPage.setTotal(page.getTotal());
			newPage.setPageSize(page.getPageSize());
			return newPage;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if(null != appNames){
			MerChants m = new MerChants();
			m.setAppId(appNames.getAppId());
			m.setMerChantId(merChantId);
			m.setMerName(merName);
			m.setMerMp(merMp);
			m.setCertNo(certNo);
			m.setMerStat(merStat);
			m.setStatus(status);
			m.setAgentId(agentId);
			m.setMerType(merType);
			m.setAgentStatus(agentStatus);
			m.setFrozen(frozen);
			PageInfo<MerChants> page = merChantsService.gain(m);
			for(MerChants n:page.getList()){
				AgentMerLevel level = agentMerLevelService.getByWeight(Long.parseLong(n.getMerType()));
				n.setMerType(level.getLevelname());
				newList.add(n);
			}
			newPage.setPages(page.getPages());
			newPage.setTotal(page.getTotal());
			newPage.setPageSize(page.getPageSize());
			return newPage;
		}
//		Agent n = new Agent();
//		n.setOneMerId(k.getMerId());
//		List<Agent> tlist=agentService.queryObjectForList(n);
		List<String> list=new ArrayList<String>();
//		for(Agent t : tlist){
//			Agent v=(Agent)t;
//			list.add(v.getMerId());
//		}
		list.add(k.getMerId());
		Mer m=new Mer();
		m.setMerChantId(merChantId);
		m.setMerName(merName);
		m.setMerMp(merMp);
		m.setCertNo(certNo);
		m.setMerStat(merStat);
		m.setStatus(status);
		m.setAgentId(agentId);
		m.setList(list);
		m.setFrozen(frozen);
		m.setAppId(appId);
		PageInfo<MerChants> hList=merChantsService.query(m);
		for(MerChants n:hList.getList()){
			AgentMerLevel level = agentMerLevelService.getByWeight(Long.parseLong(n.getMerType()));
			n.setMerType(level.getLevelname());
			n.setMerMp(n.getMerMp().substring(0,5)+"****");
			n.setCertNo("暂无");
			newList.add(n);
		}
		newPage.setPages(hList.getPages());
		newPage.setTotal(hList.getTotal());
		newPage.setPageSize(hList.getPageSize());
		return newPage;
	}
	
	@RequestMapping("editmerChants")
	public String editmerChants(Model model, Long id) {
		if (id != null) {
			model.addAttribute("merChants", merChantsService.findByPrimaryKey(id));	
		}
		return "/system/merChants/editmerChants";
	}
	
	@RequestMapping("alter")
	@ResponseBody
	public String alter(String merChantId,String merName,String merMp,String isNotUse,String agentStatus,String merType,String merChantFee,String generationFee,String generationFeeRepayment, int time) {
		Transaction t=new Transaction();
		t.setMerChantId(merChantId);
		Transaction transaction=transactionService.findByObject(t);

			MerChants n = new MerChants();
			n.setMerChantId(merChantId);
			MerChants h=merChantsService.findByObject(n);
			if(true){
				long finishDate = Long.parseLong(h.getFinishDate());
				long finishDates = (long)time*24*3600*1000;
				h.setMerName(merName);
				h.setMerMp(merMp);
				h.setAgentStatus(agentStatus);
				h.setMerType(merType);
				h.setMerChantFee(merChantFee);
				h.setIsNotUse(isNotUse);
				h.setGenerationFee(generationFee);
				h.setGenerationFeeRepayment(generationFeeRepayment);
				h.setFinishDate(finishDate+finishDates+"");
				if(transaction != null){
					//同步修改中间表代理商等级
					transaction.setAgentStatus(agentStatus);
					transactionService.update(transaction);
				}
				return merChantsService.update(h);
			}
			return "fail";
	}
	
	@RequestMapping("deleteMer")
	@ResponseBody
	public String deleteMer(Long id) {
		MerChants h=merChantsService.findByPrimaryKey(id);
		if("N".equals(h.getMerStat()) && "N".equals(h.getStatus())){
			return merChantsService.delete(h);
		}
		return "f";
	}

	@RequestMapping("getAccount")
	public String getAccount(Model model,String merchantId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Mer mer = new Mer();
		map.put("merchantId",merchantId);
		String url = "http://172.31.109.39/yj-account/account/getAccount";
		String result=new HttpClientUtils().doPost(url,map);
		JSONObject t = JSONObject.parseObject(result);
		String data = t.getString("data");
		if(data!=null){
			JSONObject tt = JSONObject.parseObject(data);
			BigDecimal big = new BigDecimal("100");
			BigDecimal bigAccount = new BigDecimal(tt.getString("balanceProfit"));
			BigDecimal freezAccount = new BigDecimal(tt.getString("balanceProfitFrozen"));
			mer.setBalanceProfit(bigAccount.divide(big));
			mer.setBalanceProfitFrozen(freezAccount.divide(big));
			mer.setMerChantId(merchantId);
			model.addAttribute("mer",mer);
			return "/system/merChants/freeze";
		}
		BigDecimal a = new BigDecimal(0);
		mer.setBalanceProfit(a);
		mer.setBalanceProfitFrozen(a);
		mer.setMerChantId(merchantId);
		model.addAttribute("mer",mer);
		return "/system/merChants/freeze";
	}

	@RequestMapping("freeze")
	@ResponseBody
	public String freeze(String merchantId,Long amount,Long balanceProfitFrozen)  {

		Map<String,Object> map = new HashedMap();
		if(amount>balanceProfitFrozen){
			amount = amount - balanceProfitFrozen;
			map.put("institutionId",InstitutionIdNumber.AGENT);
			map.put("merchantId",merchantId);
			map.put("amount",amount*100);
			String url = "http://47.104.4.155/yj-account/account/freeze";
			String result=new HttpClientUtils().doPost(url,map);
		}
		else{
			amount = balanceProfitFrozen - amount;
			map.put("institutionId",InstitutionIdNumber.AGENT);
			map.put("merchantId",merchantId);
			map.put("amount",amount*100);
			String url = "http://47.104.4.155/yj-account/account/unfreeze";
			String result=new HttpClientUtils().doPost(url,map);
		}

		return "success";
	}
	@RequestMapping("/realNameList")
	public String realNameList(Model model){
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		return "/system/merChants/realNameList";
	}
	@RequestMapping("/queryRealNameList")
	@ResponseBody
	public PageInfo<MpInformation> queryRealNameList(MpInformation info) throws ParseException {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String url = "http://47.104.4.155:1172/Information/selectLists";
		Map<String, Object> map = new HashMap<>();
		map.put("merChantId",info.getMerChantId());
		map.put("merName",info.getMerName());
		map.put("mobile",info.getMobile());
		map.put("state",info.getState());
		map.put("artificial",info.getArtificial());
		long startSeconds = 0;
		long finishSeconds = 0;
		if(!"".equals(info.getStartTime())){
			startSeconds = sdf.parse(info.getStartTime()).getTime();//毫秒
		}
		if(!"".equals(info.getFinshTime())){
			finishSeconds = sdf.parse(info.getFinshTime()).getTime();//毫秒
		}
		map.put("startTime",startSeconds);
		map.put("finshTime",finishSeconds);
		map.put("institutionId","T00000009");
		map.put("pageNum", pageNum.toString());
		map.put("pageSize", pageSize.toString());
		String result = HttpClientUtils.doPost(url, map);
		System.out.println("result:"+result);
		JSONObject t = JSONObject.parseObject(result);
		List<MpInformation> list = JSON.parseArray(t.getString("list"),MpInformation.class);
		for (int i = 0; i < list.size(); i++) {
			Long createTime = list.get(i).getCreateTime();
			String time = sdf.format(createTime);
			list.get(i).setTime(time);
		}
		PageInfo<MpInformation> page = new PageInfo<MpInformation>();
		page.setList(list);
		page.setPageSize(Integer.parseInt(t.getString("pageSize")));
		page.setPages(Integer.parseInt(t.getString("pages")));
		page.setTotal(Integer.parseInt(t.getString("total")));
		System.out.println("page:"+page);
		return page;
	}
	@RequestMapping("/check")
	public String check(Model model,String merChantId) {
		model.addAttribute("merChantId",merChantId);
		return "/system/merChants/check";
	}
	@RequestMapping("/checkRealName")
	@ResponseBody
	public String checkRealName(String merChantId,Long state)  {
		String url = "http://47.104.4.155:1172/Information/alter";
		Map<String, Object> map = new HashMap<>();
		map.put("merChantId", merChantId);
		map.put("state", state);
		String result = HttpClientUtils.doPost(url, map);
		System.out.println("审核结果："+result);
		return result;
	}
	@RequestMapping("gam")
	@SystemLog(module = "用户管理", methods = "查询用户的信息")
	public String gam (Model model) {
		model.addAttribute("res", findResByUser());
//		model.addAttribute("app", appNameService.getList());
		return "/system/merChants/getAgentMer";
	}

	@RequestMapping("getAgentMer")
	@ResponseBody
	public PageInfo<MerChants> getAgent(String merChantId,
										String merName,
										String merMp,
										String merChant,
										String certNo,
										String merStat,
										String agentStatus,
										String merType,
										String status,
										String agentId,
										String frozen,
										String appId) throws UnsupportedEncodingException {
		UserEntity k= UserEntityUtil.getUserFromSession();
		List<MerChants> newList = new ArrayList<MerChants>();
		List<String> list=new ArrayList<String>();

		merName = new String(merName.getBytes("iso8859-1"), "utf-8");
		MerChants m=new MerChants();
		m.setOneMerId(k.getMerId());
		if(!"".equals(merChant)){

		}
		m.setMerChantId(merChantId);
		m.setMerName(merName);
		m.setMerMp(merMp);
		m.setCertNo(certNo);
		m.setMerStat(merStat);
		m.setStatus(status);
		m.setAgentId(agentId);
		m.setFrozen(frozen);
		m.setAppId(appId);
		PageInfo<MerChants> hList=merChantsService.queryPageForList(m);
		return hList;

	}

	@RequestMapping("supplement")
	public String supplement(Model model,String merChantId,String name){
		model.addAttribute("merChantId",merChantId);
		model.addAttribute("name",name);
		System.out.println("name:"+name+";merChantId:"+merChantId);
		return "system/merChants/supplement";
	}
	@RequestMapping(value = "/addFaceImgUrl",method = RequestMethod.POST)
	@ResponseBody
	public String addFaceImgUrl(String merChantId,
								@RequestParam(value = "userIDCardA") MultipartFile userIDCardA,
								@RequestParam(value = "userIDCardB") MultipartFile userIDCardB,
								@RequestParam(value = "cardImgA") MultipartFile cardImgA,
								@RequestParam(value = "faceImg") MultipartFile faceImg, HttpServletRequest request){
		if (StringUtils.isEmpty(merChantId)) {
			return "merChantId";
		}
		if (!userIDCardA.isEmpty() || !userIDCardB.isEmpty() || !cardImgA.isEmpty() || !faceImg.isEmpty()) {

			if (!userIDCardA.isEmpty()) {
				String result = upFile("FRONT_ID_CARD_PHOTO",merChantId,userIDCardA,request);
				if (result.equals("error")) {
					return "f";
				}
			}
			if (!userIDCardB.isEmpty()) {
				String result = upFile("BACK_ID_CARD_PHOTO",merChantId,userIDCardB,request);
				if (result.equals("error")) {
					return "f";
				}
			}
			if (!cardImgA.isEmpty()) {
				String result = upFile("BANK_CARD_IMG",merChantId,cardImgA,request);
				if (result.equals("error")) {
					return "f";
				}
			}
			if (!faceImg.isEmpty()) {
				String result = upFile("HAND_ID_CARD_PHOTO",merChantId,faceImg,request);
				if (result.equals("error")) {
					return "f";
				}
			}
		} else {
			return "fail";
		}
		return "success";
	}
	public String upFile(String name, String merChantId, MultipartFile file, HttpServletRequest request){
		try {
			// 文件保存路径
			String filePath = request.getSession().getServletContext()
					.getRealPath("/")
					+ file.getOriginalFilename();
			// 转存文件
			file.transferTo(new File(filePath));

			String s = MD5Util.getMD5String(filePath);
			String ossKey = "indexPush/" + s;
			OSSClientUtil clientUtil = new OSSClientUtil();
			Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24
					* 365 * 10);
			try {
				InputStream instream = new FileInputStream(filePath);
				clientUtil.uploadFile2OSS(instream, ossKey);
				// 获取url
				URL url = clientUtil.createUrl(ossKey, expiration);
				clientUtil.destory();
				String fileUrl = url.toString();

				Map<String,Object> map = new HashMap();
				map.put("name",name);
				map.put("merChantId",merChantId);
				map.put("imgStr",fileUrl);
				String result = HttpClientUtils.doPost("http://47.104.4.155:1172/Information/alters",map);
				System.out.println("result"+result);
				YJResult yjResult = JSONObject.parseObject(result).toJavaObject(YJResult.class);
				System.out.println("yjResult"+JSON.toJSONString(yjResult));
				System.out.println("yjResult.getRespCode()"+yjResult.getRespCode());
				if (yjResult.getRespCode().equals("0000")) {
					return "success";
				} else {
					return "error";
				}
			} catch (FileNotFoundException ef) {
				ef.printStackTrace();
				return "f";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "f";
		}
	}

}
