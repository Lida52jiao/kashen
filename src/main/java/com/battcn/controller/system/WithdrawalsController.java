package com.battcn.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.MerChants;
import com.battcn.entity.Withdrawals;
import com.battcn.entity.XJ;
import com.battcn.entity.XJWithdrawals;
import com.battcn.service.system.MerChantsService;
import com.battcn.service.system.WithdrawalsService;
import com.battcn.service.system.XJService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Withdrawals")
public class WithdrawalsController extends BaseController {
	
	@Autowired
	private WithdrawalsService withdrawalsService;
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private XJService xjService;
	
	@RequestMapping("list")
	@SystemLog(module = "系统设置", methods = "查询代发费")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/withdrawals/list";
	}
	
	@RequestMapping(value = "selectWithdrawals")
	@ResponseBody
	public PageInfo<XJ> get() {
		XJ t = new XJ();
		t.setId((long)1);
		XJ xj = xjService.findByObject(t);
		/*Withdrawals withdrawals=new Withdrawals();
		withdrawals.setId((long)1);
		Withdrawals k = withdrawalsService.findByObject(withdrawals);
		XJWithdrawals xjwithdrawals = new XJWithdrawals(xj.getId(), k.getGenerationFee(), k.getGenerationFeeRepayment(), xj.getFee0(), xj.getD0fee()); */
		List<XJ> list = new ArrayList<XJ>();
		list.add(xj);
		return new PageInfo<XJ>(list);
	}
	
	@RequestMapping("editUIalter")
	public String editUIalter(Model model, Long id) {
		if (id != null) {
			XJ t = new XJ();
			t.setId((long)1);
			XJ xj = xjService.findByObject(t);
			/*Withdrawals withdrawals=new Withdrawals();
			withdrawals.setId((long)1);
			Withdrawals k = withdrawalsService.findByObject(withdrawals);
			XJWithdrawals xjwithdrawals = new XJWithdrawals(xj.getId(), k.getGenerationFee(), k.getGenerationFeeRepayment(), xj.getFee0(), xj.getD0fee());*/ 
			model.addAttribute("withdrawals", xj);
		}
		return "/system/withdrawals/editUIalter";
	}
	
	@RequestMapping("alter")
	@ResponseBody
	public String alter(String fee0, String d0fee, String fee1, String d1fee) {
		/*Withdrawals withdrawals=new Withdrawals();
		withdrawals.setId((long)1);
		Withdrawals k=withdrawalsService.findByObject(withdrawals);
		k.setGenerationFee(generationFee);
		k.setGenerationFeeRepayment(generationFeeRepayment);
		MerChants t=new MerChants();
		t.setGenerationFee(generationFee);
		t.setGenerationFeeRepayment(generationFeeRepayment);
		merChantsService.recieve(t);
		withdrawalsService.update(k); */
		XJ h=new XJ();
		h.setId((long)1);
		XJ xj=xjService.findByObject(h);
		xj.setFee0((Double.parseDouble(fee0) * 10) + "");
		xj.setD0fee((Double.parseDouble(d0fee) * 100) + "");
		xj.setFee1((Double.parseDouble(fee1) * 10) + "");
		xj.setD1fee((Double.parseDouble(d1fee) * 100) + "");
		return xjService.update(xj); 
	}


}
