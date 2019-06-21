package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.controller.BaseController;
import com.battcn.entity.Credit;
import com.battcn.entity.Credits;
import com.battcn.entity.Fee;
import com.battcn.entity.MerChants;
import com.battcn.entity.Transaction;
import com.battcn.entity.UserEntity;
import com.battcn.entity.Withdrawals;
import com.battcn.service.system.CreditService;
import com.battcn.service.system.MerChantsService;
import com.battcn.service.system.TransactionService;
import com.battcn.service.system.WithdrawalsService;
import com.battcn.util.UserEntityUtil;
@Controller
@RequestMapping("Credit")
public class CreditController extends BaseController {
	
	@Autowired
	private CreditService creditService;
	@Autowired
	public TransactionService transactionService;
	@Autowired
	private WithdrawalsService withdrawalsService;
	@Autowired
	private MerChantsService merChantsService;
	
	@RequestMapping("edit")
	public String add(Model model) {
		UserEntity k=UserEntityUtil.getUserFromSession();
		Credit n=new Credit();
		n.setId((long)1);
		Credit credit=creditService.findByObject(n);
		if(k.getMerId().startsWith("C")){
			Transaction t=new Transaction();
			t.setMerId(k.getMerId());
			Transaction h=transactionService.findByObject(t);
			if(h != null){
				Fee f=new Fee(k.getMerId(), h.getFloorNumber(), credit.getMerchantFee());
				model.addAttribute("fee", f);
				return "/system/credit/editMerchantFee";
			}else{
				Fee f=new Fee(k.getMerId(), "", credit.getMerchantFee());
				model.addAttribute("fee", f);
				return "/system/credit/editMerchantFee";
			}
		}
		credit.setGateId("easy");
		credit.setGateName("易生");
		credit.setCost("0.39");
		credit.setTransaction("D0");
		Withdrawals withdrawals=new Withdrawals();
		withdrawals.setId((long)1);
		Withdrawals t=withdrawalsService.findByObject(withdrawals);
		Credits credits = new Credits("easy", "易生", "0.39", "D0", credit.getMerchantFee(), t.getGenerationFeeRepayment());
		model.addAttribute("credit", credits);
		return "/system/credit/edit";
	}
	
	@RequestMapping("reset")
	@ResponseBody
	public String reset(String gateId, String gateName, String cost, String transaction, String merchantFee, String generationFeeRepayment) {
		Credit n=new Credit();
		n.setId((long)1);
		n.setMerchantFee(merchantFee);
		Withdrawals withdrawals=new Withdrawals();
		withdrawals.setId((long)1);
		Withdrawals k=withdrawalsService.findByObject(withdrawals);
		k.setGenerationFeeRepayment(generationFeeRepayment);
		withdrawalsService.update(k);
		MerChants t=new MerChants();
		t.setGenerationFeeRepayment(generationFeeRepayment);
		merChantsService.alter(t); 
		return creditService.update(n);
	}

}
