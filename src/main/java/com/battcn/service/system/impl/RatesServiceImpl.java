package com.battcn.service.system.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.TryCatchFinally;

import net.sf.jsqlparser.statement.delete.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Agent;
import com.battcn.entity.Rates;
import com.battcn.entity.RatesExample;
import com.battcn.entity.Transaction;
import com.battcn.entity.UserEntity;
import com.battcn.mapper.RatesMapper;
import com.battcn.service.system.AgentService;
import com.battcn.service.system.RatesService;
import com.battcn.service.system.TransactionService;
import com.battcn.util.CommonUtil;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

import static org.apache.commons.lang3.CharSetUtils.delete;

@Service
public class RatesServiceImpl implements RatesService{
	
	@Autowired
	private RatesMapper  ratesMapper;
	@Autowired
	public TransactionService transactionService;
	@Autowired
	private AgentService agentService;

	@Override
	public PageInfo<Rates> getList() {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		PageHelper.startPage(pageNum, pageSize);
		String orderField = request.getParameter("sort");
		String orderDirection = request.getParameter("order");
		if (StringUtil.isNotEmpty(orderField)) {
			PageHelper.orderBy(orderField);
			if (StringUtil.isNotEmpty(orderDirection)) {
				PageHelper.orderBy(orderField + " " + orderDirection);
			}
		}
		RatesExample example = new RatesExample();
		List<Rates> list = ratesMapper.selectByExample(example);
		return new PageInfo<Rates>(list);
	}

