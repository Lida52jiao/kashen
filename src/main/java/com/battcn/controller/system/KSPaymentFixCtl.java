package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.FixReward;
import com.battcn.entity.PaymentNum;
import com.battcn.service.system.KSFixRewardService;
import com.battcn.service.system.KSPaymentNumService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("KSPaymentFix")
public class KSPaymentFixCtl extends BaseController{

	@Autowired
	private KSFixRewardService ksFixRewardService;
	@Autowired
	private KSPaymentNumService ksPaymentNumService;
	
	@RequestMapping("numList")
	@SystemLog(module = "风控管理", methods = "商户模式配置")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("payment",ksPaymentNumService.getByIdentity());
		return "/system/payMent/num";
	}
	
	@RequestMapping("getMerChants")
	@ResponseBody
	public PageInfo<PaymentNum> getData(){
		PageInfo<PaymentNum> page = ksPaymentNumService.getPage();
		return page;
	}
	@RequestMapping("alertNum")
	@SystemLog(module = "风控管理", methods = "商户模式编辑")
	public String alert(Model model,Long id) {
		PaymentNum pay = ksPaymentNumService.getById(id);
		FixReward reward = ksFixRewardService.getData();
		Long number = new Long(0);
		model.addAttribute("paymenttotal",ksPaymentNumService.getByIdentity());
		if(pay.getRewardOrNot().equals("N")){
			FixReward zero = new FixReward();
			zero.setOneMer(number);
			zero.setTwoMer(number);
			zero.setThreeMer(number);
			model.addAttribute("payment",pay);
			model.addAttribute("fix",zero);
		} else {
			model.addAttribute("payment",pay);
			model.addAttribute("fix",reward);
		}
		return "/system/payMent/editNum";
	}
	
	@RequestMapping("alert")
	@ResponseBody
	public String updateData(Long id,Long fixReward, Long subsidy,Long oneMer,Long twoMer, Long threeMer,String rewardOrNot){
		Integer count = ksPaymentNumService.updateData(id,fixReward,subsidy,oneMer, twoMer,threeMer,rewardOrNot);
		if(count == 1){
			return "success";
		}
		return "error";
	}
	
	@RequestMapping("confirm")
	@ResponseBody
	public String confirm(PaymentNum num){
		Integer count = ksPaymentNumService.confirmData(num);
		if(count == 1){
			return "success";
		}
		return "error";
	}
	
}
