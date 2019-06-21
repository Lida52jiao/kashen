package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.Risk;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.RiskService;
import com.battcn.util.UserEntityUtil;

@Controller
@RequestMapping("Risk")
public class RiskController extends BaseController {
	
	@Autowired
	private RiskService riskService;
	
	@RequestMapping("addRisk")
	@ResponseBody
	public String addRisk(String agentId, String withdrawalsStarttime, String withdrawalsFinishtime, String transactionStarttime, String transactionFinishtime,
			String submitplanStarttime, String submitplanFinishtime, String maximumRepayment, String averageRepayment, 
			String consumptionAmount, String withdrawalAmount, String minimumAmount, String commissionFee, String number){
		Risk risk=new Risk();
		risk.setAgentId(agentId);
		Risk n=riskService.findByObject(risk);
		if(n != null){
			return "fail";
		}
		if(!"".equals(withdrawalsStarttime) && !"".equals(withdrawalsFinishtime) && !"".equals(transactionStarttime) && !"".equals(transactionFinishtime)){
			Risk r=new Risk(agentId, withdrawalsStarttime, withdrawalsFinishtime, transactionStarttime, transactionFinishtime, submitplanStarttime, submitplanFinishtime, maximumRepayment, averageRepayment, consumptionAmount, withdrawalAmount, minimumAmount, commissionFee, number, System.currentTimeMillis()+"", "");
			return riskService.save(r);
		}
		return "f";	
	}
	
	@RequestMapping("list")
	@SystemLog(module = "风控的设置", methods = "查询设置的风控信息")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/risk/list";
	}
	
	@RequestMapping("editRisks")
	public String editRisks(Model model) {
		UserEntity k=UserEntityUtil.getUserFromSession();
		Risk risk=new Risk();
		risk.setAgentId(k.getMerId());
		model.addAttribute("risk", riskService.findByObject(risk));
		return "/system/risk/editrisks";
	}
	
	@RequestMapping("alter")
	@ResponseBody
	public String alter(String agentId, String withdrawalsStarttime, String withdrawalsFinishtime, String transactionStarttime, String transactionFinishtime,
			String submitplanStarttime, String submitplanFinishtime, String maximumRepayment, String averageRepayment, 
			String consumptionAmount, String withdrawalAmount, String minimumAmount, String commissionFee, String number){
		if(!"".equals(withdrawalsStarttime) && !"".equals(withdrawalsFinishtime) && !"".equals(transactionStarttime) && !"".equals(transactionFinishtime)){
			Risk risk=new Risk();
			risk.setAgentId(agentId);
			Risk n=riskService.findByObject(risk);
			n.setWithdrawalsStarttime(withdrawalsStarttime);
			n.setWithdrawalsFinishtime(withdrawalsFinishtime);
			n.setTransactionStarttime(transactionStarttime);
			n.setTransactionFinishtime(transactionFinishtime);
			n.setSubmitplanStarttime(submitplanStarttime);
			n.setSubmitplanFinishtime(submitplanFinishtime);
			n.setMaximumRepayment(maximumRepayment);
			n.setAverageRepayment(averageRepayment);
			n.setConsumptionAmount(consumptionAmount);
			n.setWithdrawalAmount(withdrawalAmount);
			n.setMinimumAmount(minimumAmount);
			n.setCommissionFee(commissionFee);
			n.setNumber(number);
			return riskService.update(n);
		}
		return "f";	
	}

}
