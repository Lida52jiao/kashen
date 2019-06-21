package com.battcn.controller.system;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.battcn.entity.AppName;
import com.battcn.entity.UserEntity;
import com.battcn.entity.Withdraw;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.WithdrawEntityService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;
@Controller
@RequestMapping("Wuka")
public class WukaController extends BaseController {
	
	@Autowired
	private WithdrawEntityService withdrawEntityService;
	@Autowired
	private AppNameService appNameService;
	
	@RequestMapping("first")
	@SystemLog(module = "交易及结算管理", methods = "无卡收益结算初审")
	public String first(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/wuka/first";
	}
	
	@RequestMapping("getfirstList")
	@ResponseBody
	public PageInfo<Withdraw> getList(String merchantId, String starttime, String finishtime) {
		/*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Long start = null;
		if(!"".equals(starttime) && null != starttime){
			Date t=formatter.parse(starttime);
			start=t.getTime();
		}
		Long finish = null;
		if(!"".equals(finishtime) && null != finishtime){
			Date t=formatter.parse(finishtime); 
			finish=t.getTime();
		}*/
		UserEntity k=UserEntityUtil.getUserFromSession();
		AppName name = new AppName();
		name.setAgentId(k.getMerId());
		AppName t=appNameService.findByObject(name);
		System.out.println(t.getAppId());
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("start", starttime);
		map.put("finish", finishtime);
		map.put("appId", t.getAppId());
		map.put("institutionId", InstitutionIdNumber.AGENT);
		return withdrawEntityService.getList(map);
	}
	
	@RequestMapping("editFirst")
	public String editFirst(Model model, Long id) {
		if (id != null) {
			
			model.addAttribute("withdraw", withdrawEntityService.finds(id+""));
		}
		return "/system/wuka/editFirst";
	}
	
	@RequestMapping("alter")
	@ResponseBody
	public String alter(String orderNo, String state, String remarks) {
		//WithdrawEntity withdrawEntity = new WithdrawEntity();
		UserEntity k=UserEntityUtil.getUserFromSession();
		if("4".equals(state)){
			/*withdrawEntity.setOrderNo(orderNo);
			WithdrawEntity t = withdrawEntityService.finds(withdrawEntity);
			t.setState("2");
			t.setFirstName(k.getUserName());
			t.setFirstTime(System.currentTimeMillis());*/
			return withdrawEntityService.updates(k.getUserName(),orderNo,state,remarks);
		}
		if(!"".equals(remarks) && null != remarks){
			String s = withdrawEntityService.finds(orderNo, remarks, k.getUserName());
			return s;
		}
		return "f";
	}
	
	@RequestMapping("second")
	@SystemLog(module = "交易及结算管理", methods = "无卡收益结算复审")
	public String second(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/wuka/second";
	}
	
	@RequestMapping("getsecondList")
	@ResponseBody
	public PageInfo<Withdraw> getsecondList(String merchantId, String starttime, String finishtime) {
		/*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Long start = null;
		if(!"".equals(starttime) && null != starttime){
			Date t=formatter.parse(starttime);
			start=t.getTime();
		}
		Long finish = null;
		if(!"".equals(finishtime) && null != finishtime){
			Date t=formatter.parse(finishtime); 
			finish=t.getTime();
		}*/
		UserEntity k=UserEntityUtil.getUserFromSession();
		AppName name = new AppName();
		name.setAgentId(k.getMerId());
		AppName t=appNameService.findByObject(name);
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("start", starttime);
		map.put("finish", finishtime);
		map.put("appId", t.getAppId());
		map.put("institutionId", InstitutionIdNumber.AGENT);
		PageInfo<Withdraw> list = withdrawEntityService.selectLists(map);
		return list;
	}
	
	@RequestMapping("editSecond")
	public String editSecond(Model model, Long id) {
		if (id != null) {
			model.addAttribute("withdraw", withdrawEntityService.finds(id+""));
		}
		return "/system/wuka/editSecond";
	}
	
	@RequestMapping("alterSecond")
	@ResponseBody
	public String alterSecond(String orderNo, String state, String remarks) {
		//WithdrawEntity withdrawEntity = new WithdrawEntity();
		UserEntity k=UserEntityUtil.getUserFromSession();
		if("4".equals(state)){
			/*withdrawEntity.setOrderNo(orderNo);
			WithdrawEntity t = withdrawEntityService.findByObject(withdrawEntity);
			t.setState("4");
			t.setSecondName(k.getUserName());
			t.setSecondTime(System.currentTimeMillis());*/
			return withdrawEntityService.alter(k.getUserName(),orderNo,state,remarks);
		}
		if(!"".equals(remarks) && null != remarks){
			String s = withdrawEntityService.finds(orderNo, remarks, k.getUserName());
			return s;
		}
		return "f";
	}
	
	@RequestMapping("list")
	@SystemLog(module = "交易及结算管理", methods = "无卡结算结算查询")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/wuka/list";
	}
	
	@RequestMapping("getWithdrawList")
	@ResponseBody
	public PageInfo<Withdraw> getWithdrawList(String merchantId, String agentId, String orderNo, String state, String merchantName, String cardNo, String bankCode, String starttime, String finishtime) throws UnsupportedEncodingException, ParseException {
		/*SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Long start = null;
		if(!"".equals(starttime) && null != starttime){
			Date t=formatter.parse(starttime);
			start=t.getTime();
		}
		Long finish = null;
		if(!"".equals(finishtime) && null != finishtime){
			Date t=formatter.parse(finishtime); 
			finish=t.getTime();
		}*/
		UserEntity k=UserEntityUtil.getUserFromSession();
		AppName name = new AppName();
		name.setAgentId(k.getMerId());
		AppName t=appNameService.findByObject(name);
		merchantName = new String(merchantName.getBytes("iso8859-1"), "utf-8");
		Map<String,Object> map=new HashMap<>();
		map.put("merchantId", merchantId);
		map.put("agentId", agentId);
		map.put("orderNo", orderNo);
		map.put("state", state);
		map.put("merchantName", merchantName);
		map.put("cardNo", cardNo);
		map.put("bankCode", bankCode);
		map.put("start", starttime);
		map.put("finish", finishtime);
		map.put("appId", t.getAppId());
		map.put("institutionId", InstitutionIdNumber.AGENT);
		return withdrawEntityService.getWithdraw(map); 
	}

}