	@Override
	public Integer update(Rates Rate) {
		UserEntity k=UserEntityUtil.getUserFromSession();
		//按逗号分割传进来的数值
		String[] newAgentId = null;
		Integer count = 0;
		try{
			newAgentId = Rate.getAgentid().split(",");

		}catch(Exception e){

			return 0;
		}
		List<String> alists = Arrays.asList(newAgentId);
		List<String> alist = new ArrayList(alists);
		for(int i = 0; i< alist.size();i++) {
			if(alist.get(i).equals(k.getMerId())) {
				alist.remove(alist.get(i));
			}
		}
		String[] newMerchantId = Rate.getMerchantid().split(",");
		List<String> mlists = Arrays.asList(newMerchantId);
		List<String> mlist = new ArrayList(mlists);
		Transaction t = new Transaction();
		t.setMerId(k.getMerId());
		t = transactionService.findByObject(t);
		for(int i = 0; i< mlist.size();i++) {
			if(mlist.get(i).equals(t.getMerChantId())) {
				mlist.remove(mlist.get(i));
			}
		}
		BigDecimal big = new BigDecimal("100");
		BigDecimal bige = new BigDecimal(Rate.getRate().toString());
		RatesExample exCodes = new RatesExample();
		exCodes.or().andAislecodeEqualTo(Rate.getAislecode()).andAgentidEqualTo(k.getMerId());
		List<Rates> rl = ratesMapper.selectByExample(exCodes);
		BigDecimal low = new BigDecimal("0");
		BigDecimal heigh  = BigDecimal.valueOf(0.01);
		for(Rates r:rl){
			low = r.getRate();
		}
		Transaction h = new Transaction();
		List<Rates> list = new ArrayList<Rates>();


		//遍历传进的数组
		for(int i = 0; i < alist.size(); i++ ){
			RatesExample example = new RatesExample();
			example.or().andAgentidEqualTo(alist.get(i));
			list = ratesMapper.selectByExample(example);

			Transaction n=new Transaction();
			n.setMerId(alist.get(i));
			h = transactionService.findByObject(n);

			//根据代理商id查询修改人代理商姓名
			Agent m=new Agent();
			m.setMerId(alist.get(i));
			List<Agent> newm = agentService.queryObjectForList(m);
			String name = "";
			if(newm.size()>0){
				name = newm.get(0).getMerName();
			}

			//费率表有数据有数据
			if(list.size() > 0){
				List<Rates> listCode = new ArrayList<Rates>();
				for(Rates a : list){
					//查看该网关是否为空
					RatesExample exCode = new RatesExample();
					exCode.or().andAislecodeEqualTo(Rate.getAislecode()).andAgentidEqualTo(alist.get(i));
					listCode = ratesMapper.selectByExample(exCode);
					//网关相同更新费率
					if(a.getAislecode().equals(Rate.getAislecode())){
						if(bige.divide(big).compareTo(low)==-1)
						{
							count = 8888;
							return  count;
						}
						if(bige.divide(big).compareTo(heigh)==1)
						{
							count = 9999;
							return  count;
						}
						a.setRate(bige.divide(big));
						h.setRepayMentUpdateDate(new Date());//更新日期
						h.setRepayMentUpdateName(k.getUserName());//更新姓名
						transactionService.update(h);
						count = ratesMapper.updateByPrimaryKeySelective(a);
					} else if(!a.getAislecode().equals(Rate.getAislecode()) && listCode.size()==0) {
						//网关不同且费率表为空插入数据
						Rates nAgentRate = new Rates();
						nAgentRate.setD0fee(100);
						nAgentRate.setRate(bige.divide(big));
						nAgentRate.setAgentid(alist.get(i));
						nAgentRate.setAislecode(Rate.getAislecode());
						nAgentRate.setMerchantid(mlist.get(i));
						h.setRepayMentUpdateDate(new Date());//更新日期
						h.setRepayMentUpdateName(k.getUserName());//更新姓名
						transactionService.update(h);
						count = ratesMapper.insert(nAgentRate);
					} else if(!a.getAislecode().equals(Rate.getAislecode()) && listCode.size()==0) {
						//网关不同且费率表为空插入数据
						Rates nAgentRate = new Rates();
						nAgentRate.setD0fee(100);
						nAgentRate.setRate(bige.divide(big));
						nAgentRate.setAgentid(alist.get(i));
						nAgentRate.setAislecode(Rate.getAislecode());
						nAgentRate.setMerchantid(mlist.get(i));
//							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						h.setRepayMentUpdateDate(new Date());//更新日期
						h.setRepayMentUpdateName(k.getUserName());//更新姓名
						transactionService.update(h);
						count = ratesMapper.insert(nAgentRate);
					}
				}

			} else {
				//无数据直接插入
				Rates newAgentRate = new Rates();
				newAgentRate = new Rates();
				newAgentRate.setD0fee(100);
				newAgentRate.setRate(bige.divide(big));
				newAgentRate.setAgentid(alist.get(i));
				newAgentRate.setAislecode(Rate.getAislecode());
				newAgentRate.setMerchantid(mlist.get(i));
				h.setRepayMentUpdateDate(new Date());//更新日期
				h.setRepayMentUpdateName(k.getUserName());//更新姓名
				transactionService.update(h);
				count =  ratesMapper.insert(newAgentRate);
			}
		}
		return count;
	}

	@Override
	public PageInfo<Rates> queryList(String agentid) {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		PageHelper.startPage(pageNum, pageSize);
		String orderField = request.getParameter("sort");
		String orderDirection = request.getParameter("order");
		if (StringUtil.isNotEmpty(orderField)) {
			PageHelper.orderBy(orderField);
			if (StringUtil.isNotEmpty(orderDirection)) {
				PageHelper.orderBy(orderField + " " + orderDirection);
			}
		}
		RatesExample example = new RatesExample();
		RatesExample.Criteria cri = new  RatesExample().createCriteria();
		cri.andAgentidEqualTo(agentid);
		example.or(cri);
		List<Rates> list = ratesMapper.selectByExample(example);

		return new PageInfo<Rates>(list);
	}

	@Override
	public Integer insert(Rates rates) {
		Integer count = ratesMapper.insert(rates);
		return count;
	}

}
