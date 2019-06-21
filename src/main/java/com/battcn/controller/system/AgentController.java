package com.battcn.controller.system;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.battcn.entity.*;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.service.system.AgentAreaBindService;
import com.battcn.service.system.AgentBasePriceService;
import com.battcn.service.system.AgentLevelService;
import com.battcn.service.system.AgentService;
import com.battcn.service.system.MerChantsService;
import com.battcn.service.system.MerCodeService;
import com.battcn.service.system.ResourcesService;
import com.battcn.service.system.TransactionService;
import com.battcn.service.system.UserRoleService;
import com.battcn.service.system.UserService;
import com.battcn.util.PasswordHelper;
import com.battcn.util.UserEntityUtil;
import com.battcn.util.YJ;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Agent")
public class AgentController extends BaseController {

	@Autowired
	private AgentService agentService;
	@Autowired
	private UserService userService;
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private MerChantsService merChantsService;
	@Autowired
	private MerCodeService merCodeService;
	@Autowired
	private AgentBasePriceService agentBasePriceService;
	@Autowired
	private AgentLevelService agentLevelService;
	@Autowired
	private AgentAreaBindService agentAreaBindService;

	@RequestMapping("edit")
	public String add() {
		return "/system/agent/edit";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String insertAgent(String merMp,String merName,String accountNumber,String mailbox,String password,String oneMerId){
		if(!"".equals(merMp) && !"".equals(merName) && !"".equals(accountNumber) && !"".equals(mailbox) && !"".equals(password) ){
			UserEntity user = new UserEntity();
			user.setAccountName(accountNumber);
			UserEntity t=userService.findByObject(user);
			if(t != null){
				return "fail";
			}
			UserEntity userFormMap = new UserEntity();
			userFormMap.setAccountName(accountNumber);
			userFormMap.setPassWord(password);
			UserEntity r=PasswordHelper.encryptPassword(userFormMap);
			Agent n=new Agent(merName, merMp, accountNumber,r.getPassWord(), mailbox, oneMerId, System.currentTimeMillis()+"");
			agentService.save(n);
			Agent h=agentService.findByObject(n);
			String s = "C" + YJ.formatDate(new Date()) + YJ.formattime(new Date());
			long id = h.getId() + 10000;
			String merId = s + id;
			h.setMerId(merId);
			h.setTotalCode("0");
			h.setAssign("0");
			h.setGeneratedCode("0");
			h.setUsed("0");
			h.setNotused("0");

			agentService.update(h);
			MerCode merCode = new MerCode();
			merCode.setMerId(merId);
			merCode.setMerName(merName);
			merCode.setOneMerId(oneMerId);
			merCode.setTotalCode(0);
			merCode.setGeneratedCode(0);
			merCode.setUsed(0);
			merCode.setNotused(0);
			merCode.setAssign(0);
			merCodeService.save(merCode);
			UserEntity k=new UserEntity();
			k.setAccountName(accountNumber);
			k.setDate(YJ.formatDate(new Date())+YJ.formattime(new Date()));
			k.setEmail(mailbox);
			k.setPassWord(password);
			k.setMerId(merId);
			k.setTel(merMp);
			k.setUserName(merName);
			k.setLocked("1");
			PasswordHelper.encryptPassword(k);
			userService.save(k);
			UserEntity v=new UserEntity();
			v.setMerId(merId);
			UserEntity userEntity=userService.findByObject(v);
			UserRoleEntity userRoleEntity=new UserRoleEntity();
			userRoleEntity.setUserId(userEntity.getId());
			userRoleEntity.setRoleId((long) 3);
			userRoleService.save(userRoleEntity);
			return "success";
		}
		return "f";	
	}
	
	@RequestMapping("list")
	@SystemLog(module = "代理商管理", methods = "查询下级代理商")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/agent/list";
	}
	
	@RequestMapping("listwithpwd")
	@SystemLog(module = "代理商信息变更", methods = "查询下级代理商")
	public String listwithpwd(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/agent/listwithpwd";
	}
	
	@RequestMapping("listwithalter")
	@SystemLog(module = "代理商信息变更", methods = "查询下级代理商")
	public String listwithalter(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/agent/listwithalter";
	}
	
	@RequestMapping("editUI")
	public String editUI(Model model, Long id) {
		if (id != null) {
			Agent n=agentService.findByPrimaryKey(id);
			Transaction t = new Transaction();
			t.setMerId(n.getMerId());
			model.addAttribute("agent", transactionService.findByObject(t));
			
		}
		return "/system/agent/editAgent";
	}
	
	@RequestMapping("editUIpwd")
	public String editUIpwd(Model model, Long id) {
		if (id != null) {
			model.addAttribute("agent", agentService.findByPrimaryKey(id));
		}
		return "/system/agent/editUIpwd";
	}
	
