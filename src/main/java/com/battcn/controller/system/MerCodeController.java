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
import com.battcn.entity.UserEntity;
import com.battcn.service.system.MerCodeService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("MerCode")
public class MerCodeController extends BaseController {
	
	@Autowired
	private MerCodeService merCodeService;
	
	@RequestMapping("list")
	@SystemLog(module = "代理商管理", methods = "激活码管理")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/merCode/list";
	}
	
	@RequestMapping("getMerCodeList")
	@ResponseBody
	public PageInfo<MerCode> getList(String merId, String merName) throws UnsupportedEncodingException {
		MerCode n = null;
		UserEntity k=UserEntityUtil.getUserFromSession();
		if (!"".equals(merId) || !"".equals(merName)){
			n=new MerCode();
			merName = new String(merName.getBytes("iso8859-1"), "utf-8");
			n.setMerId(merId);
			n.setMerName(merName);
			List<MerCode> h=merCodeService.recieve(n);
			return new PageInfo<MerCode>(h);
		}
		if(k.getMerId().startsWith("T")){
			PageInfo<MerCode> hList=merCodeService.queryPageForList();
			return hList;
		}
		n=new MerCode();
		n.setOneMerId(k.getMerId());
		List<MerCode> hList=merCodeService.recieve(n);
		return new PageInfo<MerCode>(hList);
	}
	
	@RequestMapping("editMerCode")
	public String edit(Model model, String id) {
		if (id != null && !"".equals(id)) {
			model.addAttribute("merCode", merCodeService.findByPrimaryKey(id));
		}
		return "/system/merCode/lists";
	}
	
	@RequestMapping("alter")
	@ResponseBody
	public String alter(String agentId,String merName,int totalCode) {
		UserEntity k=UserEntityUtil.getUserFromSession();
		return merCodeService.get(k.getMerId(),agentId,merName,totalCode);
	}

}
