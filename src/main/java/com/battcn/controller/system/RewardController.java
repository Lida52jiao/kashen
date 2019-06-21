package com.battcn.controller.system;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.Agent;
import com.battcn.entity.MerCode;
import com.battcn.entity.Reward;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.MerCodeService;
import com.battcn.service.system.RewardService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Reward")
public class RewardController extends BaseController {
	
	@Autowired
	private RewardService rewardService;
	
	@RequestMapping("list")
	@SystemLog(module = "风控管理", methods = "会员升级分配设置")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/reward/list";
	}
	
	@RequestMapping("getRewardList")
	@ResponseBody
	public PageInfo<Reward> getList() throws UnsupportedEncodingException {
		PageInfo<Reward> page = rewardService.recieve();
		return page;
	}
	
	@RequestMapping("editReward")
	public String edit(Model model, Integer id) {
			Reward r = rewardService.selectById(id);
			model.addAttribute("reward", r);
		return "/system/reward/lists";
	}
	
	@RequestMapping("alter")
	@ResponseBody
	public String alter(Reward reward) {
		return rewardService.get(reward);
	}

}
