package com.battcn.controller.system;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.Agent;
import com.battcn.entity.Code;
import com.battcn.entity.Transaction;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AgentService;
import com.battcn.service.system.CodeService;
import com.battcn.service.system.TransactionService;
import com.battcn.util.MD5Util;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Code")
public class CodeController extends BaseController {
	
	@Autowired
	private CodeService codeService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping("edit")
	public String add() {
		return "/system/code/edit";
	}
	
	@RequestMapping("listwithcode")
	@SystemLog(module = "激活码划拨", methods = "查询下级代理商")
	public String listwithcode(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/code/listwithcode";
	}
	
	@RequestMapping("listwithbuild")
	@SystemLog(module = "激活码查询", methods = "查询代理商生成的激活码")
	public String listwithbuild(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/code/listwithbuild";
	}
	
	@RequestMapping("list")
	@SystemLog(module = "激活码查询", methods = "查询全部激活码")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/code/list";
	}
	
	@RequestMapping("editUIcode")
	public String editUIcode(Model model, Long id) {
		if (id != null) {
			model.addAttribute("agent", agentService.findByPrimaryKey(id));
		}
		return "/system/code/editUIcode";
	}
	
	@RequestMapping("generatedCode")
	@ResponseBody
	public String add(String merId,String codes){
		Transaction transaction=new Transaction();
		transaction.setMerId(merId);
		Transaction f=transactionService.findByObject(transaction);
		if(f == null){
			return "fail";
		}
		Agent n = new Agent();
		n.setMerId(merId);
		Agent h=agentService.findByObject(n);
		int code=Integer.parseInt(codes);
		int generatedCode=Integer.parseInt(h.getGeneratedCode());
		int notused=Integer.parseInt(h.getNotused());
		if(merId.startsWith("T")){
			h.setGeneratedCode(generatedCode+code+"");
			h.setNotused(notused+code+"");
			for(int i=1;i<=code;i++){
				Long k=System.currentTimeMillis();
				String s=merId+k;
				String r=MD5Util.getMD5String(s);
				Code v=new Code(r, "N", System.currentTimeMillis()+"", merId);
				codeService.save(v);
			}
			return agentService.update(h);
		}
		int assign=Integer.parseInt(h.getAssign());
		if(assign>=code){
			int t=assign-code;
			h.setGeneratedCode(generatedCode+code+"");
			h.setNotused(notused+code+"");
			h.setAssign(t+"");
			for(int i=1;i<=code;i++){
				Long k=System.currentTimeMillis();
				String s=merId+k;
				String r=MD5Util.getMD5String(s);
				Code v=new Code(r, "N", System.currentTimeMillis()+"", merId);
				codeService.save(v);
			}
			return agentService.update(h);
		}
		return "f";
	}
	
	@RequestMapping("allotsCode")
	@ResponseBody
	public String alter(String merId,String oneMerId,String codes) {
		Agent n = new Agent();
		n.setMerId(oneMerId);
		Agent h=agentService.findByObject(n);
		int code=Integer.parseInt(codes);
		if(code < 50){
			return "fail";
		}
		if(oneMerId.startsWith("T")){
			Agent a = new Agent();
			a.setMerId(merId);
			Agent f=agentService.findByObject(a);
			int totalCode=Integer.parseInt(f.getTotalCode());
			int assign=Integer.parseInt(f.getAssign());
			f.setTotalCode(totalCode+code+"");
			f.setAssign(assign+code+"");
			return agentService.update(f);
		}
		int assign=Integer.parseInt(h.getAssign());
		if(assign>=code){
			int t=assign-code;
			h.setAssign(t+"");
			Agent a = new Agent();
			a.setMerId(merId);
			Agent f=agentService.findByObject(a);
			int totalCode=Integer.parseInt(f.getTotalCode());
			int assigns=Integer.parseInt(f.getAssign());
			f.setTotalCode(totalCode+code+"");
			f.setAssign(assigns+code+"");
			agentService.update(f);
			return agentService.update(h);
		}
		return "f";
	}
	
	@RequestMapping("getCodeList")
	@ResponseBody
	public PageInfo<Code> getList(String status) {
		Code n = null;
		if (StringUtils.isNoneEmpty(status)) {
			UserEntity k=UserEntityUtil.getUserFromSession();
			n = new Code();
			n.setAgentId(k.getMerId());
			n.setStatus(status);;
			return codeService.queryPageForList(n);
		}
		UserEntity k=UserEntityUtil.getUserFromSession();
		n=new Code();
		n.setAgentId(k.getMerId());
		PageInfo<Code> hList=codeService.queryPageForList(n);
		return hList;
	}
	
	@RequestMapping("getAgentList")
	@ResponseBody
	public PageInfo<Agent> get() {
		UserEntity k=UserEntityUtil.getUserFromSession();
		Agent n=new Agent();
		n.setMerId(k.getMerId());
		PageInfo<Agent> hList=agentService.queryPageForList(n);
		return hList;
	}
	
	@RequestMapping("listactivationCode")
	@SystemLog(module = "激活码管理", methods = "查询下级代理商激活码使用情况")
	public String listactivationCode(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/code/listactivationCode";
	}
}
