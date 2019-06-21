package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.controller.BaseController;
import com.battcn.entity.Fee;
import com.battcn.service.system.FeeService;

@Controller
@RequestMapping("Fee")
public class FeeController extends BaseController {
	
	@Autowired
	private FeeService feeService;
	
	@RequestMapping("addMerchantFee")
	@ResponseBody
	public String add(String agentId, String floorNumber,String merchantFee) {
		if("".equals(floorNumber)){
			return "f";
		}
		double number=Double.parseDouble(floorNumber);
		double fee=Double.parseDouble(merchantFee);
		if(fee < 0){
			return "s";
		}
		if(number < fee){
			Fee n=new Fee();
			n.setAgentId(agentId);
			Fee h=feeService.findByObject(n);
			if(h != null){
				h.setAgentId(agentId);
				h.setFloorNumber(floorNumber);
				h.setMerchantFee(merchantFee);
				return feeService.update(h);
			}
			Fee f=new Fee(agentId, floorNumber, merchantFee);
			return feeService.save(f);
		}
		return "fail";
	}

}
