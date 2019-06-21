package com.battcn.controller.system;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.Income;
import com.battcn.service.system.IncomeService;
import com.battcn.service.system.ProfitService;
import com.battcn.service.system.SubsidyService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Income")
public class IncomeController extends BaseController {
	
	@Autowired
	private IncomeService incomeService; 
	
	@Autowired
	private ProfitService profitService; 
	
	@Autowired
	private SubsidyService subsidyService; 
	
	@RequestMapping("list")
	@SystemLog(module = "系统设置", methods = "交易分润设定")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("profit", profitService.getProfit());
		model.addAttribute("subsidy", subsidyService.getSubsidy());
		return "/system/income/list";
	}
	
	@RequestMapping(value = "getList")
	@ResponseBody
	public PageInfo<Income> get() {
		PageInfo<Income> n=incomeService.queryPageForList();
		return n;
	}
	
	@RequestMapping("editUIalter")
	public String editUIalter(Model model, Long id) {
		if (id != null) {
			model.addAttribute("income", incomeService.findByPrimaryKey(id));
		}
		return "/system/income/editUIalter";
	}
	
	@RequestMapping("alter")
	@ResponseBody
	public String alter(String level, String first, String second,String third, String brushFirst, String brushSecond, String brushThird) {
		Income income=new Income();
		income.setLevel(level);
		Income t=incomeService.findByObject(income);
		BigDecimal firsto = new BigDecimal(first);
		BigDecimal secondo = new BigDecimal(second);
		BigDecimal thirdo = new BigDecimal(third);
		BigDecimal brushFirsto = new BigDecimal(brushFirst);
		BigDecimal brushSecondo = new BigDecimal(brushSecond);
		BigDecimal brushThirdo = new BigDecimal(brushThird);
		BigDecimal big = new BigDecimal("10000");
		t.setLevel(level);
		t.setFirst(firsto.divide(big)+"");
		t.setSecond(secondo.divide(big)+"");
		t.setThird(thirdo.divide(big)+"");
		t.setBrushFirst(brushFirsto.divide(big)+"");
		t.setBrushSecond(brushSecondo.divide(big)+"");
		t.setBrushThird(brushThirdo.divide(big)+"");
		return incomeService.update(t);
	}

}