	@RequestMapping("editUIalter")
	public String editUIalter(Model model, Long id) {
		if (id != null) {
			model.addAttribute("agent", agentService.findByPrimaryKey(id));
		}
		return "/system/agent/editUIalter";
	}

	@RequestMapping("getAgentList")
	@ResponseBody
	public PageInfo<Agent> getList(String merId, String merName, String merMp) throws UnsupportedEncodingException {
		Agent n = null;
		UserEntity k=UserEntityUtil.getUserFromSession();
		if (!"".equals(merId) || !"".equals(merName) || !"".equals(merMp) ){
			n=new Agent();
			merName = new String(merName.getBytes("iso8859-1"), "utf-8");
			n.setMerId(merId);
			n.setMerName(merName);
			n.setMerMp(merMp);
			Agent h=agentService.find(n);
			if(h != null){
				List<Agent> list=new ArrayList<Agent>();
				List<Agent> agentList = get(list,h);
				Agent t=new Agent();
				t.setMerId(k.getMerId());
				if(agentList.contains(agentService.findByObject(t))){

					PageInfo<Agent> page = agentService.queryPageForList(h);
					PageInfo<Agent> newPage = new PageInfo<Agent>();
					List<Agent> hList=page.getList();
					List<Agent> nlist =new ArrayList<>();
					for (Agent a : hList) {
						Transaction tt = new Transaction();
						if(a!=null) {
							tt.setMerId(a.getMerId());
							tt = transactionService.findByObject(tt);
							if (tt != null) {
								a.setMerChantId(tt.getMerChantId());
							}
							nlist.add(a);
						}
					}
					newPage.setList(nlist);
					newPage.setPages(page.getPages());
					newPage.setTotal(page.getTotal());
					newPage.setPageSize(page.getPageSize());
					return newPage;
//					return agentService.query(n);
				}
			}
			Agent agent=new Agent();
			agent.setMerId("1");
			PageInfo<Agent> page = agentService.queryPageForList(n);
			PageInfo<Agent> newPage = new PageInfo<Agent>();
			List<Agent> hList=agentService.queryObjectForList();
			List<Agent> nlist =new ArrayList<>();
			for (Agent a : hList) {
				if(a != null) {
					Transaction t = new Transaction();
					t.setMerId(a.getMerId());
					t = transactionService.findByObject(t);
					if (t != null) {
						a.setMerChantId(t.getMerChantId());
					}
					nlist.add(a);
				}
			}
			newPage.setList(nlist);
			newPage.setPages(page.getPages());
			newPage.setTotal(page.getTotal());
			newPage.setPageSize(page.getPageSize());
			return newPage;
		}
		if(k.getMerId().startsWith("T")){
			PageInfo<Agent> page = agentService.queryPageForList(n);
			PageInfo<Agent> newPage = new PageInfo<Agent>();
			List<Agent> hList=page.getList();
			List<Agent> nlist =new ArrayList<>();
			for (Agent a : hList) {
				Transaction t = new Transaction();
				if(a!=null) {
					t.setMerId(a.getMerId());
					t = transactionService.findByObject(t);
					if (t != null) {
						a.setMerChantId(t.getMerChantId());
					}
					nlist.add(a);
				}
			}
			newPage.setList(nlist);
			newPage.setPages(page.getPages());
			newPage.setTotal(page.getTotal());
			newPage.setPageSize(page.getPageSize());
			return newPage;
		}
		n=new Agent();
		n.setOneMerId(k.getMerId());
		PageInfo<Agent> page = agentService.queryPageForList(n);
		PageInfo<Agent> newPage = new PageInfo<Agent>();
		List<Agent> hList=page.getList();
		List<Agent> nlist =new ArrayList<>();
		for (Agent a : hList) {
			Transaction t = new Transaction();
			if(a!=null) {
				t.setMerId(a.getMerId());
				t = transactionService.findByObject(t);
				if (t != null) {
					a.setMerChantId(t.getMerChantId());
				}
				String cno=a.getAccountNumber();
				if(!"".equals(cno)&&cno!=null) {
					cno = cno.substring(0, 3) + "****" + cno.substring(7, cno.length());
					a.setAccountNumber(cno);
				}
				nlist.add(a);
			}
		}
		newPage.setList(nlist);
		newPage.setPages(page.getPages());
		newPage.setTotal(page.getTotal());
		newPage.setPageSize(page.getPageSize());
		return newPage;
	}
	public List<Agent> get(List<Agent> list,Agent t){
		if("".equals(t.getOneMerId()) || null == t.getOneMerId()){
			return list;
		}
		if(!"".equals(t.getOneMerId()) && null != t.getOneMerId()){
			Agent agent=new Agent();
			agent.setMerId(t.getOneMerId());
			Agent n=agentService.findByObject(agent);
			list.add(n);
			get(list,n);
		}
		return list;
	}
	
