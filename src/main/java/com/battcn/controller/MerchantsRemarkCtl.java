package com.battcn.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.battcn.entity.Agent;
import com.battcn.entity.AgentLevelMerType;
import com.battcn.entity.AgentRate;
import com.battcn.entity.MerChants;
import com.battcn.entity.MerChantsRate;
import com.battcn.entity.MerCode;
import com.battcn.entity.MerchantsRemark;
import com.battcn.entity.Rates;
import com.battcn.entity.Transaction;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AgentAreaBindService;
import com.battcn.service.system.AgentBasePriceService;
import com.battcn.service.system.AgentLevelMerTypeService;
import com.battcn.service.system.AgentLevelService;
import com.battcn.service.system.AgentRateService;
import com.battcn.service.system.AgentService;
import com.battcn.service.system.MerChantsService;
import com.battcn.service.system.MerCodeService;
import com.battcn.service.system.MerchantsRateService;
import com.battcn.service.system.RatesService;
import com.battcn.service.system.TransactionService;
import com.battcn.service.system.UserRoleService;
import com.battcn.service.system.UserService;
import com.battcn.service.system.impl.MerchantsRemarkSeraviceImpl;
import com.battcn.util.PasswordHelper;
import com.battcn.util.YJ;

@Controller
@RequestMapping("/remark/")
public class MerchantsRemarkCtl {
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
	@Autowired
	private MerchantsRemarkSeraviceImpl merchantsRemarkSeraviceImpl;
	@Autowired
	private AgentLevelMerTypeService agentLevelMerTypeService;
	@Autowired
	private MerchantsRateService merchantsRateService;
	@Autowired
	private AgentRateService agentRateService;
	@Autowired
	private RatesService ratesService;
	//开户
	@RequestMapping("openAccount")
	@Transactional
	public String openAccount(){
			MerchantsRemark remark = new MerchantsRemark();
			remark.setAgentstatus("N");
			List<MerchantsRemark> list = merchantsRemarkSeraviceImpl.getAllMessage(remark);
			for(MerchantsRemark rr:list){
				UserEntity userFormMap = new UserEntity();
				userFormMap.setAccountName(rr.getMermp());//accountnumber
				userFormMap.setPassWord("123456");//"123456"
				UserEntity r=PasswordHelper.encryptPassword(userFormMap);
				//merName mermp accountnumber password mail box onemerid 
				Agent n=new Agent(rr.getMername(), rr.getMermp(), rr.getMermp(), r.getPassWord(), ".com", "T00000009", System.currentTimeMillis()+"");
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
				UserEntity k=new UserEntity();
				k.setAccountName(rr.getMermp());
				k.setDate(YJ.formatDate(new Date())+YJ.formattime(new Date()));
				k.setEmail(".com");
				k.setPassWord("123456");
				k.setMerId(merId);
				k.setTel(rr.getMermp());
				k.setUserName(rr.getMername());
				k.setLocked("1");
				PasswordHelper.encryptPassword(k);
				userService.save(k);
			}
			
		return "success";
	}
	//绑关系
	@RequestMapping("bindopenAccount")
	//@Transactional
	public String bindopenAccount(){

		List<Agent> agent = agentService.queryObjectForList();
		for(Agent aa:agent){
			MerchantsRemark remarks = new MerchantsRemark();
			remarks.setMermp(aa.getMerMp());
			remarks.setAgentstatus("N");
			List<MerchantsRemark> lists = merchantsRemarkSeraviceImpl.getAllMessage(remarks);
			Transaction savetransation=new Transaction();
			savetransation.setMerId(aa.getMerId());
			savetransation.setAgentName(aa.getMerName());
			savetransation.setMerChantId(lists.get(0).getMerchantid());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			savetransation.setCreatDate(sdf.format(new Date()));
			savetransation.setAgentStatus(lists.get(0).getAgentstatus());
			String message = transactionService.save(savetransation);
			//查询此商户是不是别人的直推、如果是更改这些商户的代理商号为此商户的
			MerChants onem = new MerChants();
			onem.setOneMerId(lists.get(0).getMerchantid());
			List<MerChants> onelist = merChantsService.getList(onem);
			if(onelist.size() > 0){
				for(MerChants onemo: onelist){
					if(lists.get(0).getAgentid().equals(onemo.getAgentId())){
						onemo.setAgentId(aa.getMerId());
						merChantsService.update(onemo);
					}
				}
			}
			//查询此商户是不是别人的间推、如果是更改这些商户的代理商号为此商户的
			MerChants twom = new MerChants();
			twom.setTwoMerId(lists.get(0).getMerchantid());
			List<MerChants> twolist = merChantsService.getList(twom);
			if(twolist.size() > 0){
				for(MerChants twomo: twolist){
					if(lists.get(0).getAgentid().equals(twomo.getAgentId())){
						twomo.setAgentId(aa.getMerId());
						merChantsService.update(twomo);
					}
				}
			}
			//查询此商户是不是别人的间间推。如果是更改这些商户的代理商号为此商户的
			MerChants threem = new MerChants();
			threem.setThreeMerId(lists.get(0).getMerchantid());
			List<MerChants> threelist = merChantsService.getList(threem);
			if(threelist.size() > 0){
				for(MerChants threemo: threelist){
					if(lists.get(0).getAgentid().equals(threemo.getAgentId())){
						threemo.setAgentId(aa.getMerId());
						merChantsService.update(threemo);
					}
				}
			}
			MerchantsRemark nm = merchantsRemarkSeraviceImpl.getAll(lists.get(0).getMerchantid()).get(0);
			nm.setAgentid(aa.getMerId());
			merchantsRemarkSeraviceImpl.update(nm);
			//bnindAgentMessageUtil.openAgent(v.getMerMp());//绑定代理成功发送短信
			//同步带走代理之间的归属（带走代理，带走不算自己的下面四级代理）
			//自己是谁的直推、间推、间间推及间间推的直推，就是自己要带走的代理
			MerChants onea = new MerChants();
			onea.setOneMerId(lists.get(0).getMerchantid());
			List<MerChants> onealist = merChantsService.getList(onea);//直推列表
			MerChants twoa = new MerChants();
			twoa.setTwoMerId(lists.get(0).getMerchantid());
			List<MerChants> twoalist = merChantsService.getList(twoa);//间推列表
			MerChants threea = new MerChants();
			threea.setThreeMerId(lists.get(0).getMerchantid());
			List<MerChants> threealist = merChantsService.getList(threea);//间间推列表
			List<MerChants> allList = new ArrayList<MerChants>();//存储所有的商户
			List<MerChants> notAgentList = new ArrayList<MerChants>();//存储间间间推不是代理的人
			allList.addAll(onealist);
			allList.addAll(twoalist);
			allList.addAll(threealist);
			//遍历间间推列表拿到他们的商户号，查看
			//是谁的直推，为第四级别
			for(MerChants four:threealist){
				MerChants foura = new MerChants();
				foura.setOneMerId(four.getMerChantId());
				List<MerChants> fouralist = merChantsService.getList(foura);//间间推作为直推的列表
				//查看他的间间间推里面有没有代理，是代理的话就不算做四级之内
				for(MerChants agentOrNot:fouralist){
					//不是代理才添加进去
					if(agentOrNot.getAgentStatus().equals("N")){
						notAgentList.add(agentOrNot);
					}
				}
				allList.addAll(notAgentList);
			}
//			for(MerChants last:allList){
//				if(!last.getAgentStatus().equals("N")){//只管代理
//					Transaction trana=new Transaction();//去中间表查该商户的代理商号
//					trana.setMerChantId(last.getMerChantId());
//					Transaction trananeed=transactionService.findByObject(trana);
//					Agent agenta = new Agent();
//					agenta.setMerId(trananeed.getMerId());
//					Agent agentneed = agentService.find(agenta);//查询出该代理,并将他的归属代理修改为此（开户绑定关系的代理）
//					agentneed.setOneMerId(aa.getMerId());
//					agentService.update(agentneed);
//					return "success";
//				}
//				
//			}
			AgentLevelMerType levelType = new AgentLevelMerType();
			levelType.setAgentLevel(lists.get(0).getAgentstatus());
			//查询代理等级对应的商户等级
			AgentLevelMerType agentmerType = agentLevelMerTypeService.findByObject(levelType);
			//升级代理商，默认将代理费的通道费率设置入表（费率来自商户通道表）
			List<MerChantsRate> rateList =  merchantsRateService.getMerchantsRateList("0000", agentmerType.getMerType());
			for(MerChantsRate rate:rateList){
				//无卡的
				if(rate.getIsrepayment().equals("N")){
					AgentRate agentRate = new AgentRate();
					agentRate.setMerchantid(lists.get(0).getMerchantid());
					agentRate.setAgentid(aa.getMerId());
					agentRate.setRate(rate.getRate());
					agentRate.setD0fee(rate.getD0fee().intValue());
					agentRate.setAislecode(rate.getAislecode());
					agentRateService.insert(agentRate);
					
				} else if(rate.getIsrepayment().equals("Y")){//还款的
					Rates rates = new Rates();
					rates.setMerchantid(lists.get(0).getMerchantid());
					rates.setAgentid(aa.getMerId());
					rates.setRate(rate.getRate());
					rates.setD0fee(rate.getD0fee().intValue());
					rates.setAislecode(rate.getAislecode());
					ratesService.insert(rates);
				}
			}
		}
		return "success";
	}
	//填写代理关系
	@RequestMapping("putAgent")
	@Transactional
	public String putAgent(){
		List<Agent> agent = agentService.queryObjectForList();
		for(Agent aa:agent){
			//查询该代理是谁的上级
			MerchantsRemark remarks = new MerchantsRemark();
			remarks.setUpmermp(aa.getMerMp());
			remarks.setAgentstatus("N");
			List<MerchantsRemark> lists = merchantsRemarkSeraviceImpl.getAllMessage(remarks);
			//不为空，拿他下级的手机号去找是不是代理(他的下级是多人)
			if(lists.size()!=0){
				for(MerchantsRemark mmm: lists){
					Agent aaa = new Agent();
					aaa.setMerMp(mmm.getMermp());
					Agent asss = agentService.find(aaa);
					//如果他的下级是代理
					if(asss!=null){
						asss.setOneMerId(aa.getMerId());
						agentService.update(asss);
					}
				}
			}
		}
		return "success";
	}
	//填写商户推荐关系关系
	@RequestMapping("putOTT")
	@Transactional
	public String putOTT(){
		//查找出所有商户
		MerchantsRemark remarks = new MerchantsRemark();
		List<MerchantsRemark> lists = merchantsRemarkSeraviceImpl.getAllMessage(remarks);
		for(MerchantsRemark mmm: lists){
			//遍历该商户，查看该商户是谁的上级
			MerchantsRemark rrrr = new MerchantsRemark();
			rrrr.setUpmermp(mmm.getMermp());
			List<MerchantsRemark> listrrr = merchantsRemarkSeraviceImpl.getAllMessage(rrrr);
			//遍历该商户查看他的上级是谁
			MerchantsRemark qqqq = new MerchantsRemark();
			String uupp = mmm.getUpmermp();
			List<MerchantsRemark> listsqqq = new ArrayList<MerchantsRemark>();
			List<MerchantsRemark> listseee = new ArrayList<MerchantsRemark>();
			if(!uupp.equals("1")){
				qqqq.setMermp(uupp);
				List<MerchantsRemark> listsqqqqq = merchantsRemarkSeraviceImpl.getAllMessage(qqqq);
				listsqqq.addAll(listsqqqqq);
				if(listsqqq.size()!=0){
					if(listsqqq.size()!=0){
						//查看他上级的上级是谁
						MerchantsRemark eeee = new MerchantsRemark();
						String upm = listsqqq.get(0).getUpmermp();
						if(!upm.equals("1")){
							eeee.setMermp(upm);
							List<MerchantsRemark> listseeeeee = merchantsRemarkSeraviceImpl.getAllMessage(eeee);
							listseee.addAll(listseeeeee);
						}
					}
				}
			}
			if(listrrr.size()!=0){
				//遍历这些下级数据，将他上级的商户号，填入他们的直推
				for(MerchantsRemark nnnn:listrrr){
					nnnn.setOnemerid(mmm.getMerchantid());
					if(listsqqq.size()!=0){
						if(listsqqq.size()!=0){
							nnnn.setTwomerid(listsqqq.get(0).getMerchantid());
						} else {
						nnnn.setTwomerid(null);
						}
					} else {
						nnnn.setTwomerid(null);
					}
					if(listseee.size()!=0){
						if(listseee.size()!=0){
							nnnn.setThreemerid(listseee.get(0).getMerchantid());
						} else {
							nnnn.setThreemerid(null);
						}
					} else {
						nnnn.setThreemerid(null);
					}
					merchantsRemarkSeraviceImpl.update(nnnn);
				}
			}
		}
		
		return "success";
	}
	//填写代理推荐关系关系
		@RequestMapping("changeAgent")
		@Transactional
		public String changeAgent(){
			//查找出所有不是代理的商户商户
			MerchantsRemark remarks = new MerchantsRemark();
			List<MerchantsRemark> lists = merchantsRemarkSeraviceImpl.getAllMessage(remarks);
			for(MerchantsRemark mmm: lists){
				if(mmm.getAgentstatus().equals("N")){
					//该商户查看他的上级是谁
					MerchantsRemark qqqq = new MerchantsRemark();
					String uupp = mmm.getUpmermp();
					if(!uupp.equals("1")){
						qqqq.setMermp(uupp);
						List<MerchantsRemark> listsqqqqq = merchantsRemarkSeraviceImpl.getAllMessage(qqqq);
						//他上级要不是代理，继续查他上级的上级
						if(listsqqqqq.size()!=0){
							if(listsqqqqq.get(0).getAgentstatus().equals("N")){
								String agentId = getAgentId(listsqqqqq.get(0));
								mmm.setAgentid(agentId);
								merchantsRemarkSeraviceImpl.update(mmm);
							} else {
								mmm.setAgentid(listsqqqqq.get(0).getAgentid());
								merchantsRemarkSeraviceImpl.update(mmm);
							}
						}
					} else {
					mmm.setAgentid("T00000009");
					merchantsRemarkSeraviceImpl.update(mmm);
					}
				}
			}
			
			return "success";
		}
		
		public String getAgentId(MerchantsRemark listsqqqqq){
			String upmermp = listsqqqqq.getUpmermp();
			if(!upmermp.equals("1")){
				MerchantsRemark qqqq = new MerchantsRemark();
				qqqq.setMermp(upmermp);
				List<MerchantsRemark> listsqqqqqmmmm = merchantsRemarkSeraviceImpl.getAllMessage(qqqq);
				if(listsqqqqqmmmm.size()!=0){
					if(listsqqqqqmmmm.get(0).getAgentstatus().equals("N")){
						return getAgentId(listsqqqqqmmmm.get(0));
					}
					return listsqqqqqmmmm.get(0).getAgentid();
				}
			}
			return "T00000009";
		}
	
}