	@RequestMapping("reset")
	@ResponseBody
	public String reset(String merId,String merName) {
		Agent n = new Agent();
		n.setMerId(merId);
		Agent h=agentService.findByObject(n);
		UserEntity k =new UserEntity();
		//k.setMerId(merId);
		k.setUserName(merName);
		UserEntity t=userService.findByObject(k);
		t.setPassWord("12345");
		UserEntity r=PasswordHelper.encryptPassword(t);
		userService.update(r);
		h.setPassword(r.getPassWord());
		return agentService.update(h);
	}
	
	@RequestMapping("alter")
	@ResponseBody
	public String alter(String merId,String merMp,String merName,String mailbox) {
		Agent n = new Agent();
		n.setMerId(merId);
		Agent h=agentService.findByObject(n);
		h.setMerMp(merMp);
		h.setMerName(merName);
		h.setMailbox(mailbox);
		UserEntity k=new UserEntity();
		k.setMerId(merId);
		UserEntity t=userService.findByObject(k);
		t.setTel(merMp);
		t.setUserName(merName);
		t.setEmail(mailbox);
		userService.update(t);
		//同步修改中间表信息
		Transaction tran = new Transaction();
		tran.setMerId(merId);
		Transaction tt = transactionService.findByObject(tran);
		if(tt != null){
			tt.setAgentName(merName);
			transactionService.update(tt);
		}
		return agentService.update(h);
	}
	
	@RequestMapping("editpwd")
	public String editpwd() {
		return "/system/agent/editpwd";
	}
	
	@RequestMapping("amend")
	@ResponseBody
	public String amend(String merId,String merName,String password,String newpassword) {
		Agent n = new Agent();
		n.setMerId(merId);
		Agent h=agentService.findByObject(n);
		
		UserEntity userEntity =new UserEntity();
		//userEntity.setMerId(merId);
		userEntity.setUserName(merName);
		UserEntity k =userService.findByObject(userEntity);
		k.setPassWord(newpassword);
		UserEntity r=PasswordHelper.encryptPassword(k);
		userService.update(r);
		h.setPassword(r.getPassWord());
		return agentService.update(h);
	}
	
	@RequestMapping("editfloorNumber")
	public String editfloorNumber(Model model) {
		List<AgentLevel> agentList = agentLevelService.getAgentLevel();
		model.addAttribute("agentLevel", agentList);
		model.addAttribute("agent", agentBasePriceService.getAgentLevel());
		return "/system/agent/editfloorNumber";
	}
	
	@RequestMapping("editgenerationFee")
	public String editgenerationFee() {
		return "/system/agent/editgenerationFee";
	}
	
	@RequestMapping("listrisks")
	public String editrisks() {
		return "/system/agent/listrisks";
	}
	
	@RequestMapping("listfloorNumber")
	@SystemLog(module = "代理商管理", methods = "查询下级代理商结算底价")
	public String listfloorNumber(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/agent/listfloorNumber";
	}
	
	@RequestMapping("lists")
	@SystemLog(module = "代理商管理", methods = "查询下级代理商")
	public String lists(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/agent/main";
	}
	
	@RequestMapping("edits")
	public String edit(Model model) {
		model.addAttribute("agent", agentService.queryObjectForList());
		return "/system/agent/add";
	}
	
	@RequestMapping("adds")
	@ResponseBody
	public String edits(String userName,String accountName,String email,String passWord,String tel,String merId){
		if(!"".equals(userName) && !"".equals(accountName) && !"".equals(email) && !"".equals(passWord) && !"".equals(tel) && !"".equals(merId) ){
			UserEntity user = new UserEntity();
			user.setAccountName(accountName);
			UserEntity t=userService.findByObject(user);
			if(t != null){
				return "fail";
			}
			UserEntity k=new UserEntity();
			k.setAccountName(accountName);
			k.setDate(YJ.formatDate(new Date())+YJ.formattime(new Date()));
			k.setEmail(email);
			k.setPassWord(passWord);
			k.setMerId(merId);
			k.setTel(tel);
			k.setUserName(userName);
			k.setLocked("1");
			PasswordHelper.encryptPassword(k);
			userService.save(k);
			return "success";
		}
		return "f";	
	}
	@RequestMapping("bindArea")
	@SystemLog(module = "代理商管理", methods = "代理商绑定区域")
	public String bindArea(Model model) {
		return "/system/agent/bindArea";
	}

	@RequestMapping(value="confirm",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String confirmBind(String merId, String merChantId, String province,String city,String region) {
		String message = agentAreaBindService.bindArea(merId, merChantId, province, city, region);
		return message;
	}
	 
}
